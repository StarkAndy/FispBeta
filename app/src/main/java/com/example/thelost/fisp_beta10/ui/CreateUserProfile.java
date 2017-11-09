package com.example.thelost.fisp_beta10.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thelost.fisp_beta10.R;
import com.example.thelost.fisp_beta10.utils.GPSTracker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public class CreateUserProfile extends AppCompatActivity implements View.OnClickListener {

    ImageView mImageFirst, mImageSecond, mImageThird, mImageFourth;

    EditText etShortInformation,
            etRoomFacility,
            etRoomAvailability,
            etRoomCost,
            etShortDescription,
            etNegotation,
            etRoomType,
            etLookingFor,
            etPhoneNumber,
            etAddressOne,
            etAddressSecond,
            etAddressCity,
            etAddressState,
            etAddressZipCode,
            etAddressCountry;


    TextView mTvRoomUpload;

    // GPSTracker class
    GPSTracker gps;

    LocationListener locationListener;

    LocationManager locationManager;

    private final int IMAGE_FIRST_RESULT = 1;
    private final int IMAGE_SECOND_RESULT = 2;
    private final int IMAGE_THIRD_RESULT = 3;
    private final int IMAGE_FOURTH_RESULT = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_user_entry);

        //calling function initialize (initialize all the variables)
        initialize();
        //add click listner to the views
        addClickListner();

        //calling function locacition listener which initialize locationListener
        locationListenerInitialization();


        //Calling function checkPermission which ask permission to checck permission
        checkSelfPermission();

    }

    //function initialize all the view used in the activity
    public void initialize() {
        mImageFirst = (ImageView) findViewById(R.id.user_first_image);
        mImageSecond = (ImageView) findViewById(R.id.user_second_image);
        mImageThird = (ImageView) findViewById(R.id.user_third_image);
        mImageFourth = (ImageView) findViewById(R.id.user_fourth_image);


        //initializing the edittexts:
        etShortInformation = (EditText) findViewById(R.id.et_room_information);
        etRoomAvailability = (EditText) findViewById(R.id.et_room_availability);
        etRoomFacility = (EditText) findViewById(R.id.et_room_facilty);
        etRoomCost = (EditText) findViewById(R.id.et_room_cost);
        etShortDescription = (EditText) findViewById(R.id.et_room_description);
        etNegotation = (EditText) findViewById(R.id.et_create_room_negotation);
        etRoomType = (EditText) findViewById(R.id.et_room_type);
        etLookingFor = (EditText) findViewById(R.id.et_room_lookingFor);
        etPhoneNumber = (EditText) findViewById(R.id.et_room_phone);
        etAddressOne = (EditText) findViewById(R.id.et_room_addressone);
        etAddressSecond = (EditText) findViewById(R.id.et_room_addresssecond);
        etAddressCity = (EditText) findViewById(R.id.et_room_city);
        etAddressState = (EditText) findViewById(R.id.et_room_state);
        etAddressCountry = (EditText) findViewById(R.id.et_room_country);
        etAddressZipCode = (EditText) findViewById(R.id.et_room_zipcode);

        //initializing textview
        mTvRoomUpload = (TextView) findViewById(R.id.tv_room_upload);


        //initialize location managers (used..getting location information)
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }


    //function set's click listner into the imageview
    public void addClickListner() {
      /*  //adding click listener to the imgave view to select the image from gallery
        mImageFirst.setOnClickListener(this);
        mImageSecond.setOnClickListener(this);
        mImageThird.setOnClickListener(this);
        mImageFourth.setOnClickListener(this);*/

        //setting click listener to textview
        mTvRoomUpload.setOnClickListener(this);

    }

    //Called when user click the view
    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        switch (viewId) {

            case R.id.user_first_image:


                createIntentToPickFromGallery(IMAGE_FIRST_RESULT);

                break;

            case R.id.user_second_image:

                createIntentToPickFromGallery(IMAGE_SECOND_RESULT);
                break;

            case R.id.user_third_image:
                createIntentToPickFromGallery(IMAGE_THIRD_RESULT);
                break;

            case R.id.user_fourth_image:
                createIntentToPickFromGallery(IMAGE_FOURTH_RESULT);
                break;


            //Case for the text view uploading
            case R.id.tv_room_upload:
                uploadDataToDatbase();
                break;

        }


    }

    //function load gallery and help user to selct picture picture image
    public void createIntentToPickFromGallery(int imageResult) {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, imageResult);


    }

    //function insert data to the database
    public void uploadDataToDatbase() {

        //gettting value from the edittext

        if(etShortInformation.getText().length() !=0 && etRoomFacility.getText().length() !=0 &&
                etRoomAvailability.getText().length() !=0&&
                etRoomCost.getText().length()!=0  &&
                etShortDescription.getText().length() !=0 &&
                etNegotation.getText().length()!=0 &&
                etRoomType.getText().length()!=0 &&
                etLookingFor.getText().length()!=0 &&
                etPhoneNumber.getText().length()!=0 &&
                etAddressOne.getText().length()!=0 &&
                etAddressSecond.getText().length()!=0 &&
                etAddressCity.getText().length()!=0 &&
                etAddressState.getText().length()!=0 &&
                etAddressState.getText().length()!=0 &&
                etAddressZipCode.getText().length()!=0 &&
                etAddressCountry.getText().length()!=0
                ){

            String shortInformation = etShortInformation.getText().toString();
            String facility = etRoomFacility.getText().toString();
            String availableRoom = etRoomAvailability.getText().toString();
            String roomCost = etRoomCost.getText().toString();
            String shortDescription = etShortDescription.getText().toString();
            String negotation = etNegotation.getText().toString();
            String roomType = etRoomType.getText().toString();
            String lookingFor = etLookingFor.getText().toString();
            String phoneNumber = etPhoneNumber.getText().toString();
            String addessOne = etAddressOne.getText().toString();
            String addressSecond = etAddressSecond.getText().toString();
            String addressCity = etAddressCity.getText().toString();
            String addressState = etAddressState.getText().toString();
            String addressZipCode = etAddressZipCode.getText().toString();
            String addressCountry = etAddressCountry.getText().toString();

            String fullAdress=etAddressOne+addressSecond+addressCity+addressState+addressZipCode+addressCountry;

        }else{

            Toast.makeText(CreateUserProfile.this,"Some Value are null",Toast.LENGTH_LONG).show();
        }



    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {

            final Uri imageUri = data.getData();
            final InputStream imageStream;


            if (reqCode == IMAGE_FIRST_RESULT) {


                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //calling function to set the image to imageview
                    setImageToImageView(mImageFirst, selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (reqCode == IMAGE_SECOND_RESULT) {


                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //calling function to set the image to imageview
                    setImageToImageView(mImageSecond, selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (reqCode == IMAGE_THIRD_RESULT) {

                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //calling function to set the image to imageview
                    setImageToImageView(mImageThird, selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (reqCode == IMAGE_FOURTH_RESULT) {

                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //calling function to set the image to imageview
                    setImageToImageView(mImageFourth, selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }


        } else {
            Toast.makeText(CreateUserProfile.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }


    public void setImageToImageView(ImageView mImageView, Bitmap selctedImage) {

        ImageView mImag = mImageView;

        mImag.setImageBitmap(selctedImage);


    }

    //Checking self permission
    //Check self permission ask permission from user if not accepted
    public void checkSelfPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location locationFromGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location locationFromNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


            //if Gps location is not null
            if (locationFromGps != null) {
                //Calling function getLocation for gettting location
                getLocation(locationFromGps);
                return;

            } else if (locationFromNetwork != null) {
                //Calling function getLocation for gettting location
                getLocation(locationFromNetwork);
                return;
            } else {
                Toast.makeText(CreateUserProfile.this, "Sorry Unable to get Location", Toast.LENGTH_SHORT).show();
            }

        }
    }

    //function get latitude and longitude from  location
    public void getLocation(Location location) {

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        //Calling function display latitude and longitude to textView
        getAddressFromLatAndLng(latitude, longitude);

    }


    //Function initialize locationListener
    public void locationListenerInitialization() {

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


    }

    //Function get converts latitude and longitude from latitude and longitude

    public void getAddressFromLatAndLng(double latitude, double longitude) {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();

          /*  EditText userLocation=(EditText) findViewById(R.id.user_location);

            userLocation.setText(city+ " " +state+" "+country+" "+postalCode+ " "+knownName);
*/


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                return;
            }


        }
        ;


    }


}


