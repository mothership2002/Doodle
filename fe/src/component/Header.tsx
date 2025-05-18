import React, {useEffect} from "react";
import styles from './css/Header.module.css';
import useCssUtil from "../hook/useCssUtil";
import {useSelector} from "react-redux";
import {RootState} from "../store/store";
import {Link} from "react-router-dom";
import BellIcon from "./icons/BellIcon";

interface HeaderProps {
    isProcessing: boolean
}

const Header: React.FC<HeaderProps> = ({isProcessing}) => {
    const getStyle = useCssUtil(styles);
    const auth = useSelector((state: RootState) => state.auth);

    useEffect(() => {
        // Any side effects when processing state changes
    }, [isProcessing]);

    const renderAuthButtons = () => {
        return auth.user ? (
            <>
                <Link 
                    className={getStyle('profile-button')} 
                    to="/profile" 
                    aria-label="View your profile"
                >
                    My Profile
                </Link>
                <Link 
                    className={getStyle('logout-button')} 
                    to="/logout" 
                    aria-label="Log out of your account"
                >
                    Logout
                </Link>
            </>
        ) : (
            <>
                <Link 
                    className={getStyle('login-button')} 
                    to="/login" 
                    aria-label="Log in to your account"
                >
                    Login
                </Link>
                <Link 
                    className={getStyle('signup-button')} 
                    to="/sign-up" 
                    aria-label="Create a new account"
                >
                    Sign Up
                </Link>
            </>
        );
    };

    const renderLoadingIndicator = () => {
        return (
            <div className={getStyle('loading-indicator')} role="status">
                {/*<span className={getStyle('visually-hidden')}></span>*/}
            </div>
        );
    };

    return (
        <header className={getStyle('header')} role="banner">
            <Link 
                className={getStyle('logo')} 
                to="/" 
                aria-label="Go to Dashboard"
            >
                Dashboard
            </Link>

            <input 
                type="text" 
                className={getStyle('search-bar')} 
                placeholder="Search..." 
                aria-label="Search"
            />

            <nav className={getStyle('nav-menu')} role="navigation">
                <Link to="/board">Board</Link>
                <Link to="/notifications" className={getStyle('notification-icon')} aria-label="Notifications">
                    <BellIcon />
                </Link>
            </nav>

            <div className={getStyle('user-menu')}>
                {isProcessing ? renderLoadingIndicator() : renderAuthButtons()}
            </div>
        </header>
    );
}

export default Header;
