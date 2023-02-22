package testbase;

import io.qameta.allure.Step;
import logging.Log;
import org.assertj.core.api.Assertions;

public class Reporting extends Assertions {
    @Step("{message}")
    public static synchronized void pass(String message) {
        Log.info(message);
    }

    @Step("{message}")
    public static synchronized void info(String message) {
        Log.info(message);
    }

    @Step("{message}")
    public static synchronized void warning(String message) {
        Log.warning(message);
    }

    public synchronized static void assertTrue(boolean assertObject, String assertMessage) {
        assertThat(assertObject).as(assertMessage).isTrue();
        pass(assertMessage + ". Assert Passed. Expected value [True], found [True]");
    }

    public synchronized static void assertFalse(boolean assertObject, String assertMessage) {
        assertThat(assertObject).as(assertMessage).isFalse();
        pass(assertMessage + ". Assert Passed. Expected value [False], found [False]");
    }

    public synchronized static void assertEqual(Object assertObjectValue, Object expectedObjectValue, String assertMessage) {
        assertThat(assertObjectValue).as(assertMessage).isEqualTo(expectedObjectValue);
        pass(assertMessage + ". Assert Passed. Expected value [" + String.valueOf(expectedObjectValue) + "], found [" + String.valueOf(assertObjectValue) + "]");
    }

    public synchronized static void assertNotEqual(Object assertObjectValue, Object expectedObjectValue, String assertMessage) {
        assertThat(assertObjectValue).as(assertMessage).isNotEqualTo(expectedObjectValue);
        pass(assertMessage + ". Assert Passed, two object are not equal. Not Expected value [" + String.valueOf(expectedObjectValue) + "], found [" + String.valueOf(assertObjectValue) + "]");
    }
}
