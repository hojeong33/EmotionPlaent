<template>
  <div id="alarm_body">
    <section id="alarm_header">
      <div id="title">
        <p>알림</p>
      </div>
    </section>
    <section id="alarm_content">
      <div id="results" v-if="this.$store.state.alarm.length !== 0">
        <div v-for="result in this.$store.state.alarm"
        :key="result.no"
        id="result">
          <!-- 팔로우 -->
          <div id="type_follow" v-if="result.type == 1" @click="follow(result.sender)">
            <img :src="result.senderImg" alt="" id="user">
            <div id="search">
              <span id="title">{{ result.senderNickname }}님이 회원님을</span>
              <span id="title">팔로우하기 시작했습니다.</span>
            </div>
          </div>
          <!-- 댓글 -->
          <div id="type_comment" v-if="result.type == 2">
            <img :src="result.senderImg" alt="" id="user">
            <div id="search">
              <span id="title">{{ result.senderNickname }}님이 회원님의</span>
              <span id="title">이야기에 댓글을 달았습니다.</span>
            </div>
          </div>
          <!-- 피드 좋아요 -->
          <div id="type_feedlike" v-if="result.type == 3">
            <img :src="result.senderImg" alt="" id="user">
            <div id="search">
              <span id="title">{{ result.senderNickname }}님이 회원님의</span>
              <span id="title">이야기를 좋아합니다.</span>
            </div>
          </div>
          <!-- 찜목록 좋아요 -->
          <div id="type_picklike" v-if="result.type == 4">
            <img :src="result.senderImg" alt="" id="user">
            <div id="search">
              <span id="title">{{ result.senderNickname }}님이 회원님의</span>
              <span id="title">보물상자를 좋아합니다.</span>
            </div>
          </div>
        </div>
      </div>
      <div v-else id="no_result">
        <img id="nothing" src="@/assets/images/etc/alien.png" alt="">
        <h3>알림이 없어요...</h3>
      </div> 
    </section>
    <section id="alarm_footer">
      <button @click="cancel">X</button>
    </section>
  </div>
</template>

<script>

export default {
  name: 'Alarm',
  
  data() {
    return {
      
    }
  },
  methods: {
    cancel() {
      this.$emit('cancelAlarm')
      this.$store.state.alarm = []
      this.$store.dispatch('readAlarm', this.$store.state.userInfo.no)
    },
    follow(el) {
      console.log(el)
      this.$store.state.searchUserNo = el
      this.$store.dispatch('userSelect', el)
      this.$store.dispatch('userfollowdate', el)
      this.$router.push('Userpage')
    }
  }
}
</script>

<style scoped>
  #alarm_body {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: white;
  }

  #alarm_header {
    z-index: 10;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: white;
    width: 100%;
    min-width: 300px;
    height: 10%;
    min-height: 55px;
    border-radius: 10px 10px 0 0;
    border-top: 0.15rem solid gainsboro;
    border-left: 0.15rem solid gainsboro;
    border-right: 0.15rem solid gainsboro;
  }
  
  #alarm_content {
    overflow: auto;
    display: flex;
    justify-content: center;
    background-color: white !important;
    width: 100%;
    min-width: 300px;
    height: 80%;
    min-height: 300px;
    border: 0.15rem solid gainsboro;
  }
  
  #alarm_footer {
    display: flex;
    justify-content: center;
    background-color: white;
    width: 100%;
    min-width: 300px;
    height: 10%;
    min-height: 60px;
    border-bottom: 0.15rem solid gainsboro;
    border-left: 0.15rem solid gainsboro;
    border-right: 0.15rem solid gainsboro;
    border-radius: 0 0 10px 10px;
  }

  #container {
    width: 100%;
    background-color: white;
  }

  #results {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: left;
    background-color: white;
    overflow: auto;
  }

  #result {
    display: flex;
    flex-direction: column;
    width: 85%;
    justify-content: center;
    align-items: center;
    margin: 1rem 1rem;
    background-color:rgb(220, 220, 220, 0.2);
    border-radius: 10px;
    cursor: pointer;
  }

  h3 {
    font-size: 1.25rem;
    font-weight: bold;
    margin: 0;
  }

  p {
    font-size: 1.5rem;
    font-weight: bold;
    margin: 0;
  }

  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1.125rem;
    font-weight: bold;
    border: none;
    border-radius: 20px;
    padding: 0 2.7rem;
    margin: 0.55rem;
    cursor: pointer;
    line-height: 2rem;
  }

  #title {
    width: 100%;
    display: flex;
    justify-content: center;
  }

  #no_result {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 0;
  }

  #nothing {
    width: 4vh;
    height: 4vh;
    margin-bottom: 2vh;
  }

  #type_follow {
    width: 100%;
    display: flex;
    align-items: center;
  }

  #type_comment {
    width: 100%;
    display: flex;
  }

  #type_feedlike {
    width: 100%;
    display: flex;
  }

  #type_picklike {
    width: 100%;
    display: flex;
  }

  #user {
    width: 4vh;
    height: 4vh;
    border-radius: 50%;
  }

  #search {
    margin-left: 0.7rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

</style>