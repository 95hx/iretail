<template>
    <div>
        <el-form :inline="true" :model="formItem" class="demo-form-inline">
            <el-form-item label="品类名称">
                <el-input v-model="formItem.categoryName" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="品类编码">
                <el-input v-model="formItem.categoryCode" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="父编码">
                <el-input v-model="formItem.parentCode" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="clickSearch">搜索</el-button>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="dialogTreeVisible=true">查看品类树</el-button>
        <div style="float: right">
            <el-button type="primary" @click="clickAdd">创建</el-button>
        </div>
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="categoryCode"
                    label="品类编码"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="categoryName"
                    label="品类名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="parentCode"
                    label="父编码"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="createTime"
                    label="创建时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="updateTime"
                    label="更新时间"
                    width="300">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="clickEdit(scope.row)" type="text" size="small">编辑</el-button>
                    <el-button @click="clickDelete(scope.row)" type="text" size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="block">
            <el-pagination
                    layout="total"
                    :total="page.total">
            </el-pagination>
        </div>
        <el-dialog
                :title="dialogTitle"
                :visible.sync="dialogVisible"
                width="30%"
                :close-on-press-escape="false"
                :close-on-click-modal="false"
                :show-close="false"
                :before-close="handleClose">
            <el-input v-model="category.name" placeholder="请输入品类名称"></el-input>
            <el-input v-model="category.code" placeholder="请输入品类编码"></el-input>
            <el-input v-model="category.parentCode" placeholder="请输入父编码"></el-input>
            <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogConfirm">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
                :destroy-on-close="true"
                :visible.sync="dialogTreeVisible">
            <el-tree
                    :props="categoryTree"
                    :load="loadNode"
                    style="float: left"
                    ref="tree"
                    lazy>
            </el-tree>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "category",
        created() {
            this.init();
        },
        methods: {
            update() {
                this.$axios.post('api/category/update', {
                    "id": this.category.id,
                    "categoryName": this.category.name,
                    "categoryCode": this.category.code,
                    "parentCode": this.category.parentCode
                }).then((response) => {
                    if (response.status == 'success') {
                        this.$message({
                            message: '更新成功',
                            type: 'success'
                        });
                        this.dialogVisible = false;
                        this.init();
                    } else {
                        this.$message.error('请求失败');
                    }
                }).catch((e) => {
                    this.$message.error('请求失败');
                });
            },
            save() {
                this.$axios.post('api/category/add', [{
                    "categoryName": this.category.name,
                    "categoryCode": this.category.code,
                    "parentCode": this.category.parentCode
                }]).then((response) => {
                    if (response.status == 'success') {
                        this.$message({
                            message: '保存成功',
                            type: 'success'
                        });
                        this.init();
                        this.dialogVisible = false;
                    } else {
                        this.$message.error('请求失败');
                    }
                }).catch((e) => {
                    this.$message.error('请求失败');
                });
            },
            dialogConfirm() {
                if (this.dialogTitle == '品类添加') {
                    this.save();
                } else {
                    this.update();
                }
            },
            handleClose(done) {
                this.$confirm('确认关闭？')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },
            clickSearch() {
                this.$axios.post('api/category/query-condition', {
                    "categoryName": this.formItem.categoryName,
                    "categoryCode": this.formItem.categoryCode,
                    "parentCode": this.formItem.parentCode,
                    "page": 1,
                    "size": 1000
                }).then((response) => {
                    this.tableData = response.result.rows;
                }).catch((e) => {
                    this.$message.error('请求失败');
                });
            },
            clickAdd() {
                this.dialogTitle = "品类添加";
                this.category.id = '';
                this.category.name = '';
                this.category.code = '';
                this.category.parentCode = '';
                this.dialogVisible = true;
            },
            clickDelete(row) {
                this.$confirm('此操作将永久删除该品类,不删除子品类,是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('api/category/delete', {
                        "id": row.id,
                    }).then((response) => {
                        if (response.status == 'success') {
                            this.init();
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                        } else {
                            this.$message({
                                type: 'error',
                                message: '删除失败!'
                            });
                        }
                    }).catch((e) => {
                        this.$message.error('请求失败');
                    });
                });
            },
            clickEdit(row) {
                this.category.id = row.id;
                this.category.name = row.categoryName;
                this.category.code = row.categoryCode;
                this.category.parentCode = row.parentCode;
                this.dialogTitle = "品类更新";
                this.dialogVisible = true;
            },
            loadNode(node, resolve) {
                if (node.level === 0) {
                    return resolve([{categoryName: '所有品类', categoryCode: 'root'}]);
                }
                this.$axios.post('api/category/query', {
                    "parentCode": node.data.categoryCode,
                    "page": 1,
                    "size": 1000
                }).then((response) => {
                    if (response.status == 'success') {
                        resolve(response.result.rows)
                    } else {
                        this.$message.error('请求失败');
                    }
                }).catch((e) => {
                    this.$message.error('请求失败');
                });
            },
            init() {
                this.$axios.post('api/category/query-condition', {
                    "page": 1,
                    "size": 1000
                }).then((response) => {
                    this.tableData = response.result.rows;
                    this.page.total = response.result.total
                }).catch((e) => {
                    this.$message.error('请求失败');
                });
            }
        },
        data() {
            return {
                dialogTreeVisible: false,
                categoryTree: {
                    label: 'categoryName',
                    children: 'childList',
                    isLeaf: 'leaf'
                },
                page: {total: 0},
                formItem: {
                    categoryName: '',
                    categoryCode: '',
                    parentCode: ''
                },
                category: {
                    id: '',
                    name: '',
                    code: '',
                    parentCode: '',
                },
                dialogVisible: false,
                dialogTitle: '',
                tableData: [
                    [{id: '', categoryCode: "", categoryName: "", createTime: "", updateTime: ""}]
                ]
            }
        }
    }
</script>

<style scoped>

</style>

