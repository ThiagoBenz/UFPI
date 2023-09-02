class Arma {
    constructor(area, dano) {
        this.area = area;
        this.dano = dano;
    }
}

// Ler as entradas de armas de um arquivo
const fs = require('fs');
const armas = JSON.parse(fs.readFileSync('armas.json'));
// Capacidade do foguete
const capacidadeFoguete = 30;

function mochilaForcaBruta(armas, capacidade) {
    const quantidadeArmas = armas.length;
    const quantidadeCombinacoes = 2 ** quantidadeArmas; // número total de combinações possíveis
    let melhorDano = 0;
    let melhorComb = [];

    // Percorre todas as combinações possíveis de armas
    for (let i = 0; i < quantidadeCombinacoes; i++) {
        const combAtual = [];
        let areaAtual = 0;
        let danoAtual = 0;

        // Percorre todas as armas disponíveis
        for (let j = 0; j < quantidadeArmas; j++) {
            if ((i & (1 << j)) !== 0) {
                combAtual.push(armas[j]);
                areaAtual += armas[j].area;
                danoAtual += armas[j].dano;
            }
        }

        if (areaAtual <= capacidade && danoAtual > melhorDano) {
            melhorDano = danoAtual;
            melhorComb = combAtual;
        }
    }

    return { combinacao: melhorComb, danoTotal: melhorDano };
}



const { combinacao, danoTotal } = mochilaForcaBruta(armas, capacidadeFoguete);


/*
// Impressões dos resultados para fins analíticos
console.log("Armas armazenadas no foguete:");
combinacao.forEach((arma, indice) => {
    console.log(`Arma ${indice + 1}: area: ${arma.area}m², Dano: ${arma.dano} Joules`);
});
console.log("Somatório de todas as áreas das armas disponíveis = ", areaTotal, "m²")
console.log(`Dano total: ${danoTotal} Joules`);
*/
// Imprime o resultado
console.log(`Armas escolhidas: ${combinacao.map(arma => `(${arma.area}m², ${arma.dano}J)`).join(', ')}`);

//Imprime a quantidade de armas escolhidas
console.log(`Quantidade de armas escolhidas: ${combinacao.length}`);

// Imprime o dano total
console.log(`Dano total: ${danoTotal}J`);

// Imprime a área total
console.log(`Área total: ${combinacao.reduce((acc, arma) => acc + arma.area, 0)}m²`);

// Imprime a porcentagem de área utilizada
console.log(`Porcentagem de área utilizada: ${combinacao.reduce((acc, arma) => acc + arma.area, 0) / capacidadeFoguete * 100}%`);
