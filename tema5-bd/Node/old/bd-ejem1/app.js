var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/customersDB";

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}, function (err, db) {
    if (err) throw err;
    console.log("Database created!");
    db.close();
});