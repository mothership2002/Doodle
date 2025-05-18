import React, { ReactNode, useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import styles from './css/PageTransition.module.css';
import useCssUtil from '../hook/useCssUtil';
import LoadingSpinner from './LoadingSpinner';

interface PageTransitionProps {
  children: ReactNode;
}

const PageTransition: React.FC<PageTransitionProps> = ({ children }) => {
  const location = useLocation();
  const [displayLocation, setDisplayLocation] = useState(location);
  const [isLoading, setIsLoading] = useState(true);
  const getStyle = useCssUtil(styles);

  // Initial load
  useEffect(() => {
    const timer = setTimeout(() => {
      setIsLoading(false);
    }, 800); // Show spinner for at least 800ms on initial load

    return () => clearTimeout(timer);
  }, []);

  // Handle route changes
  useEffect(() => {
    if (location !== displayLocation) {
      setIsLoading(true);

      // Short delay to ensure spinner is visible
      const timer = setTimeout(() => {
        setDisplayLocation(location);

        // After location change, wait a bit before hiding spinner
        setTimeout(() => {
          setIsLoading(false);
        }, 300);
      }, 300);

      return () => clearTimeout(timer);
    }
  }, [location, displayLocation]);

  return (
    <div className={getStyle('page-transition')}>
      <LoadingSpinner isLoading={isLoading} fullScreen={true} />

      <div className={getStyle('page-content')} style={{ 
        opacity: isLoading ? 0 : 1,
        transition: 'opacity 0.3s ease-in-out'
      }}>
        {children}
      </div>
    </div>
  );
};

export default PageTransition;
