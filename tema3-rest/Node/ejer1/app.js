const express = require('express');

//Generate unique id for resources
const uuid = require('uuid/v4');

const app = express();

//Convert json bodies to JavaScript object
app.use(express.json());

//Save info in memory 
const items = new Map();

app.post('/items', (req, res) => {
    const item = req.body;
    //Validation
    if (typeof item.description != 'string' || typeof item.checked != 'boolean') {
        res.sendStatus(400);
    } else {
        //Create object with needed fields and assign id
        const newItem = {
            id: uuid(),
            description: item.description,
            checked: item.checked
        };
        //Save resource
        items.set(newItem.id, newItem);
        //Return new resource
        res.json(newItem);
    }
});

app.get('/items', (req, res) => {
    const allItems = [...items.values()];
    res.json(allItems);
});

app.get('/items/:id', (req, res) => {
    const id = req.params.id;
    const item = items.get(id);
    if (!item) {
        res.sendStatus(404);
    } else {
        res.json(item);
    }
});

app.delete('/items/:id', (req, res) => {
    const id = req.params.id;
    const item = items.get(id);
    if (!item) {
        res.sendStatus(404);
    } else {
        items.delete(id);
        res.json(item);
    }
});

app.put('/items/:id', (req, res) => {
    const id = req.params.id;
    const item = items.get(id);
    if (!item) {
        res.sendStatus(404);
    } else {
        const itemReq = req.body;
        //Validation
        if (typeof itemReq.description != 'string' || typeof itemReq.checked != 'boolean') {
            res.sendStatus(400);
        } else {
            //Create object with needed fields and assign id
            const newItem = {
                id,
                description: itemReq.description,
                checked: itemReq.checked
            };
            //Update resource
            items.set(id, newItem);
            //Return new resource
            res.json(newItem);
        }
    }
});

app.listen(3000, () => { console.log('Server started in port 3000') });