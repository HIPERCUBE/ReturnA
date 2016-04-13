package com.joowon.returnA.classifier.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * Copyright (c) 4/11/16 Joowon Ryoo
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
public class SolveParser extends Parser {
    public static void main(String[] args) throws InterruptedException {
        System.out.println();
    }

    public static String removeExceptionalText(String text) {
        try {
            Map<String, String> replaceMap = new HashMap<>();
            replaceMap.put("", "");
            for (String key : replaceMap.keySet()) {
                text = Pattern.compile(key, Pattern.MULTILINE).matcher(text).replaceAll(replaceMap.get(key));
            }
        } catch (StackOverflowError ignored) {
        }
        return text;
    }

    public static String parseTestName(String text) {
        String regex = "\\d{4}학년도.+문제지";
        return regexFind(Pattern.compile(regex), text);
    }

    public static List<String> parseAnswers(String text) {
        String regexAnswerArea = "[0-50](.|\\n)(.|\\n)?(①|②|③|④|⑤)(.|\\n)+?(?=([0-50]))";
        String regexAnswer = "(①|②|③|④|⑤)";
        List<String> answers = regexFindAsList(Pattern.compile(regexAnswerArea), text);
        answers.replaceAll(s -> regexFind(Pattern.compile(regexAnswer), s));
        return answers;
    }
}
