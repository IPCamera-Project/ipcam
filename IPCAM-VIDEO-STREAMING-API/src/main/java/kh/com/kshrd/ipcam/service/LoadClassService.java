package kh.com.kshrd.ipcam.service;

import kh.com.kshrd.core.Transformer;
import kh.com.kshrd.core.plugin.DefaultLoader;
import kh.com.kshrd.core.plugin.PluginLoadClass;
import kh.com.kshrd.core.plugin.PluginStateEvent;
import kh.com.kshrd.core.utils.StringUtils;
import kh.com.kshrd.core.utils.Tuple;
import kh.com.kshrd.ipcam.controller.camera.IPCamCommandController;
import kh.com.kshrd.ipcam.emun.ControlEnum;
import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.entity.user.User;
import kh.com.kshrd.ipcam.service.extend.ModelService;
import kh.com.kshrd.ipcam.service.impl.IPCamServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sophatvathana on 22/12/16.
 */
@Service
public class LoadClassService {
    Logger log = org.slf4j.LoggerFactory.getLogger(IPCamCommandController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private IPCamServiceImpl ipCamService;

    public Tuple<PluginStateEvent, IPCam> loadClassService(String email, int camera) {
      /*  Integer speed = Integer.parseInt(sp);*/

        User user = userService.getUserByEmail(email);

        IPCam ipCam = ipCamService.getCamOnce(user.getUser_id(), camera);
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
        pluginStateEvent.setConnection(ipCam.getIp_address(), ipCam.getWeb_port(), ipCam.getRtsp_port(), ipCam.getUsername(), ipCam.getPassword());
        //log.debug("Camera name: {} ", ipCam.getModel().getPlugin_id());
        //log.debug("This is Camera: {} ", pluginService.findOne(ipCam.getModel().getPlugin_id()).getPlugin_name());
        return new Tuple<>(pluginStateEvent, ipCam);
    }
}
