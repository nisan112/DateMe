package reproject.Configurations;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="reproject.Controller")
public class WebConfiguration implements WebMvcConfigurer{
	 @Bean
	    public InternalResourceViewResolver viewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/WEB-INF/views/");  // Change this to your views folder path
	        resolver.setSuffix(".jsp");             // Change this if using another view type like Thymeleaf
	        resolver.setOrder(1);                   // Optional: sets the order in case of multiple resolvers
	        return resolver;
	    }
	 
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        // Serves all static content located in src/main/resources/static/
	        registry.addResourceHandler("/static/**")
	                .addResourceLocations("/static/");
	 }
	 
	 @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        // Add Jackson message converter if not already configured
	        converters.add(new MappingJackson2HttpMessageConverter());
	    }
	        

}
