var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

var contactsCollection;

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}, async function (err, db) {
    if (err) throw err;
    contactsCollection = db.db("contactsDB").collection("contactsCollection");

    // Insert one contact
    await contactsCollection.insertOne({
        contact: "Bob",
        phones: [11111, 22222]
    });

    console.log(await getAllContacts());
    console.log("--------------------");

    // Find a contact by one of its numbers
    console.log(await contactsCollection.findOne({
        phones: 22222
    }));
    console.log("--------------------");

    // Add a new element into an array
    await contactsCollection.updateOne({
        contact: "Bob"
    }, {
        $push: {
            phones: 888888
        }
    });

    console.log(await getAllContacts());
    console.log("--------------------");

    // Remove an element from an array
    await contactsCollection.updateOne({
        contact: "Bob"
    }, {
        $pull: {
            phones: 11111
        }
    });

    console.log(await getAllContacts());
    console.log("--------------------");

    // Update an element from an array (without knowing its position)
    await contactsCollection.updateOne({
        contact: "Bob",
        phones: 888888
    }, {
        $set: {
            "phones.$": 555555
        }
    });

    console.log(await getAllContacts());
    console.log("--------------------");

    await contactsCollection.drop();
    db.close();
});

async function getAllContacts() {
    return contactsCollection.find({}).toArray();
}