package org.core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class EmployeeGeneratorServiceThreadBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    @Fork(1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void task1() {
        System.out.println(Thread.currentThread());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    @Fork(1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void task2() {
        System.out.println(Thread.currentThread());
    }

    /**<b>Thread Concepts</b> [create -> start ---> join]
     *  <li>pooling</li>
     *  <li> accessing same field :: locking (synchronized), Atomic Ops.</li>
     *  <li> Inter communication :: accessing respectively (producer-consumer) :: (wait & notify)</li>
     *  <li> flow (join, sleep)</li>
     *  <li> strategy :: how to define the problem for multi-threading</li>
     *  <li> computations vs IO</li>
     * <p>
     *
     * <b>Interfaces</b><p>
     *  Runnable        :: 1995   ::: new Thread(Class::task).start();
     * <p>
     *  <T> Callable<T> :: 2004   ::: Executors.newFixedThreadPool(n)
     *                                executorService.submit(Class::task).get()
     * <p>
     *  We can use executors for Callable & also, for Runnable interfaces!
     *  Basically, Callable returns something where Runnable void!
     *  .submit returns Future<T>
     * <p>
     *  Fork/Join         :: 2011     -> dividing into smart subtasks & merging for parallel programming
     *  ::: task = ....
     *  pool = ForkJoinPool.commonPool()      //a kind of Executors!
     *  pool.execute(task)       void,
     *  pool.invoke(task)        waits, then return result immediately,
     *  pool.submit(task).get()  waits, then use get() for result
     *
     *  subtask = ...
     *  subTask.fork()
     *  subTask.join() or subTask.invoke()
     *
     *               Future<T>
     *                  |
     *             ForkJoinTask<T>
     *         |                   |
     *  RecursiveAction        RecursiveTask<T>
     *  (void)
     *
     *  <p>
     *  parallelStreams   :: 2011     -> parallel programming, processing in-memory data, mostly non-blocking
     *  CompletableFuture :: 2014
     * <p>
     * <p>
     *  Project loom :: 2022 java19 preview ->  handling numerous blocking requests/responses
     *     thread == task no way to cut this bound!!
     *     creating 1m thread {now, it costs 2tb ram, 20min startup time & context switching}
     *  - CompletionState/CompletableFuture
     *  - async -> reactive programming
     *  - Mono/Multi (spring framework)
     *  Problem is callback hell, hard to test & profile & debug where Loom is to rescue :)
     * <p>
     *  So, threads will be two types :: platform or virtual
     *
     */


    public static void main(String[] args) throws RunnerException {

        /* jmh multi threading
         *   .threads(n) defines thread count. But JMH runs test sequentially.
         * So we can only multithreading One function at a time.
         *      So, every function must be divided into smart tasks !
         */
        System.out.println("..." + Thread.currentThread());

        var opt = new OptionsBuilder()
                .include(EmployeeGeneratorServiceThreadBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .threads(2)
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
//                .resultFormat(ResultFormatType.JSON)
//                .result("build/".concat(org.core.EmployeeGeneratorServiceThreadBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;

        System.out.println("..." + Thread.currentThread());

    }
}