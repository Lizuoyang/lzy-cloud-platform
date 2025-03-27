# 🚀 lzy Platform 微服务基础平台

lzy Platform 是一个基于SpringBoot/SpringCloud的模块化微服务基础平台，提供企业级应用中常见功能的标准化实现方案。

## 📦 项目结构

### 🔧 基础核心模块
| 模块名称                     | 功能说明                               |
|------------------------------|--------------------------------------|
| lzy-platform-base          | 公共基础类、工具类、通用DTO等          |
| lzy-platform-bom           | Maven依赖版本统一管理（Bill of Materials）|
| lzy-platform-springboot    | SpringBoot定制启动器（自动配置、全局异常处理等）|
| lzy-platform-cloud         | SpringCloud组件定制扩展               |
| lzy-platform-mybatis       | Mybatis扩展（分页插件、数据权限等）     |

### 💾 数据存储模块
| 模块名称                     | 功能说明                               |
|------------------------------|--------------------------------------|
| lzy-platform-db            | 数据库连接池配置、多数据源支持          |
| lzy-platform-redis         | Redis客户端配置与扩展                 |
| lzy-platform-cache         | 统一缓存抽象层（支持多级缓存）          |
| lzy-platform-dfs-*         | 分布式文件存储实现（阿里云/腾讯云/七牛云/MinIO）|
| lzy-platform-dfs-starter   | 统一文件存储服务入口                   |

### 🔐 安全认证模块
| 模块名称                     | 功能说明                               |
|------------------------------|--------------------------------------|
| lzy-platform-oauth2        | OAuth2认证服务器扩展实现               |
| lzy-platform-captcha       | 图形/短信验证码生成与校验              |

### 🛠️ 公共服务模块
| 模块名称                     | 功能说明                               |
|------------------------------|--------------------------------------|
| lzy-platform-sms-*         | 多平台短信服务实现（阿里云/腾讯云/七牛云）|
| lzy-platform-sms-starter   | 统一短信服务入口                      |
| lzy-platform-pay           | 统一支付接口（支持多种支付渠道）        |
| lzy-platform-wechat        | 微信生态对接（公众号/小程序/支付等）    |
| lzy-platform-push          | 消息推送服务（APP/Web/短信等渠道）      |

### 👨‍💻 开发支持模块
| 模块名称                     | 功能说明                               |
|------------------------------|--------------------------------------|
| lzy-platform-dev           | 代码生成器基础模块                    |
| lzy-platform-swagger       | API文档自动生成与增强                 |

## ⚡ 快速开始

### 📥 依赖引入
在父pom中引入BOM管理：
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.lzy.platform</groupId>
            <artifactId>lzy-platform-bom</artifactId>
            <version>${lzy.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

### 🌰 典型模块集成示例

- 📱 短信服务集成：
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

- 📂 文件存储集成：
```xml
<dependency>
    <groupId>com.lzy.platform</groupId>
    <artifactId>lzy-platform-dfs-starter</artifactId>
</dependency>
<dependency>
    <groupId>com.lzy.platform</groupId>
    <artifactId>lzy-platform-dfs-minio</artifactId>
</dependency>
```

### ⚙️ 配置指南

- 📱 短信配置示例
```yaml
lzy:
  sms:
    provider: aliyun
    aliyun:
      access-key: your-access-key
      access-secret: your-access-secret
      sign-name: lzy_PLATFORM
```

- 📂 文件存储配置示例
```yaml
lzy:
  dfs:
    active: local
    minio:
      endpoint: http://minio.example.com
      access-key: minio-user
      secret-key: minio-password
      bucket-name: lzy-bucket
```

### ❗ 注意事项
<ol>
    <li>
        📌 模块依赖关系：
        <ul>
            <li>使用*-starter模块时需引入具体的实现模块</li>
            <li>基础模块(lzy-platform-base)会被大多数模块自动依赖</li>
        </ul>
    </li>
    <li>
        🔄 版本兼容性：
        <ul>
            <li>所有模块版本号由lzy-platform-bom统一管理</li>
            <li>建议使用最新release版本</li>
        </ul>
    </li>
</ol>

## 🤝 贡献指南

欢迎提交Issue和Pull Request，一起完善lzy Platform！
