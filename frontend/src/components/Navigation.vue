<template>
  <div>
    <nav class="navbar navbar-expand-lg" id="navbar-default">
        <div class="container justify-content-center">
            <a class="navbar-brand" href="#"><img src="@/assets/images/logo/EMOTION PLANET.png" id="logo_img" alt=""></a>
            <!-- <router-link :to="{ name: 'Signup' }" class="gosignup">회원가입</router-link> -->
            <form class="d-flex">
                <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit"><img src="@/assets/images/icons/search.png" id="search"></button>
            </form>
            <a class="nav-link active" aria-current="page" href="#"><img src="@/assets/images/icons/more.png" id="write"></a>
            <a class="nav-link" href="#"><img src="@/assets/images/icons/home.png" id="home"></a>
            <a class="nav-link "><img src="@/assets/images/icons/user.png" id="my_page"></a>
            <a class="nav-link "><img src="@/assets/images/icons/bell.png" id="alarm"></a>
            <a class="nav-link "><img src="@/assets/images/icons/settings.png" id="setting"></a>
        </div>
    </nav>
  </div>
</template>

<script>
export default {

}
</script>

<style>
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
    #setting{
    width: 25px;
    height: 25px;
    }
    #write{
    width: 25px;
    height: 25px;
    }
    #home{
    width: 25px;
    height: 25px;
    }
    #my_page{
    width: 25px;
    height: 25px;
    }
    #alarm{
    width: 25px;
    height: 25px;
    }
    #search{
    width: 25px;
    height: 25px;
    } 
</style>