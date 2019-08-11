<template>
  <div id="logout">
    <div class="alert alert-success logout" role="alert">
        You have been logged out successfully.
      </div>
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
  width:100%;
  display:flex;
  justify-content: space-around;
}
.logout{
  margin-top: 130px;
  width: 80%; 
  max-width: 400px;
}

</style>
