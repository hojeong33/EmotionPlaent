<template>
  <div class="feed-s">
    <img class="feed-s-thumbnail" :src="post.postImage" :alt="post.username">
    <!-- <span class="feed-s-info">
      <p>{{ post.likes }}</p>
      <p>{{ post.comment_cnt }}</p>
    </span> -->
  </div>
</template>

<script>
export default {
  props: {
    post: Object
  }
}
</script>

<style scoped>
  .feed-s {
    position: relative;
    cursor: pointer;
  }

  .feed-s-thumbnail {
    width: 100%;
    aspect-ratio: 1/1;
    border-radius: 20px;
  }

  .feed-s-info {
    background-color: rgb(0, 0, 0, 0.5);
    position: absolute;
    top: 0;
    left: 0;
    z-index: 1;
  }
</style>