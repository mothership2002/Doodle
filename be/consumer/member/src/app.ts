import express from 'express';

import pool from "./config/ConnectionPool";
import kafkaConsumer from "./config/MessageQueueConsumer";
import Logger from "./config/Logger";

const app = express();
const port = process.env.PORT;

const log = Logger.getLogger();

const poolPromise = pool(log);
const kafka = kafkaConsumer(log)


app.listen(port, () => log.info(`Server start one port: ${port}`))

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

module.exports = app;
