package kh.com.kshrd.ipcam.repository;

import java.util.ArrayList;
import java.util.List;

import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.entity.form.IPCameraInputer;
import kh.com.kshrd.ipcam.entity.form.IPCameraModifier;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface IPCamRepository {

	final String GET_ALL_CAMERA			=	"SELECT * FROM tbl_camera";
	
	final String GET_CAMERA_BY_ID		=	"SELECT * FROM tbl_camera WHERE camera_id = #{camera_id}";


	final String DELETE_CAMERA_BY_ID	=	"UPDATE  tbl_camera set active=0 WHERE camera_id = #{id}";
	
	
	final String UPDATE_CAMERA_BY_ID	=	"UPDATE tbl_camera SET name=#{name},serial_number=#{serial_number}," +
			                                "ip_address=#{ip_address},web_port=#{web_port},rtsp_port=#{rtsp_port}," +
			                                "username=#{username},password=#{password},model_id=#{model_id},user_id=#{user_id}" +
			                                " WHERE camera_id=#{camera_id}";
	
	
	final String INSERT_CAMERA	=	"INSERT INTO tbl_camera "
			                       + "(name,serial_number,ip_address,web_port,rtsp_port,username,password,model_id,user_id) "
			                       + "VALUES(#{name},#{serial_number},#{ip_address},#{web_port},#{rtsp_port},#{username}," +
			                       " #{password},#{model_id},#{user_id})";



	@Select(IPCamRepository.GET_ALL_CAMERA)
	@Results({
		@Result(property="camera_id", column="camera_id"),
		@Result(property="name", column="name"),
		@Result(property="serial_number", column="serial_number"),
		@Result(property="ip_address", column="ip_address"),
		@Result(property="web_port", column="web_port"),
		@Result(property="rtsp_port", column="rtsp_port"),
		@Result(property="username", column="username"),
		@Result(property="password", column="password"),
		@Result(property="user", column="user_id", one = @One(select = "kh.com.kshrd.ipcam.repository.UserRepository.getUserByID")),
		@Result(property="model", column="model_id", one = @One(select = "kh.com.kshrd.ipcam.repository.ModelRepository.findOne"))

	})
	ArrayList<IPCam> findAll();


	@Select(IPCamRepository.GET_CAMERA_BY_ID)
	@Results({
		@Result(property="camera_id", column="camera_id"),
		@Result(property="name", column="name"),
		@Result(property="serial_number", column="serial_number"),
		@Result(property="ip_address", column="ip_address"),
		@Result(property="web_port", column="web_port"),
		@Result(property="rtsp_port", column="rtsp_port"),
		@Result(property="username", column="username"),
		@Result(property="password", column="password"),
		@Result(property="user", column="user_id", one = @One(select = "kh.com.kshrd.ipcam.repository.UserRepository.getUserByID")),
		@Result(property="model", column="model_id", one = @One(select = "kh.com.kshrd.ipcam.repository.ModelRepository.findOne"))
	})
	IPCam findOne(@Param("camera_id")int camera_id);

	@Delete(IPCamRepository.DELETE_CAMERA_BY_ID)
	boolean remove(int id);

	@Update(IPCamRepository.UPDATE_CAMERA_BY_ID)
	boolean updateCamera(IPCameraModifier ipCam);

	@Insert(INSERT_CAMERA)
	boolean insertCamera(IPCameraInputer ipCam);

	@Select("SELECT tc.*, tu.* FROM tbl_camera tc LEFT JOIN tbl_user tu ON tc.user_id = tu.user_id WHERE tu.user_id = #{userId} AND tu.active = 1 AND tc.active = 1 AND tc.camera_id = #{camId}")
	@Results({
			@Result(property="model", column="model_id", one = @One(select = "kh.com.kshrd.ipcam.repository.ModelRepository.findOne"))
	})
	IPCam getCamOnce(@Param("userId") int userId, @Param("camId") int camId);



	@Select("SELECT tc.*, tu.* FROM tbl_camera tc LEFT JOIN tbl_user tu ON tc.user_id = tu.user_id WHERE tu.user_id = #{userId} AND tu.active = 1 AND tc.active = 1 ")
	@Results({
			@Result(property="model", column="model_id", one = @One(select = "kh.com.kshrd.ipcam.repository.ModelRepository.findOne")),
			@Result(property = "user", column = "user_id",one = @One(select = "kh.com.kshrd.ipcam.repository.UserRepository.getUserByID")),
	})
	List<IPCam> getCamByUserId(@Param("userId") int userId);




}
