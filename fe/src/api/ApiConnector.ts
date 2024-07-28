import { Method, Header } from '../model/HttpDomain';

const CONTENT_TYPE: string = 'Content-Type';
const MEDIA_TYPE: string = 'application/json';
const AUTHORIZATION: string = 'Authorization';

class ApiConnector {

    private static instance: ApiConnector;
    private readonly domain: string;

    constructor(domain: string) {
        this.domain = domain;
    }

    public static getInstance(): ApiConnector {
        const domain = 'http://localhost:8080';
        if (!ApiConnector.instance) {
            ApiConnector.instance = new ApiConnector(domain);
        }
        return ApiConnector.getInstance();
    }

    /** TODO arg update // additional param - (jwt)
     *
     * @param endPoint  @ api url -> start with '/'
     * @param method    @ http method
     * @param param     @ object param
     */
    async call(endPoint: string, method: Method, param: object | null) {
        // TODO TOKEN -> ? axios ?
        const token = null;
        return await fetch(this.domain + endPoint, this.initHttpRequest(method, param, token))
    }

    private getHeader(token: string | null): Header {
        return {
            [CONTENT_TYPE]: MEDIA_TYPE,
            [AUTHORIZATION]: token // jwt
        }
    }

    private initHttpRequest(method: Method, param: object | null, token: string | null) {
        return {
            method: method,
            header: this.getHeader(token),
        }
    }
}

export default ApiConnector;