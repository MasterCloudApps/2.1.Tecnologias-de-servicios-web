var mongoose = require('mongoose');

const url = "mongodb://localhost:27017/customersDB";

let Customer;

async function insertOne() {

    const consumer = new Customer({
        firstName: 'Jack',
        lastName: 'Bauer'
    });

    await consumer.save();

    console.log('Customer inserted');
}

async function insertOneWithId() {

    const consumer = new Customer({
        firstName: 'Jack',
        lastName: 'Bauer'
    });

    await consumer.save();

    console.log('Customer inserted with id:', consumer._id);

    return consumer._id;
}

async function insertMany() {

    await Customer.collection.insertMany([
        { firstName: 'Jack', lastName: 'Bauer' },
        { firstName: 'Juan', lastName: 'Pérez' }
    ]);

    console.log('Customers inserted');
}

async function insertManyWithId() {

    const { insertedIds } = await Customer.collection.insertMany([
        { firstName: 'Jack', lastName: 'Bauer' },
        { firstName: 'Juan', lastName: 'Pérez' }
    ]);

    console.log('Customers inserted with ids:', insertedIds);
}

async function findCustomerWithQuery() {

    const result = await Customer.find({ firstName: 'Juan' }).exec();

    console.log('Customers with firstName = "Juan":', result);
}

async function findCustomerById(id) {

    const customer = await Customer.findById(id);

    console.log('Customer with id:', customer);
}

async function updateCustomerById(id) {

    await Customer.findByIdAndUpdate(id,{ $set: { firstName: 'Pedro', age: 45 }});

    console.log('Updated customer with id:', id);
}

async function updateCustomersByFirstName() {

    const { matchedCount: n } = await Customer.updateMany(
        { firstName: 'Juan' },
        { $set: { firstName: 'John' } }
    );

    console.log(`Updated ${n} customers with name "Juan"`);
}

async function deleteCustomerById(id) {

    await Customer.findByIdAndDelete(id);

    console.log('Deleted customer with id:', id);
}

async function deleteCustomersByFirstName() {

    const { deletedCount } = await Customer.deleteMany({ firstName: 'John' });

    console.log(`Deleted ${deletedCount} customers with name "John"`);
}

async function main() {

    await mongoose.connect(url, {
        useUnifiedTopology: true,
        useNewUrlParser: true,
        useFindAndModify: false
    });

    console.log("Connected to Mongo");

    var customerSchema = new mongoose.Schema({
        firstName: String,
        lastName: String
    });

    Customer = mongoose.model('Customer', customerSchema);

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
