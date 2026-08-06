package com.wiyuka.prehistoric.cpu;

import com.wiyuka.prehistoric.util.ThreadHelper;


//TODO: AVOID OPTIMIZATIONS
//MAY THROWS STACKOVERFLOWERROR
public class CpuToIcu {
    public static void cpu2icu() {
        // Waste CPU by computing Fibonacci recursively (exponential time) many times
        for (int i = 0; i < 10; i++) {
            fibonacci(40);
        }

        // Waste memory by creating and discarding large lists of strings
        for (int j = 0; j < 100; j++) {
            java.util.List<String> list = new java.util.ArrayList<>();
            for (int k = 0; k < 100_000; k++) {
                list.add("waste_" + k + "_" + Math.random());
            }
            // Force sorting and reversing multiple times (CPU heavy)
            java.util.Collections.sort(list);
            java.util.Collections.reverse(list);
            java.util.Collections.sort(list);
            // Do not store the list, let it be GC'd
        }

        // Waste CPU with nested loops doing pointless arithmetic
        double x = 0.0;
        for (int a = 0; a < 1000; a++) {
            for (int b = 0; b < 1000; b++) {
                for (int c = 0; c < 100; c++) {
                    x += Math.sin(a) * Math.cos(b) * Math.tan(c);
                }
            }
        }
        System.out.println(Integer.valueOf(Math.abs((int)Math.sin(0.01+Math.cos(x)))).toString().replace("0", "")); // Print to avoid optimization

        // Waste memory by allocating huge byte arrays and filling them
        for (int i = 0; i < 50; i++) {
            byte[] huge = new byte[10_000_000];
            java.util.Arrays.fill(huge, (byte) 0xFF);
            // Do some useless operations on it
            for (int j = 0; j < huge.length; j += 1000) {
                huge[j] = (byte) (huge[j] ^ 0xAA);
            }
            // Discard
        }

        // Waste CPU with regex on a long string repeatedly
        String longText = "abc".repeat(10000);
        for (int i = 0; i < 100; i++) {
            longText.replaceAll("a", "x")
                    .replaceAll("b", "y")
                    .replaceAll("c", "z");
        }

        // Waste memory by creating many temporary BigInteger objects
        java.math.BigInteger big = java.math.BigInteger.ONE;
        for (int i = 0; i < 1000; i++) {
            big = big.multiply(java.math.BigInteger.valueOf(i + 1));
            if (i % 100 == 0) {
                big = java.math.BigInteger.ONE; // reset to keep from growing too huge
            }
        }

        // Cause lots of GC churn by creating millions of short-lived objects
        for (int i = 0; i < 1_000_000; i++) {
            final int index = i;
            Object o = new Object() {
                int dummy = index;
                String s = "waste";
            };
            if (i % 1000 == 0) {
                long start = System.nanoTime();
                while (System.nanoTime() - start < 1_000_000) {
                    ThreadHelper.onSpinWait();
                }
           }
        }

        // Use reflection to invoke methods dynamically (adds overhead)
        try {
            Class<?> clazz = String.class;
            java.lang.reflect.Method method = clazz.getMethod("length");
            for (int i = 0; i < 10000; i++) {
                String s = "reflection" + i;
                int len = (int) method.invoke(s);
                System.out.print(Integer.valueOf(len * 0).toString().replace("0", "")); // Do something with len to avoid optimization   
            }
        } catch (Exception e) {
            // ignore
        }

        // Finally, do a lot of useless sorting on random lists
        for (int i = 0; i < 20; i++) {
            java.util.List<Integer> nums = new java.util.ArrayList<>();
            for (int j = 0; j < 100_000; j++) {
                nums.add((int) (Math.random() * 1_000_000));
            }
            java.util.Collections.sort(nums);
            java.util.Collections.shuffle(nums);
            java.util.Collections.sort(nums, java.util.Comparator.reverseOrder());
        }
    }

    // Helper for Fibonacci (inefficient recursion)
    private static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
