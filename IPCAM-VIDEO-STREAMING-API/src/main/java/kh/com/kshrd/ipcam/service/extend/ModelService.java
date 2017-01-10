package kh.com.kshrd.ipcam.service.extend;

import kh.com.kshrd.ipcam.entity.camera.Model;
import kh.com.kshrd.ipcam.entity.form.ModelInputer;
import kh.com.kshrd.ipcam.entity.form.ModelModifier;
import kh.com.kshrd.ipcam.entity.plugin.Plugin;
import kh.com.kshrd.ipcam.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: if you want to add more method, then add here... 
 */
@Service
public interface ModelService {

	Model getModelById(int id);

	ArrayList<String> getAllModelName(int vender_id);

	List<Model> getAllModel();

	boolean remove(int id);

	boolean update(ModelModifier object);

	boolean save(ModelInputer object);

}
