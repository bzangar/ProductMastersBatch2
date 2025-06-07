package Module_7;

import java.util.ArrayList;
import java.util.List;

class User {

    private String name;
    public static List<Post> allPosts = new ArrayList<>();


    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getAllPosts() {
        return allPosts;
    }

    public static void addAllPosts(Post post) {
        User.allPosts.add(post);
    }

}
