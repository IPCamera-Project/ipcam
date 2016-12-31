package kh.com.kshrd.ipcam.base;

import kh.com.kshrd.core.RtspCamera;
import kh.com.kshrd.ipcam.controller.camera.BluePrintCamera;

/**
 * Created by sophatvathana on 22/12/16.
 */
public class BaseStream extends RtspCamera {
    public BaseStream(String vendor, String model, String host,
                      String user, String pass, int rtspPort, String rtspPath, long client){
        super(vendor, model, host, user, pass, rtspPort, rtspPath, BluePrintCamera.class, client);

    }
}
