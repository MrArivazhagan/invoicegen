import React, { useState, useEffect } from 'react';
import ReverseSensorFormService from '../services/ReverseSensorFormService';
import AuthService from '../services/AuthService';
import ReverseSensorFormHistoryComponent from './ReverseSensorFormHistoryComponent';
import '../styles/History.css'; // Import the CSS file for styling

const ReverseSensorFormHistory = () => {
  const [forms, setForms] = useState([]);
  const currentUser = AuthService.getCurrentUser();

  useEffect(() => {
    ReverseSensorFormService.getUserForms(currentUser.id)
      .then(response => {
        setForms(response.data);
      })
      .catch(error => {
        console.error('Error fetching forms', error);
      });
  }, [currentUser.id]);

  return (
    <div className="history-container">
      <h2>Reverse Sensor Form History</h2>
      <ReverseSensorFormHistoryComponent forms={forms} />
    </div>
  );
};

export default ReverseSensorFormHistory;
