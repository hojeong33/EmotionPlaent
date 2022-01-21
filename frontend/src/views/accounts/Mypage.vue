<template>
  <div id="profile_container">
    <div id="card">
      <img src="../../assets/images/profile.png" id="profile_img">
      <div id="name_card">
        <h1>최강상후</h1>
        <button>프로필 수정</button>
      </div>
    </div>
    <div id="info_card">
      <div>게시글</div>
      <div>팔로워</div>
      <div>팔로잉</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Mypage',
}
</script>

<style>
  #profile_container {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: white;
    width: 80vh;
    min-width: 350px;
    height: 30vh;
    min-height: 450px;
    border-color: gray;
    margin: auto;
    padding: 2rem;
  }

  #name_card {
    display: inline-block;
  }
  
  #info_card {
    display: inline-block;
  }
  
  #card {
    display: flex;
    flex-direction: row;
    justify-content: center;
  }

  #profile_img {
    width: 12vh;
    min-width: 120px;
  }
</style>