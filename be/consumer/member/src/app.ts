import express from 'express';
import path from 'path';
// import logger from 'morgan';

import pool from "./config/ConnectionPool";
import kafkaConfig from "./config/KafkaConfig";

const app = express();
const port = process.env.PORT;

const poolPromise = pool();
const kafka = kafkaConfig()

app.listen(port, () => console.log(`Server start one port: ${port}`))

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

module.exports = app;
