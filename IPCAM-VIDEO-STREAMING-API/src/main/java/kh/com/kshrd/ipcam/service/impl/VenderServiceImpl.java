package kh.com.kshrd.ipcam.service.impl;

import kh.com.kshrd.ipcam.entity.camera.Vender;
import kh.com.kshrd.ipcam.entity.form.VenderInputer;
import kh.com.kshrd.ipcam.repository.VenderRepository;
import kh.com.kshrd.ipcam.service.extend.VenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by ppsc08 on 05-Jan-17.
 */
@Service
public class VenderServiceImpl implements VenderService {

    @Autowired
    VenderRepository venderRepository;

    @Override
    public ArrayList<Vender> getAllVender() {
        return venderRepository.getAllVendor();
    }

    @Override
    public Vender getVenderById(int vender_id) {
        return venderRepository.getVenderById(vender_id);
    }

    @Override
    public boolean addNewVender(VenderInputer venderInputer) {
        return venderRepository.addNewVender(venderInputer);
    }

    @Override
    public boolean updateVender(Vender vender) {
        return venderRepository.updateVender(vender);
    }

    @Override
    public boolean removeVender(int vender_id) {
        return venderRepository.removeVender(vender_id);
    }
}
