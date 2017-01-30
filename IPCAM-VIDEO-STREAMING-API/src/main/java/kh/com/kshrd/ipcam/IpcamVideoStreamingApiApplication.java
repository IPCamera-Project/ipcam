package kh.com.kshrd.ipcam;
import kh.com.kshrd.ipcam.configuration.swagger.MybatisConfiguration;
import kh.com.kshrd.ipcam.configuration.swagger.WebMvcConfiguration;
import kh.com.kshrd.ipcam.controller.camera.IPCamCommandController;
import kh.com.kshrd.ipcam.entity.camera.IPCam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
//@PropertySource("classpath:application.properties")
@EnableWebMvc
//@ComponentScan({"kh.com.kshrd.ipcam.configuration.swagger","kh.com.kshrd.ipcam.controller"})
//@EntityScan(value = "kh.com.kshrd.ipcam.entity")
public class IpcamVideoStreamingApiApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(IpcamVideoStreamingApiApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(IpcamVideoStreamingApiApplication.class);
	}
}
