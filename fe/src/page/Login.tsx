import {LoginForm} from "../component/LoginForm";
import {useApi} from "../component/ApiComponentContext";
import {useNavigate} from "react-router-dom";
import ResponseUtils from "../utils/ResponseUtils";
import {jwtDecode} from "jwt-decode";
import {useDispatch} from "react-redux";
import {AppDispatch} from "../store/store";
import {loggedIn, setAccessToken, setRefreshToken} from "../store/authSlice";
import Jwt from "../model/domain/JsonWebToken";

interface ChildData {
    account: string;
    password: string;
}

const Login = () => {

    const api = useApi();
    const navigate = useNavigate();
    const dispatch: AppDispatch = useDispatch();

    const login = (result: Jwt) => {
        localStorage.setItem('access_token', result.accessToken);
        localStorage.setItem(`refresh_token`, result.refreshToken)

        dispatch(setAccessToken(result.accessToken));
        dispatch(setRefreshToken(result.refreshToken));
        dispatch(loggedIn(jwtDecode(result.accessToken)));
        navigate(`/`);
    }

    const handleData = async (child: ChildData) => {
        const account = child.account;
        // TODO encrypt
        const password = child.password;

        const result = await api.call('/api/auth/login', 'POST', {account, password});

        if (ResponseUtils.isSuccess(result)) {
            // type guard
            if (ResponseUtils.isJwt(result.data)) {
                login(result.data);
            } else {
                // /login fail
            }
        }
    }

    return (
        <LoginForm onData={handleData}/>
    );
}

export default Login;