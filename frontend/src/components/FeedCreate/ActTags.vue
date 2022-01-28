<template>
  <div>
    <button v-for="(act,idx) in selectedAct" :key="idx" id="act">
      {{ act }}
    </button>
  </div>    
</template>

<script>
export default {
  props: { 
    selectedAct: Array
  }
}
</script>

<style scoped>
#act{
  align-self: center;
  padding: 0.25rem 1rem;
  margin: 0.75rem;
}
</style>