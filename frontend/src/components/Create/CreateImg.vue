<template>
  <section id="img-container">
    <article id="subs">
      <img src="../../assets/images/icons/return.png" alt="" id="back" @click="out">
      <h1 id="title">글 쓰기</h1>
    </article>
    <article id="img-box">
      <div v-if="!images.length">
        <!-- 이미지 미리보기 만들어야 함 -->
        <img src="../../assets/images/icons/image.png" alt="" id="default-img">
        <p id="img-text">이미지를 올려주세요</p>
      </div>
      <div v-else>
        <div v-for="(image, index) in images" :key="index">
          <img :src="image.imgPreview" alt="">
        </div>
      </div>
    </article>
    <footer>
      <div class="imgUpload">
        <label for="file">이미지 업로드</label> 
        <input type="file" id="file" accept="image/*" @change="imgUpload" ref="feedImg" multiple>
      </div>
      <button id="btn-next" @click="nextPage">다음</button>
    </footer>
  </section>
</template>

<script>
import {mapState} from 'vuex'

export default {
  data () {
    return {
      images: [],
      imgPreviews: [],
      uploadImageIndex: 0,
      feedDto: {
        descr: null,
        author: null,
        tags: [],
      },
      rawImage: [],
    }
  },
  methods: {
    out(){
      this.$emit('out')
    },
    nextPage(){
      this.$emit('next-page')
    },
    imgUpload () {
      // this.$store.commit('feedImg', img)
      this.images = []
      this.rawImage = this.$refs.feedImg.files;
      
      let num = -1;
      for (let i =0; i < this.$refs.feedImg.files.length; i++) {
        this.images = [
          ...this.images,
          {
            image: this.$refs.feedImg.files[i],
            imgPreview: URL.createObjectURL(this.$refs.feedImg.files[i]),
            number: i
          }
        ];
        num = i;
      }
      this.uploadImageIndex = num + 1;
      // console.log(this.images)
      if (this.images.length > 3) {
        alert('사진이 너무 많습니다')
        this.images = []
      } else {
        this.$store.commit('feedImg', this.images)
        this.$store.commit('rawImg', this.rawImage)
        // console.log('성공')
      }
    },
  },
  computed: 
    mapState([
      'userInfo', 'planetStyles',
    ]),

  created: function () {
    this.feedDto.author = this.userInfo.no
    console.log(this.feedDto)

    for (let planet of this.planetStyles) {
      if (planet.id === this.userInfo.mood) {
        this.feedDto.tags.push({name: `${planet.name}`, type: 1 })
      }
    }
    console.log(this.feedDto.tags)
    this.$store.commit('feedUserData', this.feedDto)
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
  .imgUpload label {
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

.imgUpload input[type="file"] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}
</style>