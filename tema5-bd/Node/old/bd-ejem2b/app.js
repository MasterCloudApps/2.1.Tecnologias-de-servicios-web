var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

var customersDB;
var customersCollection;

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}, async function (err, db) {
    if (err) throw err;
    customersDB = db.db('customersDB');
    customersCollection = customersDB.collection("customers");
    await customersCollection.insertMany([{
            'firstName': 'Jack',
            'lastName': 'Bauer'
        },
        {
            'firstName': 'David',
            'lastName': 'Palmer'
        }
    ]);

    console.log(await listAllCollections());
    console.log(await listAllCustomers());
    await removeOneCustomer('David');
    console.log(await listAllCustomers());
    await removeAllCustomers();
    console.log(await listAllCustomers());
    removeCollection();
    console.log(await listAllCollections());

    db.close();
});

async function listAllCollections() {
    return new Promise((resolve, reject) => {
        customersDB.listCollections().toArray((err, collections) => {
            err ? reject(err) : resolve(collections.map(collection => collection.name));
        });
    });
}

async function listAllCustomers() {
    return new Promise((resolve, reject) => {
        customersCollection.find({}).toArray((err, documents) => {
            err ? reject(err) : resolve(documents);
        });
    });
}

async function removeOneCustomer(firstName) {
    await customersCollection.deleteOne({
        firstName
    });
}

async function removeAllCustomers() {
    await customersCollection.deleteMany({});
}

async function removeCollection() {
    await customersCollection.drop();
}
