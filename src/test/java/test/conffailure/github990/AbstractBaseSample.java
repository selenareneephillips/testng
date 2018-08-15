package test.conffailure.github990;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBaseSample {
  public static List<String> messages = new ArrayList<>();

  @BeforeTest
  protected void setup() {
    throw new RuntimeException("Fail the test.");
  }

  @Test
  protected abstract void execute();

  @AfterTest(alwaysRun = true)
  protected void cleanup() {
    messages.add("cleanup");
  }
}
