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
    const [isLoading, setIsLoading] = useState(false);
    const getStyle = useCssUtil(styles);

    const submit = async () => {
        if (isNotValid(account)) {
            // TODO modal
            console.log('fail')
            return;
        }
        setIsLoading(true);
        try {
            await onData({account, password});
        } finally {
            setIsLoading(false);
        }
    }

    const isNotValid = (account: string) => {
        return !RegExpUtils.isValid(account);
    };

    return (
        <div className={getStyle('login-container')}>
            <Link className={getStyle('logo')} to={`/`} aria-label="Go to Dashboard">Dashboard</Link>
            <form className={getStyle('login-form')} onSubmit={(e) => { e.preventDefault(); submit(); }}>
                <h2 id="login-heading">Welcome Back</h2>
                <div className={getStyle('form-group')}>
                    <label htmlFor="account" className={getStyle('visually-hidden')}>Account</label>
                    <input
                        id="account"
                        type="text"
                        placeholder="Account"
                        value={account}
                        onChange={(e) => setAccount(e.target.value)}
                        required
                        className={isNotValid(account) && account ? getStyle('input-error') : ''}
                        aria-describedby={isNotValid(account) && account ? "account-error" : undefined}
                        aria-invalid={isNotValid(account) && account ? "true" : "false"}
                    />
                    {isNotValid(account) && account && (
                        <span id="account-error" className={getStyle('error-message')} role="alert">
                            Please enter a valid account
                        </span>
                    )}
                </div>
                <div className={getStyle('form-group')}>
                    <label htmlFor="password" className={getStyle('visually-hidden')}>Password</label>
                    <input
                        id="password"
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button 
                    type="submit" 
                    aria-label="Sign in to your account"
                    disabled={isLoading}
                    className={isLoading ? getStyle('button-loading') : ''}
                >
                    {isLoading ? (
                        <>
                            <span className={getStyle('loading-spinner')}></span>
                            <span>Signing In...</span>
                        </>
                    ) : 'Sign In'}
                </button>
                <div className={getStyle('login-footer')}>
                    Don't have an account? <Link to={`/sign-up`}>Sign Up</Link>
                </div>
            </form>
        </div>
    );
};
