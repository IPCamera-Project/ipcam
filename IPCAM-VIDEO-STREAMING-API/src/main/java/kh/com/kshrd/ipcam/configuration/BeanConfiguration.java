package kh.com.kshrd.ipcam.configuration;

import kh.com.kshrd.core.RtspCamera;
import kh.com.kshrd.core.RtspStream;
import kh.com.kshrd.core.plugin.PluginStateEvent;
import kh.com.kshrd.ipcam.base.BaseStream;
import kh.com.kshrd.ipcam.entity.plugin.Plugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sophatvathana on 23/12/16.
 */

@Configuration
public class BeanConfiguration {
    private String vendor;
    private String model;
    private String host;
    private int webPort;
    private String user;
    private String pass;
    private int rtspPort;
    private String rtspPath;
    private Class cls;

    public void setData(String vendor, String model, String host, int webPort, String user, String pass, int rtspPort, String rtspPath, Class cls){
        this.vendor = vendor;
        this.model = model;
        this.host = host;
        this.webPort = webPort;
        this.user = user;
        this.pass = pass;
        this.rtspPath = rtspPath;
        this.rtspPort = rtspPort;
        this.cls = cls;
    }

    @Bean
    public BaseStream camera(){
        BaseStream cam = new BaseStream(this.vendor, this.model, this.host, this.user, this.pass, this.rtspPort, this.rtspPath,0);
        return cam;
    }

    @PreDestroy
    public void destroy(){
        camera().release();
    }

    //@PostConstruct
    public void init(){
        camera().initialize();
    }
}
