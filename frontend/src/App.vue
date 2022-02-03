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
import { mapState } from 'vuex'

export default {
  components: {
    Create,
    Navigation
  },
  computed:
    mapState(['feedActive'])
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