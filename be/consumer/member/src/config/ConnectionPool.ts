import {Pool} from 'pg';
import {resolve} from 'path';
import winston from "winston";

// 컨피그도 템플릿으로 빼서 관리할까????

interface DatabaseInfo {
    host: string;
    port: number;
    user: string;
    password: string;
    database: string;
}

let pool: Pool | null = null;

const init = async (log: winston.Logger): Promise<DatabaseInfo> => {
    const databaseInfoPath = process.env.DATABASE;
    if (databaseInfoPath) {
        const path = resolve(__dirname, '../' + databaseInfoPath);
        log.info(`Database info path: ${path}`)
        return await import(path);
    } else {
        log.error('DATABASE environment variable is not defined.');
        throw new Error('DATABASE environment variable is not defined.'); // handling point?
    }
}

const createPool = async (log: winston.Logger): Promise<Pool> => new Pool(await init(log));

const getPool = async (log :winston.Logger) => {
    log.info('Creating connection pool');
    if (!pool) {
        pool = await createPool(log);
    }
    log.info('Connection pool created');
    return pool;
}

export default getPool;