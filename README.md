# 🚀 Lzy Platform: 企业级模块化微服务基础平台

**Lzy Platform** 是一个基于 Spring Boot 和 Spring Cloud 构建的企业级微服务基础平台。它旨在通过提供一套标准化、可复用、可插拔的常见功能解决方案，显著加速现代化、分布式应用的开发进程，助力开发者聚焦于核心业务逻辑。

## ✨ 设计理念 (Design Philosophy)

Lzy Platform 的设计遵循以下核心理念：

*   **高度模块化 (High Modularity)**: 平台功能被精心拆分为独立的 Maven 模块，每个模块职责单一、内聚性强，易于独立开发、测试、部署和升级。
*   **可插拔架构 (Pluggable Architecture)**: 大量采用 Spring Boot Starter 机制，开发者可以像搭积木一样按需引入所需的功能模块（如分布式文件存储、短信服务），并轻松切换不同的底层实现。
*   **标准化与规范化 (Standardization & Normalization)**: 提供统一的基础组件（如通用 DTO、全局异常处理、标准化 API 响应格式、日志规范）、统一的配置方式和统一的依赖版本管理（通过 BOM），降低学习成本，提高代码一致性和可维护性。
*   **易扩展性 (Extensibility)**: 平台在提供通用能力的同时，也注重为二次开发和定制化预留扩展点，方便用户根据具体业务场景进行功能增强。

## ⭐ 核心特性 (Key Features)

Lzy Platform 提供了一系列开箱即用的功能模块，覆盖了企业级应用开发的方方面面：

*   **基础框架 (Core Infrastructure)**
    *   `lzy-platform-base`: 提供核心工具类 (Hutool)、通用 DTO、常量、枚举、自定义注解和统一异常处理机制。
    *   `lzy-platform-boot`: 定制化 Spring Boot 启动器，集成全局异常处理、统一日志配置 (Log4j2 + SkyWalking TraceID)、基础 Web 配置等。
    *   `lzy-platform-cloud`: 对 Spring Cloud 组件进行定制和扩展（具体内容待进一步分析）。
    *   `lzy-platform-bom`: 通过 Maven Bill of Materials (BOM) 统一管理所有平台模块及核心第三方依赖的版本。
*   **数据持久化 (Data Persistence)**
    *   `lzy-platform-db`: 集成 Druid 连接池，支持多数据源配置（MySQL, PostgreSQL），预留 ShardingSphere 集成。
    *   `lzy-platform-mybatis`: 集成 Mybatis-Plus，提供便捷的 CRUD 操作、分页插件、数据权限控制等增强功能。
    *   `lzy-platform-cache`: 提供统一的缓存抽象层，支持多级缓存，默认集成 Redis 客户端配置与扩展。
*   **分布式服务 (Distributed Services)**
    *   `lzy-platform-dfs-*`: 提供分布式文件存储 (DFS) 的统一接口 (`IDfsBaseService`) 和 Starter (`lzy-platform-dfs-starter`)。内置支持本地存储 (`dfs-local`)、阿里云 OSS (`dfs-aliyun`)，易于扩展其他云存储（如 MinIO, 腾讯云 COS, 七牛云 Kodo）。
    *   `lzy-platform-sms-*`: 提供短信服务的统一接口和 Starter (`lzy-platform-sms-starter`)。内置支持阿里云短信 (`sms-aliyun`)、创蓝短信 (`sms-chuanglan`)，易于扩展其他短信通道。
    *   `lzy-platform-xxl-job`: 集成 XXL-JOB，提供成熟可靠的分布式任务调度能力。
*   **安全认证 (Security & Auth)**
    *   `lzy-platform-oauth2`: 基于 Spring Cloud OAuth2 实现，提供 OAuth2 认证服务器的扩展实现，用于构建统一认证授权中心。
    *   `lzy-platform-captcha`: 提供图形验证码和短信验证码的生成与校验功能。
*   **业务支撑 (Business Enablement)**
    *   `lzy-platform-pay`: 聚合支付接口，简化多种支付渠道（如微信支付、支付宝）的对接。
    *   `lzy-platform-wechat`: 提供微信生态对接能力，包含公众号、小程序、微信支付 (`lzy-platform-wechat-pay`) 等。
    *   `lzy-platform-push`: 提供统一的消息推送服务接口，支持 APP 推送、Web 推送、短信等多种渠道（具体实现待确认）。
    *   `lzy-platform-justauth`: 集成 JustAuth，简化第三方社交登录的实现。
    *   `lzy-platform-mail`: 提供邮件发送服务功能。
