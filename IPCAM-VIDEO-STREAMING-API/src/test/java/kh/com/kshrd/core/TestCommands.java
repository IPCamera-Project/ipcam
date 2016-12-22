package kh.com.kshrd.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by sophatvathana on 15/12/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ExternalBaseTest.class)
@SpringBootTest
public class TestCommands {
    private HikVisionTest hikVisionTest;

    public TestCommands(){
        hikVisionTest = new HikVisionTest("DS-2CD2Q10FD-IW", "192.168.0.31", 80, "admin", "12345", 554, 1, true);
        hikVisionTest.initialize();
    }

    @Test
    public void testCommand(){


    }


}
