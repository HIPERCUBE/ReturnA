package com.joowon.returnA.classifier;

import com.joowon.returnA.classifier.cli.ClassifierCliParser;
import com.joowon.returnA.classifier.db.MongoDbManager;
import com.joowon.returnA.classifier.parser.SolveParser;
import com.joowon.returnA.classifier.parser.model.Solve;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

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
        File[] sourcePdfList = sourceDirectory.listFiles();

        // Parse solve datas
        assert sourcePdfList != null;
        MongoDbManager mongoDbManager = MongoDbManager.getInstance(MongoDbManager.DEFAULT_PORT);
        for (File pdfFile : sourcePdfList) {
            try {
                File destinationDirectory = new File(pdfFile.getAbsolutePath() + "/answer");
                destinationDirectory.mkdirs();

                SolveClassifier problemClassifier = new SolveClassifier(
                        PDDocument.load(new File(pdfFile.getAbsolutePath() + "/answer.pdf")),
                        destinationDirectory.getAbsolutePath());

                // Remove trash text
                String text = SolveParser.removeExceptionalText(problemClassifier.getBodyText());
                String testName = destinationDirectory.getParentFile().getName();

                for (Solve solve : SolveParser.parseAnswers(text)) {
                    mongoDbManager.updateAnswer(testName, solve.problemNumber, solve.answer);
                }
                problemClassifier.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public SolveClassifier(PDDocument document, String path) {
        super(document, path);
    }
}