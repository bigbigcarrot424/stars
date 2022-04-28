<template>
    <div style="border: 1px solid #ccc">
        <Toolbar
                style="border-bottom: 1px solid #ccc"
                :editor="editorRef"
                :defaultConfig="toolbarConfig"
                :mode="mode"
        />
        <Editor
                style="height: 500px; overflow-y: hidden;"
                v-model="valueHtml"
                :defaultConfig="editorConfig"
                :mode="mode"
                @onCreated="handleCreated"
        />
    </div>
    <a-button type="primary" shape="round" size="large" :style="{'float': 'right', 'margin': '15px'}" @click="saveBlog()">发帖</a-button>
</template>

<script>
    import '@wangeditor/editor/dist/css/style.css'
    import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
    import { message } from 'ant-design-vue';
    import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
    import { DomEditor } from '@wangeditor/editor'
    import axios from 'axios'
    import store from '@/store'
    import { useRouter } from 'vue-router';

    export default {
        name: "AdminPublish",
        components: { Editor, Toolbar },
        setup(){
            // 编辑器实例，必须用 shallowRef
            const editorRef = shallowRef()

            // 内容 HTML
            // const valueHtml = ref('<p>hello</p>')
            const valueHtml = ref('')

            onMounted(() => {
                // setTimeout(() => {
                //     console.log(editorRef.value.getAllMenuKeys());
                // }, 1500)
                // 模拟 ajax 异步获取内容
                // const text = editor.getHtml();
                // console.log(text)
            })

            const toolbarConfig = {
                excludeKeys: [
                    'group-more-style', // 排除菜单组，写菜单组 key 的值即可
                    'fullScreen',
                    'color',
                    'bgColor',
                    'justifyLeft',
                    'justifyRight',
                    'justifyCenter',
                    'justifyJustify',
                    'codeBlock',
                    'bulletedList',
                    'numberedList',
                    'insertTable',
                    'deleteTable',
                    'insertTableRow',
                    'deleteTableRow',
                    'insertTableCol',
                    'deleteTableCol',
                    'todo',
                    'insertVideo',
                    'insertLink',
                    'insertImage',
                    "group-image",
                    "blockquote",
                    "header3",
                    "header2",
                ],
            }
            const editorConfig = { placeholder: '请输入内容...' }

            // 组件销毁时，也及时销毁编辑器
            onBeforeUnmount(() => {
                const editor = editorRef.value
                if (editor == null) return
                editor.destroy()
            })

            const handleCreated = (editor) => {
                editorRef.value = editor // 记录 editor 实例，重要！
            }

            const { push, replace } = useRouter();

            const saveBlog = () => {
                const blog = {
                    authorId: store.state.user.id,
                    content: editorRef.value.getHtml(),
                }
                axios.post(process.env.VUE_APP_SERVER + "/blog/save", blog).then((response) => {
                    const data = response.data;
                    if(data.success){
                        message.success("发帖成功！")
                    }else {
                        message.error(data.message);
                    }
                })
                push('/square');
            }


            return {
                editorRef,
                valueHtml,
                mode: 'simple', // 或 'simple'
                toolbarConfig,
                editorConfig,
                handleCreated,
                saveBlog,
            };
        },
    }
</script>

<style scoped>

</style>