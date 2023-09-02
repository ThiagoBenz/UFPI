class Arma {
    constructor(area, dano) {
        this.area = area;
        this.dano = dano;
    }
}

// Função para gerar um número aleatório dentro de um intervalo
function gerarNumeroAleatorio(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

// Gerar as entradas de armas
const armas = [];
const quantidadeArmas = 35;


for (let i = 0; i < quantidadeArmas; i++) {
    const area = gerarNumeroAleatorio(0.5, 10); // area aleatória entre 0.5 e 10 metros
    const dano = gerarNumeroAleatorio(100, 1000); // Dano aleatório entre 100 Joules e 1000 Joules

    const arma = new Arma(area, dano);
    armas.push(arma);

    //areaTotal = areaTotal + arma.area
}


// Salvar as entradas de armas em um arquivo
const fs = require('fs');
fs.writeFileSync('armas.json', JSON.stringify(armas));