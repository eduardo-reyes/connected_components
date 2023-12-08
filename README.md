# Connected components | Componentes conexas

Implementación:
Dada una gráfica G con n vértices y e aristas, el programa utiliza DFS
para determinar el número de componentes conexas que la componen.
Como salida se imprimen el número de componentes conexas encontradas 
así como los vértices y aristas pertenecientes a cada una de las componentes 
conexas, diferenciando entre una componente y otra.

## Alumno: Eduardo Alfonso Reyes López

## Prerrequisitos

[Instalar Java] (https://www.java.com/es/download/help/download_options_es.html)

## Instalación

Descargar el comprimido ".zip" y descomprimir la carpeta.

## Pre-ejecución

El programa a implementar recibe como entrada en los argumentos
de la línea de comandos un archivo de texto tal que:

    1. En la primera línea tendrá todos los vértices que forman a G,
    separados por una coma (’,’).
    2. En las siguientes líneas irán pares de vértices, separados por
    una coma (’,’), que indicará en las aristas de G.

Colocar el archivo de texto que contenga la descripción de la gráfica en
la carpeta src/, tal como en los ejemplos.

El archivo debe contener en la primer línea los vértices que serán
únicamente números naturales empezando desde el 1. El orden de los
números no importa. Debe aparecer toda la secuencia de números
hasta el último natural.

EJEMPLOS
Válidos: "1,2,3,4,5,6", "3,2,1,4".
NO válidos: "2,3,4", "1,5,9", "a,1.2,#".

Nota: El cero ni otros carácteres están permitidos como vértices.

Se puede alterar la ruta de búsqueda en la variable path de la clase
ComponentesConexas.

Para ejecutar el programa sobre el archivo desdeado escribir 
explícitamente en línea de comandos "NombreArchivo.txt" cuando
salga el texto "Introduce el nombre del archivo: ".
Ejemplo: Escribir "Grafica1.txt".

Para ejecutar la demo sólo dar Enter. (Demo = "Grafica.txt").
En caso de no poder ejecutar el programa sobre su archivo, copiar
como texto plano la información sobre la gráfica al archivo
"Grafica.txt" y seguir la demo.

## Ejecución

En la carpeta principal ReyesEduardo/

Compilar -> javac -d . src/ComponentesConexas.java

Ejecutar -> java classes.src.ComponentesConexas