create table materia (
  id bigint not null auto_increment,
  professor_id bigint not null,
  nome varchar(100) not null,
  
  primary key (id)
) engine = InnoDB default charset = utf8;

alter table materia add constraint materia_professor 
foreign key (professor_id) references professor (id);