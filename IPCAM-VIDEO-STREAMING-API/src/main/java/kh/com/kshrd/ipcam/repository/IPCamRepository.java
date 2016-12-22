package kh.com.kshrd.ipcam.repository;

import java.util.ArrayList;

import kh.com.kshrd.ipcam.entity.camera.IPCam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface IPCamRepository {

	final String GET_ALL_CAMERA			=	"SELECT * FROM tbl_camera";
	
	final String GET_CAMERA_BY_ID		=	"SELECT * FROM tbl_camera WHERE camera_id = #{id}";


	final String DELETE_CAMERA_BY_ID	=	"DELETE FROM tbl_camera WHERE camera_id = #{id}";
	
	
	final String UPDATE_CAMERA_BY_ID	=	"UPDATE tbl_camera SET name=#{NAME},serial_number=#{SERIAL_NUMBER},ip_address=#{IP_ADDRESS},web_port=#{WEB_PORT},rtsp_port=#{RTSP_PORT},username=#{USERNAME},password=#{PASSWORD},stream_url=#{STREAM_URL},model_id=#{MODEL_ID},user_id=#{USER_ID} WHERE camera_id=#{CAMERA_ID}";
	
	
	final String INSERT_CAMERA			=	"INSERT INTO tbl_camera "
											+ "(name,serial_number,ip_address,web_port,rtsp_port,username,password,stream_url,model_id,user_id) "
											+ "VALUES(#{NAME},#{SERIAL_NUMBER},#{IP_ADDRESS},#{WEB_PORT},#{RTSP_PORT},#{USERNAME},#{PASSWORD},#{STREAM_URL},#{MODEL_ID},#{USER_ID})";


	@Select(IPCamRepository.GET_ALL_CAMERA)
	@Results({
		@Result(property="id", column="camera_id"),
		@Result(property="name", column="name"),
		@Result(property="serial_number", column="serial_number"),
		@Result(property="ip_address", column="ip_address"),
		@Result(property="web_port", column="web_port"),
		@Result(property="rtsp_port", column="rtsp_port"),
		@Result(property="username", column="username"),
		@Result(property="password", column="password"),
		@Result(property="stream_url", column="stream_url"),
		@Result(property="user", column="user_id", one = @One(select = "kh.com.kshrd.ipcam.repository.UserRepository.getUserByID")),
		@Result(property="model", column="model_id", one = @One(select = "kh.com.kshrd.ipcam.repository.camera.ModelRepository.findOne")),

	})
	ArrayList<IPCam> findAll();


	@Select(IPCamRepository.GET_CAMERA_BY_ID)
	@Results({
		@Result(property="id", column="camera_id"),
		@Result(property="name", column="name"),
		@Result(property="serial_number", column="serial_number"),
		@Result(property="ip_address", column="ip_address"),
		@Result(property="web_port", column="web_port"),
		@Result(property="rtsp_port", column="rtsp_port"),
		@Result(property="username", column="username"),
		@Result(property="password", column="password"),
		@Result(property="stream_url", column="stream_url"),
		@Result(property="user", column="user_id", one = @One(select = "kh.com.kshrd.ipcam.repository.UserRepository.getUserByID")),
		@Result(property="model", column="model_id", one = @One(select = "kh.com.kshrd.ipcam.repository.ModelRepository.findOne"))
	})
	IPCam findOne(int id);

	@Delete(IPCamRepository.DELETE_CAMERA_BY_ID)
	boolean remove(int id);

	@Update(IPCamRepository.UPDATE_CAMERA_BY_ID)
	boolean updateCameraByID(IPCam ipCam);

	@Insert(IPCamRepository.INSERT_CAMERA)
	boolean insertCamera(IPCam ipCam);




}
