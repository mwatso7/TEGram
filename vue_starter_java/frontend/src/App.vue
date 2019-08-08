<template>
  <div id="app">
    <nav-bar/>
    <router-view/>
  </div>

</template>

<script>
import auth from './auth'
import NavBar from "./components/NavBar";

export default {
  components: {
    NavBar
  },
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

