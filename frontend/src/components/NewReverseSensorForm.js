import React, { useState } from 'react';
import ReverseSensorFormService from '../services/ReverseSensorFormService';
import FormComponent from './FormComponent';
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
    ReverseSensorFormService.createForm(form)
      .then(() => {
        window.location.href = '/dashboard';
      })
      .catch((error) => {
        console.error('Form creation error', error);
      });
  };

  return (
    <div className="form-container">
      <h2>New Reverse Sensor Form</h2>
      <FormComponent
        form={form}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        buttonLabel="Submit"
      />
    </div>
  );
};

export default NewForm;
