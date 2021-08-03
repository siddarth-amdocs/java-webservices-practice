package com.animasugit.rest.webservices.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
	public LocaleResolver localeResolver(){
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
    
    @Bean
    public Docket api() {
       return new Docket(DocumentationType.SWAGGER_2);
    }


    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     registry
    //         .addResourceHandler("swagger-ui.html")
    //         .addResourceLocations("classpath:/META-INF/resources/");
    //     registry
    //         .addResourceHandler("/webjars/**")
    //         .addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }
}