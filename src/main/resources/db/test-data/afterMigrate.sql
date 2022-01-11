set foreign_key_checks = 0;

delete from aluno;
delete from professor;
delete from materia;
delete from aula;
delete from aula_aluno;

set foreign_key_checks = 1;

alter table aluno auto_increment = 1;
alter table professor auto_increment = 1;
alter table materia auto_increment = 1;
alter table aula auto_increment = 1;

insert ignore into aluno (id, nome, matricula, classificacao, mensalidade) values 
(1, 'André Silva', 1, 3, 1800.07),
(2, 'Bruno Oliveira', 2, 1, 2000.16),
(3, 'Caio Mendonça', 3, 4, 2100.65),
(4, 'Daniel Ramos', 4, 2, 2400.89),
(5, 'Emanuel Werneck', 5, 5, 1750.23);

insert ignore into professor (id, nome, sala_bloco, sala_andar, sala_numero) values 
(1, 'André Brasil', 'A', 1, 1),
(2, 'Marcos Honorato', 'C', 1, 2),
(3, 'Gilberto Gomes', 'B', 2, 5),
(4, 'Raul Durand', 'C', 2, 8);

insert ignore into materia (id, nome, professor_id) values
(1, 'Geotecnia', 1),
(2, 'Estruturas de Concreto Armado', 2),
(3, 'Análise Matricial de Estruturas', 3),
(4, 'Introdução ao Método dos Elementos Finitos', 4);

insert ignore into aula (id, dia, materia_id, sala_bloco, sala_andar, sala_numero) values 
(1, '2022-01-03', 1, 'A', 1, 1),
(2, '2022-01-05', 1, 'A', 1, 2),
(3, '2022-01-10', 2, 'A', 1, 4),
(4, '2022-01-12', 2, 'A', 1, 4),
(5, '2022-01-17', 3, 'C', 1, 3),
(6, '2022-01-19', 3, 'C', 1, 7),
(7, '2022-01-24', 4, 'A', 1, 2),
(8, '2022-01-26', 4, 'A', 1, 3);

insert ignore into aula_aluno (aluno_id, aula_id) values
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(1, 3),
(3, 3),
(1, 4),
(3, 4),
(2, 5),
(4, 5),
(2, 6),
(4, 6),
(4, 7),
(5, 7),
(4, 8),
(5, 8);
