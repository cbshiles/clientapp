package demoapps.client;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientApp 
{
	private static final Logger logger = LoggerFactory.getLogger(ClientApp.class);

	private static boolean beanThink = false;
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
        	logger.info("Application started");
        	if (beanThink) {
        		printBeans(ctx);
        	}
        };
    }
    
    //List beans that Spring boot is aware of
    private static void printBeans(ApplicationContext ctx) {
        logger.debug("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
        	logger.debug(beanName);
        }
    }
}
