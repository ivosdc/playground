package ivosdc.demo;

import org.springframework.stereotype.Service;

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

}
