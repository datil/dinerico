# Dinérico

Dinérico permite a un comerciante gestionar su negocio desde su teléfono Android.

## Funciones principales

* Catálogo de productos.
* Facturación electrónica.


## Arquitectura de la Aplicación

### Servicios Web

Todos los servicios web se acceden mediante una conexión segura, siguen el estándar REST, y para el intercambio de información se utiliza el formato JSON.
JSON provee flexibilidad y es apto para aplicaciones móviles por ser un formato compacto.


### Almacenamiento

La información ingresada es registrada en una base de datos SQLite para permitir una experiencia fluida.
Posteriormente la información es enviada a nuestros servidores para mantener la cuenta sincronizada.

### Diseño en capas

Cada funcionalidad está implementada siguiendo el patrón de diseño en capas MVVM
(Model-View-ViewModel) que permite mantener y escalar el sistema rapidamente.

### Programación Reactiva (Functional Reactive Programming)

Utilizando la librería [RxJava](https://github.com/ReactiveX/RxJava) la administración de procesos asincrónicos se simplifica. 

## Compatibilidad

Dinérico es compatible con cualquier smartphone Android.
