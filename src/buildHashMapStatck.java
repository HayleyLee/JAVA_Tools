import java.util.HashMap;

public class buildHashMapStatck {
    HashMap<String,Object> map;
    HashMap<String,Object> buildMap(String[] arr){
        map.put("cusname",arr[0]);
        map.put("sex",arr[1]);
        map.put("idNumber",arr[2]);
        //....
        return map;
    }
}
