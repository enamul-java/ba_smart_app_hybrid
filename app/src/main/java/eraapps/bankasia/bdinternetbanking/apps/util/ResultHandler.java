package eraapps.bankasia.bdinternetbanking.apps.util;

public interface ResultHandler {
    void success(String result);
    void failed(String lang, String message);
}
