package io.gongson.g_back.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class ResourceConfiguration implements WebMvcConfigurer {

    @Value("${app.file.saveDir}")
    private String saveDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:///"+saveDir)
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES));
    }
}
