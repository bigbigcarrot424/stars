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

    <a-modal
            v-model:visible="alterCircleInfoVisible"
            title="修改兴趣圈信息"
            ok-text="修改"
            cancel-text="取消"
            @ok="alterCircleInfo()"
    >

            <a-form-item
                    label="兴趣圈名"
                    name="circleName"
            >
                <a-input v-model:value="circleName" />
            </a-form-item>

            <a-form-item
                    label="兴趣圈介绍"
                    name="circleIntro"
            >
                <a-input v-model:value="circleIntro" />
            </a-form-item>
    </a-modal>

    <p>我创建的兴趣圈</p>
    <div style="padding: 20px">
        <a-row :gutter="16">
            <a-col :span="12" v-for="(circle, index) in createdCircleList" :key="circle.id" style="margin-bottom: 20px">
                <a-card hoverable style="width: 320px">
                    <template #actions>
                        <home-outlined key="home" @click="toCircleSquare(circle.id)"/>
                        <router-link :to="{
                              path:'/circleMember',
                              query:{
                                circleId: circle.id
                              }
                        }">
                            <team-outlined key="team"/>
                        </router-link>
                        <edit-outlined @click="showCircleInfo(circle.id, circle.circleName, circle.intro)"/>

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
            <a-col :span="12" v-for="(circle, index) in joinedCircleList" :key="circle.id" style="margin-bottom: 20px">
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
            <a-col :span="12" v-for="(circle, index) in circleList" :key="circle.id" style="margin-bottom: 20px">
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
    import { HomeOutlined, DeleteOutlined, MessageOutlined, ImportOutlined, TeamOutlined, EditOutlined} from '@ant-design/icons-vue';
    import  {useRouter}  from "vue-router";
    import  { message }  from "ant-design-vue";


    export default defineComponent({
        name: "Circles",
        components: {
            MessageOutlined,
            DeleteOutlined,
            HomeOutlined,
            ImportOutlined,
            TeamOutlined,
            EditOutlined,
        },
        setup() {
            const SERVER = process.env.VUE_APP_SERVER;

            const createModalVisible = ref(false);
            const alterCircleInfoVisible = ref(false);

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
                console.log("createCircleList", createdCircleList.value)
                axios.get(SERVER + "/circle/myCreatedCircle/" + store.state.user.id).then((response) => {
                    const data = response.data;
                    if (data){
                        createdCircleList.value = data.content;
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
                        getCircleList();
                        getJoinedCircleList();
                        getCreatedCircleList();
                        message.success("删除兴趣圈成功！")
                    }else {
                        message.error(data.message);
                    }
                })
            }

            /**
             * 修改兴趣圈信息
             */

            const circleId = ref(0);
            const circleName = ref("");
            const circleIntro = ref("");

            const showCircleInfo = (id, name, intro) => {
                circleId.value = id;
                circleName.value = name;
                circleIntro.value = intro;
                alterCircleInfoVisible.value = true;
            }

            const alterCircleInfo = () => {
                const updateCircleReq = {
                    id: circleId.value,
                    circleName: circleName.value,
                    intro: circleIntro.value,
                }
                axios.post(SERVER + '/circle/update', updateCircleReq).then((response) => {
                    const data = response.data;
                    if (data.success) {
                        getCircleList();
                        getJoinedCircleList();
                        getCreatedCircleList();
                        message.success("修改兴趣圈信息成功！")
                        alterCircleInfoVisible.value = false;
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
                showCircleInfo,
                alterCircleInfoVisible,
                alterCircleInfo,

                circleId,
                circleName,
                circleIntro,
            };
        },
    });
</script>