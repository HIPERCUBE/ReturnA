package com.joowon.returnA.classifier.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (c) 3/31/16 Joowon Ryoo
 * <p>
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
public class HeadlineParser extends Parser {

    public static void main(String[] args) {
        String data = "2012학년도 9월 고2 전국연합학력평가 문제지\n" +
                "영어 영역 (A형)B형\n" +
                " \n" +
                "성명\n" +
                "수험 번호\n" +
                "2\n" +
                "◦ 문제지의 해 란에 성명과 수험 번호를 정확히 쓰시오.\n" +
                "◦답안지의 해 란에 성명과 수험 번호를 쓰고, 또 수험 번호와\n" +
                "1 5. 대화를 듣고, 여자가 구입할  빌을 고르시오.\n" +
                "123\n" +
                "45\n" +
                "6.다음을 듣고,  자가 하는 말의 주제로 가장 적절한 것을 고르시오. [3점]\n" +
                "1 개인 위생의 중요성\n" +
                "2 비타민 C의 다양한 효능\n" +
                "3 여행 전 예방접종의 필요성\n" +
                "4 여행 중 건강을 지키는 방법\n" +
                "5 수분 섭취가 건강에 미치는 영향\n" +
                "7. 대화를 듣고,  자가 해야 할 일로 가장 적절한 것을 고르시오.\n" +
                "답을 정확히 표시하시오.\n" +
                "◦문항에 따라 배점이 다르니, 각 물음의 끝에 표시된 배점을";
        System.out.println(data);
        System.out.println(parseTestName(data));
        System.out.println(parseTestType(data));
    }


    public static String parseTestName(String data) {
        return regexFind(Pattern.compile("\\d{4}학년도.+문제지"), data);
    }

    public static String parseTestType(String data) {
        return regexFind(Pattern.compile("(A|B)형"), data);
    }
}
