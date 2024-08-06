import StatusCode from "./StatusCode";
import Domain from "../domain/Domain";

interface Response {

    statusCode: StatusCode;
    message: string;
    data: Domain;

}

export default Response;