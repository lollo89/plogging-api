# Plogging api

> A simple rest api to collect information about a company plogging 'races'.

## Table of Contents

- [Getting Started](#getting-started)
- [Come contribuire](#come-contribuire)
- [Manutenzione](#manutenzione)
- [Licenza](#licenza)

### Getting Started

#### Installation

1. Clone the repo:

    ```git
    git clone https://github.com/lollo89/plogging-api.git
    ```

2. Build the application:

    ```bash
    docker build -t lasagni/plogging .
    ```

#### Usage

1. Run the application:

    ```bash
    docker run -p 8080:8080 lasagni/plogging
    ```

2. Go to [Swagger UI page](http://localhost:8080/swagger-ui.html)

3. Available employees are:
    - lorenzo@lasagni.test
    - simone@lasagni.test
    - giacomo@lasagni.test
    - giovanni@lasagni.test
    - marco@lasagni.test
    - luca@lasagni.test
