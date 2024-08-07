import StatusCode from "./StatusCode";
import Domain from "../domain/Domain";

class Response {

    constructor(statusCode: StatusCode, message: string, data: Domain) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    statusCode: StatusCode;
    message: string;
    data: Domain;

}

export default Response;