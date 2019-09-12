<template>
  <div class="home">
    <b-modal id="delete-modal" ref="modal" title="Delete Post" centered>
      <template slot="modal-header">
        <h5>Delete Post</h5>
      </template>
      <template slot="default">
        <p>Are you sure you want to delete this post?</p>
      </template>
      <template slot="modal-footer" slot-scope="{ cancel }">
        <b-button size="sm" variant="default" @click="cancel()">Cancel</b-button>
        <b-button size="sm" variant="danger" @click="deletePost()">Delete</b-button>
      </template>
    </b-modal> 

    <div class="post card" style="margin-bottom: 20px; padding:0px;" v-for="post in filteredPost" v-bind:key="post.post_id">
      <div class="card-header">
        <img style="width: 32px; margin-right: 10px;" src="../../public/telogo.png"/>{{post.username}} - {{post.title}}
         <b-button v-if="isAdmin" class="float-right" variant="danger" size="sm" v-b-modal.delete-modal @click="dpost=post.post_id">delete</b-button>
      </div>
      <router-link v-bind:to="'/detail/post_id/' + post.post_id">
      <img class="card-img-center" v-bind:src ="post.img_url" alt='img' >
      </router-link>
      <ul class="list-group list-group-flush">
        <li class="list-group-item" v-if="post.comments.length != 0"><router-link style="color: #00ADEE; text-decoration: none;" :to="'/user_posts/' + post.comments[0].username">{{post.comments[0].username}}</router-link>: {{post.comments[0].comment}}</li>
      </ul>
      <div class="card-footer">
        <small class="text-muted">Posted {{ post.date_time | moment }}</small>
        <span>
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
  name: "user-posts",
  data() {
    return {
      postAPI: "http://localhost:8080/tegram/post",
      posts: [],
      dpost: '',
      userSrch: '',
      isLoggedIn: false,
      isAdmin: false
    };
  },
  filters: {
  moment: function (date) {
    let dateStr = date.year + "-" + date.monthValue + "-" + date.dayOfMonth + " " + date.hour + ":" + date.minute;
    return moment(dateStr, 'YYYY-MM-DD hh:mm').fromNow();
  }
  },
  computed: {
    filteredPost(vm){
      return vm.posts.filter((pst) => {
        return (pst.username == vm.userSrch || vm.userSrch == '');
      });
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
    this.userSrch = this.$route.params.username;
    if(auth.getUser()){
      this.isLoggedIn = true;
      this.user = auth.getUser();
      this.isAdmin = (auth.getUser().rol == "admin") ? true: false;
    }
    // load the reviews
    fetch(this.postAPI + "/allpostsauth", { 
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