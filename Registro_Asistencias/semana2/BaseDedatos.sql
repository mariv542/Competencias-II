DROP DATABASE IF EXISTS ControlAsistencia;
DROP TABLE IF EXISTS Asistencias;
DROP TABLE IF EXISTS Usuarios;
CREATE DATABASE ControlAsistencia;

USE ControlAsistencia;

-- Tabla de Usuarios
CREATE TABLE Usuarios (
    rut VARCHAR(12) PRIMARY KEY,          
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
    rut_usuario VARCHAR(12) NOT NULL,
    fecha DATE NOT NULL,
    hora_entrada TIME NULL,
    hora_salida TIME NULL,
    estado ENUM('Presente','Atraso','Salida anticipada','Inasistente') NOT NULL DEFAULT 'Inasistente',
    FOREIGN KEY (rut_usuario) REFERENCES Usuarios(rut)
);

SELECT * FROM Usuarios;

SELECT * FROM Asistencias; 