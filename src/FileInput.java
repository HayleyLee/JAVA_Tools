import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileInput {
    public static HashMap<Integer,String> getFileText(String path) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        if(path!=null && !path.equals("")){
            File file = new File(path);
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(file));
                String tempString;
                int line=0;
                while ((tempString = reader.readLine()) != null) {
                    assert tempString.contains(","):"1.Error! 文件<"+path+">内容不合法，请检查第"+line+"行,内容："+tempString;
                    String[] splitLine = tempString.split(",");
                    assert splitLine.length==2:"2.Error! 文件<"+path+">内容不合法，请检查第"+line+"行,内容："+tempString;
                    int orderId = Integer.parseInt(splitLine[0]);
                    String time = splitLine[1];
                    assert orderId>0&&!time.equals(""):"3.Error! 文件<"+path+">内容不合法，请检查第"+line+"行";
                    assert !hashMap.containsKey(orderId):"该文件内有订单号重复！程序中断！请检查文件<"+path+">！请检查订单号："+orderId;
                    hashMap.put(orderId,time);
                    line+=1;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return hashMap;
    }

    public static ArrayList<Integer> getCsvFile(String path) {
        ArrayList<Integer> list = new ArrayList<>();
        File file = new File(path);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine())!=null){
                list.add(Integer.parseInt(tempString));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public static void outPutFile(String outPutPath, HashMap<Integer, ArrayList<ServiceTable>> map) throws IOException {
        for(Map.Entry<Integer,ArrayList<ServiceTable>> entry : map.entrySet()){
            ArrayList<ServiceTable> serviceTableList = entry.getValue();
            String oldName_id = serviceTableList.get(0).getOldName()+serviceTableList.get(0).getOldId();
            String path = outPutPath+oldName_id+".csv";
            File filename = new File(path);
            filename.createNewFile();
            try {
                BufferedWriter csv = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "GB2312"));
//                BufferedWriter csv = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "gbk"));
                String title = "序号,订单号,姓名,收货人地址,服务名称,服务开始时间,服务结束时间,服务人,服务商家";
                csv.write(title);
                csv.newLine();
                int j=1;
                for (ServiceTable serviceTable : serviceTableList) {
                    csv.write(j + "," + serviceTable.toString());
                    csv.newLine();
                    j += 1;
                }
                csv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("文件："+path+"创建完成...");
        }
    }

    public static ArrayList<OracleCertification> getOCP_QuationFile(String path) {
        ArrayList<OracleCertification> list = new ArrayList<>();
        File file = new File(path);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString;
            StringBuilder question = new StringBuilder();
            StringBuilder optionA = new StringBuilder();
            StringBuilder optionB = new StringBuilder();
            StringBuilder optionC = new StringBuilder();
            StringBuilder optionD = new StringBuilder();
            String answer = "";
            String img = "";
            while ((tempString = reader.readLine()) != null){
                tempString = tempString.replaceAll("'","\\\\'");
                tempString = tempString.replaceAll("\"","\\\\\"");
                tempString += "\\\\g";
                System.out.println(tempString);
                if(tempString.contains("A. ")) optionA.append(tempString);
                else if(tempString.contains("B. ")) optionB.append(tempString);
                else if(tempString.contains("C. ")) optionC.append(tempString);
                else if(tempString.contains("D. ")) optionD.append(tempString);
                else if(tempString.equals("!!!\\\\g")){
                    list.add(new OracleCertification(0, question.toString(), optionA.toString(), optionB.toString(), optionC.toString(), optionD.toString(), answer, img));
                    question = new StringBuilder();
                    optionA = new StringBuilder();
                    optionB = new StringBuilder();
                    optionC = new StringBuilder();
                    optionD = new StringBuilder();
                }
                else question.append(tempString);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getOCP_AnswerFile(String path) {
        ArrayList<String> answers = new ArrayList<>();
        File file = new File(path);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString;
            String answer = null;
            StringBuilder tempAnswer = new StringBuilder();
            while ((tempString = reader.readLine()) != null){
                tempString = tempString.replaceAll("'","\\\\'");
                tempString = tempString.replaceAll("\"","\\\\\"");
                if(tempString.length()>3) {
                    String head = tempString.substring(0, 3);
                    if(head.contains(".") && Character.isDigit(head.charAt(0))){
                        answer = tempAnswer.toString();
                        answers.add(answer);
                        tempAnswer = new StringBuilder();
                    }
                    if(head.contains("###")) answers.add(answer);
                    tempAnswer.append(tempString);
                }
                else throw new Exception("行长度不足3,它的上一行内容为："+answer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        answers.remove(0);
        return answers;
    }
}