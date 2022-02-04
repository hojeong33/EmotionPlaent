import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/accounts/Login.vue'
import Signup from '@/views/accounts/Signup.vue'
import EmotionTest from '@/views/EmotionTest'
import Mypage from '@/views/user/Mypage.vue'
import Recommend from '@/components/MainPage/RecommendTab/Recommend.vue'
import Feed from '@/components/MainPage/FeedTab/Feed.vue'
import Main from '@/views/main/Main.vue'
import Setting from '@/views/Setting.vue'
import UserInfo from '@/components/Settings/UserInfo'
import PwChange from '@/components/Settings/PwChange'
import Withdrawal from '@/components/Settings/Withdrawal'
import FeedDetail from '@/components/FeedDetail'

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
    path:'/recommend',
    name:'Recommend',
    component:Recommend
  },
  {
    path:'/feed',
    name:'Feed',
    component:Feed
  },
  {
    path:'/main',
    name:'Main',
    component:Main
  },
  {
    path: '/emotiontest',
    name: 'EmotionTest',
    component: EmotionTest
  },
  {
    path: '/setting',
    name: 'Setting',
    component: Setting,
    children: [
      {
        path: '',
        component: UserInfo
      },
      {
        path: 'password',
        component: PwChange
      },
      {
        path: 'withdrawal',
        component: Withdrawal
      },
    ],
  },
  {
    // 경로도 이야기해야할듯
    path: '/feed/detail',
    name: 'FeedDetail',
    component: FeedDetail
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
