<template>
    <a-list item-layout="horizontal" :data-source="data">
        <template #renderItem="{ item }">
            <a-list-item>
                <a-list-item-meta
                        description="Ant Design, a design language for background applications, is refined by Ant UED Team"
                >
                    <template #title>
                        <a href="https://www.antdv.com/">{{ item.title }}</a>
                    </template>
                    <template #avatar>
                        <a-avatar src="https://joeschmoe.io/api/v1/random" />
                    </template>
                </a-list-item-meta>
            </a-list-item>
        </template>
    </a-list>

    <a-list item-layout="horizontal" :data-source="blogList">
        <template #renderItem="{ item }">
            <a-list-item>
                <a-list-item-meta
                        description="Ant Design, a design language for background applications, is refined by Ant UED Team"
                >
                    <template #title>
                        <a href="https://www.antdv.com/">{{ item.title }}</a>
                    </template>
                    <template #content>
                        {{item.content}}
                    </template>
                </a-list-item-meta>
            </a-list-item>
        </template>
    </a-list>
</template>

<script>
    import Comment from "../../components/Comment";
    import {defineComponent, ref, onMounted, reactive} from 'vue';
    import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import axios from 'axios'

    const data = [
        {
            title: 'Ant Design Title 1',
        },
        {
            title: 'Ant Design Title 2',
        },
        {
            title: 'Ant Design Title 3',
        },
        {
            title: 'Ant Design Title 4',
        },
    ];
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
            blogList = [];
            const getAllBlog = () => {
                axios.get(process.env.VUE_APP_SERVER + "/blog/list").then(
                    (response) =>{
                       blogList.value = response.data.content;
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
                data,
            };
        },
    });
</script>

<style scoped>

</style>