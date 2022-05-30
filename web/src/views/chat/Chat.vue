<template>
    <div class="bg-info panel panel-default" style="height: 600px">
        <div class="panel-heading">
            <a-avatar :src= "SERVER + '/file/avatar/' + friendAvatar"></a-avatar>
            <span style="margin-left: 10px">{{friendName}}</span>
        </div>

        <div id="chatWindow" style="height: 300px; overflow-y:scroll">
            <div v-for="(message, index) in messageList" :key="index" style="margin-left: 30px; margin-right: 30px; margin-top: 10px">

                <div v-if="message.senderId === userId">
                    <div style="text-align: right">
                        <span class="sentence bg-success" style="margin-right: 10px">{{ message.content }}</span>
                        <a-avatar :src= "SERVER + '/file/avatar/' + userAvatar"/>
                    </div>
                </div>

                <div v-else>
                    <div style="text-align: left">
                        <a-avatar :src= "SERVER + '/file/avatar/' + friendAvatar" />
                        <span class="sentence bg-primary" style="margin-left: 10px">{{ message.content }}</span>
                    </div>
                </div>
            </div>
        </div>

        <div style="margin-top: 50px;">
            <a-input-group compact>
                <a-input v-model:value="content" style="width: calc(100% - 130px)" />
                <a-button type="primary" @click="sendMessage" >发送</a-button>
                <a-button type="primary" danger @click="content = null" style="float: right">清空</a-button>
            </a-input-group>
        </div>
    </div>

</template>

<script>
        import Comment from "../../components/Comment";
        import {defineComponent, ref, onMounted, reactive, computed, onBeforeUnmount, onUpdated, onBeforeUpdate} from 'vue';
        import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
        import { message } from 'ant-design-vue'
        import axios from 'axios'
        import store from '@/store'
        import dayjs from 'dayjs'
        export default {
            props:['friendId', 'friendName', 'friendAvatar'],
            setup(props){
                let ws;
                const url = 'ws://localhost:8080/ws/chat/';
                let messageList = ref([]);
                let content = ref();
                const userName = ref(store.state.user.name);
                const userId = ref(store.state.user.id);
                const userAvatar = ref(store.state.user.avatar);

                const getMessageList = () => {
                    axios.get(SERVER + '/message/getMessage/' + store.state.user.id + '/' + props.friendId).then((response)=>{
                        const data = response.data;
                        if (data.success){
                            messageList.value = data.content;
                        }

                    })
                }

                // 加入聊天室
                const add = ()=> {
                    // 创建WebSocket连接
                    ws = new WebSocket(url+store.state.user.id + '/' + props.friendId);

                    // 建立连接成功回调
                    ws.onopen = () =>{
                        // isOpenMessage = '服务器连接成功'
                    }

                    // 客户端收到服务器端消息时回调
                    // 这里是收到的服务端发回的信息
                    ws.onmessage = (message) => {
                        const messageInfo = {
                            senderId: '',
                            receiverId: '',
                            content: '',
                            time: '',
                        }
                        messageInfo.senderId = message.data.split(':')[0];
                        messageInfo.receiverId = message.data.split(':')[1];
                        messageInfo.content = message.data.split(':')[2];
                        messageInfo.time = dayjs().format('YYYY-MM-DD HH:mm:ss');

                        messageList.value.push(messageInfo)
                    }
                }

                // 发送消息
                const sendMessage = ()=> {
                    ws.send(content.value)
                    const messageItem = {
                        senderId: store.state.user.id,
                        receiverId: props.friendId,
                        content: content.value,
                    }
                    let chatWindow = document.getElementById("chatWindow");
                    axios.post(SERVER + '/message/save', messageItem).then((response)=>{
                        const data = response.data;
                        if (data.success){
                            message.success("消息发送成功！")
                        }
                        chatWindow.scrollTop = chatWindow.scrollHeight
                    })
                    content.value = '';

                }

                // 连接关闭
                const close = ()=> {
                    ws.close()
                }

                const SERVER = process.env.VUE_APP_SERVER
                onMounted(() => {
                    add();
                    getMessageList();
                    setTimeout(() => {
                        let chatWindow = document.getElementById("chatWindow");
                        chatWindow.scrollTop = chatWindow.scrollHeight
                    }, 100)
                })

                onBeforeUnmount(() => {
                    ws.close()
                })

                return {
                    url,
                    ws,
                    userName,
                    userId,
                    userAvatar,
                    messageList,
                    content,
                    add,
                    sendMessage,
                    close,
                    SERVER,
                }
            }
        }

</script>


<style scoped>

    .sentence{
        border-radius: 5px;
        padding-left: 5px;
        padding-right: 5px;
    }

    .chat{
        margin-left: 200px;
        margin-right: 200px;
        margin-top: 50px
    }

</style>