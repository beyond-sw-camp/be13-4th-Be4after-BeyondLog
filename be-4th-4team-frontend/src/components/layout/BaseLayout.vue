<template>
  <header>
    <Header></Header>
  </header>
  <main>
    <RouterView></RouterView>
  </main>
</template>

<script setup>
  import Header from '@/components/common/Header.vue';
</script>