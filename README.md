# 在 Spring Boot 中使用兼容 gRPC 的 Dubbo

## 背景

### 什么是 gRPC？

gRPC 是一种高性能、跨平台的开源远程过程调用（RPC）框架，由 Google 开发并基于 Protocol Buffers（ProtoBuf）序列化协议构建。它允许客户端和服务器之间通过定义服务和消息类型的接口来进行通信。 **gRPC 支持多种编程语言**，并提供了强大的功能，如双向流式传输、身份验证和负载均衡等。

通过使用 gRPC，开发者可以定义一个服务接口，并使用 .proto 文件中的 IDL（Interface Description Language，接口描述语言）来声明服务和消息类型。然后，gRPC 根据这个 .proto 文件自动生成相应的客户端和服务器代码，从而简化了远程通信的开发过程。

### gRPC 对 Java 的支持不够友好

目前，gRPC 对 Java 的支持不算好，**gRPC 本身不具备服务治理功能**。为了获得更好的跨语言特性，我们希望保留 .proto 文件，并基于此进行 Java 模块的开发。因此，我们把目光转向 Java 支持友好的 Dubbo 进行开发，Dubbo 支持兼容各种 RPC 协议，当然也包括 gRPC。

### 问题

我们希望 .proto 文件被保留，而 Dubbo 用作服务治理。在 Dubbo 的 [文档](https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/quick-start/)，没有提供一种良好的基于 Spring Boot 的并基于 IDL 定义跨语言服务的示例。



## 在 Spring Boot 中使用兼容 gRPC 的 Dubbo

源码发布在：https://github.com/Hiworle/dubbo-samples-spring-boot-idl

### 项目结构

```
F:.
│  HELP.md
│  .gitignore
│  pom.xml
│
├─.idea
│      .gitignore
│      misc.xml
│      jarRepositories.xml
│      workspace.xml
│      compiler.xml
│      encodings.xml
│
├─.mvn
│  └─wrapper
│          maven-wrapper.jar
│          maven-wrapper.properties
│
├─dubbo-samples-spring-boot-consumer
│  │  pom.xml
│  │
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─org
│      │  │      └─apache
│      │  │          └─dubbo
│      │  │              └─springboot
│      │  │                  └─demo
│      │  │                      └─consumer
│      │  │                              Task.java
│      │  │                              ConsumerApplication.java
│      │  │
│      │  ├─proto
│      │  │      greeter.proto
│      │  │
│      │  └─resources
│      │          application.yml
│      │
│      └─test
│          └─java
│              └─org
│                  └─apache
│                      └─dubbo
│                          └─springboot
│                              └─demo
│                                  └─test
│                                          ConsumerIT.java
│                                          ConsumerApplication.java
│
├─dubbo-samples-spring-boot-provider
│  │  pom.xml
│  │
│  └─src
│      └─main
│          ├─java
│          │  └─org
│          │      └─apache
│          │          └─dubbo
│          │              └─springboot
│          │                  └─demo
│          │                      └─provider
│          │                              DemoServiceImpl.java
│          │                              ProviderApplication.java
│          │
│          ├─proto
│          │      greeter.proto
│          │
│          └─resources
│                  application.yml
│
└─embedded-zookeeper
    │  pom.xml
    │
    ├─src
    │  └─main
    │      ├─java
    │      │  └─org
    │      │      └─apache
    │      │          └─dubbo
    │      │              └─samples
    │      │                      EmbeddedZooKeeper.java
    │      │
    │      └─resources
    │              log4j.properties
    │
    └─target
        ├─classes
        │  │  log4j.properties
        │  │
        │  └─org
        │      └─apache
        │          └─dubbo
        │              └─samples
        │                      EmbeddedZooKeeper.class
        │
        └─generated-sources
            └─annotations
```



### 使用说明

0. 未编译 .proto 文件的情况（以 provider 为例）
   <img src="https://artemisia-1307592663.cos.ap-guangzhou.myqcloud.com/202310281118362.png" alt="image-20231028111849256" style="zoom:67%;" />

1. 使用 Maven 插件编译 protobuf
   <img src="https://artemisia-1307592663.cos.ap-guangzhou.myqcloud.com/202310281120208.png" alt="image-20231028112030142" style="zoom: 67%;" />

   或者使用命令：

   ```powershell
   PS ...\dubbo-samples-spring-boot-idl> mvn -pl dubbo-samples-spring-boot-provider protobuf:compile
   ```

   proto 文件被编译：
   <img src="https://artemisia-1307592663.cos.ap-guangzhou.myqcloud.com/202310281130847.png" alt="image-20231028113046783" style="zoom: 67%;" />

2. 类似地，使用 Maven 插件可以编译 Consumer 部分的 proto 文件：

   ```powershell
   PS ...\dubbo-samples-spring-boot-idl> mvn -pl dubbo-samples-spring-boot-consumer protobuf:compile
   ```

3. 依次启动 EmbeddedZookeeper、ProviderApplication、ConsumerApplication
   ![image-20231028113841083](https://artemisia-1307592663.cos.ap-guangzhou.myqcloud.com/202310281138139.png)

















