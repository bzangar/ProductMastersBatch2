package Module_7;

public class Comment {
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
