

CREATE TABLE CATEGORIA ( 
	ID bigint NOT NULL,
	NOMBRE varchar(100) NOT NULL,
	DESCRIPCION varchar(500)
)
;

CREATE TABLE CLIENTE ( 
	CI bigint NOT NULL,
	NOMBRE_COMPLETO varchar(500) NOT NULL
)
;

CREATE TABLE DETALLE_PEDIDO ( 
	ID_PEDIDO bigint NOT NULL,
	ID_PRODUCTO bigint NOT NULL,
	CANTIDAD bigint NOT NULL
)
;

CREATE TABLE EMPLEADO ( 
	CI bigint NOT NULL,
	NOMBRE_COMPLETO varchar(150) NOT NULL,
	CELULAR varchar(50),
	CORREO varchar(150) NOT NULL
)
;

CREATE TABLE EMPRESA ( 
	ID bigint NOT NULL,
	NOMBRE varchar(150) NOT NULL,
	SUCURSAL bigint NOT NULL,
	DIRECCION varchar(150) NOT NULL,
	NIT varchar(50) NOT NULL,
	NRO_AUTORIZACION varchar(50) NOT NULL,
	COMENTARIO_FACTURA varchar(500)
)
;

CREATE TABLE FACTURA ( 
	NRO_FACTURA varchar(150) NOT NULL,
	COD_CONTROL varchar(50) NOT NULL,
	TOTAL_PAGAR decimal(10,2) NOT NULL,
	ID_EMPRESA bigint NOT NULL,
	ID_PEDIDO bigint NOT NULL,
	CI_EMPLEADO bigint
)
;

CREATE SEQUENCE MESA_ID_seq INCREMENT 1 START 1
;

CREATE TABLE MESA ( 
	ID bigint DEFAULT nextval(('MESA_ID_seq'::text)::regclass) NOT NULL,
	NUMERO varchar(50) NOT NULL,
	DESCRIPCION varchar(150)
)
;

CREATE SEQUENCE PEDIDO_ID_seq INCREMENT 1 START 1
;

CREATE TABLE PEDIDO ( 
	ID bigint DEFAULT nextval(('PEDIDO_ID_seq'::text)::regclass) NOT NULL,
	FECHA_HORA timestamp NOT NULL,
	ESTADO varchar(50) NOT NULL,
	CI_CLIENTE bigint NOT NULL,
	ID_MESA bigint NOT NULL
)
;

CREATE SEQUENCE PRODUCTO_ID_seq INCREMENT 1 START 1
;

CREATE TABLE PRODUCTO ( 
	ID bigint DEFAULT nextval(('PRODUCTO_ID_seq'::text)::regclass) NOT NULL,
	NOMBRE varchar(500),
	PRECIO decimal(10,2),
	DESCRIPCION varchar(500),
	STOCK bigint NOT NULL,
	ID_CATEGORIA bigint NOT NULL,
	IMAGEN varchar(50) 
)
;


ALTER TABLE CATEGORIA ADD CONSTRAINT PK_CATEGORIA 
	PRIMARY KEY (ID)
;


ALTER TABLE CLIENTE ADD CONSTRAINT PK_CLIENTE 
	PRIMARY KEY (CI)
;


ALTER TABLE DETALLE_PEDIDO ADD CONSTRAINT PK_DETALLE_PEDIDO 
	PRIMARY KEY (ID_PEDIDO, ID_PRODUCTO)
;


ALTER TABLE EMPLEADO ADD CONSTRAINT PK_EMPLEADO 
	PRIMARY KEY (CI)
;


ALTER TABLE EMPRESA ADD CONSTRAINT PK_EMPRESA 
	PRIMARY KEY (ID)
;


ALTER TABLE FACTURA ADD CONSTRAINT PK_FACTURA 
	PRIMARY KEY (NRO_FACTURA, COD_CONTROL)
;


ALTER TABLE MESA ADD CONSTRAINT PK_MESA 
	PRIMARY KEY (ID)
;


ALTER TABLE PEDIDO ADD CONSTRAINT PK_PEDIDO 
	PRIMARY KEY (ID)
;


ALTER TABLE PRODUCTO ADD CONSTRAINT PK_PRODUCTO 
	PRIMARY KEY (ID)
;




ALTER TABLE DETALLE_PEDIDO ADD CONSTRAINT FK_DETALLE_PEDIDO_PEDIDO 
	FOREIGN KEY (ID_PEDIDO) REFERENCES PEDIDO (ID)
;

ALTER TABLE DETALLE_PEDIDO ADD CONSTRAINT FK_DETALLE_PEDIDO_PRODUCTO 
	FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTO (ID)
;

ALTER TABLE FACTURA ADD CONSTRAINT FK_FACTURA_EMPLEADO 
	FOREIGN KEY (CI_EMPLEADO) REFERENCES EMPLEADO (CI)
;

ALTER TABLE FACTURA ADD CONSTRAINT FK_FACTURA_EMPRESA 
	FOREIGN KEY (ID_EMPRESA) REFERENCES EMPRESA (ID)
;

ALTER TABLE FACTURA ADD CONSTRAINT FK_FACTURA_PEDIDO 
	FOREIGN KEY (ID_PEDIDO) REFERENCES PEDIDO (ID)
;

ALTER TABLE PEDIDO ADD CONSTRAINT FK_PEDIDO_CLIENTE 
	FOREIGN KEY (CI_CLIENTE) REFERENCES CLIENTE (CI)
;

ALTER TABLE PEDIDO ADD CONSTRAINT FK_PEDIDO_EMPLEADO 
	FOREIGN KEY (CI_EMPLEADO) REFERENCES EMPLEADO (CI)
;

ALTER TABLE PEDIDO ADD CONSTRAINT FK_PEDIDO_MESA 
	FOREIGN KEY (ID_MESA) REFERENCES MESA (ID)
;

ALTER TABLE PRODUCTO ADD CONSTRAINT FK_PRODUCTO_CATEGORIA 
	FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIA (ID)
;


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

INSERT INTO "public"."categoria" VALUES ('1', 'Tortas', 'Las mejores tortas de Santa Cruz');
INSERT INTO "public"."categoria" VALUES ('6', 'Cafés/Té', 'Categoría de cafés y té');
INSERT INTO "public"."categoria" VALUES ('7', 'Horneados', 'Horneados tipicos');
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





