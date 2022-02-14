<template>
  <div id="profile_img_change_modal">
    <div id="modal">
      <div id="header">
        <h3>프로필 사진 바꾸기</h3>

        <div v-if="!images" id="thumbnail">
          <!-- 이미지 미리보기 만들어야 함 -->
          <img
            src="../../assets/images/icons/image.png"
            alt=""
            id="default-img"
          />
          <p id="img-text">이미지를 올려주세요</p>
        </div>
        <div v-else>
          <img :src="images" alt="" />
        </div>
      </div>

      <div id="uploading">
        <label for="file">이미지 업로드</label>
        <input
          type="file"
          id="file"
          accept="image/*"
          @change="imgUpload"
          ref="feedImg"
        />
      </div>
      <div id="footer_buttons">
        <button @click="changeprofileImg">변경하기</button>
        <button @click="cancel">취소</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const session = window.sessionStorage;
export default {
  data() {
    return {
      images: null,
    };
  },
  methods: {
    cancel: function () {
      this.$store.commit("profileImgChangeModalActivate");
    },
    imgUpload() {
      this.images = null;
      this.images = URL.createObjectURL(this.$refs.feedImg.files[0]);
    },
    changeprofileImg() {
      let headers = {
        "at-jwt-access-token": session.getItem("at-jwt-access-token"),
        "at-jwt-refresh-token": session.getItem("at-jwt-refresh-token"),
      };

      const formData = new FormData();
      formData.append("multipartFile", this.$refs.feedImg.files[0]);

      axios({
        method: "post",
        url:
          "http://13.125.47.126:8080/s3/users/img/" + this.$store.state.userInfo.no,
        data: formData, // post 나 put에 데이터 넣어 줄때
        headers: headers, // 넣는거 까먹지 마세요
      })
        .then((res) => {
          console.log("이미지 변경 성공");
          console.log(res.data);
          this.$store.dispatch("allTokenRefreshOnUserInfo", res); // store아닌곳에서
          this.$store.state.userInfo.profileImg = this.images
          this.cancel()
          this.$router.go(-1)
        })
        .catch((error) => {
          console.log("이미지 변경 실패");
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
#upload {
  cursor: pointer;
}

#profile_img_change_modal {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background-color: rgb(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  left: 0;
  z-index: 999;
}
#modal {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;
  border-radius: 20px;
  width: 20vw;
  height: 50vh;
  text-align: center;
}

h3 {
  margin: 0rem 2rem 1rem;
}
p {
  margin: auto;
  font-weight: bold;
  cursor: pointer;
}

#header {
  height: 45%;
}

#uploading {
  display: flex;
  width: 100%;
  height: 25%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border-top: 0.2rem solid gainsboro;
  border-bottom: 0.2rem solid gainsboro;
}

#file {
  width: 80%;
}

#default-img {
  width: 5rem;
  height: 5rem;
}

button {
  background-color: #5E39B3;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  border: 1px #5E39B3 solid;
  border-radius: 20px;
  padding: 0.6rem 1.5rem;
  margin-bottom: 1rem;
  cursor: pointer;
  line-height: 1rem;
}
#footer_buttons {
  display: flex;
  width: 80%;
  justify-content: space-around;
  padding-top: 2rem;
}

</style>
