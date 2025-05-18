import styles from "./css/SignUpForm.module.css";
import useCssUtil from "../hook/useCssUtil";
import React, {useState, useEffect, useRef} from "react";
import {Link, useNavigate} from "react-router-dom";
import {useApi} from "./ApiComponentContext";

export const SignUpForm = () => {

    const getStyle = useCssUtil(styles);
    const api = useApi();

    const [isAccountAvailable, setIsAccountAvailable] = useState<boolean | null>(null);
    const [verificationCode, setVerificationCode] = useState('');
    const [isCodeSent, setIsCodeSent] = useState(false);
    const [timeRemaining, setTimeRemaining] = useState<number>(300); // 5 minutes in seconds
    const [timerActive, setTimerActive] = useState<boolean>(false);
    const timerRef = useRef<NodeJS.Timeout | null>(null);

    const [account, setAccount] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [email, setEmail] = useState('');

    const navigate = useNavigate();

    // Format time as MM:SS
    const formatTime = (seconds: number): string => {
        const minutes = Math.floor(seconds / 60);
        const remainingSeconds = seconds % 60;
        return `${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
    };

    // Timer effect
    useEffect(() => {
        if (timerActive && timeRemaining > 0) {
            timerRef.current = setInterval(() => {
                setTimeRemaining(prev => prev - 1);
            }, 1000);
        } else if (timeRemaining === 0) {
            setTimerActive(false);
        }

        return () => {
            if (timerRef.current) {
                clearInterval(timerRef.current);
            }
        };
    }, [timerActive, timeRemaining]);

    const handleAccountCheck = () => {
        // 중복 체크 로직 추가
        setIsAccountAvailable(true); // 예시로 true로 설정
    };

    const handleSendCode = () => {
        // 이메일 인증 코드 전송 로직 추가
        setIsCodeSent(true); // 인증 코드가 전송되었다고 가정
        setTimeRemaining(300); // Reset timer to 5 minutes
        setTimerActive(true); // Start the timer
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
            <Link className={getStyle('logo')} to={`/`}>Dashboard</Link>
            <form className={getStyle('signup-form')} onSubmit={handleSubmit}>
                <h2>Create Your Account</h2>

                {/* Account Field with Availability Check */}
                <div className={getStyle('input-with-button')}>
                    <input
                        type="text"
                        placeholder="Account"
                        value={account}
                        onChange={(e) => setAccount(e.target.value)}
                        required
                    />
                    <button 
                        type="button" 
                        className={getStyle('check-button')} 
                        onClick={handleAccountCheck}
                    >
                        Check
                    </button>
                </div>
                {isAccountAvailable !== null && (
                    <span className={isAccountAvailable ? getStyle('available'): getStyle('not-available')}>
                        {isAccountAvailable ? '✓ Account is available' : '✗ Account is already taken'}
                    </span>
                )}

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    required
                />

                {/* Email Field with Verification Code */}
                <div className={getStyle('input-with-button')}>
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <button 
                        type="button" 
                        className={getStyle('check-button')} 
                        onClick={handleSendCode}
                        disabled={!email}
                    >
                        {isCodeSent ? 'Resend Code' : 'Send Code'}
                    </button>
                </div>

                {isCodeSent && (
                    <div className={getStyle('verification-section')}>
                        <div className={getStyle('verification-input-with-timer')}>
                            <input
                                type="text"
                                placeholder="Enter verification code"
                                value={verificationCode}
                                onChange={(e) => setVerificationCode(e.target.value)}
                                required
                            />
                            {timerActive && (
                                <span className={getStyle('timer-display')}>
                                    {formatTime(timeRemaining)}
                                </span>
                            )}
                        </div>
                        <span className={getStyle('verification-hint')}>
                            Please check your email for the verification code
                        </span>
                    </div>
                )}

                <button type="submit" className={getStyle('submit-button')}>
                    Create Account
                </button>
                <div className={getStyle('signup-footer')}>
                    Already have an account? <Link to={`/login`}>Log In</Link>
                </div>
            </form>
        </div>
    );
};
