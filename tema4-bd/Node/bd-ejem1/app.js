const MongoClient = require('mongodb').MongoClient;
const url = "mongodb://localhost:27017/customersDB";

async function main() {

    const conn = await MongoClient.connect(url, {
        useUnifiedTopology: true,
        useNewUrlParser: true
    });

    console.log("Connected to Mongo");

    const customers = conn.db().collection('customers');

    await customers.insertOne({
        firstName: 'Jack',
        lastName: 'Bauer'
    });

    console.log("Customer inserted");

    conn.close();

    console.log("Connection closed");
}

main();