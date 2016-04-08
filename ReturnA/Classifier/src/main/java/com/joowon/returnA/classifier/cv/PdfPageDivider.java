package com.joowon.returnA.classifier.cv;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Arrays;

/**
 * Copyright (c) 3/30/16 Joowon Ryoo
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
public class PdfPageDivider {
    public static void main(String[] args) {
        for (int i = 1; i < 8; ++i) {
            System.out.println(Arrays.toString(
                    new PdfPageDivider("/Users/Joowon/Documents/Github/ReturnA/data/tests/problem/parsed/image_" + i + ".png")
                            .divide()
                            .findHeadLine()));
        }
    }

    private Mat img;
    private Mat lines;

    public PdfPageDivider(String targetPath) {
        // Load library
        System.load("/Users/Joowon/Documents/Github/ReturnA/ReturnA/Classifier/libs/libopencv_java300.dylib");
        this.img = Imgcodecs.imread(targetPath);
    }

    public PdfPageDivider divide() {
        // generate gray scale and blur
        Mat gray = new Mat();
        Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.blur(gray, gray, new Size(3, 3));

        // detect the edges
        Mat edges = new Mat();
        int lowThreshold = 50;
        int ratio = 3;
        Imgproc.Canny(gray, edges, lowThreshold, lowThreshold * ratio);

        lines = new Mat();
        Imgproc.HoughLinesP(edges, lines, 1, Math.PI / 180, 50, 50, 10);
        return this;
    }

    public double[] findHeadLine() {
        double[] currentResult = getCenterHorizontalLine();

        double[] result = new double[2];
        result[0] = 0;   // Body start point Y
        result[1] = currentResult[3] / (double) img.height();   // Body end point Y
        return result;
    }

    public double[] findBody() {
        double[] currentResult = getCenterHorizontalLine();

        double[] result = new double[2];
        result[0] = currentResult[3] / (double) img.height();   // Body start point Y
        result[1] = currentResult[1] / (double) img.height();   // Body end point Y
        return result;
    }

    private double[] getCenterHorizontalLine() {
        double[] result = new double[4];
        double currentDistanceY = 0;
        for (int i = 0; i < lines.cols(); i++) {
            double[] val = lines.get(0, i);
            double distanceY = Math.abs(val[1] - val[3]);
            if (currentDistanceY < distanceY)
                result = val;
        }
        return result;
    }
}


