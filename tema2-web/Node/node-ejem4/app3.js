import lodash from 'lodash';

const { without } = lodash;

var output = without([1, 2, 3], 1);
console.log(output);
