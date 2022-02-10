<template>
  <section id="text-container">
    <article id="subs">
      <img src="../../assets/images/icons/return.png" alt="" id="back" @click="out">
      <h1>글 쓰기</h1>
    </article>
    <article id="text-header">
      <h3>이제 자유태그와 글을 작성할 수 있습니다!</h3>
      <h3>여러분의 하루를 공유해주세요</h3> 
    </article>
    {{feedData.tags[0].name}} {{feedData.tags[1].name}}
    <input type="text" id="tag-input" v-model="freeTag">
    <textarea id="text-input" v-model="feedText" />
    <footer>
      <span id="secret">
        <input type="checkbox" id="secret-check" v-model="isChecked">
        <label for="secret-check">비밀글</label>
      </span>
      <span>
        <button id="btn-before" @click="beforePage">이전</button>
        <button id="btn-next" @click="feedWrite">작성</button>
      </span>
    </footer>
  </section>
</template>

<script>
import {mapState} from 'vuex'
import axios from 'axios'

const session = window.sessionStorage;

export default {
  name: 'CreateText',
  data: function () {
    return {
      isChecked: false,
      freeTag: null,
      feedText: null,
      feedData: {
        descr: null,
        author: null,
        tags: [],
      },
      Feedimages: [],
    }
  },
  methods: {
    out(){
      this.$emit('out')
    },
    beforePage(){
      this.$emit('before-page')
    },
    feedWrite () {
      console.log(this.feedData)
      let headers = {
        "Content-Type": 'multipart/form-data',
        'at-jwt-access-token': session.getItem('at-jwt-access-token'),
        'at-jwt-refresh-token': session.getItem('at-jwt-refresh-token'),
      };
      const formData = new FormData();
      this.Feedimages.forEach((file) => formData.append("file", file));
      formData.append(
        "userInfo",
        new Blob([JSON.stringify(this.feedData)], { type: "application/json" })
      );
      axios({
          method: 'post',
          url: 'http://13.125.47.126:8080/feeds',
          data: formData, // post 나 put에 데이터 넣어 줄때
          headers: headers,  // 넣는거 까먹지 마세요
        }).then((res) => {
        this.$store.dispatch('accessTokenRefresh', res) // store아닌곳에서
        this.dispatch('accessTokenRefresh', res) // store에서
        }).catch((error) => {
          console.log(error);
        }).then(() => {
          console.log('getQSSList End!!');
        });
    }

  },
  computed: 
    mapState([
      'feedCreateData',
    ]),
  created: function () {
    this.Feedimages = this.feedCreateData[1].image
    this.feedData.author = this.feedCreateData[0].author
    this.feedData.tags = this.feedCreateData[0].tags
    console.log(this.feedData)
  }, 
}
</script>

<style scoped>
  h1 {
    font-size: 1.25rem;
    font-weight: bold;
    margin: 0;
  }

  h2 {
    font-size: 2rem;
    font-weight: bold;
    margin-bottom: 2rem;
  }

  h3 {
    font-size: 1.5rem;
    margin: 0;
  }
  
  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1.125rem;
    font-weight: bold;
    border: 3px #5E39B3 solid;
    border-radius: 20px;
    padding: 0.2rem 2rem;
    margin: 1rem;
    cursor: pointer;
    line-height: 2rem;
  }

  input:focus, textarea:focus {
    outline-color: #5E39B3;
  }

  label {
    color: #5E39B3;
    font-weight: bold;
    margin-left: 0.5rem;
    font-size: 1.125rem;
  }

  #text-container {
    display:flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    width: 100%;
    height: 100%;
    padding: 0;
  }

  #subs {
    display:flex;
    justify-content: center;
    border-bottom: 1px #cccccc solid;
    width: 100%;
    padding: 0.5rem;
    position: relative;
  }

  #back {
    width: 20px;
    height: 20px;
    position: absolute;
    left: 2%;
    cursor: pointer;
  }

  #text-header {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 80%;
    margin: 2rem 1rem 1rem;
  }

  #tag-input {
    width: 80%;
    height: 5%;
    border: 1px #cccccc solid;
    border-radius: 10px;
    margin: 1rem;
    padding: 1rem;
  }

  #text-input{
    width: 80%;
    height: 50%;
    border: 1px #cccccc solid;
    border-radius: 10px;
    padding: 1rem;
  }

  footer {
    display:flex;
    justify-content: space-between;
    width: 80%;
    margin: 1rem;
  }

  #secret {
    display:flex;
    flex-direction: row;
    justify-content: left;
    align-items: center;
  }

  #btn-before {
    background-color: #777777;
    border: 3px #777777 solid;
  }
</style>