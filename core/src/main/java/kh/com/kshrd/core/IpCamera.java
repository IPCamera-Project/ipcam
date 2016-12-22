package kh.com.kshrd.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sophatvathana on 15/12/16.
 */
public abstract class IpCamera {
    static Logger logger = LoggerFactory.getLogger(IpCamera.class);

    private ICommand ptzInterface;
    private String       vendorName, modelName;
    private String       host, user, pass;
    private int          port;
    private List<StreamConsumer> consumers = new java.util.ArrayList<StreamConsumer>();
    private Object       locker = new Object();
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getModelName() {
        return modelName;
    }

    protected void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getUser() {
        return user;
    }

    protected void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    protected void setPass(String pass) {
        this.pass = pass;
    }

    public ICommand getPtzInterface() {
        return ptzInterface;
    }

    protected void setPtzInterface(ICommand ptzInterface) {
        this.ptzInterface = ptzInterface;
    }

    public int getPort() {
        return port;
    }

    protected void setPort(int port) {
        this.port = port;
    }

    public void addStreamConsumer(StreamConsumer cons) {
        synchronized(locker) {
            consumers.add(cons);
        }
    }

    public void delStreamConsumer(StreamConsumer cons) {
        synchronized(locker) {
            consumers.remove(cons);
        }
    }

    public void sendPacket(byte[] packet) {
        boolean needCheck = false;
        synchronized(locker) {
            for(StreamConsumer c: consumers) {
                try {
                    c.addPacket(packet);
                } catch (Exception e) {
                    logger.info(e.getMessage());
                    needCheck = true;
                }
            }
        }

        if (needCheck)
            checkConsumer();
    }

    public void checkConsumer() {
        LinkedList<StreamConsumer> list = new LinkedList<StreamConsumer>();
        synchronized(locker) {
            for(StreamConsumer c: consumers) {
                try {
                    if (!c.getContinue())
                        list.add(c);
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            }
        }

        for(StreamConsumer c : list) {
            delStreamConsumer(c);
        }
    }

    public abstract void initialize();
    public abstract void release();

    public String getVendorName() {
        return vendorName;
    }

    protected void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
