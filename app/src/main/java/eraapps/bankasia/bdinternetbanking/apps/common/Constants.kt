package eraapps.bankasia.bdinternetbanking.apps.common

import eraapps.bankasia.bdinternetbanking.apps.BuildConfig

object Constants {

    fun allowNanoLoan():Boolean{
        if(isLive()){
            return false
        }else if(BuildConfig.FLAVOR.equals("nanolive")){
            return true
        }else {
            return false
        }
    }

    fun isLive():Boolean{
        return BASE_URL.equals(BASE_URL)
    }

    private fun getBaseUrl():String{
        return BASE_URL
    }

    @kotlin.jvm.JvmField
    val BASE_URL = "http://202.40.191.226:9191/smart-app-api/" // Remote Apps

    const val access_auth_request_header =
        "Basic XtNYpaTinqHyeJ9bXsQmCutBNUrS6P7NmX+cuPTgUuy8cNKnG0UcmSuhuIfkYHWI2lA3qcGgQGxQlS2ZHW0MEVDa40k3nM7DSbW9C0GD1vNNoMK1yCoebP7DozwGsPloCHU4q28yeTJdp+pQCNFb9KO68ofIoGxiRhFUrJ6IbqhgMcyFkD5vPIV/fhayxSC+ITOEkrjhSlxGyuADKGzccIQ9lNSp07VMf0TodHoJq1IhnPGdEuyywN2Gt9fgCqke/IMKloVgzSFGe7eg7pvA+KNpH+o/BeI5qGBenBcJtipm1pUXtHyf3NXOD2H3UiUe6LJUrEKGiOQBtAUphQ+NrkBSecp1HH8lNuwVdf4UpZoMyDWbbSwzkCZPNjhuOnpfkcxfRCv/VCFQLPH6NNEvipXn25muszD3cWjJK15oGUgoGP0vR6gjnKlz52vHje+2QIz/sM2/jHa5emnITjvupZj6Tl82YuwmklNlpG0Q0DIx/x5+5p/ttBjb/i3HOcZ8UXjijSdPzmVvUIWcixQ1uPQekzTUd1pImJgKsnzYJaUaBFDTRfbIWKx5fJYSg0St+Ktm+IK2FI6oRNrM6iKV3Roa1xxo1qjCoa+X9PivA200ZS2z7VMPGVYuPlMToGvPvBNrVeKJ4Y7tpOmF7NpF2gOy6ioUeYmL6LvEbQr/WyM="
    const val publickey =
        "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAk1XWeFPurPM0MVg+Or5dIfrVAtF/MK+HDcLOh/wRaYeODSoRw8unse7CqtndUdZ/E0nXp+X2wYxPlor90V5lPqw2v8FN8iSJt+z+QT4+5yCSMY/tTRY7n/b/GApkmamwnPfhOntOfpuUNZ+EZvWzzm+um27RGVGTEzHhvi65hxERmCnr4UV9YQ936nSbAu2PfnHnG+HoEfFL5ehbGGMKcoSN20z25eu+/fEJ603ae2ruMCHNCTa1MFx2U++eVKGre2Wj+dtYOnUm85cFgSv9DVuXpKqPpjTbGEsG/wIGfggBYLvDCwxbj+L5ZPUj8c90CRIXLWHt7xq+Bt5U1s9WHmH/8sO215aYi7EKS59AuNpmxtJVf+4kx9XnDCcwic5UGIhTPWr/iX6peIKzn+QCl2MzRQ1NaKN7RyGJZ3ISG0I5aIm91i8n60JdNdc/JaAP+h90UEfGkaSuXobOQTjXI5HOZefz50I7JDbfh+n5M1SBZvtCKJ2+i9uB8Du52g5ko4KDbtANGO8v9P0eQWRGpT0gejS85WQ5Xa4atWcRSlFL0nXkJlOMyPE5A3vSQacUw7G1PBHs2GIUY4fl4CZyMPxkcEzB2YNuDKfYkUdXz8nUXUzcQ4G3G3vjEJlvuH9E6QTTlHEIRCMwJ3Wjz6WgWDQe1Vq5A98JGTQi2CjAaY8CAwEAAQ=="
    const val app_user =
        "g8ZmsT9TDp66UatEnZSuTvAP+BL208z1joFVW1n1X1O61+IUm09kYuW2XYwNUcSctedbK87Ce71ZrHOrSBN53YZcyvEuQL1WRabEm8LyAqt28qTSC36wI/5dV2S6R0mGLUXv9PZFvVV1c4kIx+H2S5zdX+Kb2ISsys9JDO8JwdVMD5v9IeTRHW4tXg70cKwrrjIFRHpBUrusoixMso54FSrTWWU/LG0VaTfUMc5EO7K7ODBZNCswrwm/VGMyCfQYR+zNQkVGejkGTjiRzdGnuDQPqzRs1Xj1AFwhr06mFJajbC8nxSBfMnRSYqGdSHDK39HDKmaToS1vGMfe24Js1ag1U50zp7D0gv/lgth+Gksf6srBPKznz1tKiH0YRGgXEqjzlp1lrVEp9URFsAVYHUqIbgKLCHnRdRFjvx3lyxSY/rvJw1jnOQfHJaBWC68It4M63EA8wC9vqBW+xRXxjEop2LyKMxY6IRakMYGwgXSwyBpH8T5mf4eUsQHzllRQJB5XbVh3tdW8X8iHBD09+iO3s/MsCvEQWm532AzwM0M3EVdAO+6LOCXma4FSgZMDQtGd9EXa/cu7yXM6ad/H/XmP42xxtsgl2DTNV0XHvm0zAwPWWwrUqyz+z8DzONNMLpiUSPJrpc1eeCjuRt24m4lRj97PQVUTqQTn3uC8blg="
    const val app_user_pwd =
        "LDm2RCS9luLvz3NACMD81ZCeyWyWMZJd5BJIqaHcrekv4WUb4989Qa95WUhTJs2Z6JOpo2fJE3zRS77wXGJeFonfurDAC6pcWh+HzvHyydUpbxFfiBv/Gjs5KRnsYQC8lJvBcMfPjiOyuzINOl2CVmRX/cLJbvEIwhYDow5UmZsCb6mHU2BpyX/9PpMSKjYI++UBgH5VNmOrvTZySirPhN2ToXbLQoZ59nyVGmLs3wfb18RvVj0rKntDvHwHGC4nxSn5Sk7cUgDTPmZCDMig64tzRdoQ9ZxTko9xzDB3d44+Z4iAkrXn5H1vLzZiXQJzXDhX67slE+YxaKf/7Iab/99GtnA9cMzdSyiFXFMd6muGo3+3gDRVD5W1RRInOnn6d939y2UMzXNB2EuXoLyvXHyCMy9eLB/EhJY1hRMiKIs7Aj6lTGmy/vGWM33dBq0MD27GmtThjkfYq6NWK3TmXCJXEMFSElQEGeRI+DkScUHv0nxJwbFbplJJs5KMQ0JAeTyoQF5VdWUG1qmX772DztVAsFRlbpdXlH9oyIXHkKlPP+qDbIlGa2/ji6HveZN7sCUB5CogRNQ5DhFlsRvb3nr3+1uRevxEoXp4AGjCQvVUh3IBy26fjT/cM3dx60J1vjQcfkOR6ih80rd3+WVqCUKaGfMG4BHgyeEBqouNaNs="

    const val appVersion = "MOBIBANKV4.0"
    const val requestFrom = "ANDROID"

    const val accept = "application/json"

    const val accessTypePublic = "PUBLIC"
    const val accessTypePrivate = "PRIVATE"
    const val productCodeFundTransfer = "FTR"
    const val terminalId = "MOBIBANK"
}