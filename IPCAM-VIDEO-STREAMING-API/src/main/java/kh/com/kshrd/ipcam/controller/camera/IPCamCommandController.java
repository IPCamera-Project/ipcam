package kh.com.kshrd.ipcam.controller.camera;

import kh.com.kshrd.core.Transformer;
import kh.com.kshrd.core.exceptions.SystemException;
import kh.com.kshrd.core.plugin.DefaultLoader;
import kh.com.kshrd.core.plugin.PluginLoadClass;
import kh.com.kshrd.core.plugin.PluginStateEvent;
import kh.com.kshrd.core.utils.StringUtils;
import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.entity.camera.Model;
import kh.com.kshrd.ipcam.entity.plugin.Plugin;
import kh.com.kshrd.ipcam.entity.user.User;
import kh.com.kshrd.ipcam.service.CameraType.CameraFactory.ICamFactory;
import kh.com.kshrd.ipcam.service.CameraType.CmdInterface;
import kh.com.kshrd.ipcam.service.PluginService;
import kh.com.kshrd.ipcam.service.UserService;
import kh.com.kshrd.ipcam.service.extend.ModelService;
import kh.com.kshrd.ipcam.service.impl.IPCamServiceImpl;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author Chen Rina
 *
 */

@Controller
@RequestMapping("api/camControl")
public class IPCamCommandController {
    Logger log = org.slf4j.LoggerFactory.getLogger(IPCamCommandController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private IPCamServiceImpl ipCamService;

    @Autowired
    private PluginService pluginService;

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/cmd",
                    method = RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public Boolean setCommand(@RequestParam  String email,@RequestParam int camera, @RequestParam String method){
      /*  Integer speed = Integer.parseInt(sp);*/
        User user = userService.getUserByEmail(email);

        IPCam ipCam = ipCamService.getCamOnce(user.getUser_id(),camera);
       // Model = modelService.getModelById(ipCam.getModel())
        System.out.println(ipCam.getModel().getPlugin().getPlugin_name());
//        Plugin plugin = pluginService.findOne(ipCam.getModel().getPlugin_id());
        PluginLoadClass pluginLoadClass = new PluginLoadClass();
        log.debug("This is file name {}", StringUtils.formatFileName(ipCam.getModel().getPlugin().getPlugin_name(),
                ipCam.getModel().getPlugin().getPlugin_version(),
                ipCam.getModel().getPlugin().getPlugin_release()));
        DefaultLoader.getInstance().loadPlugin(StringUtils.formatFileName(ipCam.getModel().getPlugin().getPlugin_name(),
                ipCam.getModel().getPlugin().getPlugin_version(),
                ipCam.getModel().getPlugin().getPlugin_release()), pluginLoadClass);
        Transformer transformer = new Transformer(pluginLoadClass);
        PluginStateEvent pluginStateEvent = transformer.newInstance();
        pluginStateEvent.setConnection(ipCam.getIp_address(), ipCam.getWeb_port(), ipCam.getUsername(), ipCam.getPassword());
        //log.debug("Camera name: {} ", ipCam.getModel().getPlugin_id());
        //log.debug("This is Camera: {} ", pluginService.findOne(ipCam.getModel().getPlugin_id()).getPlugin_name());
        switch (method){
            //case
        }

        return true;

    }

}
