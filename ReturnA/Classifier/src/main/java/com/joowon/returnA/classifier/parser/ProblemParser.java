package com.joowon.returnA.classifier.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Copyright (c) 4/3/16 Joowon Ryoo
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
public class ProblemParser extends Parser {
    public static void main(String[] args) throws InterruptedException {
//        String reformedText = removeExceptionalText(text);
//        for (int i = 1; i <= 45; ++i) {
//            String group = parseProblemGroup(reformedText, i);
//            System.out.println(group);
//            if (group.length() != 0)
//                reformedText = reformedText.replace(group, "");
//            System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = =\n");
//            System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = =");
////            Thread.sleep(300);
//        }
//        reformedText = removeExceptionalText(reformedText);
//        for (int i = 1; i <= 45; ++i) {
//            System.out.println(parseProblem(reformedText, i));
//            System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = =\n");
//            System.out.println("= = = = = = = = = = = = = = = = = = = = = = = = = = =");
////            Thread.sleep(300);
//        }
    }

    public static String removeExceptionalText(String text) {
        Map<String, String> replaceMap = new HashMap<>();
        replaceMap.put("\\n\\]\\[", "\n");
        replaceMap.put("^( )+", "");
        for (int i = 1; i <= 45; ++i) {
            replaceMap.put("\\n+(?=" + i + "\\.)", "\n");
        }
        for (String key : replaceMap.keySet()) {
            text = Pattern.compile(key, Pattern.MULTILINE).matcher(text).replaceAll(replaceMap.get(key));
        }
        return text;
    }

    public static String parseProblem(String data, int problemNumber) {
        String regex = "(^" + problemNumber + "\\.)(()|(.))+(\\n.+)+";
        if (problemNumber != 45) {
            regex += "(?=(\\n" + (problemNumber + 1) + "\\.))";
        }
        return regexFind(Pattern.compile(regex, Pattern.MULTILINE), data);
    }

    public static String parseProblemName(String data) {
        String regex = "(((^\\d)|(^\\d{2}))(?=\\.))";
        return regexFind(Pattern.compile(regex, Pattern.MULTILINE), data);
    }

    public static String parseProblemGroup(String data, int problemNumber) {
        String regex = "(^\\[" + problemNumber + ")(()|( ))(～|~)(()|( ))((\\d{2}\\])|(\\d\\]))(()|(.))+(()|(\\n.+))+(?=(\\n" + problemNumber + "\\.))";
        return regexFind(Pattern.compile(regex, Pattern.MULTILINE), data);
    }

    public static String parseProblemGroupName(String data) {
        String regex = "((\\[\\d{2})|(\\[\\d))(()|( ))(～|~)(()|( ))((\\d{2}\\])|(\\d\\]))";
        return regexFind(Pattern.compile(regex), data);
    }
}