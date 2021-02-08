# Spring Boot new retail system

![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack

![](https://img.shields.io/badge/java_8-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/mysql-✓-blue.svg)
![](https://img.shields.io/badge/mybatis-✓-blue.svg)
![](https://img.shields.io/badge/pagehelper-✓-blue.svg)
![](https://img.shields.io/badge/jwt-✓-blue.svg)
![](https://img.shields.io/badge/swagger_2-✓-blue.svg)




# Main file structure

```
iretail/
 │
 ├── src/main/java/
 │   └── iretail
 │       ├── config
 │       │   └── SwaggerConfig.java
 │       │   └── OperationAnno.java
 │       │   └── OperationAspect.java
 │       │   └── GlobalDefaultExceptionHandler.java
 │       │   └── BusinessException.java
 │       │
 │       ├── controller
 │       │   └── UserController.java
 │       │
 │       ├── dto
 │       │   ├── UserInfo.java
 │       │
 │       ├── entity
 │       │   ├── Operation.java
 │       │   └── Org.java
 │       │   └── Role.java
 │       │   └── RoleOperation.java
 │       │   └── Store.java
 │       │   └── User.java
 │       │   └── UserOrg.java
 │       │   └── UserRole.java
 │       │
 │       ├── service
 │       │   └── UserService.java
 │       │
 │       ├── utils
 │       │   └── JwtUtil.java
 │       │   └── UserScopeUtil.java
 │       │
 │       └── IretailApplication.java
 │
 ├── src/main/resources/
 │   └── application.yaml
 │
 ├── .gitignore
 ├── README.md
 └── pom.xml
```

# Introduction

Ready to build an inventory system, including data dimension check, role permissions check

