<template>
    <a-list item-layout="horizontal" :data-source="data">
        <template #renderItem="{ item }">
            <a-list-item>
                <a-list-item-meta
                        description="Ant Design, a design language for background applications, is refined by Ant UED Team"
                >
                    <template #title>
                        <a href="https://www.antdv.com/">{{ item.title }}</a>
                    </template>
                    <template #avatar>
                        <a-avatar src="https://joeschmoe.io/api/v1/random" />
                    </template>
                </a-list-item-meta>
            </a-list-item>
        </template>
    </a-list>
    <div id="app">
        <div class="left">
            <input type="text" v-model="userName"> <button @click="add">加入</button>
            <br><br><br>
            <textarea
                    cols="30"
                    style="height: 50px"
                    v-model="message"
            ></textarea>
            <br>
            <button class="send" @click="sendMessage">发送</button>

            <button class="reset" @click="message = null">重置</button>

            <button class="close" @click="close">断开</button>
        </div>
        <div class="right">
            <p>{{isOpenMessage}}</p>
            <p v-for="(message,index) in messageList" :key="index"> {{ message }}</p>
        </div>
    </div>
</template>

<script>
        // import {listMsg,sendMsg} from "@/api/index";
        import Comment from "../../components/Comment";
        import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
        import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
        import axios from 'axios'
        import store from '@/store'
        export default {
            setup(){
                let ws;
                const url = 'ws://localhost:8080/ws/chat/';
                let isOpenMessage = '暂未连接到服务器';
                let messageList = ref([]);
                let message = ref();
                const userName = ref(store.state.user.name);
                // 加入聊天室
                const add = ()=> {
                    // 创建WebSocket连接
                    ws = new WebSocket(url+store.state.user.id);

                    // 建立连接成功回调
                    ws.onopen = () =>{
                        isOpenMessage = '服务器连接成功'
                    }

                    // 客户端收到服务器端消息时回调
                    // 这里是收到的服务端发回的信息
                    ws.onmessage = (message) => {
                        messageList.value.push(message.data)
                        console.log(message.data);
                    }
                }
                // 判断是否支持WebSocket
                const isSocket = ()=> {
                    if (!('WebSocket' in window)) {
                        alert('该浏览器不支持WebSocket')
                        return false
                    }
                    return true
                }

                // 发送消息
                const sendMessage = ()=> {
                    ws.send(message.value)
                }

                // 连接关闭
                const close = ()=> {
                    ws.close()
                }

                onMounted(() => {
                    isSocket()
                })

                return {
                    url,
                    ws,
                    userName,
                    isOpenMessage,
                    messageList,
                    message,
                    add,
                    sendMessage,
                    close,
                }
            }
        }

</script>


<style scoped>
    .send, .reset, .close {
        margin: 10px;
    }
    .left, .right {
        width: 300px;
        float: left
    }

    .sentence{
        border-radius: 5px;
        padding-left: 5px;
        padding-right: 5px;
    }
</style>