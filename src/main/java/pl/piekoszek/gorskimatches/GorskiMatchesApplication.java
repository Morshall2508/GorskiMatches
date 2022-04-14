package pl.piekoszek.gorskimatches;

import pl.piekoszek.gorskimatches.image.LineImageCreator;

import java.io.IOException;


//@SpringBootApplication
public class GorskiMatchesApplication {

	public static void main(String[] args) throws IOException {
		var createImage = new LineImageCreator();
		createImage.create();
//		SpringApplication.run(GorskiMatchesApplication.class, args);
	}

}



