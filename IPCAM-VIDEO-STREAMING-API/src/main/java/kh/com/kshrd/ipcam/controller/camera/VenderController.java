package kh.com.kshrd.ipcam.controller.camera;

import kh.com.kshrd.ipcam.entity.camera.Vender;
import kh.com.kshrd.ipcam.entity.form.VenderInputer;
import kh.com.kshrd.ipcam.respone.Response;
import kh.com.kshrd.ipcam.respone.ResponseCode;
import kh.com.kshrd.ipcam.respone.ResponseList;
import kh.com.kshrd.ipcam.respone.ResponseMessage;
import kh.com.kshrd.ipcam.respone.ResponseObject;
import kh.com.kshrd.ipcam.service.impl.VenderServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ppsc08 on 05-Jan-17.
 */

@RestController
@RequestMapping("api/vender")
public class VenderController {
    @Autowired
    VenderServiceImpl venderService;

    @GetMapping("/getAllVender")
    ResponseList<Vender> getAlLVender(){
        ResponseList<Vender> response = new ResponseList<Vender>();
        if(venderService.getAllVender()!=null){
            response.setCode(ResponseCode.QUERY_FOUND);
            response.setMessage(ResponseMessage.VENDER_MESSAGE);
            response.setData(venderService.getAllVender());
        }
        else {
            response.setCode(ResponseCode.QUERY_NOT_FOUND);
            response.setMessage(ResponseMessage.VENDER_MESSAGE);
        }
        return response;
    }

    @GetMapping("/getVenderById")
    ResponseObject<Vender> getVenderById(@RequestParam("VENDER_ID")int vender_id){
        ResponseObject<Vender> response = new ResponseObject<Vender>();

        if(venderService.getVenderById(vender_id)!=null){
            response.setCode(ResponseCode.QUERY_FOUND);
            response.setMessage(ResponseMessage.VENDER_MESSAGE);
            response.setData(venderService.getVenderById(vender_id));
        }
        else {
            response.setCode(ResponseCode.QUERY_NOT_FOUND);
            response.setMessage(ResponseMessage.VENDER_MESSAGE);
        }
        return response;
    }

    @PostMapping("/addNewVender")
    Response addNewVender(@RequestBody VenderInputer venderInputer){
        Response response = new Response() ;
            if(venderService.addNewVender(venderInputer)){
                response.setCode(ResponseCode.INSERT_SUCCESS);
                response.setMessage(ResponseMessage.VENDER_MESSAGE);
            }
            else {
                response.setMessage(ResponseMessage.VENDER_MESSAGE);
                response.setCode(ResponseCode.INSERT_FAIL);
            }
            return response;
    }

    @PutMapping("/updateVender")
    Response updateVender(@RequestBody Vender vender){
        Response response = new Response() ;
        if(venderService.updateVender(vender)){
            response.setCode(ResponseCode.UPDATE_SUCCESS);
            response.setMessage(ResponseMessage.VENDER_MESSAGE);
        }
        else {
            response.setMessage(ResponseMessage.VENDER_MESSAGE);
            response.setCode(ResponseCode.UPDATE_FAIL);
        }
        return response;
    }

    @DeleteMapping("/removeVender")
    Response removeVender(@RequestParam("VENDER_ID") int vender_id){
        Response response = new Response() ;
        if(venderService.removeVender(vender_id)){
            response.setCode(ResponseCode.DELETE_SUCCESS);
            response.setMessage(ResponseMessage.VENDER_MESSAGE);
        }
        else {
            response.setMessage(ResponseMessage.VENDER_MESSAGE);
            response.setCode(ResponseCode.DELETE_FAIL);
        }
        return response;
    }
}
