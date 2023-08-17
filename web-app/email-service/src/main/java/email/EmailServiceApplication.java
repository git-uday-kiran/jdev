package email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class EmailServiceApplication {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(EmailServiceApplication.class);
        Environment env = app.run(args).getEnvironment();

        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\t\thttp://localhost:{}\n\t" +
                        "External: \t\thttp://{}:{}\n\t" +
                        "Environment: \t{} \n" +
                        "----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                Arrays.toString(env.getActiveProfiles())
        );
    }
}
