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
@PrepareForTest(RtspStream.class)
@SpringBootTest
public class RtspStreamTest {

    @Test
    public void getNativePath(){
        RtspStream.getOSType();
    }

}
