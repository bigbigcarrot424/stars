<template>
    <a-layout-header>
        <div class="logo" />
        <a-menu
                theme="dark"
                mode="horizontal"
                :style="{ lineHeight: '64px' }"
        >
            <a-menu-item key="1"><router-link to="/square">广场</router-link></a-menu-item>
            <a-menu-item key="2"><router-link to="/follow">关注</router-link></a-menu-item>
            <a-menu-item key="3"><router-link to="/publish">发帖</router-link></a-menu-item>
        </a-menu>
    </a-layout-header>
</template>

<script>
    export default {
        name: "TheHeader",
    }
</script>

<style scoped>

</style>