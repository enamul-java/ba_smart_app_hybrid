package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.eligibility

import android.app.Activity
import eraapps.bankasia.bdinternetbanking.apps.common.Constants

class CheckEligibility {

    fun checkEligibility(activity: Activity):Boolean{
        return Constants.allowNanoLoan()
    }
}