import {Pool} from 'pg';
import {inject, injectable} from "inversify";
import {TYPES} from "../config/types";
import {BaseModule} from "./Module";
import {Logger} from "./Logger";

interface DatabaseInfo {
    host: string;
    port: number;
    user: string;
    password: string;
    database: string;
}

@injectable()
export class ConnectionPool extends BaseModule {

    private pool: Pool | null = null;

    constructor(@inject(TYPES.Logger) logger: Logger) {
        super(logger.getLogger());
    }

    private async createPool(): Promise<Pool> {
        return new Pool(await super.init(process.env.DATABASE, 'connection pool'))
    };

    async getPool(): Promise<Pool> {
        this.log.info('Creating connection pool');
        if (!this.pool) {
            this.pool = await this.createPool();
        }
        this.log.info('Connection pool created');
        return this.pool;
    }
}

