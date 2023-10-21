CREATE DATABASE k13;
USE k13;

CREATE TABLE mahasiswa (
`id` INTEGER AUTO_INCREMENT,
`nama` VARCHAR(100),
`username` VARCHAR(20),
`kelas` VARCHAR(5),
`password` VARCHAR(20),
PRIMARY KEY (id)
);

CREATE TABLE `admin` (
`id` INTEGER AUTO_INCREMENT PRIMARY KEY,
`username` VARCHAR(20),
`password` VARCHAR(20)
);
 

CREATE TABLE ruangan (
`id` INTEGER AUTO_INCREM`request_room``ruangan``mahasiswa``request_room`ENT PRIMARY KEY,
`nama_ruangan` VARCHAR(10),
`time` DATE
);


CREATE TABLE request_room (
`id` INTEGER AUTO_INCREMENT PRIMARY KEY,
`id_ruangan` INTEGER,
`id_mahasiswa` INTEGER,
`time` DATE,
`status` VARCHAR(10),
`reason` TEXT,
FOREIGN KEY (`id_ruangan`) REFERENCES ruangan (`id`),
FOREIGN KEY (`id_mahasiswa`) REFERENCES mahasiswa (`id`)
);


INSERT INTO `admin` (`id`, `username`, `password`)
VALUES (1, "admin", "admin")

INSERT INTO ruangan (`nama_ruangan`)
VALUES ("AUDIT"), 
("513"), ("514"), ("515"), ("516"), ("525"), ("526"),
("711"), ("712"), ("714"), ("721"), ("722"), ("723"), ("724"), ("725"),
("914"), ("915"), ("917"), ("927"), ("928"), ("932"), ("937"), ("938"), ("939"), ("943");


DROP TABLE ruangan
