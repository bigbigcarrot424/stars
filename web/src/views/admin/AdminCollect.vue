<template>

    <div v-for="(item, index) in blogList" :key="item.id">
        <collected-comment :blogInfo="item"></collected-comment>
    </div>

</template>

<script>
    import Comment from "../../components/Comment";
    import CollectedComment from "./CollectedComment";
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import {message} from 'ant-design-vue'
    import axios from 'axios'
    import store from '@/store'

    export default defineComponent({
        name: "AdminCollect",
        components: {
            StarOutlined,
            LikeOutlined,
            MessageOutlined,
            Comment,
            CollectedComment,
        },

        setup() {
            let blogList = ref();
            const getAllBlog = () => {
                axios.get(process.env.VUE_APP_SERVER + "/collect/collectBlogList/" + store.state.user.id).then(
                    (response) =>{
                        if(response.data.success){
                            blogList.value = response.data.content ? response.data.content :[];
                        }else {
                            message.error(response.data.message)
                        }
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
            }

            onMounted(()=>{
                getAllBlog();
                getMyLikes();
            })
            return {
                blogList,
                getAllBlog,
            };
        },
    });
</script>

<style scoped>

</style>