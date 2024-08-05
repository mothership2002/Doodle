import {useEffect} from "react";
import styles from './css/Header.module.css';
import useCssUtil from "../hook/useCssUtil";

const Header = () => {
    // is Useful? ...
    const getStyle = useCssUtil(styles);

    // mount
    useEffect(() => {

    }, [])

    return (
        <header className={getStyle('header')}>
            <div className={getStyle('logo')}>Dashboard</div>
            <input type="text" className={getStyle('search-bar')} placeholder="Search..."/>
            <nav className={getStyle('nav-menu')}>
                <a href="/">Home</a>
                <a href="/board">Board</a>
                <a href="/profile">Profile</a>
                <a href="/notifications" className={getStyle('notification-icon')}>
                    <img src="/img/notification-icon.svg" alt="Notifications"/>
                </a>
            </nav>
            <div className={getStyle('user-menu')}>
                <button className={getStyle('login-button')}>Login</button>
                <button className={getStyle('signup-button')}>Sign Up</button>
            </div>
        </header>
    )
}

export default Header;