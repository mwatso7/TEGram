<template>
  <div id="logout">
    <h3>You've been successfully logged out</h3>
  </div>
</template>

<script>
import auth from '../auth'

export default {
  name: "logout",
  data(){
    return {
      user: auth.getUser()
    }
  },
  created(){
    auth.logout();
    let status = false;
    this.$emit('changeLogin', status);
  }
}
</script>

<style>
#logout{
  margin-top: 300px;
}

</style>
