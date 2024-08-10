import React, {useState} from "react";
import useCssUtil from "../hook/useCssUtil";
import styles from "./css/LoginForm.module.css";
import {Link} from "react-router-dom";
import {RegExpUtils} from "../utils/StringUtils";

interface LoginInfo {
    onData: (data: { account: string; password: string; }) => void;
}

export const LoginForm: React.FC<LoginInfo> = ({onData}) => {
    const [account, setAccount] = useState('');
    const [password, setPassword] = useState('');
    const getStyle = useCssUtil(styles);

    const submit = () => {
        if (isNotValid(account)) {
            // TODO modal
            console.log('fail')
            return;
        }
        onData({account, password})
    }

    const isNotValid = (account: string) => {
        return !RegExpUtils.isValid(account);
    };

    return (
        <div className={getStyle('login-container')}>
            <Link className={getStyle('logo')} to={`/`}>Dashboard</Link>
            <div className={getStyle('login-form')}>
                <input
                    type="text"
                    placeholder="Account"
                    value={account}
                    onChange={(e) => setAccount(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button onClick={submit}>Login</button>
                <div className={getStyle('sign-up')}>
                    <div>Didn't have account?</div>
                    <Link className={getStyle('sign-up-button')} to={`/sign-up`}>here</Link>
                </div>
            </div>
        </div>
    );
};