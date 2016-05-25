function sleep(num) {	//[1/1000초]
    var now = new Date();
    var stop = now.getTime() + num;
    while (true) {
        now = new Date();
        if (now.getTime() > stop)return;
    }
}
function previousSearchGoMine() {
    //분류체계 값 셋팅
    var treeArray = jQuery('[name=chkTree]');//선택 분류체계
    var catDataArray = [];
    var larcatArray = [];
    var ingcatArray = [];
    var catCompleteArray = [];
    var larcatList = '';
    var ingcatList = '';
    var smacatList = '';

    //선택 분류체계 배열생성
    for (var i = 0; i < treeArray.length; i++) {
        if (jQuery(treeArray[i]).hasClass('checked')) {
            catDataArray.push(new Array(jQuery(treeArray[i]).attr('catId'), jQuery(treeArray[i]).attr('catLvl'), jQuery(treeArray[i]).attr('larcat'), jQuery(treeArray[i]).attr('ingcat'), 'Y'));
        }
    }

    //대분류,중분류 배열생성
    for (var i = 0; i < catDataArray.length; i++) {
        if (catDataArray[i][1] == 4) {
            larcatArray.push(catDataArray[i][2]);
        }
        if (catDataArray[i][1] == 5) {
            ingcatArray.push(catDataArray[i][2]);
        }
    }

    //중분류,소분류 미사용 분류 확인
    for (var i = 0; i < catDataArray.length; i++) {
        if (catDataArray[i][1] == 5 || catDataArray[i][1] == 6) {
            for (var y = 0; y < larcatArray.length; y++) {
                if (catDataArray[i][2] == larcatArray[y]) {
                    catDataArray[i][4] = 'N';
                }
            }
        }
        if (catDataArray[i][1] == 6) {
            for (var y = 0; y < ingcatArray.length; y++) {
                if (catDataArray[i][2] == ingcatArray[y]) {
                    catDataArray[i][4] = 'N';
                }
            }
        }
    }

    //대중소 분류 구별하여 파라미터 생성
    for (var i = 0; i < catDataArray.length; i++) {
        if (catDataArray[i][4] == 'Y') {
            if (catDataArray[i][1] == '4') {
                larcatList += ',' + catDataArray[i][0];
            } else if (catDataArray[i][1] == '5') {
                ingcatList += ',' + catDataArray[i][0];
            } else if (catDataArray[i][1] == '6') {
                smacatList += ',' + catDataArray[i][0];
            }
        }
    }
    larcatList = larcatList.substr(1, larcatList.length);
    ingcatList = ingcatList.substr(1, ingcatList.length);
    smacatList = smacatList.substr(1, smacatList.length);

    var vGrade = jQuery('#vGrade').val();//학년
    var vGradeCat = 126;

    if ('D100' == vGrade) {
        vGradeCat = 17006;
    } else if ('D200' == vGrade) {
        vGradeCat = 17000;
    } else {
        vGradeCat = 126;
    }

    var vArSelect = jQuery('#vArSelect').val();//영역
    var vSubject = jQuery('#vSubject').val();//과목

    if ('' == vSubject) {
        alert('영역과 과목을 선택해주세요.');
        return;
    }

    var vPointAll = 0;//배점 전체
    var vPoint1 = 0;//배점 1점
    var vPoint2 = 0;//배점 2점
    var vPoint3 = 0;//배점 3점
    var vPoint4 = 0;//배점 4점

    var checkboxElementAll = document.getElementsByName("vPointAll");
    var checkboxElement1 = document.getElementsByName("vPoint1");
    var checkboxElement2 = document.getElementsByName("vPoint2");
    var checkboxElement3 = document.getElementsByName("vPoint3");
    var checkboxElement4 = document.getElementsByName("vPoint4");

    if (checkboxElementAll[0].checked == true) vPointAll = checkboxElementAll[0].value;
    if (checkboxElement1[0].checked == true) vPoint1 = checkboxElement1[0].value;
    if (checkboxElement2[0].checked == true) vPoint2 = checkboxElement2[0].value;
    if (checkboxElement3[0].checked == true) vPoint3 = checkboxElement3[0].value;
    if (checkboxElement4[0].checked == true) vPoint4 = checkboxElement4[0].value;

    if (vPointAll == 0 & vPoint1 == 0 & vPoint2 == 0 & vPoint3 == 0 & vPoint4 == 0) {
        alert('배점을 하나 이상 선택해 주세요.');
        return;
    }

    var vWrongRate = jQuery('#vWrongRate').val();//오답률

    var vItemCnt = jQuery('#vItemCnt').val();//검색하려는 문항수
    vItemCnt = 500;
    jQuery('#vItemCnt').val(vItemCnt);

    var vBookStartYear = jQuery('#vBookStartYear').val();//교재 출제년도시작
    var vBookEndYear = jQuery('#vBookEndYear').val();//교재 출제년도종료

    var vStartYear = jQuery('#vStartYear').val();//기출 출제년도시작
    var vEndYear = jQuery('#vEndYear').val();//기출 출제년도종료

    var chkElement = '';
    var radioElement = document.getElementsByName("searchType");
    for (var i = 0; i < radioElement.length; i++) {
        if (radioElement[i].checked == true)
            chkElement = radioElement[i].value;
    }

    if (vBookStartYear > vBookEndYear && ( chkElement == '0' || chkElement == '1')) {
        alert('출제년도의 시작년도는 종료년도보다\n같거나 작아야 됩니다.');
        return;
    }

    if (vStartYear > vEndYear && ( chkElement == '0' || chkElement == '2' )) {
        alert('출제년도의 시작년도는 종료년도보다\n같거나 작아야 됩니다.');
        return;
    }

    var checkboxExamGbnCd = document.getElementsByName("vExamGbnCd");
    var vExamGbnCd = '';

    for (var i = 0; i < checkboxExamGbnCd.length; i++) {
        if (checkboxExamGbnCd[i].checked == true)
            vExamGbnCd += ',' + checkboxExamGbnCd[i].value;
    }

    vExamGbnCd = vExamGbnCd.substr(1, vExamGbnCd.length);

    var vCheckDupl = '';//풀어본문항제외
    var checkboxElementDupl = document.getElementsByName("vCheckDupl");
    if (checkboxElementDupl[0].checked == true) {
        vCheckDupl = 1;
    } else {
        vCheckDupl = 0;
    }

    jQuery('#paperSearchFrm > #targetCd').val(vGrade);
    jQuery('#paperSearchFrm > #gradeCat').val(vGradeCat);
    jQuery('#paperSearchFrm > #setSubjectList').val(vSubject);

    jQuery('#paperSearchFrm > #set_larcatList').val(larcatList);
    jQuery('#paperSearchFrm > #set_ingcatList').val(ingcatList);
    jQuery('#paperSearchFrm > #set_smacatList').val(smacatList);

    jQuery('#paperSearchFrm > #searchTypeSet').val(chkElement);
    jQuery('#paperSearchFrm > #bookStartYear').val(vBookStartYear + '0101000000');
    jQuery('#paperSearchFrm > #bookEndYear').val(vBookEndYear + '1231235959');
    jQuery('#paperSearchFrm > #startYear').val(vStartYear);
    jQuery('#paperSearchFrm > #endYear').val(vEndYear);
    jQuery('#paperSearchFrm > #set_examGbnCd').val(vExamGbnCd);

    jQuery('#paperSearchFrm > #pointAll').val(vPointAll);
    jQuery('#paperSearchFrm > #point1').val(vPoint1);
    jQuery('#paperSearchFrm > #point2').val(vPoint2);
    jQuery('#paperSearchFrm > #point3').val(vPoint3);
    jQuery('#paperSearchFrm > #point4').val(vPoint4);
    jQuery('#paperSearchFrm > #wrongRate').val(vWrongRate);
    jQuery('#paperSearchFrm > #itemCnt').val(vItemCnt);
    jQuery('#paperSearchFrm > #checkDupl').val(vCheckDupl);

    //waiting = 1;
    sleep(400);
    //setTimeout(function () {
    //    /*
    //     fnAjaxCall({
    //     url : '/ebs/xip/xipc/previousPaperSearchAjax.ebs',
    //     params : jQuery('#paperSearchFrm').serialize(),
    //     callback : fnAjaxResultSet
    //     });
    //     */
    //    jQuery.ajax({
    //        url: '/ebs/xip/xipc/previousPaperSearchAjax.ebs',
    //        type: 'POST',
    //        data: jQuery('#paperSearchFrm').serialize(),
    //        async: false,
    //        success: function (data) {
    //            waiting = data;
    //            console.log(jQuery('#paperSearchFrm').serialize());
    //            console.log(data);
    //        },
    //        error: function (data) {
    //        }
    //    });
    //}, 400);
    return jQuery('#paperSearchFrm').serialize();
}
return previousSearchGoMine();