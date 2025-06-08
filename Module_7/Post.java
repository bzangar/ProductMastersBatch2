package Module_7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Post implements Serializable {
    private static final long serialVersionUID = 1L;


    private int ID;
    private String author;
    private String content;
    private int countOfLikes;
    private int countOfReposts;
    public static int identificator = 0;
    private List<Comment> comments = new ArrayList<>(); //комментарии принадлежат только экземпляру поста

    public List<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment) {
        this.comments.add(comment);
    }

    public Post(User user, String text) {
        identificator ++;
        this.author = user.getName();
        this.ID = identificator;
        this.content = text;
    }

    public int getID() {
        return ID;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author.getName();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(content.length() <= 280){
            this.content = content;
        } else {
            System.out.println("Вы достигли максимального количество символов (" + content.length() + " из 280)!. Сократите текст");
        }
    }

    public int getCountOfLikes() {
        return countOfLikes;
    }

    public void setCountOfLikes(int countOfLikes) {
        this.countOfLikes = countOfLikes;
    }

    public int getCountOfReposts() {
        return countOfReposts;
    }

    public void setCountOfReposts(int countOfReposts) {
        this.countOfReposts = countOfReposts;
    }

    @Override
    public String toString() {
        return "Post{" +
                "ID=" + ID +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", countOfLikes=" + countOfLikes +
                ", countOfReposts=" + countOfReposts +
                ", comments=" + comments +
                '}';
    }
}
