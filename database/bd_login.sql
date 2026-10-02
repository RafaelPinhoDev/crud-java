#Criação do Banco de Dados
create database bd_login;

# usando o banco
use bd_login;


#Criando tabela de login 
create table usuario(
	usuario varchar(10) primary key,
    nome varchar(30) default '',
    senha char(32) default ''

);