<template>

    <div v-for="item in blogList">
        <comment :blogInfo="item"></comment>
    </div>

</template>

<script>
    import Comment from "../../components/Comment";
    import {defineComponent, ref, onMounted, reactive} from 'vue';
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
            const getAllBlog = () => {
                axios.get(process.env.VUE_APP_SERVER + "/blog/list").then(
                    (response) =>{
                       blogList.value = response.data ? response.data :[];
                    },
                    (error) => {
                        message.error(error)
                    }
                )
            }
            const pagination = {

            };
            onMounted(()=>{
                getAllBlog();
            })
            return {
                pagination,
                blogList,
            };
        },
    });
</script>

<style scoped>

</style>