package nl.ckramer.webshop.service;

import org.springframework.stereotype.Service;

@Service
public class HelloSpringService {

	public String sayHello() {
		return "hello from spring service";
	}
}
