<template>
    <div>
      <el-form
        label-position="left"
        ref="numberValidateForm"
        label-width="70px" class="el-form">
        <el-form-item label="头像">
          <img v-if="user.avatar" :src="user.avatar" class="authorAvatar">
        </el-form-item>
        <el-form-item label="账号">
          <el-alert

            type="success"
            :closable="false">
            {{user.uid}}
          </el-alert>


        </el-form-item>
        <el-form-item label="邮箱">
          <el-alert

            type="warning"
            :closable="false">
            {{user.email}}
          </el-alert>

        </el-form-item>
        <el-form-item label="昵称">
          <el-alert

            type="info"
            :closable="false">
            {{user.name}}
          </el-alert>
        </el-form-item>
        <el-form-item label="个人简介" >
          <el-alert
            type="error"
            :closable="false">
            {{user.state}}
          </el-alert>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
    export default {
      props: ["user"]
    }
</script>

<style scoped>
  .authorAvatar {
    border-radius: 10%;
    height: 70px;
    width: 75px;
  }
</style>
