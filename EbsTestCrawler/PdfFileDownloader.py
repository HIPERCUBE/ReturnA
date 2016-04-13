# coding=utf-8
from pymongo import MongoClient
import os
import urllib

# MongoDB Setup
mongoClient = MongoClient('mongodb://localhost:27017')
db = mongoClient.ReturnA

rootDirectory = '/Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS'
if not os.path.exists(rootDirectory):
    os.makedirs(rootDirectory)

index = 0
size = db.ebsCrawl.find().count()

for document in db.ebsCrawl.find():
    path = rootDirectory + '/' + document['testName']
    try:
        if not os.path.exists(path):
            os.makedirs(path)

        if document['kind'] == u'문제':
            urllib.URLopener().retrieve(document['url'], path + '/' + 'problem.pdf')
        elif document['kind'] == u'해설':
            urllib.URLopener().retrieve(document['url'], path + '/' + 'answer.pdf')
    except IOError:
        pass
    index += 1
    print '[ ' + str(index) + ' / ' + str(size) + ' ] : ' + document['testName']