<p align="center">
    <picture>
      <source media="(prefers-color-scheme: dark)" srcset="https://jaya.tech/images/logo-white.png" />
      <source media="(prefers-color-scheme: light)" srcset="https://jaya.tech/images/logo-black.png" />
      <img alt="logo" src="https://jaya.tech/images/logo-black.png" />
    </picture>
</p>

## Table of contents
* [Getting Started](#getting-started)
  * [API](#api)
* [Requirements](#requirements)
* [Install](#install)
  * [Config](#config)
* [Start](#start)
* [Technologies](#technologies)
  
## Getting Started
**Jaya Exchange Java** it's a currency exchange rates Rest API wrote using [Java](https://www.java.com) and [Spring Boot](https://spring.io/projects/spring-boot).

To access real-time exchange rates we use the [Exchange Rates Api](https://exchangeratesapi.io/) and we support **all** currencies supported by then.

The code is divided in the model, control and service layers.

### API
Our API counts with the following endpoints:

- \[POST\] **/transactions** - Create a transaction:
```js
// Request example:
{
  "userId": 1,
  "from": "USD",
  "amount": 5,
  "to": "BRL"
}

// Response example:
// HTTP 201
{
  "id": 252,
  "userId": 1,
  "from": "USD",
  "amount": 5.0,
  "to": "BRL",
  "rate": 5.424254892758378,
  "createdAt": "2024-07-14T23:04:46.646365Z",
  "result": 27.121274463791888
}

// HTTP 422
{
  "errors": [
    {
      "field": "amount",
      "message": "must be greater than 0"
    }
  ]
}
```

- \[​GET\] **/transactions/{userId}** - Transactions by userId paginated list:
```js
// Response example:
// HTTP 200
{
  "_embedded": {
    "transactionList": [
      {
        "id": 1,
        "userId": 1,
        "from": "USD",
        "amount": 2.345,
        "to": "BRL",
        "rate": 5.429301244624819,
        "createdAt": "2024-07-13T16:54:17.694103Z",
        "result": 12.731711418645201
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost/transactions/1?page=1&size=20"
    }
  },
  "page": {
    "size": 20,
    "totalElements": 1,
    "totalPages": 1,
    "number": 1
  }
}
```

Default base url: [http://localhost](http://localhost)

## Requirements
- [docker](https://docs.docker.com/engine/install)
- [docker compose](https://docs.docker.com/compose/install)

## Install
Once on the application folder you have two options:
1. Run the install script using [make](https://www.gnu.org/software/make/): 
```sh
make install
```
2. Run the script lines by yourself:
```sh
# build
docker run --rm -it -v .:/app -v maven:/root/.m2 -w /app maven ./mvnw clean install
# create .env file
cp .env.example .env
```

### Config
After install you can set your [Exchange Rates Api](https://exchangeratesapi.io) access key by editing the **exchange_rates_api.access_key** config on the application.properties file but thats not mandatory.

You can also edit the others configs on the .env file and application.properties file if you want.

## Start
Once installed just run:
```sh
docker compose up
```
And wait the application start, then you can access all the applications links listed on the sections above.

## Technologies
- Language: **[Java 17](https://www.java.com)**
- Framework: **[Spring Boot 3.3](https://spring.io/projects/spring-boot)**
- Database: **[PostgreSQL](https://www.postgresql.org)**
- Linter: **[Spring Java Format](https://github.com/spring-io/spring-javaformat)**
- Containerization: **[Docker](https://www.docker.com)**
- And more...
