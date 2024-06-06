package eraapps.bankasia.bdinternetbanking.apps.util

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.MapsInitializer.Renderer
import com.google.android.gms.maps.OnMapsSdkInitializedCallback
import dagger.hilt.android.HiltAndroidApp
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanResultDataModel
import okhttp3.MultipartBody


@HiltAndroidApp
class GlobalVariable : Application(),OnMapsSdkInitializedCallback{



    var vCardOperationMood: String? = ""
    var vCardActivityTitle: String? = ""
    var vCardPrimaryCardNo: String? = ""
    var vCardPrimaryCardNoForVirtualCardList: String? = ""
    var vCardCardType: String? = ""
    var vCardPrimaryCardTitle: String? = ""
    var vCardFrom: String? = ""

    //Ac Open---------
    var acOpenLanguageBool: Boolean = true
    var acOpenLat: String? = ""
    var acOpenLon: String? = ""
    var acOpenLanguage: String? = ""
    var acOpenNidFrontorBack = ""
    var acOpenName: String? = ""
    var acOpenNidFrontBitmapValue: Bitmap? = null
    var acOpenNidBackBitmapValue: Bitmap? = null
    var acOpenMobileNoOnBoard: String? = ""
    var acOpenX_mobile_token: String? = ""
    var acOpenToken: String? = ""
    var nidFrontorBack = ""
    var photoCount = ""
    var image = ""
    var selectedNIDType: String = ""
    var ocrDob: String = ""
    var nIDfromOCR: String = ""
    var getdOB: String = ""
    var accountTypeCheck: String = ""
    var productCategoryCode: String = ""
    var tenorCode: String = ""
    var tenor: String = ""
    var nidFrontBitmapValue: Bitmap? = null
    var nidBackBitmapValue: Bitmap? = null
    var signatureBitmapValue: Bitmap? = null
    var nomineePhotoBitmapValue: Bitmap? = null
    var nomineeGuardianPhotoBitmapValue: Bitmap? = null
    var front_part: MultipartBody.Part? = null
    var backPart: MultipartBody.Part? = null
    //Ac Open---------

    var deviceInfo: String = ""
    var customerCode: String = ""
    var identifyBanking: String = ""
    var sessionId: String = ""
    var mailId: String = ""
    var companycode: String = "001"
    var outCode: String = ""
    var outMessage: String = ""
    var imei: String = ""
    var token: String = ""
    var fcmToken: String = ""
    var tokenFlg: String = ""
    var userId: String = ""
    var accountNo: String = ""
    var userName: String = ""
    var primaryAc: String = ""
    var mobileNo: String = ""
    var address: String = ""
    var nidNo: String = ""
    var type: String = ""
    var apiOutCode: String = ""
    var shadowAc: String = ""
    var emailId: String = ""
    var statmentSelectAc: String = "0"
    var userImage: String = ""
    var languageCode: String = ""
    var dashboardPosition: String = ""
    var sourceAccountList: ArrayList<SourceAccountListDto> = ArrayList()
    var chequesourceAccountList: ArrayList<SourceAccountListDto> = ArrayList()
    var sourceAcStatementList: ArrayList<SourceAccountListDto> = ArrayList()
    var dashboardMenuList: ArrayList<MenuModel> = ArrayList()
 //Nano laon
    var loanList: ArrayList<LoanResultDataModel> = ArrayList<LoanResultDataModel>()
    var nanoLoanSourceAccountList: ArrayList<SourceAccountListDto> = ArrayList()
    var loanItemSelected: LoanResultDataModel? = null;

    var userImage_part: MultipartBody.Part? = null
    var userImageBitmapValue: Bitmap? = null


    var colorCode = "#F13530"
    var themeCode = "RED"

    //Payoneer v2
    var payoneer_info_url:String = "";
    var payoneer_code:String = "";
    var payoneer_id_token:String = "";
    var copayoneer_token_type:String = "";
    var payoneer_access_token:String = "";
    var payoneer_scope:String = "";
    var payoneer_account_id:String = "";
    var shadhinCardNumber:String = "";
    var shadhinMaskCardNumber:String = "";
    var shadinCardFcAccount:String = "";
    var shadinCardClientId:String = "";
    var livePhotoBitmapValue: Bitmap? = null
    var  orderId = ""
    var  shohozToken = ""

