use DBFrescoExpress;



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

-- CRUD COMPRAS

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


-- CRUD PROVEEDOR
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

-- CRUD CARGO EMPLEADO

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

-- CRUD de Productos

DELIMITER $$

CREATE PROCEDURE sp_agregarProducto(
    IN p_codigoProducto VARCHAR(15),
    IN p_descripcionProducto VARCHAR(15),
    IN p_precioUnitario DECIMAL(10,2),
    IN p_precioDocena DECIMAL(10,2),
    IN p_precioMayor DECIMAL(10,2),
    IN p_imagenProducto VARCHAR(45),
    IN p_existencia INT,
    IN p_codigoTipoProducto INT,
    IN p_codigoProveedor INT
)
BEGIN
    INSERT INTO Productos(codigoProducto, descripcionProducto, precioUnitario, precioDocena, precioMayor, imagenProducto, existencia, codigoTipoProducto, codigoProveedor)
    VALUES(p_codigoProducto, p_descripcionProducto, p_precioUnitario, p_precioDocena, p_precioMayor, p_imagenProducto, p_existencia, p_codigoTipoProducto, p_codigoProveedor);
END$$
DELIMITER ;

CALL sp_agregarProducto('P001', 'Producto 1', 10.00, 100.00, 500.00, 'img1.jpg', 50, 1, 1);
CALL sp_agregarProducto('P002', 'Producto 2', 20.00, 200.00, 1000.00, 'img2.jpg', 30, 2, 2);
CALL sp_agregarProducto('P003', 'Producto 3', 30.00, 300.00, 1500.00, 'img3.jpg', 20, 3, 3);
CALL sp_agregarProducto('P004', 'Producto 4', 40.00, 400.00, 2000.00, 'img4.jpg', 10, 4, 4);
CALL sp_agregarProducto('P005', 'Producto 5', 50.00, 500.00, 2500.00, 'img5.jpg', 5, 5, 5);

DELIMITER $$
CREATE PROCEDURE sp_listarProductos()
BEGIN
    SELECT * FROM Productos;
END$$
DELIMITER ;

CALL sp_listarProductos();

DELIMITER $$
CREATE PROCEDURE sp_actualizarProducto(
    IN _codigoProducto VARCHAR(15), 
    IN _descripcionProducto VARCHAR(15), 
    IN _precioUnitario DECIMAL(10,2), 
    IN _precioDocena DECIMAL(10,2), 
    IN _precioMayor DECIMAL(10,2), 
    IN _imagenProducto VARCHAR(45), 
    IN _existencia INT, 
    IN _codigoTipoProducto INT, 
    IN _codigoProveedor INT
)
BEGIN
    UPDATE Productos
    SET
        descripcionProducto = _descripcionProducto,
        precioUnitario = _precioUnitario,
        precioDocena = _precioDocena,
        precioMayor = _precioMayor,
        imagenProducto = _imagenProducto,
        existencia = _existencia,
        codigoTipoProducto = _codigoTipoProducto,
        codigoProveedor = _codigoProveedor
    WHERE
        codigoProducto = _codigoProducto;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_eliminarProducto(IN _codigoProducto VARCHAR(15))
BEGIN
    DELETE FROM Productos WHERE codigoProducto = _codigoProducto;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_buscarProducto(IN _codigoProducto VARCHAR(15))
BEGIN
    SELECT * FROM Productos WHERE codigoProducto = _codigoProducto;
END$$
DELIMITER ;

-- CRUD TELEFONO PROVEEDOR
DELIMITER $$
CREATE PROCEDURE sp_agregarTelefonoProveedor(
    IN _numeroPrincipal VARCHAR(8), 
    IN _numeroSecundario VARCHAR(8), 
    IN _observaciones VARCHAR(45), 
    IN _codigoProveedor INT
)
BEGIN
    INSERT INTO TelefonoProveedor (
        numeroPrincipal, 
        numeroSecundario, 
        observaciones, 
        codigoProveedor
    )
    VALUES (
        _numeroPrincipal, 
        _numeroSecundario, 
        _observaciones, 
        _codigoProveedor
    );
END$$
DELIMITER ;

CALL sp_agregarTelefonoProveedor('12345678', '87654321', 'Observaciones 1', 1);
CALL sp_agregarTelefonoProveedor('23456789', '98765432', 'Observaciones 2', 2);
CALL sp_agregarTelefonoProveedor('34567890', '09876543', 'Observaciones 3', 3);
CALL sp_agregarTelefonoProveedor('45678901', '10987654', 'Observaciones 4', 4);
CALL sp_agregarTelefonoProveedor('56789012', '21098765', 'Observaciones 5', 5);

