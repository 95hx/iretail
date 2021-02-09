<template>
    <div>
        <el-form :inline="true" :model="formItem" class="demo-form-inline">
            <el-form-item label="spu名称">
                <el-input v-model="formItem.spuName" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="spu编码">
                <el-input v-model="formItem.spuCode" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="品类编码">
                <el-input v-model="formItem.categoryCode" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="clickSearch">搜索</el-button>
            </el-form-item>
        </el-form>
        <div style="float: right">
            <el-button type="primary" @click="clickAdd">创建</el-button>
        </div>
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="spuCode"
                    label="spu编码"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="spuName"
                    label="spu名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="categoryCode"
                    label="品类编码"
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
                :destroy-on-close="true"
                :title="dialogTitle"
                :visible.sync="dialogVisible"
                width="30%"
                :close-on-press-escape="false"
                :close-on-click-modal="false"
                :show-close="false"
                :before-close="handleClose">
            <el-cascader
                    :props="cascadeCategory"
                    :disabled="isEdit"
                    ref="cascade">
            </el-cascader>
            <el-input v-model="itemSpu.name" placeholder="请输入spu名称"></el-input>
            <el-input :disabled="isEdit" v-model="itemSpu.code" placeholder="请输入spu编码"></el-input>
            <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogConfirm">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    let id = 0;
    export default {
        name: "spu",
        created() {
            this.init();
        },
        methods: {
            clickEdit(row) {
                this.isEdit = true;
                this.itemSpu.name = row.spuName;
                this.itemSpu.code = row.spuCode;
                this.itemSpu.categoryCode = row.categoryCode;
                this.dialogTitle = "spu更新";
                this.dialogVisible = true;
            },
            clickDelete(row) {
                this.$confirm('此操作将永久删除该spu,及与品类对应的关系是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('api/item-spu/delete', {
                        "spuCode": row.spuCode,
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
            clickAdd() {
                this.isEdit = false;
                this.dialogTitle = "spu添加";
                this.itemSpu.id = '';
                this.itemSpu.name = '';
                this.itemSpu.code = '';
                this.itemSpu.categoryCode = '';
                this.dialogVisible = true;
            },
            clickSearch() {
                this.$axios.post('api/item-spu/query', {
                    "spuName": this.formItem.spuName,
                    "spuCode": this.formItem.spuCode,
                    "categoryCode": this.formItem.categoryCode,
                    "page": 1,
                    "size": 1000
                }).then((response) => {
                    this.tableData = response.result.rows;
                }).catch((e) => {
                    this.$message.error('请求失败');
                });
            },
            handleClose() {

            },
            init() {
                this.$axios.post('api/item-spu/query', {
                    "page": 1,
                    "size": 1000
                }).then((response) => {
                    this.tableData = response.result.rows;
                    this.page.total = response.result.total
                }).catch((e) => {
                    this.$message.error('请求失败');
                });
            },
            dialogConfirm() {
                if (this.dialogTitle == 'spu添加') {
                    this.save();
                } else {
                    this.update();
                }
            },
            save() {
                let valueSet = new Set();
                this.$refs.cascade.getCheckedNodes().forEach(e => {
                    valueSet.add(e.value);
                });
                this.$axios.post('api/item-spu/add', {
                    "spuName": this.itemSpu.name,
                    "spuCode": this.itemSpu.code,
                    "categoryList": Array.from(valueSet)
                }).then((response) => {
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
            update() {
                this.$axios.post('api/item-spu/update', {
                    "spuName": this.itemSpu.name,
                    "spuCode": this.itemSpu.code
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
            }
        },
        data() {
            return {
                isEdit: false,
                page: {
                    total: 0
                },
                itemSpu: {
                    id: '',
                    name: '',
                    code: '',
                    categoryList: [],
                },
                cascadeCategory: {
                    that: this,
                    multiple: true,
                    value: 'categoryCode',
                    isLeaf: 'leaf',
                    label: 'categoryName',
                    children: 'childList',
                    lazy: true,
                    lazyLoad(node, resolve) {
                        if (node.level === 0) {
                            return resolve([{categoryName: '所有品类', categoryCode: 'root'}]);
                        }
                        this.that.$axios.post('api/category/query', {
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
                    }
                },
                dialogVisible: false,
                dialogTitle: '',
                tableData: [{
                    spuCode: "",
                    spuName: "",
                    categoryCode: "",
                    createTime: "",
                    updateTime: ""
                }],
                formItem: {
                    spuName: '',
                    spuCode: '',
                    categoryCode: ''
                },
            }
        }
    }
</script>

<style scoped>

</style>