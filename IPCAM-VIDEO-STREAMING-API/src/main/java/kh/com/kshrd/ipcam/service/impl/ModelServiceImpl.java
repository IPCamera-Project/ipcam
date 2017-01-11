package kh.com.kshrd.ipcam.service.impl;

import java.util.ArrayList;
import java.util.List;

import kh.com.kshrd.ipcam.entity.form.ModelInputer;
import kh.com.kshrd.ipcam.entity.form.ModelModifier;
import kh.com.kshrd.ipcam.entity.plugin.Plugin;
import kh.com.kshrd.ipcam.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.com.kshrd.ipcam.entity.camera.Model;
import kh.com.kshrd.ipcam.service.extend.ModelService;

import java.util.List;

/**
 * Created by rina on 12/21/16.
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepository modelRepository;

    @Override
    public ArrayList<String> getAllModelName(String vender_name){
      return modelRepository.getAllModelName(vender_name);
    }

    @Override
    public Model getModelById(int id) {
        return modelRepository.findOne(id);
    }

    @Override
    public List<Model> getAllModel() {
        return modelRepository.getAllModel();
    }

    @Override
    public boolean remove(int id) {
        return modelRepository.removeModle(id);
    }

    @Override
    public boolean update(ModelModifier object) {
        return update(object);
    }

    @Override
    public boolean save(ModelInputer object) {
        return modelRepository.addModel(object);
    }
}