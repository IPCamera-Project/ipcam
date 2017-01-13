package kh.com.kshrd.ipcam.service.extend;

import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.entity.form.IPCameraInputer;
import kh.com.kshrd.ipcam.entity.form.IPCameraModifier;
import kh.com.kshrd.ipcam.service.CrudService;

import java.util.List;

/**
 * Created by rina on 12/20/16.
 */

public interface IPCamService {

    IPCam findOne(int id);

    List<IPCam> findAll();

    List<IPCam> findCameraByUserId(int user_id);

    boolean remove(int id);

    boolean update(IPCameraModifier object);

    boolean save(IPCameraInputer object);

    IPCam getCamOnce(int userId, int camId);

}
