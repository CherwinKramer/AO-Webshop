package nl.ckramer.webshop.util;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

public class AutowireHelper {

	public static void autowire(Object bean) {
		AutowireCapableBeanFactory autowireCapableBeanFactory = ApplicationContextProvider.getApplicationContext()
				.getAutowireCapableBeanFactory();
		autowireCapableBeanFactory.autowireBean(bean);
	}

	public static <T> T autowire(Class<T> requiredType) {
		AutowireCapableBeanFactory autowireCapableBeanFactory = ApplicationContextProvider.getApplicationContext()
				.getAutowireCapableBeanFactory();
		return autowireCapableBeanFactory.getBean(requiredType);
	}
}