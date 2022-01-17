import java.io.IOException;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Main {
  public static void main (String[] args) throws IOException, RunnerException {
    Options opt = new OptionsBuilder()
        .addProfiler(GCProfiler.class)
        .build();
    new Runner(opt).run();
  }
}