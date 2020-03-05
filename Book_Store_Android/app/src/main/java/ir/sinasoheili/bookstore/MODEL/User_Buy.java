package ir.sinasoheili.bookstore.MODEL;

import com.google.gson.annotations.SerializedName;

public class User_Buy
{
    @SerializedName("ID")
    private int id;

    @SerializedName("USER_ID")
    private int user_id;

    @SerializedName("BOOK_ID")
    private int book_id;

    @SerializedName("DATE")
    private String date;

    @SerializedName("COUNT")
    private int count;

    //constructor
    public User_Buy(int user_id, int book_id, String date, int count)
    {
        this.user_id = user_id;
        this.book_id = book_id;
        this.date = date;
        this.count = count;
    }

    //setter
    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public void setBook_id(int book_id)
    {
        this.book_id = book_id;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    //getter
    public int getId()
    {
        return id;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public int getBook_id()
    {
        return book_id;
    }

    public String getDate()
    {
        return date;
    }

    public int getCount()
    {
        return count;
    }

    //to string
    @Override
    public String toString()
    {
        return "User_Buy{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", date='" + date + '\'' +
                ", count=" + count +
                '}';
    }
}
