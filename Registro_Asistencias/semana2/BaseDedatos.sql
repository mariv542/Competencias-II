DROP DATABASE IF EXISTS ControlAsistencia;
CREATE DATABASE ControlAsistencia;

USE ControlAsistencia;

-- Tabla de Usuarios
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) UNIQUE NOT NULL,
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