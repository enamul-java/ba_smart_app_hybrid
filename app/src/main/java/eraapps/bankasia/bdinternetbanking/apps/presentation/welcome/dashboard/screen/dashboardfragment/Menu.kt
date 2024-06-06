package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.dashboardfragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuItemModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar.MenuItemListAdaptar
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.screen.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.view_model.OptionsViewModel
import eraapps.bankasia.bdinternetbanking.apps.util.*
import java.util.*

@AndroidEntryPoint
class Menu : Fragment() {
    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog
    var menuItemList: ArrayList<MenuItemModel> = ArrayList()
    private lateinit var mAdapter: MenuItemListAdaptar

    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_edit: ImageView

    private lateinit var iv_profile_image: ImageView
    private lateinit var menu_item_recyclerview: RecyclerView

    val optionsViewModel: OptionsViewModel by viewModels()
    private lateinit var optionsEntities: ArrayList<OptionsEntity>
    var optionsEntityId = 0

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        globalVariable = requireActivity().applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(requireActivity(), globalVariable.languageCode)

        toolbar = view.findViewById(R.id.toolbar)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_edit = toolbar.findViewById(R.id.iv_header_edit)
        iv_profile_image = view.findViewById(R.id.iv_profile_image)
        menu_item_recyclerview = view.findViewById(R.id.menu_item_recyclerview)



        toolbar_title.text = globalVariable.userName
        globalVariable.dashboardPosition = "3"

        optionsViewModel.getLanguage()
        observeViewModel()

        menuItemList.clear()
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_notification,
                    "নোটিফিকেশন",
                    "NO",
                    ""
                )
            )
            //menuItemList.add(MenuItemModel(R.drawable.icon_profile, "আমার প্রোফাইল", "MP", ""))
            //menuItemList.add(MenuItemModel(R.drawable.icon_activity_log, "কার্য বিবরণ", "ACL", ""))

            //off customer feedback on 3 Dec 2023 for live problem as Nashed vai suggestion
            //menuItemList.add(MenuItemModel(R.drawable.icon_complaint, "গ্রাহকের প্রতিক্রিয়া", "COMP", ""))


            menuItemList.add(MenuItemModel(R.drawable.icon_call_center, "কন্টাক্ট সেন্টার", "CALL", ""))
            //menuItemList.add(MenuItemModel(R.drawable.ic_menu_services, "সেবাসমূহ", "SE", ""))
            if(ResourceAccessControlUtil.otpPerferenceAccess(requireActivity())) {
                menuItemList.add(
                    MenuItemModel(
                        android.R.drawable.ic_menu_set_as,
                        "OTP Preference",
                        "OP",
                        ""
                    )
                )
            }
            menuItemList.add(MenuItemModel(R.drawable.icon_currency, "মুদ্রার হার", "CR", ""))
            menuItemList.add(MenuItemModel(R.drawable.ic_products, "প্রোডাক্টস", "PO", ""))
            menuItemList.add(MenuItemModel(R.drawable.emi_calc, "ইএমআই ক্যালকুলেটর", "EMI", ""))
            menuItemList.add(MenuItemModel(R.drawable.ic_news, "নিউজ এন্ড ইভেন্টস", "NE", ""))
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_about,
                    "আমাদের সম্পর্কে",
                    "AU",
                    ""
                )
            )
            menuItemList.add(MenuItemModel(R.drawable.ic_menu_safety, "সুরক্ষা টিপস", "ST", ""))
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_transaction_limit,
                    "লেনদেন সীমা",
                    "TL",
                    ""
                )
            )
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_branch,
                    "ব্রাঞ্চ লোকেশন",
                    "BL",
                    ""
                )
            )
            menuItemList.add(MenuItemModel(R.drawable.ic_menu_atm, "এটিএম লোকেশন", "AL", ""))
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_terms_and_condition,
                    "টার্মস এন্ড কন্ডিশন",
                    "TC",
                    ""
                )
            )
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_language,
                    "ভাষা পরিবর্তন",
                    "LS",
                    "English"
                )
            )
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_contact,
                    "আমাদের সাথে যোগাযোগ করুন",
                    "CU",
                    ""
                )
            )
            menuItemList.add(MenuItemModel(R.drawable.ic_menu_logout, "লগআউট", "LO", ""))
        } else {
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_notification,
                    "Notifications",
                    "NO",
                    ""
                )
            )
            //menuItemList.add(MenuItemModel(R.drawable.icon_profile, "My Profile", "MP", ""))
            //menuItemList.add(MenuItemModel(R.drawable.icon_activity_log, "Activity Log", "ACL", ""))

            //off customer feedback on 3 Dec 2023 for live problem as Nashed vai suggestion
            //menuItemList.add(MenuItemModel(R.drawable.icon_complaint, "Customer Feedback", "COMP", ""))

            menuItemList.add(MenuItemModel(R.drawable.icon_call_center, "Contact Center", "CALL", ""))
            //menuItemList.add(MenuItemModel(R.drawable.ic_menu_services, "Services", "SE", ""))
            if(ResourceAccessControlUtil.otpPerferenceAccess(requireActivity())) {
                menuItemList.add(
                    MenuItemModel(
                        android.R.drawable.ic_menu_set_as,
                        "OTP Preference",
                        "OP",
                        ""
                    )
                )
            }
            menuItemList.add(MenuItemModel(R.drawable.icon_currency, "Currency Rate", "CR", ""))
            menuItemList.add(MenuItemModel(R.drawable.ic_products, "Products", "PO", ""))
            menuItemList.add(MenuItemModel(R.drawable.emi_calc, "EMI Calculator", "EMI", ""))
            menuItemList.add(MenuItemModel(R.drawable.ic_news, "News & Events", "NE", ""))
            menuItemList.add(MenuItemModel(R.drawable.ic_menu_about, "About SMART App", "AU", ""))
            menuItemList.add(MenuItemModel(R.drawable.ic_menu_safety, "Safety Tips", "ST", ""))
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_transaction_limit,
                    "Transaction Limit",
                    "TL",
                    ""
                )
            )
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_branch,
                    "Branch Location",
                    "BL",
                    ""
                )
            )
            menuItemList.add(MenuItemModel(R.drawable.ic_menu_atm, "ATM Location", "AL", ""))
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_terms_and_condition,
                    "Term & Conditions",
                    "TC",
                    ""
                )
            )
            menuItemList.add(
                MenuItemModel(
                    R.drawable.ic_menu_language,
                    "Language Settings",
                    "LS",
                    "বাংলা"
                )
            )
            menuItemList.add(MenuItemModel(R.drawable.ic_menu_contact, "Contact Us", "CU", ""))
            //  menuItemList.add(MenuItemModel(R.drawable.ic_menu_contact, "Complain", "COMP", ""))

            menuItemList.add(MenuItemModel(R.drawable.ic_menu_logout, "Log Out", "LO", ""))
        }


        val mLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        menu_item_recyclerview.layoutManager = mLayoutManager
        menu_item_recyclerview.itemAnimator = DefaultItemAnimator()

        mAdapter =
            MenuItemListAdaptar(
                menuItemList,
                object : MenuItemListAdaptar.OnItemClickListener {
                    override fun onItemClick(item: MenuItemModel) {
                        //    Toast.makeText(requireActivity(), item.menuTitle, Toast.LENGTH_SHORT).show()
                        if (item.code == "LO") {
                            //CustomAlert.logout(requireActivity(), globalVariable.languageCode)
                            CustomAlert.logout(requireActivity(), globalVariable)
                        }

                        else if (item.code == "CALL") {
                            //
                        }
                        else if (item.code == "LS") {

                            //

                        } else if (item.code == "AU") {
                            //
                        } else if (item.code == "CU") {
                            //
                        } else if (item.code == "TC") {
                            //
                        } else if (item.code == "ST") {
                            //
                        } else if (item.code == "SE") {
                            //
                        } else if (item.code == "TL") {
                            //
                        } else if (item.code == "PO") {
                            //
                        } else if (item.code == "EMI") {
                            //
                        } else if (item.code == "CR") {
                            //
                        } else if (item.code == "BL") {
                            //
                        } else if (item.code == "AL") {
                            //
                        } else if (item.code == "NO") {
                            //
                        } else if (item.code == "MP") {
                            //
                        } else if (item.code == "ACL") {
                            //
                        } else if (item.code == "COMP") {
                            //
                        } else if (item.code == "OP") {
                            //
                        } else if (item.code == "NE") {
                            val url =
                                "https://www.bankasia-bd.com/about/news"

                            ValidationUtil.goToUrl(requireActivity(), url)
                        }
                    }
                })

        menu_item_recyclerview.adapter = mAdapter
        mAdapter.notifyDataSetChanged()


        iv_header_edit.setOnClickListener {
            //image
        }


        if (globalVariable.userImage == "") {
            iv_profile_image.setImageResource(R.drawable.ic_baseline_person_24)
        } else {
            iv_profile_image.loadOperatorsImagesClear(
                globalVariable.userImage,
                getProgressDrawble(iv_profile_image.context)
            )
        }





        return view
    }

    @SuppressLint("SuspiciousIndentation")
    private fun observeViewModel() {

        optionsViewModel.optionsResponse
            .observe(viewLifecycleOwner) {

                optionsEntities = it as ArrayList<OptionsEntity>
                if (optionsEntities.size == 0) {

                } else {
                    for (i in optionsEntities) {
                        optionsEntityId = i.id
                    }

                }

            }

        optionsViewModel.errorResponse
            .observe(viewLifecycleOwner) {
                // pDialog.dismiss()
                //CustomAlert.showErrorMessage(requireActivity(), it.message, globalVariable.languageCode)

                Log.e("QUICK", it.message)
            }
    }


    private fun reload() {
        val intent = requireActivity().intent
        intent.putExtra("LAN", "LAN")
        requireActivity().finish()
        startActivity(intent)
    }


    //******************* Contact Center****************************************
    fun getCallPermission() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val READ_CONTACTS = ContextCompat.checkSelfPermission(
                    requireActivity(), Manifest.permission.CALL_PHONE
                )


                if (READ_CONTACTS != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        requireActivity(), arrayOf(
                            Manifest.permission.CALL_PHONE
                        ), 1
                    )


                }


            }


        } catch (ex: Exception) {
            ex.message?.let { Log.e("", it) }
        }
    }

    private fun checkCallPermission(): Boolean {
        val result =
            ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CALL_PHONE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Toast.makeText(requireActivity(), "Call Permission is Ok", Toast.LENGTH_SHORT).show()
        } else {
            getCallPermission()
        }
    }
    //******************* Contact Center****************************************

}