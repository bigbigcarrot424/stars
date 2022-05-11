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
        props: ['friendId'],
        setup(props) {
            const router = useRouter()
            const toChat = () => {
                console.log("friendId",props.friendId)
                router.push({
                    name:'chat',
                    params:{
                        friendId: props.friendId,
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