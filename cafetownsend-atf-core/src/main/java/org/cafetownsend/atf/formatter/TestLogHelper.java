package org.cafetownsend.atf.formatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestLogHelper {

    private static final Logger logger = LoggerFactory.getLogger(TestLogHelper.class);
    private static final String TEST_NAME_KEY = "testname";
    public static String CURRENT_LOG_NAME = "";
    private static String timeStamp;

    private TestLogHelper() {
    }

    public static void startTestLogging(String name) {
        MDC.put(TEST_NAME_KEY, name);
        CURRENT_LOG_NAME = name;
    }

    public static String stopTestLogging() {
        String name = MDC.get(TEST_NAME_KEY);
        MDC.remove(TEST_NAME_KEY);
        return name;
    }

    public static String getCurrentLogName() {
        String testname = MDC.get(TEST_NAME_KEY);
        return testname == null ? "test" : testname;
    }

    public static String createTimeStamp(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH-mm-ss.SSS");
        return LocalDateTime.now().format(dateTimeFormatter);
    }

}
