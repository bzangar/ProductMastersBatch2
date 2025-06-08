package Module_7;

import java.io.Serializable;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;
    private String author;
    private String content;
    public static int identificator = 0;

    public Comment(String author, String content) {
        identificator++;
        this.id = identificator;
        this.author = author;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "author='" + author + '\'' +
                ", comment='" + content + '\'' +
                '}';
    }
}
