<template lang="">
    <div>
        <div class="list-group" v-if="data">
            <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
                <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0" />
                <div class="d-flex gap-2 w-100 justify-content-between">
                    <div>
                        <h6 class="mb-0">{{ data.title }}</h6>
                        <p class="mb-0 opacity-75">{{ data.content }}</p>
                    </div>
                    <small class="opacity-50 text-nowrap">{{ formatDate(data.createdAt) }}</small>
                    <ul class="nav nav-pills">
                        <li class="nav-item nav-link active" @click="goToEditPage(data)">수정</li>
                        <li class="nav-item nav-link active" @click="deleteQna(data.id)">삭제</li>
                    </ul>
                </div>
            </a>
            <a v-if="data.replies.length" href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
                <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0" />
                <div class="d-flex gap-2 w-100 justify-content-between">
                    <div>
                        <h6 class="mb-0">{{ data.replies[0].title }}</h6>
                        <p class="mb-0 opacity-75">{{ data.replies[0].content }}</p>
                    </div>
                    <small class="opacity-50 text-nowrap">{{ formatDate(data.replies[0].createdAt) }}</small>
                </div>
            </a>
        </div>
        <div v-else-if="loading">로딩 중...</div>
        <div v-else-if="error">오류 발생: {{ error }}</div>
        <div v-else>Q&A 정보를 찾을 수 없습니다.</div>
    </div>
</template>

<script setup>
import apiClient from "@/api";
import {ref, onMounted} from "vue";
import {useRouter, useRoute} from "vue-router";
import {format} from "date-fns";

const route = useRoute();
const router = useRouter();

const qnaId = Number(route.params.id);

const data = ref(null); // 단일 객체를 담을 것이므로 null로 초기화
const loading = ref(false);
const error = ref(null);

const formatDate = (dateString) => {
    if (!dateString) return "";
    try {
        return format(new Date(dateString), "yyyy-MM-dd HH:mm:ss");
    } catch (error) {
        console.error("날짜 포맷 오류:", error);
        return dateString;
    }
};

const fetchData = async () => {
    loading.value = true;
    error.value = null;
    try {
        const response = await apiClient.get(`/qna/${qnaId}`);
        if (response.status === 200) {
            console.log(response.data);
            data.value = response.data[0]; // 단일 객체 할당
        } else {
            alert("데이터 조회 실패");
        }
    } catch (err) {
        console.error("Q&A 데이터 조회 오류:", err);
    } finally {
        loading.value = false;
    }
};

// 수정
const goToEditPage = (data) => {
    router.push({
        name: "qnaAdd",
        query: {
            id: data.id,
            title: data.title,
            content: data.content,
        },
    });
};

// 삭제
const deleteQna = async (id) => {
    if (!confirm("정말로 삭제하시겠습니까?")) return;

    try {
        await apiClient.delete(`/qna/${id}`);
        alert("qna가 삭제되었습니다.");
        router.push("/qna");
    } catch (error) {
        console.error("삭제 실패:", error.response?.data?.message || error.message);
    }
};

onMounted(() => {
    fetchData();
});
</script>