    var dob:String = "";
    var FROM:String = "";

    //Account Opening Value Start

    var ac_open_from: String = ""
    var ac_open_country_of_issue: String = ""
    var ac_open_country_code: String = ""
    var ac_open_email: String = ""
    var ac_open_gender: String? = ""
    var ac_open_marrital_status: String? = ""
    var ac_open_mobileNoOnBoard: String? = ""

    var ac_open_ocrDob: String? = ""
    var ac_open_ocrBangName: String? = ""
    var ac_open_ocrEngName: String? = ""
    var ac_open_nominneocrDob: String? = ""
    var ac_open_permissioncode: String? = "1"

    var ac_open_questionAnsCode1: String = ""
    var ac_open_questionAnsCode2: String = ""
    var ac_open_questionAnsCode3: String = ""
    var ac_open_questionAnsCode4: String = ""
    var ac_open_questionAnsCode5: String = ""
    var ac_open_questionAnsCode6: String = ""
    var ac_open_questionAnsCode7: String = ""
    var ac_open_questionAnsCode8: String = ""

    var ac_open_questionAns1: String = ""
    var ac_open_questionAns2: String = ""
    var ac_open_questionAns3: String = ""
    var ac_open_questionAns4: String = ""
    var ac_open_questionAns5: String = ""
    var ac_open_questionAns6: String = ""
    var ac_open_questionAns7: String = ""
    var ac_open_questionAns8: String = ""

    var ac_open_agentPoint: String? = ""

    var ac_open_checkNomineePercentage: Int = 100
    var ac_open_editEnable: Boolean = false
    var ac_open_selectedNIDType: String = ""
    var ac_open_accountType: String = ""
    var ac_open_tenor: String = ""
    var ac_open_typeOfBusiness: String = ""
    var ac_open_nationality: String = ""
    var ac_open_passport: String = ""
    var ac_open_tin: String = ""
    var ac_open_monthlyIncome: String = ""
    var ac_open_sourceOfFund: String = ""
    var ac_open_nomineeName: String = ""
    var ac_open_nomineeRelation: String = ""
    var ac_open_nomineeRelationCode: String = ""
    var ac_open_nomineePercentage: Int = 100
    var ac_open_nomineeNid: String = ""
    var ac_open_nomineeDob: String = ""
    var ac_open_nomineeGender: String = ""
    var ac_open_nomineeIOSStandard: String = ""
    var ac_open_nomineeGuardianName: String = ""
    var ac_open_nomineeGuardianNID: String = ""
    var ac_open_nomineeGuardianRelation: String = ""
    var ac_open_nomineeGuardianAddress: String = ""

    var ac_open_languageBool: Boolean = true
    var ac_open_outCode: String? = ""
    var ac_open_selectedOccupation: String? = "P"
    var ac_open_nomineetype = "Adult"
    var ac_open_outMessage: String? = ""
    var ac_open_token: String? = ""
    var ac_open_x_mobile_token: String? = ""
    var ac_open_nIDMyPhotoBase64Image: String? = null
    var ac_open_accountTypeCheck = ""
    var ac_open_nidFrontorBack = ""
    var ac_open_photoCount = ""
    var ac_open_image = ""
    var ac_open_nidFrontBitmapValue: Bitmap? = null
    var ac_open_nidBackBitmapValue: Bitmap? = null
    var ac_open_livePhotoBitmapValue: Bitmap? = null
    var ac_open_signatureBitmapValue: Bitmap? = null
    var ac_open_nomineePhotoBitmapValue: Bitmap? = null
    var ac_open_nomineeGuardianPhotoBitmapValue: Bitmap? = null
    // var ac_open_nomineeNidFontPhotoBitmapValue: Bitmap? = null
    // var ac_open_nomineeNidBackPhotoBitmapValue: Bitmap? = null
    var ac_open_nomineePhotoByte: ByteArray? = null
    var ac_open_guardianPhotoByte: ByteArray? = null
    // var ac_open_nomineeNidFontPhotoByte: ByteArray? = null
    // var ac_open_nomineeNidBackPhotoByte: ByteArray? = null
    var ac_open_nID = ""
    var ac_open_nIDfromOCR = ""
    var ac_open_nomineenIDfromOCR = ""
    var ac_open_nIDForFinalSubmit = ""
    var ac_open_rPAfoundOrNot = true
    var ac_open_nameBangla = ""
    var ac_open_nameEnglish = ""
    var ac_open_getdOB = ""
    var ac_open_ocrScan = false
    var ac_open_nIDfrontPart: MultipartBody.Part? = null
    var ac_open_signaturePhoto: MultipartBody.Part? = null
    var ac_open_livePhoto: MultipartBody.Part? = null
    var ac_open_nidTypeCode: String? = ""
    var ac_open_nidTypeDesc: String? = ""
    var ac_open_productCategoryCode: String? = ""
    var ac_open_tenorCode: String? = ""
    var ac_open_productCode: String? = ""
    var ac_open_product: String? = ""
    var ac_open_transactionOrMaturityAmount: String? = ""
    var ac_open_x_mobile_tokenReqBody: String? = ""
    var ac_open_x_verification_token: String? = ""
    var ac_open_transactionOrMaturityAmountReqBody: String? = ""
    var ac_open_nidReqBody: String? = ""

