create table aula (
  id bigint not null auto_increment,
  materia_id bigint not null,
  dia date,

  primary key (id)
) engine=InnoDB default charset=utf8;

alter table aula add constraint fk_aula_materia
foreign key (materia_id) references materia (id);

create table aula_aluno (
  aula_id bigint not null,
  aluno_id bigint not null,

  primary key (aula_id, aluno_id)
) engine=InnoDB default charset=utf8;

alter table aula_aluno add constraint fk_aula_aluno_aula
foreign key (aula_id) references aula (id);

alter table aula_aluno add constraint fk_aula_aluno_aluno
foreign key (aluno_id) references aluno (id);