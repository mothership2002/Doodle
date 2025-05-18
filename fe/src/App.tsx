import React from "react";
import { Routes, Route, useLocation } from 'react-router-dom';
import Main from "./page/Main";
import Login from "./page/Login";
import Logout from "./page/Logout";
import SignUp from "./page/SignUp";
import PageTransition from "./component/PageTransition";


const App: React.FC = () => {
    const location = useLocation();

    return (
        <PageTransition>
            <Routes location={location} key={location.pathname}>
                <Route path={`/`} element={<Main />} />
                <Route path={`/login`} element={<Login />} />
                <Route path={`/logout`} element={<Logout />} />
                <Route path={`/sign-up`} element={<SignUp />}></Route>
                {/*<Route path="/about" element={<About/>}/>*/}
                {/*<Route path="/user/:userId" element={<User/>}/>*/}
                {/*<Route path="/search" element={<Search/>}/>*/}
                {/*<Route path="/settings" element={<Settings/>}>*/}
                {/*    <Route path="general" element={<GeneralSettings/>}/>*/}
                {/*    <Route path="profile" element={<ProfileSettings/>}/>*/}
                {/*</Route>*/}
                {/*<Route path="*" element={<NotFound/>}/>*/}
            </Routes>
        </PageTransition>
    )
}

export default App;
