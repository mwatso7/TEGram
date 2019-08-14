<template>
  <div class="detail">
    <div class="card">
      <div class="card-header">
      <img style="width: 32px; margin-right: 10px;" src="../../public/telogo.png"/><router-link style="color: #00ADEE; text-decoration: none;" :to="'/user_posts/' + post.username">{{post.username}}</router-link> - {{post.title}}
     </div>
     <div class="card-block">
            <div class="row" style="width:100%; margin:0px;">
                <div class="nopad col-lg-8">
      <img class="card-img-center" v-bind:src ="post.img_url" alt='img'>
                </div>
                <div class="bpad col-lg-4" >
                  <div class="detailBox">
    <div class="titleBox">
      <label>Comments</label>
    </div>
    <div class="actionBox">
        <ul class="commentList">
            <li v-for="aComment in post.comments" v-bind:key="aComment.id">
                <div class="commenterImage">
                  <router-link style="color: #00ADEE; text-decoration: none;" :to="'/user_posts/' + aComment.username">{{aComment.username}}</router-link>
                  <!-- <img src="http://placekitten.com/50/50" /> -->
                </div>
                <div class="commentText">
                    <p class="">{{aComment.comment}}</p> <span class="date sub-text">{{ aComment.date_time | moment }}</span>

                </div>
            </li>
        </ul>
        
    </div>
    
    </div>
        <form class="form-inline" role="form" v-on:submit.prevent="addComment">
            <div class="form-group">
                <input
                  class="form-control"
                  type="text"
                  name="comment"
                  id="comment"
                  v-model="comment.comment"
                  autocomplete="off"
                  placeholder="Add a comment..."
            />
            </div>
            <div class="form-group">
                <button v-bind:disabled="!canAdd" class="btn btn-default">Add</button>
            </div>
        </form>
                </div>
                
    </div>
    </div>
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
window.moment = require('moment');
import auth from '../auth'

export default {
  name: "detail",
  data() {
    return {
      postAPI: "http://localhost:8080/tegram/post",
      post: {},
      comment: {
        comment_id: "",
        post_id: "",
        username: "",
        comment: ""
      }
    }
  },
  computed: {
    canAdd() {
      return this.comment.comment;
    }
  },
  methods: {
    addComment(){
      fetch(`${this.postAPI}/addcomment`, {
        method: "POST",
        headers: {
          Authorization: "Bearer " + auth.getToken(),
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.comment)
      })
        .then(response => {
          if (response.ok) {
            this.$router.push("/");
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    toggleLike(post_id, event){
      if(!this.post.liked){
        fetch(this.postAPI+"/addlike", { 
          method: "POST",
          headers: {
            Authorization: "Bearer " + auth.getToken(),
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.post.post_id)
         })
        .then(response => {
          if (response.ok) {
            this.post.liked = !this.post.liked;
            this.post.numberOfLikes++;
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
          body: JSON.stringify(this.post.post_id)
         })
        .then(response => {
          if (response.ok) {
            this.post.liked = !this.post.liked;
            this.post.numberOfLikes--;
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
      if(!this.post.favorited){
        fetch(this.postAPI+"/addfavorite", { 
          method: "POST",
          headers: {
            Authorization: "Bearer " + auth.getToken(),
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.post.post_id)
         })
        .then(response => {
          if (response.ok) {
            this.post.favorited = !this.post.favorited;
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
          body: JSON.stringify(this.post.post_id)
         })
        .then(response => {
          if (response.ok) {
            this.post.favorited = !this.post.favorited;
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
  filters: {
    moment: function (date) {
      let dateStr = date.year + "-" + date.monthValue + "-" + date.dayOfMonth + " " + date.hour + ":" + date.minute;
      return moment(dateStr, 'YYYY-MM-DD hh:mm').fromNow();
    }
  },
  created() {
    // load the reviews
    console.log(auth.getToken());
    let postURL = this.postAPI+ "/single_post/" +this.$route.params.post_id;
    fetch(postURL, {
      method: 'GET',
      headers: {
        Authorization: `Bearer ${auth.getToken()}`
      },
    })
      .then((response) => {
        return response.json();
      })
      .then((post) => {
        this.post = post;
        this.comment.post_id = post.post_id;
      })
      .catch((err) => console.error(err));

      /* fetch(this.commentAPI+this.$route.params.post_id)
      .then((response) => {
        return response.json();
      })
      .then((comments) => {
        this.comments = comments;
      })
      .catch((err) => console.error(err)); */
  }

}
</script>




<style>
.nopad{
  padding:0px;
}
.bpad{
  padding:0px 0px 55px 0px;
}

.detailBox {
    width:100%;
    height: 100%;

    margin:0px;
}
.titleBox {
    background-color:#fdfdfd;
    padding:10px;
}
.titleBox label{
  color:#444;
  margin:0;
  display:inline-block;
}

.commentBox {
    padding:10px;
    border-top:1px dotted #bbb;
}
.commentBox .form-group:first-child, .actionBox .form-group:first-child {
    width:85%;
}
.commentBox .form-group:nth-child(2), .actionBox .form-group:nth-child(2) {
    width:20%;
}
.actionBox .form-group * {
    width:100%;
}
.form-inline {
 padding: 8px 20px 8px 20px;
 width: 100%;
}
.taskDescription {
    margin-top:10px 0;
}
.commentList {
    padding:0;
    list-style:none;
    height: 100%;
    overflow:auto;
}
.commentList li {
    margin:0;
    margin-top:10px;
}
.commentList li > div {
    
}
.commenterImage {
    width:70px;
    margin-right:5px;
    height:100%;

}
.commenterImage img {
    width:100%;
    border-radius:50%;
}
.commentText p {
    margin:0;
}
.sub-text {
    color:#aaa;
    font-family:verdana;
    font-size:11px;
}
.actionBox {
    border-top:1px dotted #bbb;
    padding:10px;
    height: 100%;
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
  text-align: center;
  width:100%;
  position: fixed;
  top: 0;
  
}

div.detail{
  margin-top: 130px;
  margin-bottom: 30px;
  
}

div.detail > div.detailpost > p{
  text-align: left;
  
}

div.detailpost > img {
  align-content: center;
}



@media only screen and (min-width: 600px){
  div.detailpost > img {
    object-fit: cover;
    width: 100%;
  }
}



@media only screen and (min-width: 992px){
  div.detail {
    display: flex;
    justify-content: space-around;
    
  }
  
   div.detailpost > img {
    grid-area: img;
    object-fit: cover;

  }
.card{
  width:900px;
}
  h4{
    padding:0px;
    margin:0px;
    font-size: 2rem;
    grid-area: title;
  }
}



</style>