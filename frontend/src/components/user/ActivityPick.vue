<template>
  <div id="item_container" 
    v-if="activityExist"
    :activityExist="activityExist">
    <span id="item" 
      v-for="activity in activityList"
      :activity="activity"
      :key="activityList.indexOf(activity)">
      <article>
        <img :src="activity.postImgLink" alt="" id="poster_img">
      </article>
      <article id="info">
        <p>{{ activity.name }}</p>
      </article>
    </span>
  </div>
  <div v-else id="no_result">
		<img id="nothing" src="@/assets/images/etc/alien.png" alt="">
		<p>찜한 활동이 없어요...</p>
	</div>
</template>

<script>
export default {
  name: 'ActivityPick',
  props: {
    activityList: Array,
    activityExist: Boolean,
  },
}
</script>

<style scoped>
  #item_container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
  }

  #item {
    display: flex;
    align-items: center;
    width: 75vh;
    height: 7vh;
    margin: 1.15rem auto;
    background-color: #ECE9E9;
    border-radius: 20px;
  }

  #poster_img {
    width: 7vh;
    height: 7vh;
    border-radius: 20px 0 0 20px;
  }

  p {
    margin-left: 0.5rem;
  }

  #info {
    display: flex;
    font-size: 1.2rem;
    font-weight: bold;
    margin-top: 1rem;
    margin-left: 1.5rem;
  }

  #no_result {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 5vh;
  }

  #nothing {
    width: 10vh;
    height: 10vh;
    margin-bottom: 2vh;
  }
</style>