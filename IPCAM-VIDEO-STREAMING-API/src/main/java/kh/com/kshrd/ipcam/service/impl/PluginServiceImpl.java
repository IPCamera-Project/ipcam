package kh.com.kshrd.ipcam.service.impl;

import kh.com.kshrd.ipcam.entity.plugin.Plugin;
import kh.com.kshrd.ipcam.repository.PluginRepository;
import kh.com.kshrd.ipcam.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sophatvathana on 21/12/16.
 */
@Service
public class PluginServiceImpl implements PluginService {

    @Autowired
    private PluginRepository pluginRepository;

    @Override
    public Plugin findOne(int id) {
        return pluginRepository.getPluginById(id);
    }

    @Override
    public List<Plugin> findAll() {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean update(Plugin object) {
        return false;
    }

    @Override
    public boolean save(Plugin object) {
        return pluginRepository.insertPlugin(object);
    }
}
