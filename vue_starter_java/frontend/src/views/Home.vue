<template>
  <div class="home">
    <h1>TE-Gram</h1>
    <h4>Title for post</h4>
    <img alt='img'>
    <p>08/05/2019 10:55am</p>
    
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
