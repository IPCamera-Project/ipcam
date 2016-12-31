package kh.com.kshrd.ipcam.repository;
import kh.com.kshrd.ipcam.entity.form.UserInputer;
import kh.com.kshrd.ipcam.entity.form.UserModifier;
import kh.com.kshrd.ipcam.entity.user.Role;
import kh.com.kshrd.ipcam.entity.user.User;

import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository {


	//THIS SQL USED IN IPCAMERA REPOSITORY. BY CHIVORN !
	final String GET_USER_BY_ID	=	"select tbl_user.*,tbl_role.* from tbl_user " +
			" LEFT JOIN tbl_user_detail ON tbl_user_detail.user_id = tbl_user.user_id " +
			" LEFT JOIN tbl_role ON tbl_user_detail.role_id = tbl_role.role_id " +
			" where tbl_user.active = 1 and tbl_user.user_id = #{user_id}";


	final String GET_ALL_USER = "select tbl_user.* from tbl_user LEFT JOIN tbl_user_detail ON " +
			"tbl_user.user_id = tbl_user.user_id where tbl_user.active = 1";

	final String GET_USER_BY_EMAIL = "select tbl_user.*,tbl_role.* from tbl_user " +
			" LEFT JOIN tbl_user_detail ON tbl_user_detail.user_id = tbl_user.user_id " +
			" LEFT JOIN tbl_role ON tbl_user_detail.role_id = tbl_role.role_id " +
			" where tbl_user.active = 1 and tbl_user.email = #{email}";;

	final String INSERT_USER = "INSERT INTO tbl_user(username,password,email,image) " +
			"VALUES(#{username},#{password},#{email},#{image})";

	final String UPDATE_USER = "UPDATE tbl_user set (#{username,},#{password},#{email}) " +
			"WHERE user_id = #{user_id}";

	final String UPDATE_USER_IMAGE = "UPDATE tbl_user set image = #{image} where user_id = #{user_id}";

	final String REMOVE_USER = "UPDATE tbl_user set active = 1 where user_id = #{user_id}" ;

	final String GET_ROLE_BY_ID = "SELECT role_id,name,description FROM tbl_role where role_id = #{role_id}";

	final String GET_ROLE_BY_NAME = "SELECT role_id,name,description FROM tbl_role where name = #{role_name}";


	@Select(GET_USER_BY_ID)
	@Results(value = {
			@Result(property = "role",column = "role_id",one =@One(select = "getRoleById") )
	})
	User getUserByID(@Param("user_id") int user_id);


	@Select(GET_ROLE_BY_ID)
	Role getRoleById(@Param("role_id")int role_id);


	@Select(GET_USER_BY_EMAIL)
	@Results(value = {
			@Result(property = "role",column = "role_id",one =@One(select = "getRoleById") )
	})
	User getUserByEmail(@Param("email") String email);

	@Select(GET_ALL_USER)
	ArrayList<User> getAllUser();

	@Insert(INSERT_USER)
	boolean addUser(UserInputer userInputer);

	@Update(UPDATE_USER)
	boolean updateUser(UserModifier userModifier);

	@Update(UPDATE_USER_IMAGE)
	boolean updateUserImage(@Param("image")String image , @Param("user_id")int user_id);

	@Delete(REMOVE_USER)
	boolean removeUser(@Param("user_id")int user_id);

}