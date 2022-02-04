import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/accounts/Login.vue'
import Signup from '@/views/accounts/Signup.vue'
import EmotionTest from '@/views/EmotionTest'
import Mypage from '@/views/user/Mypage.vue'
import Recommend from '@/components/MainPage/RecommendTab/Recommend.vue'
import Feed from '@/components/MainPage/FeedTab/Feed.vue'
import Main from '@/views/main/Main.vue'
import Setting from '@/views/setting'
import UserInfo from '@/components/Settings/UserInfo'
import PwChange from '@/components/Settings/PwChange'
import Withdrawal from '@/components/Settings/Withdrawal'
import PwFind from '@/components/Settings/PwFind' 

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
      {
        path: 'password-find',
        component: PwFind
      }
    ],
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  const body = document.querySelector('body')

  if (to.name == 'Signup' || to.name == 'Login' || to.name == 'EmotionTest'){
    body.setAttribute('class', 'astro')
    next()
  }
  else {
    body.removeAttribute('class')
    next()
  }
})

export default router
