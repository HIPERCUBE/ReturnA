package com.joowon.returnA.classifier;

import com.joowon.returnA.classifier.cli.ClassifierCliParser;
import com.joowon.returnA.classifier.cv.PdfPageDivider;
import com.joowon.returnA.classifier.export.PdfImageExport;
import com.joowon.returnA.classifier.extractor.PdfTextExtractor;
import com.joowon.returnA.classifier.parser.HeadlineParser;
import com.joowon.returnA.classifier.parser.ProblemParser;
import com.joowon.returnA.classifier.txt.TxtWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;

/**
 * Copyright (c) 3/29/16 Joowon Ryoo
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
public class Classifier {
    public static void main(String[] args) throws IOException {
        ClassifierCliParser cliParser = new ClassifierCliParser(args);
        cliParser.parse();

        File sourceDirectory = new File(cliParser.getTarget());
        File destinationParentDirectory = new File(cliParser.getDestination());
        File[] sourcePdfList = sourceDirectory.listFiles();

        assert sourcePdfList != null;
        for (File pdfFile : sourcePdfList) {
            try {
                Classifier classifier = new Classifier(PDDocument.load(pdfFile), destinationParentDirectory.getAbsolutePath());
                File destinationDirectory = new File(destinationParentDirectory.getAbsolutePath() + "/" + classifier.getTestName());
                destinationDirectory.mkdirs();

                String text = ProblemParser.removeExceptionalText(classifier.getProblemText());
                new TxtWriter(destinationDirectory.getAbsolutePath() + "/" + "test.txt").write(text);
                for (int i = 1; i <= 45; ++i) {
                    String group = ProblemParser.parseProblemGroup(text, i);
                    if (group.length() != 0) {
                        String fileName = destinationDirectory.getAbsolutePath() + "/" + ProblemParser.parseProblemGroupName(group) + ".txt";
                        System.out.println(fileName);
                        new TxtWriter(fileName).write(group);
                        text = text.replace(group, "");
                    }
                }
                text = ProblemParser.removeExceptionalText(text);
                for (int i = 1; i <= 45; ++i) {
                    String problemText = ProblemParser.parseProblem(text, i);
//                System.out.println(problemText);
                    String fileName = destinationDirectory.getAbsolutePath() + "/" + ProblemParser.parseProblemName(problemText) + ".txt";
                    System.out.println(fileName);
                    new TxtWriter(fileName).write(problemText);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private PDDocument document;

    public Classifier(PDDocument document, String path) {
        // Export images from PDF
        this.document = document;
        System.out.println(path);
        PdfImageExport.export(document, path, "image");
    }

    public String getProblemText() throws IOException {
        int numberOfPages = document.getNumberOfPages();

        String text = "";
        for (int i = 1; i <= numberOfPages; ++i) {
            double[] bodyPosition = new PdfPageDivider(System.getProperty("user.dir") + "/image_" + i + ".png")
                    .divide()
                    .findBody();
            PDPage page = document.getPage(i - 1);

            int width = (int) page.getMediaBox().getWidth();
            int height = (int) page.getMediaBox().getHeight();
            int startY = (int) (height * bodyPosition[0]);
            int endY = (int) (height * bodyPosition[1]);

            text += new PdfTextExtractor(page)
                    .addRegion(0, startY, width / 2, endY - startY)
                    .extract();
            text += new PdfTextExtractor(page)
                    .addRegion(width / 2, startY, width / 2, endY - startY)
                    .extract();
        }
        return text;
    }

    public String getTestName() throws IOException {
        double[] bodyPosition = new PdfPageDivider(System.getProperty("user.dir") + "/image_" + 1 + ".png")
                .divide()
                .findHeadLine();
        PDPage page = document.getPage(0);
        int width = (int) page.getMediaBox().getWidth();
        int height = (int) page.getMediaBox().getHeight();
        int endY = (int) (height * bodyPosition[1]);

        String text = new PdfTextExtractor(page)
                .addRegion(0, 0, width, endY)
                .extract().toString();
        String testName = HeadlineParser.parseTestName(text);
        String testType = HeadlineParser.parseTestType(text);
        if (testType.length() != 0)
            testName += " " + testType;
        return testName;
    }
}
