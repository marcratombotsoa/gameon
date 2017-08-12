package mg.ratombotsoa.gamecollection.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

	@Bean
    ServletRegistrationBean<?> registerH2Servlet(){
        return new ServletRegistrationBean<>(new WebServlet(), "/h2/*");
//        registrationBean.addUrlMappings("/h2/*");
//        return registrationBean;
    }
}
