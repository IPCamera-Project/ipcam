package kh.com.kshrd.ipcam.service.extend;

import kh.com.kshrd.ipcam.entity.camera.Vender;
import kh.com.kshrd.ipcam.entity.form.VenderInputer;
import kh.com.kshrd.ipcam.service.CrudService;

import java.util.ArrayList;

/**
 * TODO: if you want to add more method, then add here... 
 */
public interface VenderService{
     ArrayList<Vender> getAllVender();
     Vender getVenderById(int vender_id);
     boolean addNewVender(VenderInputer venderInputer);
     boolean updateVender(Vender vender);
     boolean removeVender(int vender_id);
}
