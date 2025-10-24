
本文档涵盖项目核心信息、技术栈、结构设计、核心功能及从环境准备到运行的全流程配置，可直接用于开发参考、项目交接或代码仓库说明。

## 一、项目概述

MyDemoly\_2 是一个基于 **Java + MyBatis** 开发的 **数据库关联关系演示项目**，核心目标是直观展示数据库中「一对一、一对多、多对多」三种经典关联关系的设计与查询实现。

适合 Java 后端初学者学习 ORM 框架使用、数据库关联逻辑设计及 MyBatis 映射配置，也可作为小型项目的关联查询模块参考模板。

## 二、技术栈选型



| 模块     | 技术 / 工具           | 版本要求   | 说明                 |
| ------ | ----------------- | ------ | ------------------ |
| 编程语言   | Java              | 17 及以上 | 项目核心开发语言           |
| 构建工具   | Maven             | 3.6+   | 依赖管理、项目编译与打包       |
| ORM 框架 | MyBatis           | 3.5.2  | 实现 Java 与数据库的交互    |
| 数据库    | MySQL             | 8.0+   | 存储项目数据及关联关系        |
| 数据库驱动  | mysql-connector-j | 8.0.31 | Java 连接 MySQL 的驱动包 |
| 开发工具   | IntelliJ IDEA（推荐） | 2022+  | 代码编写、调试与项目管理       |

## 三、项目结构



```
MyDemoly\_2/                  # 项目根目录

├── .gitignore               # Git 忽略配置（过滤 target、IDE 配置等无关文件）

├── pom.xml                  # Maven 核心配置（依赖管理、构建规则）

├── .git/                    # Git 版本控制目录（若使用 Git 管理代码）

├── src/

│   └── main/

│       ├── java/            # Java 源代码目录（按包名分层）

│       │   └── com/

│       │       └── cduestc/

│       │           ├── Main.java          # 项目主入口（测试关联查询逻辑）

│       │           ├── dao/               # 数据访问层（DAO 接口，定义查询方法）

│       │           │   ├── PersonDao.java

│       │           │   ├── CardDao.java

│       │           │   ├── ClazzDao.java

│       │           │   ├── StudentDao.java

│       │           │   └── SubjectDao.java

│       │           ├── mapper/            # MyBatis Mapper 接口（可选，用于接口绑定）

│       │           └── pojo/              # 实体类（与数据库表一一对应）

│       │               ├── Person.java    # 人员实体（一对一关联 Card）

│       │               ├── Card.java      # 卡片实体（一对一关联 Person）

│       │               ├── Clazz.java     # 班级实体（一对多关联 Student）

│       │               ├── Student.java   # 学生实体（多对多关联 Subject）

│       │               └── Subject.java   # 课程实体（多对多关联 Student）

│       └── resources/       # 资源文件目录（配置文件、Mapper 映射文件）

│           ├── db.properties              # 数据库连接配置（用户名、密码、URL 等）

│           ├── mybatis.config.xml         # MyBatis 核心配置（环境、映射器扫描）

│           └── com/

│               └── cduestc/

│                   └── mapper/            # MyBatis 映射文件（编写 SQL 语句）

│                       ├── PersonMapper.xml

│                       ├── CardMapper.xml

│                       ├── ClazzMapper.xml

│                       ├── StudentMapper.xml

│                       └── SubjectMapper.xml

├── .idea/                   # IntelliJ IDEA 项目配置目录（IDE 自动生成）

└── target/                  # Maven 构建输出目录（编译后的 class 文件、Jar 包）
```

## 四、核心功能

项目围绕数据库三种关联关系设计，通过 MyBatis 实现关联查询，具体功能如下：

### 1. 一对一关联（Person ↔ Card）



* **业务逻辑**：一个人员（Person）对应唯一一张卡片（Card），一张卡片仅归属一个人员（通过 `person_id` 唯一关联）。

* **实现方式**：在 `PersonMapper.xml` 和 `CardMapper.xml` 中，通过 `association` 标签配置关联映射，查询时自动关联另一张表的数据。

