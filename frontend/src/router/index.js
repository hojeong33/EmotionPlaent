import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/accounts/Login'
import Signup from '@/views/accounts/Signup'
import EmotionTest from '@/views/EmotionTest'
import Mypage from '@/views/user/Mypage.vue'
import Recommend from '@/components/MainPage/RecommendTab/Recommend'
import Feed from '@/components/MainPage/FeedTab/Feed'
import Main from '@/views/main/Main'
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
  // {
  //   path: '/searchresult',
  //   name: 'SearchResult',
  //   component: SearchResult
  // },
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
  // {
  //   path: '/create',
  //   name: 'Create',
  //   component: Create,
  //   children: [
  //     {
  //       path: 'Img',
  //       component: CreateImg
  //     },
  //     {
  //       path: 'Tags',
  //       component: CreateTag,
  //     },
  //     {
  //       path: 'Text',
  //       component: CreateText,
  //     }
  //   ],  
  // },

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
// router.push의 중복 에러 해결방법
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => {
		if (err.name !== 'NavigationDuplicated') throw err;
	});
};

export default router
