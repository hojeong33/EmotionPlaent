<template>
  <div class="card_container">
    <div class="card" style="width: 15rem;">
      <div class="card-header text-black overlay">
        <div class="overlay-content">
          <h5 class="card-title">최강상후</h5>
          <button>프로필 수정</button>
        </div>
        <img class="card-img-top" src="../assets/images/icons/profile.png" alt="Card image cap">
      </div>
      
      <div class="card-body">
        <p>게시글</p>
        <p>팔로우</p>
        <p>팔로워</p>
        <a href="#" class="btn btn-primary">피드 작성</a>
        <a href="#" class="btn btn-primary">테스트 다시하기</a>
      </div>
    </div>
  </div>
  
</template>

<script>
export default {
  name: 'SideProfileCard',
}
</script>

<style>
  .card_container {
    position: fixed;
    top: 15rem;
    right: 0;
    padding-right: 30px;
  }
  .card-img-top {
    width: 12vh;
    min-width: 120px;
  }
</style>