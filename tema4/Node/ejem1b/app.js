var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/customersDB";

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}, function (err, db) {
    if (err) throw err;
    console.log("Database created!");

    // Insert one document in collection "customers"
    var collection = db.db().collection("customers");

    wrong(collection);
    // usingCallbacks(collection);
    // usingPromise(collection);
    // usingAsyncAwait(collection);

    db.close();
});

function wrong(collection) {
    collection.insertOne({
        'firstName': 'Jack',
        'lastName': 'Bauer'
    });
    collection.drop();
    console.log('Collection dropped? Error?');
}

function usingCallbacks(collection) {
    collection.insertOne({
        'firstName': 'Jack',
        'lastName': 'Bauer'
    }, function (err, res) {
        if (err) throw err;
        console.log('Document inserted');
    });
}

function usingPromise(collection) {
    collection.insertOne({
            'firstName': 'Jack',
            'lastName': 'Bauer'
        })
        .then(() => console.log('Document inserted'))
        .catch(err => console.error(error));
}

async function usingAsyncAwait(collection) {
    await collection.insertOne({
        'firstName': 'Jack',
        'lastName': 'Bauer'
    });
    console.log('Document inserted');
}