* **测试入口**：`Main.java` 中调用 `PersonDao.getPersonWithCard(Integer id)`，查询人员时同时返回关联的卡片信息（如 “张三的卡片编号：C2024001”）。

### 2. 一对多关联（Clazz ↔ Student）



* **业务逻辑**：一个班级（Clazz）包含多个学生（Student），一个学生仅属于一个班级（通过 `clazz_id` 关联）。

* **实现方式**：在 `ClazzMapper.xml` 中，通过 `collection` 标签配置学生列表映射，查询班级时批量获取下属所有学生。

* **测试入口**：`Main.java` 中调用 `ClazzDao.getClazzWithStudents(Integer id)`，返回班级及关联的学生列表（如 “Java 开发班的学生：张三、李四”）。

### 3. 多对多关联（Student ↔ Subject）



* **业务逻辑**：一个学生（Student）可选择多门课程（Subject），一门课程可被多个学生选择（通过中间表 `student_subject` 建立关联）。

* **实现方式**：在 `StudentMapper.xml` 和 `SubjectMapper.xml` 中，通过多表联查（关联 `student_subject` 中间表）实现双向关联查询。

* **测试入口**：`Main.java` 中调用 `StudentDao.getStudentWithSubjects(Integer id)`（查询学生所选课程），或 `SubjectDao.getSubjectWithStudents(Integer id)`（查询课程的选课学生）。

## 五、项目配置与运行步骤

### 1. 环境准备

#### （1）安装 JDK



