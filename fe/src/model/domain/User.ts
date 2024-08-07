import ParentParam from "../ParentParam";

export default interface User extends ParentParam {
    accountNo: number | null;
    account: string | null;
    password: string | null;
    nickname: string | null;
    email: string | null;
    role: string | null;
}
