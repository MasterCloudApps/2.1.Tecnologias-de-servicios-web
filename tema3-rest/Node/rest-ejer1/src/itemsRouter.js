import express from 'express';
import url from 'url';

const router = express.Router();

const items = new Map();
let nextId = 1;

addItem({ description: 'Leche', checked: false });
addItem({ description: 'Pan', checked: true });

function addItem(item) {

    let id = nextId++;

    item.id = id.toString();

    items.set(item.id, item);
}

function validItem(item) {
    return typeof item.description == 'string'
        && typeof item.checked == 'boolean';
}

function fullUrl(req) {
  const fullUrl = url.format({
    protocol: req.protocol,
    host: req.get('host'),
    pathname: req.originalUrl
  });
  
  return fullUrl + (fullUrl.endsWith('/')?'':'/');
}

router.post('/items', (req, res) => {

    if (!validItem(req.body)) {
        res.sendStatus(400);
    } else {

        let item = {
            description: req.body.description,
            checked: req.body.checked
        };

        addItem(item);
        res.location(fullUrl(req)+item.id);
        res.json(item);
    }
});

router.get('/items', (req, res) => {
    res.json([...items.values()]);
});

router.get('/items/:id', (req, res) => {
    const id = req.params.id;
    const item = items.get(id);
    if (!item) {
        res.sendStatus(404);
    } else {
        res.json(item);
    }
});

router.delete('/items/:id', (req, res) => {
    const id = req.params.id;
    const item = items.get(id);
    if (!item) {
        res.sendStatus(404);
    } else {
        items.delete(id);
        res.json(item);
    }
});

router.put('/items/:id', (req, res) => {
    const id = req.params.id;
    const item = items.get(id);
    if (!item) {
        res.sendStatus(404);
    } else {
        if (!validItem(req.body)) {
            res.sendStatus(400);
        } else {

            let newItem = {
                id,
                description: req.body.description,
                checked: req.body.checked
            };
            
            items.set(id, newItem);

            res.json(newItem);
        }
    }
});

export default router;


