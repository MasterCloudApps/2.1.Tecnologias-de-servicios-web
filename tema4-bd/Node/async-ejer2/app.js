import axios from 'axios';

try {
    var response = await axios.get('https://jsonplaceholder.typicode.com/posts/1');
    console.log('Request successful. HTTP status: ' + response.status + ' ' + response.statusText);
    console.log(response.data);
} catch (error) {
    console.error(error.message);
} finally {
    console.log('Request ended');
}
