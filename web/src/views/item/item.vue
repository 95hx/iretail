<template>
    <div>
        <el-form :inline="true" :model="formItem" class="demo-form-inline">
            <el-form-item label="品类名称">
                <el-input v-model="formItem.categoryName" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="clickSearch">搜索</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "item",
        data(){
            return{
                formItem: {
                    skuName: '',
                    skuCode: '',
                    eanCode: '',
                    categoryCode: '',
                    spuCode: '',
                    enableSn: '',
                    priceMin: '',
                    priceMax: ''
                },
            }
        },
    }
</script>

<style scoped>

</style>