package xyz.mxue.procrundemo;

import org.springframework.boot.loader.JarLauncher;
import org.springframework.boot.loader.jar.JarFile;
import xyz.mxue.procrundemo.enums.ExecutionTypeEnum;

/**
 * @description: 引导程序
 * @author: mxuexxmy
 * @date: 2023/5/7 16:19
 * @since : 1.0
 */
public class Bootstrap extends JarLauncher {

    private static ClassLoader classLoader = null;
    private static Bootstrap bootstrap = null;

    public static void start(String[] args) {
        bootstrap = new Bootstrap();
        try {
            JarFile.registerUrlProtocolHandler();
            classLoader = bootstrap.createClassLoader(bootstrap.getClassPathArchivesIterator());
            bootstrap.launch(args, bootstrap.getMainClass(), classLoader);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static void stop(String[] args) {
        try {
            if (bootstrap != null) {
                bootstrap.launch(args, bootstrap.getMainClass(), classLoader);
                bootstrap = null;
                classLoader = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        String mode = args != null && args.length > 0 ? args[0] : null;
        if (ExecutionTypeEnum.START.getCode().equals(mode)) {
            Bootstrap.start(args);
        } else if (ExecutionTypeEnum.STOP.getCode().equals(mode)) {
            Bootstrap.stop(args);
        }
    }

    protected void launch(String[] args, String mainClass, ClassLoader classLoader)
            throws Exception {
        Thread.currentThread().setContextClassLoader(classLoader);
        Thread runnerThread = new Thread(() -> {
            try {
                createMainMethodRunner(mainClass, args, classLoader).run();
            } catch (Exception ignored) {

            }
        });
        runnerThread.setContextClassLoader(classLoader);
        runnerThread.setName(Thread.currentThread().getName());
        runnerThread.start();
        runnerThread.join();
    }

}
