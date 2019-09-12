package com.radiant.rpl.testa.Common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.LargestFaceFocusingProcessor;
import com.radiant.rpl.testa.EyeBlink.Eye_blinkActivity;

import java.io.IOException;

import radiant.rpl.radiantrpl.R;

public class Camera_activity extends Activity {


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

    @SuppressWarnings("deprecation")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_activity);

        SurView = (SurfaceView) findViewById(R.id.sview1);
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
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedFps(2.0f)
                    .setRequestedPreviewSize(580, 580)
                    .setAutoFocusEnabled(true)
                    .build();



            SurView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(Camera_activity.this,
                                    new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(SurView.getHolder());
                       /* detector.setProcessor(
                                new LargestFaceFocusingProcessor(detector, new Eye_blinkActivity.GraphicFaceTracker()));*/

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

    }


}
