import axios from 'axios';

const response = await axios.get('http://localhost:3000/posts/');

console.log(response.data);

