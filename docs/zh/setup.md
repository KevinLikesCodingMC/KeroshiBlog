# 🚀 Keroshi Blog 部署指南

![Version](https://img.shields.io/badge/version-0.0.1SNAPSHOT3-orange.svg)

感谢使用 Keroshi Blog。本文档将指引你完成基础环境的搭建与启动。

---

## 🛠 环境依赖

在开始部署前，请确保你的服务器环境满足以下要求：

### 1. Java 环境

* **要求**: 安装 **JDK 24** 或更高版本。
* **验证**: 执行 `java -version` 确认版本号。

### 2. 数据库支持

Keroshi Blog 使用了高度兼容的持久层设计，支持以下数据库：

* **主流驱动**: MySQL, MariaDB, PostgreSQL
* **企业级**: Oracle Database, Microsoft SQL Server
* **轻量级**: H2 Database (适合快速测试)

> 请预先创建一个空的数据库实例（例如命名为 `keroshi_blog`）。

---

## 📥 下载与解压

1. 前往 [Releases](https://github.com/KevinLikesCodingMC/KeroshiBlog/releases) 页面。
2. 选择最新版本（推荐 `v0.0.1-SNAPSHOT3`）下载压缩包。
3. 解压后，你将获得如下目录结构：

```text
KeroshiBlog/
├── KeroshiBlog.jar      # 程序核心文件
├── blogdata/            # 博客静态资源
├── keroshi/             # 博客静态资源配置参考      
└── blog.properties      # 核心配置文件（数据库、端口等）
```

---

## ⚙️ 配置

编辑 `blog.properties` 文件，重点配置以下项：

1. **数据库链接**: 填入你的数据库 URL、用户名和密码。
2. **管理员设置**: **修改默认的超级管理员用户名和密码。**
3. **端口号**: 默认 `8080`，可根据需要更改。

## ⚠️ 重要安全警告

> **请务必在启动前修改 `blog.properties` 中的超级管理员账户与密码！**
>
> 默认配置文件中包含了初始的管理员凭据，若不修改直接部署到公网，将面临极大的安全风险。

---


## 🚀 启动

在根目录下执行：

```bash
java -jar KeroshiBlog.jar
```

启动成功后，通过 `http://服务器IP:端口` 即可访问。

---

## ⚠️ 重要说明

> **数据备份提示**:
> 当前版本为 `SNAPSHOT`（快照版），意味着它仍处于快速迭代中。未来的更新**可能会修改数据库结构**。
> **在执行任何版本升级前，请务必手动备份您的数据库和 `blogdata` 目录！**

