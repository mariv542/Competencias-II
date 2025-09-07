DROP DATABASE IF EXISTS ControlAsistencia;
CREATE DATABASE ControlAsistencia;

USE ControlAsistencia;

-- Tabla de Usuarios
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(9) UNIQUE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    rol ENUM('Administrador', 'Empleado') NOT NULL,
    estado ENUM('Activo','Inactivo') DEFAULT 'Activo'
);

-- Tabla de Asistencias
CREATE TABLE Asistencias (
    id_asistencia INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_hora_entrada DATETIME NULL,
    fecha_hora_salida DATETIME NULL,
    estado ENUM('Presente','Atraso','Salida anticipada','Inasistente') 
           NOT NULL DEFAULT 'Inasistente',
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

--Indice  para optimizar consultas por Fecha
CREATE INDEX idx_fecha_entrada ON Asistencias(fecha_hora_entrada);

SELECT * FROM Usuarios;

SELECT * FROM Asistencias; 


INSERT INTO Usuarios (rut, nombre, apellido, correo, contraseña, rol, estado)
VALUES
('218642519', 'Cristobal', 'Martinez', 'martinezcristobal2005@gmail.com', '1', 'Administrador', 'Activo'),
('123456789', 'Juan', 'Pérez', 'juan.perez@email.com', 'pass123', 'Administrador', 'Activo'), 
('987654321', 'Ana', 'Gómez', 'ana.gomez@email.com', 'pass456', 'Empleado', 'Activo'), 
('111222334', 'Pedro', 'López', 'pedro.lopez@email.com', 'pass789', 'Empleado', 'Activo'), 
('555666778', 'Carla', 'Martínez', 'carla.martinez@email.com', 'pass321', 'Empleado', 'Inactivo'); 

-- Pre-carga de datos en Asistencias
INSERT INTO Asistencias (id_usuario, fecha_hora_entrada, fecha_hora_salida, estado)
VALUES
(1, '2025-09-01 08:00:00', '2025-09-01 17:00:00', 'Presente'),
(2, '2025-09-01 08:15:00', '2025-09-01 17:00:00', 'Atraso'),
(3, '2025-09-01 08:05:00', '2025-09-01 16:50:00', 'Salida anticipada'),
(4, NULL, NULL, 'Inasistente'),
(1, '2025-09-02 08:00:00', '2025-09-02 17:00:00', 'Presente'),
(2, '2025-09-02 08:10:00', '2025-09-02 17:00:00', 'Atraso'),
(1, '2025-09-03 08:05:00', '2025-09-03 17:00:00', 'Presente');


SELECT 
    u.nombre,
    a.fecha_hora_entrada,
    a.fecha_hora_salida,
    a.estado
FROM Asistencias a
INNER JOIN Usuarios u ON a.id_usuario = u.id_usuario;