    var ac_open_dobReqBody: String? = ""
    var ac_open_dobReqBodyIOS: String? = ""
    var ac_open_motherNameBan: String? = ""
    var ac_open_motherNameEng: String? = ""
    var ac_open_fatherNameBan: String? = ""
    var ac_open_fatherNameEng: String? = ""
    var ac_open_profession: String? = ""
    var ac_open_professionCode: String? = ""

    var ac_open_divisionCodePresent: String? = ""
    var ac_open_districtCodePresent: String? = ""
    var ac_open_upozilaCodePresent: String? = ""
    var ac_open_unionCodePresent: String? = ""
    var ac_open_hasUnion = false
    var ac_open_branch: String? = ""
    var ac_open_districtPresent: String? = ""
    var ac_open_divisionPresent: String? = ""
    var ac_open_upozilaPresent: String? = ""
    var ac_open_unionPresent: String? = ""
    var ac_open_divisionEngPresent: String? = ""
    var ac_open_districtCodePermanent: String? = ""
    var ac_open_upozilaCodePermanent: String? = ""
    var ac_open_divisionCodePermanent: String? = ""
    var ac_open_unionCodePermanent: String? = ""
    var ac_open_districtPermanent: String? = ""
    var ac_open_divisionPermanent: String? = ""
    var ac_open_upozilaPermanent: String? = ""
    var ac_open_unionPermanent: String? = ""
    var ac_open_divisionEngPermanent: String? = ""

    var ac_open_presentAddressVill: String? = ""
    var ac_open_presentAddressRoad: String? = ""

    var ac_open_permanentAddressVill: String? = ""
    var ac_open_permanentAddressRoad: String? = ""

    var ac_open_presentAddress: String? = ""
    var ac_open_permanentAddress: String? = ""
    var ac_open_nidPhoto: MultipartBody.Part? = null
    var ac_open_bloodGroup = ""
    var ac_open_occupation = ""
    var ac_open_pin = ""
    var ac_open_spouseName = ""
    var ac_open_permanentAdditionalMouzaOrMoholla = ""
    var ac_open_permanentAdditionalVillageOrRoad = ""
    var ac_open_permanentCityCorporationOrMunicipality = ""
    var ac_open_permanentDistrict = ""
    var ac_open_permanentDivision = ""
    var ac_open_permanentHomeOrHoldingNo = ""
    var ac_open_permanentMouzaOrMoholla = ""
    var ac_open_permanentPostOffice = ""
    var ac_open_permanentPostalCode = ""
    var ac_open_permanentRegion = ""
    var ac_open_permanentRmo = ""
    var ac_open_permanentUnionOrWard = ""
    var ac_open_permanentUpozila = ""
    var ac_open_permanentVillageOrRoad = ""
    var ac_open_permanentWardForUnionPorishod = ""

