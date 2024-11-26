# Gestión de Productos - Aplicación Android

## Descripción del Proyecto

Esta aplicación Android es una herramienta sencilla para la gestión de productos, diseñada como parte de una evaluación. Permite al usuario realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre una lista de productos almacenada en una base de datos SQLite. Además, incluye funciones de búsqueda y ordenamiento de productos por nombre.

---

## Características

1. **Agregar productos**: Se pueden añadir nuevos productos introduciendo su SKU y nombre.  
2. **Editar productos**: Es posible modificar el nombre de un producto existente seleccionándolo desde la lista.  
3. **Eliminar productos**: Permite eliminar productos seleccionados por su SKU.  
4. **Listar productos**: Los productos se muestran en un `ListView`.  
5. **Ordenar productos**: Función para ordenar los productos alfabéticamente de Z a A.  
6. **Validación de SKU único**: Evita agregar productos con un SKU ya existente y muestra un mensaje de error mediante un `Toast`.  
7. **Selección de producto**: Al seleccionar un producto del `ListView`, sus datos se cargan automáticamente en los campos de edición para facilitar su actualización o eliminación.

---

## Tecnologías Utilizadas

- **Lenguaje**: Java.
- **Base de datos**: SQLite.
- **Interfaz de usuario**: XML con `LinearLayout` y componentes como `EditText`, `Button` y `ListView`.

---

## Estructura del Proyecto

### 1. **DBHelper.java**
Clase encargada de manejar la base de datos SQLite. Proporciona métodos para:

- Crear y actualizar la base de datos.
- Agregar, editar, eliminar y buscar productos.
- Validar la existencia de un SKU único.

### 2. **MainActivity.java**
Actividad principal de la aplicación, que:

- Conecta los botones y componentes de la interfaz gráfica.
- Gestiona las operaciones de CRUD mediante los métodos de `DBHelper`.
- Actualiza el `ListView` utilizando un adaptador personalizado.

### 3. **ProductoAdapter.java**
Clase personalizada para adaptar los datos de la base de datos a la vista `ListView`.

### 4. **Diseño XML**
El diseño principal (`activity_main.xml`) contiene:

- **Campos de entrada**:
  - `EditText` para SKU y nombre del producto.
- **Botones**:
  - Botones para las operaciones CRUD y ordenamiento.
- **Lista de productos**:
  - `ListView` para mostrar los productos registrados.

---

## Cómo Usar la Aplicación

1. **Agregar un producto**:
   - Introducir un SKU único y un nombre.
   - Presionar el botón *Insertar*.

2. **Editar un producto**:
   - Seleccionar un producto de la lista para cargar sus datos en los campos.
   - Modificar el nombre en el campo correspondiente.
   - Presionar el botón *Editar*.

3. **Eliminar un producto**:
   - Seleccionar un producto de la lista para cargar sus datos.
   - Presionar el botón *Eliminar*.

4. **Buscar un producto**:
   - Introducir el nombre (o parte del nombre) del producto en el campo de texto.
   - Presionar el botón *Buscar* para filtrar la lista.

5. **Ordenar de Z a A**:
   - Presionar el botón *Ordenar de Z a A* para reorganizar los productos.

---

## Captura de Pantalla

[app.png](https://postimg.cc/xkGHxFBf)

---

## Requisitos del Sistema

- **Android SDK**: Nivel mínimo 21 (Android 5.0 Lollipop).
- **IDE**: Android Studio.
- **Lenguaje de Programación**: Java.

---

## Autor

Desarrollado como parte de una evaluación de Android.  
