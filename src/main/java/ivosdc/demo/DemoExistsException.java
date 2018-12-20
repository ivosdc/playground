package ivosdc.demo;

public class DemoExistsException extends RuntimeException {
    public DemoExistsException(String name) {
        super(name + " exists in database.");
    }
}
