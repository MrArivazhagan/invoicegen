import React from 'react';
import '../styles/shared-styles.css';

const FormComponent = ({ form, handleChange, handleSubmit, buttonLabel }) => {
  return (
    <div className="container">
      <h2>Reverse Sensor Form</h2>
      <form onSubmit={handleSubmit} className="form-grid">
        <div className="form-group">
          <label>Device Name</label>
          <input type="text" name="deviceName" value={form.deviceName} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label>Equipment Name</label>
          <input type="text" name="equipmentName" value={form.equipmentName} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label>Model</label>
          <input type="text" name="modal" value={form.modal} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label>RTO Office</label>
          <input type="text" name="rtoOffice" value={form.rtoOffice} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label>Registration Number</label>
          <input type="text" name="registrationNumber" value={form.registrationNumber} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label>Make/Model</label>
          <input type="text" name="make" value={form.make} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label>Chassis Number</label>
          <input type="text" name="chassisNumber" value={form.chassisNumber} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label>Name</label>
          <input type="text" name="name" value={form.name} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label>Address</label>
          <input type="text" name="address" value={form.address} onChange={handleChange} />
        </div>
        <div className="form-group">
          <label>Installation Date</label>
          <input type="date" name="installationDate" value={form.installationDate} onChange={handleChange} />
        </div>
        <div className="form-group full-width">
          <button type="submit" className="button primary">{buttonLabel}</button>
        </div>
      </form>
    </div>
  );
};

export default FormComponent;