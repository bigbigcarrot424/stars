import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import AdminSquare from '../views/admin/AdminSquare.vue'
import AdminPublish from '../views/admin/AdminPublish.vue'
import AdminInfo from '../views/admin/AdminInfo.vue'
import AppIntro from '../views/AppIntro.vue'
import UploadFile from '../views/UploadFile.vue'
import OthersInfo from '../views/admin/OthersInfo.vue'
import MyBlog from '../views/admin/MyBlog.vue'
import MyFriend from '../views/admin/MyFriend.vue'
import AdminBlogEdit from '../views/admin/AdminBlogEdit.vue'
import Follow from '../views/admin/Follow.vue'
import Chat from '../views/chat/Chat.vue'
import Nothing from '../views/chat/Nothing.vue'
import Circles from '../views/Circles.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'appIntro',
    component: AppIntro
  },
  {
    path: '/square',
    name: 'square',
    component: AdminSquare
  },
  {
    path: '/follow',
    name: 'follow',
    component: Follow
  },
  {
    path: '/publish',
    name: 'publish',
    component: AdminPublish
  },
  {
    path: '/myBlog',
    name: 'myBlog',
    component: MyBlog
  },
  {
    path: '/myFriend',
    name: 'myFriend',
    component: MyFriend
  },
  {
    path: '/adminInfo',
    name: 'adminInfo',
    component: AdminInfo
  },
  {
    path: '/adminBlogEdit',
    name: 'adminBlogEdit',
    props: true,
    component: AdminBlogEdit
  },
  {
    path: '/othersInfo',
    name: 'othersInfo',
    component: OthersInfo
  },
  {
    path: '/circles',
    name: 'circles',
    component: Circles
  },
  {
    path: '/nothing',
    name: 'nothing',
    props: true,
    component: Nothing
  },
  {
    path: '/uploadFile',
    name: 'uploadFile',
    component: UploadFile
  },
  {
    path: '/chat',
    name: 'chat',
    props: true,
    component: Chat
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
