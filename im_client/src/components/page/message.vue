<template>
    <el-row>
      <el-col :xs="0" :sm="6" :md="6" :lg="8" :xl="8">
        <p></p>
      </el-col>
      <el-col :xs="24" :sm="14" :md="12" :lg="9" :xl="7">
        <el-card class="box-card">
    <el-upload
      class="avatar-uploader myavatar"
      :action="url"
      name="myFile"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload">
      <img v-if="userMessage.avatar" :src="userMessage.avatar" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>

    <el-form
             label-position="left"
             ref="numberValidateForm"
             label-width="70px" class="el-form">
      <el-form-item label="账号">
        <el-alert

          type="success"
          :closable="false">
          {{userMessage.uid}}
        </el-alert>


      </el-form-item>
      <el-form-item label="邮箱">
        <el-alert

          type="warning"
          :closable="false">
          {{userMessage.email}}
        </el-alert>

      </el-form-item>
      <el-form-item label="昵称">

        <el-input v-model="userMessage.name" maxlength="10"></el-input>
      </el-form-item>
      <el-form-item label="个人简介" >
        <el-input v-model="userMessage.state"
          type="textarea" placeholder="你还没有个人简介..."
                  maxlength="100"
                  :autosize="{ minRows: 2, maxRows: 30}"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="success" class="submit" round @click="submitForm">保存修改</el-button>
      </el-form-item>
    </el-form>
        </el-card>
      </el-col>
    </el-row>
</template>
<style>

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .submit {
    width: 100%;
  }
</style>
<style scoped>

  .el-form {
    /*width: 50%;*/
  }
  .myavatar{
    margin-left: 30%;
    /*margin-right: auto;*/

  }
  .box-card {
    margin-top: 5%;
  }
</style>
<script>
  import global_variale from '@/api/global_variale'
  export default {
    data() {
      return {
        userMessage:{
          uid:'',
          avatar: '',
          name:'',
          state:'',
          email:''
        },
        url:global_variale.fireUrl
      };
    },
    mounted(){
      this.axios.post('/Blog/Login/intercepter/getLogin')
        .then((response) => {
          this.userMessage = response.data;
      }).catch((error) => {
        // this.$router.push({path: '/error'});
      });
    },
    methods: {

      submitForm() {
        this.axios.post('/Blog/User/modifyUserMessage',this.userMessage)
          .then((response) => {
            this.$message({
              message: '修改成功',
              type: 'success'
            });
          });
      },

      handleAvatarSuccess(res, file) {
        // alert(file.raw);
        // console.log("==>>"+JSON.stringify(res)+"==>"+JSON.stringify(file));
        console.log("=>"+res.url);
        this.userMessage.avatar = res.url;
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
  }
</script>
