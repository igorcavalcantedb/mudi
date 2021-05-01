package br.com.alura.mvc.mudi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.alura.mvc.mudi.interceptor.InterceptorAcesso;
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	
	@Bean
	public InterceptorAcesso interceptorAcesso() {
	    return new InterceptorAcesso();
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorAcesso());
	}
	

}
