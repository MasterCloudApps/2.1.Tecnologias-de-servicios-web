async function async1() {
    await sleep(1000);
    return 'Async 1';
}
async function async2() {
    await sleep(2000);
    return 'Async 2';
}
async function async3() {
    await sleep(3000);
    return 'Async 3';
}

console.log(await async1());
console.log(await async2());
console.log(await async3());

const [v1, v2, v3] = await Promise.all([async1(), async2(), async3()])

console.log(v1);
console.log(v2);
console.log(v3);

function sleep(millis) {
    return new Promise(resolve => {
        setTimeout(() => resolve(), millis);
    });
}