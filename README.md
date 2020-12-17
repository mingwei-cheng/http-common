# 注解方式的Http请求
### 0.基础
0.0.1 版本 
- spring-boot-starter:2.2.4.RELEASE
- spring-boot-starter-aop:2.2.4.RELEASE
- spring-boot-starter-webflux:2.2.4.RELEASE
### 1. 支持类型
- [GET]
- [POST]
- [HEAD]
- [PUT]
### 2. 注解参数
| 名称 | 含义 | 类型 | 格式 | 存在于 |
| ------ | ------ | ------ | ------ | ------ |
| url | 请求地址 | String | http://xxx.xxx.xxx | * |
| head | 请求头 | String[] | key:value| | * |
| attr | 请求参数 | String[] | key:value |除POST|
| cookies | 请求地址 | String[] | json |POST|
|  | 返回类型 | Mono\<String> |  | * |
### 3. 食用方式
- 在HttpApplicationTests.java中，有一个简单的DEMO
- 只需要在@Service类中的某个方法上，使用注解，成功则会返回调用的结果，<b>失败</b>则会执行该方法体里内容
- WebFlux的WebClient默认最大内存大小为256K，可以通过配置文件中cn.cheng.http.max-memory-size参数进行配置，如cn.cheng.http.max-memory-size=2097152
- 请通过安装jar包的方式，将本包安装到maven仓库中，再在pom中进行依赖的导入
