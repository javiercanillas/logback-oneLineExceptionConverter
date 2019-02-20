package me.java.logging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(BlockJUnit4ClassRunner.class)
public class ExceptionLoggingTest {

    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionLoggingTest.class);

    @Test
    public void testLoggingException() {
        LOGGER.info("Here I come");
        RuntimeException e = new RuntimeException("This is a test cause exception");
        LOGGER.error("ExpectedError", new RuntimeException("This is a test", e));
        LOGGER.info("Did you see it?");
    }
}
