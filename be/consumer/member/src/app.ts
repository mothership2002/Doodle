import express from 'express';
import container from "./config/inversify.config";
import {Logger} from "./module/Logger";
import {TYPES} from "./config/types";
import {MessageQueueConsumer} from "./module/MessageQueueConsumer";
import {ConnectionPool} from "./module/ConnectionPool";

const app = express();
const port = process.env.PORT;
const log = container.get<Logger>(TYPES.Logger).getLogger();

app.listen(port, () => log.info(`Server start one port: ${port}`))
applicationStart()

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

module.exports = app;


function applicationStart() {
    const pool = container.get<ConnectionPool>(TYPES.ConnectionPool).getPool();
    const messageQueue = container.get<MessageQueueConsumer>(TYPES.MessageQueueConsumer).run();
}