package com.kepes.service;

import com.kepes.exception.ItemNotFoundException;
import com.kepes.model.User;
import com.kepes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        else
            throw new ItemNotFoundException(String.format("User with id '%s' can not be found.", id));
    }
}
