<template>

    <a-card hoverable style="width: 300px">
<!--        <template #cover>-->
<!--            <img-->
<!--                    alt="example"-->
<!--                    src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"-->
<!--            />-->
<!--        </template>-->
        <template #actions>
            <home-outlined key="home" @click=""/>
            <message-outlined key="message" @click=""/>
            <delete-outlined key="delete" @click=""/>
        </template>
        <a-card-meta title="Card title" description="This is the description">
            <template #avatar>
                <a-avatar src="https://joeschmoe.io/api/v1/random" />
            </template>
        </a-card-meta>
    </a-card>

    <a-pagination
            v-model:current="current"
            :total="blogNum ? blogNum : 1 + 6"
            :style="{marginLeft:'auto', textAlign: 'right'}"
            @change="getAllBlog(current)"/>

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