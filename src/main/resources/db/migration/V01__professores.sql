CREATE TABLE professor (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    materia VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO professor (nome, materia) values ('Alberto', 'Física');
INSERT INTO professor (nome, materia) values ('Bruno', 'Matemática');
INSERT INTO professor (nome, materia) values ('Caio', 'Literatura');
INSERT INTO professor (nome, materia) values ('Daniel', 'Biologia');
INSERT INTO professor (nome, materia) values ('Emerson', 'Artes');