package ivosdc.demo;

public class DemoNotFoundException extends RuntimeException {
    DemoNotFoundException()  {
        super("Demo not found.");
    }
}
