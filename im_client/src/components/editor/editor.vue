<template>
  <div>

    <el-row>
      <el-col :xs="1" :sm="1" :md="6" :lg="6" :xl="6">
        <div><p></p></div>
      </el-col>
      <el-col :xs="22" :sm="22" :md="12" :lg="12" :xl="12">
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
      </el-col>
    </el-row>


  </div>
</template>

<script>
  import editor from './Quilleditor.vue'

  export default {

    data() {
      return {
        canCrop: false,
        /*测试上传图片的接口，返回结构为{url:''}*/
        uploadUrl: 'http://127.0.0.1:8081/Blog/FileUpload/upload',
        content: '',
        title: ''
      }
    },
    beforeCreate: function () {
      // this.axios.post('/Blog/isOrNotLogin')
      //   .then((response)=>{
      //         if(response.data!="Login") {
      //           this.$message({
      //             message: '警告:请先登录',
      //             type: 'warning'
      //           });
      //           this.$router.push({path: '/sign/in'});
      //         }
      //   });

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
          this.axios.post('/Blog/Article/newArticle', {
            content: content,
            title: this.title

          }).then((response) => {
            if(response!="") {
              this.$message({
                message: '提交成功',
                type: 'success'
              });
              this.$router.push({path:'/chat/article/'+response.data});
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
    /*margin-left: 100px;*/
    /*font-size: 40px;*/

  }

  .submit {
    margin-top: 1%;
    margin-left: 40%;
    width: 20%;
  }

  .item {
    margin: 4px;
  }
</style>
