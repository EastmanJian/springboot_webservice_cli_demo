package hello;

import hello.wsdl.Country;
import hello.wsdl.GetCountryResponse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    //The main() method defers to the SpringApplication helper class, providing QuoteConfiguration.class as an argument
    // to its run() method. This tells Spring to read the annotation metadata from QuoteConfiguration and to manage it
    // as a component in the Spring application context.
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient countryClient) {
        return args -> {
            String countryName = "Poland";

            if (args.length > 0) {
                countryName = args[0];
            }
            GetCountryResponse response = countryClient.getCountry(countryName);
            Country country = response.getCountry();
            System.out.println("Country Info: Country Name=" + country.getName() +
                    ", Population=" + country.getPopulation() +
                    ", Currency=" + country.getCurrency() +
                    ", Capital=" + country.getCapital());
        };
    }
}