1. 下载 Java 17：推荐 [Oracl](https://www.oracle.com/java/technologies/downloads/)[e JDK](https://www.oracle.com/java/technologies/downloads/) 或 [Op](https://adoptium.net/)[enJDK](https://adoptium.net/)。

2. 配置环境变量 `JAVA_HOME`（指向 JDK 安装目录，如 `C:\Program Files\Java\jdk-17`）。

3. 验证配置：打开终端执行以下命令，输出 “java version "17.xxx"” 即成功：



```
java -version
```

#### （2）安装 Maven



1. 下载 Maven：前往 [Mav](https://maven.apache.org/download.cgi)[en 官网](https://maven.apache.org/download.cgi)，选择 “Binary zip archive” 版本。

2. 配置环境变量 `MAVEN_HOME`（指向 Maven 安装目录，如 `D:\apache-maven-3.9.6`）。

3. 验证配置：打开终端执行以下命令，输出 Maven 版本信息（如 3.9.6）即成功：



```
mvn -version
```

#### （3）安装 MySQL



1. 安装 MySQL 8.0+：推荐使用 [MyS](https://dev.mysql.com/downloads/installer/)[QL In](https://dev.mysql.com/downloads/installer/)[stall](https://dev.mysql.com/downloads/installer/)[er](https://dev.mysql.com/downloads/installer/)（Windows）或 Docker 部署（Linux/macOS）。

2. 记录核心信息：数据库地址（默认 `localhost:3306`）、用户名（如 `root`）、密码（自定义，需牢记）。

### 2. 项目初始化

#### （1）获取项目代码



* 若使用 Git，克隆仓库：



```
git clone <项目仓库地址>  # 替换为实际仓库地址
```



* 若为压缩包，下载后解压到本地目录（确保路径无中文 / 特殊字符，避免编译异常）。

#### （2）创建数据库



1. 打开 MySQL 终端（或使用 Navicat、DataGrip 等工具），登录后执行命令创建项目专用数据库（编码为 `utf8mb4`，支持中文）：



```
CREATE DATABASE javaee CHARACTER SET utf8mb4 COLLATE utf8mb4\_unicode\_ci;
```

### 3. 核心配置

#### （1）配置数据库连接



1. 打开项目中的 `src/main/resources/db.properties` 文件，替换为你的 MySQL 实际信息：



```
\# 数据库驱动（MySQL 8.0+ 必须使用 com.mysql.cj.jdbc.Driver，旧版本用 com.mysql.jdbc.Driver）

jdbc.driver=com.mysql.cj.jdbc.Driver

\# 数据库 URL（替换 localhost:3306 为你的地址，javaee 为数据库名；useSSL=false 关闭 SSL 验证，适合开发环境）

jdbc.url=jdbc:mysql://localhost:3306/javaee?useSSL=false\&serverTimezone=UTC

\# MySQL 用户名（替换为你的用户名，如 root）

jdbc.username=root

\# MySQL 密码（替换为你的密码，如 123456）

jdbc.password=123456
```

#### （2）验证 MyBatis 配置



1. 打开 `src/main/resources/mybatis.config.xml`，确认配置与 `db.properties` 一致，且映射器路径正确：



```
\<?xml version="1.0" encoding="UTF-8"?>

\<!DOCTYPE configuration

&#x20;       PUBLIC "-//mybatis.org//DTD Config 3.0//EN"

&#x20;       "http://mybatis.org/dtd/mybatis-3-config.dtd">

\<configuration>

&#x20;   \<!-- 加载数据库配置文件（db.properties） -->

&#x20;   \<properties resource="db.properties"/>

&#x20;   \<!-- 配置数据库环境（默认使用开发环境） -->

&#x20;   \<environments default="development">

&#x20;       \<environment id="development">

&#x20;           \<!-- 事务管理方式：JDBC（依赖数据库自身事务） -->

&#x20;           \<transactionManager type="JDBC"/>

&#x20;           \<!-- 数据源：POOLED（使用连接池，提升性能） -->

&#x20;           \<dataSource type="POOLED">

&#x20;               \<property name="driver" value="\${jdbc.driver}"/>

&#x20;               \<property name="url" value="\${jdbc.url}"/>

&#x20;               \<property name="username" value="\${jdbc.username}"/>

&#x20;               \<property name="password" value="\${jdbc.password}"/>

&#x20;           \</dataSource>

&#x20;       \</environment>

&#x20;   \</environments>

&#x20;   \<!-- 配置映射器（扫描 Mapper.xml 文件，路径需与实际文件一致） -->

&#x20;   \<mappers>

&#x20;       \<mapper resource="com/cduestc/mapper/PersonMapper.xml"/>

&#x20;       \<mapper resource="com/cduestc/mapper/CardMapper.xml"/>

&#x20;       \<mapper resource="com/cduestc/mapper/ClazzMapper.xml"/>

&#x20;       \<mapper resource="com/cduestc/mapper/StudentMapper.xml"/>

&#x20;       \<mapper resource="com/cduestc/mapper/SubjectMapper.xml"/>

&#x20;   \</mappers>

\</configuration>
```

### 4. 导入数据库表结构与测试数据

#### （1）创建表结构

登录 MySQL 终端，切换到 `javaee` 数据库，执行以下 SQL 脚本创建表（含关联关系）：



```
USE javaee;

\-- 1. 一对一关联：Person 表（人员）

CREATE TABLE person (

&#x20;   id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   name VARCHAR(50) NOT NULL,  -- 姓名

&#x20;   age INT                     -- 年龄

);

\-- 一对一关联：Card 表（卡片，关联 person.id）

CREATE TABLE card (

&#x20;   id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   card\_num VARCHAR(20) NOT NULL UNIQUE,  -- 卡片编号（唯一）

&#x20;   person\_id INT UNIQUE,                  -- 关联人员 ID（唯一，确保一对一）

&#x20;   FOREIGN KEY (person\_id) REFERENCES person(id) ON DELETE CASCADE  -- 级联删除（人员删除时，卡片同步删除）

);

\-- 2. 一对多关联：Clazz 表（班级）

CREATE TABLE clazz (

&#x20;   id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   class\_name VARCHAR(50) NOT NULL UNIQUE  -- 班级名称（唯一）

);

\-- 一对多关联：Student 表（学生，关联 clazz.id）

CREATE TABLE student (

&#x20;   id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   name VARCHAR(50) NOT NULL,  -- 学生姓名

&#x20;   clazz\_id INT,               -- 关联班级 ID（一个班级对应多个学生）

&#x20;   FOREIGN KEY (clazz\_id) REFERENCES clazz(id) ON DELETE SET NULL  -- 班级删除时，学生的 clazz\_id 设为 NULL

);

\-- 3. 多对多关联：Subject 表（课程）

CREATE TABLE subject (

&#x20;   id INT PRIMARY KEY AUTO\_INCREMENT,

&#x20;   subject\_name VARCHAR(50) NOT NULL UNIQUE,  -- 课程名称（唯一）

&#x20;   score INT                                  -- 学分

);

\-- 多对多中间表：student\_subject（关联 student.id 和 subject.id）

CREATE TABLE student\_subject (

&#x20;   student\_id INT,

&#x20;   subject\_id INT,

&#x20;   PRIMARY KEY (student\_id, subject\_id),  -- 联合主键，避免重复关联

&#x20;   FOREIGN KEY (student\_id) REFERENCES student(id) ON DELETE CASCADE,  -- 级联删除（学生删除时，关联记录同步删除）

&#x20;   FOREIGN KEY (subject\_id) REFERENCES subject(id) ON DELETE CASCADE   -- 级联删除（课程删除时，关联记录同步删除）

);
```

#### （2）插入测试数据（可选）

为便于验证关联查询功能，可插入以下测试数据：



```
\-- 插入班级数据

INSERT INTO clazz (class\_name) VALUES ('Java 开发班'), ('Python 数据分析班');

\-- 插入学生数据（关联班级）

INSERT INTO student (name, clazz\_id) VALUES

('张三', 1), ('李四', 1), ('王五', 2), ('赵六', 2);

\-- 插入课程数据

INSERT INTO subject (subject\_name, score) VALUES

('MyBatis 框架', 3), ('Spring 核心', 4), ('MySQL 优化', 2);

\-- 插入学生-课程关联数据（多对多）

INSERT INTO student\_subject (student\_id, subject\_id) VALUES

(1, 1), (1, 2), (2, 1), (3, 2), (3, 3), (4, 3);

\-- 插入人员与卡片数据（一对一）

INSERT INTO person (name, age) VALUES ('张三', 20), ('李四', 21);

INSERT INTO card (card\_num, person\_id) VALUES ('C2024001', 1), ('C2024002', 2);
```

### 5. 构建与运行项目

#### （1）构建项目



1. 打开终端，进入项目根目录（含 `pom.xml` 的目录），执行 Maven 构建命令：



```
mvn clean package  # 清理旧构建产物 → 编译代码 → 打包（生成 Jar 包）
```



1. 构建成功标志：终端输出 “BUILD SUCCESS”，且 `target` 目录生成 `MyDemoly_2-1.0-SNAPSHOT.jar`（Jar 包名称以 `pom.xml` 中配置的 `artifactId` 和 `version` 为准）。

#### （2）运行项目

##### 方式 1：通过 IntelliJ IDEA 运行（推荐，便于调试）



1. 打开 IDEA，选择「File → Open」，导入项目根目录的 `pom.xml`（IDEA 会自动识别为 Maven 项目）。

2. 等待 Maven 下载依赖（首次打开需联网，耗时约 1-3 分钟，依赖下载完成后无报错）。

3. 定位到 `src/main/java/com/cduestc/Main.java`，右键点击「Run Main.main ()」。

##### 方式 2：通过命令行运行



1. 进入 `target` 目录：



```
cd target
```



1. 执行 Jar 包（替换 `MyDemoly_2-1.0-SNAPSHOT.jar` 为实际生成的 Jar 包名称）：



```
java -cp MyDemoly\_2-1.0-SNAPSHOT.jar com.cduestc.Main
```

#### （3）运行验证

成功运行后，控制台会输出关联查询结果，示例如下：



```
【一对一查询】人员：张三（20岁），卡片编号：C2024001

【一对多查询】班级：Java 开发班，学生列表：张三、李四

【多对多查询】学生：张三，所选课程：MyBatis 框架（3学分）、Spring 核心（4学分）
```

## 六、常见问题排查

### 1. 数据库连接失败



* **原因 1**：用户名 / 密码错误 → 检查 `db.properties` 中的 `jdbc.username` 和 `jdbc.password` 是否与 MySQL 实际信息一致。

* **原因 2**：MySQL 服务未启动 → Windows 需在 “服务” 中启动 MySQL；Linux 执行 \`systemctl

> （注：文档部分内容可能由 AI 生成）
