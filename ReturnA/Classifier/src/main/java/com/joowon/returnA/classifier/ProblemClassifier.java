package com.joowon.returnA.classifier;

import com.joowon.returnA.classifier.cli.ClassifierCliParser;
import com.joowon.returnA.classifier.cv.PdfPageDivider;
import com.joowon.returnA.classifier.db.MongoDbManager;
import com.joowon.returnA.classifier.export.PdfImageExport;
import com.joowon.returnA.classifier.extractor.PdfTextExtractor;
import com.joowon.returnA.classifier.parser.HeadlineParser;
import com.joowon.returnA.classifier.parser.ProblemParser;
import com.joowon.returnA.classifier.transfer.TxtToMongoTransfer;
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
public class ProblemClassifier extends Classifier {
    public static void main(String[] args) throws IOException {
        ClassifierCliParser cliParser = new ClassifierCliParser(args);
        cliParser.parse();

        File sourceDirectory = new File(cliParser.getTarget());
        File destinationParentDirectory = new File(cliParser.getDestination());
        if (!destinationParentDirectory.exists())
            destinationParentDirectory.mkdirs();
        File[] sourcePdfList = sourceDirectory.listFiles();

        // Parse problem datas
        assert sourcePdfList != null;
        for (File pdfFile : sourcePdfList) {
            try {
                System.out.println(pdfFile);
                ProblemClassifier problemClassifier = new ProblemClassifier(PDDocument.load(pdfFile), destinationParentDirectory.getAbsolutePath());
                File destinationDirectory = new File(destinationParentDirectory.getAbsolutePath() + "/" + problemClassifier.getTestName());
                destinationDirectory.mkdirs();

                // Parse problem group
                String text = ProblemParser.removeExceptionalText(problemClassifier.getProblemText());
                new TxtWriter(destinationDirectory.getAbsolutePath() + "/" + "test.txt").write(text);
                for (int i = 1; i <= 50; ++i) {
                    String group = ProblemParser.parseProblemGroup(text, i);
                    if (group.length() != 0) {
                        String fileName = destinationDirectory.getAbsolutePath() + "/" + ProblemParser.parseProblemGroupName(group) + ".txt";
                        System.out.println(fileName);
                        new TxtWriter(fileName).write(group);
                        text = text.replace(group, "");
                    }
                }

                // Remove trash text
                text = ProblemParser.removeExceptionalText(text);

                // Parse problem
                for (int i = 1; i <= 50; ++i) {
                    String problemText = ProblemParser.parseProblem(text, i);
                    String fileName = destinationDirectory.getAbsolutePath() + "/" + ProblemParser.parseProblemName(problemText) + ".txt";
                    System.out.println(fileName);
                    new TxtWriter(fileName).write(problemText);
                }
                problemClassifier.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Put data into MongoDB
        new TxtToMongoTransfer(destinationParentDirectory.getAbsolutePath(),
                "localhost:" + MongoDbManager.DEFAULT_PORT)
                .transfer();
    }


    public ProblemClassifier(PDDocument document, String path) {
        super(document, path);
    }

    public String getProblemText() throws IOException {
        int numberOfPages = document.getNumberOfPages();

        String text = "";
        for (int i = 1; i <= numberOfPages; ++i) {
            double[] bodyPosition = new PdfPageDivider(destinationParentPath + "/image_" + i + ".png")
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
}