package kh.com.kshrd.ipcam.service;

import kh.com.kshrd.ipcam.entity.plugin.Plugin;
import kh.com.kshrd.ipcam.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * Created by sophatvathana on 21/12/16.
 */

@Service
public interface PluginService extends CrudService<Plugin> {
    @Override
    Plugin findOne(int id);

    @Override
    boolean save(Plugin object);

}
