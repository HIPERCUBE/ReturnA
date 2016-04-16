package com.joowon.returnA.classifier;

import com.joowon.returnA.classifier.cli.ClassifierCliParser;
import com.joowon.returnA.classifier.db.MongoDbManager;
import com.joowon.returnA.classifier.parser.ProblemParser;
import com.joowon.returnA.classifier.transfer.TxtToMongoTransfer;
import com.joowon.returnA.classifier.txt.TxtWriter;
import org.apache.pdfbox.pdmodel.PDDocument;

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
        File[] testList = sourceDirectory.listFiles();

        // Parse problem datas
        assert testList != null;
        for (File pdfFile : testList) {
            try {
                File destinationDirectory = new File(pdfFile.getAbsolutePath() + "/problem");
                destinationDirectory.mkdirs();

                ProblemClassifier problemClassifier = new ProblemClassifier(
                        PDDocument.load(new File(pdfFile.getAbsolutePath() + "/problem.pdf")),
                        destinationDirectory.getAbsolutePath());

                // Parse problem group
                String text = ProblemParser.removeExceptionalText(problemClassifier.getBodyText());
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
        new TxtToMongoTransfer(sourceDirectory.getAbsolutePath(),
                "localhost:" + MongoDbManager.DEFAULT_PORT)
                .transfer();
    }


    public ProblemClassifier(PDDocument document, String path) {
        super(document, path);
    }
}