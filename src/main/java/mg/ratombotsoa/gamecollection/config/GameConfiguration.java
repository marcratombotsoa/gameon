package mg.ratombotsoa.gamecollection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import mg.ratombotsoa.gamecollection.model.Console;
import mg.ratombotsoa.gamecollection.model.VideoGame;
import mg.ratombotsoa.gamecollection.model.builder.ConsoleBuilder;
import mg.ratombotsoa.gamecollection.model.builder.VideoGameBuilder;
import static mg.ratombotsoa.gamecollection.util.DateUtil.parseDate;

import java.security.SecureRandom;

@Configuration
public class GameConfiguration {

	private static final int PASSWORD_STRENGTH = 4;
	private static final SecureRandom RANDOM = new SecureRandom();
	
	/* Console Beans Definition */
	@Bean
	public Console xboxOne() {
		return new ConsoleBuilder().withName("XBox One").withManufacturer("Flextronics").withNumberOfBits(128)
				.withReleaseDate(parseDate("11/22/13")).build();
	}
	
	@Bean
	public Console ps4() {
		return new ConsoleBuilder().withName("Playstation 4").withManufacturer("Sony").withNumberOfBits(64)
				.withReleaseDate(parseDate("11/15/13")).build();
	}

	@Bean
	public Console nintendoSwitch() {
		return new ConsoleBuilder().withName("Nintendo Switch").withManufacturer("Nintendo PTD").withNumberOfBits(32)
				.withReleaseDate(parseDate("3/3/17")).build();
	}

	/* Game Beans Definition */
	
	@Bean
	public VideoGame fifa18() {
		return new VideoGameBuilder().withName("FIFA 18").withPublisher("EA Sports").withConsole(ps4())
				.withReleaseDate(parseDate("9/29/17")).build();
	}
	
	@Bean
	public VideoGame nb2k17() {
		return new VideoGameBuilder().withName("NBA 2K17").withPublisher("2K Sports").withConsole(ps4())
				.withReleaseDate(parseDate("9/20/16")).build();
	}
	
	@Bean
	public VideoGame splinterCellBlacklist() {
		return new VideoGameBuilder().withName("Splinter cell blacklist").withPublisher("Ubisoft").withConsole(xboxOne())
				.withReleaseDate(parseDate("8/20/13")).build();
	}
	
	@Bean
	public VideoGame marioKart8() {
		return new VideoGameBuilder().withName("Mario Kart 8").withPublisher("Nintendo").withConsole(nintendoSwitch())
				.withReleaseDate(parseDate("4/28/17")).build();
	}
	
	@Bean
	public VideoGame blackOps3() {
		return new VideoGameBuilder().withName("Call of duty: Black Ops 3").withPublisher("Activision").withConsole(ps4())
				.withReleaseDate(parseDate("11/6/15")).build();
	}
	
	@Bean
	public VideoGame hitman() {
		return new VideoGameBuilder().withName("Hitman 2016").withPublisher("Square Enix").withConsole(xboxOne())
				.withReleaseDate(parseDate("3/11/16")).build();
	}
	
	@Bean
	public VideoGame tekken7() {
		return new VideoGameBuilder().withName("Tekken 7").withPublisher("Bandai Namco Entertainment").withConsole(ps4())
				.withReleaseDate(parseDate("6/2/17")).build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(PASSWORD_STRENGTH, RANDOM);
		return encoder;
	}
}
