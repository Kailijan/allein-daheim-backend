package de.team_franky.allein_daheim;

		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlleinDaheimApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlleinDaheimApplication.class, args);
	}

}
