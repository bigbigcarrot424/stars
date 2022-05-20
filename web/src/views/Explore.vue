<template>
    <a-card title="为你推荐">
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

</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed,} from 'vue';
    import  {useRouter}  from "vue-router";
    import {StarOutlined, LikeOutlined, MessageOutlined, HomeOutlined, HeartOutlined} from '@ant-design/icons-vue';
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
        },
        setup() {
            const SERVER = process.env.VUE_APP_SERVER;
            let recommendList = ref();

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


            onMounted(()=>{
                getFollowList();
            })
            return {
                SERVER,
                recommendList,
                toUserInfo,
                followUser
            };
        },
    });
</script>

<style scoped>

</style>