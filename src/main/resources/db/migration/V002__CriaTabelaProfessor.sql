create table professor (
  id bigint not null auto_increment,
  nome varchar(100) not null,
  
  sala_bloco varchar(50),
  sala_andar int,
  sala_numero int,
  
  data_cadastro datetime,
  data_atualizacao datetime,

  primary key (id)
) engine = InnoDB default charset = utf8;