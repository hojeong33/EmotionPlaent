<template>
  <div id="app">
    <navigation />
    <create v-if="feedActive" />
    <router-view/>
  </div>
</template>

<script>
  import Create from '@/views/Create'
  import Navigation from '@/components/Navigation'

  // const jwt = require('jsonwebtoken');
  // const token = window.sessionStorage.getItem('at-jwt-access-token');

  export default {
    components: {
      Create,
      Navigation,
    },
    computed:{
      feedActive(){
        return this.$store.state.navActive[0]
      }
    },
    // beforeCreate(){
    //   window.addEventListener('load', () => {
    //     console.log('loaded!')
    //     if (token){
    //       const decodeAccessToken = jwt.decode(token)
    //       this.$store.commit('userUpdate', decodeAccessToken.userInfo)
    //     }
    //   })
    // }
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