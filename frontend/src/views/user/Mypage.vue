<template>
  <div>
    <navigation></navigation>
    <side-profile-card></side-profile-card>
    <div id="container">
      <div id="v1"></div>
      <div id="profile_container">
        <img src="../../assets/images/icons/profile.png" id="profile_img">
        <div id="profile_card">
          <div id="name_card">
            <h1>최강상후</h1>
            <button>프로필 수정</button>
          </div>
          <div id="info_card">
            <h3>게시글</h3>
            <h3>팔로워</h3>
            <h3>팔로잉</h3>
          </div>
        </div>
      </div>
      <div id="v2"></div>
    </div>
  </div>  
</template>

<script>
import SideProfileCard from '../../components/SideProfileCard.vue'
import Navigation from '../../components/Navigation.vue'
// import TabItem from './TabItem.vue'
export default {
  name: 'Mypage',
  components: {SideProfileCard, Navigation},
  // TabItem
}
</script>

<style scoped>
  #container {
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  #v1 {
    display: flex;
    border: 1px solid gainsboro;
    min-height: 100vh;
  }
  
  #v2 {
    display: flex;
    border: 1px solid gainsboro;
    min-height: 100vh;
  }

  #profile_container {
    display: flex;
    flex-direction: row;
    background-color: white;
    width: 100vh;
    min-width: 350px;
    height: 30vh;
    min-height: 450px;
    padding: 2rem;
    margin-left: 20rem;
  }

  #profile_card {
    width: 500px;
    margin-left: 1rem;
  }

  #name_card {
    display: flex;
    direction: row;
    justify-content: space-between;
    align-items: center;
    margin-top: 10px;
    margin-right: 10rem;
  }
  
  #info_card {
    display: flex;
    direction: row;
    justify-content: space-between;
    margin-right: 10rem;
  }
  
  #card {
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  #profile_img {
    width: 10vh;
    height: 10vh;
    min-width: 50px;
  }

  h1 {
  color: black;
  font-size: 2rem;
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

  button {
  background-color: #5E39B3;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  border: 1px #5E39B3 solid;
  border-radius: 20px;
  padding: 0.5rem 0.5rem;
  margin-bottom: 1rem;
  cursor: pointer;
  line-height: 1rem;
  }

  input {
  border: 2px #5E39B3 solid;
  border-radius: 20px;
  width: 35vh;
  min-width: 350px;
  height: 4vh;
  min-height: 40px;
  padding: 0.75rem;
  font-size: 1rem;
  font-weight: bold;
  }

  </style>