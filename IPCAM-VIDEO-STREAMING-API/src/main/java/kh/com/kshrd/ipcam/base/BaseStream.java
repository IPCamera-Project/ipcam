package kh.com.kshrd.ipcam.base;

import kh.com.kshrd.core.RtspCamera;
import kh.com.kshrd.core.RtspStream;

/**
 * Created by sophatvathana on 22/12/16.
 */
public class BaseStream extends RtspCamera {
    public BaseStream(String vendor, String model, String host, int webPort,
                      String user, String pass, int rtspPort, String rtspPath){
        super(vendor, model, host, webPort, user, pass, rtspPort, rtspPath);

    }
}
