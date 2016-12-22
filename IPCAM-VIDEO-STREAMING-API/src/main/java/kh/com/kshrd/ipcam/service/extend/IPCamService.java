package kh.com.kshrd.ipcam.service.extend;

import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.service.CrudService;

import java.util.List;

/**
 * Created by rina on 12/20/16.
 */

public interface IPCamService extends CrudService<IPCam> {
    @Override
    IPCam findOne(int id);

    @Override
    List<IPCam> findAll();

    @Override
    boolean remove(int id);

    @Override
    boolean update(IPCam object);

    @Override
    boolean save(IPCam object);
}
