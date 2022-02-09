<template>
  <div id="carousel">
    <div id="carousel-frame">
      <transition-group id="carousel-rail" :name="page > beforePage ? 'slide':'slide-reverse'">
        <img class="carousel-items" v-for="(item, idx) in data" 
        :src="item.img" :alt="'item no.' + idx" :key="idx" v-show="page <= idx">
      </transition-group>
    </div>
    <button @click="move(true)" id="btn-left" />
    <button @click="move(false)" id="btn-right" />
  </div>
</template>

<script>
export default {
  data(){
    return {
      page: 0,
      beforePage: 0,
      nums: null
    }
  },
  props: {
    data: Array,
    w: Number,
    h: Number,
    n: Number
  },
  methods: {
    move(payload){
      this.beforePage = this.page
      if (payload){
        this.page += 1
      }
      else {
        this.page -= 1
      }
      if (this.page < 0){
        this.page = 0
      }
      else if (this.page > this.len){
        this.page = this.len
      }
    }
  },
  computed: {
    len(){
      return this.data.length
    },
  },
  mounted(){
    const carousel = document.getElementById('carousel')
    const item = document.getElementsByClassName('carousel-items')
    const btnLeft = document.getElementById('btn-left')
    const btnRight = document.getElementById('btn-right')
    let width = 800
    let height = 200
    let btnSize = 50
    let nums = 4
    if (this.w){
      width = this.w
      carousel.style.cssText += `width:${width};`
    }
    if (this.h){
      height = this.h
      carousel.style.cssText += `height:${height};`
      btnSize = Math.floor(height / 8)
    }
    if (this.n){
      nums = this.n
    }
    this.nums = nums
    for (let i=0;i<item.length;i++){
      item[i].style.width = `${width/nums}px`
    }
    const horizontal = (btnSize / 2) / width * 100
    const vertical = (btnSize / 2) / height * 100
    btnLeft.style.width = `${btnSize}px`; btnLeft.style.height = `${btnSize}px`
    btnLeft.style.cssText += `left:-${horizontal}%; top:${50-vertical}%;`
    btnRight.style.width = `${btnSize}px`; btnRight.style.height = `${btnSize}px`
    btnRight.style.cssText += `right:-${horizontal}%; top:${50-vertical}%;`
  }
}
</script>

<style scoped>
  button {
    background-size: cover;
    border: none;
    border-radius: 50%;
    opacity: 0.75;
    position: absolute;
  }

  #carousel {
    width: 800px;
    height: 200px;
    box-sizing: content-box;
    position: relative;
    /* overflow: hidden; */
    border: 2px #cccccc solid;
  }

  #carousel-frame {
    width: 100%;
    height: 100%;
    position: relative;
    overflow: hidden;
  }

  #carousel-rail {
    min-width: 100%;
    height: 100%;
    display: flex;
    justify-content: flex-start;
    align-items: stretch;
    flex-wrap: nowrap;
    position: absolute;
  }

  .carousel-items {
    border: 1px #cccccc solid;
  }

  @keyframes slide-in {
    from { right: -100% }
    to { right: 0 }
  }

  @keyframes slide-out {
    from { right: 0 }
    to { right: 100% }
  }

  .slide-enter-active {
    animation: slide-in 1s ease-in-out;
  }

  .slide-leave-active {
    animation: slide-out 1s ease-in-out;
  }

  .slide-reverse-enter-active {
    animation: slide-out 1s ease-in-out reverse;
  }

  .slide-reverse-leave-active {
    animation: slide-in 1s ease-in-out reverse;
  }

  #btn-left {
    background-image: url('../assets/images/icons/left.png');
  }

  #btn-right {
    background-image: url('../assets/images/icons/right.png');
  }
</style>