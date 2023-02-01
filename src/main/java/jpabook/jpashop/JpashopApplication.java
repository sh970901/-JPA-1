package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}
	@Bean
	Hibernate5JakartaModule hibernate5JakartaModule(){
//		hibernate5JakartaModule().configure(Hibernate5JakartaModule.Feature.FORCE_LAZY_LOADING, true);
		return new Hibernate5JakartaModule();
		//json 라이브러리는 기본적으로 프록시 객체를 json으로 어떻게 생성해야하는지 모른다. 이를 등록하면 사용가능
	}
}
