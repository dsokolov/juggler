package me.ilich.juggler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @JugglerContent
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}