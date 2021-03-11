# TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER Y A AWS

# DESCRIPCION
El taller consiste en crear una aplicación web pequeña usando el micro-framework de Spark java (http://sparkjava.com/). Una vez tenemos esta aplicación procedemos a construir un container para docker para la aplicación y los desplegamos y configuramos en nuestra máquina local. Luego, cerremos un repositorio en DockerHub y subimos la imagen al repositorio. Finalmente, creamos una máquina virtual de en AWS, instalamos Docker , y desplegamos el contenedor que creamos.

# Pre requisitos

El estudiante conoce Java, Maven

El estudiante sabe desarrollar aplicaciones web en Java

Tiene instalado Docker es su máquina

# ENTREGABLE

1. El servicio MongoDB es una instancia de MongoDB corriendo en un container de docker en una máquina virtual de EC2

2. LogService es un servicio REST que recibe una cadena, la almacena en la base de datos y responde en un objeto JSON con las 10 ultimas cadenas almacenadas en la base de datos y la fecha en que fueron almacenadas.

3. La aplicación web APP-LB-RoundRobin está compuesta por un cliente web y al menos un servicio REST. El cliente web tiene un campo y un botón y cada vez que el usuario envía un mensaje, este se lo envía al servicio REST y actualiza la pantalla con la información que este le regresa en formato JSON. El servicio REST recibe la cadena e implementa un algoritmo de balanceo de cargas de Round Robin, delegando el procesamiento del mensaje y el retorno de la respuesta a cada una de las tres instancias del servicio LogService.

## DIAGRAMA DE LA IMPLEMENTACION

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/0.PNG)

# DISEÑO

- LogService

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/diagrama1.PNG)

- RoundRobin-LB

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/diagrama2.PNG)

# EVIDENCIAS

Se implemento el "LogService" encargado de la logica de la aplicacion y persistencia con la BD mongo (corriendo en la instancia ec2) y el "RoundRobin-LB" que es el balanceador de carga implemntado para optimizar las peticiones en los 3 servicios de LogService que se corrieron en la instancia ec2 en los puertos 35001, 35002 y 35003 respectivamente.

- SERVICIOS CORRIENDO EN EC2

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/10.PNG)

- LogService1 35001 (primer servicio corriendo en ec2) con prueba de funcionamiento

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/1.PNG)

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/2.PNG)

- LogService2 35002 (segundo servicio corriendo en ec2) con prueba de funcionamiento

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/3.PNG)

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/4.PNG)

- LogService3 35003 (tercer servicio corriendo en ec2) con prueba de funcionamiento

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/5.PNG)

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/6.PNG)

Posteriormente se implemento el Load Balancer y se corrio en la insancia por el puerto 20000

-  RoundRobin-LB

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/7.PNG)

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/8.PNG)

Evidencia de repositorios implementados

![](https://github.com/aosfandres/AREP-Lab5/blob/master/images/9.PNG)


# DOCUMENTACION (JAVADOC)

Para generar la documentacion con maven usar ```mvn javdoc:javadoc``` en consola

[JAVADOC-LogService](https://github.com/aosfandres/AREP-Lab5/blob/master/LogService/javadoc/index.html)

[JAVADOC-RoundRobin-LB](https://github.com/aosfandres/AREP-Lab5/blob/master/RoundRobin-LB/javadoc/index.html)

# DOCUMENTO LATEX
[TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER Y A AWS](https://github.com/aosfandres/AREP-Lab5/blob/master/LatexDocument.pdf)

# AUTOR
Andres Orlando Sotelo Fajardo 

# LICENCIA

[LICENSE](https://github.com/aosfandres/AREP-Lab5/blob/master/LICENSE)