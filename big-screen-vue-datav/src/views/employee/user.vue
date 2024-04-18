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
