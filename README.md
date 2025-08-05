# Camel Demo
This is a Spring Boot application using Apache Camel that proxies public REST endpoints from `https://jsonplaceholder.typicode.com`.

## ✅ Features

- Built with **Apache Camel 4** and **Spring Boot 3**
- Uses **Netty HTTP** as the REST component
- Logs response using a Camel processor
- Routes for:
    - `/posts`
    - `/users`
    - `/comments`
    - `/todos`
    - `/photos`
