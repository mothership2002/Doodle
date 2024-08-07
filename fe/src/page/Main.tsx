import Header from "../component/Header";
import Footer from "../component/Footer";
import React, {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {AppDispatch, RootState} from "../store/store";
import {useApi} from "../component/ApiComponentContext";
import StringUtils from "../utils/StringUtils";
import SessionStorageKey from "../common/SessionStorageKey";
import sessionStorageKey from "../common/SessionStorageKey";
import {login, setAccessToken, setRefreshToken} from "../store/authSlice";
import Jwt from "../model/domain/JsonWebToken";
import {jwtDecode} from "jwt-decode";
import ResponseUtils from "../utils/ResponseUtils";
import {ApiConnector} from "../api/ApiConnector";
import Response from "../model/http/Response";

const hasToken = (type: SessionStorageKey) => {
    return StringUtils.hasText(sessionStorage.getItem(type));
}

const Main = () => {

    const sampleJwt = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJhY2NvdW50IjoibW90aGVyc2hpcCIsImVtYWlsIjoiZmlja3RAbmF2ZXIuY29tIiwibmlja25hbWUiOiJoZWxsbyIsInJvbGUiOiJhZG1pbiJ9.ZaLcSS-OwNIOLZfvkKqcuCUm5kgR6ptwnNIQTVXK2Pw';

    const ACCESS_TOKEN_TYPE: SessionStorageKey = 'access_token';
    const REFRESH_TOKEN_TYPE: SessionStorageKey = `refresh_token`;

    const api: ApiConnector = useApi();
    const auth = useSelector((state: RootState) => state.auth);
    const dispatch: AppDispatch = useDispatch();

    const [isAccessAble, setIsAccessAble] = useState<boolean>(true);

    /**
     * type guard
     * @param jwt
     */
    const isJwt = (jwt: any): jwt is Jwt => {
        return (jwt as Jwt).accessToken !== undefined;
    }
    useEffect(() => {
        dispatch(setAccessToken(hasToken(ACCESS_TOKEN_TYPE)));
        dispatch(setRefreshToken(hasToken(REFRESH_TOKEN_TYPE)));
    }, [dispatch, ACCESS_TOKEN_TYPE, REFRESH_TOKEN_TYPE]);

    useEffect(() => {
        // const validateToken = async (token: string, type: sessionStorageKey) => {
        //     const param = {[type]: token}
        //     return await api.call("/api/auth/token", "GET", param);
        // }

        // mock up
        const validateToken = async (token: string, type: sessionStorageKey): Promise<Response> => {
            return new Promise(resolve =>
                setTimeout(() =>
                    resolve(new Response(200, 'success', null)), 300));
        }

        const init = async () => {
            if (auth.accessToken) {
                const accessTokenResult = await validateToken(auth.accessToken, ACCESS_TOKEN_TYPE);
                if (!ResponseUtils.isSuccess(accessTokenResult)) {
                    setIsAccessAble(false);
                    return;
                }
                if (ResponseUtils.isValid(accessTokenResult)) {
                    dispatch(login(jwtDecode(auth.accessToken)));
                    return;
                }

                if (auth.refreshToken) {
                    const refreshTokenResult = await validateToken(auth.refreshToken, REFRESH_TOKEN_TYPE);
                    if (!ResponseUtils.isSuccess(refreshTokenResult)) {
                        setIsAccessAble(false);
                        return;
                    }

                    if (ResponseUtils.isValid(refreshTokenResult) && isJwt(refreshTokenResult.data)) {
                        dispatch(setAccessToken(refreshTokenResult.data.accessToken));
                        dispatch(login(jwtDecode(auth.accessToken)))
                    }
                }
            }
        }
        init().then(r => null);
    }, [auth.accessToken, auth.refreshToken, api, dispatch, ACCESS_TOKEN_TYPE, REFRESH_TOKEN_TYPE]);

    return (
        <>
            <Header/>
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