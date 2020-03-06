package ir.sinasoheili.bookstore.VIEW;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.R;

public class Book_Content extends AppCompatActivity
{
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__content);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            book = (Book) bundle.get("BOOK");
            Log.i("tag" , book.getName());
        }
    }
}
