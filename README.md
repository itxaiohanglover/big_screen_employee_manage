﻿[TOC]


# 前言
:blush:你好，我是小航，一个正在变秃、变强的文艺倾年。
:bell:本专栏【仗剑天涯】每晚11:30-12:30更新，欢迎大家多多关注

# 需求分析（3min）
| 序号 | 类型     | 名称     | 描述                                                         |
| ---- | -------- | -------- | ------------------------------------------------------------ |
| 1    | 基本功能 | 部门管理 | l 管理员可以维护部门信息  l 部门可以只有一级层次结构         |
| 2    | 基本功能 | 员工信息 | l 管理员录入员工信息(工号、姓名、性别、出生日期、籍贯、学历、毕业院校、专业等)  l 管理员指定员工所属的部门 |
| 3    | 基本功能 | 查询     | l 管理员可以查询部门下所有员工  l 管理员可根据员工工号、姓名等查询员工详细信息 |
| 4    | 扩展功能 | 简历管理 | l 每个员工有一份Word格式的简历，可以存入系统中，并可供下载   |
| 5    | 扩展功能 | 员工照片 | l 每个员工有一张照片，可存入系统中                           |

 根据需求，项目主要分两个模块：部门管理（树形展示）、员工管理（录入信息，指定部门）

# 技术选型（1min）

SpringBoot、MyBatis-plus、Vue、Webpack、DataV、Echarts、OSS

项目环境：jdk8、Vue-cli-2.0、DataV-2.7.3、Echarts-4.6.0、Webpack-4.0、Npm-6.13、Node-v12.16

# 数据库设计（10min）

数据库名：tyut_employee

