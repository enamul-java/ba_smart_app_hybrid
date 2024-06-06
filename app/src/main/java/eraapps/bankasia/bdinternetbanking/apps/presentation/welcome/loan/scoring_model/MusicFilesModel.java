package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.List;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;

public class MusicFilesModel {

    DatabaseHelper db;
    Context context;
    int musicCount = 0;

    public MusicFilesModel(Context context){
        this.context = context ;
    }
    public Integer musicFilesModel() {

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

        int valFlag=0;
        int insertdata=0;
        int MaxvalFlag=0;

        db = new DatabaseHelper(this.context);

        List<Component> components = db.getAllComponents();
        List<Variable> variables = db.getAllVariables();
        List<Value> values = db.getAllValues();

        for (Component cn : components) {
            compID = cn.get_id();
            compName = cn.get_name();
            //Log.d("asd",compName);
            if(compName.contains("Life Habits"))
            {
                Log.d("bankDepositModel","compNameEquals: "+compName);
                for (Variable vb : variables) {
                    if(compID == vb.get_component_id())
                    {
                        varID = vb.get_id();
                        varName = vb.get_name();
                        MaxvalFlag=0;
                        for (Value vl : values) {

//                                Log.d("asd","get_flag: "+vl.get_value_variable_id());
                            if((varID == vl.get_value_variable_id())){
//                                    Log.d("asd","get_value_variable_id: "+vl.get_value_variable_id());
//                                    Log.d("asd","get_flag: "+vl.get_flag());
//                                    Log.d("asd","check: "+vl.get_max_value());
//                                    Log.d("asd","check: "+vl.get_min_value());
                                valScoreFlag = vl.get_flag();
                                MaxvalFlag = MaxvalFlag + 1;
                            }
                        }

                        //Log.d("asd","valFlag: "+valScoreFlag);
                        if(valScoreFlag.contains("0")){
                            for (Value vl : values) {
                                if((varID == vl.get_value_variable_id()) && varName.contains("% of music files"))
                                {
                                    valFlag = valFlag + 1;
                                    Log.d("bankDepositModel", "valScoreFlag: "+valScoreFlag);
                                    Log.d("bankDepositModel","flag value: " + valFlag);
                                    Log.d("bankDepositModel","varID: "+varID);
                                    Log.d("bankDepositModel","varName: "+varName);
                                    if(valFlag==1)
                                    {
                                        //Log.d("HEllo","flag1");
                                        valName1 = vl.get_value_name();
                                        valScore1 = vl.get_value_score();
                                        if(valFlag == MaxvalFlag)
                                        {
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel","valName1: "+valName1+" valScore1: "+valScore1);

                                    }
                                    if(valFlag==2)
                                    {
                                        valName2 = vl.get_value_name();
                                        valScore2 = vl.get_value_score();
                                        if(valFlag == MaxvalFlag)
                                        {
                                            valFlag = 0;
                                            break;
                                        }
                                        //Log.d("HEllo","flag2");
                                        Log.d("bankDepositModel","valName2: "+valName2+" valScore2: "+valScore2);
                                    }
                                    if(valFlag == 3)
                                    {
                                        valName3 = vl.get_value_name();
                                        valScore3 = vl.get_value_score();
                                        Log.d("bankDepositModel","valName3: "+valName3+" valScore3: "+valScore3);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            valFlag = 0;
                                            break;
                                        }

                                        insertdata =1;
                                        //Log.d("HEllo","flag3");
                                        String log = "compName: " +compName+" ,varName: "+varName+" valName1"
                                                +valName1+" valName2 "+valName2+" valName3 "+valName3+" valScore1 "+valScore1+" valScore2 "+valScore2+" valScore3 "+valScore3;
                                        //Log.d("slabModelArrayList123", "LOG: " + log);
                                    }
                                    if(valFlag == 4)
                                    {
                                        valName4 = vl.get_value_name();
                                        valScore4 = vl.get_value_score();
                                        Log.d("bankDepositModel","valName4: "+valName4+" valScore4: "+valScore4);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            valFlag = 0;
                                            break;
                                        }
                                    }
                                    if(valFlag == 5)
                                    {
                                        valName5 = vl.get_value_name();
                                        valScore5 = vl.get_value_score();
                                        Log.d("bankDepositModel","valName5: "+valName5+" valScore5: "+valScore5);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            valFlag = 0;
                                            break;
                                        }
                                    }
                                    if(valFlag == 6)
                                    {
                                        valName6 = vl.get_value_name();
                                        valScore6 = vl.get_value_score();
                                        Log.d("bankDepositModel","valName6: "+valName6+" valScore6: "+valScore6);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            valFlag = 0;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        else if (valScoreFlag.contains("1")){
                            for (Value vl : values) {
                                if((varID == vl.get_value_variable_id()) && varName.contains("% of music files"))
                                {
                                    valFlag = valFlag + 1;
                                    Log.d("bankDepositModel1", "valScoreFlag: "+valScoreFlag);
                                    Log.d("bankDepositModel1","flag value: " + valFlag);
                                    Log.d("bankDepositModel1","varID: "+varID);
                                    Log.d("bankDepositModel1","varName: "+varName);
                                    Log.d("bankDepositModel1","checking flag");
                                    if(valFlag==1)
                                    {
                                        Log.d("bankDepositModel1"," entered flag1");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab1 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab1);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab1 = -1;
                                        }
                                        else{
                                            upperSlab1 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab1"+upperSlab1);
                                        valScore1 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore1"+valScore1);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab1: "+lowerSlab1+" upperSlab1: "+upperSlab1);
                                        Log.d("bankDepositModel1","valScore1: "+valScore1);

                                    }
                                    if(valFlag==2)
                                    {
                                        Log.d("bankDepositModel1"," entered flag2");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab2 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab2);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab2 = -1;
                                        }
                                        else{
                                            upperSlab2 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab2"+upperSlab2);
                                        valScore2 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore2"+valScore2);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab2: "+lowerSlab2+" upperSlab2: "+upperSlab2);
                                        Log.d("bankDepositModel1","valScore2: "+valScore2);

                                    }
                                    if(valFlag==3)
                                    {
                                        Log.d("bankDepositModel1"," entered flag3");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab3 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab3);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab3 = -1;
                                        }
                                        else{
                                            upperSlab3 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab3"+upperSlab3);
                                        valScore3 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore3"+valScore3);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab3: "+lowerSlab3+" upperSlab3: "+upperSlab3);
                                        Log.d("bankDepositModel1","valScore3: "+valScore3);

                                    }
                                    if(valFlag==4)
                                    {
                                        Log.d("bankDepositModel1"," entered flag4");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab4 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab4);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab4 = -1;
                                        }
                                        else{
                                            upperSlab4 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab4"+upperSlab4);
                                        valScore4 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore4"+valScore4);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab4: "+lowerSlab4+" upperSlab4: "+upperSlab4);
                                        Log.d("bankDepositModel1","valScore4: "+valScore4);

                                    }
                                    if(valFlag==5)
                                    {
                                        Log.d("bankDepositModel1"," entered flag5");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab5 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab5);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab5 = -1;
                                        }
                                        else{
                                            upperSlab5 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab5"+upperSlab5);
                                        valScore5 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore5"+valScore5);

                                        Log.d("bankDepositModel1","lowerSlab5: "+lowerSlab5+" upperSlab5: "+upperSlab5);
                                        Log.d("bankDepositModel1","valScore5: "+valScore5);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }


                                    }
                                    if(valFlag==6)
                                    {
                                        Log.d("bankDepositModel1"," entered flag6");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab6 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab6);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab6 = -1;
                                        }
                                        else{
                                            upperSlab6 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab6"+upperSlab6);
                                        valScore6 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore6"+valScore6);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab6: "+lowerSlab6+" upperSlab6: "+upperSlab6);
                                        Log.d("bankDepositModel1","valScore6: "+valScore6);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        musicCount = musicFile();
        Log.d("count", "musicCount: " + musicCount);

        int MusicFileScore = 0;
        if((musicCount>=lowerSlab1)&&(musicCount<upperSlab1)){
            MusicFileScore = Integer.parseInt(valScore1);
        }
        else if((musicCount>=lowerSlab2)&&(musicCount<upperSlab2)){
            MusicFileScore = Integer.parseInt(valScore2);
        }
        else if((musicCount>=lowerSlab3)&&(musicCount<upperSlab3)){
            MusicFileScore = Integer.parseInt(valScore3);
        }
        else if((musicCount>=lowerSlab4)&&(musicCount<upperSlab4)){
            MusicFileScore = Integer.parseInt(valScore4);
        }
        else if((musicCount>=lowerSlab5)&&(musicCount<upperSlab5)){
            MusicFileScore = Integer.parseInt(valScore5);
        }
        else if((musicCount>=lowerSlab6)&&(musicCount<upperSlab6)){
            MusicFileScore = Integer.parseInt(valScore6);
        }

        return MusicFileScore;
    }



    public Integer musicFile(){
        //MUSIC FILE ACCESS
        musicCount = 0;
        Cursor c = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.DISPLAY_NAME }, "1=1", null, null);

        c.moveToFirst();
        do{
            Log.d("music","ID: "+c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));
            Log.d("music","DATA: "+c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
            Log.d("music","ARTIST: "+c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
            Log.d("music","ALBUM: "+c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
            Log.d("music","DISPLAY_NAME: "+c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
            musicCount++;
        }while(c.moveToNext());
        Log.d("count", "musicCount: " + musicCount);
        return musicCount;
    }
}
