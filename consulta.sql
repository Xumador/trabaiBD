CREATE DATABASE IF NOT EXISTS trabalho;
USE trabalho;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(50) NOT NULL UNIQUE,
  `nome` varchar(50) DEFAULT NULL,
  `datanasc` date DEFAULT NULL,
  `endereco` varchar(50) DEFAULT NULL,
  `sexo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `telefone`;
CREATE TABLE `telefone` (
  `numero` varchar(50) NOT NULL,
  `ddd` varchar(2) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero`),
  CONSTRAINT `FK_telefone_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `idProduto` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `preco` float(11) DEFAULT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProduto`),
  CONSTRAINT `FK_produto_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `compra`;
CREATE TABLE `compra` (
  `idUser` int(11) NOT NULL,
  `dataCompra` date DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `idProduto` int(11) NOT NULL,
  PRIMARY KEY (`idUser`, `idProduto`),
  KEY `FK_compra_user` (`idUser`),
  CONSTRAINT `FK_compra_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_compra_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `produtos_venda`;
CREATE TABLE `produtos_venda` (
  `idUser` int(11) NOT NULL,
  `idProduto` int(11) NOT NULL,
  PRIMARY KEY (`idUser`, `idProduto`),
  CONSTRAINT `FK_produtos_venda_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_produtos_venda_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `avaliacao`;
CREATE TABLE `avaliacao` (
  `idUser` int(11) NOT NULL,
  `idProduto` int(11) NOT NULL,
  `notaAvaliacao` int(1) NOT NULL CHECK (`notaAvaliacao` BETWEEN 1 AND 5),
  `comentario` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idUser`, `idProduto`),
  CONSTRAINT `FK_avaliacao_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_avaliacao_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `ofertas`;
CREATE TABLE `ofertas` (
  `idProduto` int(11) NOT NULL,
  `desconto` decimal(2,2) NOT NULL CHECK (`desconto` BETWEEN 0 AND 1),
  PRIMARY KEY (`idProduto`),
  CONSTRAINT `FK_oferta_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO user (cpf, nome, datanasc, endereco, sexo)
VALUES 
("123.456.789-10", "Victor Papa", "1998-01-01", "Rua 1", "Masculino"), 
("109.876.543-21", "Maria Souza", "1997-02-01", "Rua 2", "Feminino"), 
("234.567.891-01", "Lucas Oliveira", "1996-03-01", "Rua 3", "Masculino"), 
("987.654.321-03", "Ana Paula", "1995-04-01", "Rua 4", "Feminino"), 
("111.111.111-11", "Jose Reis", "2000-03-01", "Rua 3", "Masculino"), 
("222.222.222-22", "Luiz Felipe", "2003-06-11", "Rua 3", "Masculino"), 
("333.333.333-33", "Lucas Costa", "1996-03-01", "Rua 3", "Masculino"), 
("444.444.444-44", "Rodrigo Paraiba", "1970-03-01", "Rua 3", "Masculino"), 
("456.789.101-23", "Felipe Souza", "1994-05-01", "Rua 5", "Masculino");

INSERT INTO telefone (numero, ddd, idUser)
VALUES
("9876-5432", "35", 1),
("1234-5678", "35", 2),
("2345-6789", "35", 3),
("9876-5123", "60", 4),
("8765-4321", "11", 5);

INSERT INTO categoria (nome)
VALUES
("Eletrônicos"),
("Livros"),
("Alimentos"),
("Vestuário"),
("Brinquedos");

INSERT INTO produto (nome, preco, idCategoria)
VALUES
("Notebook", 3000.00, 1),
("Livro de Matemática", 50.00, 2),
("Arroz", 10.00, 3),
("Camiseta", 40.00, 4),
("Boneca", 20.00, 5),
('Livro de Geografia', 35, 2),
("Vestido", 140.00, 4);

