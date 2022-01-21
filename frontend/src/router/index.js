import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/accounts/Login.vue'
import Signup from '@/views/accounts/Signup.vue'
import Test1st from '@/views/test/Test1st.vue'
import Mypage from '@/views/accounts/Mypage.vue'
import Recommend from '@/views/main/Recommend.vue'
import Feed from '@/views/main/Feed.vue'
import Main from '@/views/main/Main.vue'

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
    path: '/test1st',
    name: 'Test1st',
    component: Test1st
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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
