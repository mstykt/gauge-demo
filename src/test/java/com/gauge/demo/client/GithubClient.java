package com.gauge.demo.client;

import com.gauge.demo.config.FeignConfig;
import com.gauge.demo.model.Repo;
import com.gauge.demo.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "github-api", url = "${api.client.url.github}", configuration = FeignConfig.class)
public interface GithubClient {

    @GetMapping("/users/{username}")
    User getUser(@PathVariable("username") String username);

    @GetMapping("/users/{username}/repos")
    List<Repo> getRepos(@PathVariable("username") String username);
}
