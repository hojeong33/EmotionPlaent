<template>
  <div id="feed-list">
    <div>
      <search-feed v-for="post in posts"
        :post="post"
        :key="posts.indexOf(post)">
        </search-feed>
    </div>
  </div>
</template>

<script>
import SearchFeed from "./SearchFeed.vue";
import posts from '@/assets/data/posts.js'

export default {
  name: "SearchFeedList",
  data() {
    return {
      posts,
    }
  },
  components: {SearchFeed,
  }
};
</script>

<style scoped>


</style>