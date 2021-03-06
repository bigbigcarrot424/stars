<template>
    <a-comment>
        <template #actions>
              <span key="comment-basic-like">
                <a-tooltip title="Like">
                  <template v-if="status === 1">
                    <LikeFilled/>
                  </template>
                  <template v-else>
                    <LikeOutlined @click="like"/>
                  </template>
                </a-tooltip>
                <span style="padding-left: 8px; cursor: auto">
                  {{ blogInfo.voteNum }}
                </span>
              </span>
             <span key="comment-basic-dislike">
                <a-tooltip title="Dislike">
                  <template v-if="status === -1">
                    <DislikeFilled/>
                  </template>
                  <template v-else>
                    <DislikeOutlined @click="dislike"/>
                  </template>
                </a-tooltip>
                <span style="padding-left: 8px; cursor: auto">
                  {{ blogInfo.opposeNum }}
                </span>
             </span>
            <span key="comment-basic-reply-to" @click="alterCommentShow">
                <svg t="1650867780667" class="icon" viewBox="0 0 1058 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4544" width="11" height="11"><path d="M330.744242 885.372121l194.779798-129.861818 16.665859-11.106263h383.844848c36.486465 0 66.19798-29.659798 66.19798-66.146262v-529.19596c0-36.434747-29.711515-66.107475-66.19798-66.107475H132.305455c-36.486465 0-66.146263 29.659798-66.146263 66.107475v529.19596c0 36.486465 29.659798 66.146263 66.146263 66.146262h198.438787v140.968081m-66.146262 123.578182V810.550303H132.305455c-73.024646 0-132.305455-59.216162-132.305455-132.292525v-529.19596C0 76.024242 59.267879 16.808081 132.305455 16.808081h793.742222c73.076364 0 132.357172 59.216162 132.357171 132.240808v529.195959c0 73.076364-59.267879 132.292525-132.357171 132.292526h-363.830303L264.59798 1008.950303z m0 0" p-id="4545" fill="#8a8a8a"></path></svg>
                <span style="padding-left: 6px; cursor: auto">
                  {{blogInfo.commentNum}}
                </span>
            </span>
            <span key="collect">
                <StarOutlined @click="collectBlog"/>
            </span>
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
    </a-comment>


<!--    评论组件-->
    <a-list
            v-if="comments.length"
            :data-source="comments"
            :header="`${comments.length} 评论`"
            item-layout="horizontal"
            v-show="commentShow"
    >
        <template #renderItem="{ item }">
            <a-list-item>
                <a-comment
                        :author="item.author"
                        :avatar="item.avatar"
                        :content="item.content"
                        :datetime="item.datetime"
                />
            </a-list-item>
        </template>
    </a-list>
    <a-comment v-show="commentShow">
        <template #avatar>
            <a-avatar :src="SERVER + '/file/avatar/' + store.state.user.avatar" alt="Han Solo" />
        </template>
        <template #content>
            <a-form-item>
                <a-textarea v-model:value="content" :rows="4" />
            </a-form-item>
            <a-form-item>
                <a-button html-type="submit" :loading="submitting" type="primary" @click="handleSubmitComment">
                    评论
                </a-button>
            </a-form-item>
        </template>
    </a-comment>
</template>


<script lang="ts">
    import dayjs from 'dayjs';
    import {LikeFilled, LikeOutlined, DislikeFilled, DislikeOutlined, StarOutlined} from '@ant-design/icons-vue';
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
            StarOutlined,
        },
        props: ['blogInfo'],

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

            /**
             * 点赞/踩
             */
            const like = () => {
                saveReq.isLike = true;
                axios.post(SERVER + "/likes/save", saveReq).then((response) => {
                    const data = response.data;
                    if (data.success){
                        message.success("评价成功！");
                        props.blogInfo.voteNum ++;
                        status.value = 1;
                    }else {
                        message.error(data.message);
                    }
                })
            };

            const dislike = () => {
                saveReq.isLike = false;
                axios.post(SERVER + "/likes/save", saveReq).then((response) => {
                    const data = response.data;
                    if (data.success){
                        message.success("评价成功！");
                        props.blogInfo.opposeNum ++;
                        status.value = -1;
                    }else {
                        message.error(data.message);
                    }
                })
            };

            const html = ref()
            html.value = props.blogInfo.content;

            /**
             * 评论：
             */
            const content = ref('');

            const comment ={
                commentatorId: store.state.user.id,
                blogId: props.blogInfo.id,
                content: content.value,
            }

            const getComment = () => {
                comments.value = [];
                axios.get(SERVER + "/comment/list/" + props.blogInfo.id).then((response) => {
                    const data = response.data;
                    if (data.success){
                        for(let item of data.content){
                            comments.value.push({
                                author: item.commentatorName,
                                avatar: SERVER + '/file/avatar/' + item.avatar,
                                content: item.content,
                                datetime: dayjs().to(dayjs(item.commentTime)),
                            })
                        }
                    }else {
                        message.error(data.message)
                    }
                })
            }

            const saveComment = () => {
                axios.post(SERVER + "/comment/save", comment).then((response) =>{
                    if (response.data.success){
                        message.success("评论成功");
                    }else {
                        message.error(response.data.message);
                    }
                })
            }

            const comments = ref([{

            }]);

            const commentShow = ref(false);


            const alterCommentShow = () => {
                getComment()
                commentShow.value = commentShow.value ? false : true;
            }



            const submitting = ref(false);


            const handleSubmitComment = () => {
                if (!content.value) {
                    return;
                }

                submitting.value = true;
                comment.content = content.value
                saveComment();
                content.value = '';
                props.blogInfo.commentNum ++;
                setTimeout(() => {
                    getComment();
                    submitting.value = false;
                }, 600)
            };

            /**
             * 收藏帖子
             */
            const collectBlog = () => {
                const collectSaveReq = {
                    collectorId: store.state.user.id,
                    blogId: props.blogInfo.id
                }
                axios.post(SERVER + '/collect/save', collectSaveReq).then((response) =>{
                    const data = response.data;
                    if (data.success){
                        message.success("收藏成功！")
                    }else {
                        message.error(data.message);
                    }
                })
            }

            /**
             * 计算该帖子的状态（被赞还是踩）
             */
            const status = ref(0);

            const isMyLike = () => {
                for (let item of store.state.likes){
                    if (item.blogId === props.blogInfo.id && item.isLike){
                        status.value = 1;
                        break;
                    }else if (item.blogId === props.blogInfo.id && !item.isLike){
                        status.value = -1;
                        break;
                    }else {
                        status.value = 0;
                    }
                }
            }


            onMounted(() => {
                isMyLike();
            })

            return {
                like,
                dislike,
                dayjs,
                html,

                SERVER,
                store,

                comments,
                submitting,

                handleSubmitComment,

                alterCommentShow,
                commentShow,
                content,

                status,
                toUserInfo,

                collectBlog
            };
        },
    });
</script>
