package at.customer;


import at.customer.entities.Customer;
import at.customer.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {

        return args -> {
            createMockCustomer("Vorname", "Nachname", repository);
            log.info("Creating finished");
        };
    }

    private void createMockCustomer(String vorname, String nachname, CustomerRepository repository ) {
        Customer customer = new Customer(vorname, nachname);
//        log.info("Preloading " + repository.save(customer));
        repository.save(customer);
    }


    public static BigDecimal random(int range) {
        BigDecimal max = new BigDecimal(range);
        BigDecimal randFromDouble = BigDecimal.valueOf(Math.random());
        BigDecimal actualRandomDec = randFromDouble.multiply(max);
        actualRandomDec = actualRandomDec.setScale(2, RoundingMode.DOWN);
        return actualRandomDec;
    }
}
