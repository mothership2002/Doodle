import Header from "../component/Header";
import Footer from "../component/Footer";
import {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {RootState} from "../store/store";
import {useApi} from "../component/ApiComponentContext";
import StringUtils from "../utils/StringUtils";
import SessionStorageKey from "../common/SessionStorageKey";
import sessionStorageKey from "../common/SessionStorageKey";
import {setAccessToken, setRefreshToken} from "../store/authSlice";

const hasToken = (type: SessionStorageKey) => {
    return StringUtils.hasText(sessionStorage.getItem(type));
}

const Main = () => {

    const ACCESS_TOKEN_TYPE = 'access_token';
    const REFRESH_TOKEN_TYPE = `refresh_token`;

    const api = useApi();
    const auth = useSelector((state: RootState) => state.auth);
    const dispatch = useDispatch();

    const validateToken = async (token: string, type: sessionStorageKey) => {
        const param = { type }
        const result = await api.call("/api/auth/token", "GET", param);

    }

    useEffect( () => {
        const init = () => {
            // sessionStorage.setItem(ACCESS_TOKEN_TYPE, 'hello');
            // sessionStorage.setItem(REFRESH_TOKEN_TYPE, 'world');
            dispatch(setAccessToken(hasToken(ACCESS_TOKEN_TYPE)));
            dispatch(setRefreshToken(hasToken(REFRESH_TOKEN_TYPE)));
            console.log(auth)
        }

        init();
    }, [])

    return (
        <>
            <Header/>
            <p>hello world</p>
            <Footer/>
        </>
    )
}

export default Main;