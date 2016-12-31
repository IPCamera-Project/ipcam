package kh.com.kshrd.ipcam.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by sophatvathana on 21/12/16.
 */
@EnableAutoConfiguration
@EnableWebMvc
//@ComponentScan({"kh.com.kshrd.ipcam"})
@ComponentScan({"kh.com.kshrd.ipcam.configuration.swagger","kh.com.kshrd.ipcam.controller"})
@EntityScan(value = "kh.com.kshrd.ipcam.entity")
class SpringInitializer extends SpringBootServletInitializer{

}