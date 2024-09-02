import React, { useEffect, useState } from 'react';
import FormService from '../services/FormService';
import { useParams } from 'react-router-dom';
import '../styles/shared-styles.css';

const PdfViewer = () => {
  const { formId } = useParams();
  const [pdfUrl, setPdfUrl] = useState('');

  useEffect(() => {
    FormService.getFormPdf(formId)
      .then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data], { type: 'application/pdf' }));
        setPdfUrl(url);
      })
      .catch(error => {
        console.error('Error fetching PDF', error);
      });
  }, [formId]);

  return (
    <div className="container pdf-viewer-container">
      <div className="pdf-header">
        <h2>Invoice PDF</h2>
        <a href={pdfUrl} download="invoice.pdf">
          <button className="button primary">Download PDF</button>
        </a>
      </div>
      <iframe src={pdfUrl} width="100%" height="600px" title="PDF Viewer" />
    </div>
  );
};

export default PdfViewer;