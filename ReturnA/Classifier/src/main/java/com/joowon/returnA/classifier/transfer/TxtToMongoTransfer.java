package com.joowon.returnA.classifier.transfer;

import com.joowon.returnA.classifier.db.MongoDbManager;
import com.joowon.returnA.classifier.db.dto.Problem;
import com.joowon.returnA.classifier.parser.ProblemParser;
import com.joowon.returnA.classifier.txt.TxtReader;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * Copyright (c) 4/6/16 Joowon Ryoo
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
public class TxtToMongoTransfer implements Transfer {
    public static void main(String[] args) {
        new TxtToMongoTransfer("/Users/Joowon/Documents/Github/ReturnA/data/tests/problem/parsed", "localhost:" + MongoDbManager.DEFAULT_PORT).transfer();
    }

    private File parentDirectory;
    private MongoDbManager dbManager;

    public TxtToMongoTransfer(String parentDirectoryPath, String mongodbHost) {
        parentDirectory = new File(parentDirectoryPath);
        dbManager = MongoDbManager.getInstance(mongodbHost);
    }

    @Override
    public void transfer() {
        try {
            for (File testDirectory : getTestDirectories()) {
                for (File file : getProblemFiles(testDirectory)) {
                    String problemText = new TxtReader(file).read();
                    Problem problem = new Problem(testDirectory.getName());
                    problem.setTestNumber(Integer.parseInt(file.getName().replace(".txt", "")));
                    problem.setProblemText(problemText);
                    problem.setQuestion(ProblemParser.parseQuestion(problemText));
                    problem.setOptions(new String[]{
                            ProblemParser.parseOption1(problemText),
                            ProblemParser.parseOption2(problemText),
                            ProblemParser.parseOption3(problemText),
                            ProblemParser.parseOption4(problemText),
                            ProblemParser.parseOption5(problemText)
                    });
                    dbManager.saveProblem(problem);
                    System.out.println("transfer txt into mongo : " + problem.getTestName() + " " + problem.getTestNumber());
                }
                for (File file : getProblemGroupFiles(testDirectory)) {
                    String problemGroup = ProblemParser.parseProblemGroupWithoutName(
                            new TxtReader(file).read()
                    );
                    for (int i = Integer.parseInt(ProblemParser.parseProblemGroupNameStartNumber(file.getName()));
                         i <= Integer.parseInt(ProblemParser.parseProblemGroupNameEndNumber(file.getName()));
                         ++i) {
                        dbManager.updateProblemGroup(testDirectory.getName(), i, problemGroup);
                        System.out.println("update problem : " + testDirectory.getName() + " " + i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File[] getTestDirectories() {
        return parentDirectory.listFiles(File::isDirectory);
    }

    private File[] getProblemFiles(File testDirectory) {
        return testDirectory.listFiles(file -> {
            return Pattern.compile("^(\\d|\\d{2})\\.txt").matcher(file.getName()).matches();
        });
    }

    private File[] getProblemGroupFiles(File testDirectory) {
        return testDirectory.listFiles(file -> {
            return Pattern.compile("^\\[").matcher(file.getName()).matches();
        });
    }
}