DELIMITER $$
CREATE PROCEDURE sp_listarTelefonoProveedores()
BEGIN
    SELECT * FROM TelefonoProveedor;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_eliminarTelefonoProveedor(IN _codigoTelefonoProveedor INT)
BEGIN
    DELETE FROM TelefonoProveedor WHERE codigoTelefonoProveedor = _codigoTelefonoProveedor;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_buscarTelefonoProveedor(IN _codigoTelefonoProveedor INT)
BEGIN
    SELECT * FROM TelefonoProveedor WHERE codigoTelefonoProveedor = _codigoTelefonoProveedor;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_actualizarTelefonoProveedor(
    IN _codigoTelefonoProveedor INT, 
    IN _numeroPrincipal VARCHAR(8), 
    IN _numeroSecundario VARCHAR(8), 
    IN _observaciones VARCHAR(45), 
    IN _codigoProveedor INT
)
BEGIN
    UPDATE TelefonoProveedor
    SET
        numeroPrincipal = _numeroPrincipal,
        numeroSecundario = _numeroSecundario,
        observaciones = _observaciones,
        codigoProveedor = _codigoProveedor
    WHERE
        codigoTelefonoProveedor = _codigoTelefonoProveedor;
END$$
DELIMITER ;

-- CRUD EmailProveedor
DELIMITER $$
CREATE PROCEDURE sp_agregarEmailProveedor(
    IN _emailProveedor VARCHAR(50), 
    IN _descripcion VARCHAR(100), 
    IN _codigoProveedor INT
)
BEGIN
    INSERT INTO EmailProveedor (
        emailProveedor, 
        descripcion, 
        codigoProveedor
    )
    VALUES (
        _emailProveedor, 
        _descripcion, 
        _codigoProveedor
    );
END$$
DELIMITER ;

CALL sp_agregarEmailProveedor('correo1@proveedor.com', 'Descripción 1', 1);
CALL sp_agregarEmailProveedor('correo2@proveedor.com', 'Descripción 2', 2);
CALL sp_agregarEmailProveedor('correo3@proveedor.com', 'Descripción 3', 3);
CALL sp_agregarEmailProveedor('correo4@proveedor.com', 'Descripción 4', 4);
CALL sp_agregarEmailProveedor('correo5@proveedor.com', 'Descripción 5', 5);

DELIMITER $$
CREATE PROCEDURE sp_listarEmailProveedores()
BEGIN
    SELECT * FROM EmailProveedor;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_eliminarEmailProveedor(IN _codigoEmailProveedor INT)
BEGIN
    DELETE FROM EmailProveedor WHERE codigoEmailProveedor = _codigoEmailProveedor;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_buscarEmailProveedor(IN _codigoEmailProveedor INT)
BEGIN
    SELECT * FROM EmailProveedor WHERE codigoEmailProveedor = _codigoEmailProveedor;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_buscarEmailProveedor(IN _codigoEmailProveedor INT)
BEGIN
    SELECT * FROM EmailProveedor WHERE codigoEmailProveedor = _codigoEmailProveedor;
END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE sp_actualizarEmailProveedor(
    IN _codigoEmailProveedor INT, 
    IN _emailProveedor VARCHAR(50), 
    IN _descripcion VARCHAR(100), 
    IN _codigoProveedor INT
)
BEGIN
    UPDATE EmailProveedor
    SET
        emailProveedor = _emailProveedor,
        descripcion = _descripcion,
        codigoProveedor = _codigoProveedor
    WHERE
        codigoEmailProveedor = _codigoEmailProveedor;
END$$
DELIMITER ;

-- CRUD DETALLE COMPRA
DELIMITER $$
CREATE PROCEDURE sp_agregarDetalleCompra(
    IN _costoUnitario DECIMAL(10,2), 
    IN _cantidad INT, 
    IN _codigoProducto VARCHAR(15), 
    IN _numeroDocumento INT
)
BEGIN
    INSERT INTO DetalleCompra (
        costoUnitario, 
        cantidad, 
        codigoProducto, 
        numeroDocumento
    )
    VALUES (
        _costoUnitario, 
        _cantidad, 
        _codigoProducto, 
        _numeroDocumento
    );
END$$
DELIMITER ;

