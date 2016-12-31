package kh.com.kshrd.ipcam.service.CameraType;

/**
 * Created by rina on 12/18/16.
 */
public interface CmdInterface {
    boolean left(String left);
    boolean right(String right);
    boolean up(String up);
    boolean down(String down);
    boolean zoomIn(String zoomIn);
    boolean zoomOut(String zoomOut);
    boolean stop();

    boolean executeCommand(String msg,String command);
    void setConnection(String host, int port, String user,String pass);

}
