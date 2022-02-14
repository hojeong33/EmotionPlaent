import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/accounts/Login'
import MoreInfo from '@/views/accounts/MoreInfo'
import Signup from '@/views/accounts/Signup'
import KaKaoLogin from '@/views/accounts/KaKaoLogin'
import EmotionTest from '@/views/EmotionTest'

import Mypage from '@/views/user/Mypage.vue'
import Userpage from '@/views/user/Userpage.vue'
import List from '@/components/user/List'
import PickItem from '@/components/user/PickItem'

import Main from '@/views/main/Main.vue'
import Setting from '@/views/Setting'
import UserInfo from '@/components/Settings/UserInfo'
import PwChange from '@/components/Settings/PwChange'
import Withdrawal from '@/components/Settings/Withdrawal'
import FeedDetail from '@/components/FeedDetail'
import PwFind from '@/components/Settings/PwFind'
import EmailFind from '@/components/Settings/EmailFind' 
import ProfileUpdate from '@/components/Settings/ProfileUpdate'
import SearchResult from '@/components/Search/SearchResult/SearchResult'
import SearchList from '@/components/Search/SearchResult/SearchList'

import store from '../store/index.js'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      loginRequired: false,
      testRequired: false,
      showingNav: false,
    }
  },
  {
    path: '/moreInfo',
    name: 'MoreInfo',
    component: MoreInfo,
    meta: {
      loginRequired: true,
      testRequired: false,
      showingNav: false,
    }
  },
  {
    path: '/login/KaKaoLogin',
    name: 'KaKaoLogin',
    component: KaKaoLogin,
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
      testRequired: false,
      showingNav: false,
    }
  },
  {
    path: '/mypage',
    name: 'Mypage',
    redirect: '/mypage/feed',
    component: Mypage,
    meta: {
      loginRequired: true,
      testRequired: true, 
      showingNav: true,
    },
    children: [
      {
        path: ':tab',
        component: List,
        props: true,
        meta: {
          loginRequired: true,
          testRequired: true,
          showingNav: true,
        },
      },
      {
        path: 'item/:id/:tag/:index',
        component: PickItem,
        props: true,
        meta: {
          loginRequired: true,
          testRequired: true,
          showingNav: true,
        },
      }
    ]
  },
  {
    path: '/userpage',
    name: 'Userpage',
    redirect: '/userpage/feed',
    component: Userpage,
    meta: {
      loginRequired: true,
      testRequired:  true,
      showingNav: true,
    },
    children: [
      {
        path: ':tab',
        component: List,
        props: true,
        meta: {
          loginRequired: true,
          testRequired: true,
          showingNav: true,
        },
      },
      {
        path: 'item/:id/:tag/:index',
        component: PickItem,
        props: true,
        meta: {
          loginRequired: true,
          testRequired: true,
          showingNav: true,
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
      testRequired: true,
      showingNav: true,
    },
  },
  {
    path: '/emotiontest',
    name: 'EmotionTest',
    component: EmotionTest,
    meta: {
      loginRequired: true,
      testRequired: false,
      showingNav: false,
    },
  },
  {
    path: '/setting',
    name: 'Setting',
    redirect: '/setting/info',
    component: Setting,
    meta: {
      loginRequired: true,
      testRequired: true,
      showingNav: true,
    },
    children: [
      {
        path: 'info',
        component: UserInfo,
        meta: {
          loginRequired: true,
          testRequired: true,
          showingNav: true,
        },
      },
      {
        path: 'password',
        component: PwChange,
        meta: {
          loginRequired: true,
          testRequired: true,
          showingNav: true,
        },
      },
      {
        path: 'withdrawal',
        component: Withdrawal,
        meta: {
          loginRequired: true,
          testRequired: true,
          showingNav: true,
        },
      },
      {
        path: 'password-find',
        name: 'Password-find',
        component: PwFind,
        meta: {
          loginRequired: false,
          testRequired: false,
          showingNav: false,
        },
      },
      {
        path: 'email-find',
        name: 'EmailFind',
        component: EmailFind,
        meta: {
          loginRequired: false,
          testRequired: false,
          showingNav: false,
        },
      },
      {
        path:'profile-update',
        component: ProfileUpdate,
        meta: {
          loginRequired: true,
          testRequired: true,
          showingNav: true,
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
      testRequired: true,
      showingNav: true,
    },
  },
  {
    path: '/search',
    name: 'SearchResult',
    redirect: '/search/feed',
    component: SearchResult,
    meta: {
      loginRequired: true,
      testRequired: true, 
      showingNav: true,
    },
    children: [
      {
        path: ':tab',
        component: SearchList,
        props: true,
        meta: {
          loginRequired: true,
          testRequired: true,
          showingNav: true,
        },
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

let token = window.sessionStorage.getItem('at-jwt-access-token');
const jwt = require('jsonwebtoken');
const decodeAccessToken = jwt.decode(token)

//유저 정보 업데이트
const userUpdate = new Promise(() => {
  console.log('user data updated!')
  if(decodeAccessToken != null){
  store.commit('userUpdate', decodeAccessToken)
  }
})

router.beforeEach((to, from, next) => {
  console.log(to)
  // 라우터 이동 시 토큰이 필요함
  token = window.sessionStorage.getItem('at-jwt-access-token');
  console.log(token)
  //지정되지 않은 라우트로 이동할 경우 메인으로 redirect
  if (!to.matched.length){
    console.log('do not matched!!')
    next({ name:'Main' })
  }
  //네비게이션 랜더 유무
  store.commit('navActivate2', to.meta.showingNav)

  // 네비게이션 바 Active와 매칭
  if (to.name == 'Main'){store.commit('navActivate', 1)}
  else if (to.matched.langth && to.matched[0].path == '/mypage'){store.commit('navActivate', 2)}
  else if (to.matched.langth && to.matched[0].path == '/setting'){store.commit('navActivate', 4)}
  else {store.commit('navActivate', -1)}

  //로그인이 필요한 서비스의 경우 로그인 페이지로 redirect
  if (to.meta.loginRequired && !token){
    console.log('로그인')
    next({ name:'Login' })
  }
  //페이지 새로고침 등 발생했을 때 유저정보 store 갱신
  if (to.meta.loginRequired && token && !store.state.userInfo){
    userUpdate.then(() => next())
  }
  // 감정 테스트가 필요한 경우 테스트페이지로 redirect
  if (to.meta.testRequired && store.state.userInfo != null &&!store.state.userInfo.mood ){
    console.log(store.state.userInfo.mood)
    next({ name:'EmotionTest' })
  }
  //로그인 된 사용자가 로그인 or 회원가입 페이지로 가려고 할 경우
  if (!to.meta.loginRequired && store.state.userInfo){
    console.log('메인')
    next({ name:'Main' })
  }

  next()
})

// router.push의 중복 에러 해결방법
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => {
		if (err.name !== 'NavigationDuplicated') throw err;
	});
};

export default router
