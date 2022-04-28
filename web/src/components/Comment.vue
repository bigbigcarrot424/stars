<template>
    <a-comment>
        <template #actions>
              <span key="comment-basic-like">
                <a-tooltip title="Like">
                  <template v-if="action === 'liked'">
                    <LikeFilled @click="like"/>
                  </template>
                  <template v-else>
                    <LikeOutlined @click="like"/>
                  </template>
                </a-tooltip>
                <span style="padding-left: 8px; cursor: auto">
                  {{ likes }}
                </span>
              </span>
             <span key="comment-basic-dislike">
                <a-tooltip title="Dislike">
                  <template v-if="action === 'disliked'">
                    <DislikeFilled @click="dislike"/>
                  </template>
                  <template v-else>
                    <DislikeOutlined @click="dislike"/>
                  </template>
                </a-tooltip>
                <span style="padding-left: 8px; cursor: auto">
                  {{ dislikes }}
                </span>
             </span>
            <span key="comment-basic-reply-to">
                <svg t="1650867780667" class="icon" viewBox="0 0 1058 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4544" width="11" height="11"><path d="M330.744242 885.372121l194.779798-129.861818 16.665859-11.106263h383.844848c36.486465 0 66.19798-29.659798 66.19798-66.146262v-529.19596c0-36.434747-29.711515-66.107475-66.19798-66.107475H132.305455c-36.486465 0-66.146263 29.659798-66.146263 66.107475v529.19596c0 36.486465 29.659798 66.146263 66.146263 66.146262h198.438787v140.968081m-66.146262 123.578182V810.550303H132.305455c-73.024646 0-132.305455-59.216162-132.305455-132.292525v-529.19596C0 76.024242 59.267879 16.808081 132.305455 16.808081h793.742222c73.076364 0 132.357172 59.216162 132.357171 132.240808v529.195959c0 73.076364-59.267879 132.292525-132.357171 132.292526h-363.830303L264.59798 1008.950303z m0 0" p-id="4545" fill="#8a8a8a"></path></svg>
                <span style="padding-left: 6px; cursor: auto">
                  0
                </span>
            </span>
        </template>
        <template #author><a>{{blogInfo.authorName}}</a></template>
        <template #avatar>
            <a-avatar :src="SERVER + '/file/avatar/' + store.state.user.avatar" alt="Han Solo"/>
        </template>
        <template #content>
<!--            放置帖子html：-->
            <div :innerHTML="html">

            </div>
<!--            <p>-->
<!--                {{blogInfo.content}}-->
<!--            </p>-->

<!--            <a-image-preview-group>-->
<!--                <a-image :width="200" src="https://aliyuncdn.antdv.com/vue.png" />-->
<!--                <a-image :width="200" src="https://aliyuncdn.antdv.com/logo.png" />-->
<!--            </a-image-preview-group>-->
        </template>
        <template #datetime>
            <a-tooltip :title="dayjs().format('YYYY-MM-DD HH:mm:ss')">
                <span>{{ dayjs().to(dayjs(blogInfo.publishTime)) }}</span>
<!--                <span>{{ blogInfo.publishTime }}</span>-->
            </a-tooltip>
        </template>
    </a-comment>
</template>
<script lang="ts">
    import dayjs from 'dayjs';
    import {LikeFilled, LikeOutlined, DislikeFilled, DislikeOutlined} from '@ant-design/icons-vue';
    import {defineComponent, ref} from 'vue';
    import relativeTime from 'dayjs/plugin/relativeTime';
    import store from '@/store'

    dayjs.extend(relativeTime);

    export default defineComponent({
        components: {
            LikeFilled,
            LikeOutlined,
            DislikeFilled,
            DislikeOutlined,
        },
        props: ['blogInfo'],

        setup(props) {
            const SERVER  = process.env.VUE_APP_SERVER;
            const likes = ref<number>(0);
            const dislikes = ref<number>(0);
            const action = ref<string>();

            const like = () => {
                likes.value = 1;
                dislikes.value = 0;
                action.value = 'liked';
            };

            const dislike = () => {
                likes.value = 0;
                dislikes.value = 1;
                action.value = 'disliked';
            };

            const html = ref()
            html.value = props.blogInfo.content

            return {
                likes,
                dislikes,
                action,
                like,
                dislike,
                dayjs,
                html,

                SERVER,
                store
            };
        },
    });
</script>
