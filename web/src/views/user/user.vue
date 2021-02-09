<template>
    <div>
        <el-button @click="test">test</el-button>
        user
    </div>
</template>

<script>
    export default {
        name: "user",
        methods:{
            test() {
                this.$axios.post('api/test/1', {
                }).then((response) => {
                    if (response.status === 'success') {
                        this.$message.info('请求成功');
                    } else {
                        this.$message.error('请求失败'+response.code);
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>