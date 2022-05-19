<template>
    <a-card title="为你推荐">
        <a-row :gutter="16">
            <a-col :span="8" v-for="(item, index) in recommendList">
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
    </a-card>

</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import axios from 'axios'
    import store from '@/store'

    export default defineComponent({
        name: "Explore",
        components: {
            StarOutlined,
            LikeOutlined,
            MessageOutlined,
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

            onMounted(()=>{
                getFollowList();
            })
            return {
                SERVER,
                recommendList,
            };
        },
    });
</script>

<style scoped>

</style>