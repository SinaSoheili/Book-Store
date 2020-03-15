package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.PRESENTER.Image_API;
import ir.sinasoheili.bookstore.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecyclerView_Adapter_HomePage extends RecyclerView.Adapter<RecyclerView_Adapter_HomePage.ViewHolder_RecyclerView_HomePage>
{
    private ArrayList<Book> all_book;
    private Context context;
    private Book_Item_Click_Listener listener;
    private LruCache<Integer , Bitmap> cache;

    public RecyclerView_Adapter_HomePage(Context context , ArrayList<Book> all_book , Book_Item_Click_Listener listener)
    {
        this.context = context;
        this.all_book = all_book;
        this.listener = listener;
        this.cache = new LruCache<>((int)(Runtime.getRuntime().freeMemory() / 12));
    }

    @NonNull
    @Override
    public ViewHolder_RecyclerView_HomePage onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_view_item_home_page , null , false);
        ViewHolder_RecyclerView_HomePage viewholder = new ViewHolder_RecyclerView_HomePage(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_RecyclerView_HomePage holder, int position)
    {
        holder.fill(all_book.get(position) , listener);
    }

    @Override
    public int getItemCount()
    {
        return all_book.size();
    }

    public class ViewHolder_RecyclerView_HomePage extends RecyclerView.ViewHolder
    {
        private CardView root_view;
        private ImageView iv_book;
        private TextView tv_book_title , tv_book_autor;
        private Retrofit retrofit;
        private Image_API api;

        public ViewHolder_RecyclerView_HomePage(@NonNull View itemView)
        {
            super(itemView);
            root_view = itemView.findViewById(R.id.cv_item_recyclerview_homepage);
            iv_book = itemView.findViewById(R.id.iv_item_recyclerview_homepage);
            tv_book_title = itemView.findViewById(R.id.tv_book_title_item_recyclerview_homepage);
            tv_book_autor = itemView.findViewById(R.id.tv_book_autor_item_recyclerview_homepage);

            retrofit = new Retrofit.Builder().baseUrl(Image_API.base_url).build();
            api = retrofit.create(Image_API.class);
        }

        public void fill(final Book book , final Book_Item_Click_Listener listener)
        {
            this.root_view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    listener.OnClick(book);
                }
            });

            if(book.getFront_pic() != null)
            {
                Bitmap bitmap = null;
                if((bitmap = cache.get(book.getId())) != null)
                {
                    iv_book.setImageBitmap(bitmap);
                }
                else
                {
                    Call<ResponseBody> call = api.get_image(Image_API.folder_url + book.getFront_pic());
                    call.enqueue(new Callback<ResponseBody>()
                    {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                        {
                            InputStream is = response.body().byteStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            iv_book.setImageBitmap(bitmap);
                            cache.put(book.getId() , bitmap);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t)
                        {
                            iv_book.setImageResource(R.drawable.book);
                        }
                    });
                }
            }
            else
            {
                iv_book.setImageResource(R.drawable.book);
            }

            iv_book.setClipToOutline(true);
            this.tv_book_title.setText(book.getName());
            this.tv_book_autor.setText(book.getAutor_name());
        }
    }
}
