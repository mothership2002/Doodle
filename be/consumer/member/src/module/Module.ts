import {resolve} from "path";
import winston from "winston";



interface Module {
    init(jsonPath: string | undefined, moduleName: string): Promise<any>;
}

export abstract class BaseModule implements Module {
    protected readonly log: winston.Logger;

    protected constructor(logger: winston.Logger) {
        this.log = logger;
    }

    async init(jsonPath: string | undefined, moduleName: string): Promise<any> {
        if (jsonPath) {
            const path = resolve(__dirname, '../' + jsonPath);
            this.log.info(`${moduleName} info path: ${path}`);
            return await import(path);
        } else {
            this.log.error(`${moduleName} environment variable is not defined.`);
            throw new Error(`${moduleName} environment variable is not defined.`); // handling point?
        }
    }
}
