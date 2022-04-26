<template>
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
  >
    <img v-if="imageUrl" :src="imageUrl" alt="avatar" />
    <div v-else>
      <loading-outlined v-if="loading"></loading-outlined>
      <plus-outlined v-else></plus-outlined>
      <div class="ant-upload-text">Upload</div>
    </div>
  </a-upload>
</template>
<script>
  import { PlusOutlined, LoadingOutlined } from '@ant-design/icons-vue';
  import { message } from 'ant-design-vue';
  import { defineComponent, ref } from 'vue';
  import store from '@/store'

  function getBase64(img, callback) {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result));
    reader.readAsDataURL(img);
  }

  export default defineComponent({
    components: {
      LoadingOutlined,
      PlusOutlined,
    },

    setup() {
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

      return {
        fileList,
        loading,
        imageUrl,
        handleChange,
        beforeUpload,
        SERVER,
        headers: {
          userId: store.state.user.id,
        },
      };
    },

  });
</script>
<style>
  .avatar-uploader > .ant-upload {
    width: 128px;
    height: 128px;
  }
  .ant-upload-select-picture-card i {
    font-size: 32px;
    color: #999;
  }

  .ant-upload-select-picture-card .ant-upload-text {
    margin-top: 8px;
    color: #666;
  }
</style>