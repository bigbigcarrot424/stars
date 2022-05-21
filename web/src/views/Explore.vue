<template>
    <a-card title="为你推荐好友：">
        <a-row :gutter="16">
            <a-col :span="8" v-for="(item, index) in recommendList" style="margin-bottom: 20px">
                <a-card hoverable style="width: 320px">
                    <template #actions>
                        <home-outlined key="home" @click="toUserInfo(item.id)"/>
                        <heart-outlined key="follow" @click="followUser(item.id)"/>

                    </template>
                    <a-card-meta :title="item.name" :description="item.selfIntro">
                        <template #avatar>
                            <a-avatar :src="SERVER + '/file/avatar/' + item.avatar" />
                        </template>
                    </a-card-meta>
                </a-card>
            </a-col>
        </a-row>
    </a-card>

    <a-divider></a-divider>

    <a-card title="为你推荐兴趣圈：">
        <a-row :gutter="16">
            <a-col :span="8" v-for="(item, index) in recommendCircleList" style="margin-bottom: 20px">
                <a-card hoverable style="width: 320px">
                    <template #actions>
                        <home-outlined key="home" @click="toCircleSquare(item.id)"/>
                        <a-popconfirm title="确认加入兴趣圈？" ok-text="是" cancel-text="否"  @confirm="partInCircle(circle.id, circle.managerId)" @cancel="">
                            <import-outlined key="import"/>
                        </a-popconfirm>

                    </template>
                    <div style="text-align: center; font-size: 20px">{{item.circleName}}</div>
                    <div style="font-size: 15px; color: rgba(0, 0, 0, 0.45); margin-top: 10px">兴趣圈介绍：{{item.intro}}</div>
                </a-card>
            </a-col>
        </a-row>
    </a-card>

</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed,} from 'vue';
    import  {useRouter}  from "vue-router";
    import {StarOutlined, LikeOutlined, MessageOutlined, HomeOutlined, HeartOutlined, ImportOutlined} from '@ant-design/icons-vue';
    import {message} from 'ant-design-vue'
    import axios from 'axios'
    import store from '@/store'

    export default defineComponent({
        name: "Explore",
        components: {
            StarOutlined,
            LikeOutlined,
            MessageOutlined,
            HomeOutlined,
            HeartOutlined,
            ImportOutlined
        },
        setup() {
            const SERVER = process.env.VUE_APP_SERVER;
            let recommendList = ref();
            let recommendCircleList = ref();

            const getFollowList = () => {
                axios.get(SERVER + "/recommend/clusterUser/" + store.state.user.name).then((response) => {
                    const data = response.data;
                    if (data){
                        recommendList.value = data.content ? data.content :[];
                    }
                })
            }

            const router = useRouter();
            const toUserInfo = (userId) => {
                router.push ({
                    path:"/othersInfo",
                    query:{
                        userId: userId
                    }
                })
            }

            const followUser = (userId) => {
                axios.get(`${SERVER}/follow/follow/${store.state.user.id}/${userId}`).then((response) => {
                    if (response.data.success){
                        const data = response.data.content;
                        message.success("关注成功！");
                    }else {
                        message.error(response.data.message)
                    }
                })
            }


            const getCircleList = () => {
                axios.get(SERVER + "/recommend/clusterCircle/" + store.state.user.name).then((response) => {
                    const data = response.data;
                    if (data){
                        recommendCircleList.value = data.content ? data.content :[];
                    }
                })
            }

            const toCircleSquare = (circleId) => {
                router.push ({
                    path:"/circleSquare",
                    query:{
                        circleId: circleId
                    }
                })
            }

            const partInCircle = (circleId, managerId) => {
                axios.get(process.env.VUE_APP_SERVER + '/circle/joinCircle/' + store.state.user.id + '/' + circleId + '/' + managerId).then((response) => {
                    const data = response.data;
                    if (data.success){
                        getCircleList();
                        getJoinedCircleList();
                        message.success("加入兴趣圈成功");

                    }else {
                        message.error(data.message)
                    }
                })
            }


            onMounted(()=>{
                getFollowList();
                getCircleList();
            })
            return {
                SERVER,
                recommendList,
                toUserInfo,
                followUser,
                recommendCircleList,
                partInCircle,
                toCircleSquare,

            };
        },
    });
</script>

<style scoped>

</style>