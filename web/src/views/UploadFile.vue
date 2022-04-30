<template>
  <a-list
          v-if="comments.length"
          :data-source="comments"
          :header="`${comments.length} ${comments.length > 1 ? 'replies' : 'reply'}`"
          item-layout="horizontal"
  >
    <template #renderItem="{ item }">
      <a-list-item>
        <a-comment
                :author="item.author"
                :avatar="item.avatar"
                :content="item.content"
                :datetime="item.datetime"
        />
      </a-list-item>
    </template>
  </a-list>
  <a-button @click="showOrHide">展开/收起</a-button>
  <a-comment v-if="commentShow">
    <template #avatar>
      <a-avatar src="https://joeschmoe.io/api/v1/random" alt="Han Solo" />
    </template>
    <template #content>
      <a-form-item>
        <a-textarea v-model:value="value" :rows="4" />
      </a-form-item>
      <a-form-item>
        <a-button html-type="submit" :loading="submitting" type="primary" @click="handleSubmit">
          Add Comment
        </a-button>
      </a-form-item>
    </template>
  </a-comment>
</template>

<script>
  import { defineComponent, ref } from 'vue';
  import dayjs from 'dayjs';
  import relativeTime from 'dayjs/plugin/relativeTime';
  dayjs.extend(relativeTime);

  export default defineComponent({
    setup() {
      const comments = ref([]);
      const submitting = ref(false);
      const value = ref('');
      const commentShow = ref(false);
      const showOrHide = () => {
        commentShow.value = true;
      }

      const handleSubmit = () => {
        if (!value.value) {
          return;
        }

        submitting.value = true;
        setTimeout(() => {
          submitting.value = false;
          comments.value = [{
            author: 'Han Solo',
            avatar: 'https://joeschmoe.io/api/v1/random',
            content: value.value,
            datetime: dayjs().fromNow(),
          }, ...comments.value];
          value.value = '';
        }, 1000);
      };

      return {
        comments,
        submitting,
        value,
        handleSubmit,
        showOrHide,
        commentShow,
      };
    }

  });
</script>
<style scoped>
  .avatar-uploader > .ant-upload {
    width: 128px;
    height: 128px;
  }
  span.ant-upload {
    width: 32px !important;
  }

  ant-upload-select-picture-card i {

    font-size: 32px;
    color: #999;
  }

  .avatar-img {
    width: 10px !important;
    height: 10px !important;
  }

  .ant-upload-select-picture-card .ant-upload-text {
    margin-top: 8px;
    color: #666;
  }
</style>