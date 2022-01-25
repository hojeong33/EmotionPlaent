import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/accounts/Login.vue'
import Signup from '@/views/accounts/Signup.vue'
import EmotionTest from '@/views/EmotionTest'
import Mypage from '@/views/accounts/Mypage.vue'
import Recommend from '@/views/main/Recommend.vue'
import Feed from '@/views/main/Feed.vue'
import Main from '@/views/main/Main.vue'
import CreateImg from '@/views/create/CreateImg'
import CreateTag from '@/views/create/CreateTag'
import CreateText from '@/views/create/CreateText'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: Mypage
  },
  {
    path:'/Recommend',
    name:'Recommend',
    component:Recommend
  },
  {
    path:'/Feed',
    name:'Feed',
    component:Feed
  },
  {
    path:'/Main',
    name:'Main',
    component:Main
  },
  {
    path: '/emotiontest',
    name: 'EmotionTest',
    component: EmotionTest
  },
  {
    path: '/create',
    name: 'CreateImg',
    component: CreateImg
  },
  {
    path: '/create/tag',
    name: 'CreateTag',
    component: CreateTag
  },
  {
    path: '/create/text',
    name: 'CreateText',
    component: CreateText
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
