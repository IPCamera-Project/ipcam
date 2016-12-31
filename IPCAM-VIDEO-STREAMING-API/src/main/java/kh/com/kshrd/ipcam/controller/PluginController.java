package kh.com.kshrd.ipcam.controller;

import kh.com.kshrd.ipcam.entity.plugin.Plugin;
import kh.com.kshrd.ipcam.respone.*;
import kh.com.kshrd.ipcam.service.impl.PluginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sophatvathana on 21/12/16.
 */
@RestController
@RequestMapping("api/plugin")
public class PluginController {
    @Autowired
    private PluginServiceImpl pluginService;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseObject<Plugin> getPluginByID(@PathVariable("id") int id)
    {
        Plugin data= pluginService.findOne(id);
        ResponseObject<Plugin> res = new ResponseObject<Plugin>();

        if(data != null){
            res.setCode(ResponseCode.QUERY_FOUND);
            res.setMessage(ResponseMessage.PLUGIN_MESSAGE);
            res.setData(data);
        }
        else{
            res.setCode(ResponseCode.QUERY_NOT_FOUND);
            res.setMessage(ResponseMessage.PLUGIN_MESSAGE);
        }
        return res;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public Response insertPlugin(@RequestBody Plugin plugin)
    {
        Response res = new Response();
        if(pluginService.save(plugin)){
            res.setCode(ResponseCode.INSERT_SUCCESS);
            res.setMessage(ResponseMessage.PLUGIN_MESSAGE);
        }
        else{
            res.setCode(ResponseCode.INSERT_FAIL);
            res.setMessage(ResponseMessage.PLUGIN_MESSAGE);
        }

        return res;

    }


}
