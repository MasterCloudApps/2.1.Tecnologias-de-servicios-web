import lodash from 'lodash';

export default function quitaCeros(arrayCeros) {
    for (var i = 0; i < arrayCeros.length; i++) {
        arrayCeros[i] = lodash.without(arrayCeros[i], 0);
    }
}