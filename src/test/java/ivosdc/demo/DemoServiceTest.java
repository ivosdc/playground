package ivosdc.demo;

import org.junit.Before;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class DemoServiceTest {
    private static final Long A_ID = 1L;
    private static final String A_NAME = "name";
    private static final String A_DESCRIPTION = "description";
    private DemoService mockDemoService;
    private DemoProvider mockDemoProvider;
    private DemoRepository mockDemoRepository;

    @Before
    public void createMocks() {
        mockDemoRepository = mock(DemoRepository.class);
        mockDemoProvider = mock(DemoProvider.class);

        mockDemoService = spy(new DemoService(mockDemoRepository));

        when(mockDemoRepository.save(any(Demo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(mockDemoRepository.findById(A_ID))
                .thenReturn(Optional.of(createDemo()));
        when(mockDemoProvider.retrieveDemoId(A_NAME)).thenReturn(A_ID);
    }

    private static Demo createDemo() { return new Demo(A_NAME, A_DESCRIPTION); }

}