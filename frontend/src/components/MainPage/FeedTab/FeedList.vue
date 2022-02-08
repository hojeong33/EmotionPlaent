<template>
  <div id="feed-list">
    <div>
      <feed v-for="post in posts"
        :post="post"
        :key="posts.indexOf(post)">
        </feed>
    </div>
  </div>
</template>

<script>
import Feed from "./Feed.vue";
export default {
  name: "FeedList",
  props: {
    posts: Array,
  },
  components: {Feed
  }
};
</script>

<style scoped>


</style>