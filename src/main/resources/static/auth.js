export default class Auth {
    static isLoggedIn() {
        return localStorage.getItem("token") !== null;
    }

    static saveToken(token) {
        localStorage.setItem('token', token);
    }

    static getEmail() {
        const token = localStorage.getItem('token');
        return JSON.parse(atob(token.split('.')[1])).email;
    }

}

