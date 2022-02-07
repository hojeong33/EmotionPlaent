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

export default {
  components: {
    Create,
    Navigation
  },
  computed:{
    feedActive(){
      return this.$store.state.navActive[0]
    }
  }
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