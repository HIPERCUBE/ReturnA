package com.joowon.returnA.classifier.parser;

import com.joowon.returnA.classifier.parser.model.Solve;

import java.util.ArrayList;
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
        System.out.println(parseAnswers(string));
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

    public static List<Solve> parseAnswers(String text) {
        String regexAnswerArea = "\\d(.|\\n)(.|\\n)?(①|②|③|④|⑤)(.|\\n)+?(?=\\d)";
        String regexProblemNumber = "\\d{2}|\\d";
        String regexAnswer = "(①|②|③|④|⑤)";
        List<String> answers = regexFindAsList(Pattern.compile(regexAnswerArea), text);

        List<Solve> result = new ArrayList<>();
        for (String answer : answers) {
            try {
                Solve solve = new Solve(
                        Integer.parseInt(regexFind(Pattern.compile(regexProblemNumber), answer)),
                        regexFind(Pattern.compile(regexAnswer), answer)
                );
                if (solve.answer.length() != 0)
                    result.add(solve);
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    private static String string = "/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/bin/java -Didea.launcher.port=7532 \"-Didea.launcher.bin.path=/Applications/IntelliJ IDEA 15.app/Contents/bin\" -Dfile.encoding=UTF-8 -classpath \"/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/lib/tools.jar:/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/build/classes/main:/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/build/resources/main:/Users/Joowon/.gradle/caches/modules-2/files-2.1/org.mongodb/mongodb-driver/3.0.4/993f351546d3e462126eefb901383ccec4049c6f/mongodb-driver-3.0.4.jar:/Users/Joowon/.gradle/caches/modules-2/files-2.1/org.mongodb/bson/3.0.4/2ecf10d61967c2c0076a6f561088a0e15848c212/bson-3.0.4.jar:/Users/Joowon/.gradle/caches/modules-2/files-2.1/org.mongodb/mongodb-driver-core/3.0.4/adbea64839bb93c5ac07b93d9453a8710ff6ecc9/mongodb-driver-core-3.0.4.jar:/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/libs/commons-cli-1.3.1.jar:/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/libs/opencv-300.jar:/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/libs/pdfbox-app-2.0.0.jar:/Users/Joowon/.gradle/caches/modules-2/files-2.1/org.mongodb.morphia/morphia/1.1.0/9c42a813d09c7fc332e5be6bfa795dc6a9fc0e98/morphia-1.1.0.jar:/Users/Joowon/.gradle/caches/modules-2/files-2.1/org.mongodb/mongo-java-driver/3.0.2/7d3880dec6c8a2f07c1f544776c326834b513712/mongo-java-driver-3.0.2.jar:/Users/Joowon/.gradle/caches/modules-2/files-2.1/com.thoughtworks.proxytoys/proxytoys/1.0/d22baee6d1b98dc38a6a428f774fd9e5b2ccf4af/proxytoys-1.0.jar:/Users/Joowon/.gradle/caches/modules-2/files-2.1/cglib/cglib-nodep/2.2.2/d456bb230c70c0b95c76fb28e429d42f275941/cglib-nodep-2.2.2.jar:/Applications/IntelliJ IDEA 15.app/Contents/lib/idea_rt.jar\" com.intellij.rt.execution.application.AppMain com.joowon.returnA.classifier.SolveClassifier -t /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS\n" +
            "java.io.FileNotFoundException: /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/.DS_Store/answer.pdf (Not a directory)\n" +
            "\tat java.io.RandomAccessFile.open0(Native Method)\n" +
            "\tat java.io.RandomAccessFile.open(RandomAccessFile.java:316)\n" +
            "\tat java.io.RandomAccessFile.<init>(RandomAccessFile.java:243)\n" +
            "\tat org.apache.pdfbox.io.RandomAccessBufferedFileInputStream.<init>(RandomAccessBufferedFileInputStream.java:99)\n" +
            "\tat org.apache.pdfbox.pdmodel.PDDocument.load(PDDocument.java:840)\n" +
            "\tat org.apache.pdfbox.pdmodel.PDDocument.load(PDDocument.java:803)\n" +
            "\tat org.apache.pdfbox.pdmodel.PDDocument.load(PDDocument.java:757)\n" +
            "\tat com.joowon.returnA.classifier.SolveClassifier.main(SolveClassifier.java:56)\n" +
            "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
            "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
            "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
            "\tat java.lang.reflect.Method.invoke(Method.java:497)\n" +
            "\tat com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_1.png [1/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_2.png [2/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_3.png [3/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_4.png [4/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_5.png [5/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_6.png [6/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_7.png [7/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_8.png [8/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_9.png [9/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_10.png [10/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_11.png [11/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_12.png [12/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_13.png [13/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_14.png [14/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_15.png [15/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_16.png [16/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_17.png [17/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_18.png [18/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_19.png [19/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_20.png [20/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_21.png [21/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_22.png [22/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_23.png [23/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_24.png [24/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_25.png [25/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_26.png [26/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_27.png [27/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_28.png [28/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_29.png [29/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어/answer/image_30.png [30/30]\n" +
            "Name : 2007년 2008 수능 외국어\n" +
            "= = = = = = = = = = = = = = = = = = = =\n" +
            "[\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][Perception of Color)\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "]\n" +
            "= = = = = = = = = = = = = = = = = = = =\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_1.png [1/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_2.png [2/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_3.png [3/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_4.png [4/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_5.png [5/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_6.png [6/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_7.png [7/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_8.png [8/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_9.png [9/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_10.png [10/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_11.png [11/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_12.png [12/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_13.png [13/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_14.png [14/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_15.png [15/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_16.png [16/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_17.png [17/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_18.png [18/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_19.png [19/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_20.png [20/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_21.png [21/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_22.png [22/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_23.png [23/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_24.png [24/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_25.png [25/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_26.png [26/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_27.png [27/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_28.png [28/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_29.png [29/30]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 2008 수능 외국어 짝수형/answer/image_30.png [30/30]\n" +
            "Name : 2007년 2008 수능 외국어 짝수형\n" +
            "= = = = = = = = = = = = = = = = = = = =\n" +
            "[\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][Perception of Color)\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "]\n" +
            "= = = = = = = = = = = = = = = = = = = =\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 10월 학력평가(서울) 외국어/answer/image_1.png [1/4]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 10월 학력평가(서울) 외국어/answer/image_2.png [2/4]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 10월 학력평가(서울) 외국어/answer/image_3.png [3/4]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 10월 학력평가(서울) 외국어/answer/image_4.png [4/4]\n" +
            "Name : 2007년 고3 10월 학력평가(서울) 외국어\n" +
            "= = = = = = = = = = = = = = = = = = = =\n" +
            "[• 3교시 외국어(영어) 영역 •\n" +
            "정 답\n" +
            "1 ⑤ 2 ① 3 ① 4 ③ 5 ④\n" +
            "6 ③ 7 ② 8 ③ 9 ② 10 ①\n" +
            "11 ⑤ 12 ③ 13 ⑤ 14 ② 15 ②\n" +
            "16 ④ 17 ④ 18 ① 19 ② 20 ⑤\n" +
            "21 ② 22 ⑤ 23 ④ 24 ① 25 ⑤\n" +
            "26 ④ 27 ③ 28 ① 29 ③ 30 ②\n" +
            "31 ① 32 ② 33 ⑤ 34 ③ 35 ④\n" +
            "36 ⑤ 37 ④ 38 ③ 39 ④ 40 ②\n" +
            "41 ① 42 ③ 43 ① 44 ④ 45 ①\n" +
            "46 ③ 47 ② 48 ⑤ 49 ③ 50 ⑤\n" +
            "해 설\n" +
            "1. [출제의도] 그림 이해\n" +
            "M: Hello. May I help you?\n" +
            "W: Yes, please. I want to buy a lamp for my \n" +
            "bedroom. I'm going to put it on the bedside table.\n" +
            "M: Do you have any particular type in mind?\n" +
            "W: I'd like the one made from traditional Korean paper.\n" +
            "M: I get it. Then what do you think about this one?\n" +
            "W: Which one do you mean?\n" +
            "M: The pyramid\u00ADshaped one with a bulb on the top.\n" +
            "W: I think it's too simple.\n" +
            "M: Then how about this fan\u00ADshaped one? It has two \n" +
            "bulbs.\n" +
            "W: Hmm... I'm afraid it's a little bit large.\n" +
            "M: Well, how about this? It's very popular.\n" +
            "W: You mean the vase\u00ADshaped one? Oh, I like that. \n" +
            "It looks like a vase with three flowers in it.\n" +
            "M: You made a good choice. You'll be satisfied with it.\n" +
            "2. [출제의도] 심정 추론\n" +
            "W: Hi, Bob. What's up?\n" +
            "M: Do you know when your mother's birthday is?\n" +
            "W: Of course. It's May 12th. It's quite easy to \n" +
            "remember because it's just a week before my \n" +
            "birthday.\n" +
            "M: You are a good daughter, Susan.\n" +
            "W: What do you mean?\n" +
            "M: Actually, yesterday was my mother's birthday, \n" +
            "but I forgot it.\n" +
            "W: What? You mean you didn't even buy flowers \n" +
            "for your mother?\n" +
            "M: Yeah. Now, I don't know what to do. My mother \n" +
            "didn't say anything to me, but she must have \n" +
            "been disappointed.\n" +
            "W: No one in your family reminded you?\n" +
            "M: No. My father bought two opera tickets for her, \n" +
            "my sister wrote a lovely hand\u00ADwritten poem, but \n" +
            "I did nothing for her. What should I do?\n" +
            "W: You should have done something earlier, but I \n" +
            "think it's better late than never.\n" +
            "3. [출제의도] 담화 내용 이해\n" +
            "M: Traditional Korean houses have a unique \n" +
            "structure. They commonly use floor heating and \n" +
            "usually have an inner wing and an outer wing. \n" +
            "The inner wing normally consists of a living \n" +
            "room, a kitchen and a wooden\u00ADfloored central \n" +
            "hall. The individual layout largely depends on \n" +
            "the wealth of the family. The wealthier a \n" +
            "family, the larger the house. Houses differ in \n" +
            "shape according to the region as well. In the \n" +
            "][cold northern parts of Korea, houses are built in \n" +
            "a closed square form to retain the heat better. \n" +
            "In the central regions, houses are 'L' shaped. \n" +
            "Houses in the southern regions of Korea are \n" +
            "built in an open 'I' form. \n" +
            "4. [출제의도] 세부 내용 이해\n" +
            "[Telephone rings.]\n" +
            "W: Hello.\n" +
            "M: Hello, Jean. This is Peter.\n" +
            "W: Hi, Peter. How are you? I've heard that you're \n" +
            "very busy preparing for the chorus contest.\n" +
            "M: Yeah, I reserved a bus yesterday and our \n" +
            "uniforms will arrive this afternoon. \n" +
            "W: Your students are really excited, aren't they?\n" +
            "M: Absolutely! But we have a big problem.\n" +
            "W: A problem? What's that?\n" +
            "M: David sprained his finger, so we have no one to \n" +
            "play the piano. Jean, can you play for us?\n" +
            "W: What? Are you serious? There's only 3 days left. \n" +
            "How about Lisa? She's good at playing the piano.\n" +
            "M: I've already called her. But she is planning to go \n" +
            "abroad to take pictures of historic buildings.\n" +
            "W: I really want to help you, but I'm not sure if I \n" +
            "can do that. Three days is not enough time to \n" +
            "practice.\n" +
            "M: But there's no other way. Please help us. \n" +
            "5. [출제의도] 대화자의 관계 추론\n" +
            "M: Excuse me. May I speak to you for a minute?\n" +
            "W: Sure. How can I help you?\n" +
            "M: I'm having a problem with your assignment. \n" +
            "W: What sort of problem are you having? \n" +
            "M: I've been to the library several times, but all \n" +
            "the books are checked out already.\n" +
            "W: Sounds like you should have checked out the \n" +
            "books sooner.\n" +
            "M: Well, I had to prepare for a really big \n" +
            "presentation, so I couldn't spare extra time for \n" +
            "anything else. Would it be possible for me to \n" +
            "have more time?\n" +
            "W: Well, it is possible, but extensions are only \n" +
            "given for medical reasons.\n" +
            "M: I promise I'll submit the report by next Monday.\n" +
            "W: I'm sorry, but I can't help you.\n" +
            "M: Oh, well. I guess I have no other choice but to \n" +
            "try to meet the due date.\n" +
            "6. [출제의도] 세부 내용 이해\n" +
            "W: Hi, nice to meet you.\n" +
            "M: Hi, my name is Paul Davis. Nice to meet you.\n" +
            "W: Let me ask you some questions. Have you ever \n" +
            "worked at a Chinese restaurant before?\n" +
            "M: Yes, I used to work at one near City Hall.\n" +
            "W: Can I ask what you did there and why you're \n" +
            "not working there anymore?\n" +
            "M: I worked as a waiter, but the restaurant closed down.\n" +
            "W: I see. Well, do you have a motorcycle driver's \n" +
            "license? We need a person who can drive a \n" +
            "motorcycle.\n" +
            "M: Yes, I do. I got it two years ago.\n" +
            "W: Good. Are you familiar with this area?\n" +
            "M: Yes. I have lived here all my life. \n" +
            "W: All right. You are hired. Then, when can you start?\n" +
            "M: Anytime will be okay.\n" +
            "7. [출제의도] 담화의 목적 추론\n" +
            "W: During and after a snowstorm, the bus route and \n" +
            "schedule for the hospital employees will change. \n" +
            "The bus will run 15 minutes early, so be at \n" +
            "your stop early. The bus will not stop at Bell \n" +
            "Square. Instead, employees who board at that \n" +
            "][\n" +
            "][stop will be picked up at the Lakeside Mall \n" +
            "stop. Olive Street will be closed, so people who \n" +
            "usually get on at that stop must walk to the \n" +
            "Spring Street stop. The shuttle service may be \n" +
            "cancelled due to the heavy snowstorm. Any \n" +
            "cancellations will be announced in advance.\n" +
            "8. [출제의도] 세부 내용 이해\n" +
            "M: I'd like to buy two bicycles for my twin boys.\n" +
            "W: How old are they?\n" +
            "M: Ten years old. This bicycle looks good. How \n" +
            "much is it?\n" +
            "W: It's $200. It's brand\u00ADnew and quite light.\n" +
            "M: I'm afraid it's somewhat expensive.\n" +
            "W: Then, how about this one? It's only $150.\n" +
            "M: Is it safe for kids?\n" +
            "W: Of course. I guarantee it.\n" +
            "M: Okay. I'll take two of them. And I also need two \n" +
            "helmets.\n" +
            "W: This helmet is good quality and it's only $20.\n" +
            "M: All right. I'll also buy two of them. Then how \n" +
            "much is it altogether?\n" +
            "W: Two bicycles, and two helmets... wait a second. \n" +
            "Let me calculate.\n" +
            "9. [출제의도] 장소 추론\n" +
            "M: Hi, Jane. What a surprise to see you here!\n" +
            "W: Hey, Kevin! Long time no see.\n" +
            "M: Indeed! I think last time I saw you was at your \n" +
            "farewell party.\n" +
            "W: I came back last month. I really missed you.\n" +
            "M: I missed you, too. And what brings you here?\n" +
            "W: My sister just gave birth. I'm going to the \n" +
            "hospital to see my new niece.\n" +
            "M: Congratulations! You must be here to buy some \n" +
            "flowers for your niece.\n" +
            "W: Yes. And, what are you doing here?\n" +
            "M: Well, I just bought some roses for my wife. \n" +
            "Today's our wedding anniversary.\n" +
            "W: You are very romantic. If you are not in a \n" +
            "hurry, will you help me with my flowers?\n" +
            "M: How about the pink and white flower basket over \n" +
            "there? A perfect bouquet for a perfect niece.\n" +
            "10. [출제의도] 세부 내용 이해\n" +
            "W: Chris, do you have any plans this afternoon?\n" +
            "M: Nothing special. Why are you asking, Sally?\n" +
            "W: Do you remember my host family in Korea that \n" +
            "I told you about?\n" +
            "M: Yeah. The Kims. And their daughter, Yu\u00ADmi. \n" +
            "W: Right. Yu\u00ADmi will be staying with us for one year. \n" +
            "She will take an intensive English course here.\n" +
            "M: Good for her! When is she coming? \n" +
            "W: She will be arriving at 2:30 this afternoon.\n" +
            "M: This afternoon? So do you need a ride?\n" +
            "W: Actually, I am supposed to meet her at the \n" +
            "airport, but I've got an unexpected meeting this \n" +
            "afternoon. So I'm thinking...\n" +
            "M: I could pick her up at the airport for you.\n" +
            "W: Really? Thanks a lot!\n" +
            "M: Sure. Besides, it's a good opportunity to \n" +
            "practice my Korean.\n" +
            "11. [출제의도] 세부 내용 이해\n" +
            "[Telephone rings.]\n" +
            "W: Hello. Ace Sports Facility Service. How may I \n" +
            "help you?\n" +
            "M: Hi. I'd like to book a soccer field.\n" +
            "W: Sure. May I have your name and phone number, \n" +
            "please?\n" +
            "M: My name is James Howard and my phone \n" +
            "number is 640-7321.\n" +
            "][W: When are you going to use it?\n" +
            "M: We're going to have a soccer match next Friday. \n" +
            "I mean Oct. 19th.\n" +
            "W: Let me see... Oh, next Friday is okay. What \n" +
            "time will you start the game?\n" +
            "M: At 4 o'clock. And the game will last for about 3 \n" +
            "hours.\n" +
            "W: Okay, good. If you use the soccer field for 3 \n" +
            "hours, you have to pay $50, and an extra fee \n" +
            "will be added if you use it for more than 3 \n" +
            "hours. It's $20 per hour.\n" +
            "M: I understand, but the match won't last over 3 \n" +
            "hours.\n" +
            "W: I see. Then you will not have to pay the extra fee.\n" +
            "12. [출제의도] 담화의 내용 이해\n" +
            "M: Last July, the Live Earth concerts took place in \n" +
            "nine different cities on all seven continents of \n" +
            "the world, including major cities such as New \n" +
            "York, London and Tokyo. The Live Earth \n" +
            "concerts aimed to raise awareness and funds \n" +
            "for global warming. More than 100 of the \n" +
            "biggest stars in pop and rock music performed \n" +
            "around the globe. The creator of the Live Earth \n" +
            "concerts is Al Gore, the former US vice \n" +
            "president. He hoped to reach two billion people \n" +
            "with the shows through radio, television and the \n" +
            "Internet. In fact, over 10 million viewers \n" +
            "watched the concerts worldwide via the Internet.\n" +
            "13. [출제의도] 그림에 맞는 대화 찾기\n" +
            "① W: I'd like to buy a pair of shoes for indoor \n" +
            "rock\u00ADclimbing.\n" +
            "   M: What size do you wear?\n" +
            "② W: Why don't we go rock\u00ADclimbing this weekend?\n" +
            "   M: Rock\u00ADclimbing? No, it's too dangerous.\n" +
            "③ W: Oh, it's so hot today. Do you have something \n" +
            "cold to drink?\n" +
            "   M: Here you go. Cheer up. We're almost there.\n" +
            "④ W: Is that you in this picture?\n" +
            "   M: Yeah, it was taken while I was rock\u00ADclimbing \n" +
            "last month.\n" +
            "⑤ W: Oh, I feel very nervous. It's my first time to \n" +
            "rock\u00ADclimb.\n" +
            "   M: Don't worry. It's absolutely safe if you \n" +
            "follow my instructions.\n" +
            "14. [출제의도] 대화 완성\n" +
            "W: Frank, let me ask you a question. \n" +
            "M: Sure. What is it?\n" +
            "W: If you could be any person in the world, who \n" +
            "would you be?\n" +
            "M: That's easy. Bill Gates!\n" +
            "W: Is there any special reason?\n" +
            "M: He is one of the richest people in the world. \n" +
            "Isn't that a good reason?\n" +
            "W: Ah, so it's the money.\n" +
            "M: Not totally. Bill Gates is also a great donor. He \n" +
            "donates large sums of money every year.\n" +
            "W: Really? \n" +
            "M: Yeah, he saved many children from starving.\n" +
            "W: Amazing. Why else do you like him?\n" +
            "M: ___________________________________\n" +
            "15. [출제의도] 대화 완성\n" +
            "W: You don't look well. What's wrong?\n" +
            "M: Nothing. I'm all right. \n" +
            "W: Come on. It's written all over your face.\n" +
            "M: Yeah, right. I'm mixed up.\n" +
            "W: Oh, tell me now. What happened? I'll help you.\n" +
            "M: Thanks for your kindness but there's nothing \n" +
            "you can do. \n" +
            "W: What makes you say that? Is it that serious?\n" +
            "M: Mm... I don't feel like talking to anybody about it. \n" +
            "][W: Eugene, such an attitude will make you feel \n" +
            "lonely. What are friends for?\n" +
            "M: They can't solve the problem for me. It makes \n" +
            "no difference even if I tell them about it. \n" +
            "W: ___________________________________\n" +
            "16. [출제의도] 대화 완성\n" +
            "W: Dad, do you see the man next to the window?\n" +
            "M: Who? There are three people over there.\n" +
            "W: The one wearing a striped T\u00ADshirt and sunglasses.\n" +
            "M: Ah, I see. So, why? Do you know who he is?\n" +
            "W: Don't be surprised. He is my math teacher.\n" +
            "M: Really? Oh, he doesn't look like a teacher. Look \n" +
            "at those short pants and sunglasses.\n" +
            "W: Dad, this is not a school, but an amusement park.\n" +
            "M: I know. Anyway, he looks very handsome.\n" +
            "W: He's very kind to all of us. I like him very much.\n" +
            "M: ___________________________________\n" +
            "17. [출제의도] 상황에 맞는 표현 추론\n" +
            "W: Cathy is going to have a date with Tony \n" +
            "tonight. She likes him very much and she's been \n" +
            "looking forward to it. Unfortunately, Cathy feels \n" +
            "that she's caught a bad cold. She doesn't feel \n" +
            "well, but she doesn't want to break the date. \n" +
            "Cathy's mother is worried about her and wants \n" +
            "her to stay home. She wants Cathy to call him \n" +
            "and say she can't make it. In this situation, \n" +
            "what would she most probably say to Cathy? \n" +
            "18. [출제의도] 지칭 추론\n" +
            "이것은 대개 곡예사들이 하는 서커스나 휴식을 취할 \n" +
            "수 있는 현관에서 볼 수 있다. 일단 이것이 움직이면 \n" +
            "멈출 때까지 추처럼 계속 움직인다. 이것은 크기와 \n" +
            "모양이 다양하다. 유아용에는 부모나 형제자매가 아\n" +
            "이를 밀어 움직이게 만들 때, 아이를 바른 자세로 지\n" +
            "탱해주는 다리 넣는 구멍이 있다. 좀 더 큰 아이들을 \n" +
            "위해서는 유연한 캔버스 천이나 플라스틱, 또는 나무\n" +
            "판으로 안장을 만들기도 한다. 일반적인 뒤뜰의 풍경\n" +
            "에는 나뭇가지로부터 줄로 양쪽 끝을 매달아 놓은 \n" +
            "나무판자가 있다. 큰 아이들은 훨씬 높이, 때때로 지\n" +
            "상에서 15피트 이상 올라갈 수 있다. \n" +
            "19. [출제의도] 지칭 추론\n" +
            "자동차 전용 극장은 1932년에 한 제약 회사의 소유\n" +
            "주에 의해 처음 만들어졌다. 그것들은 가족들에게 매\n" +
            "우 인기 있었는데 왜냐하면 그것들이 가족 전체가 \n" +
            "영화를 보러갈 수 있게 해 주었기 때문이다. 부모들\n" +
            "은 아이를 볼 사람을 고용할 필요가 없었고, 그들은 \n" +
            "아이들이 관객 전체를 방해할까봐 걱정할 필요도 없\n" +
            "었다. 마침내, 자동차 전용 극장은 현대의 여가 활동\n" +
            "이 되었다. 그것들의 인기는 1950년대 후반과 1960\n" +
            "년대 초반에 절정에 다다랐다. 그러나 그것들은 해가 \n" +
            "저물어야 상영을 시작할 수 있기에 수입이 제한적이\n" +
            "었다. 게다가 극장을 운영하기에는 땅 값이 너무 올\n" +
            "랐다. 이런 변화와 컬러텔레비전의 등장으로 인해 그\n" +
            "것들의 인기는 급격히 떨어졌다. \n" +
            "20. [출제의도] 목적 추론\n" +
            "Sports Fitness Advisor 웹사이트에 탑재된 모든 정보\n" +
            "는 교육과 자료의 목적을 위한 것입니다. 그것은 운동\n" +
            "을 위한 훈련에 대해 잘 알아보고 결정을 내리는 것을 \n" +
            "도와주기 위함입니다. 그러나 본 웹사이트의 정보는 \n" +
            "의사의 진단을 대신하지는 못합니다. Sports Fitness \n" +
            "Advisor 웹사이트의 정보를 이용하는 방식에 대한 책\n" +
            "임은 오로지 귀하에게 있음을 이해해주시기 바랍니다. \n" +
            "Sports Fitness Advisor와 Sports Fitness Advisor의 \n" +
            "어떤 관계자도 본 웹사이트 및 탑재된 정보의 이용으\n" +
            "로 인해 발생할 수 있는 어떤 부상이나 문제에 대해서\n" +
            "도 결코 책임을 지지 않을 것입니다. \n" +
            "21. [출제의도] 어법\n" +
            "][정신 질환이 있는 사람들이 투표를 못하게 될 수도 \n" +
            "있다는 소식에 나는 충격을 받았다. 헌법상 우리의 \n" +
            "투표권이 우리가 이성적인 선택을 하도록 강요하는 \n" +
            "것은 아니다. 우리는 가장 적임자로 보여서 어떤 후\n" +
            "보자에게 투표를 할 수도 있고, 또는 단순히 외모가 \n" +
            "마음에 들어서 투표할 수도 있다. 게다가 정신 질환\n" +
            "자들은 고유의 어려움에 직면해 있는데, 그들이 투표\n" +
            "를 할 수 없다면 그들의 이익은 적절히 대변되지 못\n" +
            "할 것이다. 이미 사회적으로 소외된 사람들을 투표에\n" +
            "서 배제하는 것은 사회 계급제도를 만들어내어 우리\n" +
            "의 민주주의를 파괴한다.\n" +
            "22. [출제의도] 어법\n" +
            "전면 유리창 양쪽 벽은 그녀의 아버지가 직장에서 \n" +
            "얻어 온 사진들로 가득 차 있었다. 그는 우주 센터에\n" +
            "서 사진기를 가지고 일을 했다. 매번 우주선이 발사\n" +
            "된 후, 아버지는 검고 얇은 금속 액자에 끼워진 사진\n" +
            "들을 받았는데, 주황색 우주복을 차려입고 웃고 있는 \n" +
            "우주비행사 세 명의 사진과 로켓을 만들었던 큰 건\n" +
            "물, 주황색과 흰색 줄무늬 낙하산 아래 바다를 표류\n" +
            "하고 있는 은색 로켓 캡슐들의 사진이 거실 이 곳 \n" +
            "저 곳에 마치 상장처럼 걸려 있었다. 텔레비전 위에\n" +
            "는 Neil Armstrong이 달 표면에 서서 빳빳한 미국 \n" +
            "성조기에 경례하고 있는 큰 사진이 놓여있다.\n" +
            "23. [출제의도] 무관한 문장 추론\n" +
            "잡초를 제거하고 식물을 더 빨리 자라게 하기 위해 농\n" +
            "부와 정원사들은 토마토 묘목 주변에 검은색 비닐을 \n" +
            "종종 깔아둔다. 그렇지만 어떤 토마토 재배자들은 검\n" +
            "은 색 비닐 대신 살갈퀴라는 식물의 사용을 오랫동안 \n" +
            "선호해왔다. 겨울 내내 농부들은 털이 많은 살갈퀴를 \n" +
            "재배하는데 그것은 콩과 식물에 속한다. 봄철이 오면 \n" +
            "살갈퀴를 뽑아내고 베어낸 자리에 토마토를 심는다. \n" +
            "토마토와 토마토 함유 제품이 많이 들어 있는 식단은 \n" +
            "몇몇 암에 걸릴 위험성의 감소와 밀접하게 관련되어 \n" +
            "있다. 살갈퀴가 잡초를 없애 주고 영양분을 공급하여 \n" +
            "결국 토마토는 더 오래 살고 질병에 덜 걸린다.\n" +
            "24. [출제의도] 빈칸 완성\n" +
            "사물의 새로운 질서를 도입할 때 주도권을 잡기란 \n" +
            "매우 어렵다고 어떤 철학자가 말한 적이 있다. 사실, \n" +
            "뭔가 새로운 일을 하는 것은 두렵다. 그러나 태어나\n" +
            "서 제 역할을 하는 인간으로 성장하는 것은 변화와 \n" +
            "새로운 환경에 얼마나 잘 적응하는가에 달려 있다. \n" +
            "만약 넘어지는 것에 대한 두려움을 극복하지 못하면 \n" +
            "우리는 결코 일어서는 법을 배울 수 없다. 우리들 중 \n" +
            "많은 사람은 무릎에 상처가 있고 그것은 자전거 타\n" +
            "기라는 새로운 기술을 익히고자 하는 열망으로 몸에 \n" +
            "상처를 입을 때까지도 끈질기게 해냈다는 것을 입증\n" +
            "하는 것이다. 그러나 우리가 그 기술을 배웠을 때 얼\n" +
            "굴에 스치는 바람을 느끼는 일은 얼마나 흥분되는 \n" +
            "것인가. 우리는 두려움을 극복했고 그것에서 얻게 된 \n" +
            "느낌은 정말로 대단한 것이었다.\n" +
            "25. [출제의도] 빈칸 완성\n" +
            "아이들의 잘못된 행동을 예방하기 위해서는 부모가 자\n" +
            "녀에게 기대하는 바를 명확하게 하는 것이 필수적이\n" +
            "다. 대부분의 아이들은 부모를 기쁘게 해 주기를 열망\n" +
            "한다. 아이들은 그들에게 기대하는 바가 무엇인지 알\n" +
            "고 그것을 해내는 기쁨을 즐긴다. 그러나 아이들이 마\n" +
            "음을 읽을 수는 없다. 집 안에 들어오기 전에 세발자\n" +
            "전거를 치우고, 야채는 다 먹는 것이 중요하다는 것을 \n" +
            "말해 줄 필요가 있다. 한계와 경계를 분명히 하고 그\n" +
            "것을 지속적으로 독려하는 것이 아이들이 어느 범위까\n" +
            "지 나가도 되는지를 아는 데 도움을 준다.\n" +
            "26. [출제의도] 빈칸 완성\n" +
            "사람들은 때때로 병에 걸렸으면 하고 마음속으로 바\n" +
            "란다. 만약에 병에 걸리면 생활이 어떻게 달라질 것\n" +
            "][해 한다. 관심, 사랑, 타인의 염려를 필요로 한다는 \n" +
            "뻔한 설명 외에도 훨씬 더 일반적인 요인이 이러한 \n" +
            "반응의 원인이 된다. 본질적으로 병이란 더 생산적이\n" +
            "지 못한 것에 대한 핑계거리를 제공해 주고, 다른 사\n" +
            "람들이 쉽게 이해하고 받아들일 수 있는 설명이 된\n" +
            "다. 몸이 아파서 힘든 일을 할 수 없다는 것은 스스\n" +
            "로 부여한 죄책감을 덜어준다. 그들은 자신이 해야 \n" +
            "한다고 느끼는 것을 하지 않을 변명거리가 필요하며 \n" +
            "그래야 자신들의 태만함을 정당화할 수 있다.\n" +
            "27. [출제의도] 빈칸 완성\n" +
            "좋은 안내견이 되기 위해서는 합리적이지 않은 명령\n" +
            "에 불복종하는 능력이 있어야 한다. 이러한 능력은 \n" +
            "횡단보도에서 특히 중요한데, 이곳에서는 길을 안전\n" +
            "하게 건너기 위해서 안내견을 이끄는 시각 장애인과 \n" +
            "안내견이 서로 밀접하게 협력해야 한다. 횡단보도의 \n" +
            "연석에 다다르게 되면, 안내견은 길을 멈추고 시각 \n" +
            "장애인에게 횡단보도에 도착했음을 신호로 알린다. \n" +
            "안내견이 교통 신호의 색을 구별할 수 없기 때문에 \n" +
            "시각 장애인은 언제 길을 건너야 할지를 결정해야 \n" +
            "한다. 시각 장애인은 교통의 흐름에 귀를 기울여 신\n" +
            "호등이 바뀌었음을 파악한 후, “앞으로” 라는 명령을 \n" +
            "내린다. 위험한 상황이 아니라면, 안내견은 시각 장\n" +
            "애인의 말을 따른다. 그러나 만약 차가 다가오고 있\n" +
            "다면, 이 능력을 가진 안내견은 차가 사라질 때까지 \n" +
            "기다린 후에 앞으로 가라는 명령을 따른다.\n" +
            "28. [출제의도] 어휘 추론\n" +
            "차가우면서도 화창한 1월 말 오후에 시골길을 걷는 \n" +
            "것보다 더 기분 좋은 일은 없다. 여름철보다 겨울철\n" +
            "에 훨씬 더 멀리 바라볼 수 있다. 이는, 확 트인 산\n" +
            "울타리와 벌거벗은 나무들로 인해 더 많이 보이기 \n" +
            "때문이다. 이런 풍경은 잎사귀와 꽃에 가려 곧 숨어\n" +
            "버릴 것이다. 문에 기대어 넓은 벌판을 바라보며 떡\n" +
            "갈나무와 너도밤나무의 아름다운 가지를 가만히 쳐\n" +
            "다보면 기분이 좋아진다. 그리고 지는 해의 희미한 \n" +
            "황금빛이 겨울 황혼의 흐릿한 잿빛이 될 때, 오두막\n" +
            "집의 화롯불 옆에서 차와 쿠키를 기대하는 것은 얼\n" +
            "마나 멋진 일인가.\n" +
            "29. [출제의도] 어휘 추론\n" +
            "평면 스크린은 휘어진 부분이 전혀 없는 화면이다. \n" +
            "좌우 곡면으로 휜 스크린은 양 끝이 객석을 향해 약\n" +
            "간 휘어져 있다. 이 곡면은 영사기에서 나오는 빛의 \n" +
            "거리 변화에 따라 생겨나는 이미지의 왜곡을 방지하\n" +
            "는 기능을 한다. 평면 스크린에서는 영사기에서 나오\n" +
            "는 빛이 화면의 양 끝보다 화면의 가운데에 이를 때 \n" +
            "더 먼(→ 더 짧은) 거리를 가게 된다. 투영된 이미지\n" +
            "의 크기는 스크린까지의 거리에 따라 결정되므로, 평\n" +
            "면 스크린의 양 끝에서는 투영된 이미지가 조금 더 \n" +
            "크게 보인다. 영사기를 향해서 화면의 양 끝을 구부\n" +
            "림으로써 빛의 이동 거리가 같아질 수 있다. \n" +
            "30. [출제의도] 연결사 찾기\n" +
            "1900년대 초반에, 호주의 대부분 지역에서 코알라는 \n" +
            "거의 멸종 상태였다. 그러나, 남아있는 몇 안되는 코\n" +
            "알라를 이용하여 번식을 시킴으로써, 과학자들은 코\n" +
            "알라의 개체 수를 원래대로 되돌려 놓을 수 있었다. \n" +
            "오늘날, 많은 지역에서 코알라의 수가 늘어나고 있\n" +
            "다. 하지만 코알라에게는 여전히 문제가 있다. 코알\n" +
            "라 개체 수가 100년 또는 그전 즈음에 감소했기 때\n" +
            "문에, 호주 남부에 있는 모든 코알라는 최근까지 살\n" +
            "아남았던 몇 안 되는 코알라들의 후손이다. 결과적으\n" +
            "로, 현재 코알라는 많은 유전자를 공유하고 있는 것\n" +
            "이다. 호주 본토에서 떨어진 일부 섬에서는, 근친 번\n" +
            "식으로 인해 코알라의 신체적 결함과 질병이 생겨났\n" +
            "다. 똑같은 현상이 호주 본토에 있는 코알라에게도 \n" +
            "일어날 수 있다는 점을 과학자들은 우려하고 있다.\n" +
            "][눈치 채지 않도록 조심하면서 그는 그녀의 얼굴 모습\n" +
            "을 살펴보았다. 그녀의 외모는 매혹적이었다. 그는 그\n" +
            "녀에게서 눈을 떼고 싶었지만 그럴 수 없었기 때문에 \n" +
            "불안해지기 시작했다. 그가 그녀를 바라보는 게 싫어\n" +
            "서가 아니라 자신을 보고 있다는 것을 그녀가 알아차\n" +
            "리면 그녀가 불쾌해할지도 모른다는 생각 때문이었다. \n" +
            "그녀에게서 눈을 뗄 핑계를 찾기 위해 그는 다리를 \n" +
            "쭉 펴보기로 했다. 그래서 그는 자리에서 일어났다. \n" +
            "일어나면서 그는 그녀의 얼굴을 다시 흘끗 보았다. \n" +
            "그의 무릎은 후들거리기 시작했고 그것은 그로 하여\n" +
            "금 스스로를 바보로 만들기 전에 즉시 그녀로부터 멀\n" +
            "리 달아나야만 한다는 마음을 훨씬 더 굳게 만들었다.\n" +
            "32. [출제의도] 주장 추론\n" +
            "사람들은 사람과의 관계에서 상황이 순조롭게 진행\n" +
            "되지 않을 때 우선 상대에게 눈길을 돌리는 경향이 \n" +
            "있다. 그들은 상대가 어떻게 하여 그들의 말에 귀 기\n" +
            "울이지 않는지 뿐 아니라 상대가 하고 있는 혹은 하\n" +
            "고 있지 않는 것을 자동적으로 지적하기 시작한다. \n" +
            "이것은 당신의 특수한 상황에 따라 사실일 수도 있\n" +
            "지만 상대의 어떤 것을 지적하기 전에 우선 당신이 \n" +
            "책임지고 자신의 행동과 말을 재검토해 보는 것이 \n" +
            "중요하다. 명심해라. 다른 사람의 잘못을 파악하는 \n" +
            "것은 매우 쉽지만 당신을 바라보는 문제라면 비판의 \n" +
            "목소리를 받아들이는 것은 훨씬 더 힘든 일이다. 왜\n" +
            "냐하면 아무도 잘못하기를 원치 않기 때문이다. 이때\n" +
            "가 당신이 가장 진지해져야 할 상황이다.\n" +
            "33. [출제의도] 주제 추론\n" +
            "어떤 연구원들이 성인 123명의 신체 건강 상태를 측\n" +
            "정했다. 그러고 나서 참가자들은 컴퓨터 화면에 나타\n" +
            "나는 화살표를 보고 하나의 특정 화살표가 어느 방\n" +
            "향을 가리키고 있는지를 보여주기 위해 컴퓨터 자판\n" +
            "을 사용해야 했다. 신체적으로 건강한 성인들은 화살\n" +
            "표 실험에서 더 빨랐고 그들의 대답은 덜 건강한 동\n" +
            "년배들보다 더 정확했다는 사실을 연구원들은 알아\n" +
            "냈다. 더 건강한 참가자들은 또한 집중력과 관련된 \n" +
            "뇌 부위에 혈액 공급이 더 많았다. 또 다른 연구에서\n" +
            "는 6개월의 유산소 훈련을 마친 사람들은 같은 시간\n" +
            "의 운동을 하지 않은 사람들보다 집중력 실험에서 \n" +
            "더 빠르고 더 정확했다. \n" +
            "34. [출제의도] 주제 추론\n" +
            "한 환자가 사망했다고 판정되었을 때, 그것은 장기 \n" +
            "이식 수술과 관련된 모든 사람들에게 혼란스러운 순\n" +
            "간이다. 의사들이 충격을 받은 친족들에게 이식 허락\n" +
            "을 구하는 것은 쉽지 않다. 친족들에게도 이것은 어\n" +
            "려운 결정이다. 그들은 사랑했던 사람의 신체 일부분\n" +
            "을 양도할 수 없다고 느낄 수 있다. 해결책이 하나 \n" +
            "있는데 그것은 장기 기증 동의 카드 시스템이다. 건\n" +
            "강하게 생존 중인 사람들이 그들에게 어떤 일이 생\n" +
            "긴다면 그들의 장기를 다른 사람을 위해 이용하도록 \n" +
            "할 것인지 결정할 수 있다. 이것이 그들의 선택임을 \n" +
            "보여주기 위해 그들은 장기 기증 동의 카드를 소지\n" +
            "하고 있다. 그들이 사고를 당하거나 중병에 걸려 죽\n" +
            "게 된다면 의사들은 그 카드를 확인하고 즉시 장기\n" +
            "들이 이식에 사용될 수 있음을 알게 된다.\n" +
            "35. [출제의도] 도표 내용 이해\n" +
            "위의 도표는 2005년 5세에서 17세에 이르는 영국 청\n" +
            "소년들이 성별에 따라 선호하는 픽션 형태를 보여주고 \n" +
            "있다. 모험 이야기가 남녀 모두 가장 좋아하는 장르였\n" +
            "다. 희극과 공포 이야기는 남녀 모두 각각 2위와 3위를 \n" +
            "차지했다. 그러나 여자에 비해 보다 높은 비율의 남자 \n" +
            "아이들이 탐정 소설을 선호했다. 여자들 사이에서 탐정 \n" +
            "소설이 가장 인기가 없었으며, 반면에 시문학이 남자들\n" +
            "에게 가장 인기가 없었다. 연애소설을 좋아하는 여자의 \n" +
            "비율은 남자의 비율에 비해 약 4배 정도 높았다.\n" +
            "][\n" +
            "][모든 사람은 아름답고 건강하며 젊어 보이는 피부를 \n" +
            "갖기를 원한다. 누군들 그렇지 않겠는가? 여기 희소\n" +
            "식이 있다. 수분은 온몸을 돌아다니다가 나갈 때는 \n" +
            "피부의 표면을 통해 몸 밖으로 나간다. 이러한 과정\n" +
            "이 당신의 피부를 통통하고 탄력 있게 해준다. 그렇\n" +
            "지만 몸에 수분이 부족하면 결국 피부 표면에 문제\n" +
            "가 생긴다. 피부는 건조하고 거칠어지며 약해진다. \n" +
            "따라서 항상 몸에 충분한 수분을 유지하는 것이 중\n" +
            "요하다. 하루에 적어도 여덟 잔의 물을 마시고 수분\n" +
            "이 풍부한 과일과 야채를 많이 섭취하는 것이 몸에 \n" +
            "충분한 수분을 제공하는 데 도움이 되며 그것은 보\n" +
            "기 좋은 피부를 유지하게 해준다.\n" +
            "37. [출제의도] 요지 추론\n" +
            "당신은 주변의 사람들을 돌아보며 그들이 가진 강한 \n" +
            "의지에 감탄스러워한다. “도대체 어떻게 그들은 조깅을 \n" +
            "하기 위해 아침마다 잠자리에서 일어나는 것일까?”라\n" +
            "는 것이 당신이 늘 곰곰이 생각하는 의문이다. 마치 \n" +
            "타고난 천성인 양 자기 절제가 부족한 것이 아닐까 의\n" +
            "아해 한다. 그러나 자기 절제는 가지고 태어나는 것이 \n" +
            "아니다. 그것은 길러지는 능력이다. 아침에 자명종이 \n" +
            "울릴 때 머리 위로 이불을 끌어당길 때 당신은 자신의 \n" +
            "마음을 태만해지도록 훈련한다. 숙제를 끝내야 하는데\n" +
            "도 휴식을 위해 TV를 좀 본다면 당신의 마음을 해이\n" +
            "해지도록 훈련하는 것이다. 자기 절제란 정신 수련의 \n" +
            "문제이다.\n" +
            "38. [출제의도] 세부 내용 이해\n" +
            "Tempera는 분말 안료와 달걀노른자를 혼합해서 만\n" +
            "든 그림물감의 일종이다. Tempera 색소는 보통 광물\n" +
            "질, 나무, 식물, 혹은 진흙과 같은 천연 재료로 만들\n" +
            "어졌다. Tempera가 빨리 마르기 때문에 화가들은 작\n" +
            "은 붓놀림으로 그것을 재빨리 칠해야 했다. 또한 그\n" +
            "것의 빠른 건조는 나중에 그림을 바꾸거나 수정하는 \n" +
            "것을 어렵게 만들었다. Tempera는 알려진 것 중 가\n" +
            "장 오래된 물감이다. 그것은 고대 이집트, 바빌로니\n" +
            "아, 그리스의 벽화에 사용되었다. Tempera는 15세기 \n" +
            "유화 물감이 발달되기 전까지 인기가 많았으며, 특히 \n" +
            "초기 르네상스 시절 이탈리아에서 많이 사용되었다. \n" +
            "오늘날 병에 들어있는 Tempera 물감은 밝고 물에 \n" +
            "잘 지워지기 때문에 초등학생들에게 인기가 있다.\n" +
            "39. [출제의도] 세부 내용 이해\n" +
            "Wales 태생의 Colin Fletcher는 제2차 세계대전 때 \n" +
            "영국 왕실 해병대 군인으로서 처음으로 행군을 하였\n" +
            "다. 아프리카와 캐나다에서 머문 뒤 1956년 미국으\n" +
            "로 이주하였다. 1958년 대부분의 미국인에게 도보 \n" +
            "여행이 아직 생소할 때 Fletcher는 California를 종단\n" +
            "하기로 마음먹었다. 길고 외로운 도보 여행을 하게 \n" +
            "된 동기는 결혼 여부를 결정하기 위한 것이었다. 6개\n" +
            "월 동안 1,000마일을 도보 여행한 후 Fletcher는 여\n" +
            "자 친구와 결혼하고 그의 처녀작인 “The Thousand \n" +
            "Mile Summer”를 저술하였다. 그는 1963년 Grand \n" +
            "Canyon을 도보 여행한 최초의 사람 중 한 명이 되었\n" +
            "다. 그는 “The Man Who Walked Through Time”이\n" +
            "라는 책에서 두 달간의 도보 여행에 관하여 썼다. 그\n" +
            "는 도보 여행에 관한 서정적이고 실용적인 글을 써\n" +
            "서 ‘현대 도보 여행의 아버지’라 불린다.\n" +
            "40. [출제의도] 문장 위치 추론\n" +
            "당신이 오른손잡이나 왼손잡이인 것처럼, 당신은 오\n" +
            "른눈잡이 혹은 왼눈잡이이다. 그 말은 어느 한쪽 눈\n" +
            "이 다른 쪽보다 더 강하거나 우월하다는 뜻이다. 그\n" +
            "것을 알아낼 수 있는 한 가지 실험이 있다. 팔을 앞\n" +
            "으로 뻗은 상태에서 눈높이에 맞게 연필 한 자루를 \n" +
            "수직으로 들고 있어라. 양쪽 눈을 뜬 상태에서, 선반, \n" +
            "사진, 책이나 또는 벽에 걸린 다른 사물과 그 연필을 \n" +
            "나란히 배열해 두어라. 먼저, 한쪽 눈을 감고 그 다음 \n" +
            "다른 쪽 눈을 감아라. 한쪽 눈을 뜬 상태에서 연필이 \n" +
            "][같은 곳에 머물러 있는가? 아니면 다른 쪽 눈 쪽으로 \n" +
            "이동하는 것처럼 보이는가? 어느 쪽 눈이든 그 눈을 \n" +
            "뜨고 있을 때 연필이 벽에 걸린 사물과 나란히 보인\n" +
            "다면, 그 눈이 바로 당신의 더 강하고 우월한 눈이다.\n" +
            "41. [출제의도] 제목 추론\n" +
            "어느 날 당신은 신용카드 회사로부터 전화를 받는다. \n" +
            "그들이 신용카드 번호와 사용자 이름, 비밀번호, 그\n" +
            "리고 다른 재정적 소득에 대해 요구한다. 당신은 모\n" +
            "든 질문에 대답을 하고, 그러면 ‘비싱’ 즉, 보이스 피\n" +
            "싱(음성사기)의 피해자가 된다. 전화사기의 주된 형\n" +
            "태 중 하나는 범죄자들이 경찰이나 금융 회사의 관\n" +
            "계자를 사칭하여 사람들로부터 개인정보를 얻어내고 \n" +
            "그 정보를 이용하여 돈을 인출하는 것이다. 어떤 전\n" +
            "화 사기범은 심지어 아이의 부모에게 돈을 얻어내려\n" +
            "고 납치범 행세를 하기도 하였다. 당신은 비싱의 피\n" +
            "해자가 되지 않도록 조심해야 한다.\n" +
            "42. [출제의도] 제목 추론\n" +
            "환경 운동가들은 “개발”이라는 용어는 단지 도로 건\n" +
            "설과 같은 사업이 아주 손상되기 쉬운 Everest의 생\n" +
            "태계를 어떤 식으로 파괴할 수 있는가를 은폐하는 \n" +
            "완곡어법에 불과하다고 말한다. 한 빙하학자는 “도로\n" +
            "란 생태계에 대한 직접적인 공격과 같다”라고 말했\n" +
            "다. 2007년 7월 중국 정부는 Everest 베이스캠프에 \n" +
            "이르는 67마일의 도로를 건설하기 시작했으며 반면\n" +
            "에 호텔 건설 계획은 보류 중이다. 중국 관료들은 그 \n" +
            "도로가 2008년 올림픽 성화 봉송을 위해 중요한데 \n" +
            "그 봉송은 Everest 정상에 있는 한 지점을 포함할 \n" +
            "예정이라고 말하고 있다. 그러나 그것은 광범위한 채\n" +
            "굴을 포함해서 Everest 지역의 전체 개발을 위한 첫 \n" +
            "걸음에 불과하다고 많은 사람들은 우려하고 있다. \n" +
            "43. [출제의도] 분위기 추론\n" +
            "건초는 말이 끄는 풀 베는 기계로 말끔하게 깎여 놓\n" +
            "여 있다. 갓 베어낸 건초 냄새가 바람에 실려 이리저\n" +
            "리 날리고 있다. 미풍이 아직은 서있는 나머지 건초\n" +
            "를 사랑스럽게 어루만지고 있다. 흔들리는 건초는 마\n" +
            "치 바다처럼 보이고, 꽃을 피우고 있는 큰조아재비 \n" +
            "꽃 머리가 초록 빛 바다 같은 들에 흰 파도를 만들\n" +
            "어 내고 있다. 이렇게 또 새로운 건초 만드는 계절이 \n" +
            "시작된다. 아침이 안개를 헤치며 밝아온다. 태양은 \n" +
            "빠르게 달아올라 안개를 사라지게 한다. 내가 갈퀴에 \n" +
            "말들을 붙잡아 매고 있는 동안에, 말들은 조용히 서 \n" +
            "있다. 말들은 이 계절 이렇게 이른 시간에는 윙윙거\n" +
            "리고 물기도 하는 파리가 없다는 사실에 감사하고 \n" +
            "있는 듯 보인다. 말들은 새를 바라보며 육중한 몸 위\n" +
            "로 쏟아지는 따뜻한 햇볕을 맘껏 즐기고 있다.\n" +
            "44. [출제의도] 글의 순서 파악\n" +
            "천문학 용어로 ‘블루문’은 색상과는 실제로 아무 관련\n" +
            "이 없다. (C) 대신, 그것은 해당 양력 달에 뜬 두 번\n" +
            "째 보름달을 의미하는 데 사용되는 용어이다. ‘블루문’\n" +
            "이 실제로 파랗게 보이는 것은 아니지만, 그 달이 정\n" +
            "말 파랗게 보일 때도 있었다. (A) 1883년에 Krakatoa \n" +
            "화산의 폭발은 우리에게 그런 ‘블루문’을 보여주었다. \n" +
            "그것은 미세한 먼지 입자들을 지구 대기 상층부로 분\n" +
            "출하였다. (B) 대기 중의 이런 먼지 입자들이 빛을 산\n" +
            "란시켰다. 이 먼지가 달에서 나오는 빛에 미친 영향이 \n" +
            "달을 푸르스름하게 보이도록 만들었다. \n" +
            "45. [출제의도] 문단 요약\n" +
            "당신은 당신 자신이며, 다른 사람들과는 다른 별개의 \n" +
            "사람이라는 것을 당연히 여긴다. 당신은 개인으로서 \n" +
            "자아에 대한 강한 인식을 갖고 있다. 당신은 아마도 \n" +
            "이것이 모든 사람들이 스스로를 생각하는 방식이라\n" +
            "고 여길 것이다. 그러나 대부분의 사회에서는 가족이 \n" +
            "우선이고 개인은 그 집단 내에 묻혀 있다. 이는 다른 \n" +
            "사람들을 생각하지 않고 당신 자신을 생각하는 것은 \n" +
            "정말 불가능하다는 것을 의미한다. ‘내가’ 또는 ‘나를’\n" +
            "][이라는 말은 별 의미가 없다. 일부 언어들에서는 그\n" +
            "러한 말이 매우 제한된 범위에서 사용될 때 그 의미\n" +
            "가 드러난다. 많은 사회에서 당신은 다른 사람들과의 \n" +
            "관계에서만 의미를 갖게 된다. 당신의 정체성은 부모\n" +
            "의 딸, 아이의 엄마, 남편의 아내, 그리고 조상의 자\n" +
            "손이라는 것에서 비롯된다.\n" +
            "→ 사람들은 사회 내에서의 관계를 통해 자신의 정\n" +
            "체성을 발견할 수 있다. \n" +
            "46~48. [출제의도] 장문 독해(글의 순서 파악, 빈칸 \n" +
            "완성, 세부 내용 이해)\n" +
            "(B) 수업 첫 날 교수가 우리에게 서로 친해질 기회를 \n" +
            "갖도록 격려해 주었다. 나는 일어나서 주름이 많고 \n" +
            "자그마한 할머니가 나에게 환한 미소를 짓는 것을 발\n" +
            "견했다. 그녀는 “안녕, 미남! 내 이름은 Rose야. 여든 \n" +
            "일곱 살이야. 한번 안아 봐도 될까?”라고 말했다. 나\n" +
            "는 웃으며 열정적으로 대답했다. “물론이죠.” 나는 “그\n" +
            "런 어리고 순진한 연세에 왜 대학에 오게 되셨어요?”\n" +
            "라고 물었다. 그녀는 농담처럼 “부자를 만나서 결혼하\n" +
            "려고 왔지. 사실은 늘 대학을 다니는 것을 꿈꾸었는\n" +
            "데 지금 다니고 있는 거야.”라고 대답했다.\n" +
            "(C) 수업이 끝난 후 우리는 학생 회관으로 걸어가 \n" +
            "초콜릿 밀크셰이크를 함께 마셨다. 우리는 즉시 친구\n" +
            "가 되었다. 다음 석 달간 매일 수업이 끝나면 함께 \n" +
            "끊임없이 이야기를 나누었다. 나는 이 “타임머신”이 \n" +
            "자신의 지혜와 경험에 대해 말하는 것을 듣는 것이 \n" +
            "좋았다. Rose는 캠퍼스 명물이 되었고, 어딜 가든 쉽\n" +
            "게 친구를 사귀었다. \n" +
            "(A) 학기 말에 우리는 연설을 해달라고 Rose를 파\n" +
            "티에 초대하였다. “젊고, 행복하고, 성공을 이루는 것\n" +
            "에는 한 가지 비결이 있어요. 꿈을 가지세요. 꿈을 \n" +
            "잃으면 죽어요.”라고 그녀가 말했다. “누구나 나이 먹\n" +
            "을 수 있어요. 그건 어떤 재능이나 능력이 필요하지 \n" +
            "않죠.”라고 덧붙였다. “중요한 것은 변화 속에서 기회\n" +
            "를 늘 찾아냄으로써 성장하는 것입니다. 우리 노인들\n" +
            "은 한 일에 대해서는 대개 후회를 하지 않고, 오히려 \n" +
            "하지 못한 일에 대해 후회를 하죠.”\n" +
            "(D) 그녀는 “The Rose”라는 노래를 씩씩하게 부름\n" +
            "으로써 연설을 마쳤다. 그녀는 우리에게 그 노래의 \n" +
            "가사를 생각하고 일상생활에서 그렇게 살도록 독려\n" +
            "했다. 연말에 Rose는 학위를 받았다. 졸업하고 일주\n" +
            "일 후 Rose는 자다가 평온하게 세상을 떠났다. \n" +
            "2,000명 이상의 대학생들이 그 훌륭한 여성을 기리\n" +
            "며 장례식에 참석하였다. 그녀는 우리가 될 수 있는 \n" +
            "것이면 무엇이든 되기에 결코 늦지 않았음을 몸소 \n" +
            "가르쳐 주었다.\n" +
            "49~50. [출제의도] 복합문 이해(쟁점 추론, 빈칸 완성)\n" +
            "Person A: 노인 운전자들의 자동차 사고 사망률이 증\n" +
            "가하고 있으며, 노인 운전자들은 십대 남자를 제외한 \n" +
            "어떤 연령층보다 매 1마일 운전 거리에서 더 많은 수\n" +
            "의 치명적 사고를 당하고 있다. 노인 운전자들의 안전 \n" +
            "문제는, 약해지는 시력과 청력, 늦어지는 반사 능력과 \n" +
            "짧아지는 집중 시간 등과 같은 노화의 정상적인 과정\n" +
            "에 그 원인이 있다. 그러므로 미국의 많은 주들이 일\n" +
            "정 연령 이상의 운전자들에게 직접 운전면허를 갱신하\n" +
            "][\n" +
            "][따라서는 형편없는 운전자가 될 수 있다. 모든 위험\n" +
            "한 운전자들을 도로에서 몰아내기 위한 운전자 검사\n" +
            "나 다른 시도들은 장려되어야 한다. 그러나 특정 연\n" +
            "령층을 겨냥하는 시도들은 연령 차별의 좋은 예가 \n" +
            "될 수 있다.\n" +
            "]\n" +
            "= = = = = = = = = = = = = = = = = = = =\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_1.png [1/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_2.png [2/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_3.png [3/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_4.png [4/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_5.png [5/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_6.png [6/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_7.png [7/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_8.png [8/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_9.png [9/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_10.png [10/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_11.png [11/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_12.png [12/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_13.png [13/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_14.png [14/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_15.png [15/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_16.png [16/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_17.png [17/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_18.png [18/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_19.png [19/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_20.png [20/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_21.png [21/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_22.png [22/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_23.png [23/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_24.png [24/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_25.png [25/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_26.png [26/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_27.png [27/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_28.png [28/29]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 6월 모의평가(평가원) 외국어/answer/image_29.png [29/29]\n" +
            "Name : 2007년 고3 6월 모의평가(평가원) 외국어\n" +
            "= = = = = = = = = = = = = = = = = = = =\n" +
            "[\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][대학 생활은 바쁘다. 일정을 계획할 때 고려해야 할 것들이 너무나 많다. 활동, 친구, 그리\n" +
            "고 오락 등은 코앞에 닥친 실질적인 일을 수행하는데 약간의 어려움을 야기할 수 있다. 발\n" +
            "표, 보고서 마감시간, 혹은 시험에 의해 압도당하는 느낌이 들 때, \n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "][\n" +
            "]\n" +
            "= = = = = = = = = = = = = = = = = = = =\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 7월 학력평가(인천) 외국어/answer/image_1.png [1/5]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 7월 학력평가(인천) 외국어/answer/image_2.png [2/5]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 7월 학력평가(인천) 외국어/answer/image_3.png [3/5]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 7월 학력평가(인천) 외국어/answer/image_4.png [4/5]\n" +
            "Export image file from PDF : /Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS/2007년 고3 7월 학력평가(인천) 외국어/answer/image_5.png [5/5]\n" +
            "\n" +
            "Process finished with exit code 130\n" +
            "\n";
}
