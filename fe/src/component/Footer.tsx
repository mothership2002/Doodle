import React from "react";
import useCssUtil from "../hook/useCssUtil";
import styles from "./css/Footer.module.css"

const Footer: React.FC = () => {
    const getStyle = useCssUtil(styles);
    const currentYear = new Date().getFullYear();

    return (
        <footer className={getStyle('footer')} role="contentinfo">
            <p>
                &copy; {currentYear} Mothership's sample. All rights reserved.
                <a href="/privacy" aria-label="View our privacy policy">Privacy Policy</a>
                <a href="/terms" aria-label="View our terms of service">Terms of Service</a>
            </p>

            {/* Uncomment to add social media icons
            <div className={getStyle('social-icons')}>
                <a href="https://twitter.com" className={getStyle('social-icon')} aria-label="Follow us on Twitter" target="_blank" rel="noopener noreferrer">
                    <svg width="20" height="20" viewBox="0 0 24 24" fill="currentColor">
                        <path d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z"></path>
                    </svg>
                </a>
                <a href="https://github.com" className={getStyle('social-icon')} aria-label="View our GitHub repository" target="_blank" rel="noopener noreferrer">
                    <svg width="20" height="20" viewBox="0 0 24 24" fill="currentColor">
                        <path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path>
                    </svg>
                </a>
            </div>
            */}
        </footer>
    );
}

export default Footer;
