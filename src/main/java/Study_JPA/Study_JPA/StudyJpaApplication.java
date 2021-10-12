package Study_JPA.Study_JPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyJpaApplication {
	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("hello");
		String data = hello.getData();

		System.out.print("Data = " + data);

		SpringApplication.run(StudyJpaApplication.class, args);
	}

}
