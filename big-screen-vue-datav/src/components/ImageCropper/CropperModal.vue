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
        },
        /**
         * 确认截图
         */
        okHandel() {
            const that = this
            that.confirmLoading = true
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
        },
        //移动框的事件
        realTime(data) {
            this.previews = data
        },
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


