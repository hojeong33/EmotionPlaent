<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg" id="navbar-default">
      <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="@/assets/images/EMOTION PLANET.png" id="logo_img" alt=""></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">글작성</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">홈</a>
            </li>
            <li class="nav-item">
              <a class="nav-link ">마이페이지</a>
            </li>
            <li class="nav-item">
              <a class="nav-link ">알람</a>
            </li>
            <li class="nav-item">
              <a class="nav-link ">설정</a>
            </li>
          </ul>
          <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>
    <div id="nav">
      <router-link to="/login">Login</router-link> |
      <router-link to="/signup">Signup</router-link> |
      <router-link to="/test1st">Test1st</router-link>
    </div>
    <router-view/>
  </div>
</template>

<style>
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
  background-color: rgb(94, 57, 179)
}
#logo_img{
  width: 70px;
  height: 40px;
}
</style>
