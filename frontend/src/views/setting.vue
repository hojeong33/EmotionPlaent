<template>
  <div id="setting">
    <navigation />
    <section id="setting_container">
      <user-setting @password-change="passwordChange" />
    </section>
  </div>
</template>

<script>
import Navigation from '@/components/Navigation'
import UserSetting from '@/components/Settings/UserSetting'

export default {
  components: {
    Navigation,
    UserSetting
  },
  methods: {
    passwordChange: function(){
      console.log('hi')
    }
  }
}
</script>

<style scoped>
  #setting {
    width: 100vw;
  }

  #setting_container {
    display: flex;
    flex-direction: column;
    width: 50%;
    min-width: 700px;
    min-height: 100vh;
    border-left: 2px #cccccc solid;
    border-right: 2px #cccccc solid;
    padding: 1.5rem 0;
    margin: 0 auto;
  }
</style>