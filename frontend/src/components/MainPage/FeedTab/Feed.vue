<template>
  <div id="feed" v-if="feed">
    <div id="header">
      <section id="profile_image">
        <img :src="feed.authorDetail.profileImg" />
      </section>
      <div id="profile_content">
        <section id="username" style="font-size:2rem;">{{feed.authorDetail.nickname}}</section>
        <section style="font-size:1.2rem;">{{feed.date}}</section>
      </div>
      <div id="setting" v-if="isMine">
        <i @click="onCommentSetting" class="fas fa-ellipsis-v" style="color:black"></i>
      </div>
    </div>
    <div id="post_image">
      <article id="img-box">
      <div id="uploaded-box">
          <transition-group id="carousel" :name="page > beforePage ? 'slide':'slide-reverse'">
            <img id="my_img" v-for="(image, index) in feed.imgs" :key="index"
            class="uploadedImg" :src="image.imgLink" alt=""
            v-show="index+1 == page">
          </transition-group>
          <div id="pages">
            <span v-for="idx in feed.imgs.length" :key="idx" 
            :class="['page-num', {'here':idx==page}]" @click="paginationByDot(idx)" />
          </div>
          <span id="left" class="carousel-btn" @click="pagination(false)"/>
          <span id="right" class="carousel-btn" @click="pagination(true)"/>
        </div>
      </article>
      <!-- <div id="my_img" v-for="(img, idx) in feed.imgs" :key="idx"><div><img :src="img.imgLink" alt=""></div></div> -->
      <!-- <img :src="post.postImage" alt="" v> -->
      <!-- <p class="overlay_content" >{{post.author}}님은 {{post.tag[0]}} <img id="planet_img" :src="require('@/assets/images/emotions/' + tmp.img)" style="width:1.2rem;height:1.2rem; margin-bottom:3px">에 있어요</p> -->
    </div>
    <div id="like">
      <div id="heart">
        <i class="far fa-heart fa-lg" style="cursor: pointer;"  :class="{'fas': this.feed.like}"  @click="like"></i>
      </div>
       <p id="feed_likes" v-for="(like, idx) in feed.likes" :key="idx"></p>
      <p class="likes" style="cursor: pointer;"  @click="likesList"  >{{feed.likes.length}} likes</p>
    </div>
    <div id="content">
      <div id="tag">
        <p id="my_tag" v-for="(tag, idx) in feed.tags" :key="idx">#{{tag["name"]}}</p>
      </div> 
        <p id="caption" style="font-size:1.4rem"><span style="font-weight:bold; margin-right:5px;">{{feed.author}}</span>{{feed.descr}}</p>
    </div>
    <comment-list :feedNo="post" :feedAuthor="feed.author" @delete-comment="commentKey++" :key="commentKey"></comment-list>
  </div>
</template>

<script>
import CommentList from './CommentList.vue';
import axios from 'axios';
const session = window.sessionStorage;

