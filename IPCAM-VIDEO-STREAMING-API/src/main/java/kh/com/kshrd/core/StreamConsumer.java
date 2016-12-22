package kh.com.kshrd.core;

/**
 * Created by sophatvathana on 15/12/16.
 */
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamConsumer {
    static Logger logger = LoggerFactory.getLogger(StreamConsumer.class);

    LinkedBlockingQueue<byte[]> packetQueue = new LinkedBlockingQueue<byte[]>();
    boolean                     isContinue = true;
    OutputStream os;

    public StreamConsumer(OutputStream os) {
        this.os = os;
    }
    public int getQueueCount() {
        return packetQueue.size();
    }

    public void addPacket(byte[] packet) {
        try {
            //os.write(packet);
            packetQueue.put(packet);
        } catch (Exception e) {
            setContinue(false);
            logger.info(e.getMessage());
        }
    }

    public void setContinue(boolean val) {
        isContinue = val;
    }

    public boolean getContinue() {
        return isContinue;
    }

    public boolean exist() {
        return  isContinue && !packetQueue.isEmpty();
    }

    public byte[] take() {

        byte[] rslt = null;

        try {
            return packetQueue.take();
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }

        return rslt;
    }
}
