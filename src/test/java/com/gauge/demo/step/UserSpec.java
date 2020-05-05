package com.gauge.demo.step;

import com.gauge.demo.client.GithubClient;
import com.gauge.demo.model.Repo;
import com.gauge.demo.model.User;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class UserSpec {

    private DataStore dataStore = DataStoreFactory.getSpecDataStore();
    private final GithubClient githubClient;

    public UserSpec(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @Step("query user <username> from github and get repos count")
    public void getUser(String username) {
        dataStore.put("username", username);

        User user = githubClient.getUser(username);
        System.out.println(":::::::::::::userr" + user);

        assertThat(user.getName()).isEqualTo(username);

        dataStore.put("repoCount", user.getRepoCount());
    }

    @Step("query given user repos and validate repos count")
    public void getUserRepos() {
        String username = (String) dataStore.get("username");
        int repoCount = (int) dataStore.get("repoCount");

        List<Repo> repos = githubClient.getRepos(username);

        assertThat(repos).hasSize(repoCount);

        Repo repo = repos.get(new Random().nextInt(repoCount));
        assertThat(repo.getOwner()).isNotNull();
        assertThat(repo.getOwner().getName()).isEqualTo(username);
    }
}
