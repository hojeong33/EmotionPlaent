<template>
  <div id="feed">
    <div id="header">
      <section id="profile_image">
        <img :src="feed.userImage" />
      </section>
      <div id="profile_content">
        <section id="username">{{feed.username}}</section>
        <section>{{feed.date}}</section>
      </div>
      <!-- <div id="setting">
        <i class="fas fa-ellipsis-v"></i>
      </div> -->
      <div id="setting">
        <i @click="onUserFeedSetting" class="fas fa-ellipsis-v"></i>
      </div>
    </div>
    <div id="post_image">
      <img :src="feed.postImage" alt="">
      <p class="overlay_content" >{{feed.username}}님은 {{feed.planet}} <img id="planet_img" :src="require('@/assets/images/emotions/' + tmp.img)" style="width:1.2rem;height:1.2rem; margin-bottom:3px">에 있어요</p>
    </div>
    <div id="like">
      <div id="heart">
        <i class="far fa-heart fa-lg" :class="{'fas': this.feed.hasBeenLiked}" @click="like"></i>
      </div>
      <p class="likes" >{{feed.likes}} likes</p>
    </div>
    <div id="content">
      <div id="tag">
        <p id="my_tag" v-for="tag in feed.tag" :key="tag">#{{tag}}</p>
      </div> 
        <p id="caption">{{feed.caption}}</p>
    </div>
    <comment-list :comments="feed.comments"></comment-list>
  </div>
</template>

<script>
import CommentList from '@/components/MainPage/FeedTab/CommentList.vue';

export default {
  components: { CommentList, },
  name: "Feed",
  props: {
    feed: Object,
    comments:Array
  },
  data(){
    return{
      planetStyles: [
        { id: 1, name: '행복행성', img: "happy.png", color: '#6BD9E8' },
        { id: 2, name: '우울행성', img: "depressed.png", color: '#2A61F0' },
        { id: 3, name: '중립행성', img: "neutral.png", color: '#C5D3DC' },
        { id: 4, name: '공포행성', img: "fear.png", color: '#ED5A8E' },
        { id: 5, name: '깜짝행성', img: "surprised.png", color: '#FEA95C' },
        { id: 6, name: '분노행성', img: "rage.png", color: '#FB5D38' },
      ],
    }
  },
   computed: {
    tmp: function () {
      const name = this.feed.planet
      const style = this.planetStyles.find(el => el.name === name) || {}
      return style
    }
  },
  methods: {
    like() {
      this.feed.hasBeenLiked ? this.feed.likes-- : this.feed.likes++;
      this.feed.hasBeenLiked = !this.feed.hasBeenLiked;
    },
    onUserFeedSetting:function(){
      this.$store.commit('userFeedSettingModalActivate')
		}
  }
};
</script>

<style scoped>
  #setting{
    font-size: 2rem;
    margin-left: auto;
  }
  #content{
    text-align: left;
    margin:0rem 3rem;
    font-size: 1.2rem;
  }
  #tag{
    display:flex;
  }
  #like{
    display:flex;
    /* margin:0rem 3rem 0rem; */
    margin-left: 3rem;
    align-items: center;

  }
  .likes{
    margin-top:1rem;
    margin-left:5px;
    font-size: 1.2rem;
  }
  #profile_content{
    direction:column;
  }
  #feed{
    border: 3px solid  rgb(94, 57, 179);
    border-radius: 30px;
    margin:1rem;
  }
  #my_tag{
    color:rgb(37, 37, 201);
    margin-bottom:3px;
  }
  #header{
    display:flex;
    margin:1rem 3rem; 
  }
  #username{
    font-size: 1.5rem;
    font-weight: bold;
  }
  #profile_image{
    display: inline-flex;
    width: 4rem;
    height: 4rem; 
    border-radius: 70%;
    overflow:hidden;
  }
  #profile_image img{
    width: 4rem;
    height: 4rem;

  }
  .level-left{
    
    /* background-color: antiquewhite; */
  }
  #post_image img{
    width: 90vh;
    height: 90vh;
  }
  #post_image{
    position: relative;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: flex-end;
  }
  .overlay_content {
    position: absolute;
    padding: 0rem 1rem;
    background-color: white;
    border-radius: 10px;
  }


</style>