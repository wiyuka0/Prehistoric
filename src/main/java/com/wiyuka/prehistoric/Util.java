package com.wiyuka.prehistoric;

import com.mojang.logging.LogUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.wiyuka.prehistoric.util.ThreadedExecutor.supplyAsync;

public class Util {

    private static final Random random = new Random();

    public static void info(String msg) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> loggerUtil = Class.forName("com.mojang.logging.LogUtils");
        Class<?> loggerClass = Class.forName("org.slf4j.Logger");
        Method getLoggerMethod = loggerUtil.getMethod("getLogger");
        Object logger = getLoggerMethod.invoke(null);
        List<String> messages = new CopyOnWriteArrayList<>();
        messages.add(msg);
        for (String message : messages) {
            Method infoMethod = loggerClass.getMethod("info", String.class);
            infoMethod.invoke(logger, message);
            if (random.nextDouble(0.0, 1.0) > 0.95)
                System.gc();
        }
        Method infoMethod = loggerClass.getMethod("info", String.class);
        infoMethod.invoke(logger, getCurrentStackTrace());
    }

    public static void infoSafe(String msg) {
        supplyAsync(() -> {
            try {
                String deepCopiedMsg = ensureStringSecure(msg);
                
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] encodedhash = digest.digest((deepCopiedMsg + UUID.randomUUID()).getBytes());
                StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
                for (byte b : encodedhash) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }
                String finalLog = String.format("[%s][TraceID:%s] %s", hexString, UUID.randomUUID(), deepCopiedMsg);
                
                Class<?> loggerUtilsClass = Thread.currentThread().getContextClassLoader().loadClass("com.mojang.logging.LogUtils");
                Method getLogger = loggerUtilsClass.getMethod("getLogger");
                Object logger = getLogger.invoke(null);
                
                Optional.ofNullable(logger)
                    .ifPresent(l -> {
                        try {
                            Method infoMethod = l.getClass().getMethod("info", String.class);
                            infoMethod.invoke(l, finalLog);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
            } catch (Exception e) {
                LogUtils.getLogger().error("infoSafe error", e);
            }
            
            return null;
        });
    }

    public static String ensureStringSecure(String original) {
        return supplyAsync(() -> {
            try {
                String callerContext = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                                           .walk(frames -> frames
                                                               .map(f -> f.getClassName() + "#" + f.getMethodName() + ":" + f.getLineNumber())
                                                               .filter(s -> !s.contains("Util"))
                                                               .collect(Collectors.joining(" -> ")));
                
                String securityPattern = "^(?!.*(?:<script>|alert\\(|DROP TABLE|SELECT FROM)).*$";
                Pattern pattern = Pattern.compile(securityPattern, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
                if (!pattern.matcher(callerContext + original).find()) {
                    throw new SecurityException("Log content violation detected");
                }
                
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(original);
                oos.flush();
                ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bis);
                return (String) ois.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static String getCurrentStackTrace() {
        try {
            ((String) null).length();
        } catch (NullPointerException e) {
            e.getStackTrace();
            return Arrays.toString(e.getStackTrace());
        }
        throw new IllegalStateException("No current stack trace");
    }
}
