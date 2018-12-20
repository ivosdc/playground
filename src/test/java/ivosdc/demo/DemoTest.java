package ivosdc.demo;

import org.junit.Test;

import static org.junit.Assert.*;

public class DemoTest {

    @Test
    public void constructor_getsAndSets() {
        Demo demo = new Demo("name", "description");
        demo.setName("another_name");
        demo.setDescription("another_description");
        assertEquals("another_name", demo.getName());
        assertEquals("another_description", demo.getDescription());
    }

    @Test(expected = NullPointerException.class)
    public void constructor_nullName() {
        Demo demo = new Demo(null, "desc");
    }
}
