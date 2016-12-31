package kh.com.kshrd.core;

import org.assertj.core.util.Compatibility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.easymock.PowerMock.mockStatic;

/**
 * Created by sophatvathana on 15/12/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ExternalBaseTest.class)
@SpringBootTest
public class ExternalBaseTest {
    @Test
    public void getOS(){
        String os = ExternalBase.getOSType();
        assertThat(os).isEqualTo("mac");
    }
    public void loadNativeLibrary(){

    }
}
