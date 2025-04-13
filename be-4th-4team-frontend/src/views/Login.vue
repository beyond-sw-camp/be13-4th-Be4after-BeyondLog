<template>
    <form @submit.prevent="submitClick">
        <h1>로그인</h1>
        <div>
            <div>
                <input type="text" id="EmailInput" placeholder="E-mail" v-model.trim="formData.email" required>
            </div>
            <div>
                <input type="password" id="PwdInput" placeholder="Password" v-model.trim="formData.password" required>
            </div>
            <div>
                <button type="submit">로그인</button>
            </div>
        </div>
    </form>
</template>

<script setup>
import { reactive, toRaw } from 'vue';
import { useAuthStore } from '@/store/auth';

    const formData=reactive({
        email: '',
        password: ''
    });

    const submitClick = () =>{
        const authStore = useAuthStore();

        authStore.login(toRaw(formData));
    }

</script>

<style lang="scss" scoped>

</style>