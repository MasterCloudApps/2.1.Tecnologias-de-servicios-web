//NOT WORKING AS 'lodash' doesn't export 'without' as named export

import { without } from 'lodash';

var output = without([1, 2, 3], 1);
console.log(output);
