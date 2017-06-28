# BlogLife

Blog Life是一个简单的博客记录网站，用于展示微服务架构的demo。使用到的技术包括Spring Boot和Spring Cloud，最后以Docker部署。

## 功能服务

Blog Life核心的业务分为两个服务，包括用户服务User Service和博客服务Blog Service。这两个服务采用Spring Boot独立开发，底层数据库也相互独立。其关系如下图所示：

![function_service](/Users/yan/Desktop/doc/imgs/function_service.png)

#### User Service 用户服务

包括用户的登录、注册

| Method | Path      | Description | User  authenticated | Available from UI |
| ------ | --------- | ----------- | ------------------- | ----------------- |
| POST   | /login    | 用户登录        | 无需验证                | 有                 |
| POST   | /register | 用户注册        | 无需验证                | 有                 |

#### Blog Service 博客服务

包括博客的搜索、读取、新增和修改

| Method | Path   | Description | User authenticated | Available from UI |
| ------ | ------ | ----------- | ------------------ | ----------------- |
| GET    | /blogs | 读取所有博客      | 需要验证               | 有                 |
| GET    | /{id}  | 根据id搜索某条博客  | 需要验证               | 无                 |
| POST   | /add   | 添加博客        | 需要验证               | 有                 |
| POST   | /{id}  | 修改博客        | 需要验证               | 有                 |

##### 注意

- 每个服务都有自己对应的数据库，但是微服务不是直接访问数据库的，而是通过另一个微服务API网关（基础架构中会介绍）。
- 这里每个微服务的数据存储在Mysql数据库，采用Spring框架自带的JdbcTemplate来进行数据库操作。
- 服务之间的通信只需通过同步的REST API接口，非常简单。

## 架构服务

Spring cloud中包括了许多常用的工具，为Spring Boot应用提供了许多功能特性，本项目中使用了如下部分：

- 服务发现
  - Eureka-server实例使用注释@EnableEurekaServer即可作为服务提供者，可以注册到服务注册中心；Eureka客户端使用注释@EnableEurekaClient即可以通过Spring管理的bean发现实例。嵌套式的Eureka服务可以通过声明式的Java配置文件创建。
- API网关
  - 在Spring Cloud Netflix中使用Zuul来作为API网关组件，主要是将API提供给外部应用访问，因此需要前端路由来管理后端微服务提供的服务。API网关可通过注释@EnableZuulProxy开启，从外需要在application.yml配置文件中配置功能服务的服务名和其路径。
- 配置中心
  - Config为服务端和客户端提供了分布式系统的外部化配置支持。配置服务器为各应用的所有环境提供了一个中心化的外部配置。它实现了对服务端和客户端对Spring Environment和PropertySource抽象的映射，所以它除了适用于Spring构建的应用程序，也可以在任何其他语言运行的应用程序中使用。可以用git和svn做配置文件的版本管理，可以本地存储。

![structure_service](/Users/yan/Desktop/doc/imgs/structure_service.png)

## 如何运行？

#### 前期准备

- 安装Docker和Docker Compose。

#### 运行命令

- cd spring-cloud-demo/ 并执行 mvn clean pacakge
- cd ../docker/ 并执行 docker-compose build 和 docker-compose up

#### 重要端口

- http://localhost:8080 网关

## 运行界面

首先可以看到Eureka发现了四个服务：CONFIG-CENTER配置中心，GATEWAY网关，SERVICE1博客服务和SERVICE2用户服务。

![eureka](/Users/yan/Desktop/doc/imgs/eureka.png)



这是Blog Life的主页面，点击右上角登录。

![index](/Users/yan/Desktop/doc/imgs/index.png)



输入数据库预置的用户名wk@git.com和密码123即可成功登录。

![login](/Users/yan/Desktop/doc/imgs/login.png)



当然也可以注册新账户，可以看到若注册用户的邮箱已存在于数据库中，则系统提示该邮箱已被占用！

![register1](/Users/yan/Desktop/doc/imgs/register1.png)



注册时后台验证确保改邮箱未被注册，则提示注册成功！

![register2](/Users/yan/Desktop/doc/imgs/register2.png)



登录成功后可看到当前数据库内置的一些短博客，点击标题为hi的博客，查看内容hi there。![bloglist](/Users/yan/Desktop/doc/imgs/bloglist.png)



接着点击上图中的修改，进入修改博客页面，新增一行add one line，成功提交。![update1](/Users/yan/Desktop/doc/imgs/update1.png)



可以看到返回的博客列表页面中，标题为hi的博客内容已经改变。

![update2](/Users/yan/Desktop/doc/imgs/update2.png)



下面演示新增博客的操作，点击上图中最底部添加按钮，进入新增页面。添加一条标题为add one，作者为yan，内容为this is test data的博客，成功提交。

![add1](/Users/yan/Desktop/doc/imgs/add1.png)



返回博客列表页面，可以看到底部已经新增了一条标题为add one的博客记录。

![add2](/Users/yan/Desktop/doc/imgs/add2.png)