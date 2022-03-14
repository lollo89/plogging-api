# Plogging api

> A simple rest api to collect information about a company plogging 'races'.

## Table of Contents

- [Getting Started](#getting-started)
  - [Installation](#installation)
  - [Usage](#usage)
- [Note](#note)

### Getting Started

#### Installation

1. Clone the repo:

    ```bash
    git clone https://github.com/lollo89/plogging-api.git
    ```

2. Go to the project folder:

    ```bash
    cd plogging-api
    ```

3. Build the application:

    ```bash
    docker build -t lasagni/plogging .
    ```

#### Usage

1. Run the application:

    ```bash
    docker run -p 8080:8080 lasagni/plogging
    ```

2. Go to [Swagger UI page](http://localhost:8080/swagger-ui.html)

### Note

The application load two set of example data.

- The first one is created thanks to [java-faker](https://github.com/DiUS/java-faker) for query purpose. All the races in this set have been run before 2022-01-01.
- The second set is created fot testing the 'race attended' method. The available employees are listed down here and the races has been run after 2022-01-01
  - g.cancelli@plogging.local
  - s.lavori@logging.local
