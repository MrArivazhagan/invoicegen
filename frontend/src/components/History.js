import React, { useState, useEffect } from 'react';
import FormService from '../services/FormService';
import AuthService from '../services/AuthService';
import HistoryComponent from './HistoryComponent';
import '../styles/History.css'; // Import the CSS file for styling

const History = () => {
  const [forms, setForms] = useState([]);
  const currentUser = AuthService.getCurrentUser();

  useEffect(() => {
    FormService.getUserForms(currentUser.id)
      .then(response => {
        setForms(response.data);
      })
      .catch(error => {
        console.error('Error fetching forms', error);
      });
  }, [currentUser.id]);

  return (
    <div className="history-container">
      <h2>History</h2>
      <HistoryComponent forms={forms} />
    </div>
  );
};

export default History;
