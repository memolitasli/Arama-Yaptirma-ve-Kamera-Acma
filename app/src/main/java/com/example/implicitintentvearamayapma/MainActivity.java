package com.example.implicitintentvearamayapma;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * implicint intent benim yazdığım program ve android arasında iletişim kurma işlemi olarak adlandırılabilir
 * örnek olarak ben instagram a fotoğraf yükleyeceğim zaman instagram benim telefonumun kamerasına erişiyor
 * fotoğraf çekiyor ve o fotoğrafı instagrama sistemden yüklüyor
 * şimdi yapacağım uygulama ile buttona basıldığı zaman arama işlemi gerçekleştirecek
 *
 * Hepsinden önce bir program kurulurken bu program senin kamerana konumuna erişecek bildirimini vermem gerekiyor
 * Bu bildirimi verebilmek için manifest in içinde yapmam gerekiyor
 * */
public class MainActivity extends AppCompatActivity {
    ImageView iv;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        Bitmap bm = (Bitmap) b.get("data");
        iv.setImageBitmap(bm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.imageView2);
        Button b1 = (Button)findViewById(R.id.button); //arama yapar

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL); // bu şekilde buttona basılırsa arama ekranına geçiyor
                i.setData(Uri.parse("tel:112")); // bu sayede de otomatik olarak aranacak numarayı ekrana ekliyor
                startActivity(i);
            }
        });
 /*
 * yapacağım işlem şu ikinci buttona bastığım zaman kamera açılacak çekilen resmi de imageview içerisine yerleştirsin*/
        Button b2= (Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // çekilen fotoğrafı geri alabilmek için yukarıda bir metodu override edicem
               startActivityForResult(i,1); // start activity den farkı activity i başlatıyor ve o activity den bir sonuç bekliyor
                // ve o dönecek olan sonucu da yukarıda override ettiğim onactivityresult metodu belirliyor


            }
        });
    }
}