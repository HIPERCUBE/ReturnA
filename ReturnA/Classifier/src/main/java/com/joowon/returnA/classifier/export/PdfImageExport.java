package com.joowon.returnA.classifier.export;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Copyright (c) 3/31/16 Joowon Ryoo
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
public class PdfImageExport {
    public static void main(String[] args) throws IOException {
        String problem = "/Users/Joowon/Documents/Github/ReturnA/data/tests/problem/bnoRiCCI_h3_enga2_mun.pdf";
        String solve1 = "/Users/Joowon/Documents/Github/ReturnA/data/tests/solve/eng_hsj (88).pdf";
        String solve2 = "/Users/Joowon/Documents/Github/ReturnA/data/tests/solve/eng_hsj_81Np7vXe.pdf";
        PDDocument document = PDDocument.load(new File(solve2));
        export(document, "/Users/Joowon/Documents/Github/ReturnA", "image");
    }

    public static File[] export(PDDocument document, String filePath, String fileName) {
        File[] exportFiles = new File[document.getNumberOfPages()];
        try {
            PDFRenderer renderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) {
                BufferedImage image = renderer.renderImageWithDPI(page, 300, ImageType.RGB);

                final String file = filePath + "/" + fileName + "_" + (page + 1) + ".png";
                ImageIOUtil.writeImage(image, file, 300);
                exportFiles[page] = new File(file);

                System.out.println("Export image file from PDF : " + file + " [" + (page + 1) + "/" + document.getNumberOfPages() + "]");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.err.println("IOException occurred\nCheck file path.");
        }
        return exportFiles;
    }
}