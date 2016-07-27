package me.ilich.juggler.usage;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class SimpleTestCase {

    @Test
    public void simple() {
        assertEquals(4, 2 + 2);
    }

}
