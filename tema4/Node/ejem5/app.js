var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

MongoClient.connect(url, {
    useUnifiedTopology: true,
    useNewUrlParser: true
}, async function (err, db) {
    if (err) throw err;

    collection1 = db.db("contactsDB").collection("collection1");
    await collection1.insertMany([{
        contact: "Bob",
        city: "Madrid",
        phones: [11111, 22222]
    }, {
        contact: "Alice",
        city: "Barcelona",
        phones: [33333, 44444]
    }]);

    collection2 = db.db("contactsDB").collection("collection2");
    await collection2.insertMany([{
        name: "Madrid",
        population: 3000000
    }, {
        name: "Barcelona",
        population: 1500000
    }]);

    var docs = await collection1.aggregate([
        {$lookup: {
            from: 'collection2',
            localField: 'city',
            foreignField: 'name',
            as: 'city_name'
        }},
        {$unwind: "$city_name"},
        {$group: {
            _id: null,
            avgPopulation: {
                $avg: "$city_name.population"
            }}},
        {$project: {_id: 0, avgPopulation: 1}}
    ]).toArray();

    console.log(docs);

    db.close();

});