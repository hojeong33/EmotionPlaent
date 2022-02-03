<template>
  <section id="img-container">
    <article id="subs">
      <img src="../../assets/images/icons/return.png" alt="" id="back" @click="out">
      <h1 id="title">글 쓰기</h1>
    </article>
    <article id="img-box">
      <img src="../../assets/images/icons/image.png" alt="" id="default-img">
      <p id="img-text">이미지를 올려주세요</p>
    </article>
    <footer>
      <button id="btn-upload">이미지 업로드</button>
      <button id="btn-next" @click="nextPage">다음</button>
    </footer>
  </section>
</template>

<script>
export default {
  methods: {
    out(){
      this.$emit('out')
    },
    nextPage(){
      this.$emit('next-page')
    }
  }
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
    font-size: 1.25rem;
    margin: 0;
  }
  
  button {
    background-color: #5E39B3;
    color: white;
    font-size: 1.125rem;
    font-weight: bold;
    border: 3px #5E39B3 solid;
    border-radius: 20px;
    padding: 0.2rem 1.5rem;
    margin: 1rem;
    cursor: pointer;
    line-height: 2rem;
  }

  #img-container {
    display:flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
  }

  #subs {
    display:flex;
    justify-content: center;
    width: 100%;
    position: relative;
    border-bottom: 2px #cccccc solid;
    padding: 0.5rem;
  }

  #back {
    width: 20px;
    height: 20px;
    position: absolute;
    left: 2%;
    cursor: pointer;
  }

  #img-box {
    display: flex;
    flex-direction: column;
    width: 90%;
    height: 75%;
    background-color: lightgray;
    border-radius: 20px;
    margin: auto;
    justify-content: center;
    align-items: center;
    margin: 1rem 0.5rem 0.5rem;
  }

  #default-img {
    width: 3rem;
    height: 3rem;
  }

  #img-text{
    color: gray;
  }

  footer {
    display:flex;
    justify-content: space-between;
    width: 90%;
    margin-bottom: 0.5rem;
  }
</style>