export default {
  components: { CommentList },
  name: "Feed",
  props: {
    post: Number,
  },
  data(){
    return{
      // date:this.post.date.toLocaleDateString(),
      page:1,
      beforePage:1,
      isCommentSettingOpened:false,
      isUserFeedSettingOpened: false,
      isMine:false,
      feed:null,
      posts:[],
      planetStyles: [
        { id: 1, name: '행복행성', img: "happy.png", color: '#6BD9E8' },
        { id: 2, name: '우울행성', img: "depressed.png", color: '#2A61F0' },
        { id: 3, name: '떠돌이행성', img: "space-station.png", color: '#C5D3DC' },
        { id: 4, name: '공포행성', img: "fear.png", color: '#ED5A8E' },
        { id: 5, name: '깜짝행성', img: "surprised.png", color: '#FEA95C' },
        { id: 6, name: '분노행성', img: "rage.png", color: '#FB5D38' },
      ],
      commentKey: 0,
    }
  },
   computed: {
    tmp: function () {
      const name = this.feed.tags[0]
      const style = this.planetStyles.find(el => el.name === name) || {}
      return style
    }
  },
  methods: {
    pagination(payload){
      this.beforePage = this.page
      if (this.page < this.feed.imgs.length && payload){
        this.page ++
      }
      else if (this.page > 1 && !payload){
        this.page --
      }
    },
    paginationByDot(target){
      let d
      if (target > this.page){
        d = true
      }
      else {
        d = false
      }
      for (let i=0;i<Math.abs(target-this.page);i++){
        setTimeout(() => {
          console.log(this.page, target, d)
          this.pagination(d)
        }, 1000 * i);
      }
    },
    onCommentSetting:function(){
      this.$store.commit('commentSettingModalActivate')
      // if(this.isCommentSettingOpened){
      // 	this.isCommentSettingOpened=false
      // }else{
      // 	this.isCommentSettingOpened=true
      // }
    },
    onUserFeedSetting2:function(){
      this.$store.commit('userFeedSettingModalActivate2')
      // if(this.isUserFeedSettingOpened){
      // 	this.isUserFeedSettingOpened=false
      // }else{
      // 	this.isUserFeedSettingOpened=true
      // }
    },
    like:function(){
      this.feed.like ? this.cancelLike(): this.doLike();
      this.feed.like= !this.feed.like;
    },
    doLike:function(){
      let el = {
        receiver : this.feed.author,
        feedno : this.post,
      }
      this.$store.dispatch('addfeedlike',el)
    },
    cancelLike:function(){
      this.$store.dispatch('deletefeedlike',this.post)
    },
    getFeed:function(){
       let headers = {
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
        };
        axios({
            method: 'get',
            url:`/api/feed/${this.post}`,
            headers: headers,  // 넣는거 까먹지 마세요
          }).then((res) => {
          this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
          console.log('!!!!!!!!!!!!!!!!!!!')
          console.log(res.data)
          this.feed=res.data
          this.isMine=res.data.owner
          }).catch((error) => {
            console.log(error);
          }).then(() => {
            console.log('피드 하나 가져오기');
          });
    },
    likesList:function(){
       this.$store.commit('likesListActive',this.feed.likes)
       console.log(this.feed.likes)
    }
  },
  created(){
   this.getFeed()
    
  }
  
};
</script>

<style scoped>
  
  .fas{
    color: crimson;
  }
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
    font-size:1.2rem;
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
  #my_img{
    height: 100%;
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
    margin-right:16rem;
    background-color: white;
    border-radius: 10px;
  }
  #detail{
    width: 8%;
    font-size: 20px;
    border:1px solid black;
    border-radius: 10px;
    text-align: center;
    display: flex;
    flex-direction:column;
    position: absolute;
    /* transform: translate(34rem,-10px); */
    background-color: white;
  }
  #uploaded-box {
    width: 100%;
    height: 100%;
    position: relative;
  }
  
  .uploadedImg {
    width: 100%;
    /* aspect-ratio: 1/1;
    position: absolute; */
  }
  @keyframes slide-in {
    from { right: -100% }
    to { right: 0 }
  }

  @keyframes slide-out {
    from { right: 0 }
    to { right: 100% }
  }
  .slide-enter-active {
    animation: slide-in 1s ease;
  }

  .slide-leave-active {
    animation: slide-out 1s ease;
  }

  .slide-reverse-enter-active {
    animation: slide-out 1s ease reverse;
  }

  .slide-reverse-leave-active {
    animation: slide-in 1s ease reverse;
  }
  #pages {
    display: flex;
    flex-wrap: nowrap;
    justify-content: center;
    align-items: center;
    margin: 1rem;
  }

  .page-num {
    width: 12px;
    height: 12px;
    border-radius: 50%;
    background-color: #cccccc;
    margin: 0.75rem;
    cursor: pointer;
  }
   #img-box {
    display: flex;
    flex-direction: column;
    width: 85%;
    height: 65%;
    background-color: lightgray;
    border-radius: 20px;
    margin: auto;
    justify-content: center;
    align-items: center;
    margin: 2rem;
  }
  .here {
    background-color: #777777;
  }
  #left {
    background-image: url('../../../assets/images/icons/left.png');
    left: -2%;
  }

  #right {
    background-image: url('../../../assets/images/icons/right.png');
    right: -2%;
  }

  #carousel {
    display: flex;
    align-items: center;
    width: 100%;
    height: 100%;
    position: relative;
    overflow: hidden;
    border-radius: 20px;
  }
  .carousel-btn {
    background-size: cover;
    border: none;
    border-radius: 50%;
    opacity: 0.75;
    position: absolute;
    width: 2rem;
    height: 2rem;
    top: 45%;
    cursor: pointer;
  }

</style>