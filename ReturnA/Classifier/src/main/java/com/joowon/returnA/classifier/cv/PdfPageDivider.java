package com.joowon.returnA.classifier.cv;

import com.joowon.returnA.classifier.util.NumUtil;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.*;

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
        for (int i = 1; i <= 9; ++i) {
            for (double[] a : new PdfPageDivider("/Users/Joowon/Documents/Github/ReturnA/imagea_" + i + ".png")
                    .divide()
                    .findBody()) {
                System.out.println(Arrays.toString(a));
            }
            System.out.println("= = = = = = = = = = = ");
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
        Imgproc.HoughLinesP(edges, lines, 10, Math.PI / 180, 50, 50, 10);
        return this;
    }

    public double[] findHeadLine() {
        double[] currentResult = getVerticalSeparations().get(0);

        double[] result = new double[2];
        result[0] = 0;   // Body start point Y
        result[1] = currentResult[3] / (double) img.height();   // Body end point Y
        return result;
    }

    /**
     * result[][]
     * [[x, y, width, height], [x, y, width, height]]
     */
    public double[][] findBody() {
        List<double[]> separations = getVerticalSeparations();
        Collections.sort(separations, (o1, o2) -> o1[0] < o2[0] ? -1 : o1[0] < o2[0] ? 1 : 0);

        if (separations.size() == 0) {
            return new double[][]{
                    {0, 0, img.width(), img.height()}
            };
        }

        double[][] result = new double[separations.size() + 1][4];
        for (int i = 0; i < result.length; ++i) {
            double val[] = null;
            double beforeVal[] = null;
            if (i != 0)
                beforeVal = separations.get(i - 1);
            if (i != separations.size())
                val = separations.get(i);

            if (i == 0) {
                result[i][0] = 0;
                result[i][1] = (double) NumUtil.getSmaller(val[1], val[3]);
                result[i][2] = val[0];
                result[i][3] = (double) NumUtil.getBigger(val[1], val[3]);
            } else if (i == separations.size()) {
                result[i][0] = beforeVal[0];
                result[i][1] = (double) NumUtil.getSmaller(beforeVal[1], beforeVal[3]);
                result[i][2] = img.width();
                result[i][3] = (double) NumUtil.getBigger(beforeVal[1], beforeVal[3]);
            } else {
                result[i][0] = beforeVal[0];
                result[i][1] = (double) NumUtil.getSmaller(val[1], val[3]);
                result[i][2] = val[0];
                result[i][3] = (double) NumUtil.getBigger(val[1], val[3]);
            }
        }
        return result;
    }

    private List<double[]> getVerticalSeparations() {
        List<double[]> lineList = new ArrayList<>();
        for (int i = 0; i < lines.rows(); i++) {
            double[] val = lines.get(i, 0);
            double distanceY = Math.abs(val[1] - val[3]);
            if (img.height() / 2 < distanceY)
                lineList.add(val);
        }
        return lineList;
    }
}