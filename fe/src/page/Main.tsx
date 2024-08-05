import Header from "../component/Header";
import Footer from "../component/Footer";
import {useEffect, useState} from "react";
import {useSelector} from "react-redux";
import {RootState} from "../store/store";
import {useApi} from "../component/ApiComponentContext";
import StringUtils from "../utils/StringUtils";
import SessionStorageKey from "../common/SessionStorageKey";

const Main = () => {

    const api = useApi();
    const user = useSelector((state: RootState) => state.auth);

    const [isLoggedIn, setIsLoggedIn] = useState<boolean>();

    const hasSession = () => {
        return user !== null;
    }

    const hasToken = (type: SessionStorageKey) => {
        return StringUtils.hasText(sessionStorage.getItem(type));
    }

    const temp = async () => {

        // temp;
        if (!hasSession()) {
            if (!hasToken('access_token')) {
                // user data
                const result = await api.call('/api/auth/token', 'GET', {});
            } else if (!hasToken('refresh_token')) {

            }
        }
        else {
            setIsLoggedIn(true);
        }
    }


    useEffect(() => {
        temp();
    }, [user])

    return (
        <>
            <Header/>
            <p>hello world</p>
            <Footer/>
        </>
    )
}

export default Main;