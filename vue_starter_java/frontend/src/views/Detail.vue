<template>
  <div class="detail">
    <div class="detailpost">
    <router-link to="/"></router-link>
    <h4>{{post.title}}</h4>
    <img v-bind:src ="post.img_url" alt='img'>
    <span> Comments </span>
   <p>{{post.date_time.monthValue + "/" + post.date_time.dayOfMonth + "/" + post.date_time.year + " " + post.date_time.hour + ":" + post.date_time.minute}}</p>
    <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: "detail",
  data() {
    return {
      postAPI: "http://localhost:8080/capstone/api/post/",
      post: {}
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
  
}

div.detail > div.detailpost > p{
  text-align: left;
  
}

div.detail > div.detailpost{
  
  border: 5px solid black;
  
  
}

div.detailpost > img {
  align-content: center;
  
  
}

@media only screen and (max-width: 600px){
  div.detailpost > img {
    object-fit: cover;
    width: 100%;
   
  }
}

@media only screen and (min-width: 600px){
  div.detailpost > img {
    object-fit: cover;
    width: 100%;
  }
}

@media only screen and (min-width: 768px){
  div.detailpost > img {
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
  div.detailpost {
    display:grid;
    grid-template-columns: 0.5fr 4fr 1.5fr 0.5fr;
    grid-template-areas: 
    ". title . ."
    ". img comments ."
    ". img comments ."
    ". date . .";
    grid-gap: 5px;
    
  }
   div.detailpost > img {
    height: 650px;
    width: 100%;
    grid-area: img;
  }

  div.detailpost > span {
    grid-area: comments;
  }

  h4{
    font-size: 2rem;
  }

  p{
    font-size: 1.5rem;
    grid-area: date;
  }
}

@media only screen and (min-width: 1200px){
  div.detail > img {
    
    object-fit: cover;
    width: 50%;
    
  }

  div.detail {
    display: flex;
    justify-content: space-around;
  }

  div.detail > div.detailpost{
    width: 750px;
    
  }
}


</style>