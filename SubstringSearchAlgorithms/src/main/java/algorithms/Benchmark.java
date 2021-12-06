package algorithms;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Threads(1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5, time=1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations =5, time =1, timeUnit = TimeUnit.SECONDS  )
@Fork(value = 3, jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g",})
public class Benchmark {
    @Param({"What does the brain matter",
        "What does the brain matter does the the does the"})
    String string;
    @Param({"the","abra"})
    String pattern;

    //@org.openjdk.jmh.annotations.Benchmark
    public void rkSearchBenchmark(){
      SubstringSearchAlgorithms.rkSearch(string, pattern);
    }

    //@org.openjdk.jmh.annotations.Benchmark
    public void bmSearchBenchmark(){
      SubstringSearchAlgorithms.bmSearch(string, pattern);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void kmpSearchBenchmark(){
      SubstringSearchAlgorithms.kmpSearch(string, pattern);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void directSearchBenchmark(){
      SubstringSearchAlgorithms.directSearch(string, pattern);
    }


}
