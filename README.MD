# Algoritmo de emparejamientos para el padel 

## Requisitos

- Tener instalado [Java 8]
- Tener instalado [Maven 3.xx]  

## Construcción 

Para construir el proyecto solo tenemos que lanzar el siguiente comando maven sobre el proyecto:

```mvn clean install```

esto nos genera el fichero **padel-1.0-0-SNAPSHOT.jar** en la carpeta target/

## Ejecución
Para ejecutar el programa ponemos el siguiente comando:

```java -jar padel-1.0-0-SNAPSHOT.jar Nombre1 Nombre2 Nombre3 Nombre4 Nombre5 Nombre6```

Empareja mínimo 5 jugadores y máximo 6. La lista de nombres admitidos por el programa son:
- Andres
- Garcho
- Hector
- Huertas
- Jorge
- Lax
![image](https://user-images.githubusercontent.com/28756802/110225612-893db580-7ee7-11eb-9bf3-32dc1d5ee2ef.png)

### Funcionamiento
Los criterios de emparejamiento seguidos son:
- Se calculan todas las parejas posibles de entre todos los que seamos para ese dia.
- Se reordenan de forma aleatoria y se van asignando partidos con la siguiente prioridad:
- Se examina que para el partido SIEMPRE entren los que en el anterior partido han DESCANSADO.
- Los jugadores que salgan serán aquellos que lleven más tiempo jugando
- Se intenta que la pareja que juegue no sea la misma que en el anterior partido
- Se asignará pareja local o pareja visitante de acuerdo a las veces que esa pareja haya sido local o visitante.
- La pareja local tiene la ventaja de sacar primero.
 
 
[Maven 3.xx]: https://maven.apache.org/index.html
[Java 8]: https://openjdk.java.net/
