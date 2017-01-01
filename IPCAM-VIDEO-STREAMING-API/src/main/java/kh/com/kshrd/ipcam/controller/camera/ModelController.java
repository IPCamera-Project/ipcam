package kh.com.kshrd.ipcam.controller.camera;

import com.sun.org.apache.xpath.internal.operations.Mod;
import kh.com.kshrd.ipcam.entity.camera.Model;
import kh.com.kshrd.ipcam.entity.form.ModelInputer;
import kh.com.kshrd.ipcam.entity.form.ModelModifier;
import kh.com.kshrd.ipcam.respone.Response;
import kh.com.kshrd.ipcam.respone.ResponseCode;
import kh.com.kshrd.ipcam.respone.ResponseMessage;
import kh.com.kshrd.ipcam.respone.ResponseObject;
import kh.com.kshrd.ipcam.service.impl.ModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by rina on 12/21/16.
 */
@Controller
@RequestMapping("api/model")
public class ModelController {

    @Autowired
    ModelServiceImpl modelService;

    @RequestMapping(value = "/getAllModel",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseObject<Model> getAllModel(){
        ResponseObject<Model> response  = new ResponseObject<>();
        List<Model> list = modelService.getAllModel();
        List<Model> modelArrayList = new ArrayList<>();
            for(Model model : list) {
                String directory = environment.getProperty("file.getImage.path");
                model.setImage(directory+"/"+model.getImage());
                modelArrayList.add(model);
            }
            try{
                if(modelArrayList!=null){
                    response.setCode(ResponseCode.QUERY_FOUND);
                    response.setMessage(ResponseMessage.MODEL_MESSAGE);
                    response.setData(modelArrayList);
                }
                else {
                    response.setCode(ResponseCode.QUERY_NOT_FOUND);
                    response.setMessage(ResponseMessage.MODEL_MESSAGE);
                }
            }
            catch (Exception e){
                e.printStackTrace();
                response.setCode(ResponseCode.QUERY_NOT_FOUND);
                response.setMessage(ResponseMessage.MODEL_MESSAGE);
            }

        return response;
    }

    @RequestMapping(  value = "/getModelById",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public ResponseObject<Model> getModelById(@RequestParam("model_id")int model_id){
        ResponseObject<Model> response  = new ResponseObject<>();

        try {
            Model model = modelService.getModelById(model_id);

            String directory = environment.getProperty("file.getImage.path");
            model.setImage(directory+"/"+model.getImage());

            if(model!=null){
                response.setCode(ResponseCode.QUERY_FOUND);
                response.setMessage(ResponseMessage.MODEL_MESSAGE);
                response.setData(model);
            }
            else {
                response.setCode(ResponseCode.QUERY_NOT_FOUND);
                response.setMessage(ResponseMessage.MODEL_MESSAGE);
            }
        }
        catch (Exception e){
            response.setCode(ResponseCode.QUERY_NOT_FOUND);
            response.setMessage(ResponseMessage.MODEL_MESSAGE);
        }

        return response;
    }

    @Autowired
    Environment environment;
    @RequestMapping(value = "/addModel",method=RequestMethod.POST,consumes = "multipart/form-data;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<?> addModel(@RequestParam("NAME")String name,
                                      @RequestParam("VENDER_ID")int vender_id,
                                      @RequestParam("PLUGIN_ID")int plugin_id,
                                      @RequestParam("FILE_IMAGE")MultipartFile multipartFile){




        String   filename = multipartFile.getOriginalFilename();
        String[] output = filename.split("\\.");//split string specific "."
        String randomFileName = UUID.randomUUID()+"."+output[1];
        randomFileName = randomFileName+"";

        String directory = environment.getProperty("file.upload.path");
        String filepath = Paths.get(directory, randomFileName).toString();

        ModelInputer modelInputer = new ModelInputer();
        modelInputer.setName(name);
        modelInputer.setVender_id(vender_id);
        modelInputer.setImage(randomFileName);
        modelInputer.setPlugin_id(plugin_id);


        Map<String,Object> map = new HashMap<>();

        try{
            if(modelService.save(modelInputer)){

                // Save the file locally
                BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(filepath));
                stream.write(multipartFile.getBytes());
                stream.close();

                map.put("CODE", HttpStatus.OK);
                map.put("MESSAGE","SUCESSFULL");
            }
            else {
                map.put("MESSAGE","UNSUCESSFULL");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @RequestMapping( value = "/updateModel",
                     method = RequestMethod.PUT,
                     produces = "application/json")
    @ResponseBody
    public Response updateModel(@RequestParam("MODEL_ID")int model_id,
                                @RequestParam("NAME")String name,
                                @RequestParam("VENDER_ID")int vender_id,
                                @RequestParam("PLUGIN_ID")int plugin_id,
                                @RequestParam("FILE_IMAGE")MultipartFile multipartFile){

        Response response = new Response();

        String   filename = multipartFile.getOriginalFilename();
        String[] output = filename.split("\\.");//split string specific "."
        String randomFileName = UUID.randomUUID()+"."+output[1];
        randomFileName = randomFileName+"";

        String directory = environment.getProperty("file.upload.path");
        String filepath = Paths.get(directory, randomFileName).toString();

        ModelModifier modelModifier = new ModelModifier();
            modelModifier.setModel_id(model_id);
            modelModifier.setName(name);
            modelModifier.setVender_id(vender_id);
            modelModifier.setImage(randomFileName);
            modelModifier.setPlugin_id(plugin_id);

        if(modelService.update(modelModifier)){
            response.setMessage(ResponseCode.UPDATE_SUCCESS);
            response.setCode(ResponseMessage.MODEL_MESSAGE);

            // Save the file locally
          try {
              BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(filepath));
              stream.write(multipartFile.getBytes());
              stream.close();
            } catch (IOException e) {e.printStackTrace();}
        }
        else {
            response.setCode(ResponseMessage.MODEL_MESSAGE);
            response.setMessage(ResponseCode.UPDATE_FAIL);
        }
        return response;
    }


    @RequestMapping(value = "/removeModel", method = RequestMethod.DELETE,
                      produces = "application/json")
    @ResponseBody
    public  Response removeModel(@RequestParam("model_id") int model_id){
        Response response = new Response();
        if(modelService.remove(model_id)){
            response.setMessage(ResponseCode.DELETE_SUCCESS);
            response.setCode(ResponseMessage.MODEL_MESSAGE);
        }
        else {
            response.setCode(ResponseMessage.MODEL_MESSAGE);
            response.setMessage(ResponseCode.DELETE_FAIL);
        }
        return response;
    }

}