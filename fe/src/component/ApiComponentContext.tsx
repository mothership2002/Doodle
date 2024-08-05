import React, {createContext, ReactNode, useContext} from "react";
import { ApiConnector, instance } from "../api/ApiConnector";

interface ApiContextProps {
    api: ApiConnector;
}

interface ApiProviderProps {
    children: ReactNode;
}

const ApiContext = createContext<ApiContextProps | undefined>(undefined);

export const ApiProvider: React.FC<ApiProviderProps> = ({children}) => {
    const api = instance;
    return (
        <ApiContext.Provider value={{api}}>
            {children}
        </ApiContext.Provider>
    )
}

export const useApi = () => {
    const context = useContext(ApiContext)
    if (context === undefined) {
        throw new Error();
    }
    return context.api;
};