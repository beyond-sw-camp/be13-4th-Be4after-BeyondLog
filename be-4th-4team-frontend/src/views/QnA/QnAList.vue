<template>
    <div>
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="/qna/add" class="nav-link active" aria-current="page">질문 등록</a></li>
        </ul>
        <div v-if="loading">로딩 중...</div>
        <div v-else-if="error">오류 발생: {{ error }}</div>
        <table v-else-if="data && data.length > 0" class="table table-striped table-hover text-center">
            <thead>
                <tr>
                    <th>제목</th>
                    <th>등록일</th>
                    <th>답변여부</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in data" :key="item.id" @click="goToProjectDetail(item.id)">
                    <td>{{ item.title }}</td>
                    <td>{{ item.createdAt }}</td>
                    <td>{{ item.replies.length ? "답변 완료" : "미답변" }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import apiClient from "@/api";
import {ref, computed, onMounted} from "vue";
import {useRouter} from "vue-router";

const router = useRouter();
const data = ref(null);
const loading = ref(false);
const error = ref(null);

// 데이터 가져오는 함수
const fetchData = async () => {
    loading.value = true;
    error.value = null;
    try {
        const response = await apiClient.get("/qna");
        if (response.status === 200) {
            console.log(response.data);
            data.value = response.data; // 응답 데이터 할당
        } else {
            alert("데이터 조회 실패");
        }
    } catch (err) {
        console.error("Q&A 데이터 조회 오류:", err);
    } finally {
        loading.value = false;
    }
};

const goToProjectDetail = (no) => {
    router.push(`/qna/${no}`);
};

// 컴포넌트가 마운트될 때 데이터 가져오기
onMounted(() => {
    fetchData();
});
</script>
