
package com.upohtech.callumthursby.mealmate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;


public class captureImage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);
        setTitle("Capture my Creation");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_capture_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    private Uri pictureUri; // Image holder

    //Context context = getApplicationContext();
    //CharSequence text = "Hello toast!";
    int duration = Toast.LENGTH_SHORT;




    // Access the camera function from the capture button - this section also contains code from youtube video
    public void cameraIntent (View view){
        Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Specify the Save Location
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        // Specify the file name
        String pictureName = getPictureName();
        // Send the image to the correct directory with the correct image name
        File imageFile = new File(pictureDirectory,pictureName);
        // Converts format
        //Uri pictureUri = Uri.fromFile(imageFile);
        pictureUri = Uri.fromFile(imageFile);

        captureImage.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
        startActivityForResult(captureImage, REQUEST_IMAGE_CAPTURE);

    }

    // Following code adds the image to the gallery once called - Code from developers.Android website
    private void galleryAddPic(){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(pictureUri);
        this.sendBroadcast(mediaScanIntent);
        Toast toastSave = Toast.makeText(getApplicationContext(), "File has be saved and added to the gallery", duration);
       toastSave.show();
    }


    // Following code helps with the Null Exp, this section contains code from the androidhive article
    // The NullPointerExpection is caused when the file is sent to storage and the variables are null,
    // saves and instance of the image and then resets the variable with the image in order for it to be used.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", pictureUri );
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        pictureUri = savedInstanceState.getParcelable("file_uri");
    }



//
//    if (data.exists()) {
//        Toast toastGood = Toast.makeText(getApplicationContext(), "File has Saved", duration);
//        toastGood.show();
//    }
//
//    else{
//        Toast toastBad = Toast.makeText(getApplicationContext(), "File not save, please try again", duration);
//        toastBad.show();
//    }

    //Method which gets the picture name - code from youtube video
    private String getPictureName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd_HHmmss");
        String timeStamp = sdf.format(new Date());
        return "MealMateImage" + timeStamp + ".jpg";
    }






    //Once an image has been captured it will be displayed ina Image view widget - bits of code form androidhive article
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){


            //bitmap factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            //Downsize the image if it is to large
            options.inSampleSize = 8;

            final Bitmap imageBitmap = BitmapFactory.decodeFile(pictureUri.getPath(),options);

           // Bundle extras = data.getExtras();
           // Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView)findViewById(R.id.imageViewCapture);
            mImageView.setImageBitmap(imageBitmap);

            //Calls the galleryAddPic method which adds a picture to the gallery
            galleryAddPic();
        }
    }

}
