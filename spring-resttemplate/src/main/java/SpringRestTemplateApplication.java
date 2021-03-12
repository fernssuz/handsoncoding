import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringRestTemplateApplication {
    public static void main(String[] args) {
		/*
        This allows us to add an additional configuration file to the default configuration.
        By using the `spring.config.name` property, we can specify the order of property files to load from the
        `spring.config.location` that we specify on startup (if not specified, it assumes the default).  This
        also respects profiles as well.
         */
        new SpringApplicationBuilder(SpringRestTemplateApplication.class)
                .properties("spring.config.name:application,secrets")
                .build()
                .run(args)
        ;
    }
}
