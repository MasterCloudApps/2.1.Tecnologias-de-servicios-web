var axios = require('axios');

(async () => {
    try {
        var response = await axios.get('https://jsonplaceholder.typicode.com/posts/1'); // ERR: https://jsonplaceholder.typicode.coms/posts/1
        console.log('Request successful. HTTP status: ' + response.status + ' ' + response.statusText);
        console.log(response.data);
    } catch (error) {
        console.error(error.message);
    }
})();