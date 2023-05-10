# Reto-PeriferiaItGroup-MutantHuman

Proyecto Reto Indentificar mediante array String dna genético si es perteneciente a Humano o Mutante con Estadisticas.

Se desarrollo usando Spring Boot v2.6.4, java 17, Maven, Mysql 8, Docker v20.10.23 con docker-compose v2.15.1, Docker Hub y EC2 de AWS para el despliegue y testing con junit.
```
cd Reto-PeriferiaItGroup-MutantHuman
docker-compose up -d
```

En application.properties dentro de src -> main -> resources cambiar la variable de ```spring.datasource.password``` y ```spring.datasource.username``` si es el caso con los valores de acceso de su mysql local.

Para prueba contenedores Docker cambiar localhost por mysqlDB (nombre servicio mysql en Docker-compose.yml) así: ```spring.datasource.url=jdbc:mysql://mysqlDB:3306/mutant?createDatabaseIfNotExist=true```

No Mutante Status 403

![image](https://github.com/AlejandroHack1/Reto-PeriferiaItGroup-MutantHuman/assets/17314013/a3e4dc3a-f469-4b37-b9bb-79a5bc6ca8cc)

Status: 200 es Mutante

![image](https://github.com/AlejandroHack1/Reto-PeriferiaItGroup-MutantHuman/assets/17314013/d04567fe-d30a-4ef5-a674-0fa8ea515a69)


Verifica no almacenar la misma secuencia de ADN

![image](https://github.com/AlejandroHack1/Reto-PeriferiaItGroup-MutantHuman/assets/17314013/4f35be2a-ab05-4520-9a1c-ed2be5b513b3)

Obtiene estadísticas.

![image](https://github.com/AlejandroHack1/Reto-PeriferiaItGroup-MutantHuman/assets/17314013/d7ab2b5c-aa2b-4ea0-ab06-81c893e4a798)

