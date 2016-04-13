package com.joowon.returnA.classifier;

import com.joowon.returnA.classifier.cli.ClassifierCliParser;
import com.joowon.returnA.classifier.db.MongoDbManager;
import com.joowon.returnA.classifier.parser.ProblemParser;
import com.joowon.returnA.classifier.parser.SolveParser;
import com.joowon.returnA.classifier.transfer.TxtToMongoTransfer;
import com.joowon.returnA.classifier.txt.TxtWriter;
import org.apache.pdfbox.debugger.stringpane.StringPane;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Copyright (c) 4/10/16 Joowon Ryoo
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
public class SolveClassifier extends Classifier {
    public static void main(String[] args) throws IOException {
        ClassifierCliParser cliParser = new ClassifierCliParser(args);
        cliParser.parse();

        File sourceDirectory = new File(cliParser.getTarget());
        File destinationParentDirectory = new File(cliParser.getDestination());
        if (!destinationParentDirectory.exists())
            destinationParentDirectory.mkdirs();
        File[] sourcePdfList = sourceDirectory.listFiles();

        // Parse solve datas
        assert sourcePdfList != null;
        for (File pdfFile : sourcePdfList) {
            try {
                System.out.println(pdfFile);
                SolveClassifier problemClassifier = new SolveClassifier(PDDocument.load(pdfFile), destinationParentDirectory.getAbsolutePath());
                File destinationDirectory = new File(destinationParentDirectory.getAbsolutePath() + "/" + problemClassifier.getTestName());
                destinationDirectory.mkdirs();

                String text = SolveParser.removeExceptionalText(problemClassifier.getBodyText());
                text = SolveParser.removeExceptionalText(text);
                System.out.println(text);

                System.out.println("Name : " + SolveParser.parseTestName(text));
                SolveParser.parseAnswers(text).forEach(System.out::println);
                System.out.println("= = = = = = = = = = = = = = = = = = = =");

                new TxtWriter(destinationDirectory.getAbsolutePath() + "/" + SolveParser.parseTestName(text) + ".txt").write(String.valueOf(SolveParser.parseAnswers(text)));
                // Parse problem
//                for (int i = 1; i <= 50; ++i) {
//                    String problemText = ProblemParser.parseProblem(text, i);
//                    String fileName = destinationDirectory.getAbsolutePath() + "/" + ProblemParser.parseProblemName(problemText) + ".txt";
//                    System.out.println(fileName);
//                    new TxtWriter(fileName).write(problemText);
//                }
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


    public SolveClassifier(PDDocument document, String path) {
        super(document, path);
    }
}