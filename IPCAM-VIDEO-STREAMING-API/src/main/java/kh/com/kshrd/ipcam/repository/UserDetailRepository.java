package kh.com.kshrd.ipcam.repository;

import com.sun.istack.internal.Nullable;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by rina on 12/27/16.
 */
@Repository
public interface UserDetailRepository {
    String INSERT = "insert into tbl_user_detail values(#{user_id},#{role_id})";

    @Insert(INSERT)
    boolean insertRole(@Param("user_id")int user_id, @Nullable @Param("role_id")int role_id);



    /**
     *
     * @return return key values from sequencial user_id
     */
    @Select("select last_value from tbl_user_user_id_seq")
    int getLastId();
}
