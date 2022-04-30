<template>

    <div v-for="(item, index) in blogList" :key="index">
        <comment :blogInfo="item"></comment>
    </div>

    <a-pagination
            v-model:current="current"
            :total="blogNum ? blogNum : 1 + 6"
            :style="{marginLeft:'auto', textAlign: 'right'}"
            @change="getAllBlog(current)"/>
</template>

<script>
    import Comment from "../../components/Comment";
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import axios from 'axios'

    export default defineComponent({
        name: "AdminSquare",
        components: {
            StarOutlined,
            LikeOutlined,
            MessageOutlined,
            Comment,
        },
        setup() {
            let blogList = ref();
            const getAllBlog = (pageNum) => {
                axios.get(process.env.VUE_APP_SERVER + "/blog/listByPage/" + pageNum + "/7").then(
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
                axios.get(process.env.VUE_APP_SERVER + "/blog/blogNum").then(
                    (response) => {
                        blogNum.value = response.data.content;
                        // console.log(blogNum.value)
                    }
                )
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
            };
        },
    });
</script>

<style scoped>

</style>