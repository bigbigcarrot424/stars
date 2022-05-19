<template>

    <a-card title="兴趣圈成员">

        <a-card :title="'成员' + (index + 1)" v-for="(item, index) in memberList" :key="item.id">
<!--            <template #extra>-->
<!--                <a href="#">More</a>-->
<!--            </template>-->
            <a-avatar :src="SERVER + '/file/avatar/' + item.avatar"/>
            <span style="margin-left: 10px">{{item.name}}</span>

            <a-popconfirm title="确定删除该成员？" ok-text="是" cancel-text="否" @confirm="deleteMemberFromCircle(item.id)" @cancel="">
                <a-button style="margin-left: 30px" type="primary">删除</a-button>
            </a-popconfirm>
        </a-card>
    </a-card>

</template>

<script>
    import Comment from "../../components/Comment";
    import CircleComment from "./CircleComment";
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import {message} from 'ant-design-vue'
    import axios from 'axios'
    import store from '@/store'
    import  {useRouter}  from "vue-router";

    export default defineComponent({
        name: "CircleMember",
        components: {
            StarOutlined,
            LikeOutlined,
            MessageOutlined,
            Comment,
            CircleComment,
        },
        setup() {
            const router = useRouter();
            let circleId = router.currentRoute.value.query.circleId;
            const SERVER = process.env.VUE_APP_SERVER;
            const memberList = ref({});

            const getCircleMember = () => {
                axios.get(SERVER + '/circle/circleMember/' + circleId).then((response) => {
                    const data = response.data;
                    if (data.success){
                        memberList.value = data.content;
                    }else {
                        message.error(data.message);
                    }
                })
            }

            const deleteMemberFromCircle = (memberId) => {
                axios.get(SERVER + '/circle/deleteMemberFromCircle/' + circleId + '/' + memberId).then((response) => {
                    const data = response.data;
                    if (data.success){
                        getCircleMember();
                        message.success("删除成员成功！")
                    }else {
                        message.error(data.message);
                    }
                })
            }



            onMounted(()=>{
                getCircleMember();
            })


            return {
                memberList,
                deleteMemberFromCircle,
                SERVER,
            };
        },
    });
</script>

<style scoped>

</style>