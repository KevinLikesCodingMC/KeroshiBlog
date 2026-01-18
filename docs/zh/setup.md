# Keroshi Blog 搭建

![Version](https://img.shields.io/badge/version-0.0.1SNAPSHOT3-orange.svg)

本篇文章将讲解如何搭建 Keroshi Blog。

---

## 环境依赖

### 1. Java

安装 **JDK 24** 或更高版本。

### 2. 数据库

Keroshi Blog 支持以下数据库：

```text
MySQL
MariaDB
PostgreSQL
Oracle Database
Microsoft SQL Server
H2 Database
```

然后创建一个空的数据库，例如命名为 `keroshi`。

---

## 下载

前往 [Releases](https://github.com/KevinLikesCodingMC/KeroshiBlog/releases)
页面下载 `v0.0.1-SNAPSHOT3` 版本的压缩包。

解压后，目录结构会类似这样：

```text
KeroshiBlog/
├── KeroshiBlog.jar
├── blogdata/
├── keroshi/   
├── docs/
├── blog.properties
├── README.md
└── LICENSE
```

---

## 配置

编辑 `blog.properties` 文件。

这里给出一个示例：

```properties
blog.name=Keroshi Blog
blog.lang=zh

blog.su_username=admin
blog.su_password=your_secure_password_here

blog.data_path=blogdata

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/keroshi
spring.datasource.username=root
spring.datasource.password=your_db_password
```

## 启动

在根目录下执行以下命令即可：

```bash
java -jar KeroshiBlog.jar
```


