# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy
# 第二步：明确目标
#------------定义数据结构(你需要抓取的元素)
# scrapy.Item 对象是个简单的容器。其API和dict API非常相似，并可以用dict(item)将这个对象转化为字典，这个用法在piplines.py中可以见到。
class DoubanItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()

    # 电影序号  电影名  电影介绍  电影星级 电影评论数  电影的描述
    serial_number = scrapy.Field()
    movie_name = scrapy.Field()
    introduce = scrapy.Field()
    star = scrapy.Field()
    evaluate = scrapy.Field()
    describe = scrapy.Field()

