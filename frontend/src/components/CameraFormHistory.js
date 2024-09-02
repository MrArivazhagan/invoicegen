import React, { useState, useEffect } from 'react';
import CameraFormService from '../services/CameraFormService';
import AuthService from '../services/AuthService';
import CameraFormHistoryComponent from './CameraFormHistoryComponent';
import '../styles/History.css'; // Import the CSS file for styling

const CameraFormHistory = () => {
  const [forms, setForms] = useState([]);
  const currentUser = AuthService.getCurrentUser();

  useEffect(() => {
    CameraFormService.getUserForms(currentUser.id)
      .then(response => {
        setForms(response.data);
      })
      .catch(error => {
        console.error('Error fetching forms', error);
      });
  }, [currentUser.id]);

  return (
    <div className="history-container">
      <h2>Camera Form History</h2>
      <CameraFormHistoryComponent forms={forms} />
    </div>
  );
};

export default CameraFormHistory;
