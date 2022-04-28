<template>
  <a-layout-content style="padding: 0 50px">
    <a-breadcrumb style="margin: 16px 0">
      <a-breadcrumb-item>Home</a-breadcrumb-item>
      <a-breadcrumb-item>List</a-breadcrumb-item>
      <a-breadcrumb-item>App</a-breadcrumb-item>
    </a-breadcrumb>
    <a-layout has-sider :style="{ marginLeft: '200px', padding: '24px 0', background: '#fff'}">
      <the-sider></the-sider>
      <a-layout-content :style="{ padding: '0 30px', minHeight: '800px' }">
        <router-view/>
      </a-layout-content>

    </a-layout>
  </a-layout-content>
</template>

<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import TheSider from '@/components/TheSider.vue';
  export default defineComponent({
    components: {
      TheSider,
    }
  })
</script>
<style>
  #components-layout-demo-top-side .logo {
    float: left;
    width: 120px;
    height: 31px;
    margin: 16px 24px 16px 0;
    background: rgba(255, 255, 255, 0.3);
  }

  .ant-row-rtl #components-layout-demo-top-side .logo {
    float: right;
    margin: 16px 0 16px 24px;
  }

  .site-layout-background {
    background: #fff;
  }
</style>