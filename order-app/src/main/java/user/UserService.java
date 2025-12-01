package user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository,
                       PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public User createUserWithPosts(User user, boolean fail) {
        if (user.getPosts() != null) {
            for (Post post : user.getPosts()) {
                post.setUser(user);
            }
        }
        User saved = userRepository.save(user);
        if (fail) {
            throw new RuntimeException("Simulated failure for rollback");
        }
        return saved;
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getUsersByEmailDomain(String domain) {
        if (!domain.startsWith("@")) {
            domain = "@" + domain;
        }
        return userRepository.findByEmailEndingWith(domain);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }
}
