package runner.printscript;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import providers.inputprovider.TestInputProvider;
import providers.observer.Observer;
import providers.printprovider.TestPrintProvider;
import runner.Runner;
import runner.printscript.observers.RunnerTestObserver;

public class ObserversTests {
  private final ExpectedTransformer expectedTransformer = new ExpectedTransformer();

  @Test
  public void observerTest() throws IOException {
    RunnerTestObserver runnerTestObserver = new RunnerTestObserver();
    List<Observer> observers = List.of(runnerTestObserver);
    TestInputProvider testInputProvider = new TestInputProvider(List.of("47.63"));
    TestPrintProvider testPrintProvider = new TestPrintProvider();
    String expected = expectedTransformer.transform(List.of("47.63"));
    Runner runner = new Runner();
    runner.setObservers(observers);
    runner.run(
        new FileInputStream("src/test/resources/input/printNumberReadInput.txt"),
        "1.1",
        testPrintProvider,
        testInputProvider);
    assertFalse(testInputProvider.hasInputsToRead());
    assertEquals(expected, testPrintProvider.getMessages().next());
    assertEquals(List.of(" █ ", " █ "), runnerTestObserver.getMessages());
  }
}
