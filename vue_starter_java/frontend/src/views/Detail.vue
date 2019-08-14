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
      <label>Comment Box</label>
        <button type="button" class="close" aria-hidden="true">&times;</button>
    </div>
    <div class="actionBox">
        <ul class="commentList">
            <li>
                <div class="commenterImage">
                  <img src="http://placekitten.com/50/50" />
                </div>
                <div class="commentText">
                    <p class="">Hello this is a test comment.</p> <span class="date sub-text">on March 5th, 2014</span>

                </div>
            </li>
            <li>
                <div class="commenterImage">
                  <img src="http://placekitten.com/45/45" />
                </div>
                <div class="commentText">
                    <p class="">Hello this is a test comment and this comment is particularly very long and it goes on and on and on.</p> <span class="date sub-text">on March 5th, 2014</span>

                </div>
            </li>
            <li>
                <div class="commenterImage">
                  <img src="http://placekitten.com/40/40" />
                </div>
                <div class="commentText">
                    <p class="">Hello this is a test comment.</p> <span class="date sub-text">on March 5th, 2014</span>

                </div>
            </li>
            <p v-for="comment in post.comments" v-bind:key="comment.id">{{comment.username}}: {{comment.comment}}</p>
        </ul>
        
    </div>
    
    </div>
        <form class="form-inline" role="form">
            <div class="form-group">
                <input class="form-control" type="text" placeholder="Your comments" />
            </div>
            <div class="form-group">
                <button class="btn btn-default">Add</button>
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
      postAPI: "http://localhost:8080/tegram/post/single_post/",
      //commentAPI: "http://localhost:8080/tegram/comment/all/",
      post: {},
      //comments: []
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
    let postURL = this.postAPI+this.$route.params.post_id;
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
    border:1px solid #bbb;
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
    display:table-cell;
}
.commenterImage {
    width:30px;
    margin-right:5px;
    height:100%;
    float:left;
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