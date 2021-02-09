let jwt = require('jsonwebtoken');

class GlobalUtil {

    static getJwtUserInfo() {
        if (sessionStorage.getItem("jwt") === null) {
            return null;
        }
        return JSON.parse(jwt.decode(sessionStorage.getItem("jwt")).userInfo);
    }

    static getJwtExp() {
        if (sessionStorage.getItem("jwt") === null) {
            return 0;
        }
        return jwt.decode(sessionStorage.getItem("jwt")).exp;
    }

    static validJwt() {
        return this.getJwtExp() > new Date().getTime() / 1000 && this.getJwtUserInfo() != null;
    }
}

export default GlobalUtil;
