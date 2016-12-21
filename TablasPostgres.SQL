CREATE TABLE accion ( 
	accion_id bigint NOT NULL,
	descripcion varchar(255),
	estado boolean,
	nombre varchar(255)
)
;

CREATE TABLE bitacora ( 
	bitacora_id bigint NOT NULL,
	descripcion varchar(255),
	fecha_hora timestamp,
	ip varchar(255),
	accion_id bigint,
	formulario_id bigint,
	usuario_id bigint
)
;

CREATE TABLE Categoria ( 
	ID bigint NOT NULL,
	NOMBRE varchar(100),
	DESCRIPCION varchar(500)
)
;

CREATE TABLE Cliente ( 
	CI bigint NOT NULL,
	NOMBRE_COMPLETO varchar(500)
)
;

CREATE TABLE DetallePedido ( 
	CANTIDAD bigint,
	ID_PEDIDO bigint,
	ID_PRODUCTO bigint
)
;

CREATE TABLE Empleado ( 
	CI bigint NOT NULL,
	NOMBRE_COMPLETO varchar(150),
	CELULAR varchar(50),
	CORREO varchar(150)
)
;

CREATE TABLE Empresa ( 
	ID bigint NOT NULL,
	NOMBRE varchar(150),
	SUCURSAL bigint,
	DIRECCION varchar(150),
	NIT varchar(50),
	NRO_AUTORIZACION varchar(50),
	COMENTARIO_FACTURA varchar(500)
)
;

CREATE TABLE Factura ( 
	NRO_FACTURA varchar(150),
	COD_CONTROL varchar(50),
	TOTAL_PAGAR decimal(10,2),
	ID_EMPRESA bigint,
	ID_PEDIDO  bigint,
	ID_EMPLEADO bigint,
	usuario_id bigint
)
;

CREATE TABLE formulario ( 
	formulario_id bigint NOT NULL,
	descripcion varchar(255),
	estado boolean,
	nombre varchar(255),
	url varchar(255),
	menu_id bigint
)
;

CREATE TABLE formulario_accion ( 
	formulario_accion_id bigint NOT NULL,
	estado boolean,
	accion_id bigint,
	formulario_id bigint
)
;

CREATE TABLE grupo ( 
	grupo_id bigint NOT NULL,
	descripcion varchar(255),
	estado boolean,
	nombre varchar(255),
	privilegio_id bigint
)
;

CREATE TABLE menu ( 
	menu_id bigint NOT NULL,
	descripcion char(100),
	estado boolean,
	nombre char(50),
	menu_id_padre bigint
)
;

CREATE TABLE Mesa ( 
	ID bigint NOT NULL,
	NUMERO varchar(50),
	DESCRIPCION varchar(150)
)
;

CREATE TABLE Pedido ( 
	ID bigint NOT NULL,
	FECHA_HORA timestamp,
	ESTADO varchar(50),
	CI_CLIENTE  bigint,
	ID_MESA  bigint
)
;

CREATE TABLE privilegio ( 
	privilegio_id bigint NOT NULL,
	descripcion varchar(255),
	estado boolean,
	nombre varchar(255)
)
;

CREATE TABLE privilegio_formulario ( 
	privilegio_formulario_id bigint NOT NULL,
	estado boolean,
	accion_id bigint,
	formulario_id bigint,
	privilegio_id bigint
)
;

CREATE TABLE Producto ( 
	ID bigint NOT NULL,
	NOMBRE varchar(500),
	PRECIO decimal(10,2),
	DESCRIPCION varchar(500),
	STOCK bigint,
	ID_CATEGORIA bigint
)
;

CREATE TABLE usuario ( 
	usuario_id bigint NOT NULL,
	apellido_materno varchar(255),
	apellido_paterno varchar(255),
	estado boolean,
	login varchar(255),
	password varchar(255),
	grupo_id bigint
)
;

ALTER TABLE accion ADD CONSTRAINT PK_accion 
	PRIMARY KEY (accion_id)
;

ALTER TABLE bitacora ADD CONSTRAINT PK_bitacora 
	PRIMARY KEY (bitacora_id)
;

ALTER TABLE Categoria ADD CONSTRAINT PK_Categoria 
	PRIMARY KEY (ID)
;

ALTER TABLE Cliente ADD CONSTRAINT PK_Cliente 
	PRIMARY KEY (CI)
;

