<template>

    <div v-for="(item, index) in blogList" :key="item.id">
        <comment :blogInfo="item"></comment>
        <router-link :to="{
          name:'adminBlogEdit',
          params:{
            blogId: item.id,
            content:item.content,
          }
        }">
            <a-button size="small" type="primary" style="margin-left: 30px">编辑</a-button>
        </router-link>
        <a-button size="small" danger type="primary" style="margin-left: 10px">删除</a-button>
        <a-divider></a-divider>
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
    import store from '@/store'

    export default defineComponent({
        name: "MyBlog",
        components: {
            StarOutlined,
            LikeOutlined,
            MessageOutlined,
            Comment,
        },
        setup() {
            let blogList = ref();
            const getAllBlog = (pageNum) => {
                axios.get(process.env.VUE_APP_SERVER + "/blog/myListByPage/" + pageNum + "/7/" + store.state.user.id).then(
                    (response) =>{
                        blogList.value = response.data.content ? response.data.content :[];
                    }
                )
            }
            const pagination = ref();

            const current = ref(1);

            const blogNum = ref();

            const getBlogNum = () => {
                axios.get(process.env.VUE_APP_SERVER + "/blog/myBlogNum/" + store.state.user.id).then(
                    (response) => {
                        blogNum.value = response.data.content;
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
            };
        },
    });
</script>