<template>
    <a-button type="primary" style="margin-bottom: 20px" @click="showCreateCircleModal">创建兴趣圈</a-button>

    <a-modal
            v-model:visible="createModalVisible"
            title="创建兴趣圈"
            ok-text="创建"
            cancel-text="取消"
            @ok="createCircle"
    >

            <a-form-item
                    label="兴趣圈名"
                    name="circleName"
            >
                <a-input v-model:value="createdCircle.circleName" />
            </a-form-item>

            <a-form-item
                    label="兴趣圈介绍"
                    name="circleIntro"
            >
                <a-input v-model:value="createdCircle.intro" />
            </a-form-item>
    </a-modal>

    <p>我创建的兴趣圈</p>
    <div style="padding: 20px">
        <a-row :gutter="16">
            <a-col :span="6" v-for="(circle, index) in createdCircleList" :key="circle.id" style="margin-bottom: 20px">
                <a-card hoverable style="width: 320px">
                    <template #actions>
                        <home-outlined key="home" @click="toCircleSquare(circle.id)"/>
                        <a-popconfirm title="确认删除兴趣圈？" ok-text="是" cancel-text="否"  @confirm="deleteCircle(circle.id)" @cancel="">
                            <delete-outlined key="import"/>
                        </a-popconfirm>

                    </template>
                    <div style="text-align: center; font-size: 20px">{{circle.circleName}}</div>
                    <div style="font-size: 15px; color: rgba(0, 0, 0, 0.45); margin-top: 10px">兴趣圈介绍：{{circle.intro}}</div>
                </a-card>
            </a-col>
        </a-row>
    </div>

    <a-divider></a-divider>

    <p>我所在的兴趣圈</p>
    <div style="padding: 20px">
        <a-row :gutter="16">
            <a-col :span="6" v-for="(circle, index) in joinedCircleList" :key="circle.id" style="margin-bottom: 20px">
                <a-card hoverable style="width: 320px">
                    <template #actions>
                        <home-outlined key="home" @click="toCircleSquare(circle.id)"/>
                        <a-popconfirm title="确认退出兴趣圈？" ok-text="是" cancel-text="否"  @confirm="exitCircle(circle.id)" @cancel="">
                            <delete-outlined key="import"/>
                        </a-popconfirm>

                    </template>
                    <div style="text-align: center; font-size: 20px">{{circle.circleName}}</div>
                    <div style="font-size: 15px; color: rgba(0, 0, 0, 0.45); margin-top: 10px">兴趣圈介绍：{{circle.intro}}</div>
                </a-card>
            </a-col>
        </a-row>
    </div>

    <a-divider></a-divider>
    <p>所有兴趣圈</p>
    <div style="padding: 20px">
        <a-row :gutter="16">
            <a-col :span="6" v-for="(circle, index) in circleList" :key="circle.id" style="margin-bottom: 20px">
                <a-card hoverable style="width: 320px">
                    <template #actions>
                        <home-outlined key="home" @click="toCircleSquare(circle.id)"/>
                        <a-popconfirm title="确认加入兴趣圈？" ok-text="是" cancel-text="否"  @confirm="partInCircle(circle.id, circle.managerId)" @cancel="">
                            <import-outlined key="import"/>
                        </a-popconfirm>

                    </template>
                    <div style="text-align: center; font-size: 20px">{{circle.circleName}}</div>
                    <div style="font-size: 15px; color: rgba(0, 0, 0, 0.45); margin-top: 10px">兴趣圈介绍：{{circle.intro}}</div>
                </a-card>
            </a-col>
        </a-row>
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

            const createModalVisible = ref(false);
            let circleList = ref();
            let joinedCircleList = ref();
            let createdCircleList = ref();
            const createdCircle = ref();
            createdCircle.value = {
                managerId: store.state.user.id,
                circleName: '',
                intro: '',
            }

            const containCircle = (circle, circleList)=>{
                for (let item of circleList){
                    if (item.id === circle.id){
                        return true;
                    }
                }
                return false;
            }

            const getCircleList = () => {
                axios.get(SERVER + "/circle/list").then((response) => {
                    const data = response.data;
                    axios.get(SERVER + "/circle/myJoinedCircle/" + store.state.user.id).then((response) => {
                        const data = response.data;
                        if (data){
                            joinedCircleList.value = data.content ? data.content :[];
                        }
                    })
                    if (data){
                        circleList.value = data.content ? data.content :[];
                        console.log(circleList.value);

                        circleList.value = circleList.value.filter((circle) =>{
                            for (let item in joinedCircleList.value){
                                if (circle.id === item.id){
                                    return false;
                                }
                            }
                            return true;
                        })
                        console.log(circleList.value);
                    }
                })
            }


            const getJoinedCircleList = () => {
                axios.get(SERVER + "/circle/myJoinedCircle/" + store.state.user.id).then((response) => {
                    const data = response.data;
                    if (data){
                        joinedCircleList.value = data.content ? data.content :[];
                    }
                })
            }

            const getCreatedCircleList = () => {
                axios.get(SERVER + "/circle/myCreatedCircle/" + store.state.user.id).then((response) => {
                    const data = response.data;
                    if (data){
                        createdCircleList.value = data.content ? data.content :[];
                    }
                })
            }

            /**
             * 进入兴趣圈主页
             */

            const router = useRouter();
            const toCircleSquare = (circleId) => {
                router.push ({
                    path:"/circleSquare",
                    query:{
                        circleId: circleId
                    }
                })
            }

            const partInCircle = (circleId, managerId) => {
                axios.get(process.env.VUE_APP_SERVER + '/circle/joinCircle/' + store.state.user.id + '/' + circleId + '/' + managerId).then((response) => {
                    const data = response.data;
                    if (data.success){
                        getCircleList();
                        getJoinedCircleList();
                        message.success("加入兴趣圈成功");

                    }else {
                        message.error(data.message)
                    }
                })
            }

            const exitCircle = (circleId) => {
                axios.get(process.env.VUE_APP_SERVER + '/circle/exitCircle/' + store.state.user.id + '/' + circleId).then((response) => {
                    const data = response.data;
                    if (data.success){
                        getCircleList();
                        getJoinedCircleList();
                        message.success("退出兴趣圈成功");
                    }
                })
            }

            /**
             * 创建兴趣圈
             */
            const createCircle = () => {
                axios.post(SERVER + '/circle/create', createdCircle.value).then((response) => {
                    const data = response.data;
                    if (data.success){
                        message.success("兴趣圈创建成功！")
                        createdCircle.value.circleIntro = '';
                        createdCircle.value.circleName = '';
                        getJoinedCircleList();
                        getCircleList();
                        getCreatedCircleList();
                        createModalVisible.value = false;
                    }else {
                        message.error(data.message);
                        createModalVisible.value = false;
                    }
                })
            }

            const showCreateCircleModal = () => {
                createModalVisible.value = true;
            }

            /**
             * 删除兴趣圈
             */
            const deleteCircle = (circleId) => {
                axios.get(SERVER + '/circle/drop/' + circleId).then((response) => {
                    const data = response.data;
                    if (data.success){
                        message.success("删除兴趣圈成功！")
                    }else {
                        message.error(data.message);
                    }
                })
            }



            onMounted(()=>{
                getCircleList();
                getJoinedCircleList();
                getCreatedCircleList();
            })
            return {
                SERVER,
                circleList,
                toCircleSquare,
                joinedCircleList,
                partInCircle,
                createdCircle,
                exitCircle,

                showCreateCircleModal,
                createCircle,
                createModalVisible,
                deleteCircle,
                createdCircleList,
            };
        },
    });
</script>