package com.joowon.returnA.classifier;

import com.joowon.returnA.classifier.cv.PdfPageDivider;
import com.joowon.returnA.classifier.export.PdfImageExport;
import com.joowon.returnA.classifier.extractor.PdfTextExtractor;
import com.joowon.returnA.classifier.parser.HeadlineParser;
import javafx.beans.DefaultProperty;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

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
public class Classifier {
    protected PDDocument document;
    protected String destinationParentPath;

    protected static final String DATA_FROM_EBS_PATH = "/Users/Joowon/Documents/Github/ReturnA/data/DaraFromEBS";

    public Classifier(PDDocument document, String path) {
        // Export images from PDF
        this.document = document;
        this.destinationParentPath = path;
        PdfImageExport.export(document, destinationParentPath, "image");
    }

    @Deprecated
    public String getTestName() throws IOException {
        double[] bodyPosition = new PdfPageDivider(destinationParentPath + "/image_" + 1 + ".png")
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

    public String getBodyText() throws IOException {
        int numberOfPages = document.getNumberOfPages();

        String text = "";
        for (int i = 1; i <= numberOfPages; ++i) {
            double[][] bodyPosition = new PdfPageDivider(destinationParentPath + "/image_" + i + ".png")
                    .divide()
                    .findBody();
            PDPage page = document.getPage(i - 1);

            for (double[] val : bodyPosition) {
                text += new PdfTextExtractor(page)
                        .addRegion(
                                (int) (val[0] * page.getMediaBox().getWidth()),
                                (int) (val[1] * page.getMediaBox().getHeight()),
                                (int) (val[2] * page.getMediaBox().getWidth()),
                                (int) (val[3] * page.getMediaBox().getHeight())
                        ).extract();
            }
        }
        return text;
    }

    public void close() {
        try {
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
