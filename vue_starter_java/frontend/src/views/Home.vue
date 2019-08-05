<template>
  <div class="home">
    <div class="post">
    <h4>Title for post</h4>
    <img src ="https://course_report_production.s3.amazonaws.com/rich/rich_files/rich_files/4763/original/jamie-justin-brian-tech-elevator-veterans.jpg" 
          alt='img'>
    <p>08/05/2019 10:55am</p>
    </div>
  </div>
</template>

<script>
import moment from 'moment'
export default {
  name: 'home',
  
 data() {
   return {
      dating: dating.filter('formatDate', function(value) {
      if (value) {
      return moment(String(value)).format('MM/DD/YYYY hh:mm')
    }
})
   }
 },
 methods: {

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

div.home > div.post > p{
  text-align: left;
  
}

div.home > div.post{
  
  border: 5px solid black;
  
  
}

img {
  align-content: center;
  
  
}

@media only screen and (max-width: 600px){
  img {
    object-fit: cover;
    width: 100%;
   
  }
}

@media only screen and (min-width: 600px){
  img {
    object-fit: cover;
    width: 100%;
  }
}

@media only screen and (min-width: 768px){
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

@media only screen and (min-width: 1200px){
  img {
    
    object-fit: cover;
    width: 100%;
    
  }

  div.home {
    display: flex;
    justify-content: space-around;
  }

  div.home > div.post{
    width: 1000px;
  }
}
}


</style>
