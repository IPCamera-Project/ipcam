package kh.com.kshrd.ipcam.service.impl;

import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.entity.form.IPCameraInputer;
import kh.com.kshrd.ipcam.entity.form.IPCameraModifier;
import kh.com.kshrd.ipcam.repository.IPCamRepository;
import kh.com.kshrd.ipcam.service.extend.IPCamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rina on 12/20/16.
 */
@Service
public class IPCamServiceImpl implements IPCamService {

    @Autowired
    IPCamRepository ipCamRepository;

    @Override
    public IPCam findOne(int id) {
        return ipCamRepository.findOne(id);
    }

    @Override
    public List<IPCam> findAll() {
        return ipCamRepository.findAll();
    }

    @Override
    public List<IPCam> findCameraByUserId(int user_id) {
        return ipCamRepository.getCamByUserId(user_id);
    }

    @Override
    public boolean remove(int id) {
        return ipCamRepository.remove(id);
    }

    @Override
    public boolean update(IPCameraModifier object) {
        return ipCamRepository.updateCamera(object);
    }

    @Override
    public boolean save(IPCameraInputer object) {
        return ipCamRepository.insertCamera(object);
    }

    @Override
    public IPCam getCamOnce(int userId, int camId) {
        return ipCamRepository.getCamOnce(userId, camId);
    }
}