*   **开发运维 (Dev & Ops)**
    *   `lzy-platform-log`: 统一的日志处理模块，基于 Log4j2，集成 SkyWalking 实现链路追踪 ID 打印，支持日志异步输出和 Kafka 同步。
    *   `lzy-platform-swagger`: 集成 Swagger 并通过 Knife4j 提供增强的、更友好的 API 文档界面。
    *   `lzy-platform-dev`: 包含代码生成器等开发辅助工具（具体功能待确认）。

## 🛠️ 技术选型 (Technology Stack)

*   **核心框架**: Spring Boot 2.3.12.RELEASE, Spring Cloud Hoxton.SR12
*   **编程语言**: Java 8
*   **构建工具**: Apache Maven
*   **数据库 ORM**: Mybatis-Plus
*   **数据库连接池**: Druid
*   **数据库支持**: MySQL, PostgreSQL
*   **缓存**: Redis (通过 `lzy-platform-cache` 抽象)
*   **日志框架**: Log4j2, Slf4j
*   **分布式任务调度**: XXL-JOB
*   **认证授权**: Spring Cloud OAuth2
*   **API 文档**: Swagger 2, Knife4j
*   **工具库**: Lombok, Hutool, Jackson

## 🏗️ 架构亮点 (Architecture Highlights)

*   **彻底的模块化**: 将平台能力细分为独立模块，职责清晰，易于管理和演进。
*   **Starter 驱动集成**: 大量运用 `*-starter` 模块，简化开发者集成配置，实现功能的按需加载和可插拔。
*   **BOM 统一版本**: 通过 `lzy-platform-bom` 管理所有模块及核心依赖的版本，解决版本冲突，保证兼容性。
*   **面向接口抽象**: 关键服务（如 DFS, SMS）提供统一接口，屏蔽底层实现差异，易于扩展和替换。
*   **基础能力下沉**: 将通用工具、配置、规范等沉淀到 `base`, `boot`, `log` 等基础模块，避免重复造轮子。

## 📦 模块清单 (Module Inventory)

| 模块名称                        | 功能说明                                                     |
|---------------------------------|--------------------------------------------------------------|
| **基础核心 (Core)**             |                                                              |
| `lzy-platform-base`           | 核心工具类、通用 DTO/VO、常量、枚举、自定义注解、基础异常      |
| `lzy-platform-bom`            | Maven 依赖版本统一管理 (Bill of Materials)                   |
| `lzy-platform-boot`           | Spring Boot 定制启动器 (自动配置、全局异常处理、Web配置等)   |
| `lzy-platform-cloud`          | Spring Cloud 组件定制与扩展                                  |
| `lzy-platform-log`            | 统一日志框架 (Log4j2, SkyWalking TraceID 集成)               |
| **数据存储 (Data)**             |                                                              |
| `lzy-platform-db`             | 数据库连接池 (Druid)、多数据源支持、ShardingSphere 预留        |
| `lzy-platform-mybatis`        | Mybatis-Plus 扩展 (分页插件、数据权限等)                     |
| `lzy-platform-cache`          | 统一缓存抽象 (支持 Redis、多级缓存)                          |
| `lzy-platform-dfs`            | 分布式文件存储 (DFS) 核心接口与模型                          |
| `lzy-platform-dfs-local`      | DFS 本地文件存储实现                                         |
| `lzy-platform-dfs-aliyun`     | DFS 阿里云 OSS 实现                                          |
| `lzy-platform-dfs-starter`    | DFS 统一入口 Starter                                         |
| **安全认证 (Security)**         |                                                              |
| `lzy-platform-oauth2`         | OAuth2 认证服务器扩展实现 (基于 Spring Cloud OAuth2)          |
| `lzy-platform-captcha`        | 图形/短信验证码生成与校验                                    |
| **公共服务 (Services)**         |                                                              |
| `lzy-platform-sms`            | 短信服务核心接口与模型                                       |
| `lzy-platform-sms-aliyun`     | 短信服务阿里云实现                                           |
| `lzy-platform-sms-chuanglan`  | 短信服务创蓝云实现                                           |
| `lzy-platform-sms-starter`    | 短信服务统一入口 Starter                                     |
| `lzy-platform-pay`            | 统一支付接口 (聚合多种支付渠道)                              |
| `lzy-platform-wechat`         | 微信生态对接基础模块                                         |
| `lzy-platform-wechat-pay`     | 微信支付实现                                                 |
| `lzy-platform-push`           | 统一消息推送服务接口 (具体实现待确认)                        |
| `lzy-platform-justauth`       | 第三方社交登录集成 (基于 JustAuth)                           |
| `lzy-platform-mail`           | 邮件发送服务                                                 |
| **任务调度 (Job)**              |                                                              |
| `lzy-platform-xxl-job`        | 集成 XXL-JOB 分布式任务调度平台                              |
| **开发支持 (Dev Support)**      |                                                              |
| `lzy-platform-dev`            | 代码生成器等开发辅助工具 (具体功能待确认)                    |
| `lzy-platform-swagger`        | API 文档自动生成与增强 (集成 Knife4j)                        |

