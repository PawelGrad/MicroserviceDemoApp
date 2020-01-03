package photoGallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("photoGallery")
public class PhotoApp {

    public static void main(String[] args) {
        SpringApplication.run(PhotoApp.class, args);
    }

}
