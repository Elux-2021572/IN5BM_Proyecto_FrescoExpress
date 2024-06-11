drop database if exists DBFrescoExpress;

set global time_zone = "-6:00";

create database DBFrescoExpress;

use DBFrescoExpress;

-- ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'admi';


create table Clientes(
    clienteID int not null auto_increment,
    nitCliente varchar(10), 
    nombresCliente varchar(50), 
    apellidosCliente varchar(50), 
    direccionCliente varchar(150), 
    telefonoCliente varchar(8), 
    correoCliente varchar(45), 
    primary key PK_clienteID(clienteID)
);

create table Compras(
    numeroDocumento int not null auto_increment, 
    fechaDocumento date, 
    descripcion varchar(60), 
    totalDocumento decimal(10,2),
    primary key PK_numeroDocumento(numeroDocumento)
);

create table TipoProducto(
    codigoTipoProducto int not null auto_increment,
    descripcion varchar(45), 
    primary key PK_codigoTipoProducto(codigoTipoProducto)
);

create table Proveedores(
    codigoProveedor int not null auto_increment, 
    nitProveedor varchar(10), 
    nombresProveedor varchar(60), 
    apellidosProveedor varchar(60), 
    direccionProveedor varchar(150), 
    razonSocial varchar (60), 
    contactoPrincipal varchar(100),
    paginaWeb varchar(50), 
    primary key PK_codigoProveedor(codigoProveedor)
);

create table CargoEmpleado(
	codigoCargoEmpleado int not null auto_increment,
    nombreCargo varchar(45), 
    descripcionCargo varchar(45),
    primary key PK_codigoCargoEmpleado(codigoCargoEmpleado)
);

create table Productos(
codigoProducto varchar(15) not null,
descripcionProducto varchar(15),
precioUnitario decimal(10,2),
precioDocena decimal(10,2),
precioMayor decimal(10,2),
imagenProducto varchar(45),
existencia int,
codigoTipoProducto int,
codigoProveedor int,
PRIMARY KEY PK_codigoProducto (codigoProducto),
FOREIGN KEY (codigoTipoProducto) REFERENCES TipoProducto(codigoTipoProducto),
FOREIGN KEY (codigoProveedor) REFERENCES Proveedores(codigoProveedor)
    
);

-- 6 

create table TelefonoProveedor(
codigoTelefonoProveedor int not null auto_increment,
numeroPrincipal varchar(8),
numeroSecundario varchar(8),
observaciones varchar(45),
codigoProveedor int,
PRIMARY KEY PK_codigoTelefonoProveedor (codigoTelefonoProveedor),
FOREIGN KEY (codigoProveedor) REFERENCES Proveedores (codigoProveedor)

);

create table EmailProveedor(
codigoEmailProveedor int not null auto_increment,
emailproveedor varchar(50),
descripcion varchar(100),
codigoProveedor int,
PRIMARY KEY PK_codigoEmailProveedor (codigoEmailProveedor),
FOREIGN KEY (codigoProveedor) REFERENCES Proveedores (codigoProveedor)

);

create table DetalleCompra(
codigoDetalleCompra int not null auto_increment,
costoUnitario decimal(10,2),
cantidad int,
codigoProducto varchar(15),
numeroDocumento int,
PRIMARY KEY PK_codigoDetalleCompra (codigoDetalleCompra),
FOREIGN KEY (codigoProducto) REFERENCES Productos(codigoProducto),
FOREIGN KEY (numeroDocumento) REFERENCES Compras(numeroDocumento)
    
);

create table Empleados(
codigoEmpleado int not null auto_increment,
nombresEmpleado varchar(50),
apellidosEmpleado varchar(50),
sueldo decimal(10,2),
direccion varchar(150),
turno varchar(15),
codigoCargoEmpleado int,
PRIMARY KEY PK_codigoEmpleado (codigoEmpleado),
FOREIGN KEY (codigoCargoEmpleado) REFERENCES CargoEmpleado(codigoCargoEmpleado)
);

create table Factura(
numeroDeFactura int not null auto_increment,
estado varchar(50),
totalFactura decimal(10,2),
fechaFactura varchar(45),
clienteID int,
codigoEmpleado int,
PRIMARY KEY PK_numeroDeFactura (numeroDeFactura),
FOREIGN KEY (clienteID) REFERENCES Clientes(clienteID),
FOREIGN KEY (codigoEmpleado) REFERENCES Empleados(codigoEmpleado)

);

create table DetalleFactura(
codigoDetalleFactura int not null auto_increment,
precioUnitario decimal(10,2),
cantidad int,
numeroDeFactura int,
codigoProducto varchar(15),
PRIMARY KEY PK_codigoDetalleFactura (codigoDetalleFactura),
FOREIGN KEY (numeroDeFactura) REFERENCES Factura(numeroDeFactura),
FOREIGN KEY (codigoProducto) REFERENCES Productos(codigoProducto)

);

select * from DetalleFactura
	join Factura on DetalleFactura.numeroDeFactura = Factura.numeroDeFactura
    join clientes on Factura.clienteID = Clientes.clienteID
    join Productos on DetalleFactura.codigoProducto = Productos.codigoProducto
    where Factura.numeroDeFactura = 2; 

