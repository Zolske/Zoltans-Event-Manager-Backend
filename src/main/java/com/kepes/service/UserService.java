package com.kepes.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.kepes.model.User;

public interface UserService {

    User getUser(String id);
    GoogleIdToken verifyGoogleIdToken(String googleIdToken);

}
