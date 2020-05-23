# -*- coding: utf-8 -*-
import scrapy
from douban.items import DoubanItem
# 继承自scrapy.Spider类
class DoubanSpiderSpider(scrapy.Spider):
    # 爬虫名。注意不能和项目名称重复。
    name = 'douban_spider'
    # 允许的域名
    allowed_domains = ['movie.douban.com']
    # spider提供入口URL，然后通过引擎，交给调度器，然后~~~，最后，spider收到Response响应。
    start_urls = ['https://movie.douban.com/top250']

    #如何测试项目？
    ## 安装好sqlite,方便测试项目。
    ## 修改 setting.py中的url_agent
    ## 项目运行base命令： scrapy crawl douban_spider （注：在douban/douban_spider.py所在文件夹下）

    # 默认的解析方法
    # spider收到的Response响应，将在parse()函数中解析。
    def parse(self, response):
        #循环电影的条目
        #解析Response中的数据。
        movie_list = response.xpath("//div[@class='article']//ol[@class='grid_view']//li")
        for i_item in movie_list:
            # 对电影列表继续xpath,将细分的数据保存到在items.py中编写的数据结构的对象之中
            douban_item = DoubanItem()
            # i_item.xpath(".//xxx") 这里的"."号不要忘。 另外，结尾extract_first()作用是解析到它第一条数据。
            douban_item['serial_number'] = i_item.xpath(".//div[@class='item']//div[@class='pic']//em/text()").extract_first()
            douban_item['movie_name'] = i_item.xpath(".//div[@class='item']//div[@class='info']//div[@class='hd']//span[@class='title'][position()<=1]/text()").extract_first()
            douban_item['star'] = i_item.xpath(".//div[@class='item']//div[@class='bd']//div[@class='star']//span[@class='rating_num']/text()").extract_first()
            douban_item['evaluate'] = i_item.xpath(".//div[@class='item']//div[@class='bd']//div[@class='star']//span[position()=4]/text()").extract_first()
            douban_item['describe'] = i_item.xpath(".//div[@class='item']//p[@class='quote']/text()").extract_first()
            # ”电影介绍“的数据处理. 注意:这里结尾是extract()
            content_introduce = i_item.xpath(".//div[@class='item']//div[@class='bd']//p[position()=1]/text()").extract()
            for i_content in content_introduce:
                content_s = "".join(i_content.split())
                douban_item['introduce'] = content_s
            # 根据框架的用法，这里将数据yield到"item Pipeline"组件中,进行数据的后续处理。
            yield douban_item

        # 解析Response中的新URl（取后一页的URL）。 注意：这里结尾的方法是extract()
        next_link = response.xpath("//span[@class='next']//link//@href").extract()
        # 注：最后一页没有下一页，此时next_link==None。
        if next_link:
            next_link = next_link[0]
            # 根据框架的用法，引擎将URL封装为请求之后，被yield到"Scheduler"组件中排队。
            # 注意 参数中的回调函数是parse()函数
            yield scrapy.Request("https://movie.douban.com/top250"+next_link,callback=self.parse)