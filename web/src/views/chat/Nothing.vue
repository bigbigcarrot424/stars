<template>
    
</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import axios from 'axios'
    import store from '@/store'
    import { HomeOutlined, DeleteOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import  {useRouter, useRoute}  from "vue-router";
    export default {
        name: "Nothing",
        props: ['friendId', 'friendName', 'friendAvatar'],
        setup(props) {
            const router = useRouter()
            const toChat = () => {
                router.push({
                    name:'chat',
                    params:{
                        friendId: props.friendId,
                        friendName: props.friendName,
                        friendAvatar: props.friendAvatar,
                    }
                })
            }
            onMounted(() => {
                toChat()
            })
        }
    }
</script>

<style scoped>

</style>