package Module_7;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Twitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        User user1 = new User(name);
        System.out.println("Добро пожаловать, " + name);

        Twitter.addStartPosts();
        System.out.println("Добавлены стартовые посты");

        System.out.println();
        System.out.println("=== Twitter Console ===");
        System.out.println("1. Написать пост");
        System.out.println("2. Лайкнуть пост");
        System.out.println("3. Сделать репост");
        System.out.println("4. Показать все посты");
        System.out.println("5. Показать популярные посты");
        System.out.println("6. Показать мои посты");
        System.out.println("7. Выход");
        System.out.println("8. Написать комментарии");
        System.out.println("9. Удалить пост");
        System.out.println("10. Сохранить посты в файл");
        System.out.println();

        while(0 < 1) {

            System.out.print("Выберите действие: ");
            int action = scanner.nextInt();

            if (action == 1) {
                writePost(user1);
            } else if (action == 2) {
                likePost();
            } else if (action == 3) {
                makeRepost();
            } else if (action == 4) {
                showAllPosts();
            } else if (action == 5) {
                showPopularPosts();
            } else if (action == 6) {
                showMyPosts(name);
            } else if (action == 7) {
                System.out.println("Выход...");
                break;
            } else if (action == 8) {
                writeComment(user1);
            } else if (action == 9) {
                deletePost();
            } else if (action == 10) {
                savePostsToFile(User.allPosts, "posts.data");
            } else {
                System.out.println("Вы ввели не правильный номер!");
            }
            System.out.println();

        }



    }


    static void addStartPosts(){
        User user1 = new User("Ilon Mask");
        User user2 = new User("Bill Gates");

        Post postOne = new Post(user1, "Я Илон Маск, Сегодня купил Twitter");
        Post postTwo = new Post(user2, "Я Билл Гейтс, Я богатый чел!");
        postOne.setCountOfLikes(501);
        postTwo.setCountOfLikes(250);

        User.addAllPosts(postOne);
        User.addAllPosts(postTwo);

    }



    // Методы 1-7
    // 1. Написать пост
    static void writePost(User user){

        System.out.println("Введите текст поста (макс. 280 символов): ");
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();

        if(content.length() <= 280) {
            Post post = new Post(user, content);
            System.out.println("Пост добавлен!");

            User.addAllPosts(post); //добавляем пост в allPosts - где хранятся все посты
        } else {
            System.out.println("Вы превысили лимит букв: " + content.length());
        }
    }

    // 2. Лайкнуть пост
    static void likePost(){
        System.out.print("Введите ID поста которую хотите лайкнуть: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();


        //Находим пост с подходящим ID. Ищим через StreamAPI в allposts
        Post result = User.allPosts.stream().
                filter(post -> post.getID() == id).
                findFirst().
                orElse(null);

        //увеличиваем количество лайков на +1
        result.setCountOfLikes(result.getCountOfLikes() + 1);


        System.out.println("Лайк поставлен");
        System.out.println("Количество лайков сейчас: " + result.getCountOfLikes());
    }


    //3. Сделать репост
    static void makeRepost(){
        System.out.print("Введите ID поста которую сделать репостнуть: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();


        //Находим пост с подходящим ID. Ищим через StreamAPI в allposts
        Post result = User.allPosts.stream().
                filter(post -> post.getID() == id).
                findFirst().
                orElse(null);


        //делаем репост, увеличивая его на +1
        result.setCountOfReposts(result.getCountOfReposts() + 1);



        System.out.println("Репост сделан!");
        System.out.println("Количество репостов сейчас: " + result.getCountOfReposts());

    }

    //4. Показать все посты
    static void showAllPosts(){
        System.out.println("Все посты:");
        for(Post p: User.allPosts){
            System.out.println(p);
        }
    }

    //5. Показать популярные посты
    static void showPopularPosts(){
        System.out.print("Введите количество популярных постов: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Post> topNPosts = User.allPosts.stream()
                .sorted(Comparator.comparingInt(Post::getCountOfLikes).reversed())
                .limit(n) // берем только n постов
                .collect(Collectors.toList());

        topNPosts.forEach(post -> System.out.println(post));


    }

    //6. Показать мои посты
    static void showMyPosts(String name){
        System.out.println("Мои посты: ");

        List<Post> myPosts = User.allPosts.stream()
                .filter(post -> post.getAuthor() == name)
                .toList();
        myPosts.forEach(post -> System.out.println(post));

    }

    static void writeComment(User user){
        System.out.print("Введите ID поста для которого хотите написать комментарии: ");
        Scanner scanner = new Scanner(System.in);
        int postID = scanner.nextInt();

        Post result = User.allPosts.stream().
                filter(post -> post.getID() == postID).
                findFirst().
                orElse(null);

        System.out.println("Пишите комментарии: ");
        Scanner scanner2 = new Scanner(System.in);
        String commentContent = scanner2.nextLine();

        Comment comment = new Comment(user.getName(), commentContent);

        result.addComments(comment);
        System.out.println("Комментарии добавлен");
    }

    static void deletePost(){
        System.out.print("Введите ID поста для которого хотите удалить: ");
        Scanner scanner = new Scanner(System.in);
        int postID = scanner.nextInt();

        Post result = User.allPosts.stream().
                filter(post -> post.getID() == postID).
                findFirst().
                orElse(null);

        User.allPosts.remove(result);

        System.out.println("Пост удален! ");
    }

    public static void savePostsToFile(List<Post> posts, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(posts);
            System.out.println("Посты успешно сохранены в файл: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Post> loadPostsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Post>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>(); // возвращаем пустой список в случае ошибки
        }
    }
}
