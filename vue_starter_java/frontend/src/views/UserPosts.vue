<template>
  <div class="home">
    <div class="post card" style="margin-bottom: 20px; padding:0px;" v-for="post in filteredPost" v-bind:key="post.post_id">
      <div class="card-header">
        <img style="width: 32px; margin-right: 10px;" src="../../public/telogo.png"/>{{post.username}} - {{post.title}}
      </div>
      <router-link v-bind:to="'/detail/post_id/' + post.post_id">
      <img class="card-img-center" v-bind:src ="post.img_url" alt='img' >
      </router-link>
      <div class="card-text" style="padding-left: 10px;">
      <i class="fas fa-heart"></i>
      </div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item" v-if="post.comments.length != 0"><span>{{post.comments[0].username}}</span>: {{post.comments[0].comment}}</li>
      </ul>
      <div class="card-footer">
        <small class="text-muted">{{post.date_time.monthValue + "/" + post.date_time.dayOfMonth + "/" + post.date_time.year + " " + post.date_time.hour + ":" + post.date_time.minute}}</small>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "user-posts",
  data() {
    return {
      postAPI: "http://localhost:8080/tegram/post/allposts",
      posts: [],
      userSrch: ''
    };
  },
  computed: {
    filteredPost(vm){
      return vm.posts.filter((pst) => {
        return (pst.username == vm.userSrch || vm.userSrch == '');
      });
    }
  },
  method: {

  },
  created() {
    this.userSrch = this.$route.params.username;
    // load the reviews
    fetch(this.postAPI)
      .then((response) => {
        return response.json();
      })
      .then((posts) => {
        this.posts = posts;
      })
      .catch((err) => console.error(err));
  },
  
  
};
</script>

<style>

body{
  margin:0px;
  padding: 0px;
}

div.home {
  margin-top: 100px;
}

a {
  text-decoration: none;
  
}

h1 {
  color: blue;
  text-align: center;
  
}

h4 {
  color:green;
  text-align: left;
  
}

body > div#app > div#nav{
  margin:0px;
  text-align: center;
  background-color: aliceblue;
  
}

div.home > div.post > p{
  text-align: left;
  
}

img {
  align-content: center;
  
  
}

@media only screen and (max-width: 600px){
  img {
    object-fit: cover;
    width: 100%;
   
  }
  div.home > div.post{

  }
}

/* @media only screen and (min-width: 600px){
  img {
    object-fit: cover;
    width: 100%;
  }
} */

/* @media only screen and (min-width: 768px){
  img {
    object-fit: cover;
    width: 100%;
  }

  h4{
    font-size: 1.5rem;
    
  }

  p{
    font-size: 1rem;
  }
}

@media only screen and (min-width: 992px){
  div.home > div.post{
    border: 2px solid rgba(60, 74, 80, 0.13);
    margin: 10px;
    padding: 10px;
    width: 600px;
  }

  img {
    object-fit: cover;
    width: 100%;
  }

  h4{
    font-size: 2rem;
  }

  p{
    font-size: 1.5rem;
  }
}
 */
@media only screen and (min-width: 600px){
  img {
    
    object-fit: cover;
    width: 100%;
    
  }

  div.home {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }

  div.home > div.post{
    border: 2px solid rgba(60, 74, 80, 0.13);
    margin: 10px;
    padding: 10px;
    width: 600px;
  }
}
</style>