package kh.com.kshrd.ipcam.service.extend;

import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.service.CrudService;

import java.util.List;

/**
 * Created by rina on 12/20/16.
 */

public interface IPCamService {

    IPCam findOne(int id);

    List<IPCam> findAll();

    boolean remove(int id);

    boolean update(IPCam object);

    boolean save(IPCam object);

    IPCam getCamOnce(int userId, int camId);

}
