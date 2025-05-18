import React from 'react';
import styles from './css/LoadingSpinner.module.css';
import useCssUtil from '../hook/useCssUtil';

interface LoadingSpinnerProps {
  isLoading: boolean;
  fullScreen?: boolean;
}

const LoadingSpinner: React.FC<LoadingSpinnerProps> = ({ isLoading, fullScreen = false }) => {
  const getStyle = useCssUtil(styles);
  
  if (!isLoading) return null;
  
  return (
    <div className={`${getStyle('spinner-container')} ${fullScreen ? getStyle('fullscreen') : ''}`}>
      <div className={getStyle('spinner')}>
        <div className={getStyle('spinner-inner')}></div>
      </div>
      <div className={getStyle('spinner-text')}>Loading...</div>
    </div>
  );
};

export default LoadingSpinner;