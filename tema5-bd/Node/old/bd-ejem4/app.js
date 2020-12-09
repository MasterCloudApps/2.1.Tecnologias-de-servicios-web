var express = require('express');
var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";


// Data base connection and initialization
var contactsCollection;

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}).then(async db => {
    contactsCollection = db.db("contactsDB").collection("contactsCollection");
    await contactsCollection.insertMany([{
        contact: "Bob",
        phones: [11111, 22222]
    }, {
        contact: "Alice",
        phones: [333333, 444444]
    }]);
});


// Express controller initialization
var app = express();
app.use(express.json()); // support json encoded bodies

app.get("/", async function (req, res) {
    res.json(await contactsCollection.find({}).toArray());
});

app.post("/", async function (req, res) {
    var response = await contactsCollection.insertOne(req.body);
    res.json(response.ops[0]);
});

app.listen(3000);