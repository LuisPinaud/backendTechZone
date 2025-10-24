drop database if exists Tech_Zone;
create database Tech_Zone;
use Tech_Zone;

-- tables
create table tb_usuario (
    cod_usu int primary key auto_increment,
    nom_usu varchar(50),
    ape_usu varchar(50),
    dni_usu varchar(8),
    email varchar(50) unique,
    telefono varchar(15),
    direccion varchar(150),
    usuario varchar(50) unique,
    clave varchar(70),
    rol varchar(20)  -- valores posibles: 'usuario', 'admin'
);

create table tb_proveedor(
cod_prov int primary key auto_increment,
nom_prov varchar(169)
);


create table tb_marca(
cod_marca int primary key auto_increment,
nom_marca varchar(30),
pai_marca varchar(50)
);

CREATE TABLE tb_categoria (
  cod_cat INT PRIMARY KEY AUTO_INCREMENT,
  nom_cat VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE tb_producto (
  cod_prod INT PRIMARY KEY AUTO_INCREMENT,
  nom_prod VARCHAR(80),
  des_prod VARCHAR(255),
  cod_cat INT,  -- clave foránea
  stock_prod INT,
  precio_compra DECIMAL(5,2),
  cod_prov INT,
  cod_marca INT,
  FOREIGN KEY (cod_cat) REFERENCES tb_categoria(cod_cat)
);

create table tb_compra (
    cod_compra int primary key auto_increment,
    fec_compra date,
    monto_compra decimal(5,2),
    mpago varchar(10),
    cod_usu int -- ya no hay cod_adm
);


-- union y restricciones claves secundarias
alter table tb_producto
add constraint fk_cod_proveedor foreign key(cod_prov) references tb_proveedor(cod_prov);

alter table tb_producto
add constraint fk_cod_marca foreign key(cod_marca) references tb_marca(cod_marca);

/*alter table tb_detalle_compra
add constraint fk_cod_producto foreign key(cod_prod) references tb_producto(cod_prod);

alter table tb_detalle_compra
add constraint fk_cod_compra foreign key(cod_compra) references tb_compra(cod_compra);*/

alter table tb_compra	
add constraint fk_cod_usu foreign key(cod_usu) references tb_usuario(cod_usu);

-- inserts 
INSERT INTO tb_proveedor (nom_prov) VALUES
('Proveedor A'),
('Proveedor B'),
('Proveedor C'),
('Proveedor D'),
('Proveedor E'),
('Proveedor F'),
('Proveedor G'),
('Proveedor H'),
('Proveedor I'),
('Proveedor J');

INSERT INTO tb_marca (nom_marca, pai_marca) VALUES
('Marca Uno', 'Perú'),
('Marca Dos', 'Chile'),
('Marca Tres', 'Argentina'),
('Marca Cuatro', 'Brasil'),
('Marca Cinco', 'Ecuador'),
('Marca Seis', 'Colombia'),
('Marca Siete', 'México'),
('Marca Ocho', 'España'),
('Marca Nueve', 'Italia'),
('Marca Diez', 'Francia');

INSERT INTO tb_categoria (nom_cat) VALUES
('Electrónica'),
('Ropa'),
('Alimentos'),
('Juguetes'),
('Libros'),
('Muebles'),
('Deportes'),
('Belleza'),
('Herramientas'),
('Automotriz');

INSERT INTO tb_producto (nom_prod, des_prod, cod_cat, stock_prod, precio_compra, cod_prov, cod_marca) VALUES
('Producto 1', 'Descripción del producto 1', 1, 100, 10.50, 1, 1),
('Producto 2', 'Descripción del producto 2', 2, 50, 20.75, 2, 2),
('Producto 3', 'Descripción del producto 3', 3, 200, 5.99, 3, 3),
('Producto 4', 'Descripción del producto 4', 4, 80, 15.00, 4, 4),
('Producto 5', 'Descripción del producto 5', 5, 60, 25.00, 5, 5),
('Producto 6', 'Descripción del producto 6', 6, 150, 30.99, 6, 6),
('Producto 7', 'Descripción del producto 7', 7, 90, 12.49, 7, 7),
('Producto 8', 'Descripción del producto 8', 8, 110, 8.99, 8, 8),
('Producto 9', 'Descripción del producto 9', 9, 70, 19.99, 9, 9),
('Producto 10', 'Descripción del producto 10', 10, 40, 50.00, 10, 10);



select * from tb_categoria;
select * from tb_marca;
select * from tb_producto;
select * from tb_proveedor;
select * from tb_usuario;