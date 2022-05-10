<template>
<!--    <a-layout-sider width="200" style="background: #fff"  :style="{background: '#fff', overflow: 'auto', height: '100vh', position: 'fixed', left: 0, top: 0, bottom: 0 }">-->
<!--    <a-layout-sider width="200" :style="{background: '#fff', height: '100vh'}">-->
    <a-layout-sider
            :style="{ overflow: 'auto', height: '100vh', position: 'fixed', left: 0, top: 0, bottom: 0, paddingTop: '60px'}"
    >
        <a-menu

                mode="inline"
                style="height: 100%"
        >

<!--            <div class="sider">-->
<!--                聊  天  列  表-->
<!--            </div>-->
<!--            <a-menu-item disabled key="32" class="sider-title" style="padding-left: 70px; padding-top: 20px">聊  天  列  表</a-menu-item>-->
            <a-divider/>
            <a-sub-menu key="sub1">
                <template #title>
                <span>
                  我的好友
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
                  兴趣圈
                </span>
                </template>
                <a-menu-item key="5">option5</a-menu-item>
                <a-menu-item key="6">option6</a-menu-item>
                <a-menu-item key="7">option7</a-menu-item>
                <a-menu-item key="8">option8</a-menu-item>
            </a-sub-menu>
        </a-menu>
    </a-layout-sider>
</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import axios from 'axios'
    import store from '@/store'
    import { HomeOutlined, DeleteOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import  {useRouter}  from "vue-router";
    import  { message }  from "ant-design-vue";
    export default {
        name: "TheSider",
        setup(){
            const SERVER = process.env.VUE_APP_SERVER;
            const followList = ref();
            const getFollowList = () => {
                axios.get(SERVER + "/follow/followList/" + store.state.user.id).then((response) => {
                    const data = response.data;
                    if (data){
                        followList.value = data.content ? data.content :[];
                    }
                })
            }
            return{
                getFollowList,
            }
        }
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
    .sider-title {
        text-align: center !important;
    }
</style>