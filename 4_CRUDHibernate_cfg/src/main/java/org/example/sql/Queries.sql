DROP DATABASE IF EXISTS tutorial;
CREATE DATABASE  tutorial;
USE tutorial;


CREATE TABLE  usuario (
  id integer NOT NULL AUTO_INCREMENT,
  firstname varchar(50) DEFAULT NULL,
  lastname varchar(100) DEFAULT NULL,
  dob   date DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE  profesor (
  id integer NOT NULL ,
  nombre varchar(50) DEFAULT NULL,
  ape1 varchar(100) DEFAULT NULL,
  ape2 varchar(100) DEFAULT NULL
);