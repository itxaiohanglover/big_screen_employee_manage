<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="姓名" prop="name">
      <el-input v-model="dataForm.name" placeholder="姓名"></el-input>
    </el-form-item>
    <el-form-item label="性别（0代表女1代表男，默认男）" prop="sex">
      <el-input v-model="dataForm.sex" placeholder="性别（0代表女1代表男，默认男）"></el-input>
    </el-form-item>
    <el-form-item label="出生日期" prop="birthday">
      <el-input v-model="dataForm.birthday" placeholder="出生日期"></el-input>
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
      <el-input v-model="dataForm.photo" placeholder="照片"></el-input>
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
  export default {
    data () {
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
          photo: '',
          resume: ''
        },
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
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/employee/user/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
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
      dataFormSubmit () {
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
            }).then(({data}) => {
              if (data && data.code === 0) {
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
