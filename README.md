#这是一个爬虫项目

根据知乎用户爬取所有和用户相关的数据

数据包括关注者,被关注者,文章,专栏,想法,提问,答案,和用户相关的其他信息

应的框架有

jdk1.8

spring-boot --基本架构

spring-data-elastic --elastic客户端

elasticsearch --搜索引擎,也用于存储数据

kinaba --数据展示和分析


使用spring的resttemplate抓去知乎的接口

使用数据线程池实现多线程抓取

记录上次停顿的位置实现连续抓取

控制抓取的数据类型

去重使用的是elsstic提供的upset方法,id的话,people使用url_token

当访问被限制的时候切换本地的http代理,不想找线程池,所以一般只开两个线程进行抓取

跑了一个星期大概抓取了200W的用户数据和其他的一些






