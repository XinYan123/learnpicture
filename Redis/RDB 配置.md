## RDB 配置

redis服务的配置文件.conf配置如下

```properties
#修改配置
port 6379
daemonize yes
logfile "6379.log"
dir /root/redis-4.0.0/data
dbfilename dump-6379.rdb
rdbcompression yes
rdbchecksum yes

```

