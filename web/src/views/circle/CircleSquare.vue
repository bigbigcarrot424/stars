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
        <circle-comment :blogInfo="item"></circle-comment>
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
    import CircleComment from "./CircleComment";
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import {message} from 'ant-design-vue'
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
            CircleComment,
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


            const blogContent = ref('');

            const modalVisible = ref(false);

            const popModal = () => {
                modalVisible.value = true;
            }

            const saveCircleBlog = () => {
                const circleBlog = {
                    authorId: store.state.user.id,
                    circleId: circleId,
                    content: blogContent.value
                }
                axios.post(process.env.VUE_APP_SERVER + "/circleBlog/saveCircleBlog", circleBlog).then((response) => {
                    const data = response.data;
                    if (data.success){
                        message.success("发言成功！");
                        getAllBlog(1);
                    }else {
                        message.error(data.message);
                    }
                    modalVisible.value = false;
                    blogContent.value = '';
                })
            }


            onMounted(()=>{
                getAllBlog(1);
                getBlogNum();
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
                saveCircleBlog,
            };
        },
    });
</script>

<style scoped>

</style>