package eraapps.bankasia.bdinternetbanking.apps.util.download;

public interface DownloadState {

    enum DownLoadState{
        START, DOWNLOADING, FINISH
    }

    enum DownLoadType{
        PDF, APK, IMAGE
    }
    void downloadProgress(int progress);
    void downloadStatus(DownLoadState state);
}