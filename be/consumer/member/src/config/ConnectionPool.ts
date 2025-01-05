import {Pool} from 'pg';
import {resolve} from 'path';

interface DatabaseInfo {
    host: string;
    port: number;
    user: string;
    password: string;
    database: string;
}

let pool: Pool | null = null;

const init = async (): Promise<DatabaseInfo> => {
    const databaseInfoPath = process.env.DATABASE;
    if (databaseInfoPath) {
        const path = resolve(__dirname, '../' + databaseInfoPath);
        console.log(`Database info path: ${path}`)
        return await import(path);
    } else {
        throw new Error('DATABASE environment variable is not defined.');
    }
}

const createPool = async (): Promise<Pool> => new Pool(await init());

const getPool = async () => {
    if (!pool) {
        pool = await createPool();
    }
    return pool;
}

export default getPool;