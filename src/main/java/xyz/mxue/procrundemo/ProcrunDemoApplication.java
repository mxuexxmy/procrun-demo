package xyz.mxue.procrundemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import xyz.mxue.procrundemo.enums.ExecutionTypeEnum;

import java.lang.management.ManagementFactory;

/**
 * @author mxuexxmy
 */
@SpringBootApplication
public class ProcrunDemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProcrunDemoApplication.class);
    private static ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        String mode = args != null && args.length > 0 ? args[0] : null;

        if (logger.isDebugEnabled()) {
            logger.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application mode:" +
                    mode + " context:" + applicationContext);
        }
        if (applicationContext != null && ExecutionTypeEnum.STOP.getCode().equals(mode)) {
            System.exit(SpringApplication.exit(applicationContext, () -> 0));
        } else {
            SpringApplication app = new SpringApplication(ProcrunDemoApplication.class);
            applicationContext = app.run(args);
            if (logger.isDebugEnabled()) {
                logger.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application started context:" +
                        applicationContext);
            }
        }
    }

}
