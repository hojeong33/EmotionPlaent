<template>
  <div id="pick_container">
    <div id="sort_bar">
      <p style="font-size:1.8rem; font-weight:bold">우울행성</p>
      <img id="rocket" src="@/assets/images/icons/rocket.png">
    </div>
    <div id="list_tab">
      <p>음악</p>
      <p>영화</p>
      <p>활동</p>
    </div>
    <div id="item_container">
      <span id="item"></span>
      <span id="item"></span>
      <span id="item"></span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PickList',
}
</script>

<style scoped>
  #pick_container {
    width: 100%;
    min-width: 700px;
    min-height: 100vh;
    padding-top: 1rem;
    margin: 2rem auto;
    display: flex;
    flex-direction: column;
    border: 0.1rem solid gainsboro;
  }

  #sort_bar {
    display: flex;
    align-self: flex-end;
    margin-top: 2rem;
    margin-right: 1.9rem;
  }

  #rocket {
    width: 3vh;
    height: 3vh;
    min-width: 10px;
    margin-top: 0.35rem;
    margin-left: 0.5rem;
  }

  #list_tab {
    display: flex;
    justify-content: center;
    width: 40%;
    margin-left: 13.3rem;
    margin-top: 2rem;
    margin-bottom: 2rem;
    justify-content: space-evenly;
  }

  #item_container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
  }

  #item {
    width: 40rem;
    height: 3.5rem;
    margin: 1.15rem auto;
    background-color: antiquewhite;
    border-radius: 20px;
  }

  p {
    color: black;
    font-size: 1.4rem;
    font-weight: bold;
    letter-spacing: 0.05rem;
  }
</style>