ALTER TABLE Empleado ADD CONSTRAINT PK_Empleado 
	PRIMARY KEY (CI)
;

ALTER TABLE Empresa ADD CONSTRAINT PK_Empresa 
	PRIMARY KEY (ID)
;

ALTER TABLE formulario ADD CONSTRAINT PK_formulario 
	PRIMARY KEY (formulario_id)
;

ALTER TABLE formulario_accion ADD CONSTRAINT PK_formulario_accion 
	PRIMARY KEY (formulario_accion_id)
;

ALTER TABLE grupo ADD CONSTRAINT PK_grupo 
	PRIMARY KEY (grupo_id)
;

ALTER TABLE menu ADD CONSTRAINT PK_menu 
	PRIMARY KEY (menu_id)
;

ALTER TABLE Mesa ADD CONSTRAINT PK_Mesa 
	PRIMARY KEY (ID)
;

ALTER TABLE Pedido ADD CONSTRAINT PK_Pedido 
	PRIMARY KEY (ID)
;

ALTER TABLE privilegio ADD CONSTRAINT PK_privilegio 
	PRIMARY KEY (privilegio_id)
;

ALTER TABLE privilegio_formulario ADD CONSTRAINT PK_privilegio_formulario 
	PRIMARY KEY (privilegio_formulario_id)
;

ALTER TABLE Producto ADD CONSTRAINT PK_Producto 
	PRIMARY KEY (ID)
;

ALTER TABLE usuario ADD CONSTRAINT PK_usuario 
	PRIMARY KEY (usuario_id)
;

ALTER TABLE bitacora ADD CONSTRAINT FK_bitacora_accion 
	FOREIGN KEY (accion_id) REFERENCES accion (accion_id)
;

ALTER TABLE bitacora ADD CONSTRAINT FK_bitacora_formulario 
	FOREIGN KEY (formulario_id) REFERENCES formulario (formulario_id)
;

ALTER TABLE bitacora ADD CONSTRAINT FK_bitacora_usuario 
	FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id)
;

ALTER TABLE DetallePedido ADD CONSTRAINT FK_DetallePedido_Pedido 
	FOREIGN KEY (ID_PEDIDO) REFERENCES Pedido (ID)
;

ALTER TABLE DetallePedido ADD CONSTRAINT FK_DetallePedido_Producto 
	FOREIGN KEY (ID_PRODUCTO) REFERENCES Producto (ID)
;

ALTER TABLE Factura ADD CONSTRAINT FK_Factura_Empleado 
	FOREIGN KEY (ID_EMPLEADO) REFERENCES Empleado (CI)
;

ALTER TABLE Factura ADD CONSTRAINT FK_Factura_Empresa 
	FOREIGN KEY (ID_EMPRESA) REFERENCES Empresa (ID)
;

ALTER TABLE Factura ADD CONSTRAINT FK_Factura_Pedido 
	FOREIGN KEY (ID_PEDIDO ) REFERENCES Pedido (ID)
;

ALTER TABLE Factura ADD CONSTRAINT FK_Factura_usuario 
	FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id)
;

ALTER TABLE formulario ADD CONSTRAINT FK_formulario_menu 
	FOREIGN KEY (menu_id) REFERENCES menu (menu_id)
;

ALTER TABLE formulario_accion ADD CONSTRAINT FK_formulario_accion_accion 
	FOREIGN KEY (accion_id) REFERENCES accion (accion_id)
;

ALTER TABLE formulario_accion ADD CONSTRAINT FK_formulario_accion_formulario 
	FOREIGN KEY (formulario_id) REFERENCES formulario (formulario_id)
;

ALTER TABLE grupo ADD CONSTRAINT FK_grupo_privilegio 
	FOREIGN KEY (privilegio_id) REFERENCES privilegio (privilegio_id)
;

ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_Cliente 
	FOREIGN KEY (CI_CLIENTE ) REFERENCES Cliente (CI)
;

ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_Mesa 
	FOREIGN KEY (ID_MESA ) REFERENCES Mesa (ID)
;

ALTER TABLE privilegio_formulario ADD CONSTRAINT FK_privilegio_formulario_accion 
	FOREIGN KEY (accion_id) REFERENCES accion (accion_id)
;

ALTER TABLE privilegio_formulario ADD CONSTRAINT FK_privilegio_formulario_formulario 
	FOREIGN KEY (formulario_id) REFERENCES formulario (formulario_id)
