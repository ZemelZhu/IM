<template>
  <div>


        <el-tooltip class="item" effect="light" content="最大长度为20" placement="top">
          <el-input maxlength="20"
                    placeholder="请输入标题"
                    v-model="title">
            <i slot="prefix" class="el-input__icon el-icon-edit-outline"></i>
          </el-input>
        </el-tooltip>


        <editor ref="myTextEditor"
                class="editor"
                :fileName="'myFile'"

                :uploadUrl="uploadUrl"
                v-model="content"></editor>

        <!--<div v-text="content"></div>-->
        <el-button @click="submit" type="primary" round class="submit">发布</el-button>
        <br>



  </div>
</template>

<script>
  import editor from './QuilleditorTest.vue'

  export default {

    data() {
      return {
        canCrop: false,
        /*测试上传图片的接口，返回结构为{url:''}*/
        uploadUrl: 'http://120.78.164.110:8088/upload',
        content: '',
        title: ''
      }
    },

    methods: {

      submit() {
        let content = this.content;
        let title = this.title;
        if (content == ""||title=="") {
          this.$message({
            message: '警告:内容和标题不能为空',
            type: 'warning'
          });

        }
        else {
          this.axios.post('/Blog/intercepter/newArticle', {
            content: content,
            title: this.title

          }).then((response) => {
            if(response!="") {
              this.$notify({
                title: '发布成功',
                message: '请去我的文章查看',
                position: 'bottom-left',
                showClose:false
              });
              this.$router.go(0);
            }
            else
            {
              this.$message({
                message: '登陆过时,请重新登陆',
                type: 'warning'
              });
            }




          }).catch((error) => {
            this.$router.push({path: '/error'});
          });
        }


      }
    },
    components: {
      editor
    }
  }
</script>

<style scoped>
  .editor {


  }

  .submit {
    margin-top: 1%;
    width: 100%;
  }

  .item {
    margin: 4px;
  }
</style>
