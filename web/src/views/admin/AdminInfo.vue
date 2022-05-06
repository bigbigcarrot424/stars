<template>

    <a-modal
            v-model:visible="visible"
            title="修改头像"
            ok-text="确认"
            cancel-text="取消"
            @ok="alterAvatar"
    >
        <div style="text-align: center">
            <a-upload
                    v-model:file-list="fileList"
                    name="avatar"
                    list-type="picture-card"
                    class="avatar-uploader"
                    :show-upload-list="false"
                    :action="SERVER + '/user/upload/avatar'"
                    :before-upload="beforeUpload"
                    :headers="headers"
                    @change="handleChange"
                    :icon-render="UserOutlined"
            >
                    <a-avatar :size="120" v-if="imageUrl" :src="imageUrl" alt="Han Solo"/>
                    <div v-else>
                        <loading-outlined v-if="loading"></loading-outlined>
                        <plus-outlined v-else></plus-outlined>
                    </div>
            </a-upload>
        </div>
    </a-modal>
    <a-descriptions title="个人信息" bordered :column="4">
        <template #extra>
            <a-button type="primary" v-if="!ifEdit" @click="editInfo">编辑</a-button>
            <a-button type="primary" v-else danger @click="saveInfo">保存</a-button>
        </template>

        <a-descriptions-item label="用户昵称：" :span="1">
            <a-input type="text" v-model:value="user.name" v-if="ifEdit"/>
            <span v-else>{{user.name}}</span>
        </a-descriptions-item>
        <a-descriptions-item label="性别：" :span="1">
            <a-radio-group v-model:value="user.gender" name="radioGroup" v-if="ifEdit">
                <a-radio value="male">男</a-radio>
                <a-radio value="female">女</a-radio>
            </a-radio-group>
            <span v-else>{{user.gender == 'male' ? '男' : '女'}}</span>
        </a-descriptions-item>
        <a-descriptions-item label="年龄：" :span="1">
            <div v-if="ifEdit">
                <a-input-number id="userAge" v-model:value="user.age" :min="1" :max="100" />
            </div>
            <span v-else>{{user.age}}</span>
        </a-descriptions-item>
        <a-descriptions-item label="状态：" :span="1">
            <div v-if="user.status">
                <a-badge status="processing" text="在线" />
            </div>
            <div v-else>
                <a-badge status="default" text="离线"/>
            </div>

        </a-descriptions-item>

        <a-descriptions-item label="所在兴趣圈：" :span="4">{{user.circles}}羽毛球社、LOL</a-descriptions-item>


        <a-descriptions-item label="头像：" :span="1">
            <a-avatar :size="64" :src="'http://localhost:8080/file/avatar/' + avatarName" alt="Han Solo"/>
            <a-button type="primary" v-if="ifEdit" @click="showModal">修改头像</a-button>
        </a-descriptions-item>
        <a-descriptions-item label="自我介绍：" :span="3">{{user.self_intro}}来自上个世纪的萝卜种子</a-descriptions-item>
        <a-descriptions-item label="个性标签">
            {{user.tags}}
            <a-tag color="pink">学霸</a-tag>
            <a-tag color="red">插画家</a-tag>
            <a-tag color="orange">数码产品技术宅</a-tag>
            <a-tag color="green">云吸猫吸狗</a-tag>
            <a-tag color="cyan">在山顶看过日出</a-tag>
            <a-tag color="blue">社恐自闭小孩</a-tag>
            <a-tag color="purple">老歌听不腻</a-tag>
        </a-descriptions-item>
    </a-descriptions>
</template>

<script>
    import { UserOutlined } from '@ant-design/icons-vue';
    import { PlusOutlined, LoadingOutlined } from '@ant-design/icons-vue';
    import { message } from 'ant-design-vue';
    import { defineComponent, ref, onMounted, computed } from 'vue';
    import { Modal } from 'ant-design-vue';
    import store from '@/store'


    function getBase64(img, callback) {
        const reader = new FileReader();
        reader.addEventListener('load', () => callback(reader.result));
        reader.readAsDataURL(img);
    }

    export default {
        name: "AdminInfo",
        components: {
            UserOutlined,
            LoadingOutlined,
            PlusOutlined,
        },
        setup(){
            // const user = computed(() => store.state.user);

            const user = store.state.user
            user.gender = "male"

            const ifEdit = ref();
            ifEdit.value = false;

            const editInfo = () => {
                ifEdit.value = ifEdit.value ? false : true;
            }

            const saveInfo = () => {
                ifEdit.value = ifEdit.value ? false : true;
            }
            /**
             * 获取个人详细信息
             */
            const getUserInfo = () => {
                axios.get(SERVER + "/user/")
            }

            /**
             * 头像上传
             */
            const fileList = ref([]);
            const loading = ref(false);
            const imageUrl = ref('');


            const SERVER = process.env.VUE_APP_SERVER;

            const handleChange = info => {
                if (info.file.status === 'uploading') {
                    loading.value = true;
                    return;
                }

                if (info.file.status === 'done') {
                    // Get this url from response in real world.
                    getBase64(info.file.originFileObj, base64Url => {
                        imageUrl.value = base64Url;
                        loading.value = false;
                    });
                }

                if (info.file.status === 'error') {
                    loading.value = false;
                    message.error('upload error');
                }
            };

            const beforeUpload = file => {
                console.log(file)
                const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';

                if (!isJpgOrPng) {
                    message.error('You can only upload JPG file!');
                }

                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isLt2M) {
                    message.error('Image must smaller than 2MB!');
                }

                return isJpgOrPng && isLt2M;
            };

            /**
             * 头像上传对话框
             */

            const visible = ref(false);

            const showModal = () => {
                visible.value = true;
            };

            const alterAvatar = () => {
                visible.value = false;
            };

            const confirm = () => {
                Modal.confirm({
                    title: 'Confirm',
                    icon: createVNode(ExclamationCircleOutlined),
                    content: 'Bla bla ...',
                    okText: '确认',
                    cancelText: '取消',
                });
            };

            const avatarName = ref();
            avatarName.value = store.state.user.avatar;

            return {
                fileList,
                loading,
                imageUrl,
                handleChange,
                beforeUpload,
                headers: {
                    userId: store.state.user.id,
                },
                SERVER,
                ifEdit,

                visible,
                showModal,
                alterAvatar,
                confirm,
                avatarName,

                editInfo,
                saveInfo,

                // 个人信息：
                user
            };
        }
    }
</script>

<style scoped>

</style>