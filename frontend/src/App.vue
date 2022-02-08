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
  </div>
</template>

<script>
import Create from '@/views/Create'
import Navigation from '@/components/Navigation'
import LogoutModal from '@/components/Modal/LogoutModal.vue'
import UserFeedSetting from '@/components/Modal/UserFeedSetting.vue'
import CommentSetting from '@/components/Modal/CommentSetting.vue'
import ProfileImgChange from '@/components/Modal/ProfileImgChange.vue'
import LoginConfirm from '@/components/Modal/LoginConfirm.vue'
import SignupConfirm from '@/components/Modal/SignupConfirm.vue'
import { mapState } from 'vuex'


export default {
  components: {
    Create,
    Navigation,
    LogoutModal,
    UserFeedSetting,
    CommentSetting,
    ProfileImgChange,
    LoginConfirm,
    SignupConfirm,
  },
  computed:
    mapState([
      'feedActive', 'modalActive', 'profileImgChangeModalActive', 'commentSettingModalActive', 'logoutModalActive', 'userFeedSettingModalActive', 'loginConfirmModalActive', 
      'signupConfirmModalActive',
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