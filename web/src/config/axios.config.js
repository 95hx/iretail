import axios from "axios";

class AxiosConfig {

    addHttpRequestPrefix() {
        axios.defaults.headers.common["Content-Type"] = "application/json;charset=utf-8";
        axios.defaults.headers.common["Authorization"] = sessionStorage.getItem("jwt");
    }

    addCommonDataToRequest() {
        axios.interceptors.request.use((config) => {
            return config;
        });
    }

    handleResponseData() {
        axios.interceptors.response.use((response) => {
                if (response.data.code === '000008' || response.data.code === '000009') {
                    this.$router.push({
                        name: 'oauth',
                        params: {}
                    });
                }
                return response.data;
            },
            err => {
                return err;
            });
    }

    async init() {
        this.addHttpRequestPrefix();
        this.addCommonDataToRequest();
        this.handleResponseData();
    }
}

export default new AxiosConfig();
