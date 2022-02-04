<template>
  <article class="feed">
    <img class="feed-img" :src="feed.postImage" alt="feed image">
    <img class="feed-planet" :src="require('@/assets/images/emotions/'+`${feed.planet}` + '.png')">
  </article>
</template>

<script>
export default {
  props: {
    feed: Object
  }
}
</script>

<style>
  .feed {
		position: relative;
    aspect-ratio: 1 / 1;
	}

	.feed-img {
		border-radius: 10px;
	}

	.feed-planet {
		position: absolute;
		width: 25%;
		height: 25%;
		bottom: 5%;
    left: 5%;
		border-radius: 50%;
		border: 3px solid;
		border-color: white;
	}
</style>