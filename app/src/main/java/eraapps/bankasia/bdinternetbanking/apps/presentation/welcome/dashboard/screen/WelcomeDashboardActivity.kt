package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NotificationTokenRegistrationRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.*


@AndroidEntryPoint
class WelcomeDashboardActivity : AutoLogoutCompatActivity() {

    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var fab_QR: FloatingActionButton

    var BENE = ""
    var LAN = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_dashboard)

        globalVariable = this.applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        fab_QR = findViewById(R.id.fab_QR)
        navController = findNavController(R.id.nav_host_fragment)

        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        if (globalVariable.customerCode.isEmpty()) {
            CustomActivityClear.forceLogout(
                this,
                "Your active session is expired. Please login again."
            )
        }


        requestCemeraPermission()

        fab_QR.setOnClickListener {
            if (!checkPermission()) {
                requestCemeraPermission()
            } else {
                //QR

            }

        }
        val intent = intent
        intent.getStringExtra("BENE").also {
            if (it != null) {
                BENE = it
            }
        }

        intent.getStringExtra("LAN").also {
            if (it != null) {
                LAN = it
            }
        }

        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            val menu = bottomNavigationView.menu
            for (i in 0 until menu.size()) {
                val mi = menu.getItem(i)
                val id = mi.itemId
                if (id == R.id.navigation_home) {
                    mi.setTitle(R.string.home_bangla)
                } else if (id == R.id.navigation_statement) {
                    mi.setTitle(R.string.statement_bangla)
                } else if (id == R.id.placeholder) {
                    mi.setTitle(R.string.qr_pay_bangla)
                } else if (id == R.id.navigation_beneficiary) {
                    //if(CheckEligibility().checkEligibility(this)) {
                        mi.setTitle(R.string.beneficiary_bottom_bangla)
                    /*}else{
                        mi.setTitle(R.string.contact_bottom_bangla)
                    }*/
                } else if (id == R.id.navigation_menu) {
                    mi.setTitle(R.string.menu_bangla)
                }
            }
        }
        /*
        else{
            val menu = bottomNavigationView.menu
            for (i in 0 until menu.size()) {
                val mi = menu.getItem(i)
                val id = mi.itemId

                if (id == R.id.navigation_beneficiary) {
                    if(CheckEligibility().checkEligibility(this)) {
                        mi.setTitle(R.string.beneficiary_bottom)
                    }else{
                        mi.setTitle(R.string.contact_bottom)
                    }
                }
            }
        }
        */

        bottomNavigationView.setupWithNavController(navController)

        /*
        if(CheckEligibility().checkEligibility(this)) {

        }else {
            */

            if (BENE == "ABENE" || BENE == "CBENE" || BENE == "CRBENE") {
                findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_beneficiary)

            } else if (BENE == "0") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 0)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "STBENE") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 0)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "OWBENE") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 0)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "OWABENE") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 0)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "OTBENE") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 1)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "OABENE") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 1)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "1") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 1)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "2") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 2)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "3") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 3)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "MABENE") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 2)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (BENE == "MBENE") {
                val bundle = Bundle()
                bundle.putInt("viewpager", 2)
                findNavController(R.id.nav_host_fragment).navigate(
                    R.id.navigation_beneficiary,
                    bundle
                )
            } else if (LAN == "LAN") {
                findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_menu)
            } else if (LAN == "HOME") {
                findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
            }
        //}



        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    globalVariable.dashboardPosition = "0"
                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_statement -> {
                    globalVariable.dashboardPosition = "1"
                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_statement)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_beneficiary -> {
                    globalVariable.dashboardPosition = "2"
                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_beneficiary)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_menu -> {
                    globalVariable.dashboardPosition = "3"
                    findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_menu)
                    return@setOnItemSelectedListener true
                }

                else -> return@setOnItemSelectedListener false

            }

        }

        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("dashboardPosition", globalVariable.dashboardPosition)
                    //if(CheckEligibility().checkEligibility(this)) {

                    //}else{
                    if (BENE == "MBENE") {
                        //MFS
                    } else if (BENE == "OWBENE") {
                        //Own Bank
                    } else if (BENE == "OTBENE") {
                        //Other Bank
                    } else if (BENE == "STBENE") {
                        //Standing ins
                    } else if (BENE == "CBENE") {
                        //Card Transfer
                    } else if (BENE == "CRBENE") {
                        //Card Payment
                    } else if (BENE == "OWABENE") {
                        val stintent =
                            Intent(
                                this@WelcomeDashboardActivity,
                                WelcomeDashboardActivity::class.java
                            )
                        startActivity(stintent)
                    }

                    else if (globalVariable.dashboardPosition == "0") {
                        //CustomAlert.logout(this@WelcomeDashboardActivity, globalVariable.languageCode)
                        CustomAlert.logout(this@WelcomeDashboardActivity, globalVariable)
                    } else if (globalVariable.dashboardPosition == "1") {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
                    } else if (globalVariable.dashboardPosition == "2") {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
                    } else if (globalVariable.dashboardPosition == "3") {
                        findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_home)
                    } else {
                        //CustomAlert.logout(this@WelcomeDashboardActivity, globalVariable.languageCode)
                        CustomAlert.logout(this@WelcomeDashboardActivity, globalVariable)
                    }
                }
            }
            )

        getFcmToken()

        observeEvents()

    }


    private fun observeEvents() {

    }

    fun requestCemeraPermission() {
        //Permission
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                val CAMERA = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                )

                if (CAMERA != PackageManager.PERMISSION_GRANTED

                ) {
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

    private fun checkPermission(): Boolean {
        val camera =
            this.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CAMERA
                )
            }
        return camera == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("SuspiciousIndentation")
    fun getFcmToken() {
        try {
            FirebaseApp.initializeApp(this)
            FirebaseMessaging.getInstance().token.addOnSuccessListener { fcmtoken: String ->
                if (!TextUtils.isEmpty(fcmtoken)) {
                   // Log.d("token", "retrieve token successful : $fcmtoken")
                    globalVariable.fcmToken = fcmtoken
                    if (globalVariable.tokenFlg!="0"){
                        doRegisterToken(fcmtoken)}

                } else {
                     //  Log.d("token", "token should not be null...")
                }
            }.addOnFailureListener {
               // Log.d("Failed1", "Failed")
            }.addOnCanceledListener {}
                .addOnCompleteListener { task: Task<String> ->
                    try {
                        /*  Log.d(
                              "token",
                              "This is the token : " + task.result
                          )*/

                        //Log.d("Failed", "Failed")

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun doRegisterToken(fcmtoken: String) {
        val tokenFrom: String
        if (globalVariable.identifyBanking == TextContants.identifyBanking_agent) {
            tokenFrom = "ABS"
        } else {
            tokenFrom = "MYB"
        }


    }


}