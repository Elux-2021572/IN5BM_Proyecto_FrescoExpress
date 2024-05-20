drop database if exists DBFrescoExpress;

set global time_zone = "-6:00";

create database DBFrescoExpress;

use DBFrescoExpress;

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

-- CRUD CLIENTES
-- SP para agregar un cliente
DELIMITER $$
create procedure sp_agregarCliente(in _nitCliente varchar(10), in _nombresCliente varchar(50), in _apellidosCliente varchar(50), in _direccionCliente varchar(150), in _telefonoCliente varchar(8), in _correoCliente varchar(45))
begin
    insert into Clientes (nitCliente, nombresCliente, apellidosCliente, direccionCliente, telefonoCliente, correoCliente)
    values (_nitCliente, _nombresCliente, _apellidosCliente, _direccionCliente, _telefonoCliente, _correoCliente);
end$$
DELIMITER ;

call sp_agregarCliente('1234567890', 'Juan', 'Pérez', 'Calle 123, Ciudad', '12345678', 'juan@kinal.edu.gt');
call sp_agregarCliente('9876543210', 'María', 'Gómez', 'Avenida 456, Pueblo', '87654321', 'maria@kinal.edu.gt');
call sp_agregarCliente('4567890123', 'Pedro', 'López', 'Calle 789, Villa', '45678901', 'pedro@kinal.edu.gt');
call sp_agregarCliente('7890123789', 'Ana', 'Martínez', 'Avenida 012, Aldea', '78901234', 'ana@kinal.edu.gt');
call sp_agregarCliente('2345901234', 'Luis', 'Hernández', 'Calle 345, Pueblo Nuevo', '23456789', 'luis@kinal.edu.gt');


-- SP para listar todos los clientes
DELIMITER $$
create procedure sp_listarClientes()
begin
    select * from Clientes;
end$$
DELIMITER ;

call sp_listarClientes();

-- SP para eliminar un cliente por su NIT
DELIMITER $$
create procedure sp_eliminarCliente(in _clienteID int)
begin
    delete from Clientes where clienteID = _clienteID;
end$$
DELIMITER ;


-- SP para buscar un cliente por su NIT
DELIMITER $$
create procedure sp_buscarCliente(in _nitCliente varchar(10))
begin
    select * from Clientes where nitCliente = _nitCliente;
end$$
DELIMITER ;

-- SP para actualizar la información de un cliente
DELIMITER $$
create procedure sp_actualizarCliente(in _clienteID int, _nitCliente varchar(10), in _nombresCliente varchar(50), in _apellidosCliente varchar(50), in _direccionCliente varchar(150), in _telefonoCliente varchar(8), in _correoCliente varchar(45))
begin
    update Clientes
    set
		nitCliente = _nitCliente,
        nombresCliente = _nombresCliente,
        apellidosCliente = _apellidosCliente,
        direccionCliente = _direccionCliente,
        telefonoCliente = _telefonoCliente,
        correoCliente = _correoCliente
    where
        clienteID = _clienteID;
end$$
DELIMITER ;

-- SP para agregar una compra
delimiter $$
create procedure sp_agregarCompra(in _fechaDocumento date, in _descripcion varchar(60), in _totalDocumento decimal(10,2))
begin
    insert into Compras (fechaDocumento, descripcion, totalDocumento)
    values (_fechaDocumento, _descripcion, _totalDocumento);
end$$
delimiter ;

-- Agregar compra 1
CALL sp_agregarCompra('2024-04-26', 'Compra de productos varios', 150.50);

-- Agregar compra 2
CALL sp_agregarCompra('2024-04-25', 'Compra de material de oficina', 200.75);

-- Agregar compra 3
CALL sp_agregarCompra('2024-04-24', 'Compra de alimentos para el personal', 350.20);

-- Agregar compra 4
CALL sp_agregarCompra('2024-04-23', 'Compra de herramientas de trabajo', 180.00);

-- Agregar compra 5
CALL sp_agregarCompra('2024-04-22', 'Compra de suministros de limpieza', 75.30);

-- SP para listar todas las compras
delimiter $$
create procedure sp_listarCompras()
begin
    select * from Compras;
end$$
delimiter ;

-- SP para eliminar una compra por su número de documento
delimiter $$
create procedure sp_eliminarCompra(in _numeroDocumento int)
begin
    delete from Compras where numeroDocumento = _numeroDocumento;
end$$
delimiter ;

-- SP para buscar una compra por su número de documento
delimiter $$
create procedure sp_buscarCompra(in _numeroDocumento int)
begin
    select * from Compras where numeroDocumento = _numeroDocumento;
end$$
delimiter ;

-- SP para actualizar la información de una compra
delimiter $$
create procedure sp_actualizarCompra(in _numeroDocumento int, in _fechaDocumento date, in _descripcion varchar(60), in _totalDocumento decimal(10,2))
begin
    update Compras
    set
        fechaDocumento = _fechaDocumento,
        descripcion = _descripcion,
        totalDocumento = _totalDocumento
    where
        numeroDocumento = _numeroDocumento;
end$$
delimiter ;


-- SP para agregar un tipo de producto
delimiter $$
create procedure sp_agregarTipoProducto(in _descripcion varchar(45))
begin
    insert into TipoProducto (descripcion)
    values (_descripcion);
end$$
delimiter ;

CALL sp_agregarTipoProducto('Electrónicos');
CALL sp_agregarTipoProducto('Ropa');
CALL sp_agregarTipoProducto('Alimentos');
CALL sp_agregarTipoProducto('Juguetes');
CALL sp_agregarTipoProducto('Hogar');


