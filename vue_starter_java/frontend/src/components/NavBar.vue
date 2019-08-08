<template>
  <div id="nav">
      <router-link to="/"><h1>TE-Gram</h1></router-link>
      <router-link v-if="!user" to="/login"><h5>login</h5></router-link>
      <router-link v-if="user" v-on:click="user = !user" to="/logout"><h5>logout</h5></router-link>
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
    logoutUser(){
      auth.logout();
    }
  },
  created(){
    this.user = auth.getUser();
  }
}
</script>
