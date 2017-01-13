package kh.com.kshrd.ipcam.repository;

import kh.com.kshrd.ipcam.entity.camera.Vender;
import kh.com.kshrd.ipcam.entity.form.VenderInputer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface VenderRepository {
    String SELECT_BY_ID = "select * from tbl_vender where vender_id = #{vender_id} and active = 1";
    String SELECT_ALL = "select * from tbl_vender";

    @Select(SELECT_BY_ID)
        @Results(value = {
                @Result(property="vender_id", column="vender_id")
        })
    Vender getVenderById(@Param("vender_id")int vender_id);


    @Insert("insert into tbl_vender(name,logo) values(#{name},#{logo})")
    boolean addNewVender(VenderInputer venderInputer);

    @Update("update tbl_vender set name=#{name},logo=#{logo} where vender_id = {#vender_id}")
    boolean updateVender(Vender vender);

    @Delete("update tbl_vender set active = 0 where vender_id = #{vender_id}")
    boolean removeVender(@Param("vender_id")int vender_id);

    @Select(SELECT_ALL)
    ArrayList<Vender> getAllVendor();
}
