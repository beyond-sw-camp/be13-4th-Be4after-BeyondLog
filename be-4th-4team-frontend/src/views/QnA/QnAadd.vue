<template lang="">
    <div>
        <div class="col-md-7 col-lg-8">
            <h4 class="mb-3">{{ isEditMode ? "질문 수정" : "질문하기" }}</h4>
            <form class="needs-validation" @submit.prevent="submitForm">
                <div class="col-sm-6">
                    <label for="firstName" class="form-label">제목</label>
                    <input type="text" class="form-control" id="firstName" placeholder="" v-model="title" />
                    <div class="invalid-feedback">Valid first name is required.</div>
                </div>

                <div class="col-sm-6">
                    <label for="lastName" class="form-label">내용</label>
                    <input type="text" class="form-control" id="lastName" placeholder="" v-model="content" />
                    <div class="invalid-feedback">Valid last name is required.</div>
                </div>

                <hr class="my-4" />

                <button class="w-10 btn btn-primary btn-lg" type="submit">{{ isEditMode ? "수정" : "저장" }}</button>
            </form>
        </div>
    </div>
</template>

<script setup>
import apiClient from "@/api";
import {ref, onMounted} from "vue";
import {useRouter, useRoute} from "vue-router";

const route = useRoute();
const router = useRouter();

const qnaId = Number(route.query.id || "");
const title = ref(route.query.title || "");
const content = ref(route.query.content || "");
const isEditMode = ref(!!route.query.title);

const submitForm = async () => {
    const params = {
        title: title.value,
        content: content.value,
    };

    try {
        if (isEditMode.value) {
            // ?parentId=1
            await apiClient.put(`/qna/${qnaId}`, params);
            alert(" qna가 수정되었습니다.");
            router.push(`/qna/${qnaId}`);
        } else {
            const response = await apiClient.post("/qna", params);
            alert("qna가 생성되었습니다.");
            router.push(`/qna`);
        }
    } catch (error) {
        alert(error.response?.data.message || "알 수 없는 오류 발생");
    }
};
</script>
