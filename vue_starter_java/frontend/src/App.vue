<template>

  <div id="app">
  <div id="nav" class="navbar navbar-default sticky-top shadow-sm" style="background-color: #383E42;">
      <router-link  class="navbar-brand" style="text-decoration: none;" to="/"><img style="width: 40px; margin-right: 10px;" src="../public/telogo.png"/><span id="logo">TE { Gram }</span></router-link>
      <div class="d-flex" style="color: #00ADEE;">
          <router-link class="nav-item" style="color: #00ADEE; text-decoration: none; display: in-line; margin-right: 20px;" v-if="isLoggedIn" to='/favorites'><i class="far fa-bookmark"></i></router-link>
          <router-link class="nav-item" style="color: #00ADEE; text-decoration: none; display: in-line; margin-right: 20px;" v-if="isLoggedIn" to='/upload'><i class="far fa-paper-plane"></i></router-link>
        
          <router-link style="color: #00ADEE; text-decoration: none; display: in-line;" v-if="!isLoggedIn" to="/login"><h5>login</h5></router-link>
          <router-link style="color: #00ADEE; text-decoration: none; display: in-line;" v-if="isLoggedIn" to="/logout"><h5>logout</h5></router-link>
</div>
  </div>
    <router-view/>
  </div>

</template>


<style scoped>
#logo{
font-family: 'Pacifico', cursive;
color: #00ADEE;
}


body > div#app > div#nav{
  border-bottom: 1px solid #00ADEE;
  
}
</style>


<script>
import auth from './auth'
//import NavBar from "./components/NavBar";

export default {
  components: {
    //NavBar
  },
  data(){
    return {
      isLoggedIn: auth.getUser()
    }
  },
  method:{
    logoutUser(){
      auth.logout();
    }

  },
  created(){
    this.user = auth.getUser();
  },
  watch: {
    '$route': function(value){
      if(value.name === 'logout'){
        this.isLoggedIn = false;
      }else if (value.name === 'success'){
        this.isLoggedIn = true;
      }
    }
  }
}
</script>