;

ALTER TABLE privilegio_formulario ADD CONSTRAINT FK_privilegio_formulario_privilegio 
	FOREIGN KEY (privilegio_id) REFERENCES privilegio (privilegio_id)
;

ALTER TABLE Producto ADD CONSTRAINT FK_Producto_Categoria 
	FOREIGN KEY (ID_CATEGORIA) REFERENCES Categoria (ID)
;

ALTER TABLE usuario ADD CONSTRAINT FK_usuario_grupo 
	FOREIGN KEY (grupo_id) REFERENCES grupo (grupo_id)
;



INSERT INTO "public"."categoria" VALUES ('1', 'Tortas', 'Las mejores tortas de Santa Cruz');
INSERT INTO "public"."categoria" VALUES ('6', 'Cafés/Té', 'Categoría de cafés y té');
INSERT INTO "public"."categoria" VALUES ('7', 'Horneados', 'Horneados tipicos');
--------------------------------------------------------------------------------------------------
INSERT INTO "public"."producto" VALUES ('3', 'café con leche', '5.00', 'Café con leche', '10', '6', '3.png');
INSERT INTO "public"."producto" VALUES ('4', 'toddy', '6.00', 'toddy', '50', '6', '4.png');
INSERT INTO "public"."producto" VALUES ('5', 'Te con canela', '5.00', 'Te con canela y clavo de olor', '10', '6', '5.jpg');
INSERT INTO "public"."producto" VALUES ('6', 'Te de manzanilla', '4.00', 'Te de manzanilla', '5', '6', '6.jpg');
INSERT INTO "public"."producto" VALUES ('7', 'Café', '4.00', 'Café negro', '10', '6', '7.jpg');
INSERT INTO "public"."producto" VALUES ('8', 'Torta tres leches', '150.00', 'Torta tres leches para 20 personas', '20', '1', '8.png');
INSERT INTO "public"."producto" VALUES ('10', 'Torta de chocolate', '150.00', 'Torta de chocolate  para 20 personas', '15', '1', '10.png');
INSERT INTO "public"."producto" VALUES ('11', 'Torta de frutilla', '120.00', 'Torta de frutilla  para 20 personas', '20', '1', '11.png');
INSERT INTO "public"."producto" VALUES ('12', 'Torta de coco', '15.00', 'Torta de coco porcion', '10', '1', '12.png');
INSERT INTO "public"."producto" VALUES ('13', 'Torta de vainilla', '13.00', 'Torta de vainilla porcion', '10', '1', '13.png');
INSERT INTO "public"."producto" VALUES ('14', 'Cuñape', '5.00', 'Cuñape', '30', '7', '14.jpg');
INSERT INTO "public"."producto" VALUES ('15', 'Empanada con queso', '4.00', 'Empanada con queso', '30', '7', '15.jpg');
INSERT INTO "public"."producto" VALUES ('16', 'Pan de arroz', '2.00', 'Pan de arroz', '50', '7', '16.jpg');
INSERT INTO "public"."producto" VALUES ('17', 'Masaco de yuca', '5.00', 'Masaco de yuca y charque', '30', '7', '17.jpg');
--------------------------------------------------------------------------------------------------



INSERT INTO "public"."mesa" VALUES ('1', 'Nro 1', 'Mesa nro. 1');
INSERT INTO "public"."mesa" VALUES ('2', 'Nro 2', 'Mesa nro. 2');
INSERT INTO "public"."mesa" VALUES ('3', 'Nro 3', 'Mesa nro. 3');
INSERT INTO "public"."mesa" VALUES ('4', 'Nro 4', 'Mesa nro. 4');
INSERT INTO "public"."mesa" VALUES ('5', 'Nro 5', 'Mesa nro. 5');
--------------------------------------------------------------------------------------------------

INSERT INTO "public"."empresa" VALUES ('1', 'Heladeria y Pasteleria Bambi', '1', 'Plaza Villa 1ro de Mayo', '123456789', '987654321', 'Factura');
--------------------------------------------------------------------------------------------------

INSERT INTO "public"."empleado" VALUES ('123', 'Jerry ', '76667778', 'jerry@heladeria.com.bo');
INSERT INTO "public"."empleado" VALUES ('345', 'Andrea Carranza', '3540371', 'andrea@gmail.com');
--------------------------------------------------------------------------------------------------





