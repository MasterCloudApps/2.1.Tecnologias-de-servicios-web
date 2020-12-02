export function ordena(arraySinCeros) {
    var cambio;
    do {
        cambio = false;
        for (var i = 0; i < arraySinCeros.length - 1; i++) {
            if (arraySinCeros[i].length > arraySinCeros[i + 1].length) {
                var aux = arraySinCeros[i];
                arraySinCeros[i] = arraySinCeros[i + 1];
                arraySinCeros[i + 1] = aux;
                cambio = true;
            }
        }
    } while (cambio);
}