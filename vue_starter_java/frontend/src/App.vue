<template>
  <div id="app">
  <div id="nav" class="navbar navbar-default sticky-top">
      <router-link style="text-decoration: none;" to="/"><img style="width: 32px; margin-right: 10px;" src="../public/telogo.png"/>TE-Gram</router-link>
      <router-link v-if="isLoggedIn" to='/upload'><h5>Upload Picture!</h5></router-link>
      <router-link v-if="!isLoggedIn" to="/login"><h5>login</h5></router-link>
      <router-link v-if="isLoggedIn" to="/logout"><h5>logout</h5></router-link>
  </div>
    <router-view/>
  </div>

</template>





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

