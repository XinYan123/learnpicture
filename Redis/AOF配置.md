### AOF配置

```properties
port 6379
daemonize yes
logfile "6379.log"
dir /root/redis-4.0.0/data
dbfilename dump-6379.rdb
rdbcompression yes
rdbchecksum yes
appendonly yes
#三种策略
appendfsync always|everysec|no
```



