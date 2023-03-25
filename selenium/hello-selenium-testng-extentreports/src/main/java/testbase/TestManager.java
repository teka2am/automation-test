package testbase;

import logging.Log;
import reporting.ReportManager;

public class TestManager {
    private static ThreadLocal<Integer> tl_stepCount = new ThreadLocal<>();

    public static void startTestClass(String className){
        Log.info("++++++++++ START TEST: [" + className + "] ++++++++++");
        ReportManager.createNewTestNode(className);
    }

    public static void endTestClass(){
        ReportManager.endCurrentTestNode();
        Log.info("++++++++++ END TEST ++++++++++" + System.getProperty("line.separator"));
    }

    public static void startTestCase(String testName){
        Log.info("START TEST: [" + testName + "]");
        ReportManager.createNewTestNode(testName);
        tl_stepCount.set(0);
    }

    public static void endTestCase(){
        if(tl_stepCount.get()>0){
            ReportManager.endCurrentTestNode();
        }
        ReportManager.endCurrentTestNode();
        Log.info("END TEST" + System.getProperty("line.separator"));
    }

    public static void startStep(String description){
        if(tl_stepCount.get()>0){
            ReportManager.endCurrentTestNode();
        }
        tl_stepCount.set(tl_stepCount.get()+1);
        ReportManager.createNewTestNode("STEP " + tl_stepCount.get() + ": " + description);
    }
}
