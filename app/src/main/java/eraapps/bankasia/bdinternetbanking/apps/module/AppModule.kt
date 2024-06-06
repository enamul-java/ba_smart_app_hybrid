package eraapps.bankasia.bdinternetbanking.apps.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.local.data_source.SmartAppDatabase
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.*
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.*
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.branch_location_use_case.AddBranchLocation
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.branch_location_use_case.BranchLocaltionLocalUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.branch_location_use_case.BranchLocationCount
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.branch_location_use_case.GetLocalLocations
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.account.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.credit.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.imageupload.ImageUploadExe
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.imageupload.ImageuploadUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.location.GetLocations
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.location.LocationUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.login.FingerLogin
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.login.Login
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.login.LoginUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.otp.CardOtp
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.otp.OtpUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.otp.SendTransOtp
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.password_change.PasswordChangeExe
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.password_change.PasswordChangeUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.qr_transaction.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting.CardInfoVerify
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting.CardPinResetExe
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting.CustomerBasicInfo
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting.CustomerComplain
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting.LimitInfo
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting.SettingUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting.UserAccessList
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting.UserIdIChangeExe
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting.UserIdInfoVerify
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.topup.MobilerechargeExe
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.topup.MobilerechargeLimitCheck
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.topup.TopupUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideSmartApi(): SmartAppApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(SmartAppApi::class.java)
    }


    /**
     * for databse provider
     */

    @Provides
    @Singleton
    fun provideDatabase(app: Application): SmartAppDatabase {
        return Room.databaseBuilder(
            app,
            SmartAppDatabase::class.java,
            SmartAppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }


    /**
     * for local branch location
     */
    @Provides
    @Singleton
    fun provideBranchLocaitonRepository(db: SmartAppDatabase): BranchLocationLocalRepositoryImpl {
        return BranchLocationLocalRepositoryImpl(db.smartAppDao)
    }

    /**
     * for app Options
     */
    @Provides
    @Singleton
    fun provideOptionsRepository(db: SmartAppDatabase): OptionsRepositoryImpl {
        return OptionsRepositoryImpl(db.smartAppDao)
    }

    /**
     * for app Options
     */
    @Provides
    @Singleton
    fun provideUserIdRepository(db: SmartAppDatabase): UserRepositoryImpl {
        return UserRepositoryImpl(db.smartAppDao)
    }

    /**
     * for app Options
     */
    @Provides
    @Singleton
    fun provideRecentContactRepository(db: SmartAppDatabase): ContactRepositoryImpl {
        return ContactRepositoryImpl(db.smartAppDao)
    }

    /**
     * for app Finger Login
     */
    @Provides
    @Singleton
    fun provideUserFingerRepository(db: SmartAppDatabase): UserFingerRepositoryImpl {
        return UserFingerRepositoryImpl(db.smartAppDao)
    }

    /**
     * for app Recent Transfer
     */
    @Provides
    @Singleton
    fun provideRecentTransferRepository(db: SmartAppDatabase): RecentTransferRepositoryImpl {
        return RecentTransferRepositoryImpl(db.smartAppDao)
    }


    /*
    * for remote api
    * */
    @Provides
    @Singleton
    fun provideLocationRepository(api: SmartAppApi): BranchRepositoryImpl {
        return BranchRepositoryImpl(api)
    }

    /*
   * for remote api
   * */
    @Provides
    @Singleton
    fun provideLoginRepository(api: SmartAppApi): LoginRepositoryImpl {
        return LoginRepositoryImpl(api)
    }


    /*
* for remote api
* */
    @Provides
    @Singleton
    fun provideOtpRepository(api: SmartAppApi): OtpRepositoryImpl {
        return OtpRepositoryImpl(api)
    }

    /*
    * for remote branch locaiton api
    * */
    @Provides
    @Singleton
    fun providesLocationUsecase(useCase: BranchRepositoryImpl): LocationUseCase {
        return LocationUseCase(
            getLocations = GetLocations(useCase)
        )
    }

    /**
     * Add LocalBracnh Location
     * */
    @Provides
    @Singleton
    fun providesBranchLocationUsecase(useCase: BranchLocationLocalRepositoryImpl): BranchLocaltionLocalUseCase {
        return BranchLocaltionLocalUseCase(
            addBranchLocation = AddBranchLocation(useCase),
            getLocalLocations = GetLocalLocations(useCase),
            branchLocationCount = BranchLocationCount(useCase),
        )
    }

    /**
     *  Login UseCase
     * */
    @Provides
    @Singleton
    fun providesLoginUseCase(loginUseCase: LoginRepositoryImpl): LoginUseCase {
        return LoginUseCase(
            login = Login(loginUseCase),
            fingerLogin = FingerLogin(loginUseCase)
        )
    }


    /**
     *  Topup UseCase
     * */
    @Provides
    @Singleton
    fun providesTopupUseCase(useCase: TopupRepositoryImpl): TopupUseCase {
        return TopupUseCase(
            mobilerechargeLimitCheck = MobilerechargeLimitCheck(useCase),
            mobilerechargeExe = MobilerechargeExe(useCase)
        )
    }

    /**
     *  Password Change UseCase
     * */
    @Provides
    @Singleton
    fun providesPasswordChangeUseCase(useCase: PasswordChangeRepositoryImpl): PasswordChangeUseCase {
        return PasswordChangeUseCase(
            doExecuteRechange = PasswordChangeExe(useCase)
        )
    }

    /**
     *  Customer Basic Info UseCase
     * */
    @Provides
    @Singleton
    fun providesSettingUseCase(useCase: SettingRepositoryImpl): SettingUseCase {
        return SettingUseCase(
            customerBasicInfo = CustomerBasicInfo(useCase),
            limitInfo = LimitInfo(useCase),
            customerComplain = CustomerComplain(useCase),
            userIdInfoVerify = UserIdInfoVerify(useCase),
            cardInfoVerify = CardInfoVerify(useCase),
            cardPinResetExe = CardPinResetExe(useCase),
            userIdChangeExe = UserIdIChangeExe(useCase),
            userAccessList = UserAccessList(useCase)
        )
    }


    /**
     *  Password Change UseCase
     * */
    @Provides
    @Singleton
    fun providesImageUploadChangeUseCase(useCase: ImageUploadRepositoryImpl): ImageuploadUseCase {
        return ImageuploadUseCase(
            imageUploadExe = ImageUploadExe(useCase)
        )
    }


    /**
     *  OTP UseCase
     * */
    @Provides
    @Singleton
    fun providesotpUseCase(useCase: OtpRepositoryImpl): OtpUseCase {
        return OtpUseCase(
            sendTransOtp = SendTransOtp(useCase),
            doCardOtp = CardOtp(useCase)
        )
    }


    /**
     *  QR Transaction UseCase
     * */
    @Provides
    @Singleton
    fun providesQrTransactionUseCase(useCase: QrTransactionRepositoryImpl): QrTransactionUseCase {
        return QrTransactionUseCase(
            addCardInfo = AddCardInfo(useCase),
            viewCardInfo = ViewCardInfo(useCase),
            banglaQRValidation = BanglaQrValidation(useCase),
            npsbBanglaQrPayment = NpsbBanglaQrPayment(useCase),
            visaQrPayment = VisaQrPayment(useCase),
            merchantChargeCalculation = MerchantChargeCalculation(useCase),
            mmexecuteCashOut = MmExeCashout(useCase),
            ownBankQrTransfer = OwnBankQrTransfer(useCase),
            viewCardInfoList = ViewCardInfoList(useCase),
            viewCardInfoListPayment = ViewCardInfoListPayment(useCase),
            addCardExe = AddCardExe(useCase),
            qrTransHistory = QrTransHistory(useCase),
            qrTransHistoryList = QrTransHistoryList(useCase)
        )
    }

    /**
     *  Credit Card Transaction UseCase
     * */
    @Provides
    @Singleton
    fun providesCreditCardUseCase(useCase: CreditCardRepositoryImpl): CreditCardUseCase {
        return CreditCardUseCase(
            creditCardSrc = CreditCardSrc(useCase),
            creditCardDestination = CreditCardDestination(useCase),
            creditCardchargeCalculation = CreditCardChargeCalculation(useCase),
            submitCreditCardRegistration = SubmitCreditCardRegistration(useCase),
            creditCarddoExecute = CreditCarddoExecute(useCase),
            creditCardSrcList = CreditCardSrcList(useCase),
            creditCardStatementList = CreditCardStatementList(useCase),
            getCardTypeCheckList = GetCardTypeCheckList(useCase),
            bankTypeList = BankTypeList(useCase),
            creditCardDestinationList = CreditCardDestinationList(useCase),
            getAccountTypes = AccountTypesList(useCase),
        )
    }


}