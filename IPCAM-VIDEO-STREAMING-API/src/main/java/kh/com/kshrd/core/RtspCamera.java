package kh.com.kshrd.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sophatvathana on 15/12/16.
 */
public class RtspCamera extends IpCamera implements Runnable {
    static Logger logger = LoggerFactory.getLogger(RtspCamera.class);

    private RtspStream   rtspStream = new RtspStream();
    private int          rtspPort;
    private String       rtspPath;
    private Thread       rtspThread;
    private boolean      rtspContinue;

    public RtspCamera(String vendor, String model, String host, int webPort, String user, String pass, int rtspPort, String rtspPath,
                      Class cls) {
        this.setVendorName(vendor);
        this.setModelName(model);
        this.setRtspPath(rtspPath);
        this.setRtspPort(rtspPort);
        setHost(host);
        setPort(webPort);
        setUser(user);
        setPass(pass);

        if (cls != null) {
            ICommand p;

            try {
                p = (ICommand) cls.newInstance();
                p.setConnection(host, webPort, user, pass);

                setPtzInterface(p);
            } catch (InstantiationException e) {
                logger.warn(e.getMessage());
            } catch (IllegalAccessException e) {
                logger.warn(e.getMessage());
            }
        }
        else {
            logger.info("{}:{} ptz not set", model, host);
        }
    }

    public RtspStream getRtspStream() {
        return rtspStream;
    }

    public String getRtspPath() {
        return rtspPath;
    }

    public void setRtspPath(String rtspPath) {
        this.rtspPath = rtspPath;
    }

    public int getRtspPort() {
        return rtspPort;
    }

    public void setRtspPort(int rtspPort) {
        this.rtspPort = rtspPort;
    }

    @Override
    public void initialize() {
        rtspThread = new Thread(this);
        rtspThread.start();
    }

    @Override
    public void release() {
        if (rtspContinue) {
            rtspContinue = false;
            try {
                rtspThread.join(10000);
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        }
    }

    @Override
    public void run() {
        rtspContinue = true;
        String url;
        if (getUser().length() > 0 && getPass().length() > 0) {
            url = String.format("rtsp://%s:%s@%s%s%s",
                    getUser(),
                    getPass(),
                    getHost(),
                    getRtspPort() == 554? "" : String.format(":%d", getRtspPort()),
                    getRtspPath());
        }
        else if (getUser().length() > 0) {
            url = String.format("rtsp://%s@%s%s%s",
                    getUser(),
                    getHost(),
                    getRtspPort() == 554? "" : String.format(":%d", getRtspPort()),
                    getRtspPath());
        }
        else {
            url = String.format("rtsp://%s%s%s", getHost(),
                    getRtspPort() == 554? "" : String.format(":%d", getRtspPort()),
                    getRtspPath());
        }

        logger.info("Start: {}", url);
        long cnt = 0;
        while(rtspContinue) {
            try {
                rtspStream.initialize();

                logger.debug("Rtsp {}: start connect", url);
                if (rtspStream.open(url)) {
                    while(rtspContinue) {
                        byte[] packet = rtspStream.read();
                        if (packet == null) {
                            logger.info("Rtsp {}: data receive failed", url);
                            break;
                        }
                        if (cnt++ % 100 == 0)
                            logger.trace("Recv {} {}", url, packet.length);

                        sendPacket(packet);
                    }
                }
                else {
                    logger.info("Rtsp {}: connection failed", url);
                }

                // sleep for next connect
                for(int i=0; rtspContinue && i < 300; i++) {
                    Thread.sleep(10);
                }
            }
            catch (Exception ex) {
                logger.warn(ex.getMessage());
            }
        }

        if (rtspStream != null)
            rtspStream.release();


        rtspContinue = false;
        logger.info("Stop : {}", url);
    }

}
