DROP DATABASE IF EXISTS `tiendaweb`;

CREATE DATABASE tiendaweb DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,CREATE TEMPORARY TABLES,DROP,INDEX,ALTER ON tiendaweb.* TO tiendaweb@localhost IDENTIFIED BY 'tiendaweb';