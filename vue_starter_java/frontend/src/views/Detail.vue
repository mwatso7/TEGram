<template>
  <div class="detail">
    <div class="detailpost">
    <router-link to="/"></router-link>
    <h4>Title for post</h4>
    <img src ="https://course_report_production.s3.amazonaws.com/rich/rich_files/rich_files/4763/original/jamie-justin-brian-tech-elevator-veterans.jpg" 
          alt='img'>
    <p>08/05/2019 10:55am</p>
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
      post: Object
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

img.detail {
  align-content: center;
  
  
}

@media only screen and (max-width: 600px){
  img.detail {
    object-fit: cover;
    width: 50%;
   
  }
}

@media only screen and (min-width: 600px){
  img.detail {
    object-fit: cover;
    width: 25%;
  }
}

@media only screen and (min-width: 768px){
  img.detail {
    object-fit: cover;
    width: 25%;
  }

  h4{
    font-size: 1.5rem;
    
  }

  p{
    font-size: 1rem;
  }
}

@media only screen and (min-width: 992px){
   img.detail {
    object-fit: cover;
    width: 25%;
  }

  h4{
    font-size: 2rem;
  }

  p{
    font-size: 1.5rem;
  }

@media only screen and (min-width: 1200px){
  img.detail {
    
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
}


</style>