package com.kepes.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.kepes.config.Cred;
import com.kepes.exception.ItemNotFoundException;
import com.kepes.model.User;
import com.kepes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else
            throw new ItemNotFoundException(String.format("User with id '%s' can not be found.", id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @Override
    public String deleteUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return String.format("Album with id '%s' has been deleted successfully.", userId);
        } else
            throw new ItemNotFoundException(String.format("Album with id '%s' can not be found.", userId));
    }

    @Override
    public GoogleIdToken verifyGoogleIdToken(String googleIdToken) {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        final String WEB_CLIENT_ID = new Cred().getWebClientId();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the WEB_CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(WEB_CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(WEB_CLIENT_ID_1, WEB_CLIENT_ID_2, WEB_CLIENT_ID_3))
                .build();

        try {
            return verifier.verify(googleIdToken);
        } catch (GeneralSecurityException | IOException e) {
            System.out.println("Google Authentication failed: ".concat(e.getMessage()));
        }
        return null;
    }

    public String toggleAdminSetting(String userId) {
        Optional<User> userOriginal = userRepository.findById(userId);
        if (userOriginal.isPresent()) {
            User userUpdate = userOriginal.get();
            if (userOriginal.get().getIsAdmin() != null) {
                userUpdate.setIsAdmin(!userOriginal.get().getIsAdmin());
            }
            userRepository.save(userUpdate);
        }
        return "Switched Admin setting.";
    }

    public String createUserRecord(User userData) {
        if (userData != null) {
            Optional<User> exist = userRepository.findById(userData.getIdUser());
            if (exist.isPresent())
                return "User is already in database.";
            User newUser = new User();
            if (userData.getIdUser() != null)
                newUser.setIdUser(userData.getIdUser());
            if (userData.getName() != null)
                newUser.setName(userData.getName());
            if (userData.getEmail() != null)
                newUser.setEmail(userData.getEmail());
            if (userData.getPictureUrl() != null)
                newUser.setPictureUrl(userData.getPictureUrl());
                newUser.setIsAdmin(false);
                newUser.setIsRootAdmin(false);
            userRepository.save(newUser);
        }
        return "User has been created.";
    }
}
