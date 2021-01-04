
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class main {
    public static void main(String[] args) {
        String buffer = null;
        System.out.println("请选择功能:");
        System.out.println("功能1:新增评论（输入1）");
        System.out.println("功能2:新增回访记录（输入2）");
        System.out.println("功能3:修改订单状态为1或者2的订单,将其改为状态4（输入3）");
        System.out.println("功能4:修改订单状态为3的订单,将其改为状态4（输入4）");
        System.out.println("功能5:一键清除所有重复订单（输入5）");
        System.out.println("功能6:删除指定的订单（输入6）");
        System.out.println("功能7:智能重建订单表与老人表的手机号关联信息（输入7）");
        System.out.println("功能8:解决订单表与老人表信息重复问题（输入8）");
        System.out.println("功能9:创建老人信息（简易版）（输入9）");
        System.out.println("功能10:写入订单数据（客户类型）（输入10）");
        System.out.println("功能11:老人兴趣偏好算法（根据订单简单推算）（输入11）");
        System.out.println("功能12:一键生成订单服务类型（输入12）");
        System.out.println("功能13:智能修改问题订单服务时间（输入13）");
        System.out.println("功能14:一键补全 新客户类型表（输入14）");
        System.out.println("功能15:老人数据录入（输入15）");
        System.out.println("功能16:语音数据迁移（输入16）");
        System.out.println("功能17:sql转换（输入17）");
        System.out.println("功能18:查找三城区老人通话记录（输入18）");
        System.out.println("功能19:根据服务类型查找老人通话和订单数量(输入19)");
        System.out.println("功能20:根据客户类型导出服务项目、服务商家、服务人员、订单号码、回访评价、订单评价(输入20)");
        System.out.println("功能21:根据订单id智能生成新服务时间及下单时间");
        System.out.println("功能22:根据订单id智能生成服务总表");
        System.out.println("功能23:读取OCP试题");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert buffer != null;
        int i = Integer.parseInt(buffer);
        if (i == 1) {
            function1();
        } else if (i == 2) {
            function2();
        } else if (i == 3) {
            function4();
        } else if (i == 4) {
            function3();
        } else if (i == 5) {
            function5();
        } else if (i == 6) {
            function6();
        } else if (i == 7) {
            function7();
        } else if (i == 8) {
            function8();
        } else if (i == 9) {
            chooseModel();
        } else if (i == 10) {
            function11();
        } else if (i == 11) {
            function12();
        }else if (i == 12) {
            function13();
        }else if (i == 13) {
            function14();
        }else if (i == 14) {
            function15();
        }else if (i == 15) {
            function16();
        }else if (i == 16) {
            function17();
        }else if (i == 17) {
            function18();
        }else if (i == 18) {
            function19();
        }else if (i == 19) {
            function20();
        }else if (i == 20) {
            function21();
        }else if (i == 21) {
            function22();
        }else if (i == 22) {
            function23();
        }else if (i == 23) {
            function24();
        }
        else {
            System.out.println("您输入的值不正确，请重新输入");
            String[] str = new String[0];
            main(str);
        }
    }

    private static void function24() {
        JDBC jdbc = new JDBC();
        String buffer;
        String buffer2;
        System.out.println("请输入完整的题库文件路径，按以下格式:");
        System.out.println("/usr/local/xxx.txt");
        InputStreamReader is0 = new InputStreamReader(System.in);
        BufferedReader br0 = new BufferedReader(is0);
        try{
            buffer = br0.readLine();
            if(buffer.contains("/") && buffer.contains(".")){
                System.out.println("请输入完整的答案文件路径，按以下格式:");
                System.out.println("/usr/local/xxx.txt");
                InputStreamReader is1 = new InputStreamReader(System.in);
                BufferedReader br1 = new BufferedReader(is1);
                buffer2 = br1.readLine();
                ArrayList<OracleCertification> list = FileInput.getOCP_QuationFile(buffer);
                ArrayList<String> list2 = FileInput.getOCP_AnswerFile(buffer2);
                if(list.size()==list2.size()){
                    for(int i=0;i<list.size();i++){
                        OracleCertification ocp = list.get(i);
                        ocp.setAnswer(list2.get(i));
                    }
                    jdbc.instrOCP_Question(list);
                }
                else {
                    throw new Exception("数量不相等,question:"+list.size()+",answer:"+list2.size());
                }
            }
            else {
                System.out.println("输入有误，请重试");
                br0.close();
                is0.close();
                String[] str = new String[0];
                main(str);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void function23() {
        JDBC jdbc = new JDBC();
        String buffer = null;
        System.out.println("请输入完整的文件路径，按以下格式:");
        System.out.println("/usr/local/xxx.txt");
        InputStreamReader is0 = new InputStreamReader(System.in);
        BufferedReader br0 = new BufferedReader(is0);
        try{
            buffer = br0.readLine();
            if(buffer.contains("/")&&buffer.contains(".")){
                ArrayList<Integer> list = FileInput.getCsvFile(buffer);
                assert list.size()>0:"无法获取任何数据，请检查文件内容是否合法";
                System.out.println("请输入输出文件的路径，按以下格式:");
                System.out.println("/usr/local/");
                InputStreamReader is1 = new InputStreamReader(System.in);
                BufferedReader br1 = new BufferedReader(is1);
                try{
                    buffer = br1.readLine();
                    if(buffer.contains("/")){
                        String outPutPath = buffer;
                        System.out.println("警告！！！该功能开始后请不要手动停止，功能耗时将会略久，请考虑机器配置后谨慎操作");
                        System.out.println("即将会在你给定的文件路径下出现大约1000个文件，请确认磁盘空间后谨慎操作，文件名以老人姓名命名，文件格式为csv文件，即以下格式");
                        System.out.println("/usr/local/张三.csv    /usr/local/李四.csv");
                        System.out.println("请仔细阅读以上警告，明确后输入yes 功能将会开始运行...");
                        InputStreamReader is2 = new InputStreamReader(System.in);
                        BufferedReader br2 = new BufferedReader(is2);
                        try{
                            buffer = br2.readLine();
                            if(buffer.equals("yes")){
                                HashMap<Integer, ArrayList<ServiceTable>> map = jdbc.createServiceTableFile(list);
                                FileInput.outPutFile(outPutPath,map);
                            }
                            else {
                                System.out.println("你已退出");
                                br2.close();
                                is2.close();
                                br1.close();
                                is1.close();
                                br0.close();
                                is0.close();
                                String[] str = new String[0];
                                main(str);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        System.out.println("任务完成");
                    }
                    else {
                        System.out.println("输入有误，请重试");
                        br1.close();
                        is1.close();
                        br0.close();
                        is0.close();
                        String[] str = new String[0];
                        main(str);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("输入有误，请重试");
                br0.close();
                is0.close();
                String[] str = new String[0];
                main(str);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function22() {
        JDBC jdbc = new JDBC();
        String buffer = null;
        System.out.println("请输入完整的文件路径，按以下格式:");
        System.out.println("/usr/local/xxx.txt");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try{
            buffer = br.readLine();
            if(buffer.contains("/")&&buffer.contains(".")){
                HashMap<Integer, String> hashMap = FileInput.getFileText(buffer);
                assert hashMap.size()>0:"文件内容为空";
                jdbc.changeOrderTimeByOrderId(hashMap);
                System.out.println("任务完成！");
            }
            else {
                System.out.println("输入有误，请重试");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function21() {
        JDBC jdbc = new JDBC();
        String buffer = null;
        System.out.println("请输入客户类型代码:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try{
            buffer = br.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assert buffer != null;
        int clientType = Integer.parseInt(buffer);
        if(clientType>0){
            jdbc.findBackSystemDataByClientType(clientType);
        }
        else System.out.println("客户类型不正确，请重试");
        System.out.println("任务完成!");
        String[] str = new String[0];
        main(str);
    }
    private static void function20() {
        JDBC jdbc = new JDBC();
        String buffer = null;
        System.out.println("请输入服务类型代码:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try{
            buffer = br.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assert buffer != null;
        int serType = Integer.parseInt(buffer);
        if(serType>0){
            jdbc.findOldServiceCountByServerType(serType);
        }
        System.out.println("任务完成!");
        String[] str = new String[0];
        main(str);
    }
    private static void function19() {
        JDBC jdbc = new JDBC();
        String buffer = null;
        System.out.println("请输入城区代码:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try{
            buffer = br.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        assert buffer != null;
        if(buffer.equals("450421") || buffer.equals("450403") || buffer.equals("450425") || buffer.equals("450424")){
            jdbc.findOldByHometownAreaCode(buffer);
        }
        else {
            System.out.println("你输入的城区代码不正确，请重试");
            String[] str = new String[0];
            main(str);
        }
        System.out.println("任务完成!");
        String[] str = new String[0];
        main(str);
    }
    private static void function18() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        JDBC jdbc = new JDBC();
        try {
            fis = new FileInputStream("/usr/local/newLiancdrall.sql");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            int i=1;
            while (br.readLine() != null) {
                if(br.readLine().equals("end")){
                    break;
                }
                String data = br.readLine();
                String sql = "INSERT INTO cdr_table_ab(caller_id_name,caller_id_number,destination_number,context,start_stamp,answer_stamp,end_stamp,uduration,billsec,hangup_cause,uuid,call_uuid,account_code,liancreatetime,memo,callee_id_number,call_type,recordingfile,ReCallstatus,RecallUserID,account_billed) VALUE ("+data+");";
                if(jdbc.pbxDataChange(sql)==1){
                    System.out.println("已完成"+i+"条");
                }
                else {
                    System.out.println("----------------------------");
                    System.out.println("出现错误！！！");
                    System.out.println("错误语句:"+sql);
                    System.out.println("程序中止！！！");
                    System.out.println("----------------------------");
                    System.exit(0);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void function17() {
        JDBC jdbc = new JDBC();
        String buffer = null;
        System.out.println("请输入迁移SQL:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(buffer!=null){
            String[] sql_array = buffer.split(";");
            if(sql_array.length>0){
                System.out.println("即将开始执行迁移工作:请输入yes确认开始；no取消");
                try {
                    buffer = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(buffer.equals("yes")){
                    for(int i=0;i<sql_array.length;i++){
                        if(jdbc.pbxDataChange(sql_array[i])==1){
                            System.out.println("已完成"+i+"条");
                        }
                        else {
                            System.out.println("----------------------------");
                            System.out.println("出现错误！！！");
                            System.out.println("错误语句:"+sql_array[i]);
                            System.out.println("程序中止！！！");
                            System.out.println("----------------------------");
                            System.exit(0);
                        }
                    }
                }
            }
        }
        System.out.println("任务完成!");
        String[] str = new String[0];
        main(str);
    }
    private static void function16() {
        String buffer;
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        try {
            while (true) {
                System.out.println("老人姓名，性别，出生日期。。。");
                InputStreamReader is = new InputStreamReader(System.in);
                BufferedReader bf = new BufferedReader(is);
                buffer = bf.readLine();
                if(buffer.contains("stop")){
                    break;
                }
                String[] arr = buffer.split(",");
                list.add(new buildHashMapStatck().buildMap(arr));
                bf.close();
            }
            JDBC jdbc = new JDBC();
            for(HashMap<String,Object> m : list){
                for(String key:m.keySet()) {
                    if(m.get(key).equals("null")){
                        m.put(key,null);
                    }
                }
                Integer r = jdbc.findOldInfoByIdNumber(String.valueOf(m.get("idNumber")));
                if(r==1) {
                    //update
//                    if(jdbc.updateOldNewInfo(m)!=1) System.out.println("Error! oldIdNumber:"+m.get("IdNumber"));
                }
                else if(r==0){
//                    if(jdbc.instrOldNewInfo(m)!=1) System.out.println("Error! oldIdNumber:"+m.get("IdNumber"));
                }
                else {
                    System.out.println("查找老人出错！！！查找返回结果："+r);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function15() {
        String buffer=null;
        System.out.println("将会为新客户类型表智能补全数据，是否确认执行（不可恢复！）输入yes或者no");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(is);
        try {
            buffer = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(buffer!=null){
            if(buffer.equals("yes")){
                JDBC j = new JDBC();
                System.out.println("执行中...请稍等...");
                if(j.instrClientTypeTable()>0) System.out.println("任务执行完成!");
                else System.out.println("任务执行失败");
            }
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function14() {
        String buffer=null;
        System.out.println("将会为服务时间（秒针相同）的订单智能重建时间，是否确认执行（不可恢复！）输入yes或者no");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(is);
        try {
            buffer = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(buffer!=null){
            if(buffer.equals("yes")){
                JDBC j = new JDBC();
                System.out.println("执行中...请稍等...");
                if(j.changeOrderTime()>0) System.out.println("任务执行完成!");
                else System.out.println("任务执行失败");
            }
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function13() {
        System.out.println("正在执行一键生成工作...请稍等...");
        JDBC j = new JDBC();
        Integer rsl = j.createServeTypeByOrder();
        if(rsl>0) System.out.println("任务执行成功！");
        else System.out.println("任务执行失败！");
        String[] str = new String[0];
        main(str);
    }
    private static void function12() {
        JDBC j = new JDBC();
        String buffer = null;
        String[] arr;
        System.out.println("请输入老人和手机号（以逗号分割）");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(buffer!=null){
            arr = buffer.split(",");
            System.out.println("该功能耗时较久...请勿关闭，并耐心等候...");
            String[] interestArr = j.interestAlgorithm(arr);
            if(interestArr[2].equals("")){
                System.out.println(arr[0]+"的订单兴趣偏好：");
                System.out.println("        "+interestArr[0]);
                System.out.println("        "+interestArr[1]);
            }
            else {
                System.out.println(arr[0] + "的订单兴趣偏好：");
                System.out.println("        "+interestArr[0]);
                System.out.println("        "+interestArr[1]);
                System.out.println(arr[0] + "的日常兴趣爱好：");
                System.out.println("        "+interestArr[2]);
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        String[] str = new String[0];
        main(str);
    }
    private static void function11() {
        System.out.println("此操作将为所有订单写入相应的客户类型，且操作不可逆，请确认是否执行该操作？（请输入yes or no）");
        String buffer=null;
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert buffer != null;
        if(buffer.equals("yes")){
            new Thread01().start();
            new Thread02().start();
            new Thread03().start();
            new Thread04().start();
            new Thread05().start();
            new Thread06().start();
            new Thread07().start();
            new Thread08().start();
            new Thread09().start();
            new Thread10().start();
            new Thread11().start();
            new Thread12().start();
            new Thread13().start();
//            System.out.println("任务执行完毕！");
//            String[] str = new String[0];
//            main(str);
        }
        else if(buffer.equals("no")){
            String[] str = new String[0];
            main(str);
        }
        else {
            System.out.println("我无法识别你的选择，请重新选择");
            String[] str = new String[0];
            main(str);
        }
    }
    private static void function10() {
        JDBC j = new JDBC();
        String oldinfomation = null;
        String emergencyContactinfomation = null;
        ArrayList<oldObject> oldList = new ArrayList<>();
        ArrayList<emergencyContact> emergencyContactList = new ArrayList<>();
        System.out.println("请按提示输入指定数据，每一名老人的数据间隔使用&分割");
        System.out.println("例如：老人A信息&老人B信息");
        System.out.println("请按提示输入指定数据，数据之间使用,分割（小写逗号）:");
        System.out.println("例如：老人姓名,家庭住址,性别,身份证号码,身体情况（残疾人情况请写此栏）,老人联系方式,老人所在社区");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            oldinfomation = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(oldinfomation!=null){
            String[] oldArr= oldinfomation.split("&");
            for(int i=0;i<oldArr.length;i++){
                String[] oldinfoArr = oldArr[i].split(",");
                oldObject old = new oldObject();
                old.setCusname(oldinfoArr[0]);
                old.setResidenceAddress(oldinfoArr[1]);
                old.setSex(oldinfoArr[2]);
                old.setIdNumber(oldinfoArr[3].substring(0, 18));
                old.setUserbody(oldinfoArr[4]);
                old.setPhone(oldinfoArr[5]);
                ArrayList<String> homeList = j.findOldHomeAddress(oldinfoArr[6]);
                old.setHomeAddress(homeList.get(0));
                old.setHometownAreaCode(homeList.get(1));
                Long l = (Long.parseLong(homeList.get(2)))+i+1;
                String s = l.toString();
                String cid = "0"+s;
                old.setCid(cid);
                String tempIdNumber = oldinfoArr[3];
                String year = tempIdNumber.substring(6,10);
                String month = tempIdNumber.substring(10,12);
                String day = tempIdNumber.substring(12,14);
                String birthday = year+"-"+month+"-"+day;
                old.setBirthDate(birthday);
                oldList.add(old);
            }
            System.out.println("请按提示输入指定数据，每一名紧急联系人的数据间隔使用&分割");
            System.out.println("例如：联系人A信息&联系人B信息");
            System.out.println("请按提示输入指定数据，数据之间使用,分割（小写逗号）:");
            System.out.println("例如：紧急联系人姓名,紧急联系人手机号,两者关系（无则输入0）");
            InputStreamReader is2 = new InputStreamReader(System.in);
            BufferedReader br2 = new BufferedReader(is2);
            try {
                emergencyContactinfomation = br2.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(emergencyContactinfomation!=null){
                String[] emergencyContactArr = emergencyContactinfomation.split("&");
                for(int i=0;i<emergencyContactArr.length;i++){
                    String[] emergencyContactinfoArr = emergencyContactArr[i].split(",");
                    emergencyContact emergencyContact = new emergencyContact();
                    emergencyContact.setContacName(emergencyContactinfoArr[0]);
                    emergencyContact.setGuarPhone(emergencyContactinfoArr[1]);
                    if(!emergencyContactinfoArr[2].equals("0")) emergencyContact.setRelationship(emergencyContactinfoArr[2]);
                    else emergencyContact.setRelationship("");
                    emergencyContactList.add(emergencyContact);
                }
            }
            if(oldList.size()==emergencyContactList.size()){
                for(int i=0;i<oldList.size();i++){
                    Integer rsl = j.instrOldPeopleAndEmergency(oldList.get(i), emergencyContactList.get(i));
                    if(rsl>0){
                        System.out.println("任务完成！");
                    }
                    else if(rsl==0){
                        System.out.println("任务失败！请检查"+oldArr[i]);
                    }
                    else {
                        System.out.println("数据库已存在该老人！请检查"+oldArr[i]);
                    }
                }
                System.out.println("任务执行完毕！");
            }
            else {
                System.out.println("你所输入的老人数量与紧急联系人数量不一致，请检查后再来！");
            }
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function9() {
        JDBC j = new JDBC();
        String oldinfomation = null;
        String emergencyContactinfomation = null;
        String[] oldArr;
        System.out.println("请按提示输入指定数据，数据之间使用,分割（小写逗号）:");
        System.out.println("老人姓名,家庭住址,性别,身份证号码,身体情况（残疾人情况请写此栏）,老人联系方式,老人所在社区");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            oldinfomation = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(oldinfomation!=null){
            oldArr= oldinfomation.split(",");
            oldObject old = new oldObject();
            old.setCusname(oldArr[0]);
            old.setResidenceAddress(oldArr[1]);
            old.setSex(oldArr[2]);
            old.setIdNumber(oldArr[3]);
            old.setUserbody(oldArr[4]);
            old.setPhone(oldArr[5]);
            ArrayList<String> homeList = j.findOldHomeAddress(oldArr[6]);
            old.setHomeAddress(homeList.get(0));
            old.setHometownAreaCode(homeList.get(1));
            Long l = (Long.parseLong(homeList.get(2)))+1;
            String s = l.toString();
            String cid = "0"+s;
            old.setCid(cid);
            String tempIdNumber = oldArr[3];
            String year = tempIdNumber.substring(6,10);
            String month = tempIdNumber.substring(10,12);
            String day = tempIdNumber.substring(12,14);
            String birthday = year+"-"+month+"-"+day;
            old.setBirthDate(birthday);
            System.out.println("请按提示输入指定数据，数据之间使用,分割（小写逗号）:");
            System.out.println("紧急联系人姓名,紧急联系人手机号,两者关系（无则输入0）");
            InputStreamReader is2 = new InputStreamReader(System.in);
            BufferedReader br2 = new BufferedReader(is2);
            try {
                emergencyContactinfomation = br2.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(emergencyContactinfomation!=null){
                String[] emergencyContactinfomationArr = emergencyContactinfomation.split(",");
                emergencyContact emergencyContact = new emergencyContact();
                emergencyContact.setContacName(emergencyContactinfomationArr[0]);
                emergencyContact.setGuarPhone(emergencyContactinfomationArr[1]);
                if(!emergencyContactinfomationArr[2].equals("0")){
                    emergencyContact.setRelationship(emergencyContactinfomationArr[2]);
                }
                Integer rsl = j.instrOldPeopleAndEmergency(old, emergencyContact);
                if(rsl>0){
                    System.out.println("任务完成！");
                }
                else if(rsl==0){
                    System.out.println("任务失败！请检查"+oldArr[0]);
                }
                else {
                    System.out.println("数据库已存在该老人！请检查"+oldArr[0]);
                }
            }
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function8() {
        JDBC j = new JDBC();
        String buffer = null;
        String[] arr;
        System.out.println("请输入订单ID:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(buffer!=null){
            arr = buffer.split(",");
            System.out.println("该操作不可逆，请确认是否修改以上相关信息（输入yes,no）?");
            InputStreamReader is2 = new InputStreamReader(System.in);
            BufferedReader bf2 = new BufferedReader(is2);
            try {
                buffer = bf2.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(buffer!=null){
                if(buffer.equals("yes")){
                    System.out.println("该功能耗时较久...请勿关闭，并耐心等候...");
                    for(String orderId:arr){
                        Integer i = j.repeatedQuestions(orderId);
                        if(i==1){
                            System.out.println("任务完成！");
                        }
                        else {
                            System.out.println("任务执行失败，请检查："+orderId);
                        }
                    }
                }
            }
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function7() {
        JDBC j = new JDBC();
        String buffer = null;
        String[] arr;
        System.out.println("请输入订单ID:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (buffer != null) {
            arr = buffer.split(",");
            System.out.println("该操作不可逆，请确认是否修改以上相关信息（输入yes,no）?");
            InputStreamReader is2 = new InputStreamReader(System.in);
            BufferedReader bf2 = new BufferedReader(is2);
            try {
                buffer = bf2.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(buffer != null){
                if(buffer.equals("yes")){
                    System.out.println("该功能耗时较久...请勿关闭，并耐心等候...");
                    for(String orderId:arr){
                        if(j.findNameByOrderId(Integer.parseInt(orderId))==1){
                            Integer rsl = j.findUserIdByorderIdJDBC(Integer.parseInt(orderId));
                            if(rsl>0){
                                System.out.println("任务完成！");
                            }else {
                                System.out.println("任务执行失败！请检查："+orderId);
                            }
                        }
                        else {
                            if(j.confirmUser(Integer.parseInt(orderId))>-1){
                                System.out.println("任务完成！");
                            }
                        }
                    }
                }
            }
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function6() {
        JDBC j = new JDBC();
        String buffer = null;
        String[] arr;
        System.out.println("请输入订单ID:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (buffer != null) {
            arr = buffer.split(",");
            System.out.println("该操作不可逆，请确认是否删除以上订单（输入yes,no）?");
            InputStreamReader is2 = new InputStreamReader(System.in);
            BufferedReader bf2 = new BufferedReader(is2);
            try {
                buffer = bf2.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(buffer != null){
                if(buffer.equals("yes")){
                    for(int i=0;i<arr.length;i++){
                        Integer rsl = j.deleteOrderByOrderId(Integer.parseInt(arr[i]));
                        if(rsl>0){
                            System.out.println("任务执行中...第"+(i+1)+"条已完成");
                        }else {
                            System.out.println("任务执行失败,请检查，订单ID："+arr[i]);
                        }
                    }
                }
            }
        }
        System.out.println("任务完成!");
        String[] str = new String[0];
        main(str);
    }
    private static void function5() {
        System.out.println("正在执行一键清除重复订单操作，请勿关闭脚本，请稍后...");
        JDBC jdbc = new JDBC();
        Integer i = jdbc.deleteOrderEvaluation();
        if(i==1){
            System.out.println("任务完成！");
            String[] str = new String[0];
            main(str);
        }else {
            System.out.println("任务失败！请联系管理员");
            String[] str = new String[0];
            main(str);
        }
    }
    private static void function4(){
        JDBC j = new JDBC();
        String buffer = null;
        String[] arr;
        System.out.println("请输入订单ID:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (buffer != null) {
            arr = buffer.split(",");
            for(int i=0;i<arr.length;i++){
                Integer rsl = j.updateOrderStatusByOrderStatusJDBC(Integer.parseInt(arr[i]));
                if(rsl>0){
                    System.out.println("任务执行中...第"+(i+1)+"条已完成");
                }else {
                    System.out.println("任务执行失败,请检查，订单ID："+arr[i]);
                }
            }
        }
        System.out.println("任务完成!");
        String[] str = new String[0];
        main(str);
    }
    private static void function3() {
        ArrayList<String> orderTimeList = new ArrayList<>();
        JDBC j = new JDBC();
        String buffer = null;
        String[] arr;
        System.out.println("请输入订单ID:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (buffer != null) {
            arr = buffer.split(",");
            TimeFormat timeFormat = new TimeFormat();
            for (String s : arr) {
                String orderTime = j.findOrderTimeJDBC(Integer.parseInt(s));
                orderTimeList.add(orderTime);
            }
            for(int i=0;i<orderTimeList.size();i++){
                ArrayList<String> newOrderTimeList = timeFormat.craeteTimeByOrderTime(orderTimeList.get(i));
                int orderId = Integer.parseInt(arr[i]);
                Integer rsl = j.updateOrderByOrderStatusJDBC(newOrderTimeList, orderId);
                if(rsl>0){
                    System.out.println("任务执行中...第"+(i+1)+"条已完成");
                }else {
                    System.out.println("任务执行失败,请检查，订单ID："+orderId);
                }
            }
        }
        System.out.println("任务完成!");
        String[] str = new String[0];
        main(str);
    }
    private static void function2() {
        JDBC j = new JDBC();
        String buffer = null;
        System.out.println("当前将会为订单回访记录为空的订单执行智能补全操作:该操作不可逆并需要时间，请确认是否运行（输入：yes或no）");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert buffer != null;
        if (buffer.equals("yes")) {
            String[] arr = {
                    "工号1004希望下次服务做得仔细些", "工号1004满意，建议时间间隔短点，觉得时间间隔太久了", "工号1002满意，喜欢剪头发", "工号1002特别喜欢这个服务，希望服务不断", "工号1004满意，建议上门可以验血脂", "工号1004满意，希望家政时间长点，并跟老人家属说明本次家政时长，好安排工作", "工号1003满意，就是来问候下，有时候有阿姨来搞卫生", "工号1003老人很需要服务，很满意", "工号1003很满意，喜欢吴医生上门", "工号1004就量量血压，都满意的",
                    "工号2003很满意，喜欢吴医生上门", "工号2003满意，希望有药吃", "工号1004很满意，医生与家政都不要换来换去，一个跟到服务线束", "工号2003很满意，很喜欢坚叔与李民铧上门", "很满意,健康体检", "很满意电话预约", "满意擦门窗、按摩或体检", "老人表示对服务很满意", "很满意政府关心", "谢谢上门工作人员",
                    "没有穿工作服，对本次服务很满意", "工号1005致电老人，老人对本次服务很满意", "对医生很满意，对家政方面一般，觉得阿姨很随便，应付式的", "很满意,预约照神灯", "回访无人接听", "准时到家，对本次服务很满意", "工号1002对第一次上门服务回访：老人家人电话，表示对本次服务很满意。想了解云家庭现在是否有送餐服务", "很满意，希望新的一年服务，医生与家政都不要换来换去，一个跟到服务线束1004", "3005，家属表示满意", "服务人员来得有点慢 2003",
                    "扫的不是很干净，但是还不错 1005", "老人很开心，希望一个月可以来多几次", "因为老人听力不好，上门前请和家属联系", "服务很到位", "服务很不错   家里老人觉得很高兴", "太适合老年人了，服务很到位，工作很细心", "服务还不错，希望能有一些新的服务种类", "阿姨很好，人很勤快也很仔细啦", "不错，护工很准时", "家属反映：医生服务态度很好，家政阿姨服务质量有待提高",
                    "护工负责，认真，专业，不错，好评", "医生认真，专业，好评", "回访关机工号2003", "老人表示服务周到，感谢政府", "服务好，很准时", "有点一般，护工操作不太熟练", "人很好，老人表示满意1005", "感谢云家庭，医生和阿姨人很好，很负责任", "护工耐心热情，服务周到", "家属表示很方便，省心，辛苦了",
                    "派来的护工做事麻利，打扫仔细，超级满意，很放心，很开心", "服务一流，专业", "人很热情,工作很到位", "很满意希望政府有更多关怀老人年政策！希望云家庭越办越好", "满意，希望每月上门二次！", "满意 干净 护工很尽心", "很满意，喜欢这些服务，可以减少老人的病痛", "护工非常有责任心，有明显的改善", "家属表示，医生和家政阿姨都不错，也很健谈，老人很开心", "细节方面有待注意",
                    "不错，工作做得还是很认真的", "护工有穿工作服，很准时，打扫很干净，服务态度很好！", "医生家政做得好好，希望服务次数多些", "满意，希望增加多些服务内容和服务次数", "服务时间短，医生好，家政时间短清洁范围小", "医生满意，家政满意", "医生可以，希望增加帮看病开药的项目，家政满意", "医生家政服务态度好，建议服务可以多样化点", "回访满意，服务相当好", "建议检查的项目多一点，满意",
                    "回访满意，老人喜欢医生上门", "医生很细心，满意", "有穿工作服，很准时，医生家政态度好，希望再来", "服务满意，希望固定医生上门，不用每次重新描述老人情况", "家属表示老人喜欢这项服务，每次都盼着医生家政来", "服务到位，热情，安排的工作家政都认真完成", "医生很健谈，老人很开心，希望服务时间再长点", "护工服务态度好，工具齐全，打扫得很干净", "家政细节还需增强，家属建议做完检查多一次", "好满意，护工很细心，医生教了些日常养生食疗",
                    "还算准时，有穿工作服，喜欢医生，家政一般", "很满意，多谢政府关心，医生家政服务都好好", "老人家说护工阿姨有准时上门服务，打扫比较认真仔细。  回访工号1003", "医生找了很耐地址，才找得到老人住址，老人说医生上到门背夹汗流，就立即帮我按摩了", "按摩位置比较到位   感谢医生炎热夏天 都上门为老人家服务。十分满意 。回访工号1004", "医生有准时上门， 老人家说医生服务一流，很专业  好满意。回访工号1002", "家政阿姨态度很好  服务仔细，陪同老人说说家常  老人觉得很温暖  时间过得好快  每个月都期盼云家庭上门服务，十分专业  有爱心。回访工号1002", "老人表示家政阿姨服务一般  ，不过态度尚好  希望下次服务有所改进。医生按摩好舒服  专业，比较满意。   回访工号1002", "医生  家政阿姨有准时到位服务，医生嘘寒问暖  老人内心表达好多谢医生噶关心", "多谢政府关怀老人。  回访工号1003", "老人表示希望家政阿姨下次可以帮打扫防盗网的纱窗   医生服务很好 很满意。  回访工号1002",
                    "今次派来的护工老人表示很满意  工作仔细认真  服务一流。  回访工号1003", "家政阿姨打扫不够仔细  对家政阿姨服务不是很满意，医生服务不错  手臂旧患有好转  十分感谢。 回访工号3005", "老人说家政阿姨服务很好  态度又好好   十分满意。   回访工号1002", "老人说近期雨水多  风湿骨痛，幸好有云家庭医生团队上门服务  得以缓解。表示十分满意，谢谢政府对他的关怀 ，希望快点恢复服务 。回访工号1002", "理发师傅手艺很好，善谈。老人表示对今次理发好满意。   回访工号1004", "老人说家政清洗家电好干净   好满意阿姨的服务专业和态度。   回访工号1002", "老人表示理发师傅很好，一边理发一边和他聊天，心情很愉悦，谢谢云家庭的专业服务。 十分满意。 回访工号1005", "医生上次听见我脚痛，今次专程带药酒帮我局部按摩，感谢医生对我的事情上心   老人家表示很感动。 谢谢医生。对今次服务十分满意。  回访工号1002", "老人说今次阿姨服务很好，把狭窄的家里打扫的好干净，油烟清得好干净。  好满意今次上门服务。  回访工号1002", "医生  护工阿姨都有穿工作服准时上门，上门服务时间较长，态度很好，对服务好满意。回访工号1002",
                    "老人表示医生很专业  服务很好  对吴医生服务很满意，希望下次继续是吴医生上门服务。 回访工号1002", "老人家属接听电话，表示当时不在场。", "老人家说家政阿姨今天迟到了 因为大雨 表示可以理解。 但对阿姨噶服务还是满意的 。 回访工号1003", "老人说云家庭今天有上门服务，阿姨及医生都有准时上门", "阿姨拖地，见到蜘蛛网都主动去清理，十分满意今次的服务。回访工号1002", "服务很满意，希望服务时间再久些更好", "服务很满意，特别是医生解说得很仔细", "服务很满意，家政阿姨清洁得很干净", "服务很满意，希望可以持续服务", "服务满意，上门服务人员工作认真，负责",
                    "服务很满意，上门服务人员上门准时。", "服务很满意，家政阿姨连厕所都清洁得很干净", "服务还算满意，希望医生能给些实际的建议", "服务还算满意，家政阿姨能清洁下墙壁更好", "服务满意，医生服务很好、按摩后肩痛得到缓解", "服务很满意，理发师傅技术很好", "服务很满意，上门服务的人员态度都很好", "服务很满意，很感谢政府有这种为老服务", "服务很好很满意，医生和家政阿姨每次上门都很开心", "非常满意，医生和家政阿姨多些上门就好了",
                    "服务很好，真感谢安排这么好的医生来", "服务非常满意，感谢安排这么负责的家政阿姨来", "服务非常满意，叫家政阿姨不用做厨房清洁，她还是坚持清洁，很负责", "总体满意，希望每次都是这两个工作人来", "医生服务非常好，每次来都问长问短的，很满意", "这次的家政阿姨做得非常仔细，厕所也刷得很干净", "服务一直都很好，很满意，希望一直都有这种服务", "很喜欢吴医生过来服务，做得好，又会讨人喜欢。", "服务是很好，希望每次服务的时间能长一点。", "医生态度很好，如果看了有病能给药吃就好了。",
                    "老人说家政阿姨服务很好  态度又好好   十分满意。   回访工号1002", "老人说近期雨水多  风湿骨痛，幸好有云家庭医生团队上门服务  得以缓解。表示十分满意，谢谢政府对他的关怀 ，希望快点恢复服务 。回访工号1002", "理发师傅手艺很好，善谈。老人表示对今次理发好满意。   回访工号1004", "老人说家政清洗家电好干净   好满意阿姨的服务专业和态度。   回访工号1002", "老人表示理发师傅很好，一边理发一边和他聊天，心情很愉悦，谢谢云家庭的专业服务。 十分满意。 回访工号1005", "医生上次听见我脚痛，今次专程带药酒帮我局部按摩，感谢医生对我的事情上心   老人家表示很感动。 谢谢医生。对今次服务十分满意。  回访工号1002", "老人说今次阿姨服务很好，把狭窄的家里打扫的好干净，油烟清得好干净。  好满意今次上门服务。  回访工号1002", "医生  护工阿姨都有穿工作服准时上门，上门服务时间较长，态度很好，对服务好满意。回访工号1002", "老人表示医生很专业  服务很好  对吴医生服务很满意，希望下次继续是吴医生上门服务。 回访工号1002",
                    "对本次服务很满意，想要找个保姆", "这次量血糖有点高，医生教了很多食疗法，谢谢医生谢谢云家庭", "家属反应说老人对服务很满意，特别喜欢李医生。", "有准时到，有穿工作服，对本次服务满意", "很满意，有这样的服务老人很满意", "对本次服务满意，下次预约血糖检查", "希望按摩时间可以长点，其他都满意", "对本次服务很满意，照了神灯腰舒服了很多，希望继续", "阿姨搞卫生很主动，搞得很干净，希望下次还安排她过来", "云家庭有剪发服务真是太好了，老人不能下楼，真的帮助了很多",
                    "阿姨干活真是没话说，帮擦了天花板，汗水都湿透了衣服了，叫休息下都不休息", "很喜欢邓阿姨，做事情认真负责，以后能安排她来吗", "医生来量到老人血压很高，电话通知家属回来几时带老人去医院，真是太感谢了", "工号１００４希望下次服务做得仔细些", "工号１００４满意，得到了实实在在的关怀", "工号１００２满意，剪头发手势好", "工号１００２老人家比较喜欢剪发服务，不用专程跑来跑去", "工号１００４满意，医生专业，还能学到不少东西", "工号１００４满意，希望家政时间长点，再仔细点", "工号１００３满意，有时阿姨来到已经特别累，工作有些疏忽",
                    "工号１００３老人对服务很满意，减轻了家庭责任", "工号１００３很满意，喜欢吴医生上门", "工号１００４就量了血压，问下情况，都满意的", "工号２００３很满意，吴医生挺专业的", "工号２００３满意，希望用药范围再广些，减轻一下家庭经济负担", "工号１００４很满意，医生与家政调动不要太频繁", "工号２００３很满意，感谢政府，感谢党", "工号3005    满意，卫生搞得很好，就是有时忘记穿工作服", "感谢你们的关怀,上门服务老人很开心", "我是老人子女，服务的时候我不在家，听老人家说是挺满意的",
                    "家政阿姨不太熟路，迟到了","医生很耐心","医生上门按摩很舒服，现在晚上睡眠好","希望医生多上门，医生很好","这次的家政阿姨做得很细心，上次的就是随便一点","谢谢国家有这项服务","希望服务时间长一点","感谢政府对老年人的关爱","政府对老人的关爱实实在在，很喜欢云家庭 ","以前我们都没有这样的福利，现在上门服务方便老年人，因为方便",
                    "剪头发师傅很好，技术很好", "现在很少人对老人家关心，多谢云家庭上门陪陪我们聊天", "上门医生很好啊，告诉我父母街边的药物不要乱买", "医生上门帮我搽药酒，按摩，老人家脚没这么痛了，谢谢", "辛苦医生家政上门，翻风落雨", "天气比较冷，老人家起床比较麻烦，希望服务时间推后", "建议你们减少拍照，多做一点工，有些家政好像就来拍照的满意。", "感谢政府和云家庭上门服务的好政策，不用给儿女添负担。", "医生家政做得好好，希望服务次数多些。", "满意，希望增加多些服务内容和服务次数。",
                    "服务时间短，医生好，家政时间短清洁范围小。", "医生满意，家政满意。", "医生可以，希望增加帮看病开药的项目，家政满意。", "医生家政服务态度好，建议服务可以多样化点。", "回访满意，服务相当好。", "建议检查的项目多一点，满意。", "回访满意，老人喜欢医生上门。", "医生很细心，满意。", "有穿工作服，很准时，医生家政态度好，希望再来。", "服务满意，希望固定医生上门，不用每次重新描述老人情况。",
                    "家属表示老人喜欢这项服务，每次都盼着医生家政来。", "服务到位，热情，安排的工作家政都认真完成。", "医生很健谈，老人很开心，希望服务时间再长点。", "护工服务态度好，工具齐全，打扫得很干净。", "家政细节还需增强，家属建议做完检查多一次。", "好满意，护工很细心，医生教了些日常养生食疗。", "还算准时，有穿工作服，喜欢医生，家政一般。", "很满意，多谢政府关心，医生家政服务都好好。", "很满意，这次家政邓啊姨工作很细心。", "非常非常满意",
                    "很满意，照完灯晚上睡觉得很舒服", "很满意！希望有更多项目选择，清晰家政的工作范围", "很满意希望这种服务长期做下去", "医生服务很细心与老人交谈身体情况，家政帮助搞厨房卫生", "满意服务以与医生约好下次服务", "谢谢民政与云家庭对老人的关心，很喜欢满意服务", "很满意，希望家政可以做久点！", "关机！", "很满意！希望以后都医生与啊姨上门服务", "满意，希望每月上门二次！",
                    "很满意！话务员态度很好！", "很满意希望政府有更多关怀老人年政策！希望云家庭越办越好", "很满意，喜欢按摩可以减少老人的病痛", "无人接听", "很满意，很喜欢此项服务", "很满意，医生与家政都不要换来换去，一个跟到服务线束", "满意，希望有药吃", "很满意，喜欢吴医生上门", "就量量血压，都满意的", "很满意，喜欢吴医生上门",
                    "老人很需要服务，很满意", "满意，就是来问候下，有时候有阿姨来搞卫生", "满意，希望家政时间长点，并跟老人家属说明本次家政时长，好安排工作", "满意，建议上门可以验血脂", "特别喜欢这个服务，希望服务不断", "满意，喜欢剪头发", "满意，建议时间间隔短点，觉得时间间隔太久了", "希望下次服务做得仔细些", "很满意，医生做的很好，给个大姆指他", "很满意，厨房打扫比自己做得都干净",
                    "很满意，医生给老人检查都细心", "老人家说护工阿姨有准时上门服务，打扫比较认真仔细。  回访工号1003", "医生找了很耐地址  才找得到老人住址。  老人说医生上到门背夹汗流，就立即帮我按摩了 。按摩位置比较到位   感谢医生炎热夏天 都上门为老人家服务   十分满意 。  回访工号1004", "医生有准时上门， 老人家说医生服务一流，很专业  好满意。  回访工号1002", "家政阿姨态度很好  服务仔细，陪同老人说说家常  老人觉得很温暖  时间过得好快  每个月都期盼云家庭上门服务，十分专业  有爱心。回访工号1002", "老人表示家政阿姨服务一般  ，不过态度尚好  希望下次服务有所改进。医生按摩好舒服  专业，比较满意。   回访工号1002", "医生  家政阿姨有准时到位服务，医生嘘寒问暖  老人内心表达好多谢医生噶关心。  多谢政府关怀老人。  回访工号1003", "老人表示希望家政阿姨下次可以帮打扫防盗网的纱窗   医生服务很好 很满意。  回访工号1002", "今次派来的护工老人表示很满意  工作仔细认真  服务一流。  回访工号1003", "家政阿姨打扫不够仔细  对家政阿姨服务不是很满意，医生服务不错  手臂旧患有好转  十分感谢。 回访工号3005",
                    "老人家属接听电话，表示当时不在场。但回到家中，老人说云家庭今天有上门服务，阿姨及医生都有准时上门， 阿姨拖地  ，见到蜘蛛网都主动去清理，十分满意今次的服务。  回访工号1002", "老人家说家政阿姨今天迟到了 因为大雨 表示可以理解。 但对阿姨噶服务还是满意的 。 回访工号1003"
            };
            ArrayList<Integer> list = j.findOrderJDBC();
            for (int i=0;i<list.size();i++) {
                String s = arr[(int) (Math.random() * 250 + 1)];
                s = "'" + s + "'";
                Integer rsl = j.instrOrderRemakerJDBC(s, list.get(i));
                if(rsl>0){
                    System.out.println("任务执行中...第"+(i+1)+"条已完成");
                }else {
                    System.out.println("任务执行失败,请检查，订单ID："+list.get(i));
                }
            }
        } else if (buffer.equals("no")) {
            String[] str = new String[0];
            main(str);
        } else {
            System.out.println("您输入的值不正确，请重新输入");
            String[] str = new String[0];
            main(str);
        }
        String[] str = new String[0];
        main(str);
    }
    private static void function1() {
        JDBC j = new JDBC();
        String buffer = null;
        String[] arr;
        System.out.println("请输入订单ID:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (buffer != null) {
            arr = buffer.split(",");
            for (int i=0;i<arr.length;i++) {
                Integer Evaluation = j.findEvaluationJDBC(Integer.parseInt(arr[i]));
                if (Evaluation == 0) {
                    Integer userId = j.findUserIdByOrderJDBC(Integer.parseInt(arr[i]));
                    if (userId > 0) {
                        Integer rsl = j.instrEvaluationJDBC(Integer.parseInt(arr[i]), userId);
                        if (rsl > 0) {
                            System.out.println("创建第"+(i+1)+"条评论成功！");
                        } else {
                            System.out.println("创建评论失败！请检查，订单ID："+arr[i]);
                        }
                    }
                } else {
                    System.out.println("数据库已有评论");
                }
            }
        }
        System.out.println("任务完成!");
        String[] str = new String[0];
        main(str);
    }
    private static void chooseModel() {
        String buffer=null;
        System.out.println("请选择模式：1.批量录入模式；   2.单数据录入模式");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            buffer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert buffer != null;
        if(buffer.equals("1")) function10();
        if(buffer.equals("2")) function9();
        else System.out.println("我无法识别你的选择，请重新选择");
        chooseModel();
    }
}