import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/accounts/Login'
import MoreInfo from '@/views/accounts/MoreInfo'
import Signup from '@/views/accounts/Signup'
import EmotionTest from '@/views/EmotionTest'

import Mypage from '@/views/user/Mypage.vue'
import List from '@/components/user/List'
import PickItem from '@/components/user/PickItem'

import Recommend from '@/components/MainPage/RecommendTab/Recommend.vue'
import Feed from '@/components/MainPage/FeedTab/Feed.vue'
import Main from '@/views/main/Main.vue'
import Setting from '@/views/Setting'
import UserInfo from '@/components/Settings/UserInfo'
import PwChange from '@/components/Settings/PwChange'
import Withdrawal from '@/components/Settings/Withdrawal'
import FeedDetail from '@/components/FeedDetail'
import PwFind from '@/components/Settings/PwFind'
import ProfileUpdate from '@/components/Settings/ProfileUpdate'
// import Create from '@/views/Create'
// import CreateImg from '@/components/Create/CreateImg'
// import CreateTag from '@/components/Create/CreateTag'
// import CreateText from '@/components/Create/CreateText'
import store from '../store/index.js'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
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
    path: '/moreinfo',
    name: 'MoreInfo',
    component: MoreInfo
  },
  {
    path: '/mypage',
    name: 'Mypage',
    redirect: '/mypage/feed',
    component: Mypage,
    children: [
      {
        path: ':tap',
        default: '',
        component: List,
        props: true
      },
      {
        path: 'item/:id/:tag/:index',
        component: PickItem,
        props: true
      }
    ]
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
    redirect: '/setting/info',
    component: Setting,
    children: [
      {
        path: 'info',
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
      },
      {
        path:'profile-update',
        component: ProfileUpdate,
      }
    ],  
  },
  {
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
  // 네비게이션 바 Active와 매칭
  if (to.name == 'Main'){store.commit('navActivate', 1)}
  else if (to.name == 'Mypage'){store.commit('navActivate', 2)}
  else if (to.matched[0].path == '/setting'){store.commit('navActivate', 4)}
  else {store.commit('navActivate', -1)}

  // if (to.name == 'Login' || to.name =='Signup')
})
// router.push의 중복 에러 해결방법
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => {
		if (err.name !== 'NavigationDuplicated') throw err;
	});
};

export default router
