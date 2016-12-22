package kh.com.kshrd.core;

/**
 * Created by sophatvathana on 15/12/16.
 */
public class HikVisionTest extends RtspCamera {
    public HikVisionTest(String model, String host, int webPort, String user, String pass, int rtspPort, int streamId,Boolean ptz) {
        super("HikVision", model, host, webPort, user, pass, rtspPort, String.format("/Streaming/Channels/10%d", streamId + 1), ptz ? HikVisionCommandTest.class : null);
    }
}
