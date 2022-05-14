<template>


</template>

<script>
    import {defineComponent, ref, onMounted, reactive, computed} from 'vue';
    import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import axios from 'axios'
    import store from '@/store'

    export default defineComponent({
        name: "Explore",
        components: {
            StarOutlined,
            LikeOutlined,
            MessageOutlined,
        },
        setup() {


            onMounted(()=>{

            })
            return {

            };
        },
    });
</script>

<style scoped>

</style>