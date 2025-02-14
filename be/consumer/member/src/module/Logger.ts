import winston from "winston";
import DailyRotateFile from 'winston-daily-rotate-file';
import {injectable} from "inversify";

@injectable()
export class Logger {

    private readonly instance: winston.Logger;

    constructor() {
        console.log('creating logger');
        this.instance = winston.createLogger({
            level: 'info',
            format: winston.format.combine(
                winston.format.timestamp({format: 'YYYY-MM-DD HH:mm:ss'}),
                winston.format.printf(({level, message, timestamp}) => {
                    return `[${timestamp}] [${level.toUpperCase()}] ${message}`;
                })
            ),
            transports: [
                new DailyRotateFile({
                    filename: 'logs/%DATE%.log',
                    datePattern: 'YYYY-MM-DD',
                    zippedArchive: true,
                    maxSize: '20m',
                    maxFiles: '7d'
                }),
                new winston.transports.Console({
                    format: winston.format.combine(
                        winston.format.colorize(),
                        winston.format.printf(({level, message, timestamp}) => {
                            return `[${timestamp}] [${level}] ${message}`;
                        })
                    )
                })
            ]
        });
        console.log('created logger');
    }

    getLogger() {
        return this.instance;
    }
}

