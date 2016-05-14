package com.joowon.returnA.classifier;

import com.joowon.returnA.classifier.extractor.PdfTextExtractor;
import com.joowon.returnA.classifier.txt.TxtWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;

/**
 * Copyright (c) 5/11/16 Joowon Ryoo
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
public class EbsBookCrawler {
    public static void main(String[] args) throws IOException {
        new EbsBookCrawler().run();
    }

    public void run() throws IOException {
        File destination = new File("/Users/Joowon/Desktop");
        File bookFolder = new File(getClass().getClassLoader().getResource("book").getFile());
        for (File book : bookFolder.listFiles()) {
            String outputName = destination.getPath() + "/" + book.getName().replace(".pdf", ".txt");
            PDDocument document = PDDocument.load(book);
            String text = "";
            for (PDPage page : document.getPages()) {
                text += new PdfTextExtractor(page)
                        .addRegion(
                                0,
                                0,
                                (int) page.getMediaBox().getWidth(),
                                (int) page.getMediaBox().getHeight())
                        .extract();
            }

            new TxtWriter(outputName)
                    .write(text);

            document.close();
        }
    }
}
