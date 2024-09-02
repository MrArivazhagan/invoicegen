import React from 'react';
import AuthService from '../services/AuthService';
import UserService from '../services/UserService';
import '../styles/Header.css';

const Header = () => {
  const currentUser = AuthService.getCurrentUser();

  const handleLogout = () => {
    AuthService.logout();
    window.location.href = '/login';
  };

  const handleDelete = () => {
    UserService.deleteUser(currentUser.username).then(() => {
      AuthService.logout();
      window.location.href = '/login';
    });
  };

  return (
    <header className="header">
      <div className="header-content">
        <h1 className="header-title">{currentUser.username}</h1>
        <div className="header-buttons">
          <button className="header-button logout-button" onClick={handleLogout}>
            Logout
          </button>
          <button className="header-button delete-button" onClick={handleDelete}>
            Delete Account
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header;