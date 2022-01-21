<template>
  <div>
    <div>
        <div class="container justify-content-center"> 
            <img class="planet" src="../../assets/images/depressed.png" alt="">
            <h1>어서오세요</h1>
            <h1>우울행성입니다.</h1>
        </div>
    </div>
    <div>탭</div>
    <refresh></refresh>
  </div>
</template>

<script>
import Refresh from './Refresh.vue'
export default {
  components: { Refresh },

}
</script>

<style scoped>
.planet{
    width: 50px;
    height:50px;
}
    
</style>