# coding=utf-8
import re
import BeautifulSoup
from pymongo import MongoClient
from selenium import webdriver
from selenium.webdriver.support.ui import Select


# Functions
def getScript():
    return open('previousSearchGo.js', 'r').read()


# MongoDB Setup
mongoClient = MongoClient('mongodb://localhost:27017')
db = mongoClient.ReturnA


def insertCallDataAPI(grade, area, subject, data):
    json = {
        'server': 'www.ebsi.co.kr',
        'url': '/ebs/xip/xipc/previousPaperSearchAjax.ebs',
        'data': data,
        'grade': grade,
        'area': area,
        'subject': subject
    }
    db.ebsCallDataAPI.insert_one(json)


# Web Setup
driver = webdriver.PhantomJS('/Users/Joowon/Documents/Library/phantomjs-2.1.1-macosx/bin/phantomjs')
driver.get("http://www.ebsi.co.kr/ebs/xip/xipc/previousPaperSearch.ebs")

# Select Setup
selectGrade = Select(driver.find_element_by_id('vGrade'))
for grade in range(0, 3):
    selectGrade.select_by_index(grade)
    selectArea = Select(driver.find_element_by_id('vArSelect'))
    selectArea.select_by_index(3)
    selectSubject = Select(driver.find_element_by_id('vSubject'))

    for subject in range(1, 3):
        selectSubject.select_by_index(subject)

        for element in driver.find_elements_by_class_name('collapsed'):
            element.click()

        count = driver.execute_script("return jQuery('[name=chkTree]').length")
        for a in range(1, count):
            script = "var subjects = jQuery('[name=chkTree]');" \
                     "for (var i=0; i<subjects.length;++i){" \
                     "subjects[i].className = 'checkbox';}" \
                     "subjects[" + str(a) + "].className = 'checkbox checked';"
            driver.execute_script(script)
            # print str(grade) + " " + str(subject) + " " + str(a) + " " + driver.execute_script(getScript())
            insertCallDataAPI(grade, subject, a, driver.execute_script(getScript()))


            # driver.execute_script('subjectChk(\'eng\')', '')
            #
            # source = open('/Users/Joowon/Desktop/html.txt', 'r').read()
            #
            # soup = BeautifulSoup.BeautifulSoup(driver.page_source)
            #
            # count = int(soup.find('b', {'id': 'searchCntArea'}).text)
            # for x in range(1, count / 10 + 1):
            #     driver.execute_script('goPage(' + str(x) + ')', '')
            #     soup = BeautifulSoup.BeautifulSoup(driver.page_source)
            #
            #     TestListElements = soup.findAll('tr')
            #     for test in TestListElements:
            #         testNameList = test.findAll('strong')
            #         if len(testNameList) == 1:
            #             for download in test.findAll('a', {'href': re.compile('javascript:goDownLoad')}):
            #                 json = {'testName': testNameList[0].text, 'kind': download.text,
            #                         'download': re.compile('(?<=\(\').+(?=\'\))').findall(download.get('href'))[0]}
            #                 json['url'] = getDownloadUrl(json['kind'], json['download'])
            #                 db.ebsCrawl.insert_one(json)
