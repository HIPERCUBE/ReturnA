package com.joowon.returnA.classifier.db;

import com.joowon.returnA.classifier.db.dto.Problem;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

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
public class MongoDbManager {
    public static MongoDbManager instance = null;

    public static MongoDbManager getInstance(String host) {
        if (instance == null)
            instance = new MongoDbManager(host);
        return instance;
    }


    private Datastore datastore;

    private final String DB_ReturnA = "ReturnA";

    private MongoDbManager(String host) {
        // Morphia setup
        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.joowon.returnA.classifier.db.dto");
        datastore = morphia.createDatastore(new MongoClient(host), DB_ReturnA);

//     datastore.ensureIndexes();
    }

    public void saveProblem(Problem problem) {
        datastore.save(problem);
    }

    public void updateProblemGroup(String queryTestName, int queryNumber, String group) {
    }

    public UpdateResults updateAnswer(String testName, int testNumber, String answer) {
        final Query<Problem> problemFindQuery = datastore.createQuery(Problem.class);
        problemFindQuery.field("testName").equal(testName);
        problemFindQuery.field("testNumber").equal(testNumber);
        final UpdateOperations<Problem> updateOperation = datastore.createUpdateOperations(Problem.class)
                .set("answer", answer);
        return datastore.update(problemFindQuery, updateOperation);
    }


    public static final String DEFAULT_PORT = "27017";

    public static void main(String[] args) {
        MongoDbManager manager = MongoDbManager.getInstance("localhost:27017");
        manager.saveProblem(new Problem("test"));
    }
}