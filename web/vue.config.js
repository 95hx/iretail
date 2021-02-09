module.exports = {
    devServer: {
        port: 8222,
        proxy: {
            "/api": {
                target: 'http://localhost:8080',
                changeOrigin: true,
                pathRewrite:{'^/api':''}
            }
        }
    }
};
