package kh.com.kshrd.ipcam.repository;
import kh.com.kshrd.ipcam.entity.camera.Model;
import kh.com.kshrd.ipcam.entity.form.ModelInputer;
import kh.com.kshrd.ipcam.entity.form.ModelModifier;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ModelRepository {

	final String GET_ALL_MODEL	=	"select * from tbl_model where active = 1";

	String GET_MODEL_NAME = "select name from tbl_model where vender_id = #{VENDER_ID} and active = 1";

	final String GET_MODEL_BY_ID =	"select * from tbl_model where active = 1 and model_id = #{model_id}";

	final String INSERT_NEW = "INSERT INTO tbl_model(name,vender_id,image, plugin_id) VALUES(#{name},#{vender_id},#{image}, #{plugin_id})";

	final String UPDATE_MODEL = "UPDATE tbl_model SET (name = #{name},vendor_id = #{vendor_id},image = #{image},plugin_id = #{plugin_id})" +
			" WHERE model_id = #{model_id}";

	final String REMOVE_MODEL = "UPDATE tbl_model SET active = 1 where model_id = #{model_id}";

	@Select("select * from tbl_model where active = 1 and model_id = #{model_id}")
	@Results({
			@Result(property="model_id", column="model_id"),
			@Result(property="name", column="name"),
			@Result(property= "src/main/webapp/resources/image", column= "src/main/webapp/resources/image"),
			@Result(property="vender_id", column="vender_id"),
			@Result(property="logo", column="logo"),
			@Result(property="vender",column = "vender_id", one = @One(select = "kh.com.kshrd.ipcam.repository.VenderRepository.getVenderById")),
			@Result(property="plugin",column = "plugin_id", one = @One(select = "kh.com.kshrd.ipcam.repository.PluginRepository.getPluginById"))

	})
	Model findOne(@Param("model_id") int model_id);

	@Select(GET_MODEL_NAME)
	ArrayList<String> getAllModelName(@Param("VENDER_ID")int vender_id);


	@Select(GET_ALL_MODEL)
	@Results({
			@Result(property="model_id", column="model_id"),
			@Result(property="name", column="name"),
			@Result(property= "src/main/webapp/resources/image", column= "src/main/webapp/resources/image"),
			@Result(property="vender_id", column="vender_id"),
			@Result(property="logo", column="logo"),
			@Result(property="vender",column = "vender_id", one = @One(select = "kh.com.kshrd.ipcam.repository.VenderRepository.getVenderById")),
			@Result(property="plugin",column = "plugin_id", one = @One(select = "kh.com.kshrd.ipcam.repository.PluginRepository.getPluginById")),
			@Result(property="plugin_id", column="plugin_id")
	})
	public List<Model> getAllModel();

	@Insert(INSERT_NEW)
	boolean addModel(ModelInputer modelInputer);

	@Update(UPDATE_MODEL)
	boolean updateModel(ModelModifier modelModifier);

	@Delete(REMOVE_MODEL)
	boolean removeModle(@Param("model_id")int model_id);

}