package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(info = @Info(title = "Flowers API", version = "2.0", description = "Flowers DB management"))
@SpringBootApplication
public class S05T01N02LlombartRomaToniApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N02LlombartRomaToniApplication.class, args);
	}

}
