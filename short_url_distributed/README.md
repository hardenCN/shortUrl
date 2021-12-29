

###短域名服务  （分布式集群版本 zookeeper实现）

**用例图**

![usecase](https://github.com/hardenCN/shortUrl/raw/master/short_url_distributed/doc/usecase.jpg)

**利用Zookeeper实现分布式LRUCache集群**

![distributed1](https://github.com/hardenCN/shortUrl/raw/master/short_url_distributed/doc/distributed1.jpg)

- **短域名存储接口：接受长域名信息，返回短域名信息**
    1. 验证：长度，格式
    2. 根据2-8规则，80%的流量将由20%的长域名生成，考虑用``ConcurrentHashMap``实现 ``LRUCache`` 来存储 
    3. 在此基础上应该考虑结合 ConcurrentHashMap 构造参数，减少``rehash``

  | 参数 | initialCapacity | loadFactor | concurrencyLevel |
    | :----:|:----:|:----:|:----:|
  | 值 | 2 * 1024 * 1024 | 1.0f | 8 |
  | 说明 | 1个长域名映射记录(UTF-8)限制1KB，LRUCache 2G内存 | 为了cache size和ConcurrentHashMap threshold保持一致，减少一次rehash | webflux work线程数量为6，这里设置8 |


* 为了实现【``常数级别时间复杂度``】，两种方案：
1. 一个容器方案，(不幂等)长域名不判断重复(一次请求一条记录)，允许长域名重复：``Key-Value : 短域名-长域名``
2. 两个容器方案（用一个容器两条记录代替），(幂等)长域名不允许重复(判断是否存在长域名记录，有则直接返回不新增)，一次请求两条记录: ``Key-Value : 长域名-短域名``，存储后建立反向索引 ``Key-Value : 短域名-长域名`` 空间换时间


- **短域名读取接口：接受短域名信息，返回长域名信息。**
  > 验证：长度，格式；据短域名id，常数级别复杂度直接定位长域名
  >
  > 如果独立部署，对于查询接口，要支持跨域请求，后端 ``CORS`` 或 ``jsonp``跨域。


- **短域名长度最大为 8 个字符**

  > 短域名生成，使用zookeeper生成分布式自增id，转 ``62进制`` 字符串
  >
  > 参考了 [hashids](https://hashids.org/) ；


- **采用``springboot-webflux``，集成了Swagger API文档；**



