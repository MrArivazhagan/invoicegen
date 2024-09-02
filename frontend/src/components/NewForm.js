import React, { useState } from 'react';
import FormService from '../services/FormService';
import FormComponent from './FormComponent';
import '../styles/Field.css'; // Import the CSS file for styling

const NewForm = () => {
  const [form, setForm] = useState({
    deviceName: '',
    installationLocation: '',
    registrationNumber: '',
    makeModel: '',
    chassisNumber: '',
    name: '',
    address: '',
    equipmentName: '',
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
    FormService.createForm(form)
      .then(() => {
        window.location.href = '/dashboard';
      })
      .catch((error) => {
        console.error('Form creation error', error);
      });
  };

  return (
    <div className="form-container">
      <h2>New Form</h2>
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
