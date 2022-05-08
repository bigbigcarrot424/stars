<template>

    <a-card hoverable style="width: 300px">
<!--        <template #cover>-->
<!--            <img-->
<!--                    alt="example"-->
<!--                    src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"-->
<!--            />-->
<!--        </template>-->
        <template #actions>
            <home-outlined key="home" @click=""/>
            <message-outlined key="message" @click=""/>
            <delete-outlined key="delete" @click=""/>
        </template>
        <a-card-meta title="Card title" description="This is the description">
            <template #avatar>
                <a-avatar src="https://joeschmoe.io/api/v1/random" />
            </template>
        </a-card-meta>
    </a-card>

</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import axios from 'axios'
    import store from '@/store'
    import { HomeOutlined, DeleteOutlined, MessageOutlined} from '@ant-design/icons-vue';


    export default defineComponent({
        name: "MyFriend",
        components: {
            MessageOutlined,
            DeleteOutlined,
            HomeOutlined,
        },
        setup() {

            onMounted(()=>{

            })
            return {

            };
        },
    });
</script>