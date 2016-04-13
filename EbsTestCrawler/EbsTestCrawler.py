# coding=utf-8
import re
import BeautifulSoup
from pymongo import MongoClient
from selenium import webdriver
from selenium.webdriver.support.ui import Select


# Functions
def getDownloadUrl(kind, file):
    if kind == u'문제':
        return getDownloadUrlProblem(file)
    elif kind == u'해설':
        return getDownloadUrlAnswer(file)
    else:
        return ''


def getDownloadUrlProblem(file):
    return 'http://wdown.ebsi.co.kr/W61001/01exam' + file


def getDownloadUrlAnswer(file):
    return 'http://wdown.ebsi.co.kr/W61001/01exam' + file


# MongoDB Setup
mongoClient = MongoClient('mongodb://localhost:27017')
db = mongoClient.ReturnA

# Web Setup
driver = webdriver.PhantomJS('/Users/Joowon/Documents/Library/phantomjs-2.1.1-macosx/bin/phantomjs')
driver.get("http://www.ebsi.co.kr/ebs/xip/xipc/previousPaperList.ebs")
select = Select(driver.find_element_by_id('yearNum'))
select.select_by_index(0)
driver.execute_script('subjectChk(\'eng\')', '')

source = open('/Users/Joowon/Desktop/html.txt', 'r').read()

soup = BeautifulSoup.BeautifulSoup(driver.page_source)

count = int(soup.find('b', {'id': 'searchCntArea'}).text)
for x in range(1, count / 10 + 1):
    driver.execute_script('goPage(' + str(x) + ')', '')
    soup = BeautifulSoup.BeautifulSoup(driver.page_source)

    TestListElements = soup.findAll('tr')
    for test in TestListElements:
        testNameList = test.findAll('strong')
        if len(testNameList) == 1:
            for download in test.findAll('a', {'href': re.compile('javascript:goDownLoad')}):
                json = {'testName': testNameList[0].text, 'kind': download.text,
                        'download': re.compile('(?<=\(\').+(?=\'\))').findall(download.get('href'))[0]}
                json['url'] = getDownloadUrl(json['kind'], json['download'])
                db.ebsCrawl.insert_one(json)
