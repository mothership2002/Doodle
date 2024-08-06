import ParentParam from "../ParentParam";

interface User extends ParentParam {
    accountNo: number | null;
    account: string | null;
    password: string | null;
    nickname: string | null;
    email: string | null;
    role: string | null;
}

export default User;