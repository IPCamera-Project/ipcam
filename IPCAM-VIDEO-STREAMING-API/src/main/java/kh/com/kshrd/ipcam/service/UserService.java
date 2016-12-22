package kh.com.kshrd.ipcam.service;

import kh.com.kshrd.ipcam.entity.user.User;
import kh.com.kshrd.ipcam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rina on 12/20/16.
 */
@Service
public class UserService implements CrudService<User> {
    @Autowired
    UserRepository userRepository;
    @Override
    public User findOne(int id) {
        return userRepository.getUserByID(id);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean update(User object) {
        return false;
    }

    @Override
    public boolean save(User object) {
        return false;
    }
}
