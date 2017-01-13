package kh.com.kshrd.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by sophatvathana on 15/12/16.
 */
public class RtspStream extends ExternalBase {
    static Logger logger = LoggerFactory.getLogger(RtspStream.class);
    private long handler = 0;

    static {
        String path = getNativePath();
        System.out.println(path);
        logger.info(System.getProperty("java.library.path"));
        logger.info(path);
        if (getOSType().contains("windows"))
        	loadNativeLibrary(path, "strmrecv.so.dll");
        else if(getOSType().contains("mac"))
            loadNativeLibrary(path, "strmrecv.dylib");
        else
            loadNativeLibrary(path, "strmrecv.so");

    }

    public void setHandler(long handler){
        this.handler = handler;
    }

    private native long    createContextInternal(String home);
    private native boolean openRtspInternal(long handler, String url);
    private native byte[]  readFrameInternal(long handler, String url);
    private native void    releaseContextInternal(long handler);

    static private String getNativePath() {
        String homeFolder = new File(System.getProperty("user.dir")).getAbsolutePath();
        System.out.print(homeFolder);
        String arch = System.getProperty("os.arch");
        String path = String.format("%s%snative%s%s%s%s%s", homeFolder, File.separator, File.separator, getOSType(), File.separator, arch, File.separator);
        return path;
    }

    public void RtspSteam() {

    }

    public void initialize() {
        release();
        System.out.println(getNativePath());
        handler = createContextInternal(getNativePath());
    }

    public void release() {
        if (handler != 0)
            releaseContextInternal(handler);

        handler = 0;
    }

    public boolean open(String url) {
        logger.info("RTSP: {}", url);
        if (handler != 0)
            return openRtspInternal(handler, url);

        return false;
    }

    public byte[] read(String url)
    {
        if (handler != 0)
            return readFrameInternal(handler, url);

        return null;
    }

}