    var ac_open_presentAdditionalMouzaOrMoholla = ""
    var ac_open_presentAdditionalVillageOrRoad = ""
    var ac_open_presentCityCorporationOrMunicipality = ""
    var ac_open_presentDistrict = ""
    var ac_open_presentDivision = ""
    var ac_open_presentHomeOrHoldingNo = ""
    var ac_open_presentMouzaOrMoholla = ""
    var ac_open_presentPostOffice = ""
    var ac_open_presentPostalCode = ""
    var ac_open_presentRegion = ""
    var ac_open_presentRmo = ""
    var ac_open_presentUnionOrWard = ""
    var ac_open_presentUpozila = ""
    var ac_open_presentVillageOrRoad = ""
    var ac_open_presentWardForUnionPorishod = ""
    var ac_open_nomineePhoto64 = ""
    var ac_open_guardianPhoto64 = ""

    var ac_open_guardianPhoto_global: MultipartBody.Part? = null

    var ac_open_front_part: MultipartBody.Part? = null
    // var ac_open_NomineeNidback: MultipartBody.Part? = null
    var ac_open_backPart: MultipartBody.Part? = null
    var ac_open_nomineePhoto_global: MultipartBody.Part? = null
    // var ac_open_nomineenidFontPhoto_global: MultipartBody.Part? = null
    //var ac_open_nomineenidBackPhoto_global: MultipartBody.Part? = null

    var ac_open_nominee_skip: Boolean = false
    var ac_open_signature_skip: Boolean = false

