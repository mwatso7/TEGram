<template>
  <div class="detail">
    <div class="detailpost">
    <router-link to="/"></router-link>
    <h4>{{post.title}}</h4>
    <img v-bind:src ="post.img_url" alt='img'>
    <p>{{post.date_time.monthValue + "/" + post.date_time.dayOfMonth + "/" + post.date_time.year + " " + post.date_time.hour + ":" + post.date_time.minute}}</p>
    <div><p v-for="comment in comments" v-bind:key="comment.id">{{comment.user_name}}: {{comment.comment}}</p></div>
    <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: "detail",
  data() {
    return {
      postAPI: "http://localhost:8080/capstone/api/post/single_post/",
      commentAPI: "http://localhost:8080/capstone/api/comment/all/",
      post: {},
      comments: []
    }
  },

created() {
    // load the reviews
    fetch(this.postAPI+this.$route.params.post_id)
      .then((response) => {
        return response.json();
      })
      .then((post) => {
        this.post = post;
      })
      .catch((err) => console.error(err));

      fetch(this.commentAPI+this.$route.params.post_id)
      .then((response) => {
        return response.json();
      })
      .then((comments) => {
        this.comments = comments;
      })
      .catch((err) => console.error(err));
  }

}
</script>




<style>

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
  margin-top: 100px;
  
}

div.detail > div.detailpost > p{
  text-align: left;
  
}

div.detailpost > img {
  align-content: center;
}

div.detailpost > div {
    padding:5px;
    background-color:  rgba(60, 74, 80, 0.13);
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
  
  div.detailpost {
    display:grid;
    grid-template-columns: 3fr 1fr;
    grid-template-areas: 
    "title ."
    "img comments"
    "date .";
    grid-gap: 0px;
    width: 70%;
    min-width: 900px;
    border: 2px solid rgba(60, 74, 80, 0.13);
    
  }
  
   div.detailpost > img {
    grid-area: img;
    object-fit: cover;

  }

  div.detailpost > div{
    grid-area: comments;
  }

  h4{
    padding:0px;
    margin:0px;
    font-size: 2rem;
    grid-area: title;
  }

  p{
    font-size: 1.5rem;
    grid-area: date;
  }
}



</style>