package ivosdc.demo;

import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class DemoServiceTest {
    private static final Long AN_ID = 1L;
    private static final String A_NAME = "name";
    private static final String A_DESCRIPTION = "description";
    private DemoService unitUnderTest;
    private DemoProvider mockDemoProvider;
    private DemoRepository mockDemoRepository;

    @Before
    public void createMocks() {
        mockDemoRepository = mock(DemoRepository.class);
        mockDemoProvider = mock(DemoProvider.class);

        unitUnderTest = new DemoService(mockDemoRepository);

        when(mockDemoRepository.save(any(Demo.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(mockDemoRepository.findById(AN_ID))
                .thenReturn(Optional.of(createDemo()));
        when(mockDemoProvider.retrieveDemoId(A_NAME)).thenReturn(AN_ID);
    }

    @Test(expected = InvalidParameterException.class)
    public void create_emptyName_throwsException() {
        unitUnderTest.create("", A_DESCRIPTION);
    }

    @Test
    public void create_returnsCreatedDemo() {
        when(mockDemoRepository.save(any(Demo.class))).then(invocation -> {
            final Demo saveDemo = invocation.getArgument(0);
            Demo demo = new Demo(saveDemo.getName(), saveDemo.getDescription());

            return demo;
        });

        final Demo actual = unitUnderTest.create(A_NAME, A_DESCRIPTION);

        assertEquals(A_NAME, actual.getName());
        assertEquals(A_DESCRIPTION, actual.getDescription());
    }

    @Test
    public void getDemo_returnsDemoObject() {
        final Demo actual = unitUnderTest.getDemo(AN_ID);

        assertEquals(A_NAME, actual.getName());
        assertEquals(A_DESCRIPTION, actual.getDescription());
    }

    @Test
    public void getAll_returnsListOfDemoObject() {
        final List<Demo> allDemos = new ArrayList<>();
        allDemos.add(createDemo());
        when(mockDemoRepository.findAll()).thenReturn(allDemos);

        final List<Demo> actual = unitUnderTest.getAll();

        assertEquals(allDemos, actual);
    }



    private static Demo createDemo() {
        return new Demo(A_NAME, A_DESCRIPTION);
    }
}
