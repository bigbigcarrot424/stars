<template>
<!--    <a-layout-sider width="200" style="background: #fff"  :style="{background: '#fff', overflow: 'auto', height: '100vh', position: 'fixed', left: 0, top: 0, bottom: 0 }">-->
    <a-layout-sider width="200" :style="{background: '#fff', height: '100vh'}">

        <a-menu

                mode="inline"
                style="height: 100%"
        >
            <div class="sider">
                聊  天  列  表
            </div>
            <a-divider/>
            <a-sub-menu key="sub1">
                <template #title>
                <span>
                  subnav 1
                </span>
                </template>
                <a-menu-item key="1">option1</a-menu-item>
                <a-menu-item key="2">option2</a-menu-item>
                <a-menu-item key="3">option3</a-menu-item>
                <a-menu-item key="4">option4</a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub2">
                <template #title>
                <span>
                  subnav 2
                </span>
                </template>
                <a-menu-item key="5">option5</a-menu-item>
                <a-menu-item key="6">option6</a-menu-item>
                <a-menu-item key="7">option7</a-menu-item>
                <a-menu-item key="8">option8</a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub3">
                <template #title>
                <span>
                  subnav 3
                </span>
                </template>
                <a-menu-item key="9">option9</a-menu-item>
                <a-menu-item key="10">option10</a-menu-item>
                <a-menu-item key="11">option11</a-menu-item>
                <a-menu-item key="12">option12</a-menu-item>
            </a-sub-menu>
        </a-menu>
    </a-layout-sider>
</template>

<script>
    export default {
        name: "TheSider"
    }
</script>

<style scoped>
    .sider {
        height: 15px;
        background: rgba(255, 255, 255, 0.2);
        margin: 5px;
        text-align: center;
        font-size: 15px;
    }
</style>