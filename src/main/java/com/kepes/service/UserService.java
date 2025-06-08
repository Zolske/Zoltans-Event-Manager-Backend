package com.kepes.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.kepes.model.User;
import java.util.List;

public interface UserService {

    User getUser(String id);
    GoogleIdToken verifyGoogleIdToken(String googleIdToken);

    List<User> getAllUsers();
}
