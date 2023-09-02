class Arma {
    constructor(area, dano) {
        this.area = area;
        this.dano = dano;
        this.razao = razao;
    }
}


// Ler as entradas de armas de um arquivo
const fs = require('fs');
const armas = JSON.parse(fs.readFileSync('armas.json'));
// Capacidade do foguete
const capacidadeFoguete = 30;




// Função para executar o algoritmo guloso
function mochilaGulosa(armas, capacidade) {
    const combinacao = [];
    let danoTotal = 0;
    let areaTotal = 0;

    // Percorre todas as armas disponíveis
    for (let i = 0; i < armas.length; i++) {
        const arma = armas[i];
        arma.razao = arma.dano / arma.area;
    }


    // Ordena as armas por razão em ordem decrescente
    armas.sort((a, b) => b.razao - a.razao);


    // Adicione os itens um a um na mochila, seguindo a ordem, até que não caiba mais nenhum ou todos os itens foram adicionados
    for (let i = 0; i < armas.length; i++) {
        const arma = armas[i];

        // Se a área da arma atual não ultrapassar a capacidade do foguete
        if (areaTotal + arma.area <= capacidade) {
            // Adiciona a arma na combinação
            combinacao.push(arma);
            danoTotal += arma.dano;
            areaTotal += arma.area;
        }
    }

    return { combinacao, danoTotal, areaTotal };
}



// Executa o algoritmo guloso
const { combinacao, danoTotal, areaTotal } = mochilaGulosa(armas, capacidadeFoguete);

// Imprime o resultado
console.log(`Armas escolhidas: ${combinacao.map(arma => `(${arma.area}m², ${arma.dano}J)`).join(', ')}`);

//Imprime a quantidade de armas escolhidas
console.log(`Quantidade de armas escolhidas: ${combinacao.length}`);

// Imprime o dano total
console.log(`Dano total: ${danoTotal}J`);

// Imprime a área total
console.log(`Área total: ${areaTotal}m²`);

// Imprime a porcentagem de área utilizada
console.log(`Porcentagem de área utilizada: ${areaTotal / capacidadeFoguete * 100}%`);