import pymongo

client = pymongo.MongoClient(host='localhost', port=27017)
mydb = client['douban']
mysheet = mydb['douban_movie']
data = dict(a='a', b='b', t='t')
mysheet.insert(data)

