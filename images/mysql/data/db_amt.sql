DROP SCHEMA IF EXISTS db_amt;
CREATE SCHEMA db_amt;
SET foreign_key_checks = 0;
USE db_amt;

CREATE TABLE usr(
	nom varchar(30) PRIMARY KEY,
    mdp varchar(64)
);

CREATE TABLE auth_key(
	id int auto_increment PRIMARY KEY,
    auth_key varchar(15),
    init_date DATE,
    end_date date,
    usr_nom varchar(30),
	FOREIGN KEY (usr_nom) REFERENCES usr(nom)
);