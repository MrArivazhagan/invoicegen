// src/services/UserService.js
import axios from 'axios';
import authHeader from './AuthHeader';

const API_URL = 'http://localhost:8080/api/users/';

class UserService {
  getUserDetails() {
    return axios.get(API_URL, { headers: authHeader() });
  }

  deleteUser(username) {
    return axios.delete(API_URL + username, { headers: authHeader() });
  }
}

export default new UserService();
