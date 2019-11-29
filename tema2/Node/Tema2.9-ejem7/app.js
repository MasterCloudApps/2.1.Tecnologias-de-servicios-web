async function async1() {
    await sleep(1000);
    console.log('Async 1');
}
async function async2() {
    await sleep(2000);
    console.log('Async 2');
}
async function async3() {
    await sleep(3000);
    console.log('Async 3');
}

Promise.all([async1(), async2(), async3()])
    .then(values => {
        console.log("All promises resolved");
    }).catch(error => console.error(error));


function sleep(millis) {
    return new Promise(resolve => {
        setTimeout(() => resolve(), millis);
    });
}