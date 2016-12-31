package kh.com.kshrd.ipcam.repository;

import kh.com.kshrd.ipcam.entity.plugin.Plugin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by sophatvathana on 21/12/16.
 */
@Repository
public interface PluginRepository {

    final String GET_PLUGIN_BY_ID	=	"SELECT * FROM tbl_plugin WHERE plugin_id = #{plugin_id}";

    final String INSERT_PLUGIN	    =	"INSERT INTO tbl_plugin "
            + "(plugin_name,plugin_description,plugin_version,plugin_release,plugin_active,plugin_vendor) "
            + "VALUES(#{plugin_name},#{plugin_description},#{plugin_version},#{plugin_release},#{plugin_active},#{plugin_vendor})";

    @Select(GET_PLUGIN_BY_ID)
    @Results({
            @Result(property="plugin_id", column="plugin_id"),
            @Result(property="plugin_name", column="plugin_name"),
            @Result(property="plugin_description", column="plugin_description"),
            @Result(property="plugin_version", column="plugin_version"),
            @Result(property="plugin_release", column="plugin_release"),
            @Result(property="plugin_isActive", column="plugin_isActive"),
            @Result(property = "plugin_vendor", column = "plugin_vendor")
    })
    Plugin getPluginById(@Param("plugin_id") int plugin_id);

    @Insert(PluginRepository.INSERT_PLUGIN)
    boolean insertPlugin(Plugin plugin);
}
