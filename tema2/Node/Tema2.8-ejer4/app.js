var express = require('express');
var app = express();
var uuid = require('uuid/v4');

app.use(express.json()); // support json encoded bodies
app.use(express.urlencoded({
    extended: true
})); // support url encoded bodies

const ads = {};

app.route('/ad')
    .post(function (req, res) {
        var ad = req.body;
        if (typeof ad.message != 'string' || typeof ad.author != 'string') {
            res.send(400);
        } else {
            var newId = uuid();
            var newAd = {
                id: newId,
                message: ad.message,
                author: ad.author
            };
            ads[newId] = newAd;
            res.json(newAd);
        }
    }).get(function (req, res) {
        var arrayResponse = [];
        Object.values(ads).forEach(ad => {
            arrayResponse.push(ad)
        });
        res.json(arrayResponse);
    }).delete(function (req, res) {
        var id = req.params.id;
        if (!ads[id]) {
            res.send(404);
        } else {
            res.json(ads[id]);
        }
    });

app.route('/ad/:id')
    .get(function (req, res) {
        var id = req.params.id;
        if (!ads[id]) {
            res.send(404);
        } else {
            res.json(ads[id]);
        }
    }).delete(function (req, res) {
        var id = req.params.id;
        if (!ads[id]) {
            res.send(404);
        } else {
            delete ads[id];
            res.send(204);
        };
    });

app.listen(3000);