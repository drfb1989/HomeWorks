package controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.Post;
import user.User;
import user.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user,
            @RequestParam(name = "fail", defaultValue = "false") boolean fail
    ) {
        User saved = userService.createUserWithPosts(user, fail);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-name")
    public List<User> getByName(@RequestParam String name) {
        return userService.getUsersByName(name);
    }

    @GetMapping("/by-domain")
    public List<User> getByDomain(@RequestParam String domain) {
        return userService.getUsersByEmailDomain(domain);
    }

    @GetMapping("/{userId}/posts")
    public List<Post> getPosts(@PathVariable Long userId) {
        return userService.getPostsByUserId(userId);
    }
}
