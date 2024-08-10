import Header from "../component/Header";
import Footer from "../component/Footer";
import React, {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {AppDispatch, RootState} from "../store/store";
import {useApi} from "../component/ApiComponentContext";
import {StringUtils} from "../utils/StringUtils";
import localStorageKey from "../common/LocalStorageKey";
import {loggedIn, setAccessToken, setRefreshToken} from "../store/authSlice";
import {jwtDecode} from "jwt-decode";
import ResponseUtils from "../utils/ResponseUtils";
import {ApiConnector} from "../api/ApiConnector";
import Response from "../model/http/Response";

const hasToken = (type: localStorageKey) => {
    return StringUtils.hasText(localStorage.getItem(type));
}

const Main = () => {

    const sampleJwt = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJhY2NvdW50IjoibW90aGVyc2hpcCIsImVtYWlsIjoiZmlja3RAbmF2ZXIuY29tIiwibmlja25hbWUiOiJoZWxsbyIsInJvbGUiOiJhZG1pbiJ9.ZaLcSS-OwNIOLZfvkKqcuCUm5kgR6ptwnNIQTVXK2Pw';

    const ACCESS_TOKEN_TYPE: localStorageKey = 'access_token';
    const REFRESH_TOKEN_TYPE: localStorageKey = `refresh_token`;

    const api: ApiConnector = useApi();
    const auth = useSelector((state: RootState) => state.auth);
    const dispatch: AppDispatch = useDispatch();

    const [isProcessing, setIsProcessing] = useState<boolean>(false);
    const [isAccessAble, setIsAccessAble] = useState<boolean>(true);

    // const validateToken = async (token: string, type: localStorageKey) => {
    //     const param = {[type]: token}
    //     return await api.call("/api/auth/token", "GET", param);
    // }

    // mock up
    const validateToken = async (token: string, type: localStorageKey): Promise<Response> => {
        return new Promise(resolve =>
            setTimeout(() =>
                resolve(new Response(200, 'success', null)), 300));
    }

    const handleTokenValidation = async (token: string, tokenType: localStorageKey) => {
        const result = await validateToken(token, tokenType);
        if (!ResponseUtils.isSuccess(result)) {
            setIsAccessAble(false);
            return false;
        }
        if (ResponseUtils.isValid(result)) {
            return result;
        }
        localStorage.removeItem(tokenType);
        return null;
    }

    const init = async () => {
        if (auth.accessToken) {
            const accessTokenResult = await handleTokenValidation(auth.accessToken, ACCESS_TOKEN_TYPE);
            if (accessTokenResult) {
                dispatch(loggedIn(jwtDecode(auth.accessToken)));
                return;
            }

            if (auth.refreshToken) {
                const refreshTokenResult = await handleTokenValidation(auth.refreshToken, REFRESH_TOKEN_TYPE);
                if (refreshTokenResult && ResponseUtils.isJwt(refreshTokenResult.data)) {
                    dispatch(setAccessToken(refreshTokenResult.data.accessToken));
                    dispatch(loggedIn(jwtDecode(auth.accessToken)));
                    return;
                }
            }
        }
    }

    useEffect(() => {
        dispatch(setAccessToken(hasToken(ACCESS_TOKEN_TYPE)));
        dispatch(setRefreshToken(hasToken(REFRESH_TOKEN_TYPE)));
    }, [dispatch]);

    useEffect(() => {
        setIsProcessing(true);
        // init().then(r => setIsProcessing(isProcessing));
        // mock up
        init().then(r => setTimeout(() => setIsProcessing(isProcessing), 300));
    }, [auth.accessToken, auth.refreshToken, api, dispatch]);

    return (
        <>
            <Header isProcessing={isProcessing}/>
            <p>access : {auth.accessToken}</p>
            <p>refresh : {auth.refreshToken}</p>
            <p>user : {JSON.stringify(auth.user)}</p>
            <p>hello world</p>
            <p>access able : {isAccessAble.toString()}</p>
            <Footer/>
        </>
    )
}

export default Main;