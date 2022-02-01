<template>
  <div id="item_container">
      <span id="item"><p>{{ activityList }}</p></span>
      <span id="item"><p>활동2</p></span>
      <span id="item"><p>활동3</p></span>
  </div>
</template>

<script>
export default {
  name: 'ActivityPick',
  props: {
    activityList: Array,
  }
}
</script>

<style>
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
    background-color: #ECE9E9;
    border-radius: 20px;
  }
</style>