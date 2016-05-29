import requests
from pymongo import MongoClient
import threading


def isQuestionExist(question):
    return False if db.Question.find({'_id': question}).count() == 0 else True


def insertQuestion(question, api):
    print question
    db.Question.insert_one({
        '_id': question,
        'API': api
    })


def crawling(api):
    result = requests.post('http://' + api['server'] + api['url'], api['data'], headers={
        'Accept-Language': 'en-us',
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/x-www-form-urlencoded',
        'Origin': 'http://www.ebsi.co.kr',
        'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A',
        'Cookie': cookie
    }).content
    for question in result.split(','):
        question = question.strip()
        '' if isQuestionExist(question) else insertQuestion(question, api['_id'])


def crawlingLoop(api):
    while True:
        crawling(api)


# MongoDB Setup
mongoClient = MongoClient('mongodb://localhost:27017')
db = mongoClient.ReturnA

# Login to EBS
from selenium import webdriver

driver = webdriver.PhantomJS('/Users/Joowon/Documents/Library/phantomjs-2.1.1-macosx/bin/phantomjs')
driver.get("http://www.ebsi.co.kr/ebs/pot/potl/login.ebs")

driver.find_element_by_name('userid').send_keys('joowon5249')
driver.find_element_by_name('passwd').send_keys('ii3927098')
driver.find_element_by_class_name('btnLogin').click()

cookie = ''
for rawCookie in driver.get_cookies():
    cookie += rawCookie['name'] + '=' + rawCookie['value'] + ';'

for api in db.ebsCallDataAPI.find():
    th = threading.Thread(target=crawlingLoop, args=(api,))
    th.start()