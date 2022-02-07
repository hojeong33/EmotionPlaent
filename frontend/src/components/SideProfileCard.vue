<template>
  <div class="card_container">
    <div class="card_header">
      <img id="profile_image" src="https://www.thesprucepets.com/thmb/meRd41is751DsQQjofaiKV_ZUBg=/941x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/cat-talk-eyes-553942-hero-df606397b6ff47b19f3ab98589c3e2ce.jpg" alt="">
      <div class="overlay_content">
        <h2>{{ userInfo.username }}</h2>
        <button id="update" @click="$router.push({name: 'Setting'})">프로필 수정</button>
      </div>
    </div>
    <div class="card_body">
      <p>게시글 {{ userInfo.posts }}</p>
      <p>팔로우 {{ userInfo.followings }} </p>
      <p>팔로워 {{ userInfo.followers }} </p>
    </div>
    <div id="card_footer">
      <div id="where">
        <span style="font-size:1rem; font-weight:bold">나는 지금...</span>
        <span id="at">
          <img id="planet_img" :src="require('@/assets/images/emotions/' + tmp.img)">
          <p id="planet_name" style="font-size:1.4rem; font-weight:bold; margin-top:0.4rem; margin-left:0.3rem" :style="{color:tmp.color}">{{tmp.name }}</p>
        </span>
      </div>
      <div id="footer_buttons">
        <button @click="createFeed">피드 작성</button>
        <button @click="$router.push({ name:'EmotionTest' })">테스트 다시하기</button>
      </div>
    </div>  
  </div>  
</template>

<script>
export default {
  name: 'SideProfileCard',
  props: {
    userInfo: Object,
  },
  data() {
    return {
      planetStyles: [
        { id: 1, name: '행복행성', img: "happy.png", color: '#6BD9E8' },
        { id: 2, name: '우울행성', img: "depressed.png", color: '#2A61F0' },
        { id: 3, name: '중립행성', img: "neutral.png", color: '#ABBECA' },
        { id: 4, name: '공포행성', img: "fear.png", color: '#ED5A8E' },
        { id: 5, name: '깜짝행성', img: "surprised.png", color: '#FEA95C' },
        { id: 6, name: '분노행성', img: "rage.png", color: '#FB5D38' },
      ]
    }
  },
  computed: {
    tmp: function () {
      const mood = this.$store.state.userEmotion
      const style = this.planetStyles.find(el => el.id === mood) || {}
      return style
    }
  },
  methods: {
    createFeed: function(){
      this.$store.commit('activateFeed')
    }
  },
}
</script>

<style scoped>
  .card_container {
    display: flex;
    flex-direction: column;
    width: 37vh;
    min-width: 300px;
    height: 50vh;
    min-height: 400px;
    position: fixed;
    top: 10rem;
    right: 3%;
    z-index: 1;
    border: 0.1rem solid gainsboro;
    border-radius: 10px;
  }

  .card_header {
    position: relative;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: flex-end;
    height: 25vh;
  }

  .overlay_content {
    position: absolute;
    display: flex;
    direction: row;
    width: 90%;
    justify-content: space-between;
    align-items: center;
    border-bottom: 0.1rem solid gainsboro;
  }

  .card_body {
    display: flex;
    direction: row;
    justify-content: space-evenly;
    align-items: flex-end;
    height: 6vh;
    border-bottom: 0.1rem solid gainsboro;
    font-size: 1rem;
    font-weight: bold;
  }

  .card_footer {
    display: flex;
    height: 29vh;
    margin: auto;
  }

  h2 {
    color: white;
    font-size: 1.5rem;
    font-weight: bold;
  }

  h3 {
    font-size: 1.25rem;
    font-weight: bold;
    letter-spacing: -1px;
    line-height: 75%;
  }

  label {
    color: #5E39B3;
    font-size: 1.125rem;
    font-weight: bold;
    letter-spacing: -0.5px;
    margin-left: 0.5rem;
  }

  #update {
    background-color: gray;
    color: white;
    font-size: 0.8rem;
    font-weight: bold;
    border-radius: 20px;
    cursor: pointer;
    border: none;
    padding: 0.2rem 0.9rem;
  }

  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1rem;
    font-weight: bold;
    border: 1px #5E39B3 solid;
    border-radius: 20px;
    padding: 0.5rem 1rem;
    margin-bottom: 1rem;
    cursor: pointer;
    line-height: 1rem;
  }

  #footer_buttons {
    display: flex;
    justify-content: space-around;
  }

  #where {
    display: flex;
    flex-direction: column;
    margin: 1rem 0 1.2rem 0;
  }

  #at {
    display: flex;
    direction: row;
    justify-content: center;
    margin-top: 0.7rem;
  }

  #planet_img {
    width: 5vh;
    height: 5vh;
    min-width: 20px;
  }

  #profile_image {
    position: relative;
    width: 43vh;
    height: 28vh;
    border-radius: 10px;
  }
</style>