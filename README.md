
###短域名服务  （单机版） [分布式集群版点这里](https://github.com/hardenCN/shortUrl/tree/master/short_url_distributed)


![usecase](https://github.com/hardenCN/shortUrl/raw/master/short_url/doc/usecase.jpg)


![class](https://github.com/hardenCN/shortUrl/raw/master/short_url/doc/class.jpg)


  > 短域名生成，分布式环境使用全局自增id
  >
  > 参考了 [hashids](https://hashids.org/) ；


- **采用的``springboot-webflux``，集成了Swagger API文档；**

- **映射数据存储在JVM；**
  
###测试情况

![jmeter1](https://github.com/hardenCN/shortUrl/raw/master/short_url/doc/jmeter1.jpg)
![jmeter2](https://github.com/hardenCN/shortUrl/raw/master/short_url/doc/jmeter2.jpg)
