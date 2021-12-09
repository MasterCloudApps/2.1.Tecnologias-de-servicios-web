function promiseFunction(message) {
    return new Promise(resolve => {
        sleep(1000).then(() => {
            console.log("I've waited 1 second! Message: " + message);
            resolve();
        });
    });
}

async function asyncFunction(message) { // You can only use "await" statement if the function is "async"
    await sleep(1000);
    console.log("I've waited 1 second! Message: " + message);
    // To resolve the internal promise, you can return nothing or return any value:
    return true;
    // To reject the internal promise, you can throw an error:
    throw TypeError("name must be a string");
}

function sleep(millis) {
    return new Promise(resolve => {
        setTimeout(() => resolve(), millis);
    });
}

await promiseFunction('This is promise 1!');
await promiseFunction('This is promise 2!');
await promiseFunction('This is promise 3!');
await asyncFunction('This is async 1!');
await asyncFunction('This is async 2!');
await asyncFunction('This is async 3!');