import React, { useState, useEffect } from 'react';
import CameraFormService from '../services/CameraFormService';
import { useParams } from 'react-router-dom';
import CameraFormComponent from './CameraFormComponent';
import '../styles/Field.css'; // Import the CSS file for styling

const EditCameraForm = () => {
  const { formId } = useParams();
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

  useEffect(() => {
    CameraFormService.getForm(formId)
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
          cameraSerialNumber: formData.cameraSerialNumber,
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
    CameraFormService.updateForm(formId, form)
      .then(() => {
        window.location.href = '/dashboard';
      })
      .catch((error) => {
        console.error('Form update error', error);
      });
  };

  return (
    <div className="form-container">
      <h2>Edit Camera Form</h2>
      <CameraFormComponent
        form={form}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        buttonLabel="Update"
      />
    </div>
  );
};

export default EditCameraForm;
