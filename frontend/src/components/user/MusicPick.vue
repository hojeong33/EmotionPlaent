<template>
  <div id="item_container">
    <span id="item" 
      v-for="music in musicList"
      :music="music"
      :key="musicList.indexOf(music)">{{ music }}</span>
  </div>
</template>

<script>
export default {
  name: 'MusicPick',
  props: {
    musicList: Array,
  },
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