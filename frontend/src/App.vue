<template>
  <div id="app">
    <navigation v-if="showingNav" />
    <create v-if="navActive[0]" />
    <router-view/>
    <logout-modal v-if="logoutModalActive" />
    <user-feed-setting v-if="userFeedSettingModalActive" />
    <comment-setting v-if="commentSettingModalActive" />
    <profile-img-change v-if="profileImgChangeModalActive" />
    <login-confirm v-if="loginConfirmModalActive" />
    <signup-confirm v-if="signupConfirmModalActive" />
    <emotion-test-too-much-pick v-if="emotionTestTooMuchPickModalActive" />
    <first-emotion-test-confirm v-if="firstEmotionTestConfirmModalActive" />
    <emotion-test-error v-if="emotionTestErrorActive" />
    <emotion-test-pick-more v-if="emotionTestPickMoreModalActive" />
    <emotion-test-result v-if="emotionTestResultModalActive" />
    <return-to-login v-if="ReturnToLoginModalActive" />
    <login-fail v-if="loginFailModalActive" />
    <signup-fail v-if="signupFailModalActive1" />
    <signup-fail-password-error v-if="signupFailModalActive2" />
    <comment-need-content v-if="commentNeedContentModalActive" />
    <more-info-confirm v-if="moreInfoConfirmModalActive" />
    <more-info-error v-if="signupFailModalActive1" />
  </div>
</template>

<script>
import Create from '@/views/Create'
import Navigation from '@/components/Navigation'
// 모달창
//// 세팅
import UserFeedSetting from '@/components/Modal/SettingModal/UserFeedSetting.vue'
import CommentSetting from '@/components/Modal/SettingModal/CommentSetting.vue'
//// 로그인과 로그아웃
import LoginConfirm from '@/components/Modal/LoginLogoutModal/LoginConfirm.vue'
import LogoutModal from '@/components/Modal/LoginLogoutModal/LogoutModal.vue'
import LoginFail from '@/components/Modal/LoginLogoutModal/LoginFail.vue'
import ReturnToLogin from '@/components/Modal/LoginLogoutModal/ReturnToLogin.vue'
//// 회원가입
import SignupFail from '@/components/Modal/SignupModal/SignupFail.vue'
import SignupFailPasswordError from '@/components/Modal/SignupModal/SignupFailPasswordError.vue'
import SignupConfirm from '@/components/Modal/SignupModal/SignupConfirm.vue'
import MoreInfoConfirm from '@/components/Modal/SignupModal/MoreInfoConfirm.vue'
import MoreInfoError from '@/components/Modal/SignupModal/MoreInfoError.vue'
//// 감정테스트
import EmotionTestTooMuchPick from '@/components/Modal/EmotionTestModal/EmotionTestTooMuchPick.vue'
import FirstEmotionTestConfirm from '@/components/Modal/EmotionTestModal/FirstEmotionTestConfirm.vue'
import EmotionTestError from '@/components/Modal/EmotionTestModal/EmotionTestError.vue'
import EmotionTestPickMore from '@/components/Modal/EmotionTestModal/EmotionTestPickMore.vue'
import EmotionTestResult from '@/components/Modal/EmotionTestModal/EmotionTestResult.vue'
// 기타
import ProfileImgChange from '@/components/Modal/ProfileImgChange.vue'
import CommentNeedContent from '@/components/Modal/CommentNeedContent.vue'

import { mapState } from 'vuex'



export default {
  components: {
    Create,
    Navigation,
    LogoutModal,
    UserFeedSetting,
    CommentSetting,
    ProfileImgChange,
    //모달창
    LoginConfirm,
    SignupConfirm,
    EmotionTestTooMuchPick,
    FirstEmotionTestConfirm,
    EmotionTestError,
    EmotionTestPickMore,
    EmotionTestResult,
    ReturnToLogin,
    LoginFail,
    SignupFail,
    SignupFailPasswordError,
    CommentNeedContent,
    MoreInfoConfirm,
    MoreInfoError,

  },
  computed:
    mapState([
      'navActive', 'showingNav', 'modalActive', 'profileImgChangeModalActive', 'commentSettingModalActive', 'logoutModalActive', 'userFeedSettingModalActive', 'loginConfirmModalActive', 
      'signupConfirmModalActive', 'emotionTestTooMuchPickModalActive', 'firstEmotionTestConfirmModalActive', 'emotionTestErrorActive', 'emotionTestPickMoreModalActive', 
      'emotionTestResultModalActive', 'loginFailModalActive', 'signupFailModalActive1', 'signupFailModalActive2', 'ReturnToLoginModalActive', 'commentNeedContentModalActive',
      'moreInfoConfirmModalActive', 
    ]),
}

</script>


<style scoped>
#app {
font-family: '비트로 프라이드 TTF', sans-serif !important;
}
@font-face {
  font-family:'비트로 프라이드 TTF';
  src: url('assets/font/비트로 프라이드 TTF.ttf') format('truetype');
}
</style>