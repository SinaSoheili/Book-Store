package ir.sinasoheili.bookstore.MODEL;

import com.google.gson.annotations.SerializedName;

public class User
{
    @SerializedName("ID")
    private int id;

    @SerializedName("NAME")
    private String name;

    @SerializedName("FAMILY_NAME")
    private String family_name;

    @SerializedName("EMAIL")
    private String email;

    @SerializedName("PHONE")
    private String phone;

    @SerializedName("AVATAR")
    private String avatar;

    //constructor
    public User(String name , String email , String phone)
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(int id , String name , String email , String phone)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(int id , String name , String family_name , String email , String phone , String avatar)
    {
        this.id = id;
        this.name = name;
        this.family_name = family_name;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    //setter
    public void setName(String name)
    {
        this.name = name;
    }

    public void setFamily_name(String family_name)
    {
        this.family_name = family_name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    //getter
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getFamily_name()
    {
        return family_name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getAvatar()
    {
        return avatar;
    }

    //to string
    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
