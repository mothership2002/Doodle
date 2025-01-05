import {Pool} from 'pg';

const pool = new Pool(
    {
        user: 'postgres',
        host: 'localhost',
        database: 'doodle',
        password: '<PASSWORD>',
        port: 5432,
    }
);