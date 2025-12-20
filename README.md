# Keroshi Blog

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Version](https://img.shields.io/badge/version-0.0.1SNAPSHOT1-orange.svg)
![Stars](https://img.shields.io/github/stars/kevinlikescodingmc/keroshiblog?style=flat&color=yellow)  
![JDK](https://img.shields.io/badge/JDK-24-green.svg)
![springboot](https://img.shields.io/badge/springboot-4.0.0-green.svg)
![spring](https://img.shields.io/badge/spring-7-green.svg)  
![bootstrap](https://img.shields.io/badge/bootstrap-5.3.3-brightgreen.svg)
![marked](https://img.shields.io/badge/marked-17.0.1-brightgreen.svg)
![katex](https://img.shields.io/badge/katex-0.16.27-brightgreen.svg)
![highlight](https://img.shields.io/badge/highlight-11.11.1-brightgreen.svg)
![codemirror](https://img.shields.io/badge/codemirror-6-brightgreen.svg)

Project URL：https://github.com/KevinLikesCodingMC/KeroshiBlog

A light personal blog system.

## Features

- Provides a complete executable JAR file for quick start

- Imports libraries including Bootstrap, KaTeX, marked, and CodeMirror

- Supports multiple databases

- Supports both Chinese and English

## Quick Start

### Dependencies

Install Java Development Kit 24 or higher installed.

Create a database. Supported databases include:

```
mysql
mariadb
oracle database
microsoft sqlserver
postgresql
h2database
```

### Download

Choose a version from the [releases](https://github.com/KevinLikesCodingMC/KeroshiBlog/releases) page
and download the JAR file.

### Configuration

Create a configuration file `blog.properties`. The file structure should look like:

```
KeroshiBlog/
├── KeroshiBlog.jar
└── blog.properties
```

Then edit the configuration. Here's an example:

```plain
blog.name=Keroshi Blog
blog.lang=zh

blog.su_username=admin
blog.su_password=password

server.port=80

spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=root
spring.datasource.password=password
```

### Running

Execute the following command:

```bash
java -jar KeroshiBlog.jar
```

## Documentation

Documentation is currently under development and will be available soon.
