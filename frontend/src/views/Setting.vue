<template>
  <section id="setting_container">
    <router-view/>
  </section>
</template>

<script>

export default {
//   components: {
//     Navigation,
//   }
}
</script>

<style scoped>
  #setting {
    width: 100vw;
  }

  #setting_container {
    display: flex;
    flex-direction: column;
    width: 30%;
    min-width: 700px;
    min-height: 92.5vh;
    /* border-left: 2px #cccccc solid;
    border-right: 2px #cccccc solid; */
    border-left: 2px  #5E39B3 solid;
    border-right: 2px  #5E39B3 solid;
    padding: 1.5rem 0;
    margin: 0 auto;
    background-color: white;
  }
</style>
