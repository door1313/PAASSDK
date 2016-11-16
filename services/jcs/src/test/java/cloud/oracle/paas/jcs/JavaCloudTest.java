package cloud.oracle.paas.jcs;

import org.junit.Test;

import java.util.logging.Level;

/**
 * Test for JavaCloud
 */
public class JavaCloudTest {
    @Test
    public void test() throws Exception {
        JavaCloud.Instance in = new JavaCloud.Instance();
        in.enableLog(Level.INFO);
        in.viewAll();
    }
}
