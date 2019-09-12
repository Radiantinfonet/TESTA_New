package com.radiant.rpl.testa.EyeBlink;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.LargestFaceFocusingProcessor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import radiant.rpl.radiantrpl.R;

public class Eye_blinkActivity extends Activity {

    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    private SurfaceView SurView;
    private SurfaceHolder camHolder;
    private boolean previewRunning;
    final Context context = this;
    public static Camera camera = null;
    public static boolean previewing = false;
    FaceDetector detector;
    private CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
    Camera.ShutterCallback myShutterCallback;
    Camera.PictureCallback myPictureCallback_RAW;
    Camera.PictureCallback myPictureCallback_JPG;
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 270);
        ORIENTATIONS.append(Surface.ROTATION_90, 180);
        ORIENTATIONS.append(Surface.ROTATION_180, 90);
        ORIENTATIONS.append(Surface.ROTATION_270, 0);

    }
    SurfaceHolder surfaceHolder;


    public static final String PREFS_NAME = "MyPrefsFile1";
    public CheckBox dontShowAgain;
    LinearLayout liii;

    @SuppressWarnings("deprecation")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_blink);


        SurView = (SurfaceView) findViewById(R.id.sview);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceHolder = SurView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);



        camHolder = SurView.getHolder();
        //camHolder.addCallback(this);
        camHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        detector = new FaceDetector.Builder(this)
                .setProminentFaceOnly(true) // optimize for single, relatively large face
                .setTrackingEnabled(true) // enable face tracking
                .setClassificationType(/* eyes open and smile */ FaceDetector.ALL_CLASSIFICATIONS)
                .setMode(FaceDetector.ACCURATE_MODE) // for one face this is OK
                .build();

        if (!detector.isOperational()) {
            Log.w("MainActivity", "Detector Dependencies are not yet available");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), detector)
                    .setFacing(CameraSource.CAMERA_FACING_FRONT)
                    .setRequestedFps(2.0f)
                    .setRequestedPreviewSize(320, 240)
                    .setAutoFocusEnabled(true)
                    .build();



            SurView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(Eye_blinkActivity.this,
                                    new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(SurView.getHolder());
                        detector.setProcessor(
                                new LargestFaceFocusingProcessor(detector, new GraphicFaceTracker()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                   /* if (previewRunning) {
                        camera.stopPreview();
                    }
                    Camera.Parameters camParams = camera.getParameters();
                    Camera.Size size = camParams.getSupportedPreviewSizes().get(0);
                    camParams.setPreviewSize(size.width, size.height);
                    camera.setParameters(camParams);
                    try {
                        camera.setPreviewDisplay((SurfaceHolder) SurView);
                        camera.startPreview();
                        previewRunning = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });


        }




        myShutterCallback = new Camera.ShutterCallback() {

            public void onShutter() {
                // TODO Auto-generated method stub
            }
        };

        myPictureCallback_RAW = new Camera.PictureCallback() {

            public void onPictureTaken(byte[] arg0, Camera arg1) {
                // TODO Auto-generated method stub
            }
        };

        myPictureCallback_JPG = new Camera.PictureCallback() {

            public void onPictureTaken(byte[] arg0, Camera arg1) {
                // TODO Auto-generated method stub
                System.out.println("photo taken");
                Bitmap bitmapPicture = BitmapFactory.decodeByteArray(arg0, 0, arg0.length);

                Bitmap correctBmp = Bitmap.createBitmap(bitmapPicture, 0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight(), null, true);

            }
        };
        /*ImageView gifImageView = new ImageView(context);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gifImageView);
        AlertDialog.Builder share_dialog = new AlertDialog.Builder(context);
        share_dialog.setMessage("Live Message Saved");
        share_dialog.setPositiveButton("Done", null);
        share_dialog.setView(gifImageView);
        //Glide.with(this).load(R.raw.sample_gif).into(imageViewTarget);
        Glide.with(context).load(R.drawable.eye).asGif().into(gifImageView);
        share_dialog.show();*/



    }



    @Override
    protected void onResume() {
        android.support.v7.app.AlertDialog.Builder adb = new android.support.v7.app.AlertDialog.Builder(this);
        LayoutInflater adbInflater = LayoutInflater.from(this);
        View eulaLayout = adbInflater.inflate(R.layout.activity_custom_alert_box, null);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String skipMessage = settings.getString("skipMessage", "NOT checked");

        dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.check_box1);
        liii=eulaLayout.findViewById(R.id.customdialogg);
        //liii.setAlpha(0.1f);
        adb.setView(eulaLayout);
        adb.setTitle("Attention");
        //adb.setMessage(Html.fromHtml("Blink your eyes in front of Camera to Click Photo"));

        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT checked";

                if (dontShowAgain.isChecked()) {
                    checkBoxResult = "checked";
                }

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("skipMessage", checkBoxResult);
                editor.commit();

                // Do what you want to do on "OK" action

                return;
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT checked";

                if (dontShowAgain.isChecked()) {
                    checkBoxResult = "checked";
                }

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putString("skipMessage", checkBoxResult);
                editor.commit();

                // Do what you want to do on "CANCEL" action

                return;
            }
        });

        if (!skipMessage.equals("checked")) {
            adb.show();
        }

        super.onResume();
    }

   /* @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        if (previewRunning) {
            camera.stopPreview();
        }
        Camera.Parameters camParams = camera.getParameters();
        Camera.Size size = camParams.getSupportedPreviewSizes().get(0);
        camParams.setPreviewSize(size.width, size.height);
        camera.setParameters(camParams);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            previewRunning = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                return;
            }
            cameraSource.start(SurView.getHolder());
            detector.setProcessor(
                    new LargestFaceFocusingProcessor(detector, new GraphicFaceTracker()));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }*/


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(SurView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private class GraphicFaceTracker extends Tracker<Face> {

        private final float OPEN_THRESHOLD = 0.85f;
        private final float CLOSE_THRESHOLD = 0.4f;
        public  Camera camera1;
        private int state = 0;


        void blink(float value) {
            switch (state) {
                case 0:
                    if (value > OPEN_THRESHOLD) {
                        Log.i("BlinkTracker", "both eyes opened");
                        // Both eyes are initially open
                        state = 1;
                    }
                    break;

                case 1:
                    if (value < CLOSE_THRESHOLD ) {
                        Log.i("BlinkTracker", "both eyes closed");
                        // Both eyes become closed

                       /* MainActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                takeImage();
                                Toast.makeText(context, "Blinked", Toast.LENGTH_SHORT).show();
                                *//*camera.takePicture(myShutterCallback, myPictureCallback_RAW, myPictureCallback_JPG);*//*
                            }
                        });*/

                        //cameraSource.takePicture(new MainActivity().myShutterCallback,new MainActivity().myPictureCallback_RAW);



                        state = 2;
                    }
                    break;

                case 2:
                    if (value > OPEN_THRESHOLD)  {
                        // Both eyes are open again
                        Log.i("BlinkTracker", "blink occurred!");

                        Eye_blinkActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                takeImage();
                                //Toast.makeText(context, "Blinked", Toast.LENGTH_SHORT).show();
                                /*camera.takePicture(myShutterCallback, myPictureCallback_RAW, myPictureCallback_JPG);*/
                            }
                        });

                        state = 0;

                    }
                    break;
            }


        }

        /**
         * Update the position/characteristics of the face within the overlay.
         */
        @Override
        public void onUpdate(FaceDetector.Detections<Face> detectionResults, Face face) {

            float left = face.getIsLeftEyeOpenProbability();
            float right = face.getIsRightEyeOpenProbability();
            if ((left == Face.UNCOMPUTED_PROBABILITY) ||
                    (right == Face.UNCOMPUTED_PROBABILITY)) {
                // One of the eyes was not detected.
                return;
            }

            float value = Math.min(left, right);
            blink(value);
        }
    }

    private void takeImage() {
        try{

            cameraSource.takePicture(null, new CameraSource.PictureCallback() {

                private File imageFile;
                @Override
                public void onPictureTaken(byte[] bytes) {
                    try {
                        // convert byte array into bitmap
                        Bitmap loadedImage = null;
                        Bitmap rotatedBitmap = null;
                        loadedImage = BitmapFactory.decodeByteArray(bytes, 0,
                                bytes.length);

                        // rotate Image
                        Matrix rotateMatrix = new Matrix();
                        //rotateMatrix.postRotate(rotation);
                      /*  rotatedBitmap = Bitmap.createBitmap(loadedImage, 0, 0,
                                loadedImage.getWidth(), loadedImage.getHeight(),
                                rotateMatrix, false);*/
                         rotatedBitmap = Bitmap.createScaledBitmap(loadedImage, 320, 320, true);
                        String state = Environment.getExternalStorageState();
                        File folder = null;
                        if (state.contains(Environment.MEDIA_MOUNTED)) {
                            folder = new File(Environment
                                    .getExternalStorageDirectory() + "/Demo");
                        } else {
                            folder = new File(Environment
                                    .getExternalStorageDirectory() + "/Demo");
                        }

                        boolean success = true;
                        if (!folder.exists()) {
                            success = folder.mkdirs();
                        }
                        if (success) {
                            java.util.Date date = new java.util.Date();
                            imageFile = new File(folder.getAbsolutePath()
                                    + File.separator
                                    //+ new Timestamp(date.getTime()).toString()
                                    + "Image.jpg");
                            /*Toast.makeText(getBaseContext(), "Image saved"+imageFile,
                                    Toast.LENGTH_SHORT).show();*/
                            imageFile.createNewFile();
                        } else {
                            Toast.makeText(getBaseContext(), "Image Not saved",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ByteArrayOutputStream ostream = new ByteArrayOutputStream();

                        // save image into gallery
                        //rotatedBitmap = resize(rotatedBitmap, 800, 600);
                        rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);

                        FileOutputStream fout = new FileOutputStream(imageFile);
                        fout.write(ostream.toByteArray());
                        byte[] byteArray = ostream.toByteArray();
                        fout.close();
                        ContentValues values = new ContentValues();

                        values.put(MediaStore.Images.Media.DATE_TAKEN,
                                System.currentTimeMillis());
                        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                        values.put(MediaStore.MediaColumns.DATA,
                                imageFile.getAbsolutePath());

                        Eye_blinkActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


                        //String imageEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                       if (imageFile!=null){
                           Intent intent = new Intent();
                           intent.putExtra("ss", imageFile.getPath());
                           setResult(3, intent); //add this
                           finish();
                       }

                       /*  Handler myHandler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {

                            }
                            };*/


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
