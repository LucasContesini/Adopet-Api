package com.example.adopet.model.auth;


import com.example.adopet.dto.auth.FirebaseUserDTO;
import com.example.adopet.dto.auth.GoogleCredentialsDTO;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseConfig {


    public FirebaseConfig() {
        try {
            final GoogleCredentialsDTO googleCredentialsDTO = new GoogleCredentialsDTO();
            final String sCredentials = new Gson().toJson(googleCredentialsDTO);
            InputStream credentialsStream = new ByteArrayInputStream(sCredentials.getBytes());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(credentialsStream))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException ex) {
        }
    }


    public FirebaseUserDTO createFirebaseUserInfo(String email, String password) {
        try {
            UserRecord user;
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setEmailVerified(false)
                    .setPassword(password)
                    .setDisabled(false);
            user = FirebaseAuth.getInstance().createUser(request);
            return new FirebaseUserDTO(user.getUid());
        } catch (FirebaseAuthException ex) {
            String uid = getFirebaseUserByEmail(email);
            return new FirebaseUserDTO(uid);
        }
    }

    public String getFirebaseUserByEmail(String email) {
        try {
            String uid = FirebaseAuth.getInstance().getUserByEmail(email).getUid();
            return uid;
        } catch (FirebaseAuthException e) {
            return null;
        }
    }
}
