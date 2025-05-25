package com.kepes.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.kepes.config.Cred;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Component
public class GoogleAuth {

    HttpTransport transport = new NetHttpTransport();
    JsonFactory jsonFactory = new GsonFactory();

    final String WEB_CLIENT_ID = new Cred().getWebClientId();

    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            // Specify the WEB_CLIENT_ID of the app that accesses the backend:
            .setAudience(Collections.singletonList(WEB_CLIENT_ID))
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(WEB_CLIENT_ID_1, WEB_CLIENT_ID_2, WEB_CLIENT_ID_3))
            .build();

// (Receive idTokenString by HTTPS POST)
public boolean isIdTokenValid(String idTokenString) {
        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);
            System.out.println(WEB_CLIENT_ID);
            System.out.println(idTokenString);
            if (idToken != null) {
                Payload payload = idToken.getPayload();

                // Print user identifier
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);

                // Get profile information from payload
                String email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                // Use or store profile information
                // ...
                return true;
            } else {
                System.out.println("Invalid ID token.");
                return false;
            }
        }catch (GeneralSecurityException | IOException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
