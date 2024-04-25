package sadiva.mpi.platformbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class PlatformBackendApplication {

	public static void main(String[] args) {
    SpringApplication.run(PlatformBackendApplication.class, args);
	}

}
