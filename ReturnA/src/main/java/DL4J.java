import org.canova.api.util.ClassPathResource;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.plot.BarnesHutTsne;
import org.deeplearning4j.text.sentenceiterator.CollectionSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EndingPreProcessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 * Copyright (c) 4/30/16 Joowon Ryoo
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
public class DL4J {
    private static Logger log = LoggerFactory.getLogger(DL4J.class);

    public static void main(String[] args) throws IOException {
        new DL4J().runGoogleNews();
//        new DL4J().run();
    }

    public void runGoogleNews() throws IOException {
        File gFile = new File(getClass().getClassLoader().getResource("dataset").getFile() + "/GoogleNews-vectors-negative300.bin");
        WordVectors vec = WordVectorSerializer.loadGoogleModel(gFile, true);

        log.info("evaluate model...");
        double sim = vec.similarity("people", "money");
        log.info("Similarity between peeple and money: " + sim);
        Collection<String> similar = vec.wordsNearest("human", 10);
        log.info("Similarity words to 'human' : " + similar);
    }

    public void run() throws IOException {
        log.info("Load data...");
//        ClassPathResource resource = new ClassPathResource("raw_sentences.txt");
//        SentenceIterator iter = new LineSentenceIterator(resource.getFile());
        SentenceIterator iter = new LineSentenceIterator(new File("/Users/Joowon/Desktop/testFile.txt"));
        iter.setPreProcessor((SentencePreProcessor) String::toLowerCase);


        log.info("Tokenize data...");
        final EndingPreProcessor preProcessor = new EndingPreProcessor();
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
        tokenizerFactory.setTokenPreProcessor(token -> {
            token = token.toLowerCase();
            String base = preProcessor.preProcess(token);
            base = base.replaceAll("\\d", "d");
            if (base.endsWith("ly") || base.endsWith("ing"))
                System.out.println();
            return base;
        });


        log.info("Build model...");
        int batchSize = 1000;
        int iterations = 3;
        int layerSize = 300;
        Word2Vec vec = new Word2Vec.Builder()
                .batchSize(batchSize)               // # words per minibatch.
                .minWordFrequency(5)                //
                .useAdaGrad(false)                  //
                .layerSize(layerSize)               // word feature vector size
                .iterations(iterations)             // # iterations to train
                .learningRate(0.025)                //
                .minLearningRate(1e-3)              // learning rate decays wrt # words. floor learning
                .negativeSample(10)                 // sample size 10 words
                .iterate(iter)                      //
                .tokenizerFactory(tokenizerFactory) //
                .build();
        vec.fit();


        log.info("evaluate model...");
        double sim = vec.similarity("people", "money");
        log.info("Similarity between peeple and money: " + sim);
        Collection<String> similar = vec.wordsNearest("human", 10);
        log.info("Similarity words to 'day' : " + similar);


//        log.info("Plot TSNE");
//        BarnesHutTsne tsne = new BarnesHutTsne.Builder()
//                .setMaxIter(1000)
//                .stopLyingIteration(250)
//                .learningRate(500)
//                .useAdaGrad(false)
//                .theta(0.5)
//                .setMomentum(0.5)
//                .normalize(true)
//                .usePca(false)
//                .build();
//        vec.lookupTable().plotVocab(tsne);

        log.info("Save vectors....");
        WordVectorSerializer.writeWordVectors(vec, "/Users/Joowon/Desktop/words.txt");
    }
}