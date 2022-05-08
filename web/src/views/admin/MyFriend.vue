<template>
    <a-card hoverable style="width: 300px" v-for="(item, index) in followList" :key="item.id">
        <template #actions>
            <home-outlined key="home" @click=""/>
            <message-outlined key="message" @click=""/>
            <delete-outlined key="delete" @click=""/>
        </template>
        <a-card-meta :title="item.name" :description="item.selfIntro">
            <template #avatar>
                <a-avatar :src="SERVER + '/file/avatar/' + item.avatar" />
            </template>
        </a-card-meta>
    </a-card>
</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import axios from 'axios'
    import store from '@/store'
    import { HomeOutlined, DeleteOutlined, MessageOutlined} from '@ant-design/icons-vue';


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

            onMounted(()=>{
                getFollowList();
            })
            return {
                SERVER,
                followList,
            };
        },
    });
</script>