CALL sp_agregarDetalleCompra(10.00, 5, 'P001', 1);
CALL sp_agregarDetalleCompra(20.00, 10, 'P002', 2);
CALL sp_agregarDetalleCompra(30.00, 15, 'P003', 3);
CALL sp_agregarDetalleCompra(40.00, 20, 'P004', 4);
CALL sp_agregarDetalleCompra(50.00, 25, 'P005', 5);

DELIMITER $$
CREATE PROCEDURE sp_listarDetalleCompra()
BEGIN
    SELECT * FROM DetalleCompra;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_eliminarDetalleCompra(IN _codigoDetalleCompra INT)
BEGIN
    DELETE FROM DetalleCompra WHERE codigoDetalleCompra = _codigoDetalleCompra;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_buscarDetalleCompra(IN _codigoDetalleCompra INT)
BEGIN
    SELECT * FROM DetalleCompra WHERE codigoDetalleCompra = _codigoDetalleCompra;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_actualizarDetalleCompra(
    IN _codigoDetalleCompra INT, 
    IN _costoUnitario DECIMAL(10,2), 
    IN _cantidad INT, 
    IN _codigoProducto VARCHAR(15), 
    IN _numeroDocumento INT
)
BEGIN
    UPDATE DetalleCompra
    SET
        costoUnitario = _costoUnitario,
        cantidad = _cantidad,
        codigoProducto = _codigoProducto,
        numeroDocumento = _numeroDocumento
    WHERE
        codigoDetalleCompra = _codigoDetalleCompra;
END$$
DELIMITER ;

-- CRUD EMPLEADOS	
DELIMITER $$
CREATE PROCEDURE sp_agregarEmpleado(
    IN _nombresEmpleado VARCHAR(50), 
    IN _apellidosEmpleado VARCHAR(50), 
    IN _sueldo DECIMAL(10,2), 
    IN _direccion VARCHAR(150), 
    IN _turno VARCHAR(15), 
    IN _codigoCargoEmpleado INT
)
BEGIN
    INSERT INTO Empleados (
        nombresEmpleado, 
        apellidosEmpleado, 
        sueldo, 
        direccion, 
        turno, 
        codigoCargoEmpleado
    )
    VALUES (
        _nombresEmpleado, 
        _apellidosEmpleado, 
        _sueldo, 
        _direccion, 
        _turno, 
        _codigoCargoEmpleado
    );
END$$
DELIMITER ;

CALL sp_agregarEmpleado('Empleado 1', 'Apellido 1', 1000.00, 'Direccion 1', 'Mañana', 1);
CALL sp_agregarEmpleado('Empleado 2', 'Apellido 2', 2000.00, 'Direccion 2', 'Tarde', 2);
CALL sp_agregarEmpleado('Empleado 3', 'Apellido 3', 3000.00, 'Direccion 3', 'Noche', 3);
CALL sp_agregarEmpleado('Empleado 4', 'Apellido 4', 4000.00, 'Direccion 4', 'Mañana', 4);
CALL sp_agregarEmpleado('Empleado 5', 'Apellido 5', 5000.00, 'Direccion 5', 'Tarde', 5);

DELIMITER $$
CREATE PROCEDURE sp_listarEmpleados()
BEGIN
    SELECT * FROM Empleados;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_eliminarEmpleado(IN _codigoEmpleado INT)
BEGIN
    DELETE FROM Empleados WHERE codigoEmpleado = _codigoEmpleado;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_buscarEmpleado(IN _codigoEmpleado INT)
BEGIN
    SELECT * FROM Empleados WHERE codigoEmpleado = _codigoEmpleado;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_actualizarEmpleado(
    IN _codigoEmpleado INT, 
    IN _nombresEmpleado VARCHAR(50), 
    IN _apellidosEmpleado VARCHAR(50), 
    IN _sueldo DECIMAL(10,2), 
    IN _direccion VARCHAR(150), 
    IN _turno VARCHAR(15), 
    IN _codigoCargoEmpleado INT
)
BEGIN
    UPDATE Empleados
    SET
        nombresEmpleado = _nombresEmpleado,
        apellidosEmpleado = _apellidosEmpleado,
        sueldo = _sueldo,
        direccion = _direccion,
        turno = _turno,
        codigoCargoEmpleado = _codigoCargoEmpleado
    WHERE
        codigoEmpleado = _codigoEmpleado;
END$$
DELIMITER ;

