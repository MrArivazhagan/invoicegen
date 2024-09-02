// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Register from './components/Register';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import NewForm from './components/NewForm';
import NewCameraForm from './components/NewCameraForm';
import NewReverseSensorForm from './components/NewReverseSensorForm';
import EditForm from './components/EditForm';
import EditCameraForm from './components/EditCameraForm';
import EditReverseSensorForm from './components/EditReverseSensorForm';
import History from './components/History';
import CameraFormHistory from './components/CameraFormHistory';
import ReverseSensorFormHistory from './components/ReverseSensorFormHistory';
import PdfViewer from './components/PdfViewer';
import CameraFormPdfViewer from './components/CameraFormPdfViewer';
import ReverseSensorFormPdfViewer from './components/ReverseSensorFormPdfViewer';

const App = () => {
  return (
    <Router>
      <div>
        <Routes>
          <Route index element={<Register />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/new-form" element={<NewForm />} />
          <Route path="/new-camera-form" element={<NewCameraForm />} />
          <Route path="/new-reverse-sensor-form" element={<NewReverseSensorForm />} />
          <Route path="/edit-form/:formId" element={<EditForm />} />
          <Route path="/edit-camera-form/:formId" element={<EditCameraForm />} />
          <Route path="/edit-reverse-sensor-form/:formId" element={<EditReverseSensorForm />} />
          <Route path="/history" element={<History />} />
          <Route path="/camera-form-history" element={<CameraFormHistory />} />
          <Route path="/reverse-sensor-form-history" element={<ReverseSensorFormHistory />} />
          <Route path="/pdf-viewer/:formId" element={<PdfViewer />} />
          <Route path="/camera-form-pdf-viewer/:formId" element={<CameraFormPdfViewer />} />
          <Route path="/reverse-sensor-form-pdf-viewer/:formId" element={<ReverseSensorFormPdfViewer />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
