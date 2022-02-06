<template>
  <div id="app">
    <navigation />
    <create v-if="feedActive" />
    <router-view/>
    <search v-if="searchActive" />
  </div>
</template>

<script>
import Create from '@/views/Create'
import Navigation from '@/components/Navigation'
import Search from '@/components/Search/Search'
import { mapState } from 'vuex'

export default {
  components: {
    Create,
    Navigation,
    Search,
  },
  computed:
    mapState(['feedActive', 'searchActive'])
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