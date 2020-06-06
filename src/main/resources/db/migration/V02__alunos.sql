CREATE TABLE aluno (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    matricula INT(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO aluno (nome, matricula) values ('A', 1);
INSERT INTO aluno (nome, matricula) values ('B', 2);
INSERT INTO aluno (nome, matricula) values ('C', 3);
INSERT INTO aluno (nome, matricula) values ('D', 4);
INSERT INTO aluno (nome, matricula) values ('E', 5);