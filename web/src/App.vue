<template>
  <a-layout>
    <the-header></the-header>
<!--      <router-view/>-->
    <home-view></home-view>
    <the-footer></the-footer>
  </a-layout>
</template>
<script>
  import { defineComponent, ref } from 'vue';
  import TheHeader from '@/components/TheHeader.vue';
  import TheFooter from '@/components/TheFooter.vue';
  import HomeView from '@/views/HomeView.vue';

  export default defineComponent({
    components: {
      TheHeader,
      TheFooter,
      HomeView,
    },
  })
</script>
