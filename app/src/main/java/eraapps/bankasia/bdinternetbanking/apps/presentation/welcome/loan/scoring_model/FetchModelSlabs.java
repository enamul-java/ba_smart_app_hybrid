package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import eraapps.bankasia.bdinternetbanking.apps.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FetchModelSlabs {
    private Context context;

    private ArrayList<SMSModel> SMSModelArrayList;
    private Button button7;
    private Button button2;
    private String sender;
    private String msg;
    private String formattedDate;
    String variablesBody;
    String value_variable_id;
    String category;
    String score;
    TextView textView;
    String component_data_source_id;
    String component_name;
    String component_weight;
    String component_max_point;

    String value_name;
    String value_score;
    String flag;
    String min_value;
    String max_value;

    String variable_component_id;
    String variable_name;

    String dialogueBoxMsg;

    int component_id;
    int variable_id;
    int value_id;

    //For showing list view
    private ListView listView;

    ArrayList<String> arrayList = new ArrayList<>();

    //For fetching phone number
    private static String phoneNumber;
    private String EditedPhoneNumber;
    private static final int PHONE_NUMBER_HINT = 100;
    private final int PERMISSION_REQ_CODE = 200;
    DatabaseHelper db;

    int Finalscore;

    public FetchModelSlabs(Context context){
        this.context = context;
    }
    public void fetchModelSlabs(){

        Log.e("statr #1***** ->", "DatabaseHelper---");
       // Toast.makeText(context.getApplicationContext(),"Done!",Toast.LENGTH_SHORT).show();

        db = new DatabaseHelper(context.getApplicationContext());
        Log.e("statr #2***** ->", "DatabaseHelper---");
        try {


                    try{
                        context.deleteDatabase("ModelDB");
                    }catch (Exception e){
                        Log.d("Error deleteDatabase ->", e.getMessage());
                        e.printStackTrace();
                    }

            Log.e("statr #3***** ->", "DatabaseHelper---");
                    // Stuff that updates the UI
                    JSONArray jsonArray = new JSONArray();
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    OkHttpClient okHttpClient = new OkHttpClient();

                    RequestBody body = RequestBody.create(jsonArray.toString(), JSON); // new
                    // RequestBody body = RequestBody.create(JSON, json); // old
                    Request request = new Request.Builder()
                            .url(context.getResources().getString(R.string.server_ip)+"/AllMetaSlabs/get")
                            .get()
                            .build();
            Log.e("statr #4***** ->", "DatabaseHelper---");
                    okHttpClient.newCall(request).enqueue(new Callback() {

                        @Override
                        public void onFailure(Call call, IOException e) {
                            // Toast.makeText(MainActivity.this,"network not found",Toast.LENGTH_LONG).show();
                            Log.d("showModelResponse", e.getMessage());
                            Log.d("Error onFailure ->", e.getMessage());
                        }

                        // Getting response from python backend
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.d("showModelResponse", "No Error");
                            Log.e("apic calling ---->", "No Error");

                            ResponseBody responseBody = response.body();
                            String body = responseBody.string();
                            Log.d("showModelResponse", "responseBody: " + body);

                            try {
                                JSONObject reader = new JSONObject(body);
                                JSONObject Message  = reader.getJSONObject("data");
                                Log.d("JSONResponse", "Message: " + Message);

                                JSONObject componentsReader = new JSONObject(Message.toString());
                                String componentsBody  = componentsReader.getString("components");
                                Log.d("JSONResponse", "components: " + componentsBody);


                                String variablesBody  = componentsReader.getString("variables");
                                Log.d("JSONResponse", "variables: " + variablesBody);

                                String valuesBody  = componentsReader.getString("values");
                                Log.d("JSONResponse", "values: " + valuesBody);



                                JSONArray jsonArray = new JSONArray(componentsBody);
                                Log.d("JSONResponse","jsonArray: "+jsonArray);

                                for(int i = 0 ; i<jsonArray.length();i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Log.d("JSONResponse","check:"+jsonObject);
                                    Log.d("showModelResponse", "data_source_id: " + jsonObject.get("data_source_id"));
                                    Log.d("showModelResponse", "component_name: " + jsonObject.get("component_name"));
                                    Log.d("showModelResponse", "component_weight: " + jsonObject.get("component_weight"));
                                    //Log.d("showModelResponse", "component_max_point: " + jsonObject.get("component_max_point"));

                                    component_id = Integer.parseInt(jsonObject.get("component_id").toString());
                                    component_data_source_id=jsonObject.get("data_source_id").toString();
                                    component_name=jsonObject.get("component_name").toString();
                                    component_weight=jsonObject.get("component_weight").toString();
                                    //component_max_point=jsonObject.get("component_max_point").toString();

                                    Log.d("check","name: "+component_name);
                                    //componentAdapter.insertData(component_data_source_id,component_name, component_weight,component_max_point);
                                    db.createComponent(component_id, component_data_source_id,component_name, component_weight);
                                }
                                //String data = componentAdapter.getData();



                                try {
                                    JSONArray jsonArray2 = new JSONArray(variablesBody);
                                    //JSONArray jsonArray3 = jsonArray2.getJSONArray(0);
                                    //Log.d("showModelResponse", "jsonArray3 length: " + jsonArray3.length());

                                    for (int i = 0; i < jsonArray2.length(); i++) {
                                        JSONObject jsonObject = jsonArray2.getJSONObject(i);
                                        Log.d("showModelResponseVar", "component_id: " + jsonObject.get("component_id"));
                                        Log.d("showModelResponseVar", "variable_name: " + jsonObject.get("variable_name"));

                                        variable_id = Integer.parseInt(jsonObject.get("variable_id").toString());
                                        variable_component_id=jsonObject.get("component_id").toString();
                                        variable_name=jsonObject.get("variable_name").toString();

                                        db.createVariable(variable_id,variable_component_id,variable_name);

                                        //db_var.addVariable(new Variable((Integer.parseInt(variable_component_id)), variable_name));

                                    }
                                    //db.closeDB();
                                } catch(JSONException e){
                                    Log.e("statr #7***** ->", "DatabaseHelper---");
                                    Log.d("Error JSONException ->", e.getMessage());
                                    e.printStackTrace();
                                }

                                try {
                                    JSONArray jsonArray3 = new JSONArray(valuesBody);
                                    //JSONArray jsonArray5 = jsonArray4.getJSONArray(0);
                                    //Log.d("showModelResponse", "jsonArray3 length: " + jsonArray3.length());

                                    for (int i = 0; i < jsonArray3.length(); i++) {
                                        JSONObject jsonObject = jsonArray3.getJSONObject(i);
                                        Log.d("showModelResponseval", "value_name: " + jsonObject.get("value_name"));
                                        Log.d("showModelResponseval", "value_score: " + jsonObject.get("value_score"));
                                        Log.d("showModelResponseval", "value_variable_id: " + jsonObject.get("variable_id"));

                                        value_id = Integer.parseInt(jsonObject.get("value_id").toString());
                                        value_variable_id=jsonObject.get("variable_id").toString();
                                        value_name=jsonObject.get("value_name").toString();
                                        value_score=jsonObject.get("value_score").toString();
                                        flag=jsonObject.get("flag").toString();
                                        min_value=jsonObject.get("min_value").toString();
                                        max_value=jsonObject.get("max_value").toString();
//                                        flag = 1;
//                                        min_value = 5;
//                                        max_value = 10;

                                        db.createValue(value_id,value_variable_id,value_name,value_score,flag,min_value,max_value);
                                        //db.createVariable(variable_component_id,variable_name);
                                        //db_var.addVariable(new Variable((Integer.parseInt(variable_component_id)), variable_name));
                                    }
//                                    List<Component> components = db.getAllComponents();
//                                    for (Component cn : components) {
//                                        String log = "Id: " + cn.get_id() + " ,Name: " + cn.get_name();
//                                        // Writing Contacts to log
//                                        Log.d("getAllComponents", "log: "+log);
//                                    }
//                                    //db.closeDB();
                                } catch(JSONException e){
                                    Log.e("statr #5***** ->", "DatabaseHelper---");
                                    e.printStackTrace();
                                }
                            } catch (JSONException e) {
                                Log.e("statr #6***** ->", "DatabaseHelper---");
                                e.printStackTrace();
                            }
                        }

                    });



        } catch (Exception e) {
            Log.d("Error---->", e.getMessage());
            e.printStackTrace();

        }
    }
    }

