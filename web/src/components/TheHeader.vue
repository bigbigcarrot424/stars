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
            <a-menu-item key="4" :style="{marginLeft:'auto'}"><a-button type="text" @click="popSignIn">登录</a-button></a-menu-item>
            <a-menu-item key="5"><a-button type="text" @click="popSignUp">注册</a-button></a-menu-item>
            <a-menu-item key="6">{{user.name}}</a-menu-item>
        </a-menu>

    </a-layout-header>

    <a-modal v-model:visible="signInVisible" title="登录" @ok="handleSignInOk">
        <a-form>
            <a-form-item label="用户名">
                <a-input placeholder="请输入用户名"/>
            </a-form-item>
            <a-form-item label="密码">
                <a-input placeholder="请输入密码"/>
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
    import {ref, reactive} from 'vue'
    import axios from 'axios'
    import { message } from 'ant-design-vue';
    export default {
        name: "TheHeader",
        setup(){
            let user = ref({});

            const signInVisible = ref(false);
            const popSignIn = () => {
                signInVisible.value = true;
            };
            const handleSignInOk = () => {
                axios.get(process.env.VUE_APP_SERVER + "/user/login")
                signInVisible.value = false;
            };


            const signUpVisible = ref(false);
            const signUpUser = reactive({
                username: 'luobo',
                name: '',
                password: ''
            });
            const confirmPassword = ref('');

            const popSignUp = () => {
                signUpVisible.value = true;
            };


            const handleSignUpOk = () => {
                axios.post(process.env.VUE_APP_SERVER + "/user/signup", signUpUser).then((response) => {
                        user = response.data.content;
                        message.success("注册成功，已自动登录！");
                        signUpVisible.value = false;
                    },(error) =>{
                        message.error(error);
                    }
                )
                signInVisible.value = false;
            };


            return{
                popSignIn,
                popSignUp,
                signInVisible,
                signUpVisible,
                handleSignInOk,
                handleSignUpOk,
                signUpUser,
                confirmPassword,
                user,
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