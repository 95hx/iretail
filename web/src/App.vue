<template>
    <div id="app">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                 v-if="showTab">
            <el-submenu index="1">
                <template slot="title">权限管理</template>
                <el-menu-item index="1-1">数据维度管理</el-menu-item>
                <el-menu-item index="1-2">角色权限管理</el-menu-item>
                <el-menu-item index="1-3">用户角色管理</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">我的工作台</template>
                <el-menu-item index="2-1">品类管理</el-menu-item>
                <el-menu-item index="2-2">spu管理</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
                <el-submenu index="2-4">
                    <template slot="title">选项4</template>
                    <el-menu-item index="2-4-1">选项1</el-menu-item>
                    <el-menu-item index="2-4-2">选项2</el-menu-item>
                    <el-menu-item index="2-4-3">选项3</el-menu-item>
                </el-submenu>
            </el-submenu>
            <el-menu-item index="3" disabled>消息中心</el-menu-item>
            <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
        </el-menu>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: 'App',
        computed: {
            showTab: function () {
                return this.$route.name !== 'oauth';
            }
        },
        data() {
            return {
                activeIndex: '1'
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                if (key == '2-1') {
                    this.$router.push({
                        name: 'category',
                        params: {}
                    });
                }
                if (key == '2-2') {
                    this.$router.push({
                        name: 'spu',
                        params: {}
                    });
                }
                if (key == '1-1') {
                    this.$router.push({
                        name: 'dimension',
                        params: {}
                    });
                }
                if (key == '1-2') {
                    this.$router.push({
                        name: 'role',
                        params: {}
                    });
                }
                if (key == '1-3') {
                    this.$router.push({
                        name: 'user',
                        params: {}
                    });
                }
            }
        }
    }
</script>