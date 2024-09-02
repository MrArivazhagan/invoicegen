// src/services/CameraFormService.js
import axios from 'axios';
import authHeader from './AuthHeader';

const API_URL = 'http://localhost:8080/api/camera-forms/';

class CameraFormService {
  createForm(form) {
    return axios.post(API_URL, form, { headers: authHeader() });
  }

  getUserForms(userId) {
    return axios.get(API_URL + userId, { headers: authHeader() });
  }

  getForm(formId) {
    return axios.get(API_URL + "form/" + formId, { headers: authHeader() });
  }

  updateForm(formId, form) {
    return axios.put(API_URL + formId, form, { headers: authHeader() });
  }

  deleteForm(formId) {
    return axios.delete(API_URL + formId, { headers: authHeader() });
  }

  getFormPdf(formId) {
    return axios.get(`http://localhost:8080/api/camera/pdf/${formId}`, { headers: authHeader(), responseType: 'blob' });
  }
}

export default new CameraFormService();
