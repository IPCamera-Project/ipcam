package kh.com.kshrd.ipcam.repository;

import kh.com.kshrd.ipcam.entity.camera.Vender;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface VenderRepository {
    String SELECT_BY_ID = "select * from tbl_vender where vender_id = #{vender_id} and active = 1";
    String SELECT_ALL = "select * from tbl_vender where active = 1 ";

    @Select(SELECT_BY_ID)
        @Results(value = {
                @Result(property="vender_id", column="vender_id")
        })
    Vender getVenderById(@Param("vender_id")int vender_id);

    @Select(SELECT_ALL)
    Vender getAllVendor();
}
