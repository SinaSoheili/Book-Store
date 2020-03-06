package ir.sinasoheili.bookstore.MODEL;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Book implements Serializable
{
    @SerializedName("ID")
    private int id;

    @SerializedName("NAME")
    private String name;

    @SerializedName("AUTOR_NAME")
    private String autor_name;

    @SerializedName("PRINT_YEAR")
    private int print_year;

    @SerializedName("PUBLISHER")
    private String publisher;

    @SerializedName("SUMMERY")
    private String summery;

    @SerializedName("SHABAK")
    private String shabak;

    @SerializedName("PRICE")
    private float price;

    @SerializedName("TRANSLATOR")
    private String translator;

    @SerializedName("GROUP_NAME")
    private String group_name;

    @SerializedName("PAGE_COUNT")
    private int page_count;

    @SerializedName("FRONT_PIC")
    private String front_pic;

    @SerializedName("BACK_PIC")
    private String back_pic;

    @SerializedName("AVAILABLE_COUNT")
    private int available_count;

    @SerializedName("DISCOUNT_CODE")
    private String discount_code;

    @SerializedName("LIKE_COUNT")
    private int like_count;

    //constructor
    public Book(int id , String name , float price , String front_pic , String discount_code)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.front_pic = front_pic;
        this.discount_code = discount_code;
    }

    public Book(int id, String name, String autor_name, int print_year, String publisher, String summery, String shabak, float price, String translator, String group_name, int page_count, String front_pic, String back_pic, int available_count, String discount_code, int like_count)
    {
        this.id = id;
        this.name = name;
        this.autor_name = autor_name;
        this.print_year = print_year;
        this.publisher = publisher;
        this.summery = summery;
        this.shabak = shabak;
        this.price = price;
        this.translator = translator;
        this.group_name = group_name;
        this.page_count = page_count;
        this.front_pic = front_pic;
        this.back_pic = back_pic;
        this.available_count = available_count;
        this.discount_code = discount_code;
        this.like_count = like_count;
    }

    //setter
    public void setName(String name)
    {
        this.name = name;
    }

    public void setAutor_name(String autor_name)
    {
        this.autor_name = autor_name;
    }

    public void setPrint_year(int print_year)
    {
        this.print_year = print_year;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public void setSummery(String summery)
    {
        this.summery = summery;
    }

    public void setShabak(String shabak)
    {
        this.shabak = shabak;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public void setTranslator(String translator)
    {
        this.translator = translator;
    }

    public void setGroup_name(String group_name)
    {
        this.group_name = group_name;
    }

    public void setPage_count(int page_count)
    {
        this.page_count = page_count;
    }

    public void setFront_pic(String front_pic)
    {
        this.front_pic = front_pic;
    }

    public void setBack_pic(String back_pic)
    {
        this.back_pic = back_pic;
    }

    public void setAvailable_count(int available_count)
    {
        this.available_count = available_count;
    }

    public void setDiscount_code(String discount_code)
    {
        this.discount_code = discount_code;
    }

    public void setLike_count(int like_count)
    {
        this.like_count = like_count;
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

    public String getAutor_name()
    {
        return autor_name;
    }

    public int getPrint_year()
    {
        return print_year;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public String getSummery()
    {
        return summery;
    }

    public String getShabak()
    {
        return shabak;
    }

    public float getPrice()
    {
        return price;
    }

    public String getTranslator()
    {
        return translator;
    }

    public String getGroup_name()
    {
        return group_name;
    }

    public int getPage_count()
    {
        return page_count;
    }

    public String getFront_pic()
    {
        return front_pic;
    }

    public String getBack_pic()
    {
        return back_pic;
    }

    public int getAvailable_count()
    {
        return available_count;
    }

    public String getDiscount_code()
    {
        return discount_code;
    }

    public int getLike_count()
    {
        return like_count;
    }

    //to string
    @Override
    public String toString()
    {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", autor_name='" + autor_name + '\'' +
                ", print_year=" + print_year +
                ", publisher='" + publisher + '\'' +
                ", summery='" + summery + '\'' +
                ", shabak='" + shabak + '\'' +
                ", price=" + price +
                ", translator='" + translator + '\'' +
                ", group_name='" + group_name + '\'' +
                ", page_count=" + page_count +
                ", front_pic='" + front_pic + '\'' +
                ", back_pic='" + back_pic + '\'' +
                ", available_count=" + available_count +
                ", discount_code='" + discount_code + '\'' +
                ", like_count=" + like_count +
                '}';
    }
}
