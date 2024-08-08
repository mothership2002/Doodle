import React, {useState} from "react";
import {useApi} from "./ApiComponentContext";
import useCssUtil from "../hook/useCssUtil";
import styles from "./css/Login.module.css";

export const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const api = useApi();
    const getStyle = useCssUtil(styles);

    const handleSubmit = (event: React.FormEvent) => {

        // 부모로 올려서 비즈니스는 부모가 실행하도록하자.
        event.preventDefault();
        console.log(email, password)
    }

    return (

        <div className={getStyle('login-container')}>
            <form className={getStyle('login-form')} onSubmit={handleSubmit}>
                <h2>Login</h2>
                <input
                    type="text"
                    placeholder="Account"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button type="submit">Login</button>
            </form>
        </div>
    );
};