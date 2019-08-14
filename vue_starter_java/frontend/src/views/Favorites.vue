<template>
  <div class="home">
    <div class="post card" style="margin-bottom: 20px; padding:0px;" v-for="post in filteredPost" v-bind:key="post.post_id">
      <div class="card-header">
        <img style="width: 32px; margin-right: 10px;" src="../../public/telogo.png"/><router-link style="color: #00ADEE; text-decoration: none;" :to="'/user_posts/' + post.username">{{post.username}}</router-link> - {{post.title}}
        <i class="fas fa-times-circle"></i>
      </div>
      <router-link v-bind:to="'/detail/post_id/' + post.post_id">
      <img class="card-img-center" v-bind:src ="post.img_url" alt='img' >
      </router-link>
      <div class="card-text" style="padding-left: 10px;">
      </div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item" v-if="post.comments.length != 0"><span>{{post.comments[0].username}}</span>: {{post.comments[0].comment}}</li>
      </ul>
      <div class="card-footer">
        <small class="text-muted">Posted {{ post.date_time | moment }}</small>
        <span v-if="isLoggedIn">
        <i v-on:click.prevent="toggleLike(post.post_id,$event)" :class="{'fas fa-heart' : post.liked, 'far fa-heart' : !post.liked}"></i><span class="badge badge-light">{{post.numberOfLikes}}</span>
        <i v-on:click.prevent="toggleFavorite(post.post_id,$event)" class="far fa-star" :class="{'fas fa-star' : post.favorited, 'far fa-star' : !post.favorited}"></i>
        </span>
      </div>
    </div>
  </div>
</template>
<script src="../node_modules/moment/moment.js"></script>

<script>
import auth from '../auth';
window.moment = require('moment');
export default {
  name: "home",
  data() {
    return {
      postAPI: "http://localhost:8080/tegram/post",
      posts: [],
      userSrch: '',
      isLoggedIn: false,
      isAdmin: false
    };
  },
  computed: {
    filteredPost(vm){
      return vm.posts.filter((pst) => {
        return pst.favorited;
      });
    }
  },
  filters: {
  moment: function (date) {
    let dateStr = date.year + "-" + date.monthValue + "-" + date.dayOfMonth + " " + date.hour + ":" + date.minute;
    return moment(dateStr, 'YYYY-MM-DD hh:mm').fromNow();
  }
  },
  methods: {
    toggleLike(post_id, event){
      const arrIndex = this.posts.findIndex((item) => item.post_id == post_id);
      if(!this.posts[arrIndex].liked){
        fetch(this.postAPI+"/addlike", { 
          method: "POST",
          headers: {
            Authorization: "Bearer " + auth.getToken(),
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.posts[arrIndex].post_id)
         })
        .then(response => {
          if (response.ok) {
            this.posts[arrIndex].liked = !this.posts[arrIndex].liked;
            this.posts[arrIndex].numberOfLikes++;
            const likeIcon = event.target
            likeIcon.classList.remove('far');
            likeIcon.classList.add('fas');
          }
        })
        .catch(err => {
          console.log(err);
        });
       } else {
         fetch(this.postAPI+"/deletelike", { 
          method: "DELETE",
          headers: {
            Authorization: "Bearer " + auth.getToken(),
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.posts[arrIndex].post_id)
         })
        .then(response => {
          if (response.ok) {
            this.posts[arrIndex].liked = !this.posts[arrIndex].liked;
            this.posts[arrIndex].numberOfLikes--;
            const likeIcon = event.target
            likeIcon.classList.remove('fas');
            likeIcon.classList.add('far');
          }
        })
        .catch(err => {
          console.log(err);
        });
       }
    },
    toggleFavorite(post_id, event){
      const arrIndex = this.posts.findIndex((item) => item.post_id == post_id);
      if(!this.posts[arrIndex].favorited){
        fetch(this.postAPI+"/addfavorite", { 
          method: "POST",
          headers: {
            Authorization: "Bearer " + auth.getToken(),
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.posts[arrIndex].post_id)
         })
        .then(response => {
          if (response.ok) {
            this.posts[arrIndex].favorited = !this.posts[arrIndex].favorited;
            const favoriteIcon = event.target
            favoriteIcon.classList.remove('far');
            favoriteIcon.classList.add('fas');
          }
        })
        .catch(err => {
          console.log(err);
        });
       } else {
         fetch(this.postAPI+"/deletefavorite", { 
          method: "DELETE",
          headers: {
            Authorization: "Bearer " + auth.getToken(),
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.posts[arrIndex].post_id)
         })
        .then(response => {
          if (response.ok) {
            this.posts[arrIndex].favorited = !this.posts[arrIndex].favorited;
            const favoriteIcon = event.target
            favoriteIcon.classList.remove('fas');
            favoriteIcon.classList.add('far');
          }
        })
        .catch(err => {
          console.log(err);
        });
       }
    }
  },
  created() {
    if(auth.getUser()){
      this.isLoggedIn = true;
      this.isAdmin = (this.isLoggedIn.rol === "admin") ? true: false;

      fetch(this.postAPI+"/allpostsauth", { 
          method: "GET",
          headers: {
            Authorization: "Bearer " + auth.getToken(),
            "Content-Type": "application/json"
          }})
      .then((response) => {
        return response.json();
      })
      .then((posts) => {
        this.posts = posts;
      })
      .catch((err) => console.error(err));
    } else {
      fetch(this.postAPI+"/allposts")
      .then((response) => {
        return response.json();
      })
      .then((posts) => {
        this.posts = posts;
      })
      .catch((err) => console.error(err));
    }
  }
};
</script>

<style>
.fa-star{
  margin-left: 20px;
}
.fa-heart{
  margin-right: 2px;
}
.fa-times-circle{
  color:red;
  float: right;
}
.fas.fa-heart{
  color:red;
}
.fas.fa-star{
  color:rgb(254, 216, 0);
  
}
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

div.card-footer{
  display: flex;
  justify-content: space-between;
}

div.card-footer > i{
  
}

@media only screen and (max-width: 600px){
  img {
    object-fit: cover;
    width: 100%;
   
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

