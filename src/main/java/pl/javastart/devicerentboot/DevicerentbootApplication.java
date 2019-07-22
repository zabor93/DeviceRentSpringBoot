package pl.javastart.devicerentboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.javastart.devicerentboot.app.AppController;

import java.util.Scanner;

@SpringBootApplication
public class DevicerentbootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DevicerentbootApplication.class, args);

        AppController appController = ctx.getBean(AppController.class);
        appController.mainLoop();

    }

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }

}

