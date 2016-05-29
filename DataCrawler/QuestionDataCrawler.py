# coding=utf-8
from pymongo import MongoClient
import requests
import base64
import sys
import time

reload(sys)
sys.setdefaultencoding('utf-8')


def crawling(id):
    xml1 = requests.get('http://www.ebsi.co.kr/ebs/xip/xipSolve/Item.ebs', 'Action=Select&itemId=' + id).content
    xml2 = requests.get('http://www.ebsi.co.kr/ebs/xip/xipSolve/Items.ebs', 'Action=SelectGroup&itemId=' + id).content
    db.Question.update({'_id': id}, {
        '$set': {
            'xml': {
                '1': decode_base64(xml1),
                '2': decode_base64(xml2)
            }
        }
    })
    print id


def decode_base64(data):
    """Decode base64, padding being optional.

    :param data: Base64 data as an ASCII byte string
    :returns: The decoded byte string.
    """
    data = data.replace('_____', '/')
    missing_padding = 4 - len(data) % 4
    if missing_padding:
        data += b'=' * missing_padding
    return base64.decodestring(data)


# codecs.open('result.html', 'w', 'utf-8').write(decode_base64(str))

# MongoDB Setup
mongoClient = MongoClient('mongodb://localhost:27017')
db = mongoClient.ReturnA

for question in db.Question.find({'xml': {'$exists': False}}):
    crawling(question['_id'])
    time.sleep(11)
