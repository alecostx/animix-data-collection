/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Alexandre.Rodrigues
 * Created: Nov 17, 2022
 */
create database animix;
use animix;

CREATE TABLE studio(
	idStudio int primary key auto_increment,
	nomeEmpresa varchar(45),
	email varchar(45),
	senha varchar(45),
	logradouro varchar(45),
	telefone varchar(45),
	CNPJ varchar(45)
);

CREATE TABLE maquinas(
	idMaquina int primary key auto_increment,
	fkStudio int,
	disco varchar(45),
	discoIdeal decimal(5, 2) NULL,
	memoria varchar(45),
	memoriaIdeal decimal(5, 2) NULL,
	processador varchar(45),
	processamentoIdeal decimal(5, 2) NULL,
	sistema varchar(45),
	monitoraDisco bit,
	monitoraMemoria bit,
	monitoraProcessador bit,
	monitoraTemperatura bit,
	temperaturaIdeal decimal(5, 2) NULL,
	quantidadeDiscos bit,
	situacao bit,
	arquitetura varchar(45),
	fabricante varchar(45),
	permissoes varchar(45),
	inicializado varchar(45)
);

CREATE TABLE dados(
	idDado int primary key auto_increment,
	fkMaquina int,
	usoCpu decimal(5, 2) NULL,
	usoMemoria decimal(5, 2) NULL,
	temperatura decimal(5, 2) NULL,
	qtdProcessos int,
	qtdServicos int,
	dataColeta varchar(45),
	momento time,
	isCritico bit NULL,
	comment varchar(200) NULL,
	leitura decimal(5, 2) NULL,
	escrita decimal(5, 2) NULL,
	usoDisco decimal(5, 2) NULL
);

CREATE TABLE funcionario(
	idFuncionario int primary key auto_increment,
	cargo varchar(45),
	nome varchar(45),
	email varchar(45),
	senha varchar(45),
	fkStudio int
);










