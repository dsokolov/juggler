package me.ilich.juggler.usage;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.SmallTest;
import androidx.test.runner.AndroidJUnit4;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SimpleTestCase {

    @Test
    public void simple() {
        assertEquals(4, 2 + 2);
    }

}
