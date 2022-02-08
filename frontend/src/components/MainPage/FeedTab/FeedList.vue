<template>
  <div id="feed-list">
    <feed v-for="post in posts"
      :post="post"
      :key="posts.indexOf(post)">
      </feed>
  </div>
</template>

<script>
import Feed from "./Feed.vue";
export default {
  name: "FeedList",
  props: {
    posts: Array,
  },
  components: {Feed,
  }
};
</script>

<style scoped>
  #feed-list {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

</style>