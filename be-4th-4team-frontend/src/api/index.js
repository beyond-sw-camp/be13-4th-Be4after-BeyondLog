import axios from "axios";
import { useAuthStore } from "@/store/auth";

const apiClient = axios.create({
    baseURL: 'http://localhost:8088/api/v1',
    timeout: 2000,
});

// 요청 인터셉터
apiClient.interceptors.request.use(
    (config) => {
        if (config._skipInterceptor) return config;

        const accessToken = localStorage.getItem('accessToken');
        if (accessToken) {
            config.headers['Authorization'] = `Bearer ${accessToken}`;
        }

        return config;
    },
    (error) => Promise.reject(error)
);

// 응답 인터셉터
apiClient.interceptors.response.use(
    (response) => response,
    async (error) => {
        const originalRequest = error.config;

        if (error.response?.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;

            const refreshToken = localStorage.getItem('refreshToken');
          
            if (!refreshToken) {
                console.log("리프레시 토큰이 없습니다.");
                return Promise.reject(error);
            }

            try {
                const response = await apiClient.post(
                    '/user/refresh',
                    null,
                    {
                        headers: {
                            'Authorization': `Bearer ${refreshToken}`,
                        },
                        _skipInterceptor: true
                    }
                );

                const newAccessToken = response.data.accessToken;
                localStorage.setItem('accessToken', newAccessToken);

                return apiClient(originalRequest);
            } catch (err) {
                const authStore = useAuthStore();
                authStore.logout();
                return Promise.reject(err);
            }
        }

        return Promise.reject(error);
    }
);

export default apiClient;
