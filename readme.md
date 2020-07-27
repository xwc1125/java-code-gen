# code-gen

一款代码生成工具，可自定义模板生成不同的代码。

- 只需要一个Java8环境，下载后即可运行使用。
- 步骤简单，只需配置一个数据源，然后勾选模板即可生成代码。
- 默认提供了通用的实体类、mybatis接口、mybatis配置文件模板，可以快速开发mybatis应用。

教程：[快速搭建SpringBoot+Mybatis应用](https://gitee.com/durcframework/code-gen/wikis/pages?sort_id=2478942&doc_id=27724)

> 用到的技术：SpringBoot + Mybatis + Vue

## 使用步骤

- 前往[发行版页面](https://gitee.com/durcframework/code-gen/releases)，下载最新版本zip文件
- 解压zip，如果是Mac/Linux操作系统，可执行运行`startup.sh`，或者在命令行输入`java -jar gen.jar`
- 浏览器访问`http://localhost:6969/`

默认端口是6969，更改端口号可执行：`java -jar gen.jar --server.port=端口号`

## 工程说明

- front：前端vue
- gen：后端服务
- db：数据库初始化文件
- script：辅助脚本

## 自主构建

> 需要安装Maven3，Java8

- 自动构建[推荐]：

Mac/Linux系统可直接执行`build.sh`进行构建，构建结果在`dist`文件夹下。

- 手动构建：
    
    `cd front`
    
    - 执行`npm run build:prod`进行打包，结果在dist下
    - 把dist中的所有文件，放到`gen/src/main/resources/public`下
    
    `cd ..`
    
    - 执行`mvn clean package`，在`gen/target`下会生成一个`gen-xx-SNAPSHOT.jar`（xx表示本号）
    - 将`gen-xx-SNAPSHOT.jar`和db下的`gen.db`放在同一个文件夹下
    - 执行`java -jar gen-xx-SNAPSHOT.jar`
    - 浏览器访问`http://localhost:6969/`

## 效果图

![代码生成](https://images.gitee.com/uploads/images/2020/0724/180853_df66e76d_332975.png "gen7.png")

![生成结果](https://images.gitee.com/uploads/images/2020/0724/090952_4dc5fec9_332975.png "gen4.png")

![生成结果](https://images.gitee.com/uploads/images/2020/0724/091000_9e5e0e81_332975.png "gen5.png")

![生成结果](https://images.gitee.com/uploads/images/2020/0724/091008_382b9f13_332975.png "gen6.png")

