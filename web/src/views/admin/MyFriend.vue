<template>
    <a-button  @click="showSearchFriendModal()" style="margin-left: 20px">搜索好友</a-button>

    <a-modal
            v-model:visible="searchFriendModalVisible"
            title="搜索好友"
            ok-text="确认"
            cancel-text="取消"
            @ok="hideSearchFriendModal"
    >
        <a-button @click="searchWay = '1'" style="margin: 0px 20px 20px 20px">按昵称搜索</a-button>
        <a-button @click="searchWay = '2'" style="margin: 0px 20px 20px 20px">按用户标签搜索</a-button>

        <a-form
                name="getUser"
        >
            <a-form-item
                    label="用户名"
                    name="username"
                    v-if="searchWay === '1'"
            >
                <a-input v-model:value="searchUsername" />
            </a-form-item>

            <a-form-item
                    label="用户标签"
                    name="password"
                    v-if="searchWay === '2'"
            >
                <a-input v-model:value="searchUserTags" />
            </a-form-item>

            <a-form-item v-if="searchWay === '2' || searchWay === '1'" style="text-align: right;">
                <a-button type="primary" html-type="submit" @click="searchUser">搜索</a-button>
            </a-form-item>

            <a-card hoverable style="width: 320px" v-if="userInfoBySearch">
                <template #actions>
                    <home-outlined key="home" @click="toUserInfo(userInfoBySearch.id)"/>
                </template>
                <a-card-meta :title="userInfoBySearch.name" :description="userInfoBySearch.selfIntro">
                    <template #avatar>
                        <a-avatar :src="SERVER + '/file/avatar/' + userInfoBySearch.avatar"/>
                    </template>
                </a-card-meta>
            </a-card>
        </a-form>
    </a-modal>


    <div style="padding: 20px">
        <a-row :gutter="16">
            <a-col :span="12" v-for="(item, index) in followList" :key="item.id" style="margin-bottom: 20px">
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


            /**
             * 搜索好友
             */
            const searchFriendModalVisible = ref(false);

            const showSearchFriendModal = () => {
                searchFriendModalVisible.value = true;
            }

            const hideSearchFriendModal = () => {
                searchFriendModalVisible.value = false;
            }

            const searchWay = ref('0');

            const searchUsername = ref("");
            const searchUserTags = ref("");

            const userInfoBySearch = ref();

            const searchUser = () => {
                if (searchWay.value === '1'){
                    axios.get(SERVER + "/user/getUserInfoByName/" + searchUsername.value).then((response) => {
                        if (response.data){
                            userInfoBySearch.value = response.data.content;
                            message.success("查找完毕")
                        }
                    })
                }else if (searchWay.value === '2'){
                    axios.get(SERVER + "/user/getUserListByTags/" + searchUserTags.value).then((response) => {
                        if (response.data){
                            userInfoBySearch.value = response.data.content[0];
                            message.success("查找完毕")
                        }
                    })
                }
                searchWay.value = '0';
            }

            onMounted(()=>{
                getFollowList();
            })
            return {
                SERVER,
                followList,
                toUserInfo,
                unfollow,
                showSearchFriendModal,
                searchFriendModalVisible,
                searchUsername,
                searchUserTags,
                searchWay,
                hideSearchFriendModal,
                searchUser,
                userInfoBySearch,
            };
        },
    });
</script>