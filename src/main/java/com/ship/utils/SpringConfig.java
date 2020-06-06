package com.ship.utils;


import com.ship.helpers.ShipHelper;
import com.ship.service.ShipService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@Configuration
@EnableWebMvc
@ComponentScan("com.ship")
//@PropertySource("user.properties")
public class SpringConfig {

    @Bean
    public SessionFactory getSessionFactory(){
        return HibernateUtils.getSessionFactory();
    }
    @Bean
    public ShipService getShipService(){
        return new ShipService(getSessionFactory(), getShipHelper());
    }
    @Bean
    public ShipHelper getShipHelper(){
        return new ShipHelper();
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
