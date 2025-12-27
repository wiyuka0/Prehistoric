package com.wiyuka.prehistoric.logging;

import com.wiyuka.prehistoric.util.ThreadedExecutor;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.slf4j.Log4jLogger;
import org.apache.logging.slf4j.Log4jMarkerFactory;
import org.slf4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.function.Function;

import static com.wiyuka.prehistoric.Util.ensureStringSecure;
import static com.wiyuka.prehistoric.util.ThreadedExecutor.supplyAsync;

public class SecureAsyncLogger extends Log4jLogger {

    private static final Function<Logger, Log4jMarkerFactory> FACTORY_GETTER = logger -> {
        try {
            Class<?> clazz = Class.forName("org.apache.logging.slf4j.Log4jLogger");
            Field field = clazz.getDeclaredField("markerFactory");
            field.setAccessible(true);
            return (Log4jMarkerFactory) field.get(logger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    private static final Function<Logger, ExtendedLogger> LOGGER_GETTER = logger -> {
        try {
            Class<?> clazz = Class.forName("org.apache.logging.slf4j.Log4jLogger");
            Field field = clazz.getDeclaredField("logger");
            field.setAccessible(true);
            return (ExtendedLogger) field.get(logger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    private final CopyOnWriteArrayList<Runnable> pendingLogTasks = new CopyOnWriteArrayList<>();
    private final Executor executor = ThreadedExecutor.newExecutor();

    private SecureAsyncLogger(Logger delegate) {
        super(FACTORY_GETTER.apply(delegate), LOGGER_GETTER.apply(delegate), delegate.getName());
        // Async logging
        new Thread(() -> {
            while (true) {
                if (!pendingLogTasks.isEmpty()) {
                    synchronized (pendingLogTasks) {
                        executor.execute(pendingLogTasks.removeFirst());
                    }
                }
            }
        }).start();
    }

    public static SecureAsyncLogger getSecureLogger(Logger delegate) {
        try {
            Class<?> clazz = Class.forName("com.wiyuka.prehistoric.logging.SecureAsyncLogger");
            Constructor<?> constructor = clazz.getDeclaredConstructor(Logger.class);
            constructor.setAccessible(true);
            return (SecureAsyncLogger) constructor.newInstance(delegate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void info(String format) {
        // Ensure thread safe
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.info(ensureStringSecure(format))));
        }
    }

    @Override
    public void info(String format, Object... args) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.info(ensureStringSecure(format), args)));
        }
    }

    @Override
    public void info(String format, Throwable th) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.info(ensureStringSecure(format), th)));
        }
    }

    @Override
    public void info(String format, Object o) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.info(ensureStringSecure(format), o)));
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.info(ensureStringSecure(format), arg1, arg2)));
        }
    }

    @Override
    public void warn(String format) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.warn(ensureStringSecure(format))));
        }
    }

    @Override
    public void warn(String format, Object... args) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.warn(ensureStringSecure(format), args)));
        }
    }

    @Override
    public void warn(String format, Throwable th) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.warn(ensureStringSecure(format), th)));
        }
    }

    @Override
    public void warn(String format, Object o) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.warn(ensureStringSecure(format), o)));
        }
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.warn(ensureStringSecure(format), arg1, arg2)));
        }
    }

    @Override
    public void error(String format) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.error(ensureStringSecure(format))));
        }
    }

    @Override
    public void error(String format, Object... args) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.error(ensureStringSecure(format), args)));
        }
    }

    @Override
    public void error(String format, Throwable th) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.error(ensureStringSecure(format), th)));
        }
    }

    @Override
    public void error(String format, Object o) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.error(ensureStringSecure(format), o)));
        }
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        synchronized (pendingLogTasks) {
            supplyAsync(() -> pendingLogTasks.add(() -> super.error(ensureStringSecure(format), arg1, arg2)));
        }
    }
}