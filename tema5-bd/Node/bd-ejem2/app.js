const MongoClient = require('mongodb').MongoClient;
const ObjectId = require('mongodb').ObjectId;

const url = "mongodb://localhost:27017/customersDB";

let conn;
let customers;

async function insertOne() {

    await customers.insertOne({
        firstName: 'Jack',
        lastName: 'Bauer'
    });

    console.log('Customer inserted');
}

async function insertOneWithId() {

    const { insertedId } = await customers.insertOne({
        firstName: 'Jack',
        lastName: 'Bauer'
    });

    console.log('Customer inserted with id:', insertedId);

    return insertedId;
}

async function insertMany() {

    await customers.insertMany([
        { firstName: 'Jack', lastName: 'Bauer' },
        { firstName: 'Juan', lastName: 'Pérez' }
    ]);

    console.log('Customers inserted');
}

async function insertManyWithId() {

    const { insertedIds } = await customers.insertMany([
        { firstName: 'Jack', lastName: 'Bauer' },
        { firstName: 'Juan', lastName: 'Pérez' }
    ]);

    console.log('Customers inserted with ids:', insertedIds);
}

async function findCustomerWithQuery() {

    const result = await customers.find({ firstName: 'Juan' }).toArray();

    console.log('Customers with firstName = "Juan":', result);
}

async function findCustomerById(id) {

    const customer = await customers.findOne({ _id: new ObjectId(id) });

    console.log('Customer with id:', customer);
}

async function updateCustomerById(id) {

    await customers.updateOne(
        { _id: new ObjectId(id) },
        { $set: { firstName: 'Pedro', age: 45 } }
    );

    console.log('Updated customer with id:', id);
}

async function updateCustomersByFirstName() {

    const { matchedCount } = await customers.updateMany(
        { firstName: 'Juan' },
        { $set: { firstName: 'John' } }
    );

    console.log(`Updated ${matchedCount} customers with name "Juan"`);
}

async function deleteCustomerById(id) {

    await customers.deleteOne({ _id: new ObjectId(id) });

    console.log('Deleted customer with id:', id);
}

async function deleteCustomersByFirstName() {

    const { deletedCount } = await customers.deleteMany({ firstName: 'John' });

    console.log(`Deleted ${deletedCount} customers with name "John"`);
}



async function main() {

    conn = await MongoClient.connect(url, {
        useUnifiedTopology: true,
        useNewUrlParser: true
    });

    console.log("Connected to Mongo");

    customers = conn.db().collection('customers');

    await insertOne();
    const id = await insertOneWithId();
    await insertMany();
    await insertManyWithId();
    await findCustomerWithQuery();
    await findCustomerById(id);
    await updateCustomerById(id);
    await updateCustomersByFirstName();
    await deleteCustomerById(id);
    await deleteCustomersByFirstName();

    conn.close();

    console.log("Connection closed");
}

main();

