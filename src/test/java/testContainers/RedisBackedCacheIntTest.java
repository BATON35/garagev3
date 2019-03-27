package testContainers;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;


public class RedisBackedCacheIntTest {

    @Rule
    public GenericContainer redis = new GenericContainer<>("mysql:5.7")
            .withExposedPorts(3306);


    @Before
    public void setUp() {
        String address = redis.getContainerIpAddress();
        Integer port = redis.getFirstMappedPort();
    }

    @Test
    public void testSimplePutAndGet() {
    }
}