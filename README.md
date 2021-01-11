# Подключение PostgreSQL через Docker

Устанавливаем Docker с [официального сайта](https://docs.docker.com/get-docker/) 

Далее открываем терминал и проверяем что Docker установлен, доступен и смотрим его версию: 
```
docker -v
```

Установку PostgreSQL, создание контейнера и его запуск можно совместить в одну большую, но вполне понятную команду
```
docker run --name trade -e POSTGRES_DB=trade_crm -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -d -p 5437:5432 postgres
```

- run - запуск нового контейнера

- --name - имя контейнера

- -e - задаём переменную контейнера

    - POSTGRES_BD=trade_crm - имя базы данных
    
    - POSTGRES_USER=root - имя пользователя
    
    - POSTGRES_PASSWORD=root - пароль пользователя

- -d - запуск контейнера в фоновом режиме

- -p 5432:5432 - порты для соединения локального ресурса:контейнера

Для запуск и остановки контейнера используем команды:

```
docker start trade

docker stop trade
```
Далее производим подключение к контейнеру с запуском утилиты bash
```
docker exec -it trade bash
```
---
#Подключение и создание базы данных
Подключаемся к базе данных

```
psql -h localhost -p 5432 -U root
```
- -h - имя хоста

- -p - порт подключения

- -U - имя пользователя
	
Теперь находимся в самой базе данных

Отобразить список БД:
```
\l
```

Создание базы данных:

```
create database trade_crm
```

Коннектимся с базой данных:

```
\c trade_crm
```

Дисконнект с базой данных

```
\q
```
--------------
#Настройка базы данных в IDEA

Справой стороны открываем вертикальную вкладку ***Database***<br/>
Далее по цепочке "+" -> Data Source -> PostgreSQL

В поля User, Password и Database вводим соответствующие значения, что вводили ранее. После этого нажимаем кнопку "Test Connection" и если видим зелёную галочку, то всё OK! 

#Сохранение бэкапа базы данных из Docker контейнера

Делаем резервную копию 
```
docker exec trade pg_dump -u root --password=root trade-crm | gzip > trade-crm.sql.gz
Для винды
docker exec -it trade pg_dump -u root trade-crm > {свой путь строчными символами}\trade-crm.sql
```
- exec - выполнение команды внутри контейнера
- pg_dump - дамп базы данных
- -u - пользователь
- -password - пароль

Восстановливаем базу данных в контейнере из дампа:
```
gunzip < trade-crm.sql.gz | docker exec -i trade psql -U root -d trade_crm
Для винды:
docker exec -i trade psql -U root -d trade_crm < {свой путь строчными символами}\trade-crm.sql
```