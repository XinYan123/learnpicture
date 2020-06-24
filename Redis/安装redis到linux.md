### 第一步 下载redis到linux

```linux
wget http://download.redis.io/releases/redis-2.8.17.tar.gz
```



### 第二步 解压安装包

```linux
tar -xvf redis-4.0.0.tar.gz
```



### 第三步 编译make(make出错解决方案)

```linux
3.安装

cd redis-4.0.8
make (执行make，出现错误时，进行如下操作）

因为Redis是C实现的，需要gcc来进行编译，所以原因是系统未安装gcc：

yum install -y gcc g++ gcc-c++ make

再次执行make，若make出现错误为：致命错误，执行以下命令

make MALLOC=libc
```



### 第四步 启动redis

```linux
#进入redis的目录
cd src

#启动服务
./redis-server 

新建一个Xshell会话，登录redis
./redis-cli
```



### 第五步 指定端口启动并进入指定端口客户端

```linux
./redis-server --port 6380

进入指定的端口客户端
./redis-cli -p 6380
```



### 第六步 正常开发的启动redis

```properties
创建一份备份的redis.conf文件

#第一步 去除注释 去除多余空格的提示
cat redis.conf  | grep -v "#" |grep -v "^$"

#第二步 复制简化过得redis.conf
cat redis.conf  | grep -v "#" |grep -v "^$" > redis-6379.conf


#第三步 修改redis-6379.conf
vim redis-6379.conf
vim的指令：dd删除一行，
		i是进入编辑
		esc退出编辑，然后：wq保存并退出
修改成如下配置
port 6380
daemonize yes
logfile "6380.log"
dir /root/redis-4.0.0/data		·


#第四步 由于全局无法使用redis命
执行./redis-server redis-6379.conf的命令时，要保证redis-6379.conf在src目录下

./redis-server redis-6379.conf

#第五步 查看是否启动了使用新的配置文件的redis-6379
ps -ef | grep redis-
```



### 第七步 kill掉进程

```linux
kill -s 9 进程号
```



### 第八步 创建一个件夹专门保存conf文件

```linux
创建文件夹
mkdir conf

移动文件到conf文件夹中
mv redis-6379.conf conf

启动服务就变成了，在src目录下
./redis-server conf/redis-6379.conf

查看此时的进程
ps -ef | grep redis-
```



### 第九步 启动新配置conf的redis客户端,实现多个端口号运行客户端

```linux
./redis-cli -p 6380
./redis-cli -p 6379
```



### 第十步 查看日志文件

```properties
进入data目录

cat 6379.log

```





# 总结

## 1 启动服务端可以使用

#### 1.1 默认配置启动

redis-server
redis-server –-port 6379
redis-server –-port 6380 ……

####  1.2 指定配置文件启动

redis-server redis.conf
redis-server redis-6379.conf
redis-server redis-6380.conf ……
redis-server conf/redis-6379.conf
redis-server config/redis-6380.conf ……  



## 2 连接客户端

### 2.1 默认连接

redis-cli

### 2.2 连接指定服务器

redis-cli -h 127.0.0.1
redis-cli –port 6379
redis-cli -h 127.0.0.1 –port 6379  



## 3 conf内部配置

#### 3.1 守护进程方式启动  

使用本启动方式， redis将以服务的形式存在，日志将不再打印到命令窗口中  

daemonize yes  



#### 3.2设定当前服务启动端口号  

port 6***  



#### 3.3设定当前服务文件保存位置，包含日志文件、持久化文件  

dir “/自定义目录/redis/data“  



#### 3.4 设定日志文件名，便于查阅  

logfile "6***.log“  