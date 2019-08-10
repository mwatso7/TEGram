<template>
  <div class="home">
    <div v-for="post in posts" v-bind:key="post.post_id" class="post">
      <b>{{post.username}}</b> <h4>{{post.title}}</h4>
      <router-link v-bind:to="'/detail/post_id/' + post.post_id">
      <img v-bind:src ="post.img_url" alt='img' >
      </router-link>
    <span v-for="tag in post.tags" v-bind:key="tag">
      {{tag}}
    </span>  
      <p v-if="post.comments.length != 0"><span>{{post.comments[0].username}}</span>: {{post.comments[0].comment}}</p>
      <p>{{post.date_time.monthValue + "/" + post.date_time.dayOfMonth + "/" + post.date_time.year + " " + post.date_time.hour + ":" + post.date_time.minute}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      postAPI: "http://localhost:8080/tegram/post/allposts",
      posts: []
    };
  },
  method: {

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
  }
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

