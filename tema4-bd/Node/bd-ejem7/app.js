import mysql from 'mysql2/promise';

const conn = await mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'password',
    database: 'posts'
});

console.log("Connected to MySQL");

await conn.execute('CREATE TABLE customers(firstName VARCHAR(255), lastName VARCHAR(255))');

await conn.execute(
    'INSERT INTO customers SET firstName = ?, lastName = ?',
    ['Jack', 'Bauer']
);

console.log("Customer inserted");

await conn.close();

console.log("Connection closed");
