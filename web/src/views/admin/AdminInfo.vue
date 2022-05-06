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
            <div v-else>
                <a-button type="primary" danger @click="saveInfo" style="margin-right: 20px">保存</a-button>
                <a-button type="primary" @click="cancelEdit">取消</a-button>
            </div>
        </template>

        <a-descriptions-item label="用户昵称：" :span="1">
            <div v-if="ifEdit">
                <a-input type="text" v-model:value="user.name" />
            </div>
            <div v-else>
                <span>{{user.name}}</span>
            </div>
        </a-descriptions-item>
        <a-descriptions-item label="性别：" :span="1">
            <a-radio-group v-model:value="user.gender" name="radioGroup" v-if="ifEdit">
                <a-radio value="male">男</a-radio>
                <a-radio value="female">女</a-radio>
            </a-radio-group>
            <span v-else>{{user.gender === 'male' ? '男' : '女'}}</span>
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

        <a-descriptions-item label="所在兴趣圈：" :span="4">{{user.circles}}</a-descriptions-item>


        <a-descriptions-item label="自我介绍：" :span="3">
            <div v-if="ifEdit">
                <a-textarea :rows="4" placeholder="介绍一下自己吧~" :maxlength="1000" v-model:value="user.selfIntro"/>
            </div>
            <div v-else>
                {{user.selfIntro}}
            </div>
        </a-descriptions-item>
        <a-descriptions-item label="头像：" :span="1">
            <a-avatar :size="64" :src="'http://localhost:8080/file/avatar/' + avatarName" alt="Han Solo"/>
            <a-button type="primary" v-if="ifEdit" @click="showModal">修改头像</a-button>
        </a-descriptions-item>

        <a-descriptions-item label="个性标签">
            <div v-if="ifEdit">
                <template v-for="(tag, index) in tags" :key="tag">
                    <a-tooltip v-if="tag.length > 20" :title="tag">
                        <a-tag :closable="index !== 0" @close="handleClose(tag)">
                            {{ `${tag.slice(0, 20)}...` }}
                        </a-tag>
                    </a-tooltip>
                    <a-tag v-else :closable="index !== -1" @close="handleClose(tag)">
                        {{ tag }}
                    </a-tag>
                </template>
                <a-input
                        v-if="inputVisible"
                        ref="inputRef"
                        v-model:value="inputValue"
                        type="text"
                        size="small"
                        :style="{ width: '78px' }"
                        @blur="handleInputConfirm"
                        @keyup.enter="handleInputConfirm"
                />
                <a-tag v-else style="background: #fff; border-style: dashed" @click="showInput">
                    <plus-outlined />
                    自定义标签
                </a-tag>
            </div>
            <div v-else>
                <span v-for="(tag, index) in tags" :key="tag">
                    <a-tag>{{tag}}</a-tag>
                </span>
            </div>
        </a-descriptions-item>
    </a-descriptions>
</template>

<script>
    import { UserOutlined } from '@ant-design/icons-vue';
    import { PlusOutlined, LoadingOutlined } from '@ant-design/icons-vue';
    import { message } from 'ant-design-vue';
    import { defineComponent, ref, onMounted, computed, reactive, toRefs, nextTick } from 'vue';
    import { Modal } from 'ant-design-vue';
    import store from '@/store'
    import axios from 'axios'


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
            const user = computed(() => store.state.user);

            const ifEdit = ref();
            ifEdit.value = false;

            const editInfo = () => {
                ifEdit.value = ifEdit.value ? false : true;
            }

            /**
             * 获取个人详细信息
             */
            const getUserInfo = () => {
                const userStored = store.state.user;
                axios.get(SERVER + "/user/info/" + user.value.id).then((response) => {
                    const data = response.data.content;
                    const userInfo = {...userStored, ...data};
                    store.commit("setUser", userInfo)
                })
            }


            /**
             * 编辑个人信息
             */
            const saveInfo = () => {
                ifEdit.value = ifEdit.value ? false : true;
                user.value.tags = JSON.stringify(state.tags);
                axios.post(SERVER + "/user/updateInfo/", user.value).then((response) => {
                    if (response.data.success){
                        message.success("修改信息成功！");
                        getUserInfo();
                    }else {
                        message.error(data.error);
                    }
                })
            }

            const cancelEdit = () => {
                getUserInfo();
                ifEdit.value = ifEdit.value ? false : true;
            }

            /**
             * 个性标签
             */

            const inputRef = ref();
            const tags = ref();
            tags.value = JSON.parse(store.state.user.tags);
            const state = reactive({
                tags: tags.value,
                inputVisible: false,
                inputValue: '',
            });

            const handleClose = removedTag => {
                const tags = state.tags.filter(tag => tag !== removedTag);
                console.log(tags);
                state.tags = tags;
            };

            const showInput = () => {
                state.inputVisible = true;
                nextTick(() => {
                    inputRef.value.focus();
                });
            };

            const handleInputConfirm = () => {
                const inputValue = state.inputValue;
                let tags = state.tags;

                if (inputValue && tags.indexOf(inputValue) === -1) {
                    tags = [...tags, inputValue];
                }

                console.log(tags);
                Object.assign(state, {
                    tags,
                    inputVisible: false,
                    inputValue: '',
                });
            };



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

            onMounted(() =>{
                getUserInfo();
                console.log(state.tags[0]);
            })

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
                cancelEdit,

                // 个人信息：
                user,

                ...toRefs(state),
                handleClose,
                showInput,
                handleInputConfirm,
                inputRef,
            };
        }
    }
</script>

<style scoped>

</style>