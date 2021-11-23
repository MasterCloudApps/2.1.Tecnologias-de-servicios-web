import express from 'express';

const router = express.Router();

router.get('/greeting', (req, res) => {

    res.render('greeting', {
        name: req.query.userName
    });
});

export default router;