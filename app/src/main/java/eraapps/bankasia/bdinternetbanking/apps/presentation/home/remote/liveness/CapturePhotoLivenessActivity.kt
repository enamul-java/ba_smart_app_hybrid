package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.liveness

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import eraapps.bankasia.bdinternetbanking.apps.R
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.face.FaceDetector
import com.google.android.gms.vision.face.LargestFaceFocusingProcessor
import com.iceteck.silicompressorr.SiliCompressor
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.forgot_pass_user.screen.ForgotPassword
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.screen.NewUserRequestActivity
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAlert
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import java.io.IOException
@AndroidEntryPoint
class CapturePhotoLivenessActivity : AppCompatActivity(), SurfaceHolder.Callback,
    CameraSource.PictureCallback {

    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog


    val CAMERA_REQUEST = 101
    lateinit var bitmap: Bitmap
    lateinit private var surfaceHolder: SurfaceHolder
    lateinit private var surfaceView: SurfaceView
    private var neededPermissions = arrayOf(Manifest.permission.CAMERA)
    lateinit private var detector: FaceDetector
    lateinit private var cameraSource: CameraSource


    private lateinit var llBtn: LinearLayout
    private lateinit var btnTry: AppCompatButton
    private lateinit var btnNext: AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_ness)


        globalVariable = this.applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)

        surfaceView = findViewById(R.id.surfaceView)
        llBtn = findViewById(R.id.llBtn)
        btnTry = findViewById(R.id.btnTry)
        btnNext = findViewById(R.id.btnNext)

        llBtn.visibility = View.GONE




        if (!checkCallCameraPerssion()) {
            cameraPerssion()
        }

        surfaceView.setBackgroundResource(R.drawable.circle_surface)
        detector = FaceDetector.Builder(this)
            .setProminentFaceOnly(true) // optimize for single, relatively large face
            .setTrackingEnabled(true) // enable face tracking
            .setClassificationType( /* eyes open and smile */FaceDetector.ALL_CLASSIFICATIONS)
            .setMode(FaceDetector.FAST_MODE) // for one face this is OK
            .build()

        if (!detector.isOperational) {
            //Log.w("MainActivity", "Detector Dependencies are not yet available")
        } else {
            //Log.w("MainActivity", "Detector Dependencies are available")
        /*   val result: Boolean = checkPermission()
            if (result) {
                setViewVisibility(R.id.tv_capture)
                setViewVisibility(R.id.surfaceView)
                setupSurfaceHolder()
            }
            */

            if (!checkCallCameraPerssion()) {
                cameraPerssion()
            }else{
               // setViewVisibility(R.id.tv_capture)
                setViewVisibility(R.id.surfaceView)
                setupSurfaceHolder()
            }

        }



    }

    private fun setViewVisibility(id: Int) {
        val view = findViewById<View>(id)
        if (view != null) {
            view.visibility = View.VISIBLE
        }
    }

    private fun setupSurfaceHolder() {
        cameraSource = CameraSource.Builder(this, detector)
            .setFacing(CameraSource.CAMERA_FACING_FRONT)
            .setRequestedFps(2.0f)
            .setAutoFocusEnabled(true)
            .build()

        surfaceHolder = surfaceView.holder
        surfaceHolder.addCallback(this)
    }

    fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height, matrix,
            true
        )
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        startCamera()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        cameraSource.stop()
    }




    private fun startCamera() {
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            cameraSource.start(surfaceHolder)
            detector.setProcessor(LargestFaceFocusingProcessor(detector, GraphicFaceTracker(this)))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun captureImage() {
        //dots_progress.visibility = View.GONE
        try {
            Handler(Looper.getMainLooper()).postDelayed({
                runOnUiThread {

                    clickImage()
                }
            }, 1000)
            // We add a delay of 200ms so that image captured is stable.
        } catch (e: Exception) {
            //  Block of code to handle errors
        }

    }

    private fun clickImage() {
        try {
            cameraSource.takePicture(null, this)
        } catch (e: Exception) {
            //  Block of code to handle errors
        }

    }




    private fun checkCallCameraPerssion(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun cameraPerssion() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val READ_CONTACTS = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                )


                if (
                    READ_CONTACTS != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.CAMERA
                        ), 1
                    )


                }


            }


        } catch (ex: Exception) {
            ex.message?.let { Log.e("", it) }
        }
    }

    override fun onPictureTaken(bytes: ByteArray) {
        // bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        // Save or Display image as per your requirements. Here we display the image.
        val orientation: Int = ExifUtils.getOrientation(bytes)
        var bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        when (orientation) {
            90 -> bitmap = rotateImage(bitmap, 90f)
            180 -> bitmap = rotateImage(bitmap, 180f)
            270 -> bitmap = rotateImage(bitmap, 270f)
            0 ->                 // if orientation is zero we don't need to rotate this
                bitmap = bitmap
            else -> {
            }
        }
        bitmap = if (bitmap.width >= bitmap.height) {
            Bitmap.createBitmap(
                bitmap,
                bitmap.width / 2 - bitmap.height / 2,
                0,
                bitmap.height,
                bitmap.height
            )
        } else {
            Bitmap.createBitmap(
                bitmap,
                0,
                bitmap.height / 2 - bitmap.width / 2,
                bitmap.width,
                bitmap.width
            )
        }
        (findViewById<View>(R.id.iv_picture) as ImageView).setImageBitmap(bitmap)

        setViewVisibility(R.id.iv_picture)
        setViewVisibility(R.id.llBtn)
        findViewById<View>(R.id.surfaceView).visibility = View.GONE
        findViewById<View>(R.id.txtHint).visibility = View.GONE
        findViewById<View>(R.id.txtHint2).visibility = View.GONE
        findViewById<View>(R.id.txtHint3).visibility = View.GONE
        findViewById<View>(R.id.dots_progress).visibility = View.GONE

        btnNext.setOnClickListener {

            globalVariable.livePhotoBitmapValue = bitmap
           if("FP" == globalVariable.FROM){
               val intent = Intent(
                   this@CapturePhotoLivenessActivity,
                   ForgotPassword::class.java
               )
               intent.putExtra("FROM","LIVENESS")
               startActivity(intent)
           }else if("SU" == globalVariable.FROM){
               val intent = Intent(
                   this@CapturePhotoLivenessActivity,
                   NewUserRequestActivity::class.java
               )
               intent.putExtra("FROM","LIVENESS")
               startActivity(intent)
           }

        }

        btnTry.setOnClickListener {


            globalVariable.livePhotoBitmapValue = bitmap

            val intent = Intent(
                this@CapturePhotoLivenessActivity,
                CapturePhotoLivenessActivity::class.java
            )
            startActivity(intent)
        }



    }
}