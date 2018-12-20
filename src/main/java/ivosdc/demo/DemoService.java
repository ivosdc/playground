package ivosdc.demo;

import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService implements DemoProvider {
    private final DemoRepository demoRepository;

    DemoService(final DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Override
    public long retrieveDemoId(String demoName) {
        return demoRepository.findByName(demoName).orElseThrow(DemoNotFoundException::new).getId();
    }

    public List<Demo> getAll() {
        final List<Demo> demos = new ArrayList<>();
        this.demoRepository.findAll().forEach(demo -> demos.add(demo));

        return demos;
    }

    public Demo getDemo(long demoId) {
        return this.demoRepository.findById(demoId).orElseThrow(DemoNotFoundException::new);
    }

    public Demo create(String name, String description) {
        validateInput(name);
        demoRepository.findByName(name)
                .ifPresent(demo -> { throw new DemoExistsException(name + " exists in database");});
        Demo demo = new Demo(name, description);

        return demoRepository.save(demo);
    }

    private static void validateInput(final String name) {
        if (name == "") {
            throw new InvalidParameterException("name may not be empty");
        }
    }

}
