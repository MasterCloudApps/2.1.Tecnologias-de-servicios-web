const mysql = require('mysql2/promise');

let conn;

async function insertOne() {

    await conn.execute(
        'INSERT INTO customers SET firstName = ?, lastName = ?',
        ['Jack', 'Bauer']
    );

    console.log('Customer inserted');
}

async function insertOneWithId() {

    const [{ insertId }] = await conn.execute(
        'INSERT INTO customers SET firstName = ?, lastName = ?',
        ['Jack', 'Bauer']
    );

    console.log('Customer inserted with id:', insertId);

    return insertId;
}

async function findCustomerWithQuery() {

    const [rows, fields] = await conn.execute(
        'SELECT * FROM customers WHERE firstName = ?', 
        ['Jack']
    );

    console.log('Customers with firstName = "Jack":', rows);
}

async function findCustomerById(id) {

    const [rows, fields] = await conn.execute(
        'SELECT * FROM customers WHERE id = ?', 
        [id]
    );

    console.log('Customer with id:', rows[0]);
}

async function updateCustomerById(id) {

    await conn.execute(
        'UPDATE customers SET firstName = ? WHERE id = ?',
        ['Pedro', id]
    );

    console.log('Updated customer with id:', id);
}

async function updateCustomersByFirstName() {

    const [{ affectedRows }] = await conn.execute(
        'UPDATE customers SET firstName = ? WHERE firstName = ?',
        ['John', 'Pedro']
    );

    console.log(`Updated ${affectedRows} customers with name "Pedro"`);
}

async function deleteCustomerById(id) {

    await conn.execute('DELETE FROM customers WHERE id = ?', [id]);

    console.log('Deleted customer with id:', id);
}

async function deleteCustomersByFirstName() {

    const [{ affectedRows }] = await conn.execute(
        'DELETE FROM customers WHERE firstName = ?', 
        ['John']
    );

    console.log(`Deleted ${affectedRows} customers with name "John"`);
}

async function main() {

    conn = await mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: 'pass',
        database: 'customersDB'
    });

    console.log("Connected to MySQL");

    await insertOne();
    const id = await insertOneWithId();
    await findCustomerWithQuery();
    await findCustomerById(id);
    await updateCustomerById(id);
    await updateCustomersByFirstName();
    await deleteCustomerById(id);
    await deleteCustomersByFirstName();

    await conn.close();

    console.log("Connection closed");
}

main();