<template>
    <a-descriptions title="个人信息" bordered :column="4">
        <template #extra>
            <a-button type="primary" v-if="!followInfo.status" size="large" @click="follow" shape="round">关注</a-button>
            <a-button type="primary" v-if="followInfo.status" @click="unfollow" danger shape="round" size="large">取消关注</a-button>
        </template>
        <a-descriptions-item label="用户昵称：" :span="1">
            <span>{{user.name}}</span>
        </a-descriptions-item>
        <a-descriptions-item label="性别：" :span="1">
            <span>{{user.gender === 'male' ? '男' : '女'}}</span>
        </a-descriptions-item>
        <a-descriptions-item label="年龄：" :span="1">
            <span>{{user.age}}</span>
        </a-descriptions-item>
        <a-descriptions-item label="状态：" :span="1">
            <div>
                <a-badge status="default" text="离线"/>
            </div>
        </a-descriptions-item>

        <a-descriptions-item label="所在兴趣圈：" :span="4">{{user.circles}}</a-descriptions-item>

        <a-descriptions-item label="自我介绍：" :span="3">
            <div>
                {{user.selfIntro}}
            </div>
        </a-descriptions-item>
        <a-descriptions-item label="头像：" :span="1">
            <a-avatar :size="64" :src="'http://localhost:8080/file/avatar/' + user.avatar" alt="Han Solo"/>
        </a-descriptions-item>

        <a-descriptions-item label="个性标签">
            <div>
                <span v-for="(tag, index) in tags" :key="tag">
                    <a-tag :color="color[index % 7]">{{tag}}</a-tag>
                </span>
            </div>
        </a-descriptions-item>
    </a-descriptions>
</template>

<script>
    import { UserOutlined } from '@ant-design/icons-vue';
    import { PlusOutlined, LoadingOutlined } from '@ant-design/icons-vue';
    import { message } from 'ant-design-vue';
    import { defineComponent, ref, onMounted, computed, reactive, toRefs, nextTick } from 'vue';
    import { Modal } from 'ant-design-vue';
    import store from '@/store'
    import axios from 'axios'
    import  {useRouter}  from "vue-router";


    export default {
        name: "AdminInfo",
        components: {
            UserOutlined,
            LoadingOutlined,
            PlusOutlined,
        },
        setup(){
            const SERVER = process.env.VUE_APP_SERVER;
            const user = ref();
            user.value = {};

            const tags = ref();
            const color = ['pink','red','orange','green','cyan','blue','purple'];
            const router = useRouter();
            /**
             * 获取个人详细信息
             */
            let authorId = router.currentRoute.value.query.userId
            const getUserInfo = () => {
                axios.get(SERVER + "/user/info/" + authorId).then((response) => {
                    const data = response.data.content;
                    user.value = data;
                    if(user.value.gender){
                        user.value.gender === 'male' ? '男' : '女';
                    }
                    tags.value = JSON.parse(user.value.tags);
                })
            }

            /**
             * 关注 / 取关
             */

            const followInfo = ref({
            });

            const getIfFollowed = () =>{
                axios.get(`${SERVER}/follow/ifFollowed/${store.state.user.id}/${authorId}`).then((response) => {
                    if (response.data.success){
                        const data = response.data.content;
                        followInfo.value = data;
                        followInfo.value.status = true;
                    }else {

                    }
                })
            }

            const follow = () => {
                axios.get(`${SERVER}/follow/follow/${store.state.user.id}/${authorId}`).then((response) => {
                    if (response.data.success){
                        const data = response.data.content;
                        followInfo.value = data;
                        followInfo.value.status = true;
                        message.success("关注成功！");
                    }else {
                        message.error(response.data.message)
                    }
                })
            }

            const unfollow = () => {
                axios.get(`${SERVER}/follow/unfollow/${store.state.user.id}/${authorId}`).then((response) => {
                    if (response.data.success){
                        const data = response.data.content;
                        followInfo.value.status = false;
                        message.success("取消关注成功！");
                    }else {
                        message.error(response.data.message);
                    }
                })
            }


            onMounted(() =>{
                getUserInfo();
                getIfFollowed();
            })

            return {
                SERVER,
                confirm,
                // 个人信息：
                user,

                color,
                tags,
                follow,
                unfollow,
                followInfo,
            };
        }
    }
</script>

<style scoped>

</style>