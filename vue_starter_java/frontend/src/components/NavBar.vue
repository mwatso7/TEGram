<template>
  <div id="nav">
      <router-link to="/"><h1>TE-Gram</h1></router-link>
      <router-link v-if="!isloggedIn" to="/login"><h5>login</h5></router-link>
      <router-link v-if="isLoggedOut" to="/logout"><h5>logout</h5></router-link>
  </div>
</template>
  

<script>
import auth from '../auth'

export default {
  name: "NavBar",
  data(){
    return {
      user: auth.getUser()
    }
  },
  method:{
    changeStatus(status){
      this.isTrue = status;
    },
    logoutUser(){
      auth.logout();
      this.user = auth.getUser();
    }
  },
  created(){
    this.user = auth.getUser();
  }
}
</script>