## ⚡ 快速开始 (Quick Start)

### 📥 依赖引入 (Dependency Inclusion)

在您的项目父 `pom.xml` 的 `<dependencyManagement>` 中引入 BOM 进行版本管理：

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.lzy.platform</groupId>
            <artifactId>lzy-platform-bom</artifactId>
            <!-- 建议使用最新的 release 版本 -->
            <version>1.0.0-SNAPSHOT</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

### 🌰 典型模块集成示例 (Integration Examples)

-   **📱 短信服务集成 (阿里云)**:
    引入 Starter 和具体实现模块：

    ```xml
    <dependency>
        <groupId>com.lzy.platform</groupId>
        <artifactId>lzy-platform-sms-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>com.lzy.platform</groupId>
        <artifactId>lzy-platform-sms-aliyun</artifactId>
    </dependency>
    ```

-   **📂 文件存储集成 (本地)**:
    引入 Starter 和具体实现模块：

    ```xml
    <dependency>
        <groupId>com.lzy.platform</groupId>
        <artifactId>lzy-platform-dfs-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>com.lzy.platform</groupId>
        <artifactId>lzy-platform-dfs-local</artifactId>
    </dependency>
    ```

## ⚙️ 配置指南 (Configuration Guide)

在 `application.yml` 或 `application.properties` 中添加相应配置。

-   **📱 短信配置示例 (阿里云)**:

    ```yaml
    lzy:
      sms:
        # 指定使用的服务提供商 (aliyun, chuanglan, etc.)
        provider: aliyun
        # 对应服务商的配置
        aliyun:
          access-key: your-access-key
          access-secret: your-access-secret
          # 默认签名 (可被调用时覆盖)
          sign-name: YourSignName
          # 默认模板CODE (可被调用时覆盖)
          template-code: SMS_12345678
    ```

-   **📂 文件存储配置示例 (本地)**:

    ```yaml
    lzy:
      dfs:
        # 指定使用的存储类型 (local, aliyun, minio, etc.)
        active: local
        # 本地存储配置
        local:
          # 文件存储的基础路径 (绝对路径)
          base-path: /data/upload
          # 对外访问的基础 URL (需配合 Nginx 等配置静态资源访问)
          base-url: http://localhost:8080/files
        # MinIO 配置示例 (如果 active: minio)
        # minio:
        #   endpoint: http://minio.example.com
        #   access-key: minio-user
        #   secret-key: minio-password
        #   bucket-name: lzy-bucket
        #   create-bucket-if-not-exist: true
    ```

## ❗ 注意事项 (Important Notes)

1.  **📌 模块依赖关系**:
    *   使用 `*-starter` 模块时，通常需要引入至少一个具体的实现模块 (如 `dfs-local`, `sms-aliyun`)，Starter 会根据配置自动装配。
    *   `lzy-platform-base` 作为最基础的模块，会被大多数其他模块自动依赖。
2.  **🔄 版本兼容性**:
    *   所有 `com.lzy.platform` 下的模块版本由 `lzy-platform-bom` 统一管理，请确保使用配套版本。
    *   强烈建议在生产环境中使用最新的稳定 Release 版本。

## 🚀 未来展望 (Future Directions)

Lzy Platform 将持续演进，未来的发展方向可能包括：

*   **技术栈升级**: 及时跟进 Spring Boot 和 Spring Cloud 的最新稳定版；评估升级 JDK LTS 版本的可行性。
*   **云原生增强**: 提供官方 Dockerfile 和 Helm Charts；探索与 Service Mesh (如 Istio) 的集成。
*   **可观测性提升**: 提供 Prometheus/Grafana 指标模板；完善日志聚合与分布式追踪方案集成指南 (ELK, PLG, Jaeger)。
*   **性能与响应式**: 探索在 I/O 密集场景引入 Spring WebFlux；对核心模块进行持续性能优化。
*   **功能扩展**: 增强 API Gateway 功能；引入事件驱动架构支持 (Kafka/RabbitMQ)；增加分布式事务 (Seata) 等业务组件。
*   **开发体验**: 完善代码生成器；提供更详尽的模块级文档和最佳实践。
*   **安全性加固**: 引入更细粒度的权限模型；定期安全扫描和依赖更新。

## 🤝 贡献指南 (Contribution Guide)

我们热忱欢迎社区开发者参与贡献！您可以通过以下方式参与：

*   提交 Issue 报告 Bug 或提出功能建议。
*   提交 Pull Request 修复 Bug 或实现新功能。

请在贡献前查阅相关的贡献文档（如果存在），并遵循项目的代码风格和提交流程。
