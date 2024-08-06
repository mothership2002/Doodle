import { Method, Header } from '../model/http/HttpDomain';
import Endpoint from "../common/ServerEndpoint";
import ParentParam from "../model/ParentParam";
import Response from "../model/http/Response";

const CONTENT_TYPE: string = 'Content-Type';
const MEDIA_TYPE: string = 'application/json';
const AUTHORIZATION: string = 'Authorization';

class ApiConnector {

    private readonly domain: string;

    constructor(domain: string) {
        this.domain = domain;
    }

    /** TODO arg update // additional param - (jwt)
     *
     * @param endPoint  @ api url -> start with '/'
     * @param method    @ http method
     * @param param     @ object param
     */
    async call(endPoint: Endpoint, method: Method, param: ParentParam | null) {
        // TODO TOKEN -> ? axios ?
        const token = null;
        return await fetch(this.domain + endPoint, this.initHttpRequest(method, param, token));
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
const domain = 'http://localhost:8080';
const instance = new ApiConnector(domain);
Object.freeze(instance);

export { ApiConnector, instance };