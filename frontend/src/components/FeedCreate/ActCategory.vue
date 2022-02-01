<template>
  <div>
    <p id="category" @click="check"> 
      <!-- :class="classCheck ? 'checked' : 'unchecked'"> -->
      {{ category }}
    </p>

  </div>
</template>

<script>
export default {
  props: { 
    category: String,
    isChecked: Array,
    index: Number,
  },
  data: function(){
    return {
      classCheck: false
    }
  },
  methods: {
    check: function(){
      let true_index = this.isChecked.indexOf(true)
      if (true_index === -1) {
        this.isChecked[this.index] = !this.isChecked[this.index]
      } else {
        this.isChecked[true_index] = false
        this.isChecked[this.index] = !this.isChecked[this.index]
      }
      // console.log(this.isChecked)
      for (let checking of this.isChecked) {
        if (checking === true) {
          document.getElementById('category').className = 'checked'
          // console.log(document.getElementById('category').className)
        } else {
          document.getElementById('category').className = 'unchecked'
          // console.log(document.getElementById('category').className)
        }
        console.log(document.getElementById('category'))
      }
      // console.log(this.classCheck)
    }
  }
}
</script>

<style scoped>
.unchecked{
  margin-top: 7px;
  margin-bottom: 7px;
  color: black;
  font-weight: bold;
}
.checked{
  margin-top: 7px;
  margin-bottom: 7px;
  color: white;
  font-weight: bold;
  background-color: #5E39B3;
}
</style>
<!-- 포기인가..... -->