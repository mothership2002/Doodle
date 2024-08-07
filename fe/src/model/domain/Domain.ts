import User from "./User";
import Jwt from "./JsonWebToken";

type Domain = User | Jwt | null;

export default Domain