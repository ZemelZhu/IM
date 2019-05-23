<template>
  <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="50px" class="demo-ruleForm">
    <el-form-item label="账号" prop="number">
      <el-input v-model.number="ruleForm2.number" placeholder="只能为数字"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="pass">
      <el-input type="password" v-model="ruleForm2.pass" auto-complete="off"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="warning" class="login" @click="submitForm('ruleForm2')" plain>
        <i class="el-icon-upload el-icon--left"></i>
        登陆</el-button>
    </el-form-item>

  </el-form>
</template>

<script>
  export default {
    data() {
      var checkNumber = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('账号不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value) || value < 0) {
            callback(new Error('不合法'));
          } else {
            if (value.toString().length > 9) {
              callback(new Error('长度必须小于10位'));
            }
            else {
              this.axios.post('/Blog/Login/checkNumber', {
                number: value
              })
                .then((response) => {
                  if (response.data == "success")
                    callback();
                  else
                    callback(new Error('该账号不存在'));
                }).catch((error) => {
                this.$router.push({path: '/error'});
              });
            }
          }
        }, 300);
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          callback();
        }
      };
      return {
        ruleForm2: {
          pass: '',
          number: ''
        },
        rules2: {
          pass: [
            {validator: validatePass, trigger: 'blur'}
          ],
          number: [
            {validator: checkNumber, trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios.post('/Blog/Login/userLogin', {
              uid: this.ruleForm2.number,
              password: this.ruleForm2.pass
            })
              .then((response) => {
                let status = response.data;
                if(status=="success") {
                  this.$message({
                    message: '登陆成功',
                    type: 'success'
                  });
                  this.$router.push({path: '/chat'});
                }
                else {
                  this.$message({
                    message: '密码错误',
                    type: 'error'
                  });
                  this.$refs.ruleForm2.resetFields();
                }
              }).catch((error) => {
              this.$router.push({path: '/error'});
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
    }
  }
</script>

<style scoped>
  .login {
    width: 100%;
  }
</style>