-- SP para listar todos los tipos de productos
delimiter $$
create procedure sp_listarTipoProductos()
begin
    select * from TipoProducto;
end$$
delimiter ;

-- SP para eliminar un tipo de producto por su código
delimiter $$
create procedure sp_eliminarTipoProducto(in _codigoTipoProducto int)
begin
    delete from TipoProducto where codigoTipoProducto = _codigoTipoProducto;
end$$
delimiter ;

-- SP para buscar un tipo de producto por su código
delimiter $$
create procedure sp_buscarTipoProducto(in _codigoTipoProducto int)
begin
    select * from TipoProducto where codigoTipoProducto = _codigoTipoProducto;
end$$
delimiter ;

-- SP para actualizar la información de un tipo de producto
delimiter $$
create procedure sp_actualizarTipoProducto(in _codigoTipoProducto int, in _descripcion varchar(45))
begin
    update TipoProducto
    set
        descripcion = _descripcion
    where
        codigoTipoProducto = _codigoTipoProducto;
end$$
delimiter ;


-- SP para agregar un proveedor
delimiter $$
create procedure sp_agregarProveedor(in _nitProveedor varchar(10), in _nombresProveedor varchar(60), in _apellidosProveedor varchar(60), in _direccionProveedor varchar(150), in _razonSocial varchar(60), in _contactoPrincipal varchar(100), in _paginaWeb varchar(50))
begin
    insert into Proveedores (nitProveedor, nombresProveedor, apellidosProveedor, direccionProveedor, razonSocial, contactoPrincipal, paginaWeb)
    values (_nitProveedor, _nombresProveedor, _apellidosProveedor, _direccionProveedor, _razonSocial, _contactoPrincipal, _paginaWeb);
end$$
delimiter ;

CALL sp_agregarProveedor('1234567890', 'Juan', 'Zapeta', 'Dirección1', 'Razón1', 'Telefono', 'www.pescado.com');
CALL sp_agregarProveedor('0987654321', 'José', 'Lux', 'Dirección2', 'Razón2', 'Correo', 'www.pepita.com');
CALL sp_agregarProveedor('9876543210', 'Ricardo', 'Reynoso', 'Dirección3', 'Razón3', 'Sitio Web', 'www.aguapuraconsal.com');
CALL sp_agregarProveedor('5678901234', 'Luis', 'Herrera', 'Dirección4', 'Razón4', 'Telefono', 'www.escritorio.com');
CALL sp_agregarProveedor('4567890123', 'Angel', 'Ramirez', 'Dirección5', 'Razón5', 'Correo', 'www.muñecas.com');

-- SP para listar todos los proveedores
delimiter $$
create procedure sp_listarProveedores()
begin
    select * from Proveedores;
end$$
delimiter ;

-- SP para eliminar un proveedor por su código
delimiter $$
create procedure sp_eliminarProveedor(in _codigoProveedor int)
begin
    delete from Proveedores where codigoProveedor = _codigoProveedor;
end$$
delimiter ;

-- SP para buscar un proveedor por su código
delimiter $$
create procedure sp_buscarProveedor(in _codigoProveedor int)
begin
    select * from Proveedores where codigoProveedor = _codigoProveedor;
end$$
delimiter ;

-- SP para actualizar la información de un proveedor
delimiter $$
create procedure sp_actualizarProveedor(in _codigoProveedor int, in _nitProveedor varchar(10), in _nombresProveedor varchar(60), in _apellidosProveedor varchar(60), in _direccionProveedor varchar(150), in _razonSocial varchar(60), in _contactoPrincipal varchar(100), in _paginaWeb varchar(50))
begin
    update Proveedores
    set
        nitProveedor = _nitProveedor,
        nombresProveedor = _nombresProveedor,
        apellidosProveedor = _apellidosProveedor,
        direccionProveedor = _direccionProveedor,
        razonSocial = _razonSocial,
        contactoPrincipal = _contactoPrincipal,
        paginaWeb = _paginaWeb
    where
        codigoProveedor = _codigoProveedor;
end$$
delimiter ;

-- SP para agregar un cargo de empleado
delimiter $$
create procedure sp_agregarCargoEmpleado(in _nombreCargo varchar(45), in _descripcionCargo varchar(45))
begin
    insert into CargoEmpleado (nombreCargo, descripcionCargo)
    values (_nombreCargo, _descripcionCargo);
end$$
delimiter ;

-- SP para listar todos los cargos de empleados
delimiter $$
create procedure sp_listarCargosEmpleado()
begin
    select * from CargoEmpleado;
end$$
delimiter ;

-- SP para eliminar un cargo de empleado por su código
delimiter $$
create procedure sp_eliminarCargoEmpleado(in _codigoCargoEmpleado int)
begin
    delete from CargoEmpleado where codigoCargoEmpleado = _codigoCargoEmpleado;
end$$
delimiter ;

-- SP para buscar un cargo de empleado por su código
delimiter $$
create procedure sp_buscarCargoEmpleado(in _codigoCargoEmpleado int)
begin
    select * from CargoEmpleado where codigoCargoEmpleado = _codigoCargoEmpleado;
end$$
delimiter ;

-- SP para actualizar la información de un cargo de empleado
delimiter $$
create procedure sp_actualizarCargoEmpleado(in _codigoCargoEmpleado int, in _nombreCargo varchar(45), in _descripcionCargo varchar(45))
begin
    update CargoEmpleado
    set
        nombreCargo = _nombreCargo,
        descripcionCargo = _descripcionCargo
    where
        codigoCargoEmpleado = _codigoCargoEmpleado;
end$$
delimiter ;

