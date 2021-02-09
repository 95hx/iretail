<template>
    <el-form :model="loginForm" status-icon ref="loginForm" label-width="100px">
        <el-form-item label="账号" prop="username">
            <el-input type="text" v-model="loginForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    export default {
        data() {
            return {
                loginForm: {
                    username: "dianzhang",
                    password: "123456"
                }
            }
        },
        name: "oauth",
        methods: {
            login() {
                this.$axios.post('api/login', {
                    username: this.loginForm.username,
                    password: this.loginForm.password
                }).then((response) => {
                    if (response.status === 'success') {
                        sessionStorage.setItem("jwt", response.result)
                        this.$router.push({
                            path: "/home"
                        });
                    } else {
                        this.$message.error('账号或密码输入错误');
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>