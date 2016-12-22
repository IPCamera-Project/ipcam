package kh.com.kshrd.core;

import javax.sql.rowset.spi.SyncResolver;
import java.util.Map;

/**
 * Created by sophatvathana on 15/12/16.
 */
public class FactoryTest {
    Map<String, IpCamera> cameras = null;

    public void setCameras(Map<String, IpCamera> cameraMap){
        this.cameras = cameraMap;
    }

    public IpCamera getCameraMap(String id){
        if (this.cameras.containsKey(id))
            return this.cameras.get(id);

        return null;
    }
}
