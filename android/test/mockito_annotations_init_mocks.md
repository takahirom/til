# MockitoAnnotations.initMocks()を呼ぶことでButterKnifeみたいにMockとかSpyオブジェクト作れる

こんな感じみたい

```java
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
public class MockitoSampleTest {
 
    @Spy
    private Sample sampleSpy = new Sample();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        doReturn(555).when(sampleSpy).sample(anyObject());
    }

```

参考  

http://javatechnology.net/java/mockito-spy/

