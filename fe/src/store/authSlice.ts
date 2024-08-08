import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import User from "../model/domain/User";


interface AuthState {
    user: User | null;
    accessToken: string | null;
    refreshToken: string | null;
}

const initialState: AuthState = {
    user: null,
    accessToken: null,
    refreshToken: null
};

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        loggedIn: (state, action: PayloadAction<User>) => {
            state.user = action.payload;
        },
        loggedOut: (state) => {
            state.user = null;
        },
        setAccessToken: (state, action) => {
            state.accessToken = action.payload;
        },
        setRefreshToken: (state, action) => {
            state.refreshToken = action.payload;
        }
    }
});

export const
    {
        loggedIn,
        loggedOut,
        setRefreshToken,
        setAccessToken,
    } = authSlice.actions;
export default authSlice.reducer;