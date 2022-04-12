package pl.piekoszek.gorskimatches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.piekoszek.gorskimatches.image.CreateImage;

import java.awt.*;
import java.io.IOException;


//@SpringBootApplication
public class GorskiMatchesApplication {

	public static void main(String[] args) throws IOException {
		var createImage = new CreateImage();
		createImage.create();
//		SpringApplication.run(GorskiMatchesApplication.class, args);
	}

}



