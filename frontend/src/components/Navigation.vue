<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg" id="navbar-default">
      <div class="container">
        <a class="navbar-brand" href="#"><img src="@/assets/images/logo/EMOTION PLANET.png" id="logo_img" alt=""></a>
        <form class="d-flex" id="searches">
          <input id="search_bar" class="form-control" autocomplete="off"
          type="search" placeholder="Search" aria-label="Search"
          @focus="searchOn" :value="searchWords" @input="search">
          <!-- <button class="btn btn-outline-success" type="submit" id="search"><img src="@/assets/images/search.png" id="search"></button> -->
          <img src="@/assets/images/icons/search.png" id="search" type="submit">
        </form>
        <router-link to="/create"><img @click="changeImg_write" :src="require(`@/assets/images/icons/${imgName_write}`)" id="write"></router-link>
        <router-link to="/#"><img src="@/assets/images/icons/home.png" id="home"></router-link>
        <router-link to="/#"><img @click="changeImg_profile" :src="require(`@/assets/images/icons/${imgName_profile}`)" id="my_page"></router-link>
        <div>
          <router-link to="/#"><img @click="changeImg_alarm" :src="require(`@/assets/images/icons/${imgName_alarm}`)" id="alarm"></router-link>
        </div>
        <router-link to="/#"><img @click="changeImg_setting" :src="require(`@/assets/images/icons/${imgName_setting}`)" id="setting"></router-link>
        <!-- <a class="nav-link active" aria-current="page" href="#"><img src="@/assets/images/more.png" id="write"></a>
        <a class="nav-link" href="#"><img src="@/assets/images/home.png" id="home"></a>
        <a class="nav-link "><img src="@/assets/images/user.png" id="my_page"></a>
        <a class="nav-link "><img src="@/assets/images/bell.png" id="alarm"></a>
        <a class="nav-link "><img src="@/assets/images/settings.png" id="setting"></a>
        <a class="nav-link "><img src="@/assets/images/bell_selected.png" id="setting"></a> -->
      </div>
    </nav>
  </div>
</template>
<script>

export default {
  name: 'App',
  data: function (){
    return {
      isActive: false,
      imgName_write: 'more.png',
      imgName_profile: 'user.png',
      imgName_setting: 'settings.png',
      imgName_alarm: 'bell.png' ,
      searching: false,
      searchWords: null,
    }
  },
  methods: {
    changeImg_write() {
      if (this.imgName_write === 'more.png') {
        this.imgName_write = 'more_selected.png'
        this.isActive = true
      } else {
        this.imgName_write = 'more.png'
        this.isActive = false
      }
    },
    changeImg_profile() {
      if (this.imgName_profile === 'user.png') {
        this.imgName_profile = 'user_selected.png'
        this.isActive = true
      } else {
        this.imgName_profile = 'user.png'
        this.isActive = false
      }
    },
    changeImg_setting() {
      if (this.imgName_setting === 'settings.png') {
        this.imgName_setting = 'settings_selected.png'
        this.isActive = true
      } else {
        this.imgName_setting = 'settings.png'
        this.isActive = false
      }
    },
    changeImg_alarm() {
      if (this.imgName_alarm === 'bell.png') {
        this.imgName_alarm = 'bell_selected.png'
        this.isActive = true
      } else {
        this.imgName_alarm = 'bell.png'
        this.isActive = false
      }
    },
    searchOn() {
      this.$store.commit('activateSearch')
    },
    search( searchWords ) {
      this.$store.commit('updateSearch', searchWords.target.value)
      this.$store.dispatch('searchTag')
      this.$store.dispatch('searchUser')
    }
  }
}
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
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
  justify-content: center;
  flex-wrap: nowrap;
}
#search_bar{
  width: 150px;
  height: 35px;
}
#search{
  width: 25px;
  height: 25px;
  margin: 10px;
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