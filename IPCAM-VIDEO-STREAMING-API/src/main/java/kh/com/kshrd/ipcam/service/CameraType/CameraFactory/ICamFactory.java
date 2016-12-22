package kh.com.kshrd.ipcam.service.CameraType.CameraFactory;

import kh.com.kshrd.ipcam.service.CameraType.CmdInterface;

/**
 * Created by rina on 12/18/16.
 */
public class ICamFactory {
    /**
     *@ADD if you have more CAM type you must add new Object type here
     * @return camera type
     */
    public CmdInterface getICam(String iCamClass){
        CmdInterface cmdInterface =null;
        try {
            cmdInterface = (CmdInterface) Class.forName("kh.com.kshrd.ipcam.service.CameraType.Impl."+iCamClass).newInstance();
        }
        catch (Exception e){
            System.out.print(e);
        }
        return cmdInterface;
    }

}
