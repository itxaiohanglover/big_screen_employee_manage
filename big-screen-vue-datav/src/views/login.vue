<template>
  <div class="login-container">

    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on"
      label-position="left">
      <!-- 头像区域 -->
      <div class="avatar-box">
        <img src="../assets/logo.png">
      </div>

      <div class="title-container">
        <h3 class="title">数智员工考勤系统</h3>
      </div>

      <el-form-item prop="username">
        <el-input ref="username" v-model="loginForm.username" placeholder="Username" name="username" type="text"
          tabindex="1" auto-complete="on" />
      </el-form-item>

      <el-form-item prop="password">
        <el-input :key="passwordType" ref="password" v-model="loginForm.password" :type="passwordType"
          placeholder="Password" name="password" tabindex="2" auto-complete="on" @keyup.enter.native="handleLogin" />
      </el-form-item>

      <div>
        <el-button type="primary" style="width:100%;margin-bottom:20px;" @click.native.prevent="handleLogin">登录
        </el-button>
      </div>
    </el-form>
  </div>
</template>
 
<script>

export default {
  name: 'Login',
  data() {
    // 校验用户名
    const validateUsername = (rule, value, callback) => {
      if (value.length == 0) {
        callback(new Error('用户名不能为空！'))
      } else {
        callback()
      }
    }
    // 校验密码
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码最少为6位字符！'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      // 登录规则
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password'
    }
  },
  methods: {
    // 登录业务
    handleLogin() {
      this.$http({
        url: this.$http.adornUrl('/login'),
        method: 'post',
        data: this.loginForm
      }).then(({ data }) => {
        if (data && data.code === 200) {
          this.$message({
            message: '登录成功',
            type: 'success'
          });
          localStorage.setItem("login", "true")
          // 跳转页面
          this.$router.push('/')
        } else {
          this.$message.error(data.msg);
        }
      })
    }
  }
}
</script>
 
<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

.login-container {
  min-height: 100%;
  width: 100%;
  overflow: hidden;
  background: url(~@/assets/pageBg.png);
  background-size: 100% 100%;

  .el-input {
    display: inline-block;
    height: 47px;
    width: 100%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  // 头像
  .avatar-box {
    margin: 0 auto;
    width: 120px;
    height: 120px;
    border-radius: 50%;
    border: 1px solid #409eff;
    box-shadow: 0 0 10px #409eff;
    position: relative;
    bottom: 20px;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
    }
  }

  // 登录表单
  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 30px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: 500;
    }
  }
}
</style>