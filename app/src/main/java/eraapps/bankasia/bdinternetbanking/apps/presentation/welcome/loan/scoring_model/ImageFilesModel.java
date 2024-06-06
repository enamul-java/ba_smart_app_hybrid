package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;

public class ImageFilesModel {
    DatabaseHelper db;
    Context context;
    int photoCount = 0;
    int videoCount = 0;

    long image_file_size_percentage = 0;
    long total_image_file_size = 0;
    private static final String ERROR = "No External storage found!";
    long freeMemory;
    long usedMemory;
    double percentageUsed;
    String internal_storage;

    HashMap<Integer, Integer> PhotoVideoCount2 = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> PhotoVideoCount = new HashMap<Integer, Integer>();


    public ImageFilesModel(Context context) {
        this.context = context;
    }

    public Integer imageFilesModel() {

        int compID;
        String compName;
        int varID;
        String varName;
        String valName1 = null;
        String valName2 = null;
        String valName3 = null;
        String valName4 = null;
        String valName5 = null;
        String valName6 = null;
        String valScore1 = null;
        String valScore2 = null;
        String valScore3 = null;
        String valScore4 = null;
        String valScore5 = null;
        String valScore6 = null;

        int lowerSlab1 = 0;
        int upperSlab1 = 0;
        int lowerSlab2 = 0;
        int upperSlab2 = 0;
        int lowerSlab3 = 0;
        int upperSlab3 = 0;
        int lowerSlab4 = 0;
        int upperSlab4 = 0;
        int lowerSlab5 = 0;
        int upperSlab5 = 0;
        int lowerSlab6 = 0;
        int upperSlab6 = 0;

        int AboveFlag = 0;
        int BelowFlag = 0;

        String valScoreFlag = null;

        int valFlag = 0;
        int insertdata = 0;
        int MaxvalFlag = 0;

        db = new DatabaseHelper(this.context);

        List<Component> components = db.getAllComponents();
        List<Variable> variables = db.getAllVariables();
        List<Value> values = db.getAllValues();

        for (Component cn : components) {
            compID = cn.get_id();
            compName = cn.get_name();
            //Log.d("asd",compName);
            if (compName.contains("Life Habits")) {
                Log.d("bankDepositModel", "compNameEquals: " + compName);
                for (Variable vb : variables) {
                    if (compID == vb.get_component_id()) {
                        varID = vb.get_id();
                        varName = vb.get_name();
                        MaxvalFlag = 0;
                        for (Value vl : values) {

//                                Log.d("asd","get_flag: "+vl.get_value_variable_id());
                            if ((varID == vl.get_value_variable_id())) {
//                                    Log.d("asd","get_value_variable_id: "+vl.get_value_variable_id());
//                                    Log.d("asd","get_flag: "+vl.get_flag());
//                                    Log.d("asd","check: "+vl.get_max_value());
//                                    Log.d("asd","check: "+vl.get_min_value());
                                valScoreFlag = vl.get_flag();
                                MaxvalFlag = MaxvalFlag + 1;
                            }
                        }

                        //Log.d("asd","valFlag: "+valScoreFlag);
                        if (valScoreFlag.contains("0")) {
                            for (Value vl : values) {
                                if ((varID == vl.get_value_variable_id()) && varName.contains("% of image files")) {
                                    valFlag = valFlag + 1;
                                    Log.d("bankDepositModel", "valScoreFlag: " + valScoreFlag);
                                    Log.d("bankDepositModel", "flag value: " + valFlag);
                                    Log.d("bankDepositModel", "varID: " + varID);
                                    Log.d("bankDepositModel", "varName: " + varName);
                                    if (valFlag == 1) {
                                        //Log.d("HEllo","flag1");
                                        valName1 = vl.get_value_name();
                                        valScore1 = vl.get_value_score();
                                        if (valFlag == MaxvalFlag) {
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel", "valName1: " + valName1 + " valScore1: " + valScore1);

                                    }
                                    if (valFlag == 2) {
                                        valName2 = vl.get_value_name();
                                        valScore2 = vl.get_value_score();
                                        if (valFlag == MaxvalFlag) {
                                            valFlag = 0;
                                            break;
                                        }
                                        //Log.d("HEllo","flag2");
                                        Log.d("bankDepositModel", "valName2: " + valName2 + " valScore2: " + valScore2);
                                    }
                                    if (valFlag == 3) {
                                        valName3 = vl.get_value_name();
                                        valScore3 = vl.get_value_score();
                                        Log.d("bankDepositModel", "valName3: " + valName3 + " valScore3: " + valScore3);
                                        if (valFlag == MaxvalFlag) {
                                            valFlag = 0;
                                            break;
                                        }

                                        insertdata = 1;
                                        //Log.d("HEllo","flag3");
                                        String log = "compName: " + compName + " ,varName: " + varName + " valName1"
                                                + valName1 + " valName2 " + valName2 + " valName3 " + valName3 + " valScore1 " + valScore1 + " valScore2 " + valScore2 + " valScore3 " + valScore3;
                                        //Log.d("slabModelArrayList123", "LOG: " + log);
                                    }
                                    if (valFlag == 4) {
                                        valName4 = vl.get_value_name();
                                        valScore4 = vl.get_value_score();
                                        Log.d("bankDepositModel", "valName4: " + valName4 + " valScore4: " + valScore4);
                                        if (valFlag == MaxvalFlag) {
                                            valFlag = 0;
                                            break;
                                        }
                                    }
                                    if (valFlag == 5) {
                                        valName5 = vl.get_value_name();
                                        valScore5 = vl.get_value_score();
                                        Log.d("bankDepositModel", "valName5: " + valName5 + " valScore5: " + valScore5);
                                        if (valFlag == MaxvalFlag) {
                                            valFlag = 0;
                                            break;
                                        }
                                    }
                                    if (valFlag == 6) {
                                        valName6 = vl.get_value_name();
                                        valScore6 = vl.get_value_score();
                                        Log.d("bankDepositModel", "valName6: " + valName6 + " valScore6: " + valScore6);
                                        if (valFlag == MaxvalFlag) {
                                            valFlag = 0;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else if (valScoreFlag.contains("1")) {
                            for (Value vl : values) {
                                if ((varID == vl.get_value_variable_id()) && varName.contains("% of image files")) {
                                    valFlag = valFlag + 1;
                                    Log.d("bankDepositModel1", "valScoreFlag: " + valScoreFlag);
                                    Log.d("bankDepositModel1", "flag value: " + valFlag);
                                    Log.d("bankDepositModel1", "varID: " + varID);
                                    Log.d("bankDepositModel1", "varName: " + varName);
                                    Log.d("bankDepositModel1", "checking flag");
                                    if (valFlag == 1) {
                                        Log.d("bankDepositModel1", " entered flag1");
                                        Log.d("bankDepositModel1", vl.get_min_value());
                                        lowerSlab1 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1", "val" + lowerSlab1);
                                        Log.d("bankDepositModel1", "MaxVal" + MaxvalFlag);
                                        Log.d("bankDepositModel1", vl.get_max_value());
                                        if (vl.get_max_value().contains("null")) {
                                            Log.d("bankDepositModel1", "null value");
                                            upperSlab1 = -1;
                                        } else {
                                            upperSlab1 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1", "upperSlab1" + upperSlab1);
                                        valScore1 = vl.get_value_score();
                                        Log.d("bankDepositModel1", "valScore1" + valScore1);
                                        if (valFlag == MaxvalFlag) {
                                            Log.d("bankDepositModel1", "break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1", "lowerSlab1: " + lowerSlab1 + " upperSlab1: " + upperSlab1);
                                        Log.d("bankDepositModel1", "valScore1: " + valScore1);

                                    }
                                    if (valFlag == 2) {
                                        Log.d("bankDepositModel1", " entered flag2");
                                        Log.d("bankDepositModel1", vl.get_min_value());
                                        lowerSlab2 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1", "val" + lowerSlab2);
                                        Log.d("bankDepositModel1", "MaxVal" + MaxvalFlag);
                                        Log.d("bankDepositModel1", vl.get_max_value());
                                        if (vl.get_max_value().contains("null")) {
                                            Log.d("bankDepositModel1", "null value");
                                            upperSlab2 = -1;
                                        } else {
                                            upperSlab2 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1", "upperSlab2" + upperSlab2);
                                        valScore2 = vl.get_value_score();
                                        Log.d("bankDepositModel1", "valScore2" + valScore2);
                                        if (valFlag == MaxvalFlag) {
                                            Log.d("bankDepositModel1", "break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1", "lowerSlab2: " + lowerSlab2 + " upperSlab2: " + upperSlab2);
                                        Log.d("bankDepositModel1", "valScore2: " + valScore2);

                                    }
                                    if (valFlag == 3) {
                                        Log.d("bankDepositModel1", " entered flag3");
                                        Log.d("bankDepositModel1", vl.get_min_value());
                                        lowerSlab3 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1", "val" + lowerSlab3);
                                        Log.d("bankDepositModel1", "MaxVal" + MaxvalFlag);
                                        Log.d("bankDepositModel1", vl.get_max_value());
                                        if (vl.get_max_value().contains("null")) {
                                            Log.d("bankDepositModel1", "null value");
                                            upperSlab3 = -1;
                                        } else {
                                            upperSlab3 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1", "upperSlab3" + upperSlab3);
                                        valScore3 = vl.get_value_score();
                                        Log.d("bankDepositModel1", "valScore3" + valScore3);
                                        if (valFlag == MaxvalFlag) {
                                            Log.d("bankDepositModel1", "break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1", "lowerSlab3: " + lowerSlab3 + " upperSlab3: " + upperSlab3);
                                        Log.d("bankDepositModel1", "valScore3: " + valScore3);

                                    }
                                    if (valFlag == 4) {
                                        Log.d("bankDepositModel1", " entered flag4");
                                        Log.d("bankDepositModel1", vl.get_min_value());
                                        lowerSlab4 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1", "val" + lowerSlab4);
                                        Log.d("bankDepositModel1", "MaxVal" + MaxvalFlag);
                                        Log.d("bankDepositModel1", vl.get_max_value());
                                        if (vl.get_max_value().contains("null")) {
                                            Log.d("bankDepositModel1", "null value");
                                            upperSlab4 = -1;
                                        } else {
                                            upperSlab4 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1", "upperSlab4" + upperSlab4);
                                        valScore4 = vl.get_value_score();
                                        Log.d("bankDepositModel1", "valScore4" + valScore4);
                                        if (valFlag == MaxvalFlag) {
                                            Log.d("bankDepositModel1", "break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1", "lowerSlab4: " + lowerSlab4 + " upperSlab4: " + upperSlab4);
                                        Log.d("bankDepositModel1", "valScore4: " + valScore4);

                                    }
                                    if (valFlag == 5) {
                                        Log.d("bankDepositModel1", " entered flag5");
                                        Log.d("bankDepositModel1", vl.get_min_value());
                                        lowerSlab5 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1", "val" + lowerSlab5);
                                        Log.d("bankDepositModel1", "MaxVal" + MaxvalFlag);
                                        Log.d("bankDepositModel1", vl.get_max_value());
                                        if (vl.get_max_value().contains("null")) {
                                            Log.d("bankDepositModel1", "null value");
                                            upperSlab5 = -1;
                                        } else {
                                            upperSlab5 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1", "upperSlab5" + upperSlab5);
                                        valScore5 = vl.get_value_score();
                                        Log.d("bankDepositModel1", "valScore5" + valScore5);

                                        Log.d("bankDepositModel1", "lowerSlab5: " + lowerSlab5 + " upperSlab5: " + upperSlab5);
                                        Log.d("bankDepositModel1", "valScore5: " + valScore5);
                                        if (valFlag == MaxvalFlag) {
                                            Log.d("bankDepositModel1", "break");
                                            valFlag = 0;
                                            break;
                                        }


                                    }
                                    if (valFlag == 6) {
                                        Log.d("bankDepositModel1", " entered flag6");
                                        Log.d("bankDepositModel1", vl.get_min_value());
                                        lowerSlab6 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1", "val" + lowerSlab6);
                                        Log.d("bankDepositModel1", "MaxVal" + MaxvalFlag);
                                        Log.d("bankDepositModel1", vl.get_max_value());
                                        if (vl.get_max_value().contains("null")) {
                                            Log.d("bankDepositModel1", "null value");
                                            upperSlab6 = -1;
                                        } else {
                                            upperSlab6 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1", "upperSlab6" + upperSlab6);
                                        valScore6 = vl.get_value_score();
                                        Log.d("bankDepositModel1", "valScore6" + valScore6);
                                        if (valFlag == MaxvalFlag) {
                                            Log.d("bankDepositModel1", "break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1", "lowerSlab6: " + lowerSlab6 + " upperSlab6: " + upperSlab6);
                                        Log.d("bankDepositModel1", "valScore6: " + valScore6);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM.toString()+"/camera/");
        total_image_file_size = image_file_percentage(path);

        internal_storage=getTotalInternalMemorySize();
        freeMemory = getFreeMemorySize();
        usedMemory = Long.parseLong(internal_storage)-freeMemory;
        usedMemory = usedMemory * 1024;
        Log.d("total_used_memory","freeMemory: "+ freeMemory+"GB");
        Log.d("total_used_memory","internal_storage: "+ internal_storage+"GB");
        Log.d("total_used_memory","usedMemory: "+ usedMemory+"MB");

        percentageUsed = (total_image_file_size*100)/(usedMemory);

        int ImageFileScore = 0;
        if ((percentageUsed >= lowerSlab1) && (percentageUsed < upperSlab1)) {
            ImageFileScore = Integer.parseInt(valScore1);
        } else if ((percentageUsed >= lowerSlab2) && (percentageUsed < upperSlab2)) {
            ImageFileScore = Integer.parseInt(valScore2);
        } else if ((percentageUsed >= lowerSlab3) && (percentageUsed < upperSlab3)) {
            ImageFileScore = Integer.parseInt(valScore3);
        } else if ((percentageUsed >= lowerSlab4) && (percentageUsed < upperSlab4)) {
            ImageFileScore = Integer.parseInt(valScore4);
        } else if ((percentageUsed >= lowerSlab5) && (percentageUsed < upperSlab5)) {
            ImageFileScore = Integer.parseInt(valScore5);
        } else if ((percentageUsed >= lowerSlab6) && (percentageUsed < upperSlab6)) {
            ImageFileScore = Integer.parseInt(valScore6);
        }

        return ImageFileScore;
    }



    public Long image_file_percentage(File dir) {
        try {
            String[] files = dir.list();
//            Log.d("count", "TotalFileCount: " + files.length);

            for (int i = 0; i < files.length-1; i++) {
                File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM.toString() + "/camera/" + files[i])));

                Log.d("Files", "FileName:" + files[i]);
                if (files[i].contains("jpg") || files[i].contains("png") || files[i].contains("jpeg")
                        || files[i].contains("IMG")) {

                    long length = file.length();
                    length = length/1024;
                    Log.d("length"," length: "+ length);
                    total_image_file_size = total_image_file_size + length;
                }
            }
            total_image_file_size = total_image_file_size/1024;
            Log.d("total_image_file_size", "total_image_file_size: "+ total_image_file_size + " MB");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total_image_file_size;
    }



    public static long getFreeMemorySize() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable;
        bytesAvailable = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
        long megAvailable = bytesAvailable / (1024 * 1024 * 1024);
        //Log.d("Available_Internal/External_free_Space: " + megAvailable + "MB");
        Log.d("available","megAvailable "+megAvailable);
        return megAvailable;
    }

    public static boolean externalMemoryAvailable() {
        return Environment.
                getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

//2. get Internal memory .


    public static String getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long BlockSize = stat.getBlockSize();
        long TotalBlocks = stat.getBlockCount();
        return formatSize(TotalBlocks * BlockSize);
    }

//3.get External memory .

    public static String getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.
                    getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long BlockSize = stat.getBlockSize();
            long TotalBlocks = stat.getBlockCount();
            return formatSize(TotalBlocks * BlockSize);
        } else {
            return ERROR;
        }
    }


//4. Convert Memory  size MB & KB Format.

    public static String formatSize(long size) {
        String suffixSize = null;

        if (size >= 1024) {
            suffixSize = "KB";
            size /= 1024;
            if (size >= 1024) {
                suffixSize = "MB";
                size /= 1024;
            }
        }
        //Log.d("size", "MB"+size);

        size = size / 1024;
        suffixSize="GB";
        //Log.d("size", "GB"+size);


        StringBuilder BufferSize = new StringBuilder(
                Long.toString(size));

        int commaOffset = BufferSize.length() - 3;
        while (commaOffset > 0) {
            //BufferSize.insert(commaOffset, ',');
            commaOffset -= 3;
        }


        return BufferSize.toString();
    }

}
