<template>
    <div style="padding: 20px">
        <a-row :gutter="16">
            <a-col :span="6" v-for="(circle, index) in circleList" :key="circle.id" style="margin-bottom: 20px">
                <a-card hoverable style="width: 320px">
                    <template #actions>
                        <home-outlined key="home" @click="toCircleInfo(item.id)"/>
                        <a-popconfirm title="确认加入兴趣圈？" ok-text="是" cancel-text="否"  @confirm="partInCircle(item.id)" @cancel="">
                            <import-outlined key="import"/>
                        </a-popconfirm>

                    </template>
                    <div style="text-align: center; font-size: 20px">{{circle.circleName}}</div>
                    <div style="font-size: 15px; color: rgba(0, 0, 0, 0.45); margin-top: 10px">兴趣圈介绍：{{circle.intro}}</div>
                </a-card>
            </a-col>
        </a-row>
    </div>
    <div v-for="(circle, index) in circleList" :key="circle.id">
        {{circle}}
    </div>
</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import axios from 'axios'
    import store from '@/store'
    import { HomeOutlined, DeleteOutlined, MessageOutlined, ImportOutlined} from '@ant-design/icons-vue';
    import  {useRouter}  from "vue-router";
    import  { message }  from "ant-design-vue";


    export default defineComponent({
        name: "Circles",
        components: {
            MessageOutlined,
            DeleteOutlined,
            HomeOutlined,
            ImportOutlined,
        },
        setup() {
            const SERVER = process.env.VUE_APP_SERVER;
            let circleList = ref();
            const getCircleList = () => {
                axios.get(SERVER + "/circle/list").then((response) => {
                    const data = response.data;
                    if (data){
                        circleList.value = data.content ? data.content :[];
                    }
                })
            }

            /**
             * 进入好友主页
             */

            const router = useRouter();
            const toUserInfo = (userId) => {
                router.push ({
                    path:"/othersInfo",
                    query:{
                        userId: userId
                    }
                })
            }



            onMounted(()=>{
                getCircleList();
            })
            return {
                SERVER,
                circleList,
                toUserInfo,
            };
        },
    });
</script>