import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/accounts/Login'
import Signup from '@/views/accounts/Signup'
import EmotionTest from '@/views/EmotionTest'

import Mypage from '@/views/user/Mypage.vue'
import List from '@/components/user/List'
import PickItem from '@/components/user/PickItem'

import Main from '@/views/main/Main.vue'
import Setting from '@/views/Setting'
import UserInfo from '@/components/Settings/UserInfo'
import PwChange from '@/components/Settings/PwChange'
import Withdrawal from '@/components/Settings/Withdrawal'
import FeedDetail from '@/components/FeedDetail'
import PwFind from '@/components/Settings/PwFind'
import ProfileUpdate from '@/components/Settings/ProfileUpdate'
import SearchResult from '@/components/Search/SearchResult'

import store from '../store/index.js'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      loginRequired: false,
      testRequired: false
    }
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup,
    meta: {
      loginRequired: false,
      testRequired: false
    }
  },
  {
    path: '/mypage',
    name: 'Mypage',
    redirect: '/mypage/feed',
    component: Mypage,
    meta: {
      loginRequired: true,
      testRequired: true
    },
    children: [
      {
        path: ':tap',
        component: List,
        props: true,
        meta: {
          loginRequired: true,
          testRequired: true
        },
      },
      {
        path: 'item/:id/:tag/:index',
        component: PickItem,
        props: true,
        meta: {
          loginRequired: true,
          testRequired: true
        },
      }
    ]
  },
  {
    path:'/main',
    name:'Main',
    component:Main,
    meta: {
      loginRequired: true,
      testRequired: true
    },
  },
  {
    path: '/emotiontest',
    name: 'EmotionTest',
    component: EmotionTest,
    meta: {
      loginRequired: true,
      testRequired: false
    },
  },
  {
    path: '/setting',
    name: 'Setting',
    redirect: '/setting/info',
    component: Setting,
    meta: {
      loginRequired: true,
      testRequired: true
    },
    children: [
      {
        path: 'info',
        component: UserInfo,
        meta: {
          loginRequired: true,
          testRequired: true
        },
      },
      {
        path: 'password',
        component: PwChange,
        meta: {
          loginRequired: true,
          testRequired: true
        },
      },
      {
        path: 'withdrawal',
        component: Withdrawal,
        meta: {
          loginRequired: true,
          testRequired: true
        },
      },
      {
        path: 'password-find',
        component: PwFind,
        meta: {
          loginRequired: true,
          testRequired: true
        },
      },
      {
        path:'profile-update',
        component: ProfileUpdate,
        meta: {
          loginRequired: true,
          testRequired: true
        },
      }
    ],  
  },
  {
    path: '/feed/detail',
    name: 'FeedDetail',
    component: FeedDetail,
    meta: {
      loginRequired: true,
      testRequired: true
    },
  },
  {
    path: '/search/',
    name: 'SearchResult',
    component: SearchResult,
    meta: {
      loginRequired: true,
      testRequired: true
    },
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

const token = window.sessionStorage.getItem('at-jwt-access-token');
const jwt = require('jsonwebtoken');
const decodeAccessToken = jwt.decode(token)

//유저 정보 업데이트
const userUpdate = new Promise(() => {
  console.log('user data updated!')
  store.commit('userUpdate', decodeAccessToken.userInfo)
})

router.beforeEach((to, from, next) => {
  console.log(to)
  //지정되지 않은 라우트로 이동할 경우 메인으로 redirect
  if (!to.matched.length){
    console.log('do not matched!!')
    next({ name:'Main' })
  }

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
  else if (to.matched[0].path == '/mypage'){store.commit('navActivate', 2)}
  else if (to.matched[0].path == '/setting'){store.commit('navActivate', 4)}
  else {store.commit('navActivate', -1)}

  //로그인이 필요한 서비스의 경우 로그인 페이지로 redirect
  if (to.meta.loginRequired && !token){
    next({ name:'Login' })
  }
  //페이지 새로고침 등 발생했을 때 유저정보 store 갱신
  if (to.meta.loginRequired && token && !store.state.userInfo){
    userUpdate.then(() => next())
  }
  //감정 테스트가 필요한 경우 테스트페이지로 redirect
  if (to.meta.testRequired && !store.state.userInfo.mood){
    console.log(store.state.userInfo.mood)
    next({ name:'EmotionTest' })
  }
  //로그인 된 사용자가 로그인 or 회원가입 페이지로 가려고 할 경우
  if (!to.meta.loginRequired && store.state.userInfo){
    next({ name:'Main' })
  }
})
// router.push의 중복 에러 해결방법
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => {
		if (err.name !== 'NavigationDuplicated') throw err;
	});
};

export default router
