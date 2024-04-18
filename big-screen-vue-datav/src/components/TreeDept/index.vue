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