![在这里插入图片描述](https://img-blog.csdnimg.cn/bf81e32f429e47a59cd5c20d69950329.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/d106a73f5619427a9eb43c19986f5eae.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/7f172ba192f646c8805a34b21a69e450.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/55c8e213d8734bcda82f971e269250a7.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/d156a43d0004471dbacf9d25316fa616.png)

# SpringBoot初始化（10min）
![在这里插入图片描述](https://img-blog.csdnimg.cn/c9121e6a5f954bc29cf6cfc08fcf7e94.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/b329f81421f3410b842b0bb0b9ad49de.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/add1a2e381794e2da22ca74580cd052e.png)
先暂且装两个依赖：
![在这里插入图片描述](https://img-blog.csdnimg.cn/aa02df37ed5f4afa95f7acb108dde4aa.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/ca223f2dfa0144758d0a4a4aa2edeada.png)
降一降SpringBoot版本，搞搞相关的配置：

![在这里插入图片描述](https://img-blog.csdnimg.cn/de589997e98240ca9ce7a644141afdd4.png)
依赖：
```xml
	<dependencies>
        <!-- commons-lang -->
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>
        <!--        HttpUtils-->
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.13</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpcore</artifactId>
            <version>4.4.5</version>
        </dependency>
        <!-- Mybaits-Plus -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.3.1</version>
        </dependency>
        <!-- mysql驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.17</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

application.yml
```yml
server:
  port: 9999

spring:
  application:
    name: tyut_employee

  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/tyut_employee?useSSL=false&serverTimezone=UTC
    username: root
    password: root
```
引入独家配方：
![在这里插入图片描述](https://img-blog.csdnimg.cn/8fde62ca653b485ba894ac4b245f8793.png)
# 生成基础代码（5min）
这里我们使用人人代码生成器，小航魔改代码生成器还在搞ing
![在这里插入图片描述](https://img-blog.csdnimg.cn/7322c09f466f45dca0036aa6f997b579.png)
别忘了修改数据源：
![在这里插入图片描述](https://img-blog.csdnimg.cn/bb0a0189977249ee9f77fb1e46aa313c.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/ce4740f625ba472bb1f43f3665e814d1.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/bcc5ce994f2d42eaa959a333ab313c2c.png)
排除错后：
![在这里插入图片描述](https://img-blog.csdnimg.cn/ccbd9c2ad7c14d1cbf1f2ac9d7f99017.png)
# 前端大屏初始化（15min）
组件库：
[介绍 | DataV (jiaminghi.com)](http://datav.jiaminghi.com/guide/)
[Element-UI](https://element.eleme.cn/#/zh-CN/component/installation)

```shell
git clone https://gitee.com/MTrun/big-screen-vue-datav.git
cd big-screen-vue-datav 
yarn install 
yarn run serve
```
初始化完成效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/0616aa85705648c6811ee065670f7c10.png)
我们接下来使用Vscode打开
安装需要的依赖：
```shell
yarn add element-ui
yarn add axios
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/5a39ecd7fd6b498887c482a2218c5382.png)
在main.js里面注册插件：
`这里就按需引入了，一引百引叭`
![在这里插入图片描述](https://img-blog.csdnimg.cn/4ec9e866c70f417a8348e80ccfc9aefa.png)
封装Axios（这里封装风格本人不太喜欢，后期会改，这里先应付代码生成器）

httpRequest.js(慢慢再改叭，先直接复制叭)
```js
import axios from 'axios'
import qs from 'qs'
import merge from 'lodash/merge'


const http = axios.create({
  	timeout: 20000,
    baseURL: 'http://localhost:9999',
})


/**
 * 请求地址处理
 * @param {*} actionName action方法名称
 */
http.adornUrl = (actionName) => {
  // 非生产环境 && 开启代理, 接口前缀统一使用[/proxyApi/]前缀做代理拦截!
  return actionName
}

/**
 * get请求参数处理
 * @param {*} params 参数对象
 * @param {*} openDefultParams 是否开启默认参数?
 */
http.adornParams = (params = {}, openDefultParams = true) => {
  var defaults = {
    't': new Date().getTime()
  }
  return openDefultParams ? merge(defaults, params) : params
}

/**
 * post请求数据处理
 * @param {*} data 数据对象
 * @param {*} openDefultdata 是否开启默认数据?
 * @param {*} contentType 数据格式
 *  json: 'application/json; charset=utf-8'
 *  form: 'application/x-www-form-urlencoded; charset=utf-8'
 */
http.adornData = (data = {}, openDefultdata = true, contentType = 'json') => {
  var defaults = {
    't': new Date().getTime()
  }
  data = openDefultdata ? merge(defaults, data) : data
  return contentType === 'json' ? JSON.stringify(data) : qs.stringify(data)
}

export default http
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/a33447944f13495fbc02f010b6c69173.png)
引入刚刚生成的代码：
![在这里插入图片描述](https://img-blog.csdnimg.cn/756030de8e0d407894f8ae6357deddae.png)
这里加一点点美化叭，修改Element-ui主题色为透明：
tables.scss
```css
/*表格样式*/
.el-table{
    background-color: rgba(255,255,255,0);
    color: #FFFFFF;
    border:none;
    &:before,
    *:before{
      height: 0px!important;
    }
    th,tr,td{
      background-color: rgba(255,255,255,0);
    }
    th>.cell{
      color: #24b2bc;
    }
    *{
      border:none;
    }
    .el-table__header{
      tr{
        background-color: rgba(255,255,255,0);
        th{
          border:none;
          color: #fff;
          background-color: rgba(255,255,255,0);
        }
        .is-leaf{
          border-bottom:none;
        }
      }
    }
    .el-table__body{
      tr{
        background-color: rgba(255,255,255,0);
        td{
          border:none;
          button{
            background-color:#245cbc;
            padding:5px;
            color:#fff;
          }
        }
      }
      .el-table__empty-text{
        color: white;
      }
    }
    //修改带操作的表格背景色
    .el-table__row{
      &:hover{
        td{
          background-color: rgba(255,255,255,0) !important;
        }
      }
    }
    //修改固定右边操作的表格背景悬浮色
    .hover-row{
      td{
        background-color: rgba(255,255,255,0) !important;
      }
    }

    
  }

  .el-pagination button:disabled{
    background-color: #0b0f1c;
  }
  .el-button--default{
    background-color:rgb(83, 194, 83);
    color:#fff;
    border-color:green;
  }
  .el-pager li{
    background:none;
    border:1px solid #409EFF;
    border-radius:2px;
    box-shadow: 0 0 5px #409EFF inset;
  }

// 时间选择器样式修改
.el-date-editor .el-range-input, .el-date-editor .el-range-separator{
  background:none;
}

// 弹框样式修改
  .el-dialog{
    background-color:#0b0f1c;
  }
  .el-dialog .el-dialog__body .el-form label{
    color:#fff;
  }
  .el-dialog .el-dialog__header .el-dialog__title{
    color:#fff;
  }
  .el-input__inner{
    background-color:rgba(255, 255, 255, 0);
    border-color:#409EFF;
    box-shadow: 0 0 5px #409EFF inset;
  }


  // 选择框修改
  .el-date-editor .el-range-input, .el-date-editor .el-range-separator{
    background:none;
  }
  .el-form-item{
    margin-left:10px;
  }
  .el-pagination{
    width:420px;
    position:fixed;
    right:0
  }

// 下标选择
  .el-tabs__item{
    color:#a5b0b6;
  }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/92fb843dc627473094b20414e52d7733.png)
# 前端布局（35min）
先设计四个模块：首页、部门管理、员工管理、系统设置
![在这里插入图片描述](https://img-blog.csdnimg.cn/3db6d6e9d36f42f3960a3148ffca3eab.png)
配置路由：

```js
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
  path: '/',
  name: 'index',
  component: () => import('../views/index.vue')
},
{
  path: '/admin',
  name: 'admin',
  component: () => import('../views/employee/admin.vue')
},{
  path: '/dept',
  name: 'dept',
  component: () => import('../views/employee/dept.vue')
},{
  path: '/user',
  name: 'user',
  component: () => import('../views/employee/user.vue')
},{
  path: '/',
  name: 'userdept',
  component: () => import('../views/employee/userdept.vue')
},

]
const router = new VueRouter({
  mode: 'hash',
  routes: routes
})

export default router
```
访问效果：（如果遇到权限问题，请去掉isAuth()后端未整合shiro）
![在这里插入图片描述](https://img-blog.csdnimg.cn/9a1d772ed85b42ae8041fcc617767bda.png)
这样的页面我们是不喜欢的，所以我们新建一个layout布局

![在这里插入图片描述](https://img-blog.csdnimg.cn/49ce7a54be864ef78faae79687a5ca52.png)
重新配置一下路由：
```js
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "layout",
    component: () => import('../layout/index.vue'),
    children: [
      {
        path: '/index',
        name: 'index',
        component: () => import('../views/index.vue')
      },
      {
        path: '/admin',
        name: 'admin',
        component: () => import('../views/employee/admin.vue')
      },
      {
        path: '/dept',
        name: 'dept',
        component: () => import('../views/employee/dept.vue')
      },
      {
        path: '/user',
        name: 'user',
        component: () => import('../views/employee/user.vue')
      },
      {
        path: '/userdept',
        name: 'userdept',
        component: () => import('../views/employee/userdept.vue')
      },
    ]
  },
]
const router = new VueRouter({
  mode: 'hash',
  routes: routes
})

export default router
```
效果展示：（是不是好看多了呢！）
![在这里插入图片描述](https://img-blog.csdnimg.cn/16ee2bc9d6fe4951925d211b484869b8.png)
为了分辨率统一，这里对css略作了修改

![在这里插入图片描述](https://img-blog.csdnimg.cn/95fea7e881ff4057b21e53b03b6cfb9f.png)
# 登录注册实现（30min）
**前端实现：**

新建一个Login.vue

登录注册页面算是基本功吧，（头像 + 标题 + 输入框 + 按钮）
```vue
<template>
  <div class="login-container">
 
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
      <!-- 头像区域 -->
      <div class="avatar-box">
        <img src="../assets/logo.png">
      </div>
 
      <div class="title-container">
        <h3 class="title">智慧员工管理系统</h3>
      </div>
 
      <el-form-item prop="username">
        <el-input ref="username" v-model="loginForm.username" placeholder="Username" name="username" type="text" tabindex="1" auto-complete="on" />
      </el-form-item>
 
      <el-form-item prop="password">
        <el-input :key="passwordType" ref="password" v-model="loginForm.password" :type="passwordType" placeholder="Password" name="password" tabindex="2" auto-complete="on" @keyup.enter.native="handleLogin" />
      </el-form-item>

      <div>
        <el-button type="primary" style="width:100%;margin-bottom:20px;" @click.native.prevent="handleLogin">登录</el-button>
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
```
配置路由：
```js
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [

  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login1.vue')
  },
  {
    path: "/",
    name: "layout",
    component: () => import('../layout/index.vue'),
    children: [
      {
        path: '/index',
        name: 'index',
        component: () => import('../views/index.vue')
      },
      {
        path: '/admin',
        name: 'admin',
        component: () => import('../views/employee/admin.vue')
      },
      {
        path: '/dept',
        name: 'dept',
        component: () => import('../views/employee/dept.vue')
      },
      {
        path: '/user',
        name: 'user',
        component: () => import('../views/employee/user.vue')
      },
      {
        path: '/userdept',
        name: 'userdept',
        component: () => import('../views/employee/userdept.vue')
      },
    ]
  },
]
const router = new VueRouter({
  mode: 'hash',
  routes: routes
})

export default router
```
实现效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/492417d810ec4f9f931613ef648cbbb9.png)
**后端实现：**

首先前后端需要对接，配置一下全局跨域：（具体含义-注释都给出了）

![在这里插入图片描述](https://img-blog.csdnimg.cn/71e948b1f2eb4f5997137750d8bc89b9.png)

```java
package com.tyut.employee.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 全局跨域
 */

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 允许任何头
        corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
```
加入后端校验（`JSR303`）：

1.导入依赖：
```xml
<!-- 属性效验-->
 <dependency>
     <groupId>javax.validation</groupId>
     <artifactId>validation-api</artifactId>
     <version>2.0.1.Final</version>
 </dependency>
```
2.目标Bean标注注解(`AdminEntity表`)
```java
@Data
@TableName("em_admin")
public class AdminEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Integer id;
	/**
	 * 用户名
	 */
	@NotEmpty
	private String username;
	/**
	 * 密码
	 */
	@Length(min = 6, message = "密码至少为6位")
	private String password;

}
```
3.新建LoginController
```java
package com.tyut.employee.controller;

import com.tyut.employee.entity.AdminEntity;
import com.tyut.employee.service.AdminService;
import com.tyut.employee.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author xh
 * @Date 2022/5/25
 */
@RestController
public class LoginController {
    @Autowired
    AdminService adminService;
    @PostMapping("/login")
    public R login(@Valid @RequestBody AdminEntity loginVo, BindingResult bindingResult) {
        // 判断是否验证成功
        if (bindingResult.hasErrors()) {
            return R.error(101, "账号、密码不能为空，且密码必须大于6位");
        }
        AdminEntity adminEntity = adminService.login(loginVo);
        return adminEntity != null ? R.ok().put("adminEntity", adminEntity) : R.error(102, "账号或密码错误");
    }
}
```
编写service、dao层：
```java
@Override
    public AdminEntity login(AdminEntity loginVo) {
        AdminDao adminDao = this.baseMapper;
        // 1.根据用户名查询数据库是否存在账号
        @NotBlank String username = loginVo.getUsername();
        AdminEntity adminEntity = adminDao.selectOne(new QueryWrapper<AdminEntity>().eq("username", username));
        // 2.若存在核对密码
        // TODO 加盐解密
        if(adminEntity != null && adminEntity.getPassword().equals(loginVo.getPassword())) {
            return adminEntity;
        }
        return null;
    }
```
使用Apifox测试接口，并编写接口文档：（Postman、Swagger也可以，这次咱们用个新的）
![在这里插入图片描述](https://img-blog.csdnimg.cn/d7b6974aa03b4161a252b39e825a9798.png)
新建一个快捷请求：
![在这里插入图片描述](https://img-blog.csdnimg.cn/ac4866f5f2f64b508f92742ca5be24e3.png)
测试结果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/c1022a14bf544e9a923b3aef845a39ac.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/cc9d3cd7fa714d3cbfc8112bce97cc53.png)
对接前端：
```js
	// 登录业务
    handleLogin() {
      this.$http({
        url: this.$http.adornUrl('/login'),
        method: 'post',
        data: this.loginForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          console.log("登录成功")
        } else {
          console.log(data.msg)
        }
      })
    }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/adb9b30a6789480592e1a388aeaf6a2a.png)
接下来我们对登录变得对用户友好一点：

前端增加一点点提示信息：

我们打开 [ Element-ui官网](https://element.eleme.cn/) 

![在这里插入图片描述](https://img-blog.csdnimg.cn/b1bba1f128f348159ee0da7479aab9c2.png)
这个不错，就这个辽。

```js
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
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/19410fdf89934fcf871fd6c610fc45e3.png)
我们给数据库增加一条记录：
![在这里插入图片描述](https://img-blog.csdnimg.cn/242f0bf869644afb830ca31537e90ba4.png)
正确输入密码后，成功跳转
![在这里插入图片描述](https://img-blog.csdnimg.cn/9b2ae8a548a141a18cd2600e50c8e0a7.png)
如果用户已登录，就直接跳转到首页叭，这个小活就交给`路由守卫`叭：
注意我们前面在成功登录之后，给浏览器存储了一对key-value（login：true）
```js
import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [

  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login.vue')
  },
  {
    path: "/",
    name: "layout",
    component: () => import('../layout/index.vue'),
    children: [
      {
        path: '/index',
        name: 'index',
        component: () => import('../views/index.vue')
      },
      {
        path: '/admin',
        name: 'admin',
        component: () => import('../views/employee/admin.vue')
      },
      {
        path: '/dept',
        name: 'dept',
        component: () => import('../views/employee/dept.vue')
      },
      {
        path: '/user',
        name: 'user',
        component: () => import('../views/employee/user.vue')
      },
      {
        path: '/userdept',
        name: 'userdept',
        component: () => import('../views/employee/userdept.vue')
      },
    ]
  },


]
const router = new VueRouter({
  mode: 'history', // 去掉url中的#
  routes: routes
})

// 注册一个全局前置守卫
router.beforeEach((to, from, next) => {
  // 登录及注册页面可以直接进入,而主页面需要分情况
  if (to.path == '/login') {
    if (localStorage.login === "true") {
      next('/');  // 用户已登录
    } else {
      next();
    }
  }
  else {
    if (from.path == "/login") // 从登录页面可以直接通过登录进入主页面
    {
      next();
    }
    else {
      // 从/进入,如果登录状态是true，则直接next进入主页面
      if (localStorage.login === "true") {
        next();
      }
      else { // 如果登录状态是false，那么跳转至登录页面,需要登录才能进入主页面
        next('/login');
      }
    }
  }
})
export default router
```
# 表格CRUD实现（45min）
首先我们修改一下layout第二行：(使用router-link替换原来的span，为方便后续拓展更多的子菜单，渲染为列表)
```html
<!-- 第二行 -->
        <div class="d-flex jc-between px-2">
            <div class="d-flex aside-width">
                <div class="react-left ml-4 react-l-s">
                    <span class="react-left"></span>
                    <!-- <span class="text">部门管理</span> -->
                    <router-link to="/dept" class="text" tag="li">
                      <a style="color:#d3d6dd">部门管理</a>
                    </router-link>
                </div>
                <div class="react-left ml-3">
                    <!-- <span class="text">员工管理</span> -->
                    <router-link to="/user" class="text" tag="li">
                      <a style="color:#d3d6dd">员工管理</a>
                    </router-link>
                </div>
            </div>
            <div class="d-flex aside-width">
                <div class="react-right bg-color-blue mr-3">
                    <!-- <span class="text fw-b"></span> -->
                    <router-link to="/admin" class="text fw-b" tag="li">
                      <a style="color:#d3d6dd">系统设置</a>
                    </router-link>
                </div>
                <div class="react-right mr-4 react-l-s">
                    <span class="react-after"></span>
                    <span class="text">{{ dateYear }} {{ dateWeek }} {{ dateDay }}</span>
                </div>
            </div>
        </div>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/771097213ff241bbbe69fa99aa8fcccf.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/1b45a164e10649e8baec743d43ee1649.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/4a0ac24a4ada49d393ba97a9554e3300.png)
当我们点击新增按钮，发现弹窗上面被遮罩层挡住了：
![在这里插入图片描述](https://img-blog.csdnimg.cn/d94c897789b144dba2fb747218e1444d.png)
遇到问题不要慌，我们只需给el-dialog设置`:modal-append-to-body="false"`，使遮罩层插入至 Dialog 的父元素上。

![在这里插入图片描述](https://img-blog.csdnimg.cn/8aae1feed3954494aa0c1a5ae2d7cb94.png)
解决成功，当然大家也可以给position:fixed的父元素设置一个z-index，并且要比遮盖层的大，或者让el-dialog父元素不使用fixed定位。不过小航还是推荐大家使用第一种方法。

后端完善条件查询，根据用户名查询：
AdminServiceImpl
```java
@Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AdminEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)) {
            queryWrapper.like("username", key);
        }
        IPage<AdminEntity> page = this.page(
                new Query<AdminEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }
```
测试前端：
![在这里插入图片描述](https://img-blog.csdnimg.cn/513acd213968400388a5b155f79103a6.png)
于是我们又迎来了一个bug：
![在这里插入图片描述](https://img-blog.csdnimg.cn/b897ce1db30b4347b18b9a6022ba7954.png)
咦，发现点击没反应，这是为啥嘞，我们打开F12，查看报错
![在这里插入图片描述](https://img-blog.csdnimg.cn/0ce29cefa8c74de799ae121b5111a61b.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/8689c38b203f471ba149872cfb36fc93.png)

我们细心一看，原来是请求头不对，应该是application/json才对，知道问题就好办了，设置一下
```js
// 修改POST请求头
http.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8';
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/89a5fc76567c46298842227f4944e3d0.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/58ca7449d0454e58a9ab2b88c118bd3b.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/f47be85485584b8e9e1f14d95fcd82d6.png)
细心的小伙伴们应该发现，右下角没有总页数，这是因为我们Mybatis-Plus配置分页插件

我们打开[Mybatis-plus官网](https://www.mybatis-plus.com/)：

![在这里插入图片描述](https://img-blog.csdnimg.cn/76a9e5eccfff4a5fbffd7d234845ef17.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/b8e58278b853424ab102193ac25bc763.png)
这里我们采用注解的方式，Mybatis-plus我们使用的是旧版本：

![在这里插入图片描述](https://img-blog.csdnimg.cn/a86fb20ce64a4be18f74b985ca82eab9.png)
限制单页数量为100，防止恶意请求。

再次测试：
![在这里插入图片描述](https://img-blog.csdnimg.cn/c936f087662d4771a95e465888c2784a.png)
小作业：完善一下员工管理

需要用到的素材：`https://img-blog.csdnimg.cn/67c592ab8e10478982a404f82610e321.png`
![在这里插入图片描述](https://img-blog.csdnimg.cn/67c592ab8e10478982a404f82610e321.png)
1. 数据库测试
![在这里插入图片描述](https://img-blog.csdnimg.cn/c4a8a80af21446e0bbd33d043175aa7a.png)
2. 优化显示
![在这里插入图片描述](https://img-blog.csdnimg.cn/e5880e4c18b8408fb099ef02133c054e.png)

```html
<el-table-column
        prop="sex"
        header-align="center"
        align="center"
        label="性别（0代表女1代表男，默认男）">
        <template  slot-scope="scope">            
            <el-tag >{{scope.row.sex == 0 ? '女' : '男'}}</el-tag>
        </template>
      </el-table-column>

<el-table-column
        prop="photo"
        header-align="center"
        align="center"
        label="照片">
        <!-- 图片的显示 -->
          <template   slot-scope="scope">            
              <img :src="scope.row.photo"  min-width="70" height="70" />
          </template>
      </el-table-column>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/406af616554d4dab98b6287837bd680c.png)
3. 图片点击预览
 3.1 使用el-image标签
```html
<el-table-column
        prop="photo"
        header-align="center"
        align="center"
        label="照片">
        <!-- 图片的显示 -->
          <template slot-scope="scope">
            <el-image
              class="my-img"
              style="width: 70px; height: 70px"
              ref="myImg"
              :src="scope.row.photo"
              :preview-src-list="srcList"
              @click="handlePriveImg(scope.row)"
            >
            </el-image>
          </template> 
      </el-table-column>
```
3.2 设置srcList数组，图片预览方法
![在这里插入图片描述](https://img-blog.csdnimg.cn/9946356e5d894c87a138178a6f887513.png)
3.3 添加样式
```css
/*使鼠标悬浮在图片上时出现手的形状 */
.my-img:hover{
  cursor:pointer;
}
```
3.4 测试效果
![在这里插入图片描述](https://img-blog.csdnimg.cn/93dbbcbe5d084447b1377d53dec75e59.png)
完善添加功能 + 图片裁剪功能：

```html
// Select 选择器
<el-form-item label="性别" prop="sex">
        <el-select v-model="dataForm.sex" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
// 时间选择器
 <el-form-item label="出生日期" prop="birthday">
        <el-date-picker v-model="dataForm.birthday" type="date" placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/4fac3c7a634847b8b0fabae4c43a4cf7.png)
***
安装vue-cropper
```js
yarn add vue-cropper

import VueCropper from 'vue-cropper'
Vue.use(VueCropper)
```
**自定义上传组件  ImageCropper**

为了方便抒写路径，这里我们引入@别名，代替繁琐的../../
修改`vue.config.js`
```js
const path = require('path')
const resolve = dir => {
  return path.join(__dirname, dir)
}
module.exports = {
  publicPath: './',
  chainWebpack: config => {
    config.resolve.alias
    .set("@", resolve("src"))
    .set("assets", resolve("src/assets"))
    .set("components", resolve("src/components"))
    .set("base", resolve("baseConfig"))
    .set("public", resolve("public"));
  },
}
```
***

1.上传按钮组件 index.vue
```html
<template>
    <div>
        <div style="width: 100%">
            <el-upload :show-file-list="false" action :before-upload="beforeUpload" :http-request="handleChange">
                <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                <el-button v-else size="small" type="primary">点击上传</el-button>
            </el-upload>
        </div>
        <!-- modal -->
        <cropper-modal ref="CropperModal" :imgType="imgType" @cropper-no="handleCropperClose" @cropper-ok="handleCropperSuccess"></cropper-modal>
    </div>
</template>
<script>
import CropperModal from './CropperModal'
export default {
  name: 'ImageCropper',
  components: {
    CropperModal
  },
  props: {
    //图片裁切配置
    options: {
      type: Object,
      default: function() {
        return {
          autoCrop: true, //是否默认生成截图框
          autoCropWidth: 180, //默认生成截图框宽度
          autoCropHeight: 180, //默认生成截图框高度
          fixedBox: false, //是否固定截图框大小 不允许改变
          previewsCircle: true, //预览图是否是原圆形
          title: '修改头像'
        }
      }
    },
    // 上传图片的大小，单位M
    imgSize: {
      type: Number,
      default: 2
    },
    //图片存储在oss上的上级目录名
    imgType: {
      type: String,
      default: ''
    },
    // 图片地址
    imageUrl: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      loading: false,
      isStopRun: false
    }
  },

  methods: {
    //从本地选择文件
    handleChange(info) {
      if (this.isStopRun) {
        return
      }
      this.loading = true
      const { options } = this
      console.log(info)
      this.getBase64(info.file, imageUrl => {
        const target = Object.assign({}, options, {
          img: imageUrl
        })
        this.$refs.CropperModal.edit(target)
      })
    },
    // 上传之前 格式与大小校验
    beforeUpload(file) {
      this.isStopRun = false
      var fileType = file.type
      if (fileType.indexOf('image') < 0) {
        this.$message.warning('请上传图片')
        this.isStopRun = true
        return false
      }
      const isJpgOrPng =
        file.type === 'image/jpeg' ||
        file.type === 'image/png' ||
        file.type === 'image/jpg'
      if (!isJpgOrPng) {
        this.$message.error('你上传图片格式不正确!')
        this.isStopRun = true
      }
      const isLtSize = file.size < this.imgSize * 1024 * 1024
      if (!isLtSize) {
        this.$message.error('图片大小不能超过' + this.imgSize + 'MB!')
        this.isStopRun = true
      }
      return isJpgOrPng && isLtSize
    },
    //获取服务器返回的地址
    handleCropperSuccess(data) {
      //将返回的数据回显
      this.loading = false
      this.$emit('crop-upload-success', data)
    },
    // 取消上传
    handleCropperClose() {
      this.loading = false
      this.$emit('crop-upload-close')
    },
    getBase64(img, callback) {
      const reader = new FileReader()
      reader.addEventListener('load', () => callback(reader.result))
      reader.readAsDataURL(img)
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep.avatar {
  width: 108px;
  height: 108px;
  display: block;
}
</style>
```
2.模态框 CropperModal.vue
```html
<template>
    <el-dialog :visible.sync="visible" :title="options.title" :close-on-click-modal="false" width="800"
        @close="cancelHandel" append-to-body>
        <el-row>
            <el-col :xs="24" :md="12" :style="{ height: '350px' }">
                <vue-cropper ref="cropper" :img="options.img" :info="true" :autoCrop="options.autoCrop"
                    :autoCropWidth="options.autoCropWidth" :autoCropHeight="options.autoCropHeight"
                    :fixedBox="options.fixedBox" @realTime="realTime">
                </vue-cropper>
            </el-col>
            <el-col :xs="24" :md="12" :style="{ height: '350px' }">
                <div :class="options.previewsCircle ? 'avatar-upload-preview' : 'avatar-upload-preview_range'">
                    <img :src="previews.url" :style="previews.img" />
                </div>
            </el-col>
        </el-row>
        <template slot="footer">
            <el-button size="mini" @click="cancelHandel">取消</el-button>
            <el-button size="mini" type="primary" :loading="confirmLoading" @click="okHandel">保存</el-button>
        </template>
    </el-dialog>
</template>
<script>
import { UpPic } from './index'
export default {
    name: 'CropperModal',
    props: {
        //图片存储在oss上的上级目录名
        imgType: {
            type: String,
            default: ''
        }
    },
    data() {
        return {
            visible: false,
            img: null,
            confirmLoading: false,

            options: {
                img: '', //裁剪图片的地址
                autoCrop: true, //是否默认生成截图框
                autoCropWidth: 180, //默认生成截图框宽度
                autoCropHeight: 180, //默认生成截图框高度
                fixedBox: true, //是否固定截图框大小 不允许改变
                previewsCircle: true, //预览图是否是原圆形
                title: '修改头像'
            },
            previews: {},
            url: {
                upload: '/sys/common/saveToImgByStr'
            },
            centerDialogVisible: false
        }
    },

    methods: {
        edit(record) {
            const { options } = this
            this.visible = true
            this.options = Object.assign({}, options, record)
        },
        /**
         * 取消截图
         */
        cancelHandel() {
            this.confirmLoading = false
            this.visible = false
            this.$emit('cropper-no')
            this.centerDialogVisible = true
        },
        /**
         * 确认截图
         */
        okHandel() {
            const that = this
            that.confirmLoading = true
            // 获取截图的base64 数据
            this.$refs.cropper.getCropData(data => {
                UpPic({
                    img_type: this.imgType,
                    img_byte: data
                })
                    .then(res => {
                        that.$emit('cropper-ok', res)
                    })
                    .catch(err => {
                        that.$message.error(err)
                    })
                    .finally(() => {
                        that.cancelHandel()
                    })
            })
            this.centerDialogVisible = true
        },
        //移动框的事件
        realTime(data) {
            this.previews = data
        }
    }
}
</script>

<style lang="scss" scoped>
.avatar-upload-preview_range,
.avatar-upload-preview {
    position: absolute;
    top: 50%;
    transform: translate(50%, -50%);
    width: 180px;
    height: 180px;
    border-radius: 50%;
    box-shadow: 0 0 4px #ccc;
    overflow: hidden;

    img {
        background-color: red;
        height: 100%;
    }
}

.avatar-upload-preview_range {
    border-radius: 0;
}
</style>
```
3.ajax网络接口 index.js
```js
import request from '@/utils/httpRequest'

const Api = {
    UpPic: '/getImage',
}

/**
 * 上传图片
 * @returns {*}
 */
export function UpPic() {
    return request({
        baseURL: 'https://www.fastmock.site/mock/f6273fce31c98c4d64fd82d91784712f/api',
        url: Api.UpPic,
        method: 'get',
    })
}
```
4.使用组件
```html
<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible"
    append-to-body>
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
      label-width="80px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="dataForm.name" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="dataForm.sex" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="出生日期" prop="birthday">
        <el-date-picker v-model="dataForm.birthday" type="date" placeholder="选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="籍贯" prop="birth">
        <el-input v-model="dataForm.birth" placeholder="籍贯"></el-input>
      </el-form-item>
      <el-form-item label="学历" prop="education">
        <el-input v-model="dataForm.education" placeholder="学历"></el-input>
      </el-form-item>
      <el-form-item label="毕业院校" prop="school">
        <el-input v-model="dataForm.school" placeholder="毕业院校"></el-input>
      </el-form-item>
      <el-form-item label="专业" prop="major">
        <el-input v-model="dataForm.major" placeholder="专业"></el-input>
      </el-form-item>
      <el-form-item label="照片" prop="photo">
        <image-cropper :options="cropperOptions" :imgSize="3" :imgType="imgType" :imageUrl="dataForm.photo"
          @crop-upload-close="cropClose" @crop-upload-success="cropSuccess" />
      </el-form-item>
      <el-form-item label="简历" prop="resume">
        <el-input v-model="dataForm.resume" placeholder="简历"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import ImageCropper from '@/components/ImageCropper/index.vue'
export default {
  components: { ImageCropper },
  data() {
    return {
      visible: false,
      dataForm: {
        id: 0,
        name: '',
        sex: '',
        birthday: '',
        birth: '',
        education: '',
        school: '',
        major: '',
        photo: '',  //上传图片所得到的地址
        resume: ''
      },
      options: [{
        value: '1',
        label: '男'
      }, {
        value: '0',
        label: '女'
      }],
      dataRule: {
        name: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '性别（0代表女1代表男，默认男）不能为空', trigger: 'blur' }
        ],
        birthday: [
          { required: true, message: '出生日期不能为空', trigger: 'blur' }
        ],
        birth: [
          { required: true, message: '籍贯不能为空', trigger: 'blur' }
        ],
        education: [
          { required: true, message: '学历不能为空', trigger: 'blur' }
        ],
        school: [
          { required: true, message: '毕业院校不能为空', trigger: 'blur' }
        ],
        major: [
          { required: true, message: '专业不能为空', trigger: 'blur' }
        ],
        photo: [
          { required: true, message: '照片不能为空', trigger: 'blur' }
        ],
        resume: [
          { required: true, message: '简历不能为空', trigger: 'blur' }
        ]
      },
      cropperOptions: {
        autoCrop: true, //是否默认生成截图框
        autoCropWidth: 200, //默认生成截图框宽度
        autoCropHeight: 200, //默认生成截图框高度
        fixedBox: false, //是否固定截图框大小 不允许改变
        previewsCircle: false, //预览图是否是圆形
        title: '上传图片' //模态框上显示的标题
      },
      imgType: 'testUp', //图片存储在oss上的上级目录名
    }
  },
  methods: {
    //上传操作结束
    cropClose() {
      console.log('上传操作结束')
    },
    //上传图片成功
    cropSuccess(data) {
      this.dataForm.photo = data.data.avatar
      console.log(this.dataForm.photo)
    },
    init(id) {
      this.dataForm.id = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/employee/user/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({ data }) => {
            if (data && data.code === 200) {
              this.dataForm.name = data.user.name
              this.dataForm.sex = data.user.sex
              this.dataForm.birthday = data.user.birthday
              this.dataForm.birth = data.user.birth
              this.dataForm.education = data.user.education
              this.dataForm.school = data.user.school
              this.dataForm.major = data.user.major
              this.dataForm.photo = data.user.photo
              this.dataForm.resume = data.user.resume
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/employee/user/${!this.dataForm.id ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              'id': this.dataForm.id || undefined,
              'name': this.dataForm.name,
              'sex': this.dataForm.sex,
              'birthday': this.dataForm.birthday,
              'birth': this.dataForm.birth,
              'education': this.dataForm.education,
              'school': this.dataForm.school,
              'major': this.dataForm.major,
              'photo': this.dataForm.photo,
              'resume': this.dataForm.resume
            })
          }).then(({ data }) => {
            if (data && data.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    }
  }
}
</script>
```
这里由于是多层弹框，有多层遮罩层，所以我们 el-dialog 添加属性`：append-to-body`
![在这里插入图片描述](https://img-blog.csdnimg.cn/2dc5f1e73a9c4d65a64e766ced5dc005.png)
效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/6b1cc578ffd540ca83cc7ce84eb8477b.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/00e4be3d498f4a1ca498bbe5977eacd4.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/ee3847aa26274cb5a6b4bb8d13d91f97.png)
# 实现文件上传、下载（50min）
[基于MinIO搭建高性能文件服务器](https://blog.csdn.net/m0_51517236/article/details/124462300?spm=1001.2014.3001.5502)

这里我们直接给代码（小航这里采用的是读取配置文件的方式）：

1.设置配置文件
![在这里插入图片描述](https://img-blog.csdnimg.cn/e8f1618ff525497e9776e8344c1e5918.png)
```yml
oss:
  endpoint: 
  accessKey: 
  secretKey: 
  bucket:
```
2.MinIOConfiguration（使用@ConfigurationProperties注解（松散绑定））
```java
@Configuration
@ConfigurationProperties(prefix = "oss")  // 1
@Setter // 2
public class MinIOConfiguration {
    private String endpoint;  // 3 不是静态 static, Spring源码过滤掉了
    private String accessKey;
    private String secretKey;

    @Bean // 4
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/b2d790d8b3c146b8a1d37ad5d8890a74.png)
3.OssController
```java
package com.tyut.employee.controller;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author xh
 * @Date 2022/5/28
 */
@RestController
public class OssController {
    @Autowired
    MinioClient minioClient;

    @Value("${oss.bucket}")
    String bucket;
    @Value("${oss.endpoint}")
    String endpoint;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        // 上传
        String path = UUID.randomUUID().toString(); // 文件名，使用 UUID 随机
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket) // 存储桶
                    .object(path) // 文件名
                    .stream(file.getInputStream(), file.getSize(), -1) // 文件内容
                    .contentType(file.getContentType()) // 文件类型
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 拼接路径
        return String.format("%s/%s/%s", endpoint, bucket, path);
    }
}
```
4.使用ApiFox测试
![在这里插入图片描述](https://img-blog.csdnimg.cn/76f4d7b4b8e6488682017722e41ce6ed.png)
5.查看图片（是偷偷睡觉的小橘猫）
![在这里插入图片描述](https://img-blog.csdnimg.cn/b6e095d24e47448aa83b923541bf2ee4.png)
对接前端：（这里遇到了一堆堆bug，耽搁了时间）

确认截图后我们对base64解码，传到后端
```js
// 获取截图的base64 数据
            this.$refs.cropper.getCropData(imgData => {
                let formData = new FormData()
                formData.append('file', this.base64toFile(imgData))
                this.$http({
                    url: this.$http.adornUrl(`/upload`),
                    method: 'post',
                    data: formData,
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                })
                    .then(res => {
                        that.$emit('cropper-ok', res)
                    })
                    .catch(err => {
                        that.$message.error(err)
                    })
                    .finally(() => {
                        that.cancelHandel()
                    })
            })


// 解码base64文件
        base64toFile(base64Data) {
            //去掉base64的头部信息，并转换为byte
            let split = base64Data.split(',');
            let bytes = window.atob(split[1]);
            //获取文件类型
            let fileType = split[0].match(/:(.*?);/)[1];
            //处理异常,将ascii码小于0的转换为大于0
            let ab = new ArrayBuffer(bytes.length);
            let ia = new Uint8Array(ab);
            for (let i = 0; i < bytes.length; i++) {
                ia[i] = bytes.charCodeAt(i);
            }
            return new Blob([ab], { type: fileType });
        }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/966e0b8e1ee24754a7e735bac8b7846a.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/51ab020a3993428cba09ee219ffab6ce.png)
测试：
![在这里插入图片描述](https://img-blog.csdnimg.cn/7c2580093ed54eb4912136443e96d1a7.png)
上传成功！

完善一个小细节：（出生日期不太对）
![在这里插入图片描述](https://img-blog.csdnimg.cn/b23c9bf67dcb46acad1ad1627b1cb633.png)
这里我们只需要修改一下后端给前端的格式即可
```xml
jackson:
    date-format: yyyy-MM-dd
    time-zone: GMT+8
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/0f0d7b4dbcb743d5b55a820680c421eb.png)
小作业-完善简历部分

思路很简单，这里大概说一下：

1.两个按钮（无简历显示上传、有简历显示查看）
2.若无简历，通过传入变量id，调用上传得到文件地址，调用后端根据id更新简历
3.若有简历，实现预览文件

最终代码：
前端：
user.vue
```html
<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border v-loading="dataListLoading" @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50">
      </el-table-column>
      <el-table-column prop="id" header-align="center" align="center" label="工号">
      </el-table-column>
      <el-table-column prop="name" header-align="center" align="center" label="姓名">
      </el-table-column>
      <el-table-column prop="sex" header-align="center" align="center" label="性别">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.sex == 0 ? '女' : '男' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="birthday" header-align="center" align="center" label="出生日期" width="150">
      </el-table-column>
      <el-table-column prop="birth" header-align="center" align="center" label="籍贯">
      </el-table-column>
      <el-table-column prop="education" header-align="center" align="center" label="学历">
      </el-table-column>
      <el-table-column prop="school" header-align="center" align="center" label="毕业院校">
      </el-table-column>
      <el-table-column prop="major" header-align="center" align="center" label="专业">
      </el-table-column>
      <el-table-column prop="photo" header-align="center" align="center" label="照片">
        <!-- 图片的显示 -->
        <template slot-scope="scope">
          <el-image class="my-img" style="width: 70px; height: 70px" ref="myImg" :src="scope.row.photo"
            :preview-src-list="srcList" @click="handlePriveImg(scope.row)">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="resume" header-align="center" align="center" label="简历">
        <template slot-scope="scope">
          <el-upload ref="upload" :http-request="httpRequest" class="upload-demo" action :limit="1"
            :file-list="fileList" v-if="scope.row.resume == ''" :show-file-list="false" :before-upload="beforeUpload">
            <el-button type="primary" @click="uploadFile(scope.row.id)">上传</el-button>
          </el-upload>
          <el-button v-else type="primary" @click="viewFile(scope.row.resume)">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './user-add-or-update'
export default {
  data() {
    return {
      dataForm: {
        key: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      srcList: [],
      fileList: [],
      id: '',
    }
  },
  components: {
    AddOrUpdate
  },
  created() {
    this.getDataList()
  },
  methods: {
    // 点击按钮
    uploadFile(id) {
      // 当前更新的id
      this.id = id;
      this.$refs.upload.submit();
    },
    // 提交文件
    httpRequest(param) {
      // 获取上传的文件
      var file = param.file
      //发送请求的参数格式为FormData
      const formData = new FormData();
      formData.append("file", file)
      this.$http({
        url: this.$http.adornUrl(`/upload`),
        method: 'post',
        data: formData,
        headers: {
          'Content-Type': 'multipart/form-data'
        },
      })
        .then(res => {
          console.log(res.data)
          // 根据id更新简历
          this.updateResumeById(this.id, res.data);
        })
        .catch(err => {
          this.$message.error(err)
        })
    },
    beforeUpload(file) {
      console.log(file)
      // 文件只能是 word、pdf、ppt
      if (['application/vnd.ms-powerpoint', 'application/msword', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'application/pdf'].indexOf(file.type) == -1) {
        this.$message.error('请上传正确类型的文件格式');
        return false;
      }
    },
    // 查看简历
    viewFile(fileUrl) {
      console.log("查看了文件！")
      window.open(fileUrl, '_blank');
    },
    // 根据id更新简历
    updateResumeById(id, fileUrl) {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/employee/user/updateResumeById'),
        method: 'get',
        params: this.$http.adornParams({
          'id': id,
          'fileUrl': fileUrl
        })
      }).then(({ data }) => {
        if (data && data.code === 200) {
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.getDataList()
            }
          })
        } else {
          this.$message.error(data.msg)
        }
        this.dataListLoading = false
      })
    },
    // 点击查看大图
    handlePriveImg(row) {
      this.srcList.push(row.photo);//将后端的这一行的数据push进数组中
      this.$refs.myImg.clickHandler();
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/employee/user/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'key': this.dataForm.key
        })
      }).then(({ data }) => {
        if (data && data.code === 200) {
          this.dataList = data.page.list
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle(id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.id
      })
      this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/employee/user/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({ data }) => {
          if (data && data.code === 200) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      })
    }
  }
}
</script>
<style scoped>
/*使鼠标悬浮在图片上时出现手的形状 */
.my-img:hover {
  cursor: pointer;
}
</style>
```
后端：
UserController
```java
@GetMapping("/updateResumeById")
    public R updateResumeById(@RequestParam Map<String, String> params) {
        Long id = Long.valueOf(params.get("id"));
        String fileUrl = String.valueOf(params.get("fileUrl"));
        boolean flag = userService.updateResumeById(id, fileUrl);
        if(flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }
```
UserServiceImpl
```java
/**
     * 根据ID更新简历
     * @return
     */
    @Override
    public boolean updateResumeById(Long id, String fileUrl) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setResume(fileUrl);
        int count = baseMapper.updateById(userEntity);
        return count > 0;
    }
```
这里给出常用的校验模块，供大家学习：
```js
 //校验版块
        //校验---{上传成员头像}前图片格式及大小
        AvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;
            if(['image/jpeg','image/PNG'].indexOf(File.type) == -1) {
                this.$message.error('上传头像图片只能是 JPG/PNG 格式!');
                return false;
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M; 
        },
        //校验---{上传视频}前进行格式校验
        videoUpload(file) {
            const isLt50M = file.size / 1024 / 1024  < 50;
            if (['video/mp4', 'video/ogg', 'video/flv','video/avi','video/wmv','video/rmvb'].indexOf(file.type) == -1) {
                this.$message.error('请上传正确的视频格式');
                return false;
            }
            if (!isLt50M) {
                this.$message.error('上传视频大小不能超过50MB哦!');
                return false;
            }
        },
        //校验---{上传ppt}前进行格式校验
        pptUpload(file) {
        if (['application/vnd.ms-powerpoint'].indexOf(file.type) == -1) {
                this.$message.error('请上传正确的ppt格式');
                return false;
            }  
        },
        //校验---{上传商业企划书}前进行格式校验
        doxrUpload(file) {
            if (['application/vnd.ms-powerpoint', 'application/msword','application/vnd.openxmlformats-officedocument.wordprocessingml.document','application/pdf' ].indexOf(file.type) == -1) {
                this.$message.error('请上传正确类型的文件格式');
                return false;
            } 
        },
```
***
可能会遇到的问题：
```xml
2022-05-30 21:54:23.807 ERROR 14428 --- [nio-9999-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.web.multipart.MaxUploadSizeExceededException: Maximum upload size exceeded; nested exception is java.lang.IllegalStateException: org.apache.tomcat.util.http.fileupload.FileUploadBase$SizeLimitExceededException: the request was rejected because its size (17070896) exceeds the configured maximum (10485760)] with root cause

org.apache.tomcat.util.http.fileupload.FileUploadBase$SizeLimitExceededException: the request was rejected because its size (17070896) exceeds the configured maximum (10485760)
	at org.apache.tomcat.util.http.fileupload.FileUploadBase$FileItemIteratorImpl.<init>(FileUploadBase.java:808) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.tomcat.util.http.fileupload.FileUploadBase.getItemIterator(FileUploadBase.java:256) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.tomcat.util.http.fileupload.FileUploadBase.parseRequest(FileUploadBase.java:280) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.connector.Request.parseParts(Request.java:2868) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.connector.Request.getParts(Request.java:2770) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.connector.RequestFacade.getParts(RequestFacade.java:1098) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.springframework.web.multipart.support.StandardMultipartHttpServletRequest.parseRequest(StandardMultipartHttpServletRequest.java:95) ~[spring-web-5.2.1.RELEASE.jar:5.2.1.RELEASE]
	at org.springframework.web.multipart.support.StandardMultipartHttpServletRequest.<init>(StandardMultipartHttpServletRequest.java:88) ~[spring-web-5.2.1.RELEASE.jar:5.2.1.RELEASE]
	at org.springframework.web.multipart.support.StandardServletMultipartResolver.resolveMultipart(StandardServletMultipartResolver.java:87) ~[spring-web-5.2.1.RELEASE.jar:5.2.1.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.checkMultipart(DispatcherServlet.java:1178) ~[spring-webmvc-5.2.1.RELEASE.jar:5.2.1.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1012) ~[spring-webmvc-5.2.1.RELEASE.jar:5.2.1.RELEASE]
	at 
```
原因：SpringBoot 上传文件报错，请求大小超过了配置的最大值，springboot 默认 multipart.max-file-size大小是1M，max-request-size默认大小是10M
解决办法：
在application.yml文件中追加配置：（一定要带单位）
```yml
spring:
  servlet:
    multipart:
      max-file-size: 100MB
      max-request-size: 100MB
```
最终效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/25d8a0b2a5c8404eb2a842766e8deca3.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/acf137ec89494acb98bc7dec08bc2724.png)
最后咱们把新增页面也加入按钮叭（可以先尝试独立完成，推荐自行封装组件）


新建组件：
![在这里插入图片描述](https://img-blog.csdnimg.cn/0624c89fdc734b35a49c116c2cb260a0.png)
index.vue
```html
<template>
    <el-upload ref="upload" :http-request="httpRequest" class="upload-demo" action :limit="1" :file-list="fileList"
        :show-file-list="false" :before-upload="beforeUpload">
        <el-button v-if="fileUrl == ''" size="small" type="primary">点击上传</el-button>
        <el-button v-else size="small" type="primary">点击修改</el-button>
    </el-upload>
</template>

<script>
export default {
    props: {
        fileUrl: {
            type: String,
            default: ''
        }
    },
    data() {
        return {
            fileList: [],
        }
    },
    methods: {
        // 提交文件
        httpRequest(param) {
            // 获取上传的文件
            var file = param.file
            //发送请求的参数格式为FormData
            const formData = new FormData();
            formData.append("file", file)
            this.$http({
                url: this.$http.adornUrl(`/upload`),
                method: 'post',
                data: formData,
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
            })
                .then(res => {
                    console.log(res.data)
                    this.$message({
                        message: '上传成功！',
                        type: 'success'
                    });
                    // 将地址发给父组件
                    this.$emit('file-upload-success', res.data)
                })
                .catch(err => {
                    this.$message.error(err)
                })
        },
        beforeUpload(file) {
            // 文件只能是 word、pdf、ppt
            if (['application/vnd.ms-powerpoint', 'application/msword', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'application/pdf'].indexOf(file.type) == -1) {
                this.$message.error('请上传正确类型的文件格式');
                return false;
            }
        }
    }
}
</script>

<style>
</style>
```
user-add-or-update.vue
```html
<el-form-item label="简历" prop="resume">
        <!-- <el-input v-model="dataForm.resume" placeholder="简历"></el-input> -->
        <file-upload :fileUrl="dataForm.resume" @file-upload-success="fileUploadSuccess"></file-upload>
      </el-form-item>
```
```js
import FileUpload from '@/components/FileUpload/index.vue'

components: { ImageCropper, FileUpload},

// 上传文件成功
    fileUploadSuccess(data) {
      this.dataForm.resume = data;
    },
```
效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/51f19b456d8a4673a00afc1ea866fae8.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/7c1a14673e0a495ea52828a2804dfac4.png)
# 左树右图的实现（90min）
这里我们把树形菜单小小加强一下叭：
![在这里插入图片描述](https://img-blog.csdnimg.cn/de040b478fcd476baeb57765b1a5e787.png)
首先小航这里帮大家踩几个常见的坑：
修改DeptEntity
```java
package com.tyut.employee.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 *
 * @author Liu
 * @email 1531137510@qq.com
 * @date 2022-05-10 18:29:55
 */
@Data
@TableName("em_dept")
public class DeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 部门ID
	 */
	@TableId
	private Integer id;
	/**
	 * 父ID
	 */
	@JsonProperty(value = "pId")
	private Integer pId;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 层级
	 */
	private Integer deptLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	@TableLogic(value = "1", delval = "0")
	private Integer showStatus;
	/**
	 * 排序
	 */
	private Integer sort;

	@JsonInclude(JsonInclude.Include.NON_EMPTY) // 属性为空不参与序列化，这里方便前端处理
	@TableField(exist = false) // 数据库表中不存在该字段
	private List<DeptEntity> children;

}

```
坑1： 当我们使用lombok时，属性名首字母小写第二个字母大写导致无法解析参数，此时生成的get、set方法不符合规范，而jackjson按照规范解析字段名时无法匹配，导致赋值失败产生问题，前后端对接自然就会出问题了。
>JavaBean 规范中有一个特别的地方，如果属性名的第二个字母是大写的，那么该属性名直接用作 getter/setter 方法中 get/set 的后部分，也就是说大小写不变。

其次呢ibatis插件也会爆红：
![在这里插入图片描述](https://img-blog.csdnimg.cn/ca0489eb4abb44cd971d86848a3ff390.png)
所以推荐大家，使用`parent_id`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.tyut.employee.dao.DeptDao">

	<!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="com.tyut.employee.entity.DeptEntity" id="deptMap">
        <result property="id" column="id"/>
        <result property="pId" column="p_id"/>
        <result property="name" column="name"/>
        <result property="deptLevel" column="dept_level"/>
        <result property="showStatus" column="show_status"/>
        <result property="sort" column="sort"/>
    </resultMap>

</mapper>

```
坑2：@JsonInclude(JsonInclude.Include.NON_EMPTY) // 属性为空不参与序列化，这里方便前端处理

我们在给前端发送树形菜单时，如果可能会存在最后一层为空，前端渲染时会将最后一层空节点也渲染上。这里添加注释@JsonInclude(JsonInclude.Include.NON_EMPTY) 是不错的解决办法之一。
***
配置逻辑删除，也推荐配置文件配置，咱们这个过于简单，所以直接使用注解的方式配置了
application.yml
```yml
mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto  # 主键自增
      #logic-delete-value: 1
      #logic-not-delete-value: 0
```
***
开始编写Controller层：
DeptController添加一个接口
```java
/**
     * 查询部门树形菜单
     */
    @GetMapping("/list/tree")
    public R listWithTree() {
        List<DeptEntity> entities = deptService.listWithTree();
        return R.ok().put("data", entities);
    }
```
DeptServiceImpl：
```java
**
     * 查询部门树形菜单
     */
    @Override
    public List<DeptEntity> listWithTree() {
        // 1.查出所有分类（数据库只查询一次，内存进行修改）
        List<DeptEntity> entities = baseMapper.selectList(null);
        // 2.组装分类
        return entities.stream().filter(deptEntity -> deptEntity.getPId() == 0) // 先过滤得到所有一级分类
                .map((dept) -> {
                    dept.setChildren(getChildrens(dept, entities)); // 递归得到一级分类的子部门
                    return dept;
                }).sorted((dept1, dept2) -> { // 部门排序,这里运算一定要进行非空判断
                    return ((dept1.getSort() == null ? 0 : dept1.getSort()) - (dept2.getSort() == null ? 0 : dept2.getSort()));
                }).collect(Collectors.toList());
    }
    /**
     * 递归查询子部门
     */
    private List<DeptEntity> getChildrens(DeptEntity root, List<DeptEntity> all) {
        return all.stream().filter(deptEntity -> root.getId().equals(deptEntity.getPId())) // 找到root的子部门
                .map(dept -> {
                    dept.setChildren(getChildrens(dept, all)); // 设置为子部门
                    return dept;
                }).sorted((dept1, dept2) -> { // 部门排序,这里运算一定要进行非空判断
                    return ((dept1.getSort() == null ? 0 : dept1.getSort()) - (dept2.getSort() == null ? 0 : dept2.getSort()));
                }).collect(Collectors.toList());
    }
```
数据库增加测试数据，测试，编写测试文档：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2d5b11d3c00e48239193b614ecf5d0a2.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/4c179d7e07864f8390073503a39e4e40.png)
返回数据：
```json
{
    "msg": "成功",
    "code": 200,
    "data": [
        {
            "id": 1,
            "name": "1",
            "deptLevel": 1,
            "showStatus": 1,
            "sort": 1,
            "children": [
                {
                    "id": 3,
                    "name": "1-1",
                    "deptLevel": 2,
                    "showStatus": 1,
                    "sort": 1,
                    "children": [
                        {
                            "id": 4,
                            "name": "1-1-1",
                            "deptLevel": 3,
                            "showStatus": 1,
                            "sort": 1,
                            "pId": 3
                        },
                        {
                            "id": 5,
                            "name": "1-1-2",
                            "deptLevel": 3,
                            "showStatus": 1,
                            "sort": 2,
                            "pId": 3
                        }
                    ],
                    "pId": 1
                }
            ],
            "pId": 0
        },
        {
            "id": 2,
            "name": "2",
            "deptLevel": 1,
            "showStatus": 1,
            "sort": 2,
            "pId": 0
        }
    ]
}
```
编写前端：
我们先新建一个树组件：(查阅element-ui)
![在这里插入图片描述](https://img-blog.csdnimg.cn/f0ed7d61ef6e4333ac9e8546412a9e83.png)

TreeDept/index.vue
```html
<template>
    <div>
        <el-input placeholder="输入关键字进行过滤" v-model="filterText">
        </el-input>
        <el-tree class="filter-tree" :data="depts" :props="defaultProps" default-expand-all
            :filter-node-method="filterNode" ref="tree" @node-click="nodeclick" :highlight-current="true"
            node-key="id">
        </el-tree>
    </div>
</template>

<script>
export default {
    // 检测data中数据变化
    watch: {
        filterText(val) {
            this.$refs.tree.filter(val);
        }
    },

    methods: {
        // 树节点过滤
        filterNode(value, data) {
            if (!value) return true;
            return data.label.indexOf(value) !== -1;
        },
        getDepts() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/list/tree"),
                method: "get"
            }).then(({ data }) => {
                this.depts = data.data;
            });
        },
        nodeclick(data, node, component) {
            // console.log("子组件category的节点被点击", data, node, component);
            //向父组件发送事件；
            this.$emit("tree-node-click", data, node, component);
        }
    },

    created() {
        this.getDepts();
    },

    data() {
        return {
            filterText: '',
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            depts: [] // 部门表
        };
    }
};
</script>
<style>
</style>
```
引入树形组件，左右布局：（6：18）
```html
<template>
  <el-row :gutter="20">
    <el-col :span="6"><tree-dept @tree-node-click="treenodeclick"></tree-dept></el-col>
    <el-col :span="18">
      <div class="mod-config">

        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
          <el-form-item>
            <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="getDataList()">查询</el-button>
            <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
            <el-button type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="dataList" border v-loading="dataListLoading" @selection-change="selectionChangeHandle"
          style="width: 100%;">
          <el-table-column type="selection" header-align="center" align="center" width="50">
          </el-table-column>
          <el-table-column prop="id" header-align="center" align="center" label="部门ID">
          </el-table-column>
          <el-table-column prop="pId" header-align="center" align="center" label="父ID">
          </el-table-column>
          <el-table-column prop="name" header-align="center" align="center" label="部门名称">
          </el-table-column>
          <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
              <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle" :current-page="pageIndex"
          :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
        <!-- 弹窗, 新增 / 修改 -->
        <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
      </div>
    </el-col>
  </el-row>
</template>


<script>
import AddOrUpdate from './dept-add-or-update'
import TreeDept from '@/components/TreeDept/index'
export default {
  data() {
    return {
      dataForm: {
        key: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      deptId: 0
    }
  },
  components: {
    AddOrUpdate, TreeDept
  },
  created() {
    this.getDataList()
  },
  methods: {
    // 感知树节点被点击
    treenodeclick(data, node) {
      console.log("父节点", data, node)
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/employee/dept/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'key': this.dataForm.key
        })
      }).then(({ data }) => {
        if (data && data.code === 200) {
          this.dataList = data.page.list
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle(id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.id
      })
      this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/employee/dept/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({ data }) => {
          if (data && data.code === 200) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      })
    }
  }
}
</script>
```
修改树形菜单样式：
![在这里插入图片描述](https://img-blog.csdnimg.cn/93bff13bde69457dbf6ce1e61c5cf502.png)

```css
// 树形菜单样式修改
.el-tree {
  background: transparent;
  color: #c8c8c8;

}
// 鼠标在树形节点上面
.el-tree-node__content:hover, .el-upload-list__item:hover {
  background-color: #484545;
}
.el-tree-node:focus>.el-tree-node__content {
  background-color: #484545;
}
.el-tree--highlight-current .el-tree-node.is-current>.el-tree-node__content {
  background-color: #484545;
}
```
效果：
	![在这里插入图片描述](https://img-blog.csdnimg.cn/fb1a567a61db49279a02efa1134aaa74.png)
优化树形菜单--增加、修改、删除功能：

增加按钮和弹窗：
```html
<template>
    <div>
        <el-input placeholder="输入关键字进行过滤" v-model="filterText">
        </el-input>
        <el-tree class="filter-tree" :data="depts" :props="defaultProps" 
            :filter-node-method="filterNode" ref="tree" @node-click="nodeclick" :highlight-current="true" node-key="id"
            :default-expanded-keys="expandedKey"
            >
            <span class="custom-tree-node" slot-scope="{ node, data }">
                <span>{{ node.label }}</span>
                <span>
                    <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">添加
                    </el-button>
                    <el-button type="text" size="mini" @click="edit(data)">编辑</el-button>
                    <el-button v-if="node.childNodes.length == 0" type="text" size="mini"
                        @click="() => remove(node, data)">删除</el-button>
                </span>
            </span>
        </el-tree>
        <el-dialog :title="title" :visible.sync="dialogVisible" width="30%" :close-on-click-modal="false"
            :modal-append-to-body="false">
            <el-form :model="dept">
                <el-form-item label="部门名称">
                    <el-input v-model="dept.name" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitData">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
```
JS部分：(都是基础的增删改，这里就不具体赘述了)
```js
<script>
export default {

    data() {
        return {
            filterText: '',
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            depts: [], // 部门表
            title: '',
            dialogType: "", //edit,add
            dialogVisible: false,
            dept: {
                id: null,
                name: '',
                pId: 0,
                level: 0,
                showStatus: 1,
                sort: 0,
            },
            expandedKey: [], // 当前展示的树形
        };
    },
    // 检测data中数据变化
    watch: {
        filterText(val) {
            this.$refs.tree.filter(val);
        }
    },

    created() {
        this.getDepts();
    },
    methods: {
        // 树节点过滤
        filterNode(value, data) {
            if (!value) return true;
            return data.name.indexOf(value) !== -1;
        },
        getDepts() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/list/tree"),
                method: "get"
            }).then(({ data }) => {
                this.depts = data.data;
            });
        },
        nodeclick(data, node, component) {
            //向父组件发送事件；
            this.$emit("tree-node-click", data, node, component);
        },
        // 编辑数据
        edit(data) {
            console.log("要修改的数据", data);
            this.dialogType = "edit";
            this.title = "修改部门";
            this.dialogVisible = true;
            //发送请求获取当前节点最新的数据
            this.$http({
                url: this.$http.adornUrl(`/employee/dept/info/${data.id}`),
                method: "get"
            }).then(({ data }) => {
                //请求成功
                console.log("要回显的数据", data);
                this.dept.name = data.dept.name;
                this.dept.id = data.dept.id;
                this.dept.pId = data.dept.pId;
                this.dept.level = data.dept.level;
                this.dept.sort = data.dept.sort;
                this.dept.showStatus = data.dept.showStatus;
            });
        },
        // 添加
        append(data) {
            console.log("append", data);
            // 弹窗设置
            this.dialogType = "add";
            this.title = "添加部门";
            this.dialogVisible = true;
            // 初始化表单
            this.dept.pId = data.id;
            this.dept.level = data.catLevel * 1 + 1;
            this.dept.id = null;
            this.dept.name = "";
            this.dept.sort = 0;
            this.dept.showStatus = 1;
        },
        // 移除
        remove(node, data) {
            var ids = [data.id];
            this.$confirm(`是否删除【${data.name}】部门?`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$http({
                        url: this.$http.adornUrl("/employee/dept/delete"),
                        method: "post",
                        data: this.$http.adornData(ids, false)
                    }).then(() => {
                        this.$message({
                            message: "部门删除成功",
                            type: "success"
                        });
                        //刷新树形列表
                        this.getDepts();
                        //设置需要默认展开的部门
                        this.expandedKey = [node.parent.data.id];
                    });
                })
                .catch(() => { });
        },
        // 提交数据
        submitData() {
            if (this.dialogType == "add") {
                this.addCategory();
            }
            if (this.dialogType == "edit") {
                this.editCategory();
            }
        },
        //修改三级分类数据
        editCategory() {
            var { id, name } = this.dept;
            this.$http({
                url: this.$http.adornUrl("/employee/dept/update"),
                method: "post",
                data: this.$http.adornData({ id, name }, false)
            }).then(() => {
                this.$message({
                    message: "部门修改成功",
                    type: "success"
                });
                //关闭对话框
                this.dialogVisible = false;
                //刷新出新的部门
                this.getDepts();
                //设置需要默认展开的部门
                this.expandedKey = [this.dept.pId];
            });
        },
        //添加三级分类
        addCategory() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/save"),
                method: "post",
                data: this.$http.adornData(this.dept, false)
            }).then(() => {
                this.$message({
                    message: "部门保存成功",
                    type: "success"
                });
                //关闭对话框
                this.dialogVisible = false;
                //刷新树形列表
                this.getDepts();
                //设置需要默认展开的部门
                this.expandedKey = [this.dept.pId];
            });
        },
    }

};
</script>
```
效果展示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/d5f0930371494ee1a972c79911cf08c4.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/ab7332d33c4449a387a6553e78a51252.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/5cb243bb122b445881cf17dd24cbbc19.png)
继续优化，添加拖拽功能：

给树组件添加相应的属性：
```html
<el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽"></el-switch>
<el-input placeholder="输入关键字进行过滤" v-model="filterText">
        </el-input>
<el-tree class="filter-tree" 
        :data="depts" 
        :props="defaultProps" 
            :filter-node-method="filterNode"
             ref="tree" 
             @node-click="nodeclick" 
             :highlight-current="true" 
             node-key="id"
            :default-expanded-keys="expandedKey"
            :draggable="draggable"
            :expand-on-click-node="false"
             :allow-drop="allowDrop"
            @node-drop="handleDrop"
            >
            <span class="custom-tree-node" slot-scope="{ node, data }">
                <span>{{ node.label }}</span>
                <span>
                    <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">添加
                    </el-button>
                    <el-button type="text" size="mini" @click="edit(data)">编辑</el-button>
                    <el-button v-if="node.childNodes.length == 0" type="text" size="mini"
                        @click="() => remove(node, data)">删除</el-button>
                </span>
            </span>
        </el-tree>
```
```html
这四个
:draggable="draggable"
            :expand-on-click-node="false"
             :allow-drop="allowDrop"
            @node-drop="handleDrop"
```
增加变量：
```js
draggable: false, // 拖拽
```
定义方法：
```js
allowDrop() {

        },
handleDrop() {
            
        }
```
整体框架就是这样，接下来我们开始定义拖拽规则：
`当前正在拖动的节点+父节点所在的深度不大于3`
```js
allowDrop(draggingNode, dropNode, type) {
            //1、被拖动的当前节点以及所在的父节点总层数不能大于3

            //1）、被拖动的当前节点总层数
            console.log("allowDrop:", draggingNode, dropNode, type);
            //
            this.countNodeLevel(draggingNode);
            //当前正在拖动的节点+父节点所在的深度不大于3即可
            let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;
            console.log("深度：", deep);

            //   this.maxLevel
            if (type == "inner") {
                // console.log(
                //   `this.maxLevel：${this.maxLevel}；draggingNode.data.catLevel：${draggingNode.data.catLevel}；dropNode.level：${dropNode.level}`
                // );
                return deep + dropNode.level <= 3;
            } else {
                return deep + dropNode.parent.level <= 3;
            }
        },
        // 求最大深度
        countNodeLevel(node) {
            //找到所有子节点，求出最大深度
            if (node.childNodes != null && node.childNodes.length > 0) {
                for (let i = 0; i < node.childNodes.length; i++) {
                    if (node.childNodes[i].level > this.maxLevel) {
                        this.maxLevel = node.childNodes[i].level;
                    }
                    this.countNodeLevel(node.childNodes[i]);
                }
            }
        },
        handleDrop(draggingNode, dropNode, dropType) {
            console.log("handleDrop: ", draggingNode, dropNode, dropType);
            //1、当前节点最新的父节点id
            let pId = 0;
            let siblings = null;
            if (dropType == "before" || dropType == "after") {
                pId =
                    dropNode.parent.data.id == undefined
                        ? 0
                        : dropNode.parent.data.id;
                siblings = dropNode.parent.childNodes;
            } else {
                pId = dropNode.data.id;
                siblings = dropNode.childNodes;
            }
            this.pId.push(pId);

            //2、当前拖拽节点的最新顺序，
            for (let i = 0; i < siblings.length; i++) {
                if (siblings[i].data.id == draggingNode.data.id) {
                    //如果遍历的是当前正在拖拽的节点
                    let level = draggingNode.level;
                    if (siblings[i].level != draggingNode.level) {
                        //当前节点的层级发生变化
                        level = siblings[i].level;
                        //修改他子节点的层级
                        this.updateChildNodeLevel(siblings[i]);
                    }
                    this.updateNodes.push({
                        id: siblings[i].data.id,
                        sort: i,
                        pId: pId,
                        level: level
                    });
                } else {
                    this.updateNodes.push({ id: siblings[i].data.id, sort: i });
                }
            }
            //3、当前拖拽节点的最新层级
            console.log("updateNodes", this.updateNodes);
        },
        // 更新子部门层级
        updateChildNodeLevel(node) {
            if (node.childNodes.length > 0) {
                for (let i = 0; i < node.childNodes.length; i++) {
                    var cNode = node.childNodes[i].data;
                    this.updateNodes.push({
                        id: cNode.id,
                        level: node.childNodes[i].level
                    });
                    this.updateChildNodeLevel(node.childNodes[i]);
                }
            }
        },
```
拖拽结束需要保存，增加一个按钮：
```html
<el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽"></el-switch>
<el-button v-if="draggable" @click="batchSave" size="mini">批量保存</el-button>
```
```js
// 批量保存
        batchSave() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/update/sort"),
                method: "post",
                data: this.$http.adornData(this.updateNodes, false)
            }).then(() => {
                this.$message({
                    message: "部门顺序修改成功",
                    type: "success"
                });
                //刷新出新的部门
                this.getDepts();
                //设置需要默认展开的部门
                this.expandedKey = this.pCid;
                this.updateNodes = [];
                this.maxLevel = 0;
                // this.pCid = 0;
            });
        },
```
增加删除功能：
1.开启复选（show-checkbox）
```html
<el-tree class="filter-tree" :data="depts" :props="defaultProps" :filter-node-method="filterNode" ref="tree"
            @node-click="nodeclick" :highlight-current="true" node-key="id" :default-expanded-keys="expandedKey"
            :draggable="draggable" :expand-on-click-node="false" :allow-drop="allowDrop" @node-drop="handleDrop"
            show-checkbox>
            <span class="custom-tree-node" slot-scope="{ node, data }">
                <span>{{ node.label }}</span>
                <span>
                    <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">添加
                    </el-button>
                    <el-button type="text" size="mini" @click="edit(data)">编辑</el-button>
                    <el-button v-if="node.childNodes.length == 0" type="text" size="mini"
                        @click="() => remove(node, data)">删除</el-button>
                </span>
            </span>
        </el-tree>
```
2.js
```js
// 批量删除
        batchDelete() {
            let ids = [];
            let checkedNodes = this.$refs.menuTree.getCheckedNodes();
            console.log("被选中的元素", checkedNodes);
            for (let i = 0; i < checkedNodes.length; i++) {
                ids.push(checkedNodes[i].id);
            }
            this.$confirm(`是否批量删除【${ids}】部门?`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$http({
                        url: this.$http.adornUrl("/employee/dept/delete"),
                        method: "post",
                        data: this.$http.adornData(ids, false)
                    }).then(() => {
                        this.$message({
                            message: "部门批量删除成功",
                            type: "success"
                        });
                        this.getDepts();
                    });
                })
                .catch(() => { });
        }
```
3.后端代码：
DeptController
```java
/**
     * 批量修改
     */
    @PostMapping("/update/sort")
    public R updateSort(@RequestBody DeptEntity[] category){
        deptService.updateBatchById(Arrays.asList(category));
        return R.ok();
    }
```
最后完善美化一下字体的样式：
![在这里插入图片描述](https://img-blog.csdnimg.cn/978625c1c3d64b868bfcdc89443458a6.png)

```css
// 复选框
.el-checkbox__inner {
  background-color: #484545;
}

.el-switch__label {
  color: #FFFFFF;
}

.el-input__inner {
  color: #FFFFFF;
}
```
效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/a04910883e624d95a707f2138499578c.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/71f8f92004f4433a87435abcaea4027c.png)
样子有点小丑，美化一下：
```html
<template>
    <div>
        <el-row>
            <el-col :span="12">
                <el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽"></el-switch>
            </el-col>
            <el-col :span="6">
                <el-button v-if="draggable" @click="batchSave" size="mini">批量保存</el-button>
            </el-col>
            <el-col :span="6">
                <el-button type="danger" @click="batchDelete" size="mini">批量删除</el-button>
            </el-col>
        </el-row>
        <el-row>
            <el-input placeholder="输入关键字进行过滤" v-model="filterText">
            </el-input>
        </el-row>
        <el-tree class="filter-tree" :data="depts" :props="defaultProps" :filter-node-method="filterNode" ref="tree"
            @node-click="nodeclick" :highlight-current="true" node-key="id" :default-expanded-keys="expandedKey"
            :draggable="draggable" :expand-on-click-node="false" :allow-drop="allowDrop" @node-drop="handleDrop"
            show-checkbox>
            <span class="custom-tree-node" slot-scope="{ node, data }">
                <span>{{ node.label }}</span>
                <span>
                    <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">添加
                    </el-button>
                    <el-button type="text" size="mini" @click="edit(data)">编辑</el-button>
                    <el-button v-if="node.childNodes.length == 0" type="text" size="mini"
                        @click="() => remove(node, data)">删除</el-button>
                </span>
            </span>
        </el-tree>
        <el-dialog :title="title" :visible.sync="dialogVisible" width="30%" :close-on-click-modal="false"
            :modal-append-to-body="false">
            <el-form :model="dept">
                <el-form-item label="部门名称">
                    <el-input v-model="dept.name" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitData">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {

    data() {
        return {
            filterText: '',
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            depts: [], // 部门表
            title: '',
            dialogType: "", //edit,add
            dialogVisible: false,
            dept: {
                id: null,
                name: '',
                pId: 0,
                level: 0,
                showStatus: 1,
                sort: 0,
            },
            expandedKey: [], // 当前展示的树形
            draggable: false, // 拖拽
            pId: [], // 拖拽id记录
            maxLevel: 0, // 父层级
            updateNodes: [], //要更新的节点 
        };
    },
    // 检测data中数据变化
    watch: {
        filterText(val) {
            this.$refs.tree.filter(val);
        }
    },

    created() {
        this.getDepts();
    },
    methods: {
        // 树节点过滤
        filterNode(value, data) {
            if (!value) return true;
            return data.name.indexOf(value) !== -1;
        },
        // 查询菜单
        getDepts() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/list/tree"),
                method: "get"
            }).then(({ data }) => {
                this.depts = data.data;
            });
        },
        //向父组件发送事件
        nodeclick(data, node, component) {
            //向父组件发送事件；
            this.$emit("tree-node-click", data, node, component);
        },
        // 编辑数据
        edit(data) {
            console.log("要修改的数据", data);
            this.dialogType = "edit";
            this.title = "修改部门";
            this.dialogVisible = true;
            //发送请求获取当前节点最新的数据
            this.$http({
                url: this.$http.adornUrl(`/employee/dept/info/${data.id}`),
                method: "get"
            }).then(({ data }) => {
                //请求成功
                console.log("要回显的数据", data);
                this.dept.name = data.dept.name;
                this.dept.id = data.dept.id;
                this.dept.pId = data.dept.pId;
                this.dept.level = data.dept.level;
                this.dept.sort = data.dept.sort;
                this.dept.showStatus = data.dept.showStatus;
            });
        },
        // 添加
        append(data) {
            console.log("append", data);
            // 弹窗设置
            this.dialogType = "add";
            this.title = "添加部门";
            this.dialogVisible = true;
            // 初始化表单
            this.dept.pId = data.id;
            this.dept.level = data.catLevel * 1 + 1;
            this.dept.id = null;
            this.dept.name = "";
            this.dept.sort = 0;
            this.dept.showStatus = 1;
        },
        // 移除
        remove(node, data) {
            var ids = [data.id];
            this.$confirm(`是否删除【${data.name}】部门?`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$http({
                        url: this.$http.adornUrl("/employee/dept/delete"),
                        method: "post",
                        data: this.$http.adornData(ids, false)
                    }).then(() => {
                        this.$message({
                            message: "部门删除成功",
                            type: "success"
                        });
                        //刷新树形列表
                        this.getDepts();
                        //设置需要默认展开的部门
                        this.expandedKey = [node.parent.data.id];
                    });
                })
                .catch(() => { });
        },
        // 提交数据
        submitData() {
            if (this.dialogType == "add") {
                this.addCategory();
            }
            if (this.dialogType == "edit") {
                this.editCategory();
            }
        },
        //修改三级分类数据
        editCategory() {
            var { id, name } = this.dept;
            this.$http({
                url: this.$http.adornUrl("/employee/dept/update"),
                method: "post",
                data: this.$http.adornData({ id, name }, false)
            }).then(() => {
                this.$message({
                    message: "部门修改成功",
                    type: "success"
                });
                //关闭对话框
                this.dialogVisible = false;
                //刷新出新的部门
                this.getDepts();
                //设置需要默认展开的部门
                this.expandedKey = [this.dept.pId];
            });
        },
        //添加三级分类
        addCategory() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/save"),
                method: "post",
                data: this.$http.adornData(this.dept, false)
            }).then(() => {
                this.$message({
                    message: "部门保存成功",
                    type: "success"
                });
                //关闭对话框
                this.dialogVisible = false;
                //刷新树形列表
                this.getDepts();
                //设置需要默认展开的部门
                this.expandedKey = [this.dept.pId];
            });
        },
        // 拖拽规则
        allowDrop(draggingNode, dropNode, type) {
            //1、被拖动的当前节点以及所在的父节点总层数不能大于3

            //1）、被拖动的当前节点总层数
            console.log("allowDrop:", draggingNode, dropNode, type);
            //
            this.countNodeLevel(draggingNode);
            //当前正在拖动的节点+父节点所在的深度不大于3即可
            let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;
            console.log("深度：", deep);

            //   this.maxLevel
            if (type == "inner") {
                // console.log(
                //   `this.maxLevel：${this.maxLevel}；draggingNode.data.catLevel：${draggingNode.data.catLevel}；dropNode.level：${dropNode.level}`
                // );
                return deep + dropNode.level <= 3;
            } else {
                return deep + dropNode.parent.level <= 3;
            }
        },
        // 求最大深度
        countNodeLevel(node) {
            //找到所有子节点，求出最大深度
            if (node.childNodes != null && node.childNodes.length > 0) {
                for (let i = 0; i < node.childNodes.length; i++) {
                    if (node.childNodes[i].level > this.maxLevel) {
                        this.maxLevel = node.childNodes[i].level;
                    }
                    this.countNodeLevel(node.childNodes[i]);
                }
            }
        },
        handleDrop(draggingNode, dropNode, dropType) {
            console.log("handleDrop: ", draggingNode, dropNode, dropType);
            //1、当前节点最新的父节点id
            let pId = 0;
            let siblings = null;
            if (dropType == "before" || dropType == "after") {
                pId =
                    dropNode.parent.data.id == undefined
                        ? 0
                        : dropNode.parent.data.id;
                siblings = dropNode.parent.childNodes;
            } else {
                pId = dropNode.data.id;
                siblings = dropNode.childNodes;
            }
            this.pId.push(pId);

            //2、当前拖拽节点的最新顺序，
            for (let i = 0; i < siblings.length; i++) {
                if (siblings[i].data.id == draggingNode.data.id) {
                    //如果遍历的是当前正在拖拽的节点
                    let level = draggingNode.level;
                    if (siblings[i].level != draggingNode.level) {
                        //当前节点的层级发生变化
                        level = siblings[i].level;
                        //修改他子节点的层级
                        this.updateChildNodeLevel(siblings[i]);
                    }
                    this.updateNodes.push({
                        id: siblings[i].data.id,
                        sort: i,
                        pId: pId,
                        level: level
                    });
                } else {
                    this.updateNodes.push({ id: siblings[i].data.id, sort: i });
                }
            }
            //3、当前拖拽节点的最新层级
            console.log("updateNodes", this.updateNodes);
        },
        // 更新子部门层级
        updateChildNodeLevel(node) {
            if (node.childNodes.length > 0) {
                for (let i = 0; i < node.childNodes.length; i++) {
                    var cNode = node.childNodes[i].data;
                    this.updateNodes.push({
                        id: cNode.id,
                        level: node.childNodes[i].level
                    });
                    this.updateChildNodeLevel(node.childNodes[i]);
                }
            }
        },
        // 批量保存
        batchSave() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/update/sort"),
                method: "post",
                data: this.$http.adornData(this.updateNodes, false)
            }).then(() => {
                this.$message({
                    message: "部门顺序修改成功",
                    type: "success"
                });
                //刷新出新的部门
                this.getDepts();
                //设置需要默认展开的部门
                this.expandedKey = this.pCid;
                this.updateNodes = [];
                this.maxLevel = 0;
                // this.pCid = 0;
            });
        },
        // 批量删除
        batchDelete() {
            let ids = [];
            let checkedNodes = this.$refs.menuTree.getCheckedNodes();
            console.log("被选中的元素", checkedNodes);
            for (let i = 0; i < checkedNodes.length; i++) {
                ids.push(checkedNodes[i].id);
            }
            this.$confirm(`是否批量删除【${ids}】部门?`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$http({
                        url: this.$http.adornUrl("/employee/dept/delete"),
                        method: "post",
                        data: this.$http.adornData(ids, false)
                    }).then(() => {
                        this.$message({
                            message: "部门批量删除成功",
                            type: "success"
                        });
                        this.getDepts();
                    });
                })
                .catch(() => { });
        }
    },


};
</script>
<style lang="scss" scoped>
.el-row {
    margin-bottom: 10px;

    &:last-child {
        margin-bottom: 0;
    }
}
</style>
```
效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/b598199a84464532978ad7e8b8f4a785.png)
***
右图统计：（这里小航带大家把常用的统计给大家过一遍）

1.部门人数统计   
2.部门成员信息完善度   
3.部门成员词云统计  
4.部门成员注册日期统计

[DavaV](http://datav.jiaminghi.com/guide/)
[ECharts](https://echarts.apache.org/zh/index.html)

我们新建一份mychart.vue
```html
<template>
    <div id="mychart">
        <dv-border-box-1>
            <div class="up">
                <div class="bg-color-black item ml-1 mt-2 pl-3" v-for="item in titleItem" :key="item.title">
                    <p class="ml-3 colorBlue fw-b fs-l">{{ item.title }}</p>
                    <div>
                        <dv-digital-flop class="dv-dig-flop" :config="item.number" />
                    </div>
                </div>
            </div>
        </dv-border-box-1>

        <div class="down">
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            titleItem: [
                {
                    title: '子部门',
                    number: {
                        number: [12],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                },
                {
                    title: '部门成员',
                    number: {
                        number: [18],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                },
                {
                    title: '本月部员',
                    number: {
                        number: [2],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                }
            ],
        }
    },
}
</script>

<style lang="scss" scoped>
#mychart {
    display: flex;
    flex-direction: column;

    .up {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;

        .item {
            border-radius: 6px;
            padding-top: 8px;
            margin-top: 8px;
            width: 32%;
            height: 100px;

            .dv-dig-flop {
                width: 250px;
                height: 50px;
            }
        }
    }

    .down {
        padding-top: 20px;
    }
}
</style>
```
导入并使用：
![在这里插入图片描述](https://img-blog.csdnimg.cn/9f8971be87394827b5757b00cf3b4963.png)

开始编写后端：
新建ChartController：
```java
package com.tyut.employee.controller;

import com.tyut.employee.service.ChartService;
import com.tyut.employee.utils.R;
import com.tyut.employee.vo.ChartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author xh
 * @Date 2022/6/1
 */
@RestController
public class ChartController {

    @Autowired
    private ChartService chartService;

    /**
     * 统计子部门个数，部门成员个数，本月部员新加入个数
     *
     */
    @GetMapping("/getCountNum")
    public R getCountNum(@RequestParam Map<String, String> params) {
        // 统计子部门个数，部门成员个数，本月部员新加入个数
        List<ChartVo> chartVos = chartService.getCountNum(params);
        return R.ok().put("data", chartVos);
    }
}

```
ChartServiceImpl:（别忘了加注解）
```java
package com.tyut.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tyut.employee.service.ChartService;
import com.tyut.employee.service.DeptService;
import com.tyut.employee.service.UserDeptService;
import com.tyut.employee.vo.ChartVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xh
 * @Date 2022/6/1
 */
@Service("chartService")
public class ChartServiceImpl implements ChartService {

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserDeptService userDeptService;

    // 统计子部门个数，部门成员个数，本月部员新加入个数
    @Override
    public List<ChartVo> getCountNum(Map<String, String> params) {
        int id = 0;
        if(!StringUtils.isEmpty(params.get("id"))) {
            try {
                id = Integer.parseInt(params.get("id"));
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        Integer deptNums = deptService.getDeptNumById(id);
        Integer userNums = userDeptService.getUserNumsByDeptId(id);
        Integer monthUserNums = userDeptService.staMonthUserNums(id);
        List<ChartVo> chartVos = new ArrayList<>();
        chartVos.add(new ChartVo("子部门", deptNums));
        chartVos.add(new ChartVo("部门成员", userNums));
        chartVos.add(new ChartVo("本月部员", monthUserNums));
        return chartVos;

    }
}

```
新增了三个方法：
deptService.getDeptNumById(id);
```java
/**
     * 根据id统计部门数
     */
    @Override
    public Integer getDeptNumById(int i) {
        return baseMapper.selectCount(new QueryWrapper<DeptEntity>().eq("p_id", i));
    }
```
userDeptService.getUserNumsByDeptId(id);
```java
@Override
    public Integer getUserNumsByDeptId(Integer id) {
        QueryWrapper<UserDeptEntity> queryWrapper = new QueryWrapper<>();
        if(id != 0) {
            queryWrapper.eq("dept_id", id);
        }
        return baseMapper.selectCount(queryWrapper);
    }
```
userDeptService.staMonthUserNums(id);
```java'
/**
     * 统计本月新增部员数量
     */
    @Override
    public Integer staMonthUserNums(Integer id) {
        LocalDate localDate = LocalDate.now();
        LocalDate startTime = localDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endTime = localDate.with(TemporalAdjusters.lastDayOfMonth());
        log.info("startTime:{}, endTime:{}", startTime, endTime);
        QueryWrapper<UserDeptEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("create_time", startTime, endTime);
        if(id != 0) {
            queryWrapper.eq("dept_id", id);
        }
        return baseMapper.selectCount(queryWrapper);
    }


这里稍稍做了日志打印
UserDeptServiceImpl   : startTime:2022-06-01, endTime:2022-06-30
```
数据库补充一个create_time根据当前时间戳更新（也可以通过mybatis插件填充）
```
CURRENT_TIMESTAMP
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/69deeda954c54ccfb75151ebd290820b.png)
测试：
![在这里插入图片描述](https://img-blog.csdnimg.cn/6ab3fe1aefcb4aed98ea353597aba643.png)
测试接口：
![在这里插入图片描述](https://img-blog.csdnimg.cn/f6996091ac2a4e679a364c6ffe954ccd.png)
```json
{
    "msg": "成功",
    "code": 200,
    "data": [
        {
            "title": "子部门",
            "number": 2
        },
        {
            "title": "部门成员",
            "number": 1
        },
        {
            "title": "本月部员",
            "number": 1
        }
    ]
}
```
前端调用：

```html
<template>
    <div id="mychart">
        <dv-border-box-1>
            <div class="up">
                <div class="bg-color-black item ml-1 mt-2 pl-3" v-for="item in titleItem" :key="item.title">
                    <p class="ml-3 colorBlue fw-b fs-l">{{ item.title }}</p>
                    <div>
                        <dv-digital-flop class="dv-dig-flop" :config="item.number" />
                    </div>
                </div>
            </div>
        </dv-border-box-1>

        <div class="down">
            <userdeptVue></userdeptVue>
        </div>
    </div>
</template>

<script>
import userdeptVue from './userdept'
export default {
    components: { userdeptVue },
    data() {
        return {
            titleItem: [
                {
                    title: '子部门',
                    number: {
                        number: [12],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                },
                {
                    title: '部门成员',
                    number: {
                        number: [18],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                },
                {
                    title: '本月部员',
                    number: {
                        number: [2],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                }
            ],
            id: 0
        }
    },
    mounted() {
        this.init(this.id);
    },
    methods: {
        async init(id) {
            /**拿取后台数据*/
            const { data } = await this.$http({
                url: this.$http.adornUrl('/getCountNum'),
                method: 'get',
                params: this.$http.adornParams({
                    'id': id,
                })
            })
            if (data && data.code === 200) {
                let chartVos = data.data
                chartVos.map((item, index) => {
                    this.titleItem[index].title = item.title;
                    this.titleItem[index].number.number[0] = item.number;
                    /**
                    * 使用ES6拓展运算符生成新的props对象
                    * 组件侦知数据变化 自动刷新状态
                    */
                    this.titleItem[index].number = { ...this.titleItem[index].number }
                })
            } else {
                this.$message.error(data.msg)
                return;
            }
        }
    }
}
</script>
```
踩坑：使用dataV组件库时改变数据视图不主动刷新
原因：dataV里面的组件props均`未设置deep监听`，刷新props时，要直接生成新的props对象（基础数据类型除外），或完成赋值操作后使用ES6拓展运算符生成新的props对象（this.someProps = { …this.someProps }。
解决方案：
```js
async init(id) {
            /**拿取后台数据*/
            const { data } = await this.$http({
                url: this.$http.adornUrl('/getCountNum'),
                method: 'get',
                params: this.$http.adornParams({
                    'id': id,
                })
            })
            if (data && data.code === 200) {
                let chartVos = data.data
                chartVos.map((item, index) => {
                    this.titleItem[index].title = item.title;
                    this.titleItem[index].number.number[0] = item.number;
                    /**
                    * 使用ES6拓展运算符生成新的props对象
                    * 组件侦知数据变化 自动刷新状态
                    */
                    this.titleItem[index].number = { ...this.titleItem[index].number }
                })
            } else {
                this.$message.error(data.msg)
                return;
            }
        }
```
效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/61a2954486f444938147bf12b7f3f8b8.png)
最后我们再修改一下下面的表格叭：

我们先对表格数据重新封装：

新建一个封装Vo对象：
UserDeptVo:
```java
package com.tyut.employee.vo;

import lombok.Data;

/**
 * @author xh
 * @Date 2022/6/2
 */
@Data
public class UserDeptVo {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 部门ID
     */
    private Integer deptId;
    /**
     * 部门名
     */
    private String deptName;
    /**
     * 部门录入时间
     */
    private Date createTime;

}

```

UserDeptController:(这里推荐大家对比学习，上面的接口标记为过时接口)
```java
/**
     * 列表， 接口过时
     */
    @GetMapping("/list")
    @Deprecated
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userDeptService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 根据部门ID，查询用户部门关系表
     */
    @GetMapping("/list/{deptId}")
    public R listUserAndDept(@RequestParam Map<String, Object> params, @PathVariable("deptId") Integer deptId){
        PageUtils page = userDeptService.queryUserAndDept(params, deptId);
        return R.ok().put("page", page);
    }
```

```java
@Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserDeptEntity> page = this.page(
                new Query<UserDeptEntity>().getPage(params),
                new QueryWrapper<UserDeptEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 根据部门ID，查询用户部门关系表
     */
    @Override
    public PageUtils queryUserAndDept(Map<String, Object> params, Integer deptId) {
        QueryWrapper<UserDeptEntity> queryWrapper = new QueryWrapper<UserDeptEntity>();
        if(deptId != 0) {
            queryWrapper.eq("dept_id", deptId);
        }
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)) {
            queryWrapper.and((wrapper -> wrapper.like("user_id", key)));
        }
        IPage<UserDeptEntity> page = this.page(new Query<UserDeptEntity>().getPage(params), queryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        List<UserDeptVo> userDeptVos = page.getRecords().stream().map(userDeptEntity -> {
            UserDeptVo userDeptVo = new UserDeptVo();
            BeanUtils.copyProperties(userDeptEntity, userDeptVo);
            userDeptVo.setUsername(userService.getUserNameByUserId(userDeptVo.getUserId()));
            userDeptVo.setDeptName(deptService.getDeptNameByDeptId(userDeptVo.getDeptId()));
            return userDeptVo;
        }).collect(Collectors.toList());
        pageUtils.setList(userDeptVos);
        return pageUtils;
    }
```
使用ApiFOx测试接口：
```json
{
    "msg": "成功",
    "code": 200,
    "page": {
        "totalCount": 1,
        "pageSize": 10,
        "totalPage": 1,
        "currPage": 1,
        "list": [
            {
                "id": 1,
                "userId": 1,
                "username": "小航",
                "deptId": 1,
                "deptName": "11112",
                "createTime": "2022-06-02"
            }
        ]
    }
}
```
对接前端：

userdeot.vue
```html
<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="主键ID">
      </el-table-column>
      <el-table-column
        prop="userId"
        header-align="center"
        align="center"
        label="员工ID">
      </el-table-column>
      <el-table-column
        prop="username"
        header-align="center"
        align="center"
        label="员工名称">
      </el-table-column>
      <el-table-column
        prop="deptId"
        header-align="center"
        align="center"
        label="部门ID">
      </el-table-column>
      <el-table-column
        prop="deptName"
        header-align="center"
        align="center"
        label="部门名称">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="部门录入时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './userdept-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        deptId: 1
      }
    },
    components: {
      AddOrUpdate
    },
    created   () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl(`/employee/userdept/list/${this.deptId}`),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/employee/userdept/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>

```
测试效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/e931f173e5784fce82848b3d3470d5ff.png)
我们最后再对新增页面进行编辑：
```html
<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    :modal-append-to-body="false">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="员工ID" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="员工ID"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userId: '',
          deptId: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '员工ID不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id, deptId) {
        console.log(deptId)
        this.dataForm.id = id || 0
        this.dataForm.deptId = deptId
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/employee/userdept/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.dataForm.userId = data.userDept.userId
                this.dataForm.deptId = data.userDept.deptId
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/employee/userdept/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'deptId': this.dataForm.deptId
              })
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>

```
![在这里插入图片描述](https://img-blog.csdnimg.cn/95bd3397284c4f86893acf0cb303ba9d.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/092b0d8c879a487e87f883896d2e96f3.png)
最后我们再完善一下树表联动：
dept.vue
```js
<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <tree-dept @tree-node-click="treenodeclick"></tree-dept>
    </el-col>
    <el-col :span="18">
        <mychart ref="mychart"></mychart>
    </el-col>
  </el-row>
</template>

// 感知树节点被点击
    treenodeclick(data) {
      console.log("父节点点击了：", data.id)
      this.deptId = data.id
      this.$refs.mychart.init(this.deptId);
    },
```
mychart.vue
```js
<template>
    <div id="mychart">
        <dv-border-box-1>
            <div class="up">
                <div class="bg-color-black item ml-1 mt-2 pl-3" v-for="item in titleItem" :key="item.title">
                    <p class="ml-3 colorBlue fw-b fs-l">{{ item.title }}</p>
                    <div>
                        <dv-digital-flop class="dv-dig-flop" :config="item.number" />
                    </div>
                </div>
            </div>
        </dv-border-box-1>

        <div class="down">
            <userdeptVue ref="userdeptVue"></userdeptVue>
        </div>
    </div>
</template>

async init(id) {
            /**拿取后台数据*/
            const { data } = await this.$http({
                url: this.$http.adornUrl('/getCountNum'),
                method: 'get',
                params: this.$http.adornParams({
                    'id': id,
                })
            })
            if (data && data.code === 200) {
                let chartVos = data.data
                chartVos.map((item, index) => {
                    this.titleItem[index].title = item.title;
                    this.titleItem[index].number.number[0] = item.number;
                    /**
                    * 使用ES6拓展运算符生成新的props对象
                    * 组件侦知数据变化 自动刷新状态
                    */
                    this.titleItem[index].number = { ...this.titleItem[index].number }
                })
            } else {
                this.$message.error(data.msg)
                return;
            }
            this.$refs.userdeptVue.getDataList(id);
        }
```
userdept.vue
```html
data () {
      return {
        deptId: 0
      }
    },

created() {
      this.getDataList(this.deptId)
    },


// 获取数据列表
      getDataList (id) {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl(`/employee/userdept/list/${id}`),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
            this.deptId = id;
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
```
***

效果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/ff0c19e78b0b490d8f905d84b3d1c6ea.png)

最终代码：

最终代码：
mychat.vue

```html
<template>
    <div id="mychart">
        <dv-border-box-1>
            <div class="up">
                <div class="bg-color-black item ml-1 mt-2 pl-3" v-for="item in titleItem" :key="item.title">
                    <p class="ml-3 colorBlue fw-b fs-l">{{ item.title }}</p>
                    <div>
                        <dv-digital-flop class="dv-dig-flop" :config="item.number" />
                    </div>
                </div>
            </div>
        </dv-border-box-1>

        <div class="down">
            <userdeptVue ref="userdeptVue"></userdeptVue>
        </div>
    </div>
</template>

<script>
import userdeptVue from './userdept'
export default {
    components: { userdeptVue },
    data() {
        return {
            titleItem: [
                {
                    title: '子部门',
                    number: {
                        number: [12],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                },
                {
                    title: '部门成员',
                    number: {
                        number: [18],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                },
                {
                    title: '本月部员',
                    number: {
                        number: [2],
                        content: '{nt}个',
                        style: {
                            fontSize: 36
                        }
                    }
                }
            ],
            id: 0
        }
    },
    mounted() {
        this.init(this.id);
    },
    methods: {
        async init(id) {
            /**拿取后台数据*/
            const { data } = await this.$http({
                url: this.$http.adornUrl('/getCountNum'),
                method: 'get',
                params: this.$http.adornParams({
                    'id': id,
                })
            })
            if (data && data.code === 200) {
                let chartVos = data.data
                chartVos.map((item, index) => {
                    this.titleItem[index].title = item.title;
                    this.titleItem[index].number.number[0] = item.number;
                    /**
                    * 使用ES6拓展运算符生成新的props对象
                    * 组件侦知数据变化 自动刷新状态
                    */
                    this.titleItem[index].number = { ...this.titleItem[index].number }
                })
            } else {
                this.$message.error(data.msg)
                return;
            }
            this.$refs.userdeptVue.getDataList(id);
        }
    }
}
</script>

<style lang="scss" scoped>
#mychart {
    display: flex;
    flex-direction: column;

    .up {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;

        .item {
            border-radius: 6px;
            padding-top: 8px;
            margin-top: 8px;
            width: 32%;
            height: 100px;

            .dv-dig-flop {
                width: 250px;
                height: 50px;
            }
        }
    }

    .down {
        padding-top: 20px;
    }
}
</style>
```
userdept.vue
```html
<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="主键ID">
      </el-table-column>
      <el-table-column
        prop="userId"
        header-align="center"
        align="center"
        label="员工ID">
      </el-table-column>
      <el-table-column
        prop="username"
        header-align="center"
        align="center"
        label="员工名称">
      </el-table-column>
      <el-table-column
        prop="deptId"
        header-align="center"
        align="center"
        label="部门ID">
      </el-table-column>
      <el-table-column
        prop="deptName"
        header-align="center"
        align="center"
        label="部门名称">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="部门录入时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './userdept-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        deptId: 0
      }
    },
    components: {
      AddOrUpdate
    },
    created() {
      this.getDataList(this.deptId)
    },
    methods: {
      // 获取数据列表
      getDataList (id) {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl(`/employee/userdept/list/${id}`),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
            this.deptId = id;
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        console.log(id, this.deptId)
        let _this = this;
        this.$nextTick(() => {
          // 箭头函数this指向了widows
          this.$refs.addOrUpdate.init(id, _this.deptId)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/employee/userdept/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>

```
userdept-add-or-update.vue
```html
<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    :modal-append-to-body="false">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="员工ID" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="员工ID"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userId: '',
          deptId: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '员工ID不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id, deptId) {
        console.log(deptId)
        this.dataForm.id = id || 0
        this.dataForm.deptId = deptId
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/employee/userdept/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.dataForm.userId = data.userDept.userId
                this.dataForm.deptId = data.userDept.deptId
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/employee/userdept/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'deptId': this.dataForm.deptId
              })
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>

```
TreeDept/index.vue
```html
<template>
    <div>
        <el-row>
            <el-col :span="12">
                <el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽"></el-switch>
            </el-col>
            <el-col :span="6">
                <el-button v-if="draggable" @click="batchSave" size="mini">批量保存</el-button>
            </el-col>
            <el-col :span="6">
                <el-button type="danger" @click="batchDelete" size="mini">批量删除</el-button>
            </el-col>
        </el-row>
        <el-row>
            <el-input placeholder="输入关键字进行过滤" v-model="filterText">
            </el-input>
        </el-row>
        <el-tree class="filter-tree" :data="depts" :props="defaultProps" :filter-node-method="filterNode" ref="tree"
            @node-click="nodeclick" :highlight-current="true" node-key="id" :default-expanded-keys="expandedKey"
            :draggable="draggable" :expand-on-click-node="false" :allow-drop="allowDrop" @node-drop="handleDrop"
            show-checkbox>
            <span class="custom-tree-node" slot-scope="{ node, data }">
                <span>{{ node.label }}</span>
                <span>
                    <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">添加
                    </el-button>
                    <el-button type="text" size="mini" @click="edit(data)">编辑</el-button>
                    <el-button v-if="node.childNodes.length == 0" type="text" size="mini"
                        @click="() => remove(node, data)">删除</el-button>
                </span>
            </span>
        </el-tree>
        <el-dialog :title="title" :visible.sync="dialogVisible" width="30%" :close-on-click-modal="false"
            :modal-append-to-body="false">
            <el-form :model="dept">
                <el-form-item label="部门名称">
                    <el-input v-model="dept.name" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitData">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {

    data() {
        return {
            filterText: '',
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            depts: [], // 部门表
            title: '',
            dialogType: "", //edit,add
            dialogVisible: false,
            dept: {
                id: null,
                name: '',
                pId: 0,
                level: 0,
                showStatus: 1,
                sort: 0,
            },
            expandedKey: [], // 当前展示的树形
            draggable: false, // 拖拽
            pId: [], // 拖拽id记录
            maxLevel: 0, // 父层级
            updateNodes: [], //要更新的节点 
        };
    },
    // 检测data中数据变化
    watch: {
        filterText(val) {
            this.$refs.tree.filter(val);
        }
    },

    created() {
        this.getDepts();
    },
    methods: {
        // 树节点过滤
        filterNode(value, data) {
            if (!value) return true;
            return data.name.indexOf(value) !== -1;
        },
        // 查询菜单
        getDepts() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/list/tree"),
                method: "get"
            }).then(({ data }) => {
                this.depts = data.data;
            });
        },
        //向父组件发送事件
        nodeclick(data, node, component) {
            //向父组件发送事件；
            this.$emit("tree-node-click", data, node, component);
        },
        // 编辑数据
        edit(data) {
            console.log("要修改的数据", data);
            this.dialogType = "edit";
            this.title = "修改部门";
            this.dialogVisible = true;
            //发送请求获取当前节点最新的数据
            this.$http({
                url: this.$http.adornUrl(`/employee/dept/info/${data.id}`),
                method: "get"
            }).then(({ data }) => {
                //请求成功
                console.log("要回显的数据", data);
                this.dept.name = data.dept.name;
                this.dept.id = data.dept.id;
                this.dept.pId = data.dept.pId;
                this.dept.level = data.dept.level;
                this.dept.sort = data.dept.sort;
                this.dept.showStatus = data.dept.showStatus;
            });
        },
        // 添加
        append(data) {
            console.log("append", data);
            // 弹窗设置
            this.dialogType = "add";
            this.title = "添加部门";
            this.dialogVisible = true;
            // 初始化表单
            this.dept.pId = data.id;
            this.dept.level = data.catLevel * 1 + 1;
            this.dept.id = null;
            this.dept.name = "";
            this.dept.sort = 0;
            this.dept.showStatus = 1;
        },
        // 移除
        remove(node, data) {
            var ids = [data.id];
            this.$confirm(`是否删除【${data.name}】部门?`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$http({
                        url: this.$http.adornUrl("/employee/dept/delete"),
                        method: "post",
                        data: this.$http.adornData(ids, false)
                    }).then(() => {
                        this.$message({
                            message: "部门删除成功",
                            type: "success"
                        });
                        //刷新树形列表
                        this.getDepts();
                        //设置需要默认展开的部门
                        this.expandedKey = [node.parent.data.id];
                    });
                })
                .catch(() => { });
        },
        // 提交数据
        submitData() {
            if (this.dialogType == "add") {
                this.addCategory();
            }
            if (this.dialogType == "edit") {
                this.editCategory();
            }
        },
        //修改三级分类数据
        editCategory() {
            var { id, name } = this.dept;
            this.$http({
                url: this.$http.adornUrl("/employee/dept/update"),
                method: "post",
                data: this.$http.adornData({ id, name }, false)
            }).then(() => {
                this.$message({
                    message: "部门修改成功",
                    type: "success"
                });
                //关闭对话框
                this.dialogVisible = false;
                //刷新出新的部门
                this.getDepts();
                //设置需要默认展开的部门
                this.expandedKey = [this.dept.pId];
            });
        },
        //添加三级分类
        addCategory() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/save"),
                method: "post",
                data: this.$http.adornData(this.dept, false)
            }).then(() => {
                this.$message({
                    message: "部门保存成功",
                    type: "success"
                });
                //关闭对话框
                this.dialogVisible = false;
                //刷新树形列表
                this.getDepts();
                //设置需要默认展开的部门
                this.expandedKey = [this.dept.pId];
            });
        },
        // 拖拽规则
        allowDrop(draggingNode, dropNode, type) {
            //1、被拖动的当前节点以及所在的父节点总层数不能大于3

            //1）、被拖动的当前节点总层数
            console.log("allowDrop:", draggingNode, dropNode, type);
            //
            this.countNodeLevel(draggingNode);
            //当前正在拖动的节点+父节点所在的深度不大于3即可
            let deep = Math.abs(this.maxLevel - draggingNode.level) + 1;
            console.log("深度：", deep);

            //   this.maxLevel
            if (type == "inner") {
                // console.log(
                //   `this.maxLevel：${this.maxLevel}；draggingNode.data.catLevel：${draggingNode.data.catLevel}；dropNode.level：${dropNode.level}`
                // );
                return deep + dropNode.level <= 3;
            } else {
                return deep + dropNode.parent.level <= 3;
            }
        },
        // 求最大深度
        countNodeLevel(node) {
            //找到所有子节点，求出最大深度
            if (node.childNodes != null && node.childNodes.length > 0) {
                for (let i = 0; i < node.childNodes.length; i++) {
                    if (node.childNodes[i].level > this.maxLevel) {
                        this.maxLevel = node.childNodes[i].level;
                    }
                    this.countNodeLevel(node.childNodes[i]);
                }
            }
        },
        handleDrop(draggingNode, dropNode, dropType) {
            console.log("handleDrop: ", draggingNode, dropNode, dropType);
            //1、当前节点最新的父节点id
            let pId = 0;
            let siblings = null;
            if (dropType == "before" || dropType == "after") {
                pId =
                    dropNode.parent.data.id == undefined
                        ? 0
                        : dropNode.parent.data.id;
                siblings = dropNode.parent.childNodes;
            } else {
                pId = dropNode.data.id;
                siblings = dropNode.childNodes;
            }
            this.pId.push(pId);

            //2、当前拖拽节点的最新顺序，
            for (let i = 0; i < siblings.length; i++) {
                if (siblings[i].data.id == draggingNode.data.id) {
                    //如果遍历的是当前正在拖拽的节点
                    let level = draggingNode.level;
                    if (siblings[i].level != draggingNode.level) {
                        //当前节点的层级发生变化
                        level = siblings[i].level;
                        //修改他子节点的层级
                        this.updateChildNodeLevel(siblings[i]);
                    }
                    this.updateNodes.push({
                        id: siblings[i].data.id,
                        sort: i,
                        pId: pId,
                        level: level
                    });
                } else {
                    this.updateNodes.push({ id: siblings[i].data.id, sort: i });
                }
            }
            //3、当前拖拽节点的最新层级
            console.log("updateNodes", this.updateNodes);
        },
        // 更新子部门层级
        updateChildNodeLevel(node) {
            if (node.childNodes.length > 0) {
                for (let i = 0; i < node.childNodes.length; i++) {
                    var cNode = node.childNodes[i].data;
                    this.updateNodes.push({
                        id: cNode.id,
                        level: node.childNodes[i].level
                    });
                    this.updateChildNodeLevel(node.childNodes[i]);
                }
            }
        },
        // 批量保存
        batchSave() {
            this.$http({
                url: this.$http.adornUrl("/employee/dept/update/sort"),
                method: "post",
                data: this.$http.adornData(this.updateNodes, false)
            }).then(() => {
                this.$message({
                    message: "部门顺序修改成功",
                    type: "success"
                });
                //刷新出新的部门
                this.getDepts();
                //设置需要默认展开的部门
                this.expandedKey = this.pCid;
                this.updateNodes = [];
                this.maxLevel = 0;
                // this.pCid = 0;
            });
        },
        // 批量删除
        batchDelete() {
            let ids = [];
            let checkedNodes = this.$refs.menuTree.getCheckedNodes();
            console.log("被选中的元素", checkedNodes);
            for (let i = 0; i < checkedNodes.length; i++) {
                ids.push(checkedNodes[i].id);
            }
            this.$confirm(`是否批量删除【${ids}】部门?`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$http({
                        url: this.$http.adornUrl("/employee/dept/delete"),
                        method: "post",
                        data: this.$http.adornData(ids, false)
                    }).then(() => {
                        this.$message({
                            message: "部门批量删除成功",
                            type: "success"
                        });
                        this.getDepts();
                    });
                })
                .catch(() => { });
        }
    },


};
</script>
<style lang="scss" scoped>
.el-row {
    margin-bottom: 10px;

    &:last-child {
        margin-bottom: 0;
    }
}
</style>
```
dept.vue
```html
<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <tree-dept @tree-node-click="treenodeclick"></tree-dept>
    </el-col>
    <el-col :span="18">
        <mychart ref="mychart"></mychart>
    </el-col>
  </el-row>
</template>

<script>
import TreeDept from '@/components/TreeDept/index'
import mychart from './mychart'
export default {
  data() {
    return {
      dataForm: {
        key: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      deptId: 0,
    }
  },
  components: {
    TreeDept, mychart
  },
  created() {
    this.getDataList()
  },
  methods: {
    // 感知树节点被点击
    treenodeclick(data) {
      console.log("父节点点击了：", data.id)
      this.deptId = data.id
      this.$refs.mychart.init(this.deptId);
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/employee/dept/list'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'key': this.dataForm.key
        })
      }).then(({ data }) => {
        if (data && data.code === 200) {
          this.dataList = data.page.list
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle(id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.id
      })
      this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/employee/dept/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({ data }) => {
          if (data && data.code === 200) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      })
    }
  }
}
</script>
```

