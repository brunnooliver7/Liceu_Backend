create table aluno (
  id bigint not null auto_increment,
  nome varchar(100) not null,
  matricula bigint not null,
  classificacao bigint,
  mensalidade decimal(10,2),

  primary key (id)
) engine=InnoDB default charset=utf8;