var mongoose = require('mongoose');
const express = require('express');

const url = "mongodb://localhost:27017/adsDB";

const app = express();

app.use(express.json());

let Ad;

function toResponse(doc) {
    
    if(doc instanceof Array){
        return doc.map(elem => toResponse(elem));
    } else {
        let ret = doc.toObject({ versionKey: false });
        ret.id = ret._id.toString();
        delete ret._id;
        return ret;
    }    
}

app.post('/ads', async (req, res) => {
    const ad = req.body;
    if (typeof ad.message != 'string' || typeof ad.author != 'string') {
        res.sendStatus(400);
    } else {
        
        const newAd = new Ad({
            message: ad.message,
            author: ad.author
        });
        
        await newAd.save();

        res.json(toResponse(newAd));
    }
});

app.get('/ads', async (req, res) => {
    let allAds = await Ad.find().exec();
    res.json(toResponse(allAds));
});

app.get('/ads/:id', async (req, res) => {
    const id = req.params.id;
    const ad = await Ad.findById(id);
    if (!ad) {
        res.sendStatus(404);
    } else {
        res.json(toResponse(ad));
    }
});

app.delete('/ads/:id', async (req, res) => {
    const id = req.params.id;
    const ad = await Ad.findById(id);
    if (!ad) {
        res.sendStatus(404);
    } else {
        await Ad.findByIdAndDelete(id);
        res.json(toResponse(ad));
    }
});

app.put('/ads/:id', async (req, res) => {
    const id = req.params.id;
    const ad = await Ad.findById(id);
    if (!ad) {
        res.sendStatus(404);
    } else {
        const adReq = req.body;
        //Validation
        if (typeof adReq.message != 'string' || typeof adReq.author != 'string') {
            res.sendStatus(400);
        } else {
            
            //Update fields in model
            ad.message = adReq.message;
            ad.author = adReq.author;
            
            //Save
            await ad.save();
            
            //Return updated resource
            res.json(toResponse(ad));
        }
    }
});

async function dbConnect() {

    await mongoose.connect(url, {
        useUnifiedTopology: true,
        useNewUrlParser: true,
        useFindAndModify: false
    });

    console.log("Connected to Mongo");

    var adSchema = new mongoose.Schema({
        message: String,
        author: String
    });

    Ad = mongoose.model('Ad', adSchema);
}

async function main() {

    await dbConnect();

    app.listen(3000, () => console.log('Server started in port 3000'));
}

main();