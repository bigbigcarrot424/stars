<template>
    <a-layout-header :style="{ zIndex: 1, width: '100%', position: 'fixed',}">

            <div class="logo">
                <router-link to="/">
                <svg t="1650878033361" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6455" width="80" height="80"><path d="M5.1 832.6h253.5c2.8 0 5.1-2.3 5.1-5.1s-2.3-5.1-5.1-5.1H5.1c-2.8 0-5.1 2.3-5.1 5.1 0 2.9 2.3 5.1 5.1 5.1z m314.3 0h55.8c2.8 0 5.1-2.3 5.1-5.1s-2.3-5.1-5.1-5.1h-55.8c-2.8 0-5.1 2.3-5.1 5.1 0 2.9 2.3 5.1 5.1 5.1z m106.4 0h253.5c2.8 0 5.1-2.3 5.1-5.1s-2.3-5.1-5.1-5.1H425.8c-2.8 0-5.1 2.3-5.1 5.1 0 2.9 2.3 5.1 5.1 5.1z m314.3 0h55.8c2.8 0 5.1-2.3 5.1-5.1s-2.3-5.1-5.1-5.1h-55.8c-2.8 0-5.1 2.3-5.1 5.1 0 2.9 2.3 5.1 5.1 5.1z m106.5 0H1019c2.8 0 5.1-2.3 5.1-5.1s-2.3-5.1-5.1-5.1H846.6c-2.8 0-5.1 2.3-5.1 5.1 0 2.9 2.3 5.1 5.1 5.1z" fill="#050531" p-id="6456"></path><path d="M316.6 926.4c3.3 3.2 5.3 7.7 5.3 12.7 0 9.8-7.9 17.7-17.7 17.7H121.7c-9.8 0-17.7-7.9-17.7-17.7s7.9-17.7 17.7-17.7h99.1c-3.3-3.2-5.3-7.7-5.3-12.7 0-9.8 7.9-17.7 17.7-17.7h182.5c9.8 0 17.7 7.9 17.7 17.7s-7.9 17.7-17.7 17.7h-99.1z m-164.5-71h182.5c9.8 0 17.7 7.9 17.7 17.7 0 9.8-7.9 17.7-17.7 17.7H152.1c-9.8 0-17.7-7.9-17.7-17.7-0.1-9.7 7.9-17.7 17.7-17.7zM734.3 277.6H621c-11.2 0-20.3 9.1-20.3 20.3 0 11.2 9.1 20.3 20.3 20.3h212.9c11.2 0 20.3-9.1 20.3-20.3 0-6.1-2.7-11.5-6.9-15.2h113.3c11.2 0 20.3-9.1 20.3-20.3 0-11.2-9.1-20.3-20.3-20.3H747.7c-11.2 0-20.3 9.1-20.3 20.3 0 6 2.7 11.4 6.9 15.2z m190.8 81.1H712.2c-11.2 0-20.3-9.1-20.3-20.3 0-11.2 9.1-20.3 20.3-20.3h212.9c11.2 0 20.3 9.1 20.3 20.3 0 11.2-9.1 20.3-20.3 20.3zM350.5 439.8h113.3c11.2 0 20.3-9.1 20.3-20.3 0-11.2-9.1-20.3-20.3-20.3H250.9c-11.2 0-20.3 9.1-20.3 20.3 0 6.1 2.7 11.5 6.9 15.2H124.2c-11.2 0-20.3 9.1-20.3 20.3 0 11.2 9.1 20.3 20.3 20.3h212.9c11.2 0 20.3-9.1 20.3-20.3 0-6.1-2.7-11.5-6.9-15.2z m-190.8-81.1h212.9c11.2 0 20.3 9.1 20.3 20.3 0 11.2-9.1 20.3-20.3 20.3H159.7c-11.2 0-20.3-9.1-20.3-20.3 0-11.3 9.1-20.3 20.3-20.3z" fill="#85B4F7" p-id="6457"></path><path d="M537.3 543.7m-276.3 0a276.3 276.3 0 1 0 552.6 0 276.3 276.3 0 1 0-552.6 0Z" fill="#FF843C" p-id="6458"></path><path d="M661.5 455m-15.2 0a15.2 15.2 0 1 0 30.4 0 15.2 15.2 0 1 0-30.4 0Z" fill="#240F03" p-id="6459"></path><path d="M575.4 500.6m-15.2 0a15.2 15.2 0 1 0 30.4 0 15.2 15.2 0 1 0-30.4 0Z" fill="#240F03" p-id="6460"></path><path d="M512 818.8c8.3 0.8 16.8 1.1 25.3 1.1 152.6 0 276.3-123.7 276.3-276.3S689.9 267.4 537.3 267.4c-8.5 0-17 0.4-25.3 1.1 140.7 12.8 250.9 131.1 250.9 275.1S652.7 806 512 818.8z" fill="#B1531D" p-id="6461"></path><path d="M451 321.6c-54.5 21.2-99.5 62-126 114-3.2 6.2-0.7 13.9 5.5 17 6.2 3.2 13.9 0.7 17-5.5 23.7-46.5 63.9-83 112.6-101.9 6.5-2.5 9.8-9.9 7.2-16.4-2.4-6.5-9.8-9.8-16.3-7.2z" fill="#FFFFFF" p-id="6462"></path><path d="M486.6 825c-2.8 0-5.1-2.3-5.1-5.1s2.3-5.1 5.1-5.1c85.7 0 164.7-40 215.7-106.8 1.7-2.2 4.9-2.7 7.1-1 2.2 1.7 2.7 4.9 1 7.1C657.5 783.6 575.5 825 486.6 825z m226.9-114.9c-1.7 2.3-4.8 2.7-7.1 1.1-2.3-1.7-2.7-4.8-1.1-7.1 1-1.3 2-2.7 2.9-4 1.6-2.3 4.8-2.8 7.1-1.2 2.3 1.6 2.8 4.8 1.2 7.1-1 1.3-2 2.7-3 4.1z m28.7-48.5c-1.2 2.5-4.2 3.6-6.7 2.5-2.5-1.2-3.6-4.2-2.5-6.7 16.3-35.3 24.9-73.8 24.9-113.5 0-45.1-10.9-88.5-31.6-127.3-1.3-2.5-0.4-5.5 2.1-6.9 2.5-1.3 5.5-0.4 6.9 2.1 21.4 40.3 32.8 85.3 32.8 132.1-0.1 41.2-9 81.1-25.9 117.7z m-9.4-254.3c1.4 2.4 0.5 5.5-2 6.9-2.4 1.4-5.5 0.5-6.9-2-0.8-1.5-1.6-2.9-2.5-4.3-1.4-2.4-0.6-5.5 1.8-6.9 2.4-1.4 5.5-0.6 6.9 1.8 1 1.5 1.8 3 2.7 4.5z m-32.3-46.5c1.8 2.1 1.6 5.3-0.6 7.1s-5.3 1.6-7.1-0.6c-51.2-59.8-125.8-94.9-206.1-94.9-5.1 0-10.1 0.1-15.1 0.4-2.8 0.2-5.2-2-5.3-4.8-0.2-2.8 2-5.2 4.8-5.3 5.2-0.3 10.4-0.4 15.7-0.4 83.1 0 160.5 36.5 213.7 98.5z m-234.7-97.7c2.8-0.2 5.2 1.9 5.4 4.7s-1.9 5.2-4.7 5.4c-1.7 0.1-3.3 0.3-5 0.4-2.8 0.3-5.3-1.8-5.5-4.6-0.3-2.8 1.8-5.3 4.6-5.5 1.8-0.1 3.5-0.3 5.2-0.4z m-55.8 9.8c2.7-0.8 5.5 0.8 6.3 3.5 0.8 2.7-0.8 5.5-3.5 6.3-81.3 23-147.5 83.1-178.4 161.5-1 2.6-4 3.9-6.6 2.9-2.6-1-3.9-4-2.9-6.6 32-81.4 100.7-143.7 185.1-167.6zM223.1 445.1c1-2.6 3.9-4 6.5-3s4 3.9 3 6.5c-0.6 1.6-1.1 3.1-1.7 4.7-0.9 2.6-3.8 4-6.5 3.1-2.6-0.9-4-3.8-3.1-6.5l1.8-4.8z m-14.4 55c0.4-2.8 3-4.7 5.8-4.2 2.8 0.4 4.7 3 4.2 5.8-2.1 13.8-3.2 27.8-3.2 41.9 0 71 27.3 137.5 75.3 187.7 1.9 2 1.9 5.2-0.2 7.2-2 1.9-5.2 1.9-7.2-0.2-49.8-52-78.1-121.1-78.1-194.7 0-14.7 1.1-29.2 3.4-43.5zM287 741.9c-2-2-2-5.2 0-7.2s5.2-2 7.2 0l3.5 3.5c2 2 2.1 5.2 0.1 7.2s-5.2 2.1-7.2 0.1c-1.2-1.1-2.4-2.4-3.6-3.6z m44.1 36.2c-2.3-1.6-3-4.7-1.4-7s4.7-3 7-1.4c44.1 29.3 95.8 45.2 150 45.2 2.8 0 5.1 2.3 5.1 5.1s-2.3 5.1-5.1 5.1c-56.2-0.1-109.9-16.5-155.6-47z" fill="#240F03" p-id="6463"></path><path d="M232.9 497.1c2.4-1.5 5.5-0.8 7 1.6s0.8 5.5-1.6 7c-65.9 41.7-98.7 83.9-90.2 115.6 5.7 21.3 29.8 37.5 69.6 46.8 2.7 0.6 4.4 3.4 3.8 6.1-0.6 2.7-3.4 4.4-6.1 3.8-43.1-10.1-70.2-28.4-77.1-54.1-10-37.5 25.3-82.9 94.6-126.8z m-12.4 182c-2.7-0.6-4.5-3.3-3.9-6 0.6-2.7 3.3-4.5 6-3.9 1.6 0.3 3.2 0.7 4.9 1 2.7 0.5 4.5 3.2 4 5.9s-3.2 4.5-5.9 4c-1.7-0.3-3.4-0.6-5.1-1z m56.4 7.1c-2.8-0.2-4.9-2.5-4.8-5.3 0.2-2.8 2.5-4.9 5.3-4.8 69.7 3.8 158-6.6 249.2-30.1 2.7-0.7 5.5 0.9 6.2 3.6 0.7 2.7-0.9 5.5-3.6 6.2-92.2 23.7-181.5 34.2-252.3 30.4z m257.2-31.7c-2.7 0.7-5.5-0.9-6.2-3.6s0.9-5.5 3.6-6.2c1.6-0.4 3.3-0.9 4.9-1.3 2.7-0.7 5.5 0.9 6.2 3.6 0.7 2.7-0.9 5.5-3.6 6.2-1.6 0.4-3.3 0.8-4.9 1.3z m53.8-15.6c-2.7 0.8-5.5-0.6-6.4-3.3-0.8-2.7 0.6-5.5 3.3-6.4 93.6-29.7 174.2-69.3 224.9-110 2.2-1.8 5.4-1.4 7.1 0.8 1.8 2.2 1.4 5.4-0.8 7.1-51.7 41.6-133.3 81.8-228.1 111.8z m232.3-115.1c-2.2 1.8-5.4 1.5-7.1-0.7-1.8-2.2-1.5-5.4 0.7-7.1 1.3-1.1 2.6-2.1 3.8-3.2 2.1-1.8 5.3-1.6 7.1 0.5 1.8 2.1 1.6 5.3-0.5 7.1-1.4 1.2-2.7 2.3-4 3.4z m39.2-41.4c13.2-19.5 18-38.2 13.5-55.2-8.5-31.9-47.9-52.3-109.9-59.9-2.8-0.3-5.3 1.6-5.6 4.4-0.3 2.8 1.6 5.3 4.4 5.6 58.3 7.1 94.2 25.7 101.3 52.4 3.7 13.8-0.4 29.6-12.1 46.9-1.6 2.3-1 5.5 1.4 7 2.3 1.7 5.4 1.1 7-1.2z" fill="#240F03" p-id="6464"></path><path d="M770.5 158.4m-38 0a38 38 0 1 0 76 0 38 38 0 1 0-76 0Z" fill="#0076FF" p-id="6465"></path><path d="M762.9 195.7c2.5 0.5 5 0.8 7.6 0.8 21 0 38-17 38-38s-17-38-38-38c-2.6 0-5.1 0.3-7.6 0.8 17.4 3.5 30.4 18.9 30.4 37.3s-13 33.6-30.4 37.1z" fill="#1D2AB1" p-id="6466"></path><path d="M755.3 199c-1.4 0-2.5-1.1-2.5-2.5s1.1-2.5 2.5-2.5c18.3 0 33.6-14 35.3-32.1 0.1-1.4 1.4-2.4 2.8-2.3s2.4 1.4 2.3 2.8c-2 20.6-19.4 36.6-40.4 36.6z m40.5-42.2c-0.1-1.8-0.3-3.6-0.6-5.4-0.2-1.4-1.6-2.3-2.9-2.1-1.4 0.2-2.3 1.6-2.1 2.9 0.3 1.5 0.4 3.1 0.5 4.7 0.1 1.4 1.2 2.5 2.6 2.4s2.6-1.1 2.5-2.5zM792 141c0.6 1.3 0.1 2.8-1.2 3.4-1.3 0.6-2.8 0.1-3.4-1.2-5.8-12.3-18.2-20.3-32.1-20.3-4.2 0-8.2 0.7-12.1 2.1-1.3 0.5-2.8-0.2-3.2-1.5-0.5-1.3 0.2-2.8 1.5-3.2 4.4-1.6 9-2.4 13.8-2.4 15.8 0 30 9.1 36.7 23.1z m-55.5-18.5c1.2-0.7 2.8-0.2 3.4 1.1s0.2 2.8-1.1 3.4c-1.4 0.7-2.7 1.6-4 2.5-1.1 0.8-2.7 0.5-3.5-0.6-0.8-1.1-0.5-2.7 0.6-3.5 1.4-1.1 3-2.1 4.6-2.9z m-12.6 10.3c0.9-1.1 2.5-1.2 3.6-0.4 1.1 0.9 1.2 2.5 0.4 3.6-5.2 6.3-8 14.2-8 22.5 0 9.6 3.8 18.6 10.5 25.2 1 1 1 2.6 0 3.6s-2.6 1-3.6 0c-7.6-7.6-12-17.8-12-28.8 0-9.6 3.2-18.6 9.1-25.7z m7 58c-1.1-0.8-1.3-2.4-0.5-3.5s2.4-1.3 3.5-0.5c1.3 1 2.6 1.8 4 2.6 1.2 0.7 1.7 2.2 1 3.5-0.7 1.2-2.2 1.7-3.5 1-1.5-1-3-2-4.5-3.1z m15.2 7.1c-1.4-0.3-2.2-1.7-1.9-3 0.3-1.4 1.7-2.2 3-1.9 2.6 0.6 5.3 0.9 8 0.9 1.4 0 2.5 1.1 2.5 2.5s-1.1 2.5-2.5 2.5c-3 0.1-6.1-0.3-9.1-1z" fill="#240F03" p-id="6467"></path><path d="M604.5 563.9c40.6 10.9 82.3-13.2 93.1-53.8 0.7-2.7-0.9-5.5-3.6-6.2-2.7-0.7-5.5 0.9-6.2 3.6-9.4 35.2-45.6 56-80.7 46.6-2.7-0.7-5.5 0.9-6.2 3.6-0.7 2.7 0.9 5.5 3.6 6.2z" fill="#240F03" p-id="6468"></path></svg>
                </router-link>
            </div>


        <a-menu
                theme="light"
                mode="horizontal"
        >
            <a-menu-item key="1"><router-link to="/square">广场</router-link></a-menu-item>
            <a-menu-item key="2"><router-link to="/follow">关注</router-link></a-menu-item>
            <a-menu-item key="3"><router-link to="/publish">发帖</router-link></a-menu-item>
            <a-menu-item key="4"><router-link to="/">我的帖子</router-link></a-menu-item>
            <a-menu-item key="5"><router-link to="/">好友列表</router-link></a-menu-item>
            <a-menu-item key="5"><router-link to="/">我的收藏</router-link></a-menu-item>
            <a-menu-item key="6" v-if="!user.id" :style="{marginLeft:'auto'}"><a-button type="text" @click="popSignIn">登录</a-button></a-menu-item>
            <a-menu-item key="7" v-if="!user.id"><a-button type="text" @click="popSignUp">注册</a-button></a-menu-item>
            <a-menu-item key="8" v-if="!!user.id" :style="{marginLeft:'auto'}">

                <a-button type="text">
                    <a-dropdown>
                        <a class="ant-dropdown-link" @click.prevent>
                            <a-avatar :src="SERVER + '/file/avatar/' + avatarName" alt="Han Solo"/>
                            {{user.name}}
                        </a>
                        <template #overlay>
                            <a-menu>
                                <a-menu-item>
                                    <router-link to="/adminInfo">
                                        <a-button type="text">个人中心</a-button>
                                    </router-link>
                                </a-menu-item>
                                <a-menu-item>
                                    <a-button type="text" @click="logout">退出登录</a-button>
                                </a-menu-item>
                            </a-menu>
                        </template>
                     </a-dropdown>
                </a-button>
            </a-menu-item>
        </a-menu>

    </a-layout-header>

    <a-modal v-model:visible="signInVisible" title="登录" @ok="handleSignInOk">
        <a-form>
            <a-form-item label="用户名">
                <a-input v-model:value="signInUser.username" placeholder="请输入用户名"/>
            </a-form-item>
            <a-form-item label="密码">
                <a-input v-model:value="signInUser.password" type="password" placeholder="请输入密码"/>
            </a-form-item>
        </a-form>
    </a-modal>


    <a-modal v-model:visible="signUpVisible" title="注册" @ok="handleSignUpOk">
        <a-form
                ref="formRef"
                :model="formState"
                :rules="rules"
                v-bind="layout"
                @finish="handleFinish"
                @validate="handleValidate"
                @finishFailed="handleFinishFailed"
        >
            <a-form-item label="用户名">
                <a-input v-model:value="signUpUser.username" placeholder="请输入用户名"/>
            </a-form-item>
            <a-form-item label="昵称">
                <a-input v-model:value="signUpUser.name" placeholder="请输入昵称"/>
            </a-form-item>
            <a-form-item has-feedback label="密码" name="password">
                <a-input v-model:value="formState.password" placeholder="请输入密码" type="password"/>
            </a-form-item>
            <a-form-item has-feedback label="确认密码" name="checkPass">
                <a-input v-model:value="formState.checkPass" placeholder="请再次输入密码" type="password" autocomplete="off"/>
            </a-form-item>
        </a-form>
    </a-modal>


</template>

<script>
    import {ref, reactive, computed, onMounted} from 'vue'
    import axios from 'axios'
    import { message } from 'ant-design-vue';
    import store from '@/store'
    import { Rule } from 'ant-design-vue/es/form';
    import { FormInstance } from 'ant-design-vue';
    import {useRoute, useRouter} from 'vue-router';

    export default {
        name: "TheHeader",

        setup(){
            const user = computed(() => store.state.user);

            /**
             * 登录
             */

            const signInVisible = ref(false);
            const signInUser = reactive({
                username: '',
                password: ''
            });

            const popSignIn = () => {
                signInVisible.value = true;
            };


            const handleSignInOk = () => {
                axios.post("/user/login", signInUser).then((response) => {
                    const data = response.data;
                    if (data.success) {
                        console.log("user:", user);
                        message.success("登录成功！");
                        signInVisible.value = false;
                        store.commit("setUser", data.content);
                    } else {
                        message.error(data.message);
                    }
                })
            };


            const signUpVisible = ref(false);
            const signUpUser = reactive({
                username: '',
                name: '',
                password: ''
            });

            const popSignUp = () => {
                signUpVisible.value = true;
            };


            /**
             * 密码校验
             */
            const formRef = ref();
            const formState = reactive({
                password: '',
                checkPass: '',
            });


            let validatePass = async (_rule, value) => {
                if (value === '') {
                    return Promise.reject('请输入密码');
                } else {
                    if (formState.checkPass !== '') {
                        formRef.value.validateFields('checkPass');
                    }
                    return Promise.resolve();
                }
            };

            let validatePass2 = async (_rule, value) => {
                if (value === '') {
                    return Promise.reject('请再次输入密码');
                } else if (value !== formState.password) {
                    return Promise.reject("两次密码输入不一致!");
                } else {
                    return Promise.resolve();
                }
            };

            const rules = {
                password: [{
                    validator: validatePass,
                    trigger: 'change',
                }],
                checkPass: [{
                    validator: validatePass2,
                    trigger: 'change',
                }]
            };

            const handleFinish = values => {
                console.log(values, formState);
            };

            const handleFinishFailed = errors => {
                console.log(errors);
            };

            const resetForm = () => {
                formRef.value.resetFields();
            };

            const handleValidate = (...args) => {
                console.log(args);
            };

            /**
             * 注册
             */

            const handleSignUpOk = () => {
                signUpUser.password = formState.password;
                axios.post(process.env.VUE_APP_SERVER + "/user/signup", signUpUser).then((response) => {
                    signInUser.username = response.data.content.username;
                    signInUser.password = signUpUser.password;
                    //自动登录
                    axios.post(process.env.VUE_APP_SERVER + "/user/login", signInUser).then((response) => {
                        const data = response.data;
                        if (data.success) {
                            store.commit("setUser", data.content);
                            message.success("注册成功，已自动登录！");
                            signUpVisible.value = false;
                        } else {
                            message.error(data.message);
                        }
                    })
                })
            };

            /**
             * 退出登录
             */

            const logout = () => {
                console.log("退出登录开始");
                axios.get('/user/logout/' + user.value.token).then((response) => {
                    const data = response.data;
                    if (data.success) {
                        message.success("退出登录成功！");
                        store.commit("setUser", {});
                    } else {
                        message.error(data.message);
                    }
                });
            };

            const SERVER = process.env.VUE_APP_SERVER;
            const avatarName = computed(() => {
                return user.value.avatar
            })

            return{
                popSignIn,
                popSignUp,
                signInVisible,
                signUpVisible,
                handleSignInOk,
                handleSignUpOk,
                signUpUser,
                signInUser,

                user,
                logout,

                rules,
                formState,
                formRef,
                handleFinishFailed,
                handleFinish,
                resetForm,
                handleValidate,

                avatarName,

                SERVER,
            }
        }
    }

</script>

<style scoped>
    .ant-layout-header {
        background: #FFFFFF;
    }
    .logo {
        float: left;
        width: 100px;
        height: 31px;
        margin: 0px 0px 0px 0;
        background: rgba(255, 255, 255, 0.3);
    }
</style>