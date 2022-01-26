<template>
  <div id="setting">
    <navigation />
    <section id="setting_container">
      <router-view />
    </section>
  </div>
</template>

<script>
import Navigation from '@/components/Navigation'

export default {
  components: {
    Navigation,
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