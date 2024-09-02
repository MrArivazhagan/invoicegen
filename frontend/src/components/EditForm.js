import React, { useState, useEffect } from 'react';
import FormService from '../services/FormService';
import { useParams } from 'react-router-dom';
import FormComponent from './FormComponent';
import '../styles/Field.css'; // Import the CSS file for styling

const EditForm = () => {
  const { formId } = useParams();
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

  useEffect(() => {
    FormService.getForm(formId)
      .then(response => {
        const formData = response.data;
        console.log(formData);
        setForm({
          deviceName: formData.deviceName,
          installationLocation: formData.installationLocation,
          registrationNumber: formData.registrationNumber,
          makeModel: formData.makeModel,
          chassisNumber: formData.chassisNumber,
          name: formData.name,
          address: formData.address,
          equipmentName: formData.equipmentName,
          installationDate: formData.installationDate
        });
      })
      .catch(error => {
        console.error('Error fetching form', error);
      });
  }, [formId]);

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    FormService.updateForm(formId, form)
      .then(() => {
        window.location.href = '/dashboard';
      })
      .catch((error) => {
        console.error('Form update error', error);
      });
  };

  return (
    <div className="form-container">
      <h2>Edit Form</h2>
      <FormComponent
        form={form}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        buttonLabel="Update"
      />
    </div>
  );
};

export default EditForm;
