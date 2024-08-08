import {useEffect} from "react";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../store/store";
import {loggedOut, setAccessToken, setRefreshToken} from "../store/authSlice";
import localStorageKey from "../common/LocalStorageKey";
import { useNavigate } from "react-router-dom";

const Logout = () => {

    const ACCESS_TOKEN_TYPE: localStorageKey = 'access_token';
    const REFRESH_TOKEN_TYPE: localStorageKey = `refresh_token`;

    const dispatch: AppDispatch = useDispatch();
    const navigate = useNavigate();

    const logout = () => {
        dispatch(loggedOut());
        dispatch(setAccessToken(null));
        dispatch(setRefreshToken(null));
        localStorage.removeItem(ACCESS_TOKEN_TYPE);
        localStorage.removeItem(REFRESH_TOKEN_TYPE);
    }

    useEffect(() => {
        logout();
        setTimeout(() => navigate(`/`), 300)
    }, []);

    // const getStyle = useCssUtil(styles);
    return (
        <>
        </>
    )
}

export default Logout;