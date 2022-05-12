<template>
    <div>
        <a-button type="primary" style="margin-left: 20px" @click="popModal">发言</a-button>
    </div>

    <a-modal
            v-model:visible="modalVisible"
            title="发言框"
            ok-text="确定"
            cancel-text="取消"
            @ok="saveCircleBlog"
    >
        <a-textarea
                v-model:value="blogContent"
                placeholder="把想说的话写在这里吧"
                :auto-size="{ minRows: 2, maxRows: 5 }"
                allow-clear
        />
    </a-modal>

    <div v-for="(item, index) in blogList" :key="item.id">
        <comment :blogInfo="item"></comment>
    </div>

    <a-pagination
            v-model:current="current"
            :total="blogNum ? blogNum : 1"
            :defaultPageSize="7"
            :style="{marginLeft:'auto', textAlign: 'right'}"
            @change="getAllBlog(current)"/>
</template>

<script>
    import Comment from "../../components/Comment";
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import axios from 'axios'
    import store from '@/store'
    import  {useRouter}  from "vue-router";

    export default defineComponent({
        name: "CircleSquare",
        components: {
            StarOutlined,
            LikeOutlined,
            MessageOutlined,
            Comment,
        },
        setup() {
            const router = useRouter();
            let blogList = ref();
            let circleId = router.currentRoute.value.query.circleId;

            const getAllBlog = (pageNum) => {
                axios.get(process.env.VUE_APP_SERVER + "/circleBlog/blogListInCircleByPage/"+ circleId + '/' + pageNum + "/7").then(
                    (response) =>{
                        blogList.value = response.data.content ? response.data.content :[];
                    },
                    (error) => {
                        message.error(error)
                    }
                )
            }
            const pagination = ref();

            const current = ref(1);

            const blogNum = ref();

            const getBlogNum = () => {
                axios.get(process.env.VUE_APP_SERVER + "/circleBlog/blogListInCircleNum/" + circleId).then(
                    (response) => {
                        blogNum.value = response.data.content;
                        // console.log(blogNum.value)
                    }
                )
            }

            const myLikes = [];
            const getMyLikes = () => {
                myLikes.splice(0,myLikes.length);
                if (store.state.user.id){
                    axios.get(process.env.VUE_APP_SERVER + "/likes/list/" + store.state.user.id).then((response) => {
                        const data = response.data;
                        store.commit("setLikes", data.content);

                        for (let item of data.content){
                            myLikes.push(item);
                        }
                    })
                }
                console.log("getMyLikes:", myLikes);
                // store.commit("setLikes", myLikes);
            }

            const blogContent = ref('');

            const modalVisible = ref(false);

            const popModal = () => {
                modalVisible.value = true;
            }

            const saveCircleBlog = () => {
                axios.post(process.env.VUE_APP_SERVER + "/blog/list/" + store.state.user.id)
                modalVisible.value = false;
            }


            onMounted(()=>{
                getAllBlog(1);
                getBlogNum();
                getMyLikes();
            })


            return {
                pagination,
                blogList,
                pagination,
                current,
                getAllBlog,
                blogNum,
                popModal,
                modalVisible,
                blogContent,
            };
        },
    });
</script>

<style scoped>

</style>