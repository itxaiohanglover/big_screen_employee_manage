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
        <!-- <el-input v-model="dataForm.resume" placeholder="简历"></el-input> -->
        <file-upload :fileUrl="dataForm.resume" @file-upload-success="fileUploadSuccess"></file-upload>
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
import FileUpload from '@/components/FileUpload/index.vue'
export default {
  components: { ImageCropper, FileUpload},
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
          { required: true, message: '性别不能为空', trigger: 'blur' }
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
    // 上传文件成功
    fileUploadSuccess(data) {
      this.dataForm.resume = data;
    },
    //上传操作结束
    cropClose() {
      console.log('上传操作结束')
    },
    //上传图片成功
    cropSuccess(data) {
      this.dataForm.photo = data.data
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
