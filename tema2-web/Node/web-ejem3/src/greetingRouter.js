import express from 'express';

const router = express.Router();

router.get('/', (req, res) => {

    res.render('index', {
        name: "World"
    });
});

export default router;