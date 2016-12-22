package kh.com.kshrd.ipcam.repository;
import kh.com.kshrd.ipcam.entity.user.User;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
	
	
	//THIS SQL USED IN IPCAMERA REPOSITORY. BY CHIVORN !
	final String GET_USER_BY_ID	=	"SELECT * FROM tbl_user WHERE user_id = #{user_id}";




	@Select(UserRepository.GET_USER_BY_ID)
	@Results({
		@Result(property="user_id", column="user_id"),
		@Result(property="name", column="display_name"),		
		@Result(property="username", column="username"),
		@Result(property="password", column="password"),
		@Result(property="image", column="image"),
		@Result(property="status", column="status")
	})
	User getUserByID(@Param("user_id") int user_id);
}
