<template>
  <div @click="$emit('input', id)" :class="[active, 'tab']">
    {{ label }}
  </div>
</template>

<script>
export default {
  props: {
    id: Number,
    label: String,
    value: Number
  },
  computed: {
    active() {
      return this.value === this.id ? 'active' : false
    }
  }
}
</script>

<style scoped>
 div{
   margin: 1rem;
  font-size: 2rem; 
 }
.tab {
  border-radius: 2px 2px 0 0;
  background: #fff;
  color: #311d0a;
  line-height: 24px;
}
.tab:hover {
  background: #eeeeee;
}
.active {
  background: #dbc9f7;
}
</style>