import styles from "./css/SignUpForm.module.css";
import useCssUtil from "../hook/useCssUtil";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";

export const SignUpForm = () => {

    const getStyle = useCssUtil(styles);

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            alert('Passwords do not match');
            return;
        }

        // 회원가입 로직을 여기에 추가합니다.
        console.log('회원가입 시도:', { email, password });

        // 예시: 회원가입 API 호출
        // const response = await fetch('/api/signup', {
        //   method: 'POST',
        //   headers: {
        //     'Content-Type': 'application/json',
        //   },
        //   body: JSON.stringify({ email, password }),
        // });

        // const data = await response.json();
        // if (data.success) {
        //   // 회원가입 성공 처리
        //   navigate('/login');
        // } else {
        //   // 회원가입 실패 처리
        // }

        // 회원가입 성공 시 로그인 페이지로 리다이렉트
        navigate('/login');
    };
    return (
        <>
            <div className={getStyle('signup-container')}>
                <form className={getStyle('signup-form')} onSubmit={handleSubmit}>
                    <h2>Sign Up</h2>
                    <input
                        type="email"
                        placeholder="Email"
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
                    <input
                        type="password"
                        placeholder="Confirm Password"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        required
                    />
                    <button type="submit">Sign Up</button>
                </form>
            </div>
        </>
    );
};