<template>
  <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="70px" class="demo-ruleForm">
    <el-form-item label="账号" prop="number">
      <el-input type="text" v-model.number="ruleForm2.number" placeholder="只能为数字"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="pass">
      <el-input type="password" v-model="ruleForm2.pass" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="ruleForm2.email" ></el-input>
    </el-form-item>
    <el-form-item label="验证码" v-show="isShow" prop="verificationCode">
      <el-input v-model="ruleForm2.verificationCode" >
        <el-button slot="append" icon="el-icon-message" @click="sendCode"
                   :disabled="limitTimeShow"
        >
          获取{{limitTime}}
        </el-button>
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="info" class="random" @click="random">随机生成账号</el-button>
    </el-form-item>
    <div class="submitAndreset login">
      <el-button type="success" @click="submitForm('ruleForm2')"><i class="el-icon-upload el-icon--left"></i>提交
      </el-button>
      <el-button type="danger" icon="el-icon-delete" @click="resetForm('ruleForm2')">重置</el-button>
    </div>
  </el-form>
</template>

<script>
  export default {
    data() {
      var checkEmail = (rule, value, callback) => {
        setTimeout(() => {
          this.axios.post('/Blog/Login/checkEmail', {
            email: value
          })
            .then((response) => {
              if (response.data == "success")
                callback(new Error('该邮箱已经存在'));
              else
              {
                this.isShow = true;
                callback();
              }
            });
        }, 300);
      };
      var checkNumber = (rule, value, callback) => {
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
                    callback(new Error('该账号已经存在'));
                  else
                    callback();
                });
            }
          }
        }, 300);
      };
      var validatePass = (rule, value, callback) => {
        if (this.ruleForm2.checkPass !== '') {
          this.$refs.ruleForm2.validateField('checkPass');
        }
        callback();
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm2.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        limitTimeShow:false,
        limitTime:'',
        isShow:false,
        ruleForm2: {
          pass: '',
          checkPass: '',
          number: '',
          email:'',
          verificationCode:''
        },
        rules2: {
          pass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' },
            {validator: validatePass, trigger: 'blur'}
          ],
          checkPass: [
            { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' },
            {validator: validatePass2, trigger: 'blur'}
          ],
          number: [
            { required: true, message: '请输入账号', trigger: 'blur' },
            {validator: checkNumber, trigger: 'blur'}
          ],
          email: [
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' },
            {validator: checkEmail, trigger: 'blur'}
          ],
          verificationCode:[
            { required: true, message: '请输入验证码', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      sendCode(){
        this.axios.post("/Blog/Login/getVerificationCode",
          {
            emailTo: this.ruleForm2.email
          }).then(response=>{
          this.$message({
            message: response.data,
            type: 'info'
          });
        });
        let i=0;
        this.limitTime = 100;
        let time=setInterval(() => {
          i++;
          this.limitTime--;
          this.limitTimeShow=true;
          if(this.limitTime==0) {
            this.limitTime='';
            this.limitTimeShow=false;
            clearInterval(time);
          }
        },1000);
      },
      random() {
        this.axios.post('/Blog/Login/generateUserId')
          .then((response) => {
            this.ruleForm2.number = response.data;
            this.$refs.ruleForm2.validateField('number');
          }).catch((error) => {
          this.$router.push({path: '/error'});
        });
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios.post('/Blog/Login/register', this.ruleForm2)
              .then((response) => {
                this.$message({
                  message: response.data,
                  type: 'info'
                });
                if(response.data=="注册成功") {
                  let path = this.$route.fullPath;
                  if(path=="/signByPhone/up") {
                    this.$router.push({path: '/signByPhone/in'});
                  }
                  else {
                    this.$router.push({path: '/sign/in'});
                  }
                }
              });
          } else {
            // this.$message({
            //   message: "提交出错",
            //   type: 'error'
            // });
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped>
  .random {
    width: 100%;
  }
  .login {
    margin-bottom: 3%;
    margin-left: 30%;
  }
</style>
