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