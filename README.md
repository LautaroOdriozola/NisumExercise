
# Nisum Exercise

Exercise carried out as a technical test for Nisum Latam.


## API Reference

#### Create User

```http
  POST /user
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `String` | **Required**. |
| `email` | `String` | **Required**. |
| `password` | `String` | **Required**. |
| `phones` | `Array` | **Required**.|
| `phones.number` | `String` | **Required**.|
| `phones.cityCode` | `String` | **Required**.|
| `phones.countryCode` | `String` | **Required**.|

#### Login

```http
  POST /user/login
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email`   | `String` | **Required**. |
| `password`   | `String` | **Required**. |

#### Desactivate User

```http
  PUT /user/desactivate/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`   | `String` | **Required**. Id user to desactivate |

#### Change password regex

```http
  POST /passwordRegex
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `regex`   | `String` | **Required**. |



## Installation

Install the project with mvn and Java 8.

```bash
  cd NisumExercise/nisum
  mvn clean install
  mvn spring-boot:run
```

The port by default is 8002

Swagger Link: http://localhost:8002/swagger-ui.html

## Authors

- [@LautaroOdriozola](https://www.github.com/LautaroOdriozola)

