<template>
    <div style="padding: 20px">
        <a-row :gutter="16">
            <a-col :span="6" v-for="(item, index) in followList" :key="item.id">
                <a-card hoverable style="width: 320px">
                    <template #actions>
                        <home-outlined key="home" @click="toUserInfo(item.id)"/>
                        <message-outlined key="message" @click=""/>
                        <a-popconfirm title="确认删除好友？" ok-text="是" cancel-text="否"  @confirm="unfollow(item.id)" @cancel="">
                            <delete-outlined key="delete"/>
                        </a-popconfirm>

                    </template>
                    <a-card-meta :title="item.name" :description="item.selfIntro">
                        <template #avatar>
                            <a-avatar :src="SERVER + '/file/avatar/' + item.avatar" />
                        </template>
                    </a-card-meta>
                </a-card>
            </a-col>
        </a-row>
    </div>
</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import axios from 'axios'
    import store from '@/store'
    import { HomeOutlined, DeleteOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import  {useRouter}  from "vue-router";
    import  { message }  from "ant-design-vue";


    export default defineComponent({
        name: "MyFriend",
        components: {
            MessageOutlined,
            DeleteOutlined,
            HomeOutlined,
        },
        setup() {
            const SERVER = process.env.VUE_APP_SERVER;
            let followList = ref();
            const getFollowList = () => {
                axios.get(SERVER + "/follow/followList/" + store.state.user.id).then((response) => {
                    const data = response.data;
                    if (data){
                        followList.value = data.content ? data.content :[];
                    }
                })
            }

            /**
             * 进入好友主页
             */

            const router = useRouter();
            const toUserInfo = (userId) => {
                router.push ({
                    path:"/othersInfo",
                    query:{
                        userId: userId
                    }
                })
            }

            /**
             * 删除好友
             */

            const unfollow = (userId) => {
                axios.get(`${SERVER}/follow/unfollow/${store.state.user.id}/${userId}`).then((response) => {
                    if (response.data.success){
                        const data = response.data.content;
                        message.success("删除好友成功！");
                        getFollowList();
                    }else {
                        message.error(response.data.message);
                    }
                })
            }


            onMounted(()=>{
                getFollowList();
            })
            return {
                SERVER,
                followList,
                toUserInfo,
                unfollow,
            };
        },
    });
</script>