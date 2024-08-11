import styles from "./css/SignUpForm.module.css";
import useCssUtil from "../hook/useCssUtil";
import React, {useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {useApi} from "./ApiComponentContext";

export const SignUpForm = () => {

    const getStyle = useCssUtil(styles);
    const api = useApi();

    const [isAccountAvailable, setIsAccountAvailable] = useState<boolean | null>(null);
    const [verificationCode, setVerificationCode] = useState('');
    const [isCodeSent, setIsCodeSent] = useState(false);

    const [account, setAccount] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [email, setEmail] = useState('');

    const navigate = useNavigate();

    const handleAccountCheck = () => {
        // 중복 체크 로직 추가
        setIsAccountAvailable(true); // 예시로 true로 설정
    };

    const handleSendCode = () => {
        // 이메일 인증 코드 전송 로직 추가
        setIsCodeSent(true); // 인증 코드가 전송되었다고 가정
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            alert('Passwords do not match');
            return;
        }

        // 회원가입 로직 추가
        navigate('/login');
    };

    return (
        <div className={getStyle('signup-container')}>
            <div className={getStyle('signup-form')}>
                <h2>Create Your Account</h2>

                {/* Account Field with Availability Check */}
                <div className={getStyle('input-with-button')}>
                    <input
                        type="text"
                        placeholder="Account"
                        value={account}
                        onChange={(e) => setAccount(e.target.value)}
                    />
                    <button type="button" className={getStyle('check-button')} onClick={handleAccountCheck}>
                        Check Availability
                    </button>
                </div>
                {isAccountAvailable !== null && (
                    <span className={isAccountAvailable ? getStyle('available'): getStyle('not-available')}>
                        {isAccountAvailable ? 'Account is available' : 'Account is taken'}
                    </span>
                )}

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                />

                {/* Email Field with Verification Code */}
                <div className={getStyle('input-with-button')}>
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <button type="button" className={getStyle('check-button')} onClick={handleSendCode}>
                        Send Verification Code
                    </button>
                </div>

                {isCodeSent && (
                    <input
                        type="text"
                        placeholder="Enter verification code"
                        value={verificationCode}
                        onChange={(e) => setVerificationCode(e.target.value)}
                    />
                )}

                <button className={getStyle('submit-button')} onClick={handleSubmit}>Sign Up</button>
                <div className={getStyle('signup-footer')}>
                    Already have an account? <Link to={`/login`}>Log In</Link>
                </div>
            </div>
        </div>
    );
};