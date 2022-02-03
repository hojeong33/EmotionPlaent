<template>
  <div id="app">
    <router-view/>
    <create v-if="feedActive" />
  </div>
</template>

<script>
import Create from '@/views/Create'
import { mapState } from 'vuex'

export default {
  components: {
    Create
  },
  computed: mapState(['feedActive'])
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