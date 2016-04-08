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
        System.out.println(parseProblemGroupNameStartNumber("[27~41]"));
    }

    public static String removeExceptionalText(String text) {
        try {
            Map<String, String> replaceMap = new HashMap<>();
            replaceMap.put("\\n\\]\\[", "\n");
            replaceMap.put("^( )+", "");
            for (int i = 1; i <= 45; ++i) {
                replaceMap.put("\\n+(?=" + i + "\\.)", "\n");
            }
            for (String key : replaceMap.keySet()) {
                text = Pattern.compile(key, Pattern.MULTILINE).matcher(text).replaceAll(replaceMap.get(key));
            }
        } catch (StackOverflowError ignored) {
        }
        return text;
    }

    public static String parseProblem(String data, int problemNumber) {
        String regex = "(^" + problemNumber + "\\.)(()|(.))+(\\n.+)+(?=(\\n" + (problemNumber + 1) + "\\.))";
        String regexLast = "(^" + problemNumber + "\\.)(()|(.))+(\\n.+)+";
        String result = regexFind(Pattern.compile(regex, Pattern.MULTILINE), data);
        if (result.length() != 0)
            return result;
        else return regexFind(Pattern.compile(regexLast, Pattern.MULTILINE), data);
    }

    public static String parseProblemName(String data) {
        String regex = "(((^\\d)|(^\\d{2}))(?=\\.))";
        return regexFind(Pattern.compile(regex, Pattern.MULTILINE), data);
    }

    public static String parseProblemGroup(String data, int problemNumber) {
        String regex = "(^\\[" + problemNumber + ")(()|( ))(～|~|-)(()|( ))((\\d{2}\\])|(\\d\\]))(()|(.))+(()|(\\n.+))+(?=(\\n" + problemNumber + "\\.))";
        return regexFind(Pattern.compile(regex, Pattern.MULTILINE), data);
    }

    public static String parseProblemGroupWithoutName(String problemGroupText) {
        String regex = "(?<=\\])(.|\\n)+";
        return regexFind(Pattern.compile(regex), problemGroupText);
    }

    public static String parseProblemGroupName(String data) {
        String regex = "((\\[(()|( ))\\d{2})|(\\[(()|( ))\\d))(()|( ))(～|~|-)(()|( ))((\\d{2}(()|( ))\\])|(\\d(()|( ))\\]))";
        return regexFind(Pattern.compile(regex), data);
    }

    public static String parseProblemGroupNameStartNumber(String data) {
        String regex = "([^\\[]\\d(()|(.+))(?=(～|~|-)))";
        return regexFind(Pattern.compile(regex), data);
    }

    public static String parseProblemGroupNameEndNumber(String data) {
        String regex = "(?<=(～|~|-))\\d(()|(.+))(?=\\])";
        return regexFind(Pattern.compile(regex), data);
    }

    public static String parseQuestion(String problemText) {
        String regex = "((?<=\\d\\.)|(?<=\\d{2}\\.)).+((\\n.+)|())((은\\?)|(는\\?)|(오\\.))";
        return regexFind(Pattern.compile(regex), problemText);
    }

    public static String parseOption1(String problemText) {
        String regex = "(?<=①)(.|\\n)+?(?=②)";
        return regexFind(Pattern.compile(regex), problemText);
    }

    public static String parseOption2(String problemText) {
        String regex = "(?<=②)(.|\\n)+?(?=③)";
        return regexFind(Pattern.compile(regex), problemText);
    }

    public static String parseOption3(String problemText) {
        String regex = "(?<=③)(.|\\n)+?(?=④)";
        return regexFind(Pattern.compile(regex), problemText);
    }

    public static String parseOption4(String problemText) {
        String regex = "(?<=④)(.|\\n)+?(?=⑤)";
        return regexFind(Pattern.compile(regex), problemText);
    }

    public static String parseOption5(String problemText) {
        String regex = "(?<=⑤)(.|\\n)+";
        return regexFind(Pattern.compile(regex), problemText);
    }
}