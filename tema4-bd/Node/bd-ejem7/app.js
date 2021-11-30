const mysql = require('mysql2/promise');

async function main() {

    const conn = await mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: 'pass',
        database: 'customersDB'
    });

    console.log("Connected to MySQL");

    await conn.execute(
        'INSERT INTO customers SET firstName = ?, lastName = ?',
        ['Jack', 'Bauer']
    );

    console.log("Customer inserted");

    await conn.close();

    console.log("Connection closed");
}

main();