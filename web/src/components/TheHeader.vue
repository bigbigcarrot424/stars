<template>
    <a-layout-header :style="{ zIndex: 1, width: '100%' }">
        <div class="logo">

        </div>
        <a-menu
                theme="light"
                mode="horizontal"
        >
            <a-menu-item key="1"><router-link to="/square">广场</router-link></a-menu-item>
            <a-menu-item key="2"><router-link to="/follow">关注</router-link></a-menu-item>
            <a-menu-item key="3"><router-link to="/publish">发帖</router-link></a-menu-item>
            <a-menu-item key="4" v-if="!user.id" :style="{marginLeft:'auto'}"><a-button type="text" @click="popSignIn">登录</a-button></a-menu-item>
            <a-menu-item key="5" v-if="!user.id"><a-button type="text" @click="popSignUp">注册</a-button></a-menu-item>
            <a-menu-item key="6" v-if="!!user.id" :style="{marginLeft:'auto'}">

                <a-button type="text">
                    <a-dropdown>
                        <a class="ant-dropdown-link" @click.prevent>
                            {{user.name}}
                            <DownOutlined />
                        </a>
                        <template #overlay>
                            <a-menu>
                                <a-menu-item>
                                    <router-link to="">
                                        <a-button type="text">修改信息</a-button>
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


    <a-modal v-model:visible="signUpVisible" title="登录" @ok="handleSignUpOk">
        <a-form>
            <a-form-item label="用户名">
                <a-input v-model:value="signUpUser.username" placeholder="请输入用户名"/>
            </a-form-item>
            <a-form-item label="昵称">
                <a-input v-model:value="signUpUser.name" placeholder="请输入昵称"/>
            </a-form-item>
            <a-form-item label="密码">
                <a-input v-model:value="signUpUser.password" placeholder="请输入密码" type="password"/>
            </a-form-item>
            <a-form-item label="确认密码">
                <a-input v-model:value="comfirmPassword" placeholder="请再次输入密码" type="password"/>
            </a-form-item>
        </a-form>
    </a-modal>


</template>

<script>
    import {ref, reactive, computed} from 'vue'
    import axios from 'axios'
    import { message } from 'ant-design-vue';
    import store from '@/store'
    export default {
        name: "TheHeader",
        setup(){
            const user = computed(() => store.state.user);

            const signInVisible = ref(false);
            const signInUser = reactive({
                username: '',
                password: ''
            });

            const popSignIn = () => {
                signInVisible.value = true;
            };

            const handleSignInOk = () => {
                axios.post(process.env.VUE_APP_SERVER + "/user/login", signInUser).then((response) => {
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
            const confirmPassword = ref('');

            const popSignUp = () => {
                signUpVisible.value = true;
            };


            const handleSignUpOk = () => {
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


            return{
                popSignIn,
                popSignUp,
                signInVisible,
                signUpVisible,
                handleSignInOk,
                handleSignUpOk,
                signUpUser,
                signInUser,
                confirmPassword,
                user,
                logout,
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
        margin: 16px 24px 16px 0;
        background: rgba(255, 255, 255, 0.3);
    }
</style>