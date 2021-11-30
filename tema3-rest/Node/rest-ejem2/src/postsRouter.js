import express from 'express';

const router = express.Router();

router.get('/posts/1', (req, res) => {
    res.json({
        id: 1,
        user: 'Pepe',
        title: 'Vendo moto',
        text: 'Barata, barata'
    });
});

export default router;