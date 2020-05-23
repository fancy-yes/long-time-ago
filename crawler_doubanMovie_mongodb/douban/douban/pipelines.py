# -*- coding: utf-8 -*-

import pymongo
from douban.settings import mongo_host, mongo_port, mongo_db_name, mongo_db_collection

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html

class DoubanPipeline(object):
    def __int__(self):
        pass
        

    # 将数据处理并保存到mongodb
    # 参数item：来源是douban_spider.py中parse函数中的 yield douban_item
    # 参数spider: 爬取item的spider。也就是本项目中的douban_spider.py中的 name = 'douban_spider'
    def process_item(self, item, spider):
        # 这个item对象继承自scrapy.Item对象，可以转化为字典，在items.py中更多备注。
        data = dict(item)

        # 数据库相关操作
        host = mongo_host
        port = mongo_port
        dbname = mongo_db_name
        sheetname = mongo_db_collection
        # 创建mongodb的client————拿到mongodb的连接
        client = pymongo.MongoClient(host=host, port=port)
        # 指定数据库
        mydb = client[dbname]
        # 指定表。
        mysheet = mydb[sheetname]
        # 执行mongodb表的插入操作
        mysheet.insert(data)
        # 按照框架规定，item对象还要返回
        return item