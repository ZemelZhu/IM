<template>
<div>

  <userCommentDetil v-for="com in comment" :key="com.id" :comment="com" :user="user">

  </userCommentDetil>
</div>
</template>

<script>
  import userCommentDetil from './userCommentDetil'
    export default {
      data() {
        return {
          comment:[]
        }
      },
      props: ['user'],
      components:{
        userCommentDetil
      },
      mounted() {

        let lid = this.$route.params.id;
        this.axios.post("/Blog/getCommentByUserId",{
          userId:lid
        }).then(response=>{
          this.comment = response.data;
        });
      }
    }
</script>

<style scoped>

</style>
