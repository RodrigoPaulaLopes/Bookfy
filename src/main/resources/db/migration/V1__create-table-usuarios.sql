create table usuarios (
    id bigint not null primary key auto_increment,
    email varchar(100) not null unique,
    senha varchar(200) not null
)
