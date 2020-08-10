INSERT INTO `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`,
                                   `authorized_grant_types`, `web_server_redirect_uri`,
                                   `authorities`, `access_token_validity`,
                                   `refresh_token_validity`, `additional_information`,
                                   `autoapprove`)
VALUES ('gateway', 'user_info',
        '{sha256}12425bfe64aff82ba9c2f7d1464fde5454bd8d309b9a41180468e0dcfd61aa352f4f0dd4aa3e8d22',
        'all', 'authorization_code,password,refresh_token', 'http://lovebirdy:8080/login',
        '', NULL, NULL, '{}', 'all');
INSERT INTO `oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`,
                                   `authorized_grant_types`, `web_server_redirect_uri`,
                                   `authorities`, `access_token_validity`,
                                   `refresh_token_validity`, `additional_information`,
                                   `autoapprove`)
VALUES ('resource', '',
        '{sha256}251db28971a7bfc6ee6815ea61c4baca176d5336c358d7d4281a324c9893eec8d018dcc46abc323c',
        '', '', '', '', NULL, NULL, '{}', '');
