import React, {createContext, ReactNode, useContext} from "react";
import ApiConnector from "../api/ApiConnector";

interface ApiContextProps {
    api: ApiConnector;
}

const ApiContext = createContext<ApiContextProps | undefined>(undefined);

interface ApiProviderProps {
    children: ReactNode;
}

export const ApiProvider: React.FC<ApiProviderProps> = ({children}) => {
    const api = ApiConnector.getInstance();
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
    return context;
};