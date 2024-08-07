import React from "react";
import { Routes, Route } from 'react-router-dom';
import Main from "./page/Main";


const App: React.FC = () => {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Main/>}/>
                {/*<Route path="/about" element={<About/>}/>*/}
                {/*<Route path="/user/:userId" element={<User/>}/>*/}
                {/*<Route path="/search" element={<Search/>}/>*/}
                {/*<Route path="/settings" element={<Settings/>}>*/}
                {/*    <Route path="general" element={<GeneralSettings/>}/>*/}
                {/*    <Route path="profile" element={<ProfileSettings/>}/>*/}
                {/*</Route>*/}
                {/*<Route path="*" element={<NotFound/>}/>*/}
            </Routes>
        </div>
    )
}

export default App;