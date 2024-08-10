import StatusCode from "../model/http/StatusCode";
import Response from "../model/http/Response";
import Jwt from "../model/domain/JsonWebToken";

class ResponseUtils {

    /**
     * able to connect to server
     * @param response
     */
    static isSuccess(response: Response) {
        return response.statusCode !== StatusCode.INTERNAL_SERVER_ERROR;
    }

    /**
     * valid status
     * @param response
     */
    static isValid(response: Response) {
        return response.statusCode !== StatusCode.UNPROCESSABLE_ENTITY;
    }


    /**
     * type guard
     * @param jwt
     */
    static isJwt = (jwt: any): jwt is Jwt => {
        return (jwt as Jwt).accessToken !== undefined;
    }



}
export default ResponseUtils;