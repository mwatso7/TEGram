<template>
  <div id="app">
    <div id="nav">
      <router-link to="/"><h1>TE-Gram</h1></router-link>
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
      user: auth.getUser(),
      isLoggedIn: false
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

