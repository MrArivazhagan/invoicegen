import React from 'react';
import { Link } from 'react-router-dom';
import Header from './Header'; // Import the Header component
import '../styles/Dashboard.css';

const DashboardButton = ({ to, iconClass, primary, children }) => (
  <Link to={to} className="dashboard-button-link">
    <button className={`dashboard-button ${primary ? 'primary' : 'secondary'}`}>
      <span className={`button-icon ${iconClass}`}></span>
      <span className="button-text">{children}</span>
    </button>
  </Link>
);

const Dashboard = () => {
  return (
    <div className="dashboard">
      <Header /> {/* Add the Header component here */}
      <div className="dashboard-content">
        <h1 className="dashboard-title">Dashboard</h1>

        <div className="dashboard-card">
          <div className="dashboard-grid">
            <div className="dashboard-section">
              <h2 className="section-title">Camera Forms</h2>
              <DashboardButton to="/new-camera-form" iconClass="icon-plus" primary>
                New Camera Form
              </DashboardButton>
              <DashboardButton to="/camera-form-history" iconClass="icon-history">
                Camera Form History
              </DashboardButton>
            </div>

            <div className="dashboard-section">
              <h2 className="section-title">Reverse Sensor Forms</h2>
              <DashboardButton to="/new-reverse-sensor-form" iconClass="icon-plus" primary>
                New Reverse Sensor Form
              </DashboardButton>
              <DashboardButton to="/reverse-sensor-form-history" iconClass="icon-history">
                Reverse Sensor History
              </DashboardButton>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;