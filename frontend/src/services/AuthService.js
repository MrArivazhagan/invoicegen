// src/services/AuthService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/users/';

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + 'login', { username, password })
      .then(response => {
        if (response.data.token) {
          localStorage.setItem('user', JSON.stringify({
              token: response.data.token,
              id: response.data.id,
              username: response.data.username
          }));
        }
        return response.data.token;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(username, password) {
    return axios.post(API_URL + 'register', { username, password });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }
}

export default new AuthService();
