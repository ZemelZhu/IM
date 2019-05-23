<template>
  <div>
    <navbar></navbar>
    <br>
    <br>
    <br>
    <!--<noteList></noteList>-->
    <router-view></router-view>
  </div>
</template>

<script>
  import navbar from './page/navbar'
  export default {

    components: {
      navbar
    }

  }
</script>

<style scoped>

</style>
