<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
    /*a:link { text-decoration: none;color: black}*/
    /*　　 a:active { text-decoration:blink}*/
    /*　　 a:hover { text-decoration:none;color: red}*/
    /*　　 a:visited { text-decoration: none;color: green}*/
  a:link {
    font-size: 12px;
    color: #000000;
    text-decoration: none;
  }
  a:visited {
    font-size: 12px;
    color: #000000;
    text-decoration: none;
  }
  a:hover {
    font-size: 12px;
    color: #999999;
    text-decoration: underline;
  }
  #app{
    font-family: Microsoft YaHei;

  }

</style>
