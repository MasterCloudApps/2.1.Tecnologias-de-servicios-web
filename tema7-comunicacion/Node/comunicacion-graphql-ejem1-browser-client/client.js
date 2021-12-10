const query = "{ hello }";

const response = await fetch('http://localhost:3000/graphql', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
    },
    body: JSON.stringify({ query })
});

let result = await response.json();

const outputElem = document.getElementById('output');

console.log(result);

outputElem.appendChild(document.createTextNode(JSON.stringify(result.data.hello)));