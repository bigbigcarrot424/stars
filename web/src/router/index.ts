import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import AdminSquare from '../views/admin/AdminSquare.vue'
import AdminPublish from '../views/admin/AdminPublish.vue'
import AdminInfo from '../views/admin/AdminInfo.vue'
import AppIntro from '../views/AppIntro.vue'
import UploadFile from '../views/UploadFile.vue'
import OthersInfo from '../views/admin/OthersInfo.vue'

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
    path: '/publish',
    name: 'publish',
    component: AdminPublish
  },
  {
    path: '/adminInfo',
    name: 'adminInfo',
    component: AdminInfo
  },
  {
    path: '/othersInfo',
    name: 'othersInfo',
    component: OthersInfo
  },
  {
    path: '/uploadFile',
    name: 'uploadFile',
    component: UploadFile
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
