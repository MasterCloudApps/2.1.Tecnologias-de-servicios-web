import { setTimeout } from 'timers/promises';

async function request(){
    await setTimeout(Math.random() * 1000 + 1000);
}

const value = { result: 'initial' };

async function requestA() {
    
    await request();
    
    //Process response
    value.result = value.result + '-ValueA';
}
async function requestB() {

    await request();

    //Process response
    value.result = value.result + '-ValueB';
}

//Parallel requests
await Promise.all([requestA(), requestB()]);

//Final process
console.log('Result: '+value.result);
