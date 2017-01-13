package kh.com.kshrd.ipcam.service;

import kh.com.kshrd.ipcam.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rina on 12/27/16.
 */
@Service
public class UserDetailService {
    @Autowired
    UserDetailRepository userDetailRepository;

   public boolean insertRole(int userId, int roleId){
        return  userDetailRepository.insertRole(userId,roleId);
    }

    public int getLastId(){
        return  userDetailRepository.getLastId();
    }
}
