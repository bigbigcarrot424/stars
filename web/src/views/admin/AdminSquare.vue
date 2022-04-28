<template>

    <div v-for="item in blogList" :key="item">
        <comment :blogInfo="item"></comment>
    </div>

    <a-pagination
            v-model:current="current"
            :total="50"
            show-less-items
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

            const test = () => {
                console.log("hello")
            }

            onMounted(()=>{
                getAllBlog(1);
            })
            return {
                pagination,
                blogList,
                pagination,
                current,
                getAllBlog,
            };
        },
    });
</script>

<style scoped>

</style>