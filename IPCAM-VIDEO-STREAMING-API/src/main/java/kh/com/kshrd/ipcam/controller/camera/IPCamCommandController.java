package kh.com.kshrd.ipcam.controller.camera;

import kh.com.kshrd.core.StreamConsumer;
import kh.com.kshrd.core.plugin.PluginStateEvent;
import kh.com.kshrd.core.utils.Tuple;
import kh.com.kshrd.ipcam.base.BaseStream;
import kh.com.kshrd.ipcam.configuration.BeanConfiguration;
import kh.com.kshrd.ipcam.emun.ControlEnum;
import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.service.LoadClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 
 * @author Chen Rina, sophatvathana
 *
 */

@Controller
@RequestMapping("api/camera")
public class IPCamCommandController {

    Logger logger = LoggerFactory.getLogger(IPCamCommandController.class);

    @Autowired
    private LoadClassService loadClassService;

    private Boolean t;

    private PluginStateEvent pluginStateEvent;

    @Autowired
    private BeanConfiguration beanConfiguration;

    @RequestMapping(value = "/cmd",
                    method = RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public Boolean setCommand(@RequestParam  String email,@RequestParam int camera, @RequestParam ControlEnum method){
      /*  Integer speed = Integer.parseInt(sp);*/
        pluginStateEvent = loadClassService.loadClassService(email,camera)._1;
        switch (method){
            case left:
                t = pluginStateEvent.left();
                break;
            case right:
                t = pluginStateEvent.right();
                break;
            case up:
                t = pluginStateEvent.up();
                break;
            case stop:
                t =pluginStateEvent.stop();
                break;
            case down:
                t = pluginStateEvent.down();
                break;
            case zoomin:
                t = pluginStateEvent.zoomIn();
                break;
            case zoomout:
                t = pluginStateEvent.zoomOut();
                break;
            default:
                break;
        }

        return t;

    }

    @RequestMapping(value = "/stream", method = RequestMethod.GET)
    public void stream(@RequestParam  String email,@RequestParam int camera, HttpServletResponse response, HttpServletRequest request)  {
        logger.debug("\nRES:{}", response);
        Tuple loadClass = loadClassService.loadClassService(email,camera);
        pluginStateEvent = (PluginStateEvent) loadClass._1;
        IPCam ipCam = (IPCam) loadClass._2;
        //System.out.println(pluginStateEvent.getClass().getName());
//        beanConfiguration.setData(ipCam.getModel().getVender().getName(),
//                ipCam.getModel().getName(), ipCam.getIp_address(),
//                ipCam.getWeb_port(), ipCam.getUsername(), ipCam.getPassword(), ipCam.getRtsp_port(),
//                pluginStateEvent.getRtsp(),
//                pluginStateEvent.getClass()
//        );

        logger.debug("Get RTSP: ",pluginStateEvent.getRtsp());
        do {
              //beanConfiguration.init();
//            RtspCamera cam = beanConfiguration.camera();
//            beanConfiguration.init();
            BaseStream  cam = new BaseStream(ipCam.getModel().getVender().getName(),
                    ipCam.getModel().getName(),
                    ipCam.getIp_address(),
                    ipCam.getUsername(),
                    ipCam.getPassword(),
                    ipCam.getRtsp_port(),
                    pluginStateEvent.getRtsp(),
                    ipCam.getCamera_id()
            );
            cam.initialize();

                System.out.println(ipCam.getModel().getVender().getName());
                System.out.println(ipCam.getModel().getName());
                System.out.println(ipCam.getIp_address());
                System.out.println(pluginStateEvent.getRtsp());
//            BaseStream cam = new BaseStream("hikvision", "DS-2CD2Q10FD-IW", "192.168.0.29", 80, "admin", "12345", 554, "/Streaming/Channels/102");
//                    System.out.println(cam.getRtspPath());
            if (cam == null) {
                logger.info("stream command: {} was failed: object not found", camera);
                break;
            }
            try {
                OutputStream os = response.getOutputStream();

                logger.debug("Start Consumer");
                response.addHeader("Cache-Control", "no-cache");
                response.setContentType("multipart/x-mixed-replace;boundary=boundary");
                StreamConsumer cons = new StreamConsumer(os);
                cam.addStreamConsumer(cons);
                long count = 0, sendSize = 0;
                while(cons.getContinue()) {
                    if (!cons.exist()) {
                        Thread.sleep(30);
                        continue;
                    }
                    byte[] packet = cons.take();
                    String head = String.format("--boundary\r\nContent-Type: image/jpeg\r\nContent-Length: %d\r\n\r\n", packet.length);
                    os.write(head.getBytes("UTF-8"));
                    os.write(packet);
                    os.flush();
                    sendSize += packet.length;
                    count++;
                    if (count % 100 == 0) {
                        logger.info("{} stream sending: {}/{}", cam.getModelName(), count, sendSize);
                       // cam.release();
                    }
                   // cam.release();
                }

            } catch (Exception e) {
                logger.info(e.getMessage());
            }

        } while(false);
    }

//    @PreDestroy
//    public void destroy(){
//        cam.release();
//    }

//    @PostConstruct
//    public void init(){
//        cam.initialize();
//    }

}
