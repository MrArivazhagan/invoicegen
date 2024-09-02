import React, { useEffect, useState } from 'react';
import CameraFormService from '../services/CameraFormService';
import { useParams } from 'react-router-dom';
import '../styles/PdfViewer.css'; // Import the CSS file for styling

const CameraFormPdfViewer = () => {
  const { formId } = useParams();
  const [pdfUrl, setPdfUrl] = useState('');

  useEffect(() => {
    CameraFormService.getFormPdf(formId)
      .then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data], { type: 'application/pdf' }));
        setPdfUrl(url);
      })
      .catch(error => {
        console.error('Error fetching PDF', error);
      });
  }, [formId]);

  return (
    <div className="pdf-viewer-container">
      <div className="pdf-header">
        <h2>Camera Invoice PDF</h2>
        <a href={pdfUrl} download="camera-invoice.pdf">
          <button className="button primary"><b>Download PDF</b></button>
        </a>
      </div>
      <iframe src={pdfUrl} width="100%" height="600px" title="PDF Viewer" />
    </div>
  );
};

export default CameraFormPdfViewer;
