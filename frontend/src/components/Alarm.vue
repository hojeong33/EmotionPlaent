<template>
  <div id="alarm_body">
    <section id="alarm_header">
      <div id="title">
        <p>알림</p>
      </div>
    </section>
    <section id="search_content">
    </section>
    <section id="search_footer">
      <button @click="cancel">X</button>
    </section>
  </div>
</template>

<script>

export default {
  name: 'Search',
  
  data() {
    return {
      
    }
  },
  methods: {
    cancel() {
      this.$emit('cancel')
    },
  }
}
</script>

<style scoped>
  #alarm_body {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: white;
  }

  #alarm_header {
    z-index: 10;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: white;
    width: 100%;
    min-width: 300px;
    height: 10%;
    min-height: 55px;
    border-top: 0.2rem solid gainsboro;
    border-left: 0.2rem solid gainsboro;
    border-right: 0.2rem solid gainsboro;
  }
  
  #search_content {
    overflow: auto;
    display: flex;
    background-color: white !important;
    width: 100%;
    min-width: 300px;
    height: 80%;
    min-height: 300px;
    border: 0.2rem solid gainsboro;
  }
  
  #search_footer {
    display: flex;
    justify-content: center;
    background-color: white;
    width: 100%;
    min-width: 300px;
    height: 10%;
    min-height: 60px;
    border-bottom: 0.2rem solid gainsboro;
    border-left: 0.2rem solid gainsboro;
    border-right: 0.2rem solid gainsboro;
  }

  h3 {
    font-size: 1.25rem;
    font-weight: bold;
    margin: 0;
  }

  p {
    font-size: 1.5rem;
    font-weight: bold;
    margin: 0;
  }

  .inactive {
    color: #777777;
    font-size: 1.2rem;
    font-weight: bold;
  }

  .active {
    border-bottom: 0.3rem solid #5E39B3;
  }

  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1.125rem;
    font-weight: bold;
    border: none;
    border-radius: 20px;
    padding: 0 2rem;
    margin: 0.4rem;
    cursor: pointer;
    line-height: 2rem;
  }

  #title {
    width: 100%;
    display: flex;
    justify-content: center;
  }

</style>