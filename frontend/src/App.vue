<template>
  <div id="app">
    <navigation />
    <create v-if="feedActive" />
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
  </div>
</template>

<script>
import Create from '@/views/Create'
import Navigation from '@/components/Navigation'
import LogoutModal from '@/components/Modal/LogoutModal.vue'
import UserFeedSetting from '@/components/Modal/UserFeedSetting.vue'
import CommentSetting from '@/components/Modal/CommentSetting.vue'
import ProfileImgChange from '@/components/Modal/ProfileImgChange.vue'
// 모달창
import LoginConfirm from '@/components/Modal/LoginConfirm.vue'
import SignupConfirm from '@/components/Modal/SignupConfirm.vue'
import EmotionTestTooMuchPick from '@/components/Modal/EmotionTestTooMuchPick.vue'
import FirstEmotionTestConfirm from '@/components/Modal/FirstEmotionTestConfirm.vue'
import EmotionTestError from '@/components/Modal/EmotionTestError.vue'
import EmotionTestPickMore from '@/components/Modal/EmotionTestPickMore.vue'
import EmotionTestResult from '@/components/Modal/EmotionTestResult.vue'
import ReturnToLogin from '@/components/Modal/ReturnToLogin.vue'
import LoginFail from '@/components/Modal/LoginFail.vue'
import SignupFail from '@/components/Modal/SignupFail.vue'
import SignupFailPasswordError from '@/components/Modal/SignupFailPasswordError.vue'

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

  },
  computed:
    mapState([
      'feedActive', 'modalActive', 'profileImgChangeModalActive', 'commentSettingModalActive', 'logoutModalActive', 'userFeedSettingModalActive', 'loginConfirmModalActive', 
      'signupConfirmModalActive', 'emotionTestTooMuchPickModalActive', 'firstEmotionTestConfirmModalActive', 'emotionTestErrorActive', 'emotionTestPickMoreModalActive', 
      'emotionTestResultModalActive', 'loginFailModalActive', 'signupFailModalActive1', 'signupFailModalActive2',
    ]),
    feedActive(){
      return this.$store.state.navActive[0]
    },
}

</script>


<style scoped>
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    position: relative;
  }
</style>