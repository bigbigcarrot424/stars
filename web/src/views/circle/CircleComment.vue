<template>
    <a-comment>
        <template #actions>

        </template>
        <template #author><a>{{blogInfo.authorName}}</a></template>
        <template #avatar>
                <a-avatar @click="toUserInfo()" :src="SERVER + '/file/avatar/' + blogInfo.avatar" alt="Han Solo"/>
        </template>
        <template #content>
<!--            放置帖子html：-->
            <div :innerHTML="html">

            </div>
        </template>
        <template #datetime>
            <a-tooltip :title="dayjs().format('YYYY-MM-DD HH:mm:ss')">
                <span>{{ dayjs().to(dayjs(blogInfo.publishTime)) }}</span>
            </a-tooltip>
        </template>
        <a-button v-if="ifShowDeleteButton" type="primary" @click="deleteCircleBlog()">删除</a-button>
    </a-comment>
</template>


<script lang="ts">
    import dayjs from 'dayjs';
    import {LikeFilled, LikeOutlined, DislikeFilled, DislikeOutlined} from '@ant-design/icons-vue';
    import {defineComponent, ref, onMounted} from 'vue';
    import relativeTime from 'dayjs/plugin/relativeTime';
    import store from '@/store'
    import axios from "axios";
    import {message} from "ant-design-vue";
    import  {useRouter}  from "vue-router";


    dayjs.extend(relativeTime);

    export default defineComponent({
        components: {
            LikeFilled,
            LikeOutlined,
            DislikeFilled,
            DislikeOutlined,
        },
        props: ['blogInfo', 'ifShowDeleteButton'],

        setup(props) {
            const SERVER  = process.env.VUE_APP_SERVER;

            const saveReq = {
                commentatorId: store.state.user.id,
                blogId: props.blogInfo.id,
                isLike: true
            }

            /**
             * 点击头像进入主页
             */

            const router = useRouter();
            const toUserInfo = () => {
                router.push ({
                    path:"/othersInfo",
                    query:{
                        userId: props.blogInfo.authorId
                    }
                })
            }

            const html = ref()
            html.value = props.blogInfo.content;

            const comments = ref([{

            }]);

            const commentShow = ref(false);

            const submitting = ref(false);

            const deleteCircleBlog = () => {
                axios.get(SERVER + '/circleBlog/deleteCircleBlog/' + props.blogInfo.id).then((response) => {
                    const data = response.data;
                    if (data.success){
                        message.success("删除成功！")

                    }else {
                        message.error(data.message);
                    }
                })
            }

            onMounted(() => {
                console.log(props.ifShowDeleteButton)
            })

            return {

                dayjs,
                html,

                SERVER,
                store,
                comments,
                submitting,

                commentShow,
                status,
                toUserInfo,
                deleteCircleBlog,
            };
        },
    });
</script>
