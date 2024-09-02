import React, { useState } from 'react';
import CameraFormService from '../services/CameraFormService';
import CameraFormComponent from './CameraFormComponent';
import '../styles/Field.css'; // Import the CSS file for styling

const NewForm = () => {
  const [form, setForm] = useState({
    deviceName: '',
    equipmentName: '',
    modal: '',
    rtoOffice: '',
    registrationNumber: '',
    make: '',
    chassisNumber: '',
    cameraSerialNumber: '',
    name: '',
    address: '',
    installationDate: ''
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const u = JSON.parse(localStorage.getItem('user'));
    console.log(u.token);
    CameraFormService.createForm(form)
      .then(() => {
        window.location.href = '/dashboard';
      })
      .catch((error) => {
        console.error('Form creation error', error);
      });
  };

  return (
    <div className="form-container">
      <h2>New Camera Form</h2>
      <CameraFormComponent
        form={form}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        buttonLabel="Submit"
      />
    </div>
  );
};

export default NewForm;
