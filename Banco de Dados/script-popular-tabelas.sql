
-- Thiago Oliveira da Silva
-- Popular tabela de clientes

INSERT INTO Cliente (cpf, nome, telefone, endereco) VALUES
(123456789, 'João da Silva', '(11) 9999-9999', 'Rua A, 123'),
(987654321, 'Maria Oliveira', '(21) 8888-8888', 'Av. B, 456'),
(456789123, 'Pedro Santos', '(31) 7777-7777', 'Rua C, 789'),
(321654987, 'Ana Pereira', '(41) 6666-6666', 'Av. D, 1011');

-- Popular tabelas de bolos fakes

INSERT INTO BoloFake (descricao, preco, disponivel) VALUES
('Bolo de chocolate', 29.90, 1),
('Bolo de cenoura', 25.50, 1),
('Bolo de fubá', 19.90, 0),
('Bolo de leite ninho', 34.90, 1),
('Bolo de laranja', 27.00, 0),
('Bolo de limão', 28.50, 1),
('Bolo de morango', 32.00, 1),
('Bolo de prestígio', 39.90, 0),
('Bolo de coco', 23.50, 1),
('Bolo de nozes', 36.00, 1);


-- Popular tabelas de Alugueis

IINSERT INTO Aluguel (valor, descricao, dataAluguel, cpfCliente, idBolo) VALUES
(15.0, 'Aluguel para festa de aniversário', '2023-03-25', 123456789, 1),
(12.5, 'Aluguel para evento corporativo', '2023-04-01', 987654321, 2),
(20.0, 'Aluguel para chá de bebê', '2023-04-02', 456789123, 3);

-- busca de alugueis

SELECT descricao, valor, dataAluguel
FROM Aluguel;

-- listar os bolos disponiveis

SELECT id, descricao, preco
FROM BoloFake
WHERE disponivel = 1;





