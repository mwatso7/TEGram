<template>
  <div class="home">
    <div class="post card" style="margin-bottom: 20px; padding:0px;" v-for="post in filteredPost" v-bind:key="post.post_id">
      <div class="card-header">
        <img style="width: 32px; margin-right: 10px;" src="../../public/telogo.png"/><router-link style="color: #00ADEE; text-decoration: none;" :to="'/user_posts/' + post.username">{{post.username}}</router-link> - {{post.title}}
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
        <small class="text-muted">Posted {{ post.date_time | moment }}</small>
      </div>
    </div>
  </div>
</template>
<script src="../node_modules/moment/moment.js"></script>

<script>

window.moment = require('moment');
export default {
  name: "home",
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
  filters: {
  moment: function (date) {
    let dateStr = date.year + "-" + date.monthValue + "-" + date.dayOfMonth + " " + date.hour + ":" + date.minute;
    return moment(dateStr, 'YYYY-MM-DD hh:mm').fromNow();
  }
  },
  method: {
    datefrom(date_time){
       return moment(date_time).fromNow()
    }
  },
  created() {
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
  color: #00ADEE;
  
}

h1 {
  color: blue;
  text-align: center;
  
}

h4 {
  color:green;
  text-align: left;
  
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
    width: 600px;
  }
}
</style>

