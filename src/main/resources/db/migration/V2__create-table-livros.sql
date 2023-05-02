CREATE TABLE livros (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  autor VARCHAR(255) NOT NULL,
  editora VARCHAR(255),
  isbn VARCHAR(13) NOT NULL,
  ano_publicacao INT,
  edicao INT,
  num_paginas INT,
  sinopse TEXT,
  capa VARCHAR(255),
  usuario_id bigint NOT NULL,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);