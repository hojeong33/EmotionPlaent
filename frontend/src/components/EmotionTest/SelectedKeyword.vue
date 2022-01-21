<template>
  <div id="selected_keyword">
    <span>
      {{ keyword }}
    </span>
    <span id="delete_btn" @click="deleteKeyword">
      x
    </span>
  </div>
</template>

<script>
export default {
  props: {
    keyword: String
  },
  methods: {
    deleteKeyword: function(){
      this.$emit('delete_keyword', this.keyword)
    }
  }
}
</script>

<style scoped>
  span {
    margin: 0 0.25rem;
  }

  #selected_keyword {
    background-color: #5E39B3;
    color: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border: none;
    border-radius: 20px;
    font-weight: bold;
    padding: 0.25rem;
    margin-left: 0.25rem;
  }

  #delete_btn {
    cursor: pointer;
  }
</style>