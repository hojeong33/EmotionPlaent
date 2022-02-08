<template>
  <div id="nav">
    <nav class="navbar navbar-expand-lg" id="navbar-default">
      <div class="container">
        <div>
          <img src="@/assets/images/logo/EMOTION PLANET.png" id="logo_img" alt="" @click="goMain">
        </div>
        <form class="d-flex" id="searches">
          <div class="search_menu" v-on:cancel="searchOff">
            <input id="search_bar" class="form-control" autocomplete="off"
            type="search" placeholder="Search" aria-label="Search"
            @focus="searchOn" :value="searchWords" @input="search">
            <search v-if="searching" v-on:cancel="searchOff" id="dropdown"></search>
          </div>
          <!-- <button class="btn btn-outline-success" type="submit" id="search"><img src="@/assets/images/search.png" id="search"></button> -->
          <img src="@/assets/images/icons/search.png" id="search" type="submit">
        </form>
        <div id="icons">
          <img @click="changeImg_write" :src="require(`@/assets/images/icons/${imgName_write}`)" id="write">
          <img src="@/assets/images/icons/home.png" id="home" @click="goMain">
          <img @click="changeImg_profile" :src="require(`@/assets/images/icons/${imgName_profile}`)" id="my_page">
          <div>
            <img @click="changeImg_alarm" :src="require(`@/assets/images/icons/${imgName_alarm}`)" id="alarm">
          </div>
          <img @click="changeImg_setting" :src="require(`@/assets/images/icons/${imgName_setting}`)" id="setting">
        </div>
      </div>
    </nav>
  </div>
</template>
<script>
// import {mapState} from 'vuex'
// 똑같은 페이지 눌렀을 때 새로고침이 안 됨 - 수정 필요
import Search from '@/components/Search/Search'

export default {
  name: 'App',
  data: function (){
    return {
      imgName_write: 'more.png',
      imgName_profile: 'user.png',
      imgName_setting: 'settings.png',
      imgName_alarm: 'bell.png',
      isActive: [false, false, false, false], //네브바에서 선택됐는지 여부를 파악
      //검색입니둥
      searching: false,
      searchWords: null,
    }
  },
  components: { Search },
  methods: {
    changeImg_write() {
      this.$store.commit('activateFeed')
      if (this.imgName_write === 'more.png') {
        this.imgName_write = 'more_selected.png'
        this.isActive[0] = true

        if (this.isActive[1] === true || this.isActive[2] === true || this.isActive[3] === true) {
          this.imgName_profile = 'user.png'
          this.imgName_alarm = 'bell.png'
          this.imgName_setting = 'settings.png'
          this.isActive[1] = false
          this.isActive[2] = false
          this.isActive[3] = false
      

        }
      }

        // 얘는 페이지 바뀌면 색이 빠지는 것을 어떻게 코드를 짜야 하나... ㅠㅠ

    },
    changeImg_profile() {
      this.$router.push({name:'Mypage'})
      // console.log(this.$route.name)
      // if (this.$route.name === 'Mypage') {
      //   this.$router.go(0)
      // } else {
      this.isActive[1] = true
      console.log(this.isActive)
      if (this.isActive[1] === true) {
        this.imgName_profile = 'user_selected.png'
        
        if (this.isActive[0] === true || this.isActive[2] === true || this.isActive[3] === true) {
          this.imgName_write = 'more.png'
          this.imgName_alarm = 'bell.png'
          this.imgName_setting = 'settings.png'
          this.isActive[0] = false
          this.isActive[2] = false
          this.isActive[3] = false
        }
      } 
      this.isActive[1] = false
      console.log(this.isActive)
      // }
    },
    changeImg_alarm() {
      this.$router.push('#')
      if (this.imgName_alarm === 'bell.png') {
        this.imgName_alarm = 'bell_selected.png'
        this.isActive[2] = true

        if (this.isActive[0] === true || this.isActive[1] === true || this.isActive[3] === true) {
          this.imgName_write = 'more.png'
          this.imgName_profile = 'user.png'
          this.imgName_setting = 'settings.png'
          this.isActive[0] = false
          this.isActive[1] = false
          this.isActive[3] = false
        }
      } 
    },
    changeImg_setting() {
      this.$router.push({name: 'Setting'})
      if (this.imgName_setting === 'settings.png') {
        this.imgName_setting = 'settings_selected.png'
        this.isActive[3] = true
        
        if (this.isActive[0] === true || this.isActive[1] === true || this.isActive[2] === true) {
          this.imgName_write = 'more.png'
          this.imgName_profile = 'user.png'
          this.imgName_alarm = 'bell.png'
          this.isActive[0] = false
          this.isActive[1] = false
          this.isActive[2] = false
        }
      } 
    },
    goMain() {
      this.$router.push({name: 'Main'})
      this.imgName_write = 'more.png'
      this.imgName_profile = 'user.png'
      this.imgName_alarm = 'bell.png'
      this.imgName_setting = 'settings.png'
    },
    //검색 부분입니둥
    searchOn() {
      this.searching = true
    },
    searchOff() {
      this.searching = false
      this.$store.state.words = null
      this.$store.state.tagSearch = []
      this.$store.state.userSearch = []
    },
    search( searchWords ) {
      this.$store.commit('updateSearch', searchWords.target.value)
      this.$store.dispatch('searchTag')
      this.$store.dispatch('searchUser')
    },
  }
    // search() {
    //   if (this.searchInput) {
    //     axios({

    //     })
    //     .then({

    //     })
    //     .catch(err => {
    //       alert(err)
    //     })
    //   }
    // }

  //  computed: {
  //   ...mapState([
  //     'feedActive'
  //   ]) 
  // }
}
</script>

<style scoped>
#nav {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 100%;
  min-width: 700px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
#navbar-default{
  background-color: rgb(94, 57, 179);

}
#logo_img{
  width: 70px;
  height: 40px;
}
.container{
  display:flex;
  flex-direction: row;
  justify-content: space-between;
  flex-wrap: nowrap;
  width: 52%;
  min-width: 700px;
}
#search_bar{
  width: 20vw;
  height: 35px;
  margin-top:auto;
  margin-bottom: auto;
}

#search{
  width: 25px;
  height: 25px;
}

#icons {
  display: flex;
  flex-direction: row;
  margin-left: 0px;
}
#setting{
  width: 25px;
  height: 25px;
  margin: 10px;
}
#write{
  width: 25px;
  height: 25px;
  margin: 10px;
}
#home{
  width: 25px;
  height: 25px;
  margin: 10px;
}
#my_page{
  width: 25px;
  height: 25px;
  margin: 10px;
}
#alarm{
  width: 25px;
  height: 25px;
  margin: 10px;
  position: relative;
}
/* .note-num {
  position: absolute;
  top: 0;
  right: 0;
  z-index: 3;
  height: 20px;
  width: 20px;
  line-height: 20px;
  text-align: center;
  background-color: red;
  border-radius: 15px;
  display: inline-block;
  color: white;
} */
#search{
  width: 25px;
  height: 25px;
  margin: 10px;
  position: relative;
} 
img{
  cursor: pointer;
}

.search_menu {
  display: flex;
  flex-direction: column;
}

#dropdown {
  z-index: 10;
  width: 25%;
  min-width: 150px;
  height: 15%;
  position: absolute;
  background-color: white;
  margin-top: 3.5rem;
}
</style>

<!-- 알림버튼 숫자표시 
#HTML
<div class="nav-right">
        <div class="nav-btn" id="friend"></div>
        <div class="nav-btn" id="notification"><span class="note-num">3</span></div>
        <a id="logout" role="button" href="{% url 'logout' %}">로그아웃</a>
</div>

#CSS
note-num {
    position: absolute;
    top: 0;
    right: 0;
    z-index: 3;
    height: 20px;
    width: 20px;
    line-height: 20px;
    text-align: center;
    background-color: red;
    border-radius: 15px;
    display: inline-block;
}

#notification {
    background-image: url('../images/notification.svg');
    position: relative;
} -->