package eraapps.bankasia.bdinternetbanking.apps.util.download;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadFineName {
    public static String getFileName(String baseName, DownloadState.DownLoadType type){
        String dateString = "";
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_mm_ss");
            dateString = sdf.format(new Date());
        }catch (Exception e){
            dateString = "dd_mm_yyy_mm_ss";
        }
        return baseName+dateString+getFileType(type);
    }

    public static String getFileType(DownloadState.DownLoadType type){
        String name = ".pdf";
        switch (type){
            case PDF:
                name = ".pdf";
                break;
            case APK:
                name = ".apk";
                break;
            case IMAGE:
                name = ".png";
                break;
            default:
                name = ".pdf";
                break;
        }
        return name;
    }
}
