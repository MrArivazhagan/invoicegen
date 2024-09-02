import React from 'react';
import { Link } from 'react-router-dom';
import CameraFormService from '../services/CameraFormService';
import '../styles/shared-styles.css';

const CameraFormHistoryComponent = ({ forms }) => {
  const handleDelete = (formId) => {
    CameraFormService.deleteForm(formId)
      .then(() => {
        window.location.reload();
      })
      .catch(error => {
        console.error('Error deleting form', error);
      });
  };

  return (
    <div className="container">
      <h2>Camera Form History</h2>
      <div className="forms-container">
        {forms.map(form => (
          <div key={form.id} className="form-card">
            <div className="form-details">
              <h3>{form.name}</h3>
              <p>Installation Date: {form.installationDate}</p>
            </div>
            <div className="form-actions">
              <Link to={`/edit-camera-form/${form.id}`}>
                <button className="button primary">Edit</button>
              </Link>
              <button onClick={() => handleDelete(form.id)} className="button danger">Delete</button>
              <Link to={`/camera-form-pdf-viewer/${form.id}`}>
                <button className="button secondary">Get PDF</button>
              </Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CameraFormHistoryComponent;