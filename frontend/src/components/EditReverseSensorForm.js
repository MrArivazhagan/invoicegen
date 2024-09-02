import React, { useState, useEffect } from 'react';
import ReverseSensorFormService from '../services/ReverseSensorFormService';
import { useParams } from 'react-router-dom';
import FormComponent from './FormComponent';
import '../styles/Field.css'; // Import the CSS file for styling

const EditReverseSensorForm = () => {
  const { formId } = useParams();
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

  useEffect(() => {
    ReverseSensorFormService.getForm(formId)
      .then(response => {
        const formData = response.data;
        console.log(formData);
        setForm({
          deviceName: formData.deviceName,
          equipmentName: formData.equipmentName,
          modal: formData.modal,
          rtoOffice: formData.rtoOffice,
          registrationNumber: formData.registrationNumber,
          make: formData.make,
          chassisNumber: formData.chassisNumber,
          name: formData.name,
          address: formData.address,
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
    ReverseSensorFormService.updateForm(formId, form)
      .then(() => {
        window.location.href = '/dashboard';
      })
      .catch((error) => {
        console.error('Form update error', error);
      });
  };

  return (
    <div className="form-container">
      <h2>Edit Reverse-Sensor Form</h2>
      <FormComponent
        form={form}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        buttonLabel="Update"
      />
    </div>
  );
};

export default EditReverseSensorForm;