INSERT INTO ofertas (idProduto, desconto)
VALUES
(1, 0.1),
(2, 0.2),
(3, 0.05),
(4, 0.15),
(5, 0.25),
(6, 0.3),
(7, 0.2);

INSERT INTO compra (idUser, dataCompra, quantidade, idProduto)
VALUES
(1, "2023-01-01", 2, 1),
(1, "2023-02-01", 1, 2),
(3, "2023-03-01", 3, 1),
(3, "2023-04-01", 1, 4),
(3, "2023-03-01", 1, 7),
(4, "2023-04-01", 1, 4),
(5, "2023-05-01", 2, 5);

INSERT INTO produtos_venda (idUser, idProduto)
VALUES
(1, 1),
(1, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(6, 7);

INSERT INTO avaliacao (idUser, idProduto, notaAvaliacao, comentario)
VALUES 
(1, 1, 4, 'Ótimo produto'), 
(2, 1, 5, 'Excelente'), 
(3, 1, 3, 'Recomendo'), 
(8, 1, 5, 'Perfeito'), 
(6, 1, 2, 'Muito ruim'),

(5, 2, 5, 'Ótimo produto'), 
(6, 2, 5, 'Excelente'), 
(1, 2, 4, 'Recomendo'), 
(4, 2, 5, 'Perfeito'), 
(8, 2, 1, 'Muito ruim'),

(1, 3, 1, 'Muito ruim'), 
(2, 3, 2, 'Pessimo'), 
(5, 3, 3, 'Não recomendo'), 
(6, 3, 2, 'Muito ruim'), 
(7, 3, 1, 'Não compra'),

(1, 4, 5, 'Ótimo produto'), 
(2, 4, 5, 'Excelente'), 
(3, 4, 4, 'Recomendo'), 
(4, 4, 5, 'Perfeito'), 
(5, 4, 5, 'Satisfeito com a compra'),

(6, 5, 3, 'Mediano'), 
(7, 5, 5, 'Excelente'), 
(8, 5, 4, 'Recomendo'), 
(1, 5, 2, 'Não recomendo'), 
(2, 5, 4, 'Satisfeito com a compra');


UPDATE avaliacao SET comentario = 'Perfeito', notaAvaliacao = 5 WHERE idUser = 1 and idProduto = 1;
UPDATE telefone SET ddd = '35' WHERE idUser = 7;
UPDATE ofertas SET desconto = 0.25 WHERE idProduto IN (SELECT idProduto FROM produto WHERE idCategoria = 2);

DELETE FROM user WHERE endereco = 'rua 4';
DELETE FROM avaliacao WHERE idProduto = 3 and notaAvaliacao < 2;
DELETE FROM compra WHERE dataCompra like '%01-01';

SELECT p.nome, c.nome AS categoria, p.preco
FROM produto p
INNER JOIN categoria c ON p.idCategoria = c.idCategoria
INNER JOIN ofertas o ON p.idProduto = o.idProduto
WHERE o.desconto >= 0.1;

SELECT u.nome, u.endereco, t.numero
FROM user u
INNER JOIN telefone t ON u.idUser = t.idUser
INNER JOIN compra c ON u.idUser = c.idUser
INNER JOIN produtos_venda pv ON c.idProduto = pv.idProduto AND c.idUser = pv.idUser
INNER JOIN ofertas o ON pv.idProduto = o.idProduto
WHERE o.desconto >= 0.1
ORDER BY u.nome;

SELECT p.nome
FROM produto p
JOIN compra c ON p.idProduto = c.idProduto
JOIN user u ON c.idUser = u.idUser
WHERE u.cpf IN ("123.456.789-10", "109.876.543-21");

SELECT u.nome, COUNT(*) AS quantidade_compras
FROM user u
JOIN compra c ON u.idUser = c.idUser
WHERE u.cpf IN ("123.456.789-10", "109.876.543-21")
GROUP BY u.nome;

SELECT u.nome, u.endereco, AVG(a.notaAvaliacao) AS nota_media_avaliacao
FROM user u
JOIN avaliacao a ON u.idUser = a.idUser
GROUP BY u.idUser;

SELECT u.nome, COUNT(c.idProduto) AS qtde_produtos_comprados
FROM user u
JOIN compra c ON u.idUser = c.idUser
GROUP BY u.idUser
ORDER BY qtde_produtos_comprados DESC;

SELECT u.nome, SUM(p.preco * c.quantidade) AS total_gasto_categoria
FROM user u
JOIN compra c ON u.idUser = c.idUser
JOIN produto p ON c.idProduto = p.idProduto
JOIN categoria cat ON p.idCategoria = cat.idCategoria
WHERE cat.nome = 'Eletrônicos'
GROUP BY u.idUser;

SELECT u.nome, c.dataCompra, p.nome AS produto
FROM user u
INNER JOIN compra c ON u.idUser = c.idUser
INNER JOIN produto p ON c.idProduto = p.idProduto;

SELECT p.nome, p.preco 
FROM produto p 
JOIN avaliacao a ON p.idProduto = a.idProduto 
WHERE a.notaAvaliacao = 5;

SELECT c.idUser, COUNT(*) AS qtd_produtos 
FROM compra c 
GROUP BY c.idUser, c.dataCompra 
HAVING COUNT(*) > 1;

SELECT p.nome, a.notaAvaliacao 
FROM produto p 
JOIN avaliacao a ON p.idProduto = a.idProduto 
WHERE a.notaAvaliacao >= 4;

SELECT p.nome, AVG(a.notaAvaliacao) AS nota_media
FROM produto p
LEFT JOIN avaliacao a ON p.idProduto = a.idProduto
GROUP BY p.idProduto
ORDER BY nota_media DESC


SELECT * 
FROM user 
WHERE idUser NOT IN (SELECT idUser FROM produtos_venda);

SELECT u.nome, p.preco * c.quantidade as precoTotal 
FROM compra c, user u, produto p 
WHERE c.idUser = u.idUser AND c.idProduto = p.idProduto;

SELECT idUser, sum(preco) as soma_produtos 
FROM produtos_venda natural inner join produto 
GROUP BY idUser 
HAVING sum(preco) > 300;

(SELECT nome 
FROM user 
WHERE idUser NOT IN (SELECT idUser FROM telefone))
union
(SELECT u.nome 
FROM user u, produtos_venda pv, produto p 
WHERE u.idUser = pv.idUser AND pv.idProduto = p.idProduto AND idCategoria = 2);

SELECT nome 
FROM user 
WHERE nome like 'A%';

SELECT * 
FROM user
WHERE datanasc BETWEEN '1990-03-10' AND '2004-03-10';

SELECT avg(notaAvaliacao) as mediaAvaliacao 
FROM avaliacao 
WHERE idProduto IN (SELECT idProduto FROM produtos_venda WHERE idUser = 1)
GROUP BY idProduto
ORDER BY mediaAvaliacao desc;

SELECT idProduto 
FROM produto p
WHERE NOT EXISTS (SELECT * FROM avaliacao a WHERE a.idProduto = p.idProduto);

SELECT distinct u.nome
FROM user u, compra c, produto p
WHERE u.idUser = c.idUser AND p.idProduto = c.idProduto
	AND c.quantidade * p.preco > ALL (SELECT (c.quantidade * p.preco) FROM compra c, produto p WHERE idUser = 1);
    
(SELECT u.nome
FROM user u, produtos_venda pv, produto p
WHERE u.idUser = pv.idUser AND pv.idProduto = p.idProduto AND p.preco > 100) 
intersect
(SELECT u.nome
FROM user u, telefone t
WHERE u.idUser = t.idUser and t.ddd = '35'); 

SELECT p.idProduto, (p.preco - p.preco*o.desconto) as valorComDesconto
FROM produto p, ofertas o;

 


