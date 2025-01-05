import express from 'express';
import path from 'path';
// import logger from 'morgan';

import pool from "./config/ConnectionPool";

const app = express();
const port = process.env.PORT;

const poolPromise = pool();

app.listen(port, () => console.log(`server start one port: ${port}`))

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, 'public')));

module.exports = app;