-- CRUD FACTURA	
DELIMITER $$
CREATE PROCEDURE sp_agregarFactura(
    IN _estado VARCHAR(50), 
    IN _totalFactura DECIMAL(10,2), 
    IN _fechaFactura VARCHAR(45), 
    IN _clienteID INT, 
    IN _codigoEmpleado INT
)
BEGIN
    INSERT INTO Factura (
        estado, 
        totalFactura, 
        fechaFactura, 
        clienteID, 
        codigoEmpleado
    )
    VALUES (
        _estado, 
        _totalFactura, 
        _fechaFactura, 
        _clienteID, 
        _codigoEmpleado
    );
END$$
DELIMITER ;

CALL sp_agregarFactura('Pagado', 100.00, '2024-04-26', 1, 1);
CALL sp_agregarFactura('Pendiente', 200.00, '2024-04-25', 2, 2);
CALL sp_agregarFactura('Pagado', 300.00, '2024-04-24', 3, 3);
CALL sp_agregarFactura('Pendiente', 400.00, '2024-04-23', 4, 4);
CALL sp_agregarFactura('Pagado', 500.00, '2024-04-22', 5, 5);

DELIMITER $$
CREATE PROCEDURE sp_listarFacturas()
BEGIN
    SELECT * FROM Factura;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_eliminarFactura(IN _numeroDeFactura INT)
BEGIN
    DELETE FROM Factura WHERE numeroDeFactura = _numeroDeFactura;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_buscarFactura(IN _numeroDeFactura INT)
BEGIN
    SELECT * FROM Factura WHERE numeroDeFactura = _numeroDeFactura;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_actualizarFactura(
    IN _numeroDeFactura INT, 
    IN _estado VARCHAR(50), 
    IN _totalFactura DECIMAL(10,2), 
    IN _fechaFactura VARCHAR(45), 
    IN _clienteID INT, 
    IN _codigoEmpleado INT
)
BEGIN
    UPDATE Factura
    SET
        estado = _estado,
        totalFactura = _totalFactura,
        fechaFactura = _fechaFactura,
        clienteID = _clienteID,
        codigoEmpleado = _codigoEmpleado
    WHERE
        numeroDeFactura = _numeroDeFactura;
END$$
DELIMITER ;

-- CRUD DetalleFactura
DELIMITER $$
CREATE PROCEDURE sp_agregarDetalleFactura(
    IN _precioUnitario DECIMAL(10,2), 
    IN _cantidad INT, 
    IN _numeroDeFactura INT, 
    IN _codigoProducto VARCHAR(15)
)
BEGIN
    INSERT INTO DetalleFactura (
        precioUnitario, 
        cantidad, 
        numeroDeFactura, 
        codigoProducto
    )
    VALUES (
        _precioUnitario, 
        _cantidad, 
        _numeroDeFactura, 
        _codigoProducto
    );
END$$
DELIMITER ;

CALL sp_agregarDetalleFactura(10.00, 5, 1, 'P001');
CALL sp_agregarDetalleFactura(20.00, 10, 2, 'P002');
CALL sp_agregarDetalleFactura(30.00, 15, 3, 'P003');
CALL sp_agregarDetalleFactura(40.00, 20, 4, 'P004');
CALL sp_agregarDetalleFactura(50.00, 25, 5, 'P005');

DELIMITER $$
CREATE PROCEDURE sp_listarDetalleFactura()
BEGIN
    SELECT * FROM DetalleFactura;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_eliminarDetalleFactura(IN _codigoDetalleFactura INT)
BEGIN
    DELETE FROM DetalleFactura WHERE codigoDetalleFactura = _codigoDetalleFactura;
END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE sp_buscarDetalleFactura(IN _codigoDetalleFactura INT)
BEGIN
    SELECT * FROM DetalleFactura WHERE codigoDetalleFactura = _codigoDetalleFactura;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_actualizarDetalleFactura(
    IN _codigoDetalleFactura INT, 
    IN _precioUnitario DECIMAL(10,2), 
    IN _cantidad INT, 
    IN _numeroDeFactura INT, 
    IN _codigoProducto VARCHAR(15)
)
BEGIN
    UPDATE DetalleFactura
    SET
        precioUnitario = _precioUnitario,
        cantidad = _cantidad,
        numeroDeFactura = _numeroDeFactura,
        codigoProducto = _codigoProducto
    WHERE
        codigoDetalleFactura = _codigoDetalleFactura;
END$$
DELIMITER ;

