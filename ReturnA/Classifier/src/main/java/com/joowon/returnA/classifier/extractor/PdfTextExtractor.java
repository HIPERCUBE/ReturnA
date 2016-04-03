package com.joowon.returnA.classifier.extractor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

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
public class PdfTextExtractor {
    private PDFTextStripperByArea stripper;
    private PDPage page;

    public static void main(String[] args) throws IOException, PrinterException {
        // Target PDF Document
        PDDocument document = PDDocument.load(new File("/Users/Joowon/Documents/Github/ReturnA/data/tests/YAPNXRPm_eng1_mun.pdf"));

        List<String> pdfTextList = new ArrayList<>();
        final int width = (int) document.getPage(0).getMediaBox().getWidth();
        final int height = (int) document.getPage(0).getMediaBox().getHeight();

        // Extract Test Information (first page's info area)
        pdfTextList.addAll(new PdfTextExtractor(document.getPage(0))
                .addRegion(0, 0, width, height / 4)
                .extract());

        for (int i = 0; i < document.getNumberOfPages(); ++i) {
            // Left side
            pdfTextList.addAll(new PdfTextExtractor(document.getPage(i))
                    .addRegion(0, 0, width / 2, height)
                    .extract());

            // Right side
            pdfTextList.addAll(new PdfTextExtractor(document.getPage(i))
                    .addRegion(width / 2, 0, width / 2, height)
                    .extract());
        }
        System.out.println(pdfTextList.toString());
    }

    public PdfTextExtractor(PDPage page) throws IOException {
        this.page = page;
        stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);
    }

    public PdfTextExtractor addRegion(int x, int y, int width, int height) {
        return addRegion(new Rectangle(x, y, width, height));
    }

    private int currentRegionPosition = 0;

    public PdfTextExtractor addRegion(Rectangle rectangle) {
        stripper.addRegion(String.valueOf(currentRegionPosition++), rectangle);
        return this;
    }

    public List<String> extract() throws IOException {
        stripper.extractRegions(page);
        List<String> textList = new ArrayList<>();
        for (String aRegionName : stripper.getRegions())
            textList.add(stripper.getTextForRegion(aRegionName));
        return textList;
    }
}
