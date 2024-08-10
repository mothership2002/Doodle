import React, {useEffect} from "react";
import styles from './css/Header.module.css';
import useCssUtil from "../hook/useCssUtil";
import {useSelector} from "react-redux";
import {RootState} from "../store/store";
import {Link} from "react-router-dom";

interface HeaderProps {
    isProcessing: boolean
}

const Header: React.FC<HeaderProps> = ({isProcessing}) => {
    // is Useful? ...
    const getStyle = useCssUtil(styles);
    const auth = useSelector((state: RootState) => state.auth);

    // mount
    useEffect(() => {

    }, [isProcessing])

    const isLoggedIn = () => {
        return auth.user ?
            (
                <>
                    <Link className={getStyle('profile-button')} to={`/profile`}>My Profile</Link>
                    <Link className={getStyle('logout-button')} to={`/logout`}>Logout</Link>
                </>
            )
            :
            (
                <>
                    <Link className={getStyle('login-button')} to={`/login`}>Login</Link>
                    <Link className={getStyle('signup-button')} to={`/sign-up`}>Sign Up</Link>
                </>
            )
    }

    const blinkBlock = () => {
        return (<></>)
    }

    return (
        <header className={getStyle('header')}>
            <Link className={getStyle('logo')} to={`/`}>Dashboard</Link>
            <input type="text" className={getStyle('search-bar')} placeholder="Search..."/>
            <nav className={getStyle('nav-menu')}>
                <a href="/board">Board</a>
                <a href="/notifications" className={getStyle('notification-icon')}>
                    <img src="/img/notification-icon.svg" alt="Notifications"/>
                </a>
            </nav>
            <div className={getStyle('user-menu')}>
                {isProcessing ? blinkBlock() : isLoggedIn()}
            </div>
        </header>
    )
}

export default Header;