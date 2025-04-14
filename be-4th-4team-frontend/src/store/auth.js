import apiClient from "@/api";
import { defineStore } from "pinia";
import { reactive, ref, onMounted } from "vue";
import { useRouter } from "vue-router";

export const useAuthStore = defineStore('auth', () => {
    const router = useRouter();
    const isLoggedIn = ref(false);

    const userInfo = reactive({
        email: '',
        username: '',
    });

    onMounted(() => {
        checkLogin();
    });

    const login = async (loginData) => {
        try {
            console.log(loginData);
            const response = await apiClient.post('/auth/login', loginData);
            const { accessToken, refreshToken } = response.data?.data ?? {};

            if (response.status === 201) {
                const parseToken = parseJwt(accessToken);

                localStorage.setItem('accessToken', accessToken);
                localStorage.setItem('refreshToken', refreshToken);
                localStorage.setItem('userInfo', JSON.stringify({
                    username: parseToken.username,
                    email: parseToken.email,
                }));

                Object.assign(userInfo, JSON.parse(localStorage.getItem('userInfo')));
                isLoggedIn.value = true;

                router.push({ name: 'home' });
            }
        } catch (error) {
            if (error.response?.data?.code === 400) {
                alert(error.response.data.message);
            } else {
                alert('에러가 발생했습니다.');
            }
        }
    };

    const checkLogin = () => {
        const accessToken = localStorage.getItem('accessToken');

        if (accessToken) {
            const savedUserInfo = JSON.parse(localStorage.getItem('userInfo'));
            userInfo.email = savedUserInfo.email;
            userInfo.username = savedUserInfo.username;
            isLoggedIn.value = true;
        } else {
            isLoggedIn.value = false;
        }
    };

    const logout = async () => {
        try {
            const accessToken = localStorage.getItem('accessToken');

            if (isInvalidAccessToken(accessToken)) {
                alert('다시 로그인해 주세요.');
                logoutUser();
                return;
            }

            const response = await apiClient.post('/auth/logout');

            if (response.status === 200) {
                logoutUser();
            }
        } catch (error) {
            console.log(error);
            alert('에러가 발생했습니다.');
        }
    };

    const logoutUser = () => {
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
        localStorage.removeItem('userInfo');

        isLoggedIn.value = false;
        userInfo.email = '';
        userInfo.username = '';

        router.push({ name: 'login' });
    };

    const isInvalidAccessToken = (token) => {
        try {
            const decoded = parseJwt(token);
            const currentTime = Math.floor(Date.now() / 1000);
            return decoded.exp <= currentTime;
        } catch (error) {
            return true;
        }
    };

    const parseJwt = (token) => {
        try {
            const base64Url = token.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(atob(base64).split('').map((c) =>
                '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
            ).join(''));
            return JSON.parse(jsonPayload);
        } catch (e) {
            return null;
        }
    };
    return { isLoggedIn, userInfo, login, checkLogin, logout };
});
