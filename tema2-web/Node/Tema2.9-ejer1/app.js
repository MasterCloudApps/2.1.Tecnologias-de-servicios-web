var axios = require('axios');

axios.get('https://jsonplaceholder.typicode.com/posts/1') // ERR: https://jsonplaceholder.typicode.coms/posts/1
.then(response => {
    console.log('Request successful. HTTP status: ' + response.status + ' ' + response.statusText);
    console.log(response.data);
})
.catch(error => {
    console.error('Request failed');
    console.error(error.message);
})
.finally(() => console.log('Request ended'));