    fun clearAcOpeningData(){

        ac_open_presentAddressVill = ""
        ac_open_presentAddressRoad = ""

        ac_open_permanentAddressVill = ""
        ac_open_permanentAddressRoad = ""

        ac_open_email = ""
        ac_open_gender = ""
        ac_open_mobileNoOnBoard = ""

        ac_open_ocrDob = ""
        ac_open_nominneocrDob = ""
        ac_open_permissioncode = "1"

        ac_open_questionAnsCode1 = ""
        ac_open_questionAnsCode2 = ""
        ac_open_questionAnsCode3 = ""
        ac_open_questionAnsCode4 = ""
        ac_open_questionAnsCode5 = ""
        ac_open_questionAnsCode6 = ""
        ac_open_questionAnsCode7 = ""
        ac_open_questionAnsCode8 = ""

        ac_open_questionAns1 = ""
        ac_open_questionAns2 = ""
        ac_open_questionAns3 = ""
        ac_open_questionAns4 = ""
        ac_open_questionAns5 = ""
        ac_open_questionAns6 = ""
        ac_open_questionAns7 = ""
        ac_open_questionAns8 = ""

        ac_open_agentPoint = ""

        ac_open_checkNomineePercentage = 100
        ac_open_editEnable = false
        ac_open_selectedNIDType = ""
        ac_open_accountType = ""
        ac_open_tenor = ""
        ac_open_typeOfBusiness = ""
        ac_open_nationality = ""
        ac_open_passport = ""
        ac_open_tin = ""
        ac_open_monthlyIncome = ""
        ac_open_sourceOfFund = ""
        ac_open_nomineeName = ""
        ac_open_nomineeRelation = ""
        ac_open_nomineeRelationCode = ""
        ac_open_nomineePercentage = 100
        ac_open_nomineeNid = ""
        ac_open_nomineeDob = ""
        ac_open_nomineeIOSStandard = ""
        ac_open_nomineeGuardianName = ""
        ac_open_nomineeGuardianNID = ""
        ac_open_nomineeGuardianRelation = ""
        ac_open_nomineeGuardianAddress = ""

        ac_open_languageBool = true
        ac_open_outCode = ""
        ac_open_selectedOccupation = "P"
        ac_open_nomineetype = "Adult"
        ac_open_outMessage = ""
        ac_open_token = ""
        ac_open_x_mobile_token = ""
        ac_open_nIDMyPhotoBase64Image = null
        ac_open_accountTypeCheck = ""
        ac_open_nidFrontorBack = ""
        ac_open_photoCount = ""
        ac_open_image = ""
        ac_open_nidFrontBitmapValue = null
        ac_open_nidBackBitmapValue = null
        ac_open_livePhotoBitmapValue = null
        ac_open_signatureBitmapValue = null
        ac_open_nomineePhotoBitmapValue = null
        ac_open_nomineeGuardianPhotoBitmapValue = null
        ac_open_nomineePhotoByte = null
        ac_open_guardianPhotoByte = null
        ac_open_nID = ""
        ac_open_nIDfromOCR = ""
        ac_open_nomineenIDfromOCR = ""
        ac_open_nIDForFinalSubmit = ""
        ac_open_rPAfoundOrNot = true
        ac_open_nameBangla = ""
        ac_open_nameEnglish = ""
        ac_open_getdOB = ""
        ac_open_ocrScan = false
        ac_open_nIDfrontPart = null
        ac_open_signaturePhoto = null
        ac_open_livePhoto = null
        ac_open_productCategoryCode = ""
        ac_open_tenorCode = ""
        ac_open_productCode = ""
        ac_open_product = ""
        ac_open_transactionOrMaturityAmount = ""
        ac_open_x_mobile_tokenReqBody = ""
        ac_open_x_verification_token = ""
        ac_open_transactionOrMaturityAmountReqBody = ""
        ac_open_nidReqBody = ""

        ac_open_dobReqBody = ""
        ac_open_dobReqBodyIOS = ""
        ac_open_motherNameBan = ""
        ac_open_motherNameEng = ""
        ac_open_fatherNameBan = ""
        ac_open_fatherNameEng = ""
        ac_open_profession = ""
        ac_open_professionCode = ""

        ac_open_divisionCodePresent = ""
        ac_open_districtCodePresent = ""
        ac_open_upozilaCodePresent = ""
        ac_open_unionCodePresent = ""
        ac_open_hasUnion = false
        ac_open_branch = ""
        ac_open_districtPresent = ""
        ac_open_divisionPresent = ""
        ac_open_upozilaPresent = ""
        ac_open_unionPresent = ""
        ac_open_divisionEngPresent = ""
        ac_open_districtCodePermanent = ""
        ac_open_upozilaCodePermanent = ""
        ac_open_divisionCodePermanent = ""
        ac_open_unionCodePermanent = ""
        ac_open_districtPermanent = ""
        ac_open_divisionPermanent = ""
        ac_open_upozilaPermanent = ""
        ac_open_unionPermanent = ""
        ac_open_divisionEngPermanent = ""
        ac_open_presentAddress = ""
        ac_open_permanentAddress = ""
        ac_open_nidPhoto = null
        ac_open_bloodGroup = ""
        ac_open_occupation = ""
        ac_open_pin = ""
        ac_open_spouseName = ""
        ac_open_permanentAdditionalMouzaOrMoholla = ""
        ac_open_permanentAdditionalVillageOrRoad = ""
        ac_open_permanentCityCorporationOrMunicipality = ""
        ac_open_permanentDistrict = ""
        ac_open_permanentDivision = ""
        ac_open_permanentHomeOrHoldingNo = ""
        ac_open_permanentMouzaOrMoholla = ""
        ac_open_permanentPostOffice = ""
        ac_open_permanentPostalCode = ""
        ac_open_permanentRegion = ""
        ac_open_permanentRmo = ""
        ac_open_permanentUnionOrWard = ""
        ac_open_permanentUpozila = ""
        ac_open_permanentVillageOrRoad = ""
        ac_open_permanentWardForUnionPorishod = ""

        ac_open_presentAdditionalMouzaOrMoholla = ""
        ac_open_presentAdditionalVillageOrRoad = ""
        ac_open_presentCityCorporationOrMunicipality = ""
        ac_open_presentDistrict = ""
        ac_open_presentDivision = ""
        ac_open_presentHomeOrHoldingNo = ""
        ac_open_presentPostOffice = ""
        ac_open_presentPostalCode = ""
        ac_open_presentRegion = ""
        ac_open_presentRmo = ""
        ac_open_presentUnionOrWard = ""
        ac_open_presentUpozila = ""

        ac_open_presentWardForUnionPorishod = ""

        ac_open_front_part = null
        ac_open_backPart = null
        ac_open_nomineePhoto_global = null

        ac_open_nominee_skip = false
        ac_open_signature_skip = false
    }

    //Account Opening Value End
    override fun onCreate() {
        super.onCreate()
        MapsInitializer.initialize(applicationContext, Renderer.LATEST, this)
    }

    override fun onMapsSdkInitialized(renderer: Renderer) {
        when (renderer) {
            Renderer.LATEST -> Log.e("Location", "From GblobalVariable: -- The latest version of the renderer is used.")
            Renderer.LEGACY -> Log.e("Location", "From GblobalVariable: -- The legacy version of the renderer is used.")
        }
    }
}
