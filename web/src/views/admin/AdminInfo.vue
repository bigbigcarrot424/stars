<template>
    <a-descriptions title="个人信息" bordered :column="4">
        <template #extra>
            <a-button type="primary">编辑</a-button>
        </template>
        <a-descriptions-item label="用户昵称：" :span="1"><input type="text" value="你好" v-show="ifEdit.value">大萝卜</a-descriptions-item>
        <a-descriptions-item label="性别：" :span="1">男</a-descriptions-item>
        <a-descriptions-item label="年龄：" :span="1">22</a-descriptions-item>
        <a-descriptions-item label="头像：" :span="1"><a-avatar src="https://joeschmoe.io/api/v1/random" alt="Han Solo"/></a-descriptions-item>
        <a-descriptions-item label="所在兴趣圈：" :span="4">羽毛球社、LOL</a-descriptions-item>

        <a-descriptions-item label="Status" :span="3">
            <a-badge status="processing" text="Running" />
        </a-descriptions-item>
        <a-descriptions-item label="Negotiated Amount">$80.00</a-descriptions-item>
        <a-descriptions-item label="Discount">$20.00</a-descriptions-item>
        <a-descriptions-item label="Official Receipts">$60.00</a-descriptions-item>
        <a-descriptions-item label="Config Info">
            Data disk type: MongoDB
            <br />
            Database version: 3.4
            <br />
            Package: dds.mongo.mid
            <br />
            Storage space: 10 GB
            <br />
            Replication factor: 3
            <br />
            Region: East China 1
            <br />
        </a-descriptions-item>
    </a-descriptions>

</template>

<script>
    import {ref} from 'vue'
    export default {
        name: "AdminInfo",
        setup(){
            const ifEdit = ref(false);
            return{
                ifEdit,
            }
        }
    }
</script>

<style scoped>

</style>