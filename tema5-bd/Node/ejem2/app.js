var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

var customersDB;
var customersCollection;

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}, function (err, db) {
    if (err) throw err;
    customersDB = db.db('customersDB');
    customersCollection = customersDB.collection("customers");
    customersCollection.insertMany([{
            'firstName': 'Jack',
            'lastName': 'Bauer'
        },
        {
            'firstName': 'David',
            'lastName': 'Palmer'
        }
    ]);

    listAllCollections();
    listAllCustomers();
    removeOneCustomer('David');
    listAllCustomers();
    removeAllCustomers();
    listAllCustomers();
    removeCollection();
    listAllCollections();

    db.close();
});

function listAllCollections() {
    return customersDB.listCollections().toArray((err, collections) => {
        collections.map(collection => collection.name).forEach(name => console.log(name));
    });
}

function listAllCustomers() {
    customersCollection.find({}).toArray((err, documents) => {
        documents.forEach(doc => console.log(doc));
    });
}

function removeOneCustomer(firstName) {
    customersCollection.deleteOne({
        firstName
    });
}

function removeAllCustomers() {
    customersCollection.deleteMany({});
}

function removeCollection() {
    customersCollection.drop();
}