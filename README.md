# 🗄️ Ejercicio7 [ConexionOracleMaven] — CRUD Modular Completo

Este proyecto Java gestionado con Maven implementa un sistema de gestión integral (CRUD) para una base de datos Oracle. La aplicación utiliza un diseño modular donde cada operación de base de datos está separada en su propia clase, facilitando la legibilidad y el mantenimiento del código.

## 📋 Descripción del Proyecto
La aplicación permite administrar registros de la tabla `empleado` mediante un menú interactivo en la consola. El sistema incluye validaciones para evitar datos incorrectos (como salarios negativos) y maneja las conexiones de forma segura mediante configuración externalizada.

## 🎯 Funcionalidades del Menú
El programa ofrece las siguientes opciones de gestión:
1.  **Insertar un nuevo empleado**: Permite añadir registros solicitando nombre y salario, autocalculando el siguiente ID disponible.
2.  **Mostrar todos los empleados**: Genera un listado completo de la tabla directamente desde Oracle.
3.  **Actualizar un empleado**: Permite modificar selectivamente el nombre o el salario de un empleado existente mediante su ID.
4.  **Eliminar un empleado**: Borra registros de forma definitiva tras verificar que el ID introducido existe.
0.  **Salir**: Finaliza la ejecución del programa y cierra el escáner de entrada.

## 🏗️ Estructura del Proyecto
Basado en la arquitectura Maven y la organización multiclase del paquete `org.example`:

```text
Ejercicio7 [ConexionOracleMaven]/
│
├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/
│       │   └── 📁 org/example/
│       │       ├── ☕ Main.java            # Clase principal y flujo del menú
│       │       ├── ☕ Consulta.java        # Métodos de lectura y validación de IDs
│       │       ├── ☕ Insertar.java        # Lógica para añadir nuevos registros
│       │       ├── ☕ Actualizar.java      # Lógica para modificar registros existentes
│       │       ├── ☕ Eliminar.java        # Lógica para remover registros por ID
│       │       └── ☕ DBConfig.java        # Configuración de conexión JDBC
│       └── 📁 resources/
│           └── 📄 db.properties           # Archivo de propiedades (URL, Usuario, Password)
│
├── 📁 target/                             # Archivos compilados y recursos procesados
├── 📄 pom.xml                             # Archivo de configuración de Maven y dependencias
└── 📄 README.md                           # Documentación del proyecto
```

## 📄 Formato del Archivo de Entrada
Para que la conexión funcione, el archivo `src/main/resources/db.properties` debe contener:
```properties
db.url=jdbc:oracle:thin:@localhost:1521:xe
db.user=tu_usuario
db.password=tu_contraseña
```

## 🚀 Compilación y Ejecución
### Requisitos
*   Java JDK 17 o superior.
*   Maven 3.8+ instalado.
*   Base de datos Oracle configurada con la tabla `empleado`.

### Comandos de Terminal
```bash
# Limpiar y compilar el proyecto
mvn clean compile

# Ejecutar la aplicación principal
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## 🔧 Características Técnicas Implementadas
*   **Separación de Responsabilidades**: Cada clase del paquete `org.example` tiene una función única y clara.
*   **Sentencias Preparadas**: Uso de `PreparedStatement` para todas las operaciones de escritura (Insert, Update, Delete) para evitar inyección SQL.
*   **Manejo de Excepciones**: Control de `InputMismatchException` para entradas de teclado no válidas y `SQLException` para errores de base de datos.
*   **Lógica de Negocio**: Validación de salarios positivos y comprobación de existencia de registros antes de operar sobre ellos.

## 🎮 Ejemplo de Uso Visual
**Interacción típica:**
```text
Elija una opción.
1.Insertar un nuevo empleado
2.Mostrar todos los empleado
3.Actualizar un empleado
4.Eliminar un nuevo empleado
0.Salir
> 2
ID: 1, Nombre: Judith, Salario: 3500.0
ID: 10, Nombre: Sergio, Salario: 2355.15
```

---
**Autor:** Judith Olmedo Andrés  
*Ejercicio 7 - Desarrollo de Sistemas de Persistencia con Java y Oracle.*