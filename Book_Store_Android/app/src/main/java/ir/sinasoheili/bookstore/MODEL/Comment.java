package ir.sinasoheili.bookstore.MODEL;

import com.google.gson.annotations.SerializedName;

public class Comment
{
    @SerializedName("ID")
    private int id;

    @SerializedName("CONTENT")
    private String content;

    @SerializedName("DATE")
    private String date;

    @SerializedName("USER_ID")
    private int user_id;

    @SerializedName("BOOK_ID")
    private int book_id;

    //constructor
    public Comment(int id, String content, String date, int user_id, int book_id)
    {
        this.id = id;
        this.content = content;
        this.date = date;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    //setter
    public void setContent(String content)
    {
        this.content = content;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public void setBook_id(int book_id)
    {
        this.book_id = book_id;
    }

    //getter
    public int getId()
    {
        return id;
    }

    public String getContent()
    {
        return content;
    }

    public String getDate()
    {
        return date;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public int getBook_id()
    {
        return book_id;
    }

    //to string
    @Override
    public String toString()
    {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                '}';
    }
}
