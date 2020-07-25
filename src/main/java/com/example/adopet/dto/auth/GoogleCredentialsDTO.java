package com.example.adopet.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class GoogleCredentialsDTO implements Serializable {

    private String type;
    private String project_id;
    private String private_key_id;
    private String private_key;
    private String client_email;
    private String client_id;
    private String auth_uri;
    private String token_uri;
    private String auth_provider_x509_cert_url;
    private String client_x509_cert_url;

    public GoogleCredentialsDTO(){
        this.type = "service_account";
        this.project_id = "adopet-17316";
        this.private_key_id = "371142328a645179371456e71c8c05d23b67ac9b";
        this.private_key = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCjWLhNinkAUw/e\nNI2FCYzFnnkM4DHkgY4GuP/4gUlRnuwfisMrgFCubZ/ot3SdN9FMgFSQBBtVgSqT\nPPbZWY/dGHH++jxDKipCIJkMEkCkZLsHe6I/wSrXX7F0hD1t4Y8G6AtPI/XIK8TO\ncROjEMeurXNBIoJpJua1Tt/Rh0+0zBgjSuhe9CuzaF7N9VrqjumrqWAI6NEc78DV\nwQsR/+ysYTbpjnycHNAu75N+9UYwGuwUe4XR7fKCg0YJdw1n+SVy0FRZ0g97G+rO\n2py7vMrvVlCcpGXwMWEFOw5LUvDpFQr41UO26M9hj3AsHFN6Dqevbp8ydcdPI09X\nkPquM6gnAgMBAAECggEABVbxcg90ac9NSyflTGkF11vufGjube186rlXchR/2hGG\nNHhrwttHR+cWKBxUd+M1jfj6oAPnr4GGa8O9agN50XIVdTlRiPyINHCDsEJUfKCS\nVenzttPQsQj+xbP2R3DtJP5RiFJrnBHaNXQQKUTI0kFMmDTRldsLWvfrbG+uP6xd\nh9Lj6616LSn6UcP/3vVavJIW5l9/CD6/mnAzTR0p/KvF4oW8cSKTJ2rLRYX9o1mZ\nBgr4qYy10h2588l7KoTP5G92MtI2ZnHu/S6cNwSFfaZxUC3jh92XCJWhMyoTpW3D\n57Rvf0Klm+3YuztwIvBo/SXjfd+ZouBbDr+YiGonUQKBgQDfxo4pnXXXPR6N8RUh\n4H0n/K4cuTpiBmpcfPSHDA8WjQKP+vUQNhWMoxtVPwFoqe+OkLqXAwfeDuk3ZDTW\nrY7sacttemzfpSsXXGfapD0DmPxJ0F2XIHdOCnvelU0rElsEKOvloTm0UBvVKLvx\nfoiMKZuk5DDmYVffLFHw+NrImQKBgQC63nWixAj2a/5Gy8oo1S93w6zRQcXEUccD\nadSh+qcOOcRlabmxC15r5Z3Uf3QGeHBjpuLTQwtHTEMYisxbSz8dhYgIVmdkGKjC\nBtST7mJcm2NuZ/7TinwHKxptZUAHnt939VpOi/e8KNF9fQDIJXEBU4fwBId04zNJ\nt/lgx6CuvwKBgC9dp9nHKppUpZMF5KM3EUbVhUUvL9xv3zOUf23Wb5smXdR8GkNE\nL2uFWtPAclvlHH/gwp4UViX6ogLHgyettQL1ocmakFEGdQiM1q7A9Ok+CKjXIfhL\nNvWd4npKv/rN99X5k3UXm08/ZaTI6n9DeUOxyvAsHO2mzSLADtgi1KyBAoGASEDT\nw0eBH6bJ/p21YOxtGiWyoIaJ7UqhZJR6/tZn2XxEj+G9RtNaguWZjRxqxEW1TxKs\nCXuW1M6L8uVX9sS1dri2zGJ8P5u5RKxXbYtyxNPgMkRLp4eYRT1JTpsjks6/ISbg\n8BX2h+5QcfDvaHPxqc2edhOKPIvMvOhBqEYHvwkCgYEAzK1mbo+tWBTj3yTvvP7Z\nUEo8rWQ9CNEgKB1nEmxLqRA55DKGjBQ8vdBHmYTOA15oSBNqsFfb8nFQmoqW6qS7\n1SpV2SAkQCewXiMdkLsmRF1HvfYBiNJZj62P0eyzyaSVQpRq76kKKHS6oIc4QHo6\n4cqicg6teMtKYXMylnOmu7g=\n-----END PRIVATE KEY-----\n";
        this.client_email = "firebase-adminsdk-xbu46@adopet-17316.iam.gserviceaccount.com";
        this.client_id = "113098258806343922183";
        this.auth_uri = "https://accounts.google.com/o/oauth2/auth";
        this.token_uri = "https://oauth2.googleapis.com/token";
        this.auth_provider_x509_cert_url = "https://www.googleapis.com/oauth2/v1/certs";
        this.client_x509_cert_url = "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-xbu46%40adopet-17316.iam.gserviceaccount.com";
    }



}
