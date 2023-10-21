CREATE DATABASE project_UAS;
USE project_UAS;

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
`id` INTEGER AUTO_INCREMENT PRIMARY KEY,
`nama_ruangan` VARCHAR(10),
`time` DATE
);


CREATE TABLE request_room (
`id` INTEGER AUTO_INCREMENT PRIMARY KEY,
`id_ruangan` INTEGER,
`id_mahasiswa` INTEGER,
`time` DATE,
`status` VARCHAR(10),
 reason TEXT;
FOREIGN KEY (`id_ruangan`) REFERENCES ruangan (`id`),
FOREIGN KEY (`id_mahasiswa`) REFERENCES mahasiswa (`id`)
);

ALTER TABLE request_room
ADD reason TEXT;

DELETE FROM request_room WHERE id = 20
INSERT INTO mahasiswa (nama, username, kelas, PASSWORD) VALUES ('vin', 'vin', '14IF1', '123456')
INSERT INTO ADMIN(username, PASSWORD) VALUES('admin', 'admin');
INSERT INTO ruangan(nama_ruangan, TIME) VALUES('914', NOW());
INSERT INTO ruangan(nama_ruangan, TIME) VALUES('915', NOW());

INSERT INTO request_room(id_ruangan, id_mahasiswa, TIME, STATUS, reason) VALUES(1, 4, '2022-12-6', 'request', 'make doang 2')
SELECT * FROM MAHASISWA;
SELECT * FROM ruangan;
SELECT * FROM request_room;
SELECT r.id, r.id_ruangan, ru.nama_ruangan, r.time, m.nama, m.kelas, r.reason FROM request_room r LEFT JOIN mahasiswa m ON r.id_mahasiswa = m.id JOIN ruangan ru ON r.id_ruangan = ru.id WHERE r.status = 'request' ORDER BY TIME ASC
SELECT id, nama, kelas FROM mahasiswa WHERE username = 'add' AND PASSWORD = '123456' LIMIT 1;
SELECT id FROM request_room WHERE id_ruangan = '1' AND TIME = '2022-12-05' AND STATUS = 'accept' LIMIT 1
SELECT ruangan.nama_ruangan, b.status FROM ruangan LEFT JOIN (SELECT request_room.id, id_ruangan, STATUS, mahasiswa.kelas FROM request_room JOIN mahasiswa ON id_mahasiswa = mahasiswa.id WHERE request_room.time = '2022-12-06' ORDER BY STATUS LIMIT 1) b ON ruangan.id = b.id_ruangan
SELECT ruangan.nama_ruangan, b.status FROM ruangan LEFT JOIN (SELECT id, id_ruangan, STATUS FROM request_room WHERE request_room.time = '2022-12-05') b ON ruangan.id = b.id_ruangan
UPDATE request_room SET STATUS = (CASE WHEN id = '14' THEN 'accept' ELSE 'reject' END) WHERE id_ruangan = '1' AND TIME = '2022-12-08'