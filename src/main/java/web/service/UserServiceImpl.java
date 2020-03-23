package web.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.model.User;

@Service
public class UserServiceImpl implements UserService {
    RestTemplate restTemplate;
    private String USER_URL = "http://localhost:8081/api/v1/admin/user/";

    public UserServiceImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.basicAuthentication("ADMIN", "ADMIN").build();
    }

    @Override
    public String getUsers(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public User getUser(String username) {
        final User user = restTemplate.getForObject(USER_URL + username, User.class);
        return user;
    }

    @Override
    public void deleteUser(String url) {
        restTemplate.getForObject(url, String.class);
    }

    @Override
    public String saveUser(User user, String url) {
        return restTemplate.postForObject(url, user, String.class);
    }
}
