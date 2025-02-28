# BankApplication Microservices

Este proyecto implementa una arquitectura de microservicios para una aplicación bancaria. Los microservicios incluyen `client` y `account`, cada uno responsable de manejar clientes, cuentas y transacciones, respectivamente.

## Estructura del Proyecto

```
Microservices/
│
├── BankApplication/
│   ├── account/
│   │   ├── src/
│   │   ├── pom.xml
│   │   └── ...
│   ├── client/
│   │   ├── src/
│   │   ├── pom.xml
│   │   └── ...
│
└── README.md
```

## Descripción de los Microservicios

### 1. `client`
**Descripción:** Maneja las operaciones CRUD para los clientes.

**Endpoints:**
- `POST /api/clients`: Crear un cliente.
- `GET /api/clients/{id}`: Obtener un cliente por ID.
- `PUT /api/clients`: Actualizar un cliente.
- `DELETE /api/clients/{id}`: Eliminar un cliente.
- `GET /api/clients`: Listar todos los clientes.

### 2. `account`
**Descripción:** Maneja las operaciones CRUD para las cuentas y transacciones.

**Endpoints: account**
- `POST /api/accounts`: Crear una cuenta.
- `GET /api/accounts/{id}`: Obtener una cuenta por ID.
- `PUT /api/accounts`: Actualizar una cuenta.
- `DELETE /api/accounts/{id}`: Eliminar una cuenta.
- `GET /api/accounts`: Listar todas las cuentas.

**Endpoints: transaction**
- `POST /api/transactions`: Crear una transacción.
- `GET /api/transactions/{id}`: Obtener una transacción por ID.
- `GET /api/transactions/clients/{clientId}/report`: Generar un reporte de "Estado de cuenta" especificando un rango de fechas y cliente.
- `GET /api/transactions/last/{accountId}`: Obtener la última transacción para un accountId específico.
- `GET /api/transactions`: Listar todas las transacción.

## Funcionalidades del API

### F1: Generación de CRUDs
- **Entidades:** Client, Account, Transaction.
- **Endpoints:** `/api/clients`, `/api/accounts`, `/api/transactions`.

### F2: Registro de Transacciones
Al registrar un movimiento en la cuenta, se debe tener en cuenta lo siguiente:
- Para un movimiento se pueden tener valores positivos o negativos.
- Al realizar un movimiento se debe actualizar el saldo disponible.
- Se debe llevar el registro de las transacciones realizadas.

### F3: Manejo de Saldo Insuficiente
- Al realizar un movimiento que no cuente con saldo, debe alertar mediante el mensaje **"Saldo no disponible"**.

### F4: Reporte de Estado de Cuenta
Generar un reporte de **"Estado de cuenta"** especificando un rango de fechas y cliente.

El reporte debe contener:
- Cuentas asociadas con sus respectivos saldos.
- Detalle de movimientos de las cuentas.

**Endpoint:**
```
/api/transacciones/clients/{clientId}/report?dateTransactionStart={startDate}&dateTransactionEnd={endDate}
```

### F5: Pruebas Unitarias
- Implementar al menos una prueba unitaria para la entidad de dominio `Client` en `sampleTest.java`.

## Instalación y Configuración

### Requisitos Previos
- Java 11 o superior.
- Maven 3.6 o superior.
- Docker (opcional, para ejecutar los microservicios en contenedores).

### Pasos de Instalación

#### Clonar el Repositorio:
```sh
git clone <URL_DEL_REPOSITORIO>
cd Microservices/BankApplication
```

#### Compilar los Microservicios:
Navega a cada microservicio y compila:
```sh
cd account
mvn clean install
cd ../client
mvn clean install
```

#### Ejecutar los Microservicios:
Puedes ejecutar los microservicios individualmente usando Maven:
```sh
cd account
mvn spring-boot:run
cd ../client
mvn spring-boot:run
```
O puedes usar Docker para ejecutar los microservicios en contenedores.


**Autor**: [TheDanilore].
