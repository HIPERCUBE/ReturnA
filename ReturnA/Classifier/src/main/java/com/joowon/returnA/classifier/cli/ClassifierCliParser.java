package com.joowon.returnA.classifier.cli;

import org.apache.commons.cli.*;

/**
 * Copyright (c) 4/5/16 Joowon Ryoo
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
public class ClassifierCliParser {
    public static void main(String[] args) {
        new ClassifierCliParser(new String[]{"-h"});
    }

    private Options options = new Options();
    private CommandLine commandLine;

    public ClassifierCliParser() {
        options.addOption("t", "target", true, "target directory which contain pdf files");
        options.addOption("h", "help", false, "print usage");
    }

    public ClassifierCliParser(String[] args) {
        this();

        try {
            CommandLineParser parser = new DefaultParser();
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
            printHelp();
        }
        parse();
    }

    public void printHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("help", options);
    }

    public void parse() {
        // Check all necessary option is exist
        if (!commandLine.hasOption("t")
                | commandLine.hasOption("h")) {
            printHelp();
            throw new NoRequiredItemException();
        }
    }

    public String getTarget() {
        return commandLine.getOptionValue("t");
    }

    private boolean checkCommnadLineNull() {
        return commandLine != null;
    }
}
