package com.radiant.rpl.testa.ExamSection;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.radiant.rpl.testa.Common.CommonUtils;
import com.radiant.rpl.testa.Initials.StudenAtten;
import com.radiant.rpl.testa.LocalDB.DbAutoSave;
import com.radiant.rpl.testa.Initials.MyNetwork;
import com.radiant.rpl.testa.Registration.SavephotoComparison;
import com.radiant.rpl.testa.Initials.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import dmax.dialog.SpotsDialog;
import radiant.rpl.radiantrpl.R;

public class TestQuestion extends AppCompatActivity {

    FragmentParent fragmentParent;
    TextView textView,finalSubmitbutton;
    Cursor cursor,cursor11;
    Toolbar t1;
    RelativeLayout len;
    ImageButton imgRight;
    GridView drawer_Right;
    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    Context con=this;
    CustomAdapter cl1,cl2;
    String  encodedd;

    //camera by pk

    private static final String TAG = "AndroidCameraApi";
    private Button takePictureButton;
    TextureView textureView;
    public static int i = 0;
    int c=0;
    String screenshot1;
    String strDate;


    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 270);
        ORIENTATIONS.append(Surface.ROTATION_90, 180);
        ORIENTATIONS.append(Surface.ROTATION_180, 90);
        ORIENTATIONS.append(Surface.ROTATION_270, 0);

    }



    private String cameraId;
    protected CameraDevice cameraDevice;
    protected CameraCaptureSession cameraCaptureSessions;
    protected CaptureRequest captureRequest;
    protected CaptureRequest.Builder captureRequestBuilder;
    private Size imageDimension;
    private ImageReader imageReader;
    private File file;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private boolean mFlashSupported;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    private NotificationHelper mNotificationHelper;
    private android.app.AlertDialog progressDialog;

    long START_TIME_IN_MILLIS;
    private static final long START_TIME_IN_MILLISR = 00000;
    private android.os.CountDownTimer CountDownTimer;
    private boolean TimerRunning;
    private long TimeLeftInMillis;
    private long EndTime;
    RelativeLayout parentLayout;
    SessionManager sessionManager;
    ArrayList<String> studentidlist;
    ArrayList<String> questioniddd;
    ArrayList<String> answeredoptionn;
    private static final int REQ_CODE_CAMERA_PERMISSION = 1253;
    SharedPreferences sp,sp1;
    String aaa,bbb,ccc;
    DbAutoSave dbAutoSave;
    SQLiteDatabase mDatabase;
    ArrayList<SetterGetter> employeeList;
    ArrayList<String> aa=new ArrayList<>();
    ArrayList<Integer> qnooo=new ArrayList<>();
    ArrayList<String> qnooo1=new ArrayList<>();
    ArrayList<String> bb=new ArrayList<>();
    ArrayList<String> queid=new ArrayList<>();
    ArrayList<String[]> options=new ArrayList<>();
    ArrayList<String> options1=new ArrayList<>();
    ArrayList<String> options2=new ArrayList<>();
    ArrayList<String> options3=new ArrayList<>();
    ArrayList<String> options4=new ArrayList<>();
    ArrayList<String> statuss=new ArrayList<>();
    ArrayList<String> questatus=new ArrayList<>();

    ArrayList<String> Que_image=new ArrayList<>();
    ArrayList<String> Que_option1=new ArrayList<>();
    ArrayList<String> Que_option2=new ArrayList<>();
    ArrayList<String> Que_option3=new ArrayList<>();
    ArrayList<String> Que_option4=new ArrayList<>();
    String value,batchvalue,studentid;

    SetterGetter setterGetter;
    String[] title = {
            "New Delhi",
            "Mumbai",
            "Bangalore",
            "Ahmedabad",
    };
    String[] title1 = {
            "Narendra Modi",
            "Jawahar Lal Nehru",
            "Karamchand Ghandhi",
            "Anil Kapoor",
    };
    String[] title2 = {
            "Shiela Dixit",
            "Arvind Kejriwal",
            "Manish Shishodia",
            "Rajat Sharma",
    };
    String[] title3 = {
            "Imraan Khan",
            "Kapil Dev",
            "Mahendra Singh Dhoni",
            "Ravindra Jadeja",
    };
    String[] title4 = {
            "Ved Vyas",
            "TulsiDas",
            "Ramanand sagar",
            "Vishwamitra",
    };
    int arraysize;
    long timee;
    boolean alreadyExecuted=false;
    boolean alreadyExecuted_timer=false;
    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};
    int perm,perm1;
    String stringLatitude2,stringLongitude2;
    String exam_statuss;
    String jsonInString;
    int que_count;
    String stringLatitude1,stringLongitude1;
    final Timer timer = new Timer(false);
    long theory_time,practical_time;
    private long aab1;
    private String proctoring_status;
    private String proctoring_comparison_status;
    private String UserMobile;
    private String Name;
    private String student_type;
    private String Useridd;




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_question);
        getIDs();
        t1=findViewById(R.id.toolbar);
        setSupportActionBar(t1);
        alreadyExecuted_timer=false;
        progressDialog = new SpotsDialog(TestQuestion.this, R.style.Custom);
        sp=getSharedPreferences("mypref", MODE_PRIVATE);
        sp1=getSharedPreferences("mypreff", MODE_PRIVATE);
        batchvalue=sp.getString("batchid","");
        stringLatitude1=sp.getString("lat","");
        stringLongitude1=sp.getString("long","");
        stringLongitude1=sp.getString("long","");
        stringLatitude2=sp.getString("lat","");
        stringLongitude2=sp.getString("long","");
        System.out.println("lat and long in test q are"+stringLatitude2+" "+stringLongitude2);
        exam_statuss=sp1.getString("examstatus","");
        theory_time=sp1.getLong("theorytime",0);
        practical_time=sp1.getLong("practicaltime",0);
        if (!alreadyExecuted_timer){
            START_TIME_IN_MILLIS=theory_time;
            alreadyExecuted_timer=true;
        }
        START_TIME_IN_MILLIS=theory_time;
        studentid=sp.getString("userid","");
        sessionManager = new SessionManager();
        studentidlist=new ArrayList<>();
        questioniddd=new ArrayList<>();
        answeredoptionn =new ArrayList<>();
        options.add(title);
        options.add(title1);
        options.add(title2);
        options.add(title3);
        options.add(title4);
        employeeList=new ArrayList<>();
        dbAutoSave = new DbAutoSave(getApplicationContext());
        mDatabase= openOrCreateDatabase(DbAutoSave.DATABASE_NAME, MODE_PRIVATE, null);
        setterGetter =new SetterGetter();
        mNotificationHelper = new NotificationHelper(this);


        textureView = findViewById(R.id.texture1);
        assert textureView != null;
        textureView.setSurfaceTextureListener(textureListener);


        if (sp.contains("assessorid")){
            UserMobile=sp.getString("assessorid","");
        }

        if (sp.contains("Name")) {
            Name= sp.getString("Name","");
        }

        if (sp.contains("userid")){
            Useridd=sp.getString("userid","");
        }

        if (sp.contains("student_type")){
           student_type=sp.getString("student_type","");
        }
        //camera change by pk






        // assert takePictureButton != null;



        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }


        perm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        perm1=ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);
        if (perm != PackageManager.PERMISSION_GRANTED || perm1 != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(permission, 7882);

        }



        len.bringToFront();
        mdrawerLayout.requestLayout();

       /* CustomAdapter.aa(new GotoQuestion() {
            @Override
            public void getposition(int a) {
                mdrawerLayout.closeDrawers();
            }
        });*/

        drawer_Right.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                if (scrollState == SCROLL_STATE_IDLE) {
                    drawer_Right.bringToFront();
                    mdrawerLayout.requestLayout();
                }

            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub

            }
        });

        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mdrawerLayout.isDrawerOpen(len)){
                    mdrawerLayout.closeDrawer(len);
                    getData();
                    if (statuss.size()>0){
                        statuss.clear();
                        getStatusdata();
                    }else {
                        getStatusdata();
                    }
                }
                else if (!mdrawerLayout.isDrawerOpen(len)){
                    mdrawerLayout.openDrawer(len);
                    getData();
                    if (statuss.size()>0){
                        statuss.clear();
                        getStatusdata();
                    }else {
                        getStatusdata();
                    }

                }
            }
        });




        FragmentParent.aa(new ShowButton() {
            @Override
            public void getData(int a) {
                if (a==1){
                    finalSubmitbutton.setVisibility(View.VISIBLE);
                } else{
                    finalSubmitbutton.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void dd(boolean b) {
                if (b){
                    mdrawerLayout.closeDrawers();
                }
            }
        });


        mdrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);





        if(!isOnline()){


            try {
                AlertDialog alertDialog = new AlertDialog.Builder(con)
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);



                            }
                        }).create();

                alertDialog.setTitle("Info");
                alertDialog.setMessage("Internet not available,Cross check your internet connectivity.");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);

                alertDialog.show();



            } catch (Exception e) { }
        }






    }





    //camera by pk method
    public void timerstop()
    {

        TimerTask timerTask=new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {

                takePicture();
                c++;


                if(c==20)
                {

                    photodelete();
                    timer.cancel();
                    //Toast.makeText(getApplicationContext(),"chal gaya",Toast.LENGTH_SHORT).show();



                }

                if(c>=0&&c<21)
                {
                    if (proctoring_status.equals("1")) {
                        SaveDetail();
                    }

                    if (proctoring_comparison_status.equals("1") && screenshot1!=null){
                    new SavephotoComparison(getApplicationContext()).PhotoApiREc1(screenshot1,Useridd, TestQuestion.this);
                    }
                }
            }};
        timer.scheduleAtFixedRate(timerTask,60000, aab1); // 1000 = 1 second.

        System.out.println(timer.purge());
    }


    public void photodelete(){

        File target = new File(Environment.getExternalStorageDirectory()+"/"+".jpg");
        Log.d("target_path", "hello" + Environment.getExternalStorageDirectory()+"/"+".jpg");
        if (target.exists() && target.isFile() && target.canWrite()) {
            target.delete();
            Log.d("d_file", "hello1" + target.getName());
        }

    }



    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //open your camera here
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                openCamera();
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            // Transform you image captured size according to the surface width and height



        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }
        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }

    };


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            //This is called when the camera is open
            Log.e(TAG, "onOpened");
            cameraDevice = camera;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                createCameraPreview();
            }
        }
        @Override
        public void onDisconnected(CameraDevice camera) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraDevice.close();
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onError(CameraDevice camera, int error) {
            Log.d("cameraerror",""+error);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cameraDevice.close();
            }
            cameraDevice = null;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    final CameraCaptureSession.CaptureCallback captureCallbackListener = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            //Toast.makeText(TestQuestion.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                createCameraPreview();
            }
        }
    };
    protected void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    protected void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void takePicture() {
        System.out.println("take picture called");
        if(null == cameraDevice) {
            Log.e(TAG, "cameraDevice is null");
            return;
        }
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
            Size[] jpegSizes = null;
            if (characteristics != null) {
                jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
            }
            int width = 140;
            int height = 140;
            if (jpegSizes != null && 0 < jpegSizes.length) {
                width = jpegSizes[0].getWidth();
                height = jpegSizes[0].getHeight();
            }
            ImageReader reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 20);

            List<Surface> outputSurfaces = new ArrayList<Surface>(20);
            outputSurfaces.add(reader.getSurface());
            outputSurfaces.add(new Surface(textureView.getSurfaceTexture()));
            final CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(reader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
            // Orientation
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));




            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            strDate = dateFormat.format(date);

            final File file = new File(Environment.getExternalStorageDirectory()+"/"+strDate+".jpg");

            ImageReader.OnImageAvailableListener readerListener = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                readerListener = new ImageReader.OnImageAvailableListener() {
                    @Override
                    public void onImageAvailable(ImageReader reader) {
                        Image image = null;
                        try {
                            image = reader.acquireNextImage();
                            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                            byte[] bytes = new byte[buffer.capacity()];
                            buffer.get(bytes);
                           // save(bytes);


                             Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            int currentBitmapWidth = 320;
                            int currentBitmapHeight = 320;
                            //input_photograph.setImageBitmap(photo);
                            int newHeight = (int) Math.floor((double) currentBitmapHeight * ((double) currentBitmapWidth / (double) currentBitmapWidth));
                            Bitmap newbitMap = Bitmap.createScaledBitmap(bitmap, currentBitmapWidth, currentBitmapHeight, true);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            newbitMap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            save(byteArray);


                            System.out.println("file size after compression is" +byteArray.length+" and before"+bytes.length);
                            screenshot1 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                            System.out.println("yyyyyyy" +screenshot1);
                            /*if(screenshot1!= null )
                            {
                                new SavephotoComparison(getApplicationContext()).
                                        PhotoApiREc1(screenshot1,UserMobile,getApplicationContext());

                            }*/


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (image != null) {
                                image.close();
                            }
                        }
                    }
                    private void save(byte[] bytes) throws IOException {
                        OutputStream output = null;
                        try {
                            output = new FileOutputStream(file);
                            output.write(bytes);
                        } finally {
                            if (null != output) {
                                output.close();
                            }
                        }
                    }
                };
            }

            reader.setOnImageAvailableListener(readerListener, mBackgroundHandler);
            final CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                    //Toast.makeText(TestQuestion.this, "Saved:" + file, Toast.LENGTH_SHORT).show();
                    createCameraPreview();
                }
            };
            cameraDevice.createCaptureSession(outputSurfaces, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    try {
                        session.capture(captureBuilder.build(), captureListener, mBackgroundHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                }
                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                }
            }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void createCameraPreview() {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            Surface surface = new Surface(texture);
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback(){
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    //The camera is already closed
                    if (null == cameraDevice) {
                        return;
                    }
                    // When the session is ready, we start displaying the preview.
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(TestQuestion.this, "Configuration change", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        Log.e(TAG, "is camera open");
        try {
            cameraId = manager.getCameraIdList()[1];
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);

            Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
            if (facing == CameraCharacteristics.LENS_FACING_BACK) {

                Log.d(TAG, "front-facing mCamera found: " + cameraId);
                return;


            }

            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
            // Add permission for camera and let user grant the permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(TestQuestion.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);

            }

            manager.openCamera(cameraId, stateCallback, null);


        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "openCamera X");
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void updatePreview() {
        if(null == cameraDevice) {
            Log.e(TAG, "updatePreview error, return");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
        }
        try {
            cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void closeCamera() {
        if (null != cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
        }
        if (null != imageReader) {
            imageReader.close();
            imageReader = null;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(TestQuestion.this, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        textureView.setVisibility(View.VISIBLE);

        Log.e(TAG, "onResume");
        startBackgroundThread();
        if (textureView.isAvailable()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                openCamera();
            }
        } else {
            textureView.setSurfaceTextureListener(textureListener);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onPause() {
        Log.e(TAG, "onPause");
        textureView.setVisibility(View.GONE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            closeCamera();


        }
        stopBackgroundThread();
        super.onPause();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }






    private void SaveDetail() {

        String serverURL = CommonUtils.url+"save_proctoring.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("sss"+response);
                    String status= jobj.getString("status");

                    //Toast.makeText(getApplicationContext(),"We have Received your query will update soon",Toast.LENGTH_LONG).show();
                    if (status.equals("1")){

                        Toast.makeText(getApplicationContext(),"Photo Captured", Toast.LENGTH_SHORT).show();
                        Log.d("Response",response);

                    }

                    /*else if (status.equals("0")){
                        Toast.makeText(getApplicationContext(),jobj.getString("msg"),Toast.LENGTH_LONG).show();
                        Log.d("Response",response);
                    }*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(getApplicationContext(), "We Encounterd an Error. Please try again later"+error, Toast.LENGTH_LONG).show();
                System.out.println("error in saving image"+error);
            }
        })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();



                if (screenshot1!=null){
                    map.put("student_image",screenshot1);
                    map.put("student_id",studentid);
                    map.put("image_time",strDate);
                    map.put("student_type",student_type);
                    

                }
                Log.d("image_file",screenshot1);
                System.out.println("sccccc" +map);



                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


















    @Override
    protected void onRestart() {
        super.onRestart();
    }




    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }




    private void getIDs() {
        fragmentParent = (FragmentParent) this.getSupportFragmentManager().findFragmentById(R.id.fragmentParent);
        View vv=findViewById(R.id.count_down_strip);
        textView=vv.findViewById(R.id.timer);
        finalSubmitbutton=vv.findViewById(R.id.finish);
        drawer_Right=findViewById(R.id.drawer_right);
        imgRight=findViewById(R.id.imgRight);
        parentLayout=findViewById(R.id.r1);
        len=findViewById(R.id.len1);
        mdrawerLayout=findViewById(R.id.activity_main1);
        mdrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        value=sp1.getString("languagev","");
        System.out.println("value issss"+value);

        SharedPreferences prefs = getSharedPreferences("prefstimer", MODE_PRIVATE);
        TimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        TimerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDownText();
        updateButtons();
        resetTimer();

        if (TimerRunning) {
            EndTime = prefs.getLong("endTime", 0);
            TimeLeftInMillis = EndTime - System.currentTimeMillis();

            if (TimeLeftInMillis < 0) {
                TimeLeftInMillis = 0;
                TimerRunning = false;
                updateCountDownText();
                updateButtons();
            } else {
                startTimer();
            }
        }
        startTimer();






        finalSubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("exam status is"+exam_statuss);
                if (exam_statuss.equals("both")) {

                    getAnswerscount();
                    timer.cancel();


                    TimerRunning = false;
                    TimeLeftInMillis = START_TIME_IN_MILLISR;




                }else if (exam_statuss.equals("theory")){
                    getTotalanswercount();

                }

            }
        });




        if(!alreadyExecuted) {
            Questionlist();
        }





    }


    public void getTotalanswercount() {

        cursor = dbAutoSave.getData(studentid);
        int aaaa = cursor.getCount();
        int bbbbb = aa.size();
        int cccc =  bbbbb;

        System.out.println("total saved answers" + aaaa);
        System.out.println("total que count" + bbbbb);
        System.out.println("total que from prev page count" + cccc);

        if (aaaa < cccc) {
            Toast.makeText(getApplicationContext(), "Please answer all the questions since all questions are mandotary.", Toast.LENGTH_LONG).show();
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(TestQuestion.this)
                    .setMessage("Schedule the test for the Final submit.")
                    .setPositiveButton("Yes ", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getalldata();



                            if (jsonInString != null) {
                                Questionlist1();
                            }else {
                                Toast.makeText(getApplicationContext(),"Trying to save",Toast.LENGTH_LONG).show();
                            }
                            sessionManager.setPreferences(getApplicationContext(), "vipin", "0");
                        }
                    }).create();


            alertDialog.show();
            TimerRunning = false;
            TimeLeftInMillis = START_TIME_IN_MILLISR;
        }
    }

    private void Questionlist1() {
        progressDialog.show();
        String serverURL = CommonUtils.url+"save_answers.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    String status = jobj.getString("status");
                    if (status.equals("1")) {
                        Toast.makeText(getApplicationContext(), "You have successfully attempted the Assessment", Toast.LENGTH_LONG).show();
                        dbAutoSave.onDelete();
                        Intent ii = new Intent(TestQuestion.this, Thankspage.class);
                        startActivity(ii);

                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        System.out.println("Error"+response);
                        //Toast.makeText(getApplicationContext(), "Error"+response, Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Error: Please try again Later", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();

                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                map.put("JSON", jsonInString);
                map.put("username",studentid);
                map.put("mobile_imei","");
                map.put("ip","");
                map.put("company_id","");
                map.put("activity","User Logout");
                map.put("lat",stringLatitude2);
                map.put("longi",stringLongitude2);
                map.put("student_type",student_type);
                System.out.println("ddd" + map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);

    }





    private void startTimer() {
        EndTime = System.currentTimeMillis() + TimeLeftInMillis;

        CountDownTimer = new CountDownTimer(TimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                TimerRunning = false;
                updateButtons();
                resetTimer();
                Context context = TestQuestion.this;

                if (! ((Activity) context).isFinishing()) {
                    //  Activity is running
                    showDialog11();

                   /* if (exam_statuss.equals("both")) {
                        showDialog11();

                    }else if (exam_statuss.equals("theory")){
                        getTotalanswercount();
                    }*/
                } else {
                    System.out.println("THeory has been attempted");
                    //  Activity has been finished
                }



            }
        }.start();

        TimerRunning = true;
        updateButtons();
    }

    private void resetTimer() {
        TimeLeftInMillis = START_TIME_IN_MILLIS;
        //TimerRunning=false;
        updateCountDownText();
        updateButtons();
    }

    private void submitTimer() {
        TimeLeftInMillis = START_TIME_IN_MILLISR;
        updateCountDownText();
        updateButtons();
        TimerRunning = false;
        CountDownTimer.cancel();
    }


    private void updateCountDownText() {
        int minutes = (int) (TimeLeftInMillis / 1000) / 60;
        int seconds = (int) (TimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textView.setText(timeLeftFormatted);
    }

    private void updateButtons() {

    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefstimer", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("millisLeft", TimeLeftInMillis);
        editor.putBoolean("timerRunning", TimerRunning);
        editor.putLong("endTime", EndTime);
        editor.apply();

        if (CountDownTimer != null) {
            CountDownTimer.cancel();
        }

        SendInNotification("Timer is Runing", (TimeLeftInMillis / 1000) / 60, (TimeLeftInMillis / 1000) % 60);



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



    public void SendInNotification(String title, long timerNotify, long timerinSec) {

        NotificationCompat.Builder nb = mNotificationHelper.getSendNotification(title, timerNotify, timerinSec);
        mNotificationHelper.getManger().notify(1, nb.build());


    }


    String FormatSeconds(float elapsed)
    {
        int d = (int)(elapsed * 100.0f);
        int minutes = d / (60 * 100);
        int seconds = (d % (60 * 100)) / 100;
        int hundredths = d % 100;
        return String.format("{0:00}:{1:00}.{2:00}", minutes, seconds, hundredths);
    }

    private void Questionlist() {

        progressDialog.show();
        String serverURL = CommonUtils.url+"batch_questions.php";

        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    alreadyExecuted=false;
                    JSONObject jobj = new JSONObject(response);
                    aab1=jobj.getLong("proctoring_time");
                    proctoring_status= jobj.getString("proctoring_status");
                    proctoring_comparison_status= jobj.getString("proctoring_comparison_status");
                    String status= jobj.getString("status");
                    float aab=jobj.getLong("theory_time");
                    System.out.println("dddd"+aab1);
                    if (status.equals("1")){
                        alreadyExecuted = true;
                        JSONArray jsonArray=jobj.getJSONArray("theory_questions");
                        arraysize=jsonArray.length();
                        timee=arraysize*60*1000;
                        System.out.println("bsdfsdf"+timee+"   "+START_TIME_IN_MILLIS);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject c = jsonArray.getJSONObject(i);
                            if (qnooo.size()<=jsonArray.length()-1){qnooo.add(i+1);}
                            if (qnooo1.size()<=jsonArray.length()-1){ qnooo1.add(Integer.toString(i+1));
                            }
                            if (aa.size()<=jsonArray.length()-1){aa.add(c.getString("question_id"));}
                            if (bb.size()<=jsonArray.length()-1){bb.add(c.getString("question"));}
                            if (queid.size()<=jsonArray.length()-1){ queid.add(c.getString("question_id"));}
                            if (options1.size()<=jsonArray.length()-1){options1.add(c.getString("option1"));}
                            if (options2.size()<=jsonArray.length()-1){options2.add(c.getString("option2"));}
                            if (options3.size()<=jsonArray.length()-1){ options3.add(c.getString("option3"));}
                            if (options4.size()<=jsonArray.length()-1){ options4.add(c.getString("option4"));}

                            if (Que_image.size()<=jsonArray.length()-1){Que_image.add(c.getString("question_image"));}
                            if (Que_option1.size()<=jsonArray.length()-1){Que_option1.add(c.getString("option1_image"));}
                            if (Que_option2.size()<=jsonArray.length()-1){Que_option2.add(c.getString("option2_image"));}
                            if (Que_option3.size()<=jsonArray.length()-1){Que_option3.add(c.getString("option3_image"));}
                            if (Que_option4.size()<=jsonArray.length()-1){Que_option4.add(c.getString("option4_image"));}



                        }
                        System.out.println("bbbb"+aa);
                        for (int ii=0;ii<=aa.size()-1;ii++) {
                            if (dbAutoSave.getDataOfSingleClientstatus(qnooo1.get(ii))==null){
                                if (ii == 0){
                                    dbAutoSave.insertDataunanswered(studentid,qnooo1.get(ii),"0");
                                }else {
                                    dbAutoSave.insertDataunanswered(studentid,qnooo1.get(ii),"3");
                                }

                            }
                            fragmentParent.addPage(aa.get(ii) + "", bb.get(ii), qnooo.get(ii), options1.get(ii), options2.get(ii),
                                    options3.get(ii), options4.get(ii),Que_image.get(ii)
                                    ,Que_option1.get(ii),Que_option2.get(ii),Que_option3.get(ii),Que_option4.get(ii));
                        }




                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                timerstop();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Error: Please try again Later", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();

                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                map.put("batch_id", batchvalue);
                map.put("language", value);
                map.put("username",studentid);
                map.put("ip","");
                map.put("mobile_imei","");
                map.put("company_id","");
                map.put("activity","Start Exam");
                map.put("lat",stringLatitude2);
                map.put("longi",stringLongitude2);
                map.put("student_type",student_type);
                System.out.println("ddd"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    public void getAnswerscount(){

        cursor=dbAutoSave.getData(studentid);
        int aaaa=cursor.getCount();
        int bbbb=aa.size();

        System.out.println("total saved answers"+aaaa);
        System.out.println("total que count"+bbbb);

        if (aaaa<bbbb){
            Toast.makeText(getApplicationContext(),"Please answer all the questions since all questions are mandotary.",Toast.LENGTH_LONG).show();
        }
        else {
            showDialog();
        }
    }


    public void getalldata() {
        cursor = dbAutoSave.getData(studentid);
        ArrayList<SetterGetter> dataList = new ArrayList<SetterGetter>();
        String batch_id = batchvalue;
        long theory_time = (TimeLeftInMillis / 1000) % 60;
        long practical_time = (TimeLeftInMillis / 1000) % 60;
        if (cursor.getCount() > 0) {
            if (cursor != null) {
                cursor.moveToFirst();

                do {
                    SetterGetter data = new SetterGetter();
                    data.student_id = cursor.getString(1);
                    data.que_id = cursor.getString(2);
                    data.selected_answer = cursor.getString(3);

                    questioniddd.add(bbb);
                    answeredoptionn.add(ccc);
                    dataList.add(data);

                } while (cursor.moveToNext());
                Datalist listOfData = new Datalist();
                listOfData.dataList = dataList;
                listOfData.batch_id = batch_id;
                listOfData.student_type=student_type;
                Gson gson = new Gson();
                jsonInString = gson.toJson(listOfData); // Here you go!
                System.out.println("aasddd" + jsonInString);
                cursor.close();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Questions answered", Toast.LENGTH_LONG).show();
        }
    }





    public void getStatusdata(){
        cursor11=dbAutoSave.getData1(studentid);
        if (cursor11.getCount()>0){
            if (cursor11 != null) {
                cursor11.moveToFirst();

                do {
                    aaa = cursor11.getString(3);
                    bbb = cursor11.getString(2);
                    // Add into the ArrayList here

                    statuss.add(aaa);
                    questatus.add(bbb);
                    System.out.println("aaaabbb" + statuss);
                } while (cursor11.moveToNext());

                cursor11.close();
            }
        }else{

        }
    }

    public void getData(){
        cl1 = new CustomAdapter(aa, con, statuss,questatus,qnooo);
        cl2 = new CustomAdapter(aa, con, statuss,questatus,qnooo);
        drawer_Right.setAdapter(cl1);
    }



    public void showDialog11() {


        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setMessage("Your time is over.Press Yes to Schedule the test for the Final submit.")
                .setCancelable(false)
                .setPositiveButton("Yes And proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //saveLog(studentid,"","Start Viva Exam",stringLatitude2,stringLongitude2,"");

                        Intent ii = new Intent(TestQuestion.this, Testviva.class);
                        Bundle b=new Bundle();
                        b.putString("selectedva",value);
                        b.putInt("que_count",aa.size());
                        ii.putExtras(b);
                        startActivity(ii);
                        TimerRunning = false;

                        finish();

                    }
                }).create();


        alertDialog1.show();
        //
        TimerRunning = false;
        TimeLeftInMillis = START_TIME_IN_MILLISR;



    }

    public void showDialog() {


        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("For Moving onto the next section click  yes and proceed.")
                .setPositiveButton("Yes And proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //saveLog(studentid,"","Start Viva Exam",stringLatitude2,stringLongitude2,"");
                        Intent ii = new Intent(TestQuestion.this, Testviva.class);
                        Bundle b=new Bundle();
                        b.putInt("que_count",aa.size());
                        b.putString("selectedva",value);
                        ii.putExtras(b);
                        startActivity(ii);
                        finish();


                    }
                }).create();

        TimerRunning = false;
        TimeLeftInMillis = START_TIME_IN_MILLISR;

        alertDialog.show();



    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("The exam will continue and Timer will keep running.Are you sure you want to exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        moveTaskToBack(true);
                        //finish();

                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }

    private void saveLog(final String fnamee, final String ip, final String activity, final String lat, final String longi,final String cmpid) {
        String serverURL = CommonUtils.url+"save_logs.php";


        StringRequest request = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println("sss"+response);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Saving the details", Toast.LENGTH_LONG).show();
                System.out.println("aa"+error);
            }
        })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                super.getHeaders();
                Map<String, String> map = new HashMap<>();
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                super.getParams();
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                map.put("username",fnamee);
                map.put("ip",ip);
                map.put("company_id",cmpid);
                map.put("activity",activity);
                map.put("lat",lat);
                map.put("longi",longi);
                map.put("student_type",student_type);
                System.out.println("map in test questions is"+map);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(20000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyNetwork.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
