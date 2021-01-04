import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class JDBC {
    private String DB_Url="jdbc:mysql://*.*.*.*:3306/sq_ihomeweb?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    private String DB_USER="root";
    private String DB_PASS="fdxcwr660312";

    private String Local_DB_Url = "jdbc:mysql://localhost:3306/oracle_certification?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    private String Local_DB_USER = "root";
    private String Local_DB_PASS = "lqh135158";

    Integer findEvaluationJDBC(Integer orderId){
        Integer rsl = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt1 = conn.createStatement();
            String sql1 = "SELECT count(*) FROM orderevaluation WHERE orderId = "+orderId;
            ResultSet rs = stmt1.executeQuery(sql1);
            while (rs.next()){
                rsl = rs.getInt(1);
            }
            conn.close();
            stmt1.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return rsl;
    }
    Integer findUserIdByOrderJDBC(Integer orderId){
        Integer rsl = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt1 = conn.createStatement();
            String sql1 = "SELECT userId FROM userordergenerate WHERE orderId = "+orderId;
            ResultSet rs = stmt1.executeQuery(sql1);
            while (rs.next()){
                rsl = rs.getInt(1);
            }
            conn.close();
            stmt1.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return rsl;
    }
    Integer instrEvaluationJDBC(Integer orderId, Integer userId){
        Integer rsl = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt1 = conn.createStatement();
            String sql1 = "INSERT INTO orderevaluation (orderId,score,comment,isComplaint,imgSrcs,isNnonymous,userId,userName,orderTime,evaluateType) VALUES("+orderId+",5,'很满意',null,'',2,"+userId+",'','2018-03-05 15:09:24',1)";
            rsl = stmt1.executeUpdate(sql1);
            conn.close();
            stmt1.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return rsl;
    }
    Integer instrOrderRemakerJDBC(String orderRemaker, Integer orderId){
        Integer rsl = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt1 = conn.createStatement();
            String sql = "update userordergenerate set orderRemark="+orderRemaker+" WHERE orderId="+orderId;
            rsl = stmt1.executeUpdate(sql);
            conn.close();
            stmt1.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return rsl;
    }
    ArrayList<Integer> findOrderJDBC(){
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt1 = conn.createStatement();
            String sql1 = "select u.orderId from userordergenerate u,oldpeopleinfo o where u.orderRemark is null and u.orderStatus=4 and o.cusname=u.consigneeName and (o.clientType=24 ||o.clientType=25 || o.clientType=28 || o.clientType=31 || o.clientType=42 || o.clientType=43 || o.clientType=49 || o.clientType=50 || o.clientType=51 || o.clientType=52)";
            ResultSet rs = stmt1.executeQuery(sql1);
            while (rs.next()){
                list.add(rs.getInt(1));
            }
            conn.close();
            stmt1.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }
    String findOrderTimeJDBC(Integer orderId){
        String rsl = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt1 = conn.createStatement();
            String sql1 = "SELECT * FROM userordergenerate WHERE orderId = "+orderId;
            ResultSet rs = stmt1.executeQuery(sql1);
            while (rs.next()){
                rsl = rs.getString("orderTime");
            }
            conn.close();
            stmt1.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return rsl;
    }
    Integer updateOrderByOrderStatusJDBC(ArrayList<String> newOrderTimeList, Integer orderId) {
        Integer rsl = null;
        try {
            String serviceTimeBefore = newOrderTimeList.get(0);
            String serviceTimeAfter = newOrderTimeList.get(1);
            serviceTimeBefore="'"+serviceTimeBefore+"'";
            serviceTimeAfter="'"+serviceTimeAfter+"'";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt1 = conn.createStatement();
            String sql = "update userordergenerate set serviceTimeBefore="+serviceTimeBefore+",serviceTimeAfter="+serviceTimeAfter+",orderStatus=4,totalServiceTime=3600 WHERE orderId="+orderId;
            rsl = stmt1.executeUpdate(sql);
            conn.close();
            stmt1.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return rsl;
    }
    Integer updateOrderStatusByOrderStatusJDBC(Integer orderId) {
        Integer rsl = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt0 = conn.createStatement();
            String sql0 = "select nursingId from userordergenerate where orderId="+orderId;
            ResultSet resultSet = stmt0.executeQuery(sql0);
            while (resultSet.next()){
                if(resultSet.getString(1)==null||resultSet.getString(1).equals("")){
                    Statement stmt1 = conn.createStatement();
                    String sql = "update userordergenerate set orderStatus=4,nursingId=257 WHERE orderId="+orderId;
                    rsl = stmt1.executeUpdate(sql);
                    stmt1.close();
                }else {
                    Statement stmt1 = conn.createStatement();
                    String sql = "update userordergenerate set orderStatus=4 WHERE orderId="+orderId;
                    rsl = stmt1.executeUpdate(sql);
                    stmt1.close();
                }
            }
            conn.close();
            stmt0.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return rsl;
    }
    Integer deleteOrderEvaluation() {
        ArrayList<Integer> orderIdList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            String sql = "select orderId from orderevaluation group by orderId having Count(*) > 1";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            //把重复评论的ID存入集合
            while (rs.next()){
                orderIdList.add(Integer.parseInt(rs.getString("orderId")));
            }
            //遍历list进行查询
            for(int i=0;i<orderIdList.size();i++){
                Integer orderId=orderIdList.get(i);
                String sql1 = "select * from orderevaluation where orderId="+orderId;
                Connection conn1 = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
                ResultSet rs1 = conn1.createStatement().executeQuery(sql1);
                //先找评论内容为空的删除
                while (rs1.next()){
                    String orderEvaID = rs1.getString("orderEvaID");
                    String comment = rs1.getString("comment");
                    if(comment.equals("") || comment==null){
                        Connection tempConn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
                        Statement tempStmt = tempConn.createStatement();
                        String tempSQL = "DELETE FROM orderevaluation WHERE orderEvaID="+Integer.parseInt(orderEvaID);
                        tempStmt.executeUpdate(tempSQL);
                        tempConn.close();
                        tempStmt.close();
                    }
                }
                //再把内容不为空的删除剩1条
                String sql2 = "select orderEvaID from orderevaluation where orderId="+orderId;
                Connection conn2 = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
                ResultSet rs3 = conn2.createStatement().executeQuery(sql2);
                while (rs3.next()){
                    String orderEvaID = rs3.getString("orderEvaID");
                    String sql3 = "select count(orderEvaID) from orderevaluation where orderId="+orderId;
                    ResultSet rs4 = conn2.createStatement().executeQuery(sql3);
                    while (rs4.next()){
                        if(Integer.parseInt(rs4.getString(1))>1){
                            Connection tempConn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
                            Statement tempStmt = tempConn.createStatement();
                            String tempSQL = "DELETE FROM orderevaluation WHERE orderEvaID="+orderEvaID;
                            tempStmt.executeUpdate(tempSQL);
                            tempConn.close();
                            tempStmt.close();
                        }
                    }
                }
                System.out.println("正在删除第"+(i+1)+"条...");
                conn1.close();
                conn2.close();
            }
            conn.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    Integer deleteOrderByOrderId(int orderId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            String sql = "DELETE FROM orderevaluation WHERE orderId="+orderId;
            conn.createStatement().executeUpdate(sql);
            String sql2 = "DELETE FROM userGovSubsidiesFlow WHERE orderId="+orderId;
            conn.createStatement().executeUpdate(sql2);
            String sql3 = "DELETE FROM userordergenerate WHERE orderId="+orderId;
            int rsl = conn.createStatement().executeUpdate(sql3);
            conn.close();
            return rsl;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    Integer findUserIdByorderIdJDBC(int orderId) {
        ArrayList<old> olds = new ArrayList<>();
        String shippingAddress="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            //根据订单ID查找老人表名字，地址，手机号
            Statement stmt1 = conn.createStatement();
            String sql1 = "SELECT o.cusname,o.residenceAddress,o.phone FROM oldpeopleinfo o,userordergenerate u WHERE o.cusname=u.consigneeName and orderId = "+orderId;
            ResultSet rs1 = stmt1.executeQuery(sql1);
            //根据订单ID查找收货地址
            Statement stmt2 = conn.createStatement();
            String sql2 = "SELECT shippingAddress FROM userordergenerate WHERE orderId = "+orderId;
            ResultSet rs2 = stmt2.executeQuery(sql2);
            while (rs1.next()){
                olds.add(new old(rs1.getString(1),rs1.getString(2),rs1.getString(3),null,null,null));
            }
            while (rs2.next()){
                shippingAddress=rs2.getString(1);
            }
            for(old o:olds){
                String hd = o.getHomeAddress();
                String phone = o.getPhone();
                try{
                    if(hd!=null &&phone!=null){
                        if(hd.contains(shippingAddress) || shippingAddress.contains(hd)){
                            conn.close();
                            return updatePhoneAndMobile(orderId, phone);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    Integer updatePhoneAndMobile(Integer orderId, String phone) {
        Integer rsl=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            //根据订单ID修改订单表联系人手机
            Statement stmt1 = conn.createStatement();
            String sql = "update userordergenerate set consignee="+phone+" WHERE orderId="+orderId;
            int i = stmt1.executeUpdate(sql);
            if(i>0){
                String userId=null;
                //根据订单ID查找注册表userId
                Statement stmt2 = conn.createStatement();
                String sql2 = "SELECT r.userId FROM userregister r,userordergenerate u WHERE u.userId=r.userId and orderId = "+orderId;
                ResultSet rs2 = stmt2.executeQuery(sql2);
                while (rs2.next()){
                    userId = rs2.getString(1);
                }
                //根据订单ID修改注册表手机
                Statement stmt3 = conn.createStatement();
                String sql3 = "update userregister set mobile="+phone+" WHERE userId="+userId;
                int i1 = stmt3.executeUpdate(sql3);
                conn.close();
                return i1;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return rsl;
    }
    Integer findNameByOrderId(int orderId) {
        String consigneeName=null;
        int countREG=0;
        int countOLD=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            //根据订单ID查找订单人
            Statement stmt1 = conn.createStatement();
            String sql1 = "select consigneeName from userordergenerate where orderId="+orderId;
            ResultSet rs1 = stmt1.executeQuery(sql1);
            while (rs1.next()){
                consigneeName = rs1.getString(1);
            }
            //根据订单人名查找注册表
            Statement stmt2 = conn.createStatement();
            String sql2 = "select count(*) from userregister where nickName= "+"'"+consigneeName+"'";
            ResultSet rs2 = stmt2.executeQuery(sql2);
            while (rs2.next()){
                countREG = rs2.getInt(1);
            }
            //根据订单人名查找老人表
            Statement stmt3 = conn.createStatement();
            String sql3 = "select count(*) from oldpeopleinfo where cusname="+"'"+consigneeName+"'";
            ResultSet rs3 = stmt3.executeQuery(sql3);
            while (rs3.next()){
                countOLD = rs3.getInt(1);
            }
            conn.close();
            if(countOLD==1 && countREG==1){
                return 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    Integer confirmUser(int orderId) {
        String regMobile=null;
        String oldPhone=null;
        String orderIdQr=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            //根据订单ID查找注册表二维码，手机号
            Statement stmt1 = conn.createStatement();
            String sql1 = "SELECT r.mobile,r.orderIdQr FROM userregister r,userordergenerate u WHERE r.userId=u.userId and orderId = "+orderId;
            ResultSet rs1 = stmt1.executeQuery(sql1);
            while (rs1.next()){
                regMobile=rs1.getString(1);
                orderIdQr=rs1.getString(2);
            }
            //根据订单ID查找注册表二维码，手机号
            Statement stmt2 = conn.createStatement();
            String sql2 = "select phone from oldpeopleinfo where oldQr = "+"'"+orderIdQr+"'";
            ResultSet rs2 = stmt2.executeQuery(sql2);
            while (rs2.next()){
                oldPhone=rs2.getString(1);
            }
            conn.close();
            try{
                assert oldPhone != null;
                assert regMobile != null;
                if(oldPhone.equals(regMobile)){
                    System.out.println("任务执行失败！原因：老人手机号与注册表手机号一致，可能表内有同名同姓的用户，请手动检查，订单号："+orderId);
                }else {
                    Integer i = updatePhoneAndMobile(orderId, oldPhone);
                    if(i>-1){
                        return 1;
                    }
                }
            }catch (Exception e){
                System.out.println("空指针异常！！！老人表或注册表手机号为空，请检查，订单号："+orderId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    Integer repeatedQuestions(String orderId) {
        String regnickName=null;
        String regQR=null;
        String regUserId=null;
        String oldPhone=null;
        String oldQR=null;
        String oldCusname=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            //根据订单ID查找注册表二维码，姓名
            Statement stmt1 = conn.createStatement();
            String sql1 = "SELECT r.nickName,r.orderIdQr,r.userId FROM userregister r,userordergenerate u WHERE r.userId=u.userId and orderId = "+orderId;
            ResultSet rs1 = stmt1.executeQuery(sql1);
            while (rs1.next()){
                regnickName=rs1.getString(1);
                regQR=rs1.getString(2);
                regUserId=rs1.getString(3);
            }
            //根据订单ID查找老人表二维码，姓名
            Statement stmt2 = conn.createStatement();
            String sql2 = "SELECT phone,oldQr,cusname FROM oldpeopleinfo WHERE cusname like '%"+regnickName+"%' and oldQr = "+"'"+regQR+"'";
            ResultSet rs2 = stmt2.executeQuery(sql2);
            while (rs2.next()){
                oldPhone=rs2.getString(1);
                oldQR=rs2.getString(2);
                oldCusname=rs2.getString(3);
            }
            conn.close();
            if(regnickName.contains(oldCusname) || oldCusname.contains(regnickName)){
                if(regQR.equals(oldQR)){
                    Integer i = updateMobileAndUserId(Integer.parseInt(orderId), oldPhone, Integer.parseInt(regUserId));
                    if(i>-1){
                        return 1;
                    }
                }
            }
            System.out.println("老人表或注册表姓名或二维码为空，无法执行，请检查"+orderId);
        }catch (Exception e){
        }
        return 0;
    }
    Integer updateMobileAndUserId(Integer orderId, String phone, Integer regUserId) {
        Integer rsl=-1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            //根据订单ID修改订单表联系人手机
            Statement stmt1 = conn.createStatement();
            String sql = "update userordergenerate set consignee="+phone+",userId="+regUserId+"  WHERE orderId="+orderId;
            int i = stmt1.executeUpdate(sql);
            if(i>0){
                //根据订单ID修改注册表手机
                Statement stmt3 = conn.createStatement();
                String sql3 = "update userregister set mobile="+phone+" WHERE userId="+regUserId;
                int i1 = stmt3.executeUpdate(sql3);
                conn.close();
                return i1;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return rsl;
    }
    ArrayList<String> findOldHomeAddress(String homeAddress) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            //根据订单ID查找注册表二维码，姓名
            Statement stmt1 = conn.createStatement();
            String sql1 = "select homeAddress,hometownAreaCode,cid from oldpeopleinfo where homeAddress like '%"+homeAddress+"社区%' order by cid desc limit 0,1 ";
            ResultSet rs1 = stmt1.executeQuery(sql1);
            while (rs1.next()){
                list.add(rs1.getString(1));
                list.add(rs1.getString(2));
                list.add(rs1.getString(3));
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    Integer instrOldPeopleAndEmergency(oldObject old, emergencyContact emergencyContact) {
        Integer rsl = -1;
        Integer countRsl=0;
        Integer oldId=0;
        TimeFormat timeFormat = new TimeFormat();
        String createTime = timeFormat.getToDayFullStrTime();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt2 = conn.createStatement();
            String sql2 = "select count(*) from oldpeopleinfo where idNumber='"+old.getIdNumber()+"'";
            ResultSet resultSet = stmt2.executeQuery(sql2);
            while (resultSet.next()){
                countRsl = resultSet.getInt(1);
            }
            if(countRsl>0){
                conn.close();
                return rsl;
            }else {
                Statement stmt1 = conn.createStatement();
                String sql = "INSERT INTO oldpeopleinfo(equipmentId,oldQr,cid,cusname,sex,idNumber,birthDate,isLunar,phone,landLine,hometownAreaCode,homeAddress,residenceAddress,livingConditions,userbody,taste,interest,deposit,lonely,endTime,language,createTime,nursingHomeId,buildingNumber,floorNumber,roomNumber,family,bedNumber,status,residenceAreaCode,adminePower,isSubsidy,ringNum,portrait,clientType,serveType,socialWorkers,socialWorkersPhone,callsuccess,otherThing,merchantId) VALUE ('','','"+old.getCid()+"','"+old.getCusname()+"','"+old.getSex()+"','"+old.getIdNumber()+"','"+old.getBirthDate()+"',0,'"+old.getPhone()+"','','"+old.getHometownAreaCode()+"','"+old.getHomeAddress()+"','"+old.getResidenceAddress()+"',null,'"+old.getUserbody()+"','','',0.00,0,'','梧州话','"+createTime+"',0,'',0,0,'',0,1,null,null,0,'',null,29,9,'','',1,'',0)";
                rsl = stmt1.executeUpdate(sql);
                if(rsl>0){
                    Statement stmt3 = conn.createStatement();
                    String sql3 = "select oldId from oldpeopleinfo where idNumber='"+old.getIdNumber()+"'";
                    ResultSet resultSet1 = stmt3.executeQuery(sql3);
                    while (resultSet1.next()){
                        oldId = resultSet1.getInt(1);
                    }
                    if(oldId>0){
                        Statement stmt4 = conn.createStatement();
                        String sql4 = "INSERT INTO guardianoroldpeoplerelationship(guarPhone,oldId,contacName,relationship,level,status) VALUE ('"+emergencyContact.getGuarPhone()+"',"+oldId+",'"+emergencyContact.getContacName()+"','"+emergencyContact.getRelationship()+"',1,1)";
                        rsl = stmt4.executeUpdate(sql4);
                        if(rsl>0){
                            conn.close();
                            return rsl;
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rsl;
    }
    Integer instrOrderClientType(Integer p1,Integer p2) {
        ArrayList<Integer> orderIdList = new ArrayList<>();
        ArrayList<OldInfo> finalOldList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt0 = conn.createStatement();
            String sql0 = "select orderId from userordergenerate limit "+p1+","+p2;
            ResultSet rs0 = stmt0.executeQuery(sql0);
            while (rs0.next()){
                orderIdList.add(rs0.getInt(1));
            }
            for(Integer orderId:orderIdList){
                Statement stmt1 = conn.createStatement();
                String sql1 = "select o.clientType,o.hometownAreaCode,o.status from userregister r,userordergenerate u,oldpeopleinfo o where u.userId=r.userId and (r.mobile=o.phone || r.mobile=o.landLine) and u.orderId="+orderId;
                ResultSet rs1 = stmt1.executeQuery(sql1);
                ArrayList<OldInfo> oldList = new ArrayList<>();
                while (rs1.next()){
                    oldList.add(new OldInfo(rs1.getInt(1),rs1.getString(2),rs1.getInt(3)));
                }
                boolean b = false;
                if(oldList.size()!=0){
                    if(oldList.size()==1) finalOldList.add(new OldInfo(oldList.get(0).getClientType(),oldList.get(0).getHomeAreaCode(),oldList.get(0).getOldStatus()));
                    if(oldList.size()>1){
                        for(OldInfo o:oldList){
                            if(o.getOldStatus()!=0 && (o.getClientType()==31 || o.getClientType()==50)){
                                finalOldList.add(o);
                                b=true;
                                break;
                            }
                        }
                        if(!b) finalOldList.add(new OldInfo(oldList.get(0).getClientType(),oldList.get(0).getHomeAreaCode(),oldList.get(0).getOldStatus()));
                        else b=false;
                    }
                }
                else {
                    System.out.println("该订单无法找到对应的老人信息，请检查："+orderId);
                }
                stmt1.close();
            }
            if(orderIdList.size()==finalOldList.size()){
                System.out.println("正在执行修改...请稍等...");
                for(int i=0;i<orderIdList.size();i++){
                    OldInfo oldInfo = finalOldList.get(i);
                    String info = oldInfo.getClientType()+"@"+oldInfo.getHomeAreaCode()+"@"+oldInfo.getOldStatus();
                    Statement stmt2 = conn.createStatement();
                    String sq2 = "update userordergenerate set clientType='"+info+"' where orderId="+orderIdList.get(i);
                    int rs2 = stmt2.executeUpdate(sq2);
                    if(rs2<0){
                        System.out.println("任务执行失败！检查订单ID："+orderIdList.get(i));
                    }
                    stmt2.close();
                }
                return 1;
            }else {
                System.out.println("两条链表长度不同！请检查");
                System.out.println("orderId链表长度："+orderIdList.size()+"     finalOld链表长度："+finalOldList.size());
            }
            stmt0.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    String[] interestAlgorithm(String[] arr) {
        String[] interest = {"无","无",""};
        String name = arr[0];
        String phone = arr[1];
        ArrayList<String> orderList = new ArrayList<>();
        ArrayList<MerchantName> orderType = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt0 = conn.createStatement();
            String sql0 = "select interest from oldpeopleinfo where phone='"+phone+"' and cusname like '%"+name+"%'";
            ResultSet rs0 = stmt0.executeQuery(sql0);
            while (rs0.next()){
                interest[2] = rs0.getString(1);
            }
            Statement stmt1 = conn.createStatement();
            String sql1 = "select u.merchantName from userordergenerate u,oldpeopleinfo o,userregister r where r.mobile=o.phone and u.userId=r.userId and o.phone='"+phone+"' and o.cusname like '%"+name+"%'";
            ResultSet rs = stmt1.executeQuery(sql1);
            while (rs.next()){
                orderList.add(rs.getString(1));
            }
            if(orderList.size()>2){
                orderType.add(new MerchantName(orderList.get(0),1));
                boolean key = false;
                for(int i=1;i<orderList.size();i++){
                    for(int j=0;j<orderType.size();j++){
                        if(orderList.get(i).equals(orderType.get(j).getMerchantName())) {
                            Integer count = orderType.get(j).getCount();
                            count++;
                            orderType.get(j).setCount(count);
                            key=true;
                        }
                    }
                    if(!key) orderType.add(new MerchantName(orderList.get(i),1));
                    else key=false;
                }
                Integer topMax=orderType.get(0).getCount();
                Integer top2=0;
                int topIndex=0;
                int top2Index=0;
                for(int i=0;i<orderType.size();i++){
                    Integer count = orderType.get(i).getCount();
                    if(count>topMax && count>top2) {
                        topMax=count;
                        topIndex=i;
                    }
                    else if(count<topMax && count>top2) {
                        top2=count;
                        top2Index=i;
                    }
                }
                interest[0]=orderType.get(topIndex).getMerchantName();
                interest[1]=orderType.get(top2Index).getMerchantName();
            }else {
                System.out.println("该老人订单数据太少，无法计算");
            }
            conn.close();
            stmt0.close();
            stmt1.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return interest;
    }
    Integer createServeTypeByOrder() {
        int rsl=1;
        ArrayList<Integer> orderIdList = new ArrayList<>();
        ArrayList<Integer> serveIdList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt0 = conn.createStatement();
            String sql0 = "select oldId from oldpeopleinfo where serveType is null or serveType=''";
            ResultSet rs0 = stmt0.executeQuery(sql0);
            while (rs0.next()){
                int oldId = rs0.getInt(1);
                if(oldId>0){
                    System.out.println("老人表存在服务类型为空的数据，请检查老人ID:"+oldId);
                }
            }
            Statement stmt1 = conn.createStatement();
            String sql1 = "select orderId from userordergenerate where serveType is null or serveType=''";
            ResultSet rs = stmt1.executeQuery(sql1);
            while (rs.next()){
                orderIdList.add(rs.getInt(1));
            }
            for (int i=0;i<orderIdList.size();i++) {
                Statement stmt2 = conn.createStatement();
                String sql2 = "select o.serveType from oldpeopleinfo o,userregister r,userordergenerate u where u.userId=r.userId and r.mobile=o.phone and u.orderId="+orderIdList.get(i);
                ResultSet rs2 = stmt2.executeQuery(sql2);
                while (rs2.next()){
                    serveIdList.add(rs2.getInt(1));
                }
                Statement stmt3 = conn.createStatement();
                String sql3 = "update userordergenerate set serveType="+serveIdList.get(i)+" WHERE orderId="+orderIdList.get(i);
                int rs3 = stmt3.executeUpdate(sql3);
                if(rs3<1){
                    System.out.print("任务执行失败,请检查orderId与serveId:"+orderIdList.get(i)+","+serveIdList.get(i));
                    rsl=0;
                }
                stmt2.close();
                stmt3.close();
            }
            conn.close();
            stmt1.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rsl;
    }
    Integer changeOrderTime() {
        ArrayList<orderServiceTime> list = new ArrayList<>();
        ArrayList<orderServiceTime> newList = new ArrayList<>();
        ArrayList<orderServiceTime> finalList = new ArrayList<>();
        int rsl = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt1 = conn.createStatement();
            String sql1 = "select orderId,serviceTimeBefore,serviceTimeAfter from userordergenerate where serviceTimeAfter is not null and serviceTimeBefore is not null";
            ResultSet rs = stmt1.executeQuery(sql1);
            while (rs.next()){
                list.add(new orderServiceTime(rs.getInt(1),null,rs.getString(2),rs.getString(3)));
            }
            System.out.println("开始执行订单时间智能分析...");
            for (orderServiceTime o : list) {
                String serviceTimeBefore = o.getServiceTimeBefore();
                String serviceTimeAfter = o.getServiceTimeAfter();
                String secondBefore = serviceTimeBefore.substring(serviceTimeBefore.length() - 4,serviceTimeBefore.length()-2);
                String secondAfter = serviceTimeAfter.substring(serviceTimeAfter.length() - 4,serviceTimeAfter.length()-2);
                String minBefore = serviceTimeBefore.substring(serviceTimeBefore.length() - 6,serviceTimeBefore.length()-4);
                String minAfter = serviceTimeAfter.substring(serviceTimeAfter.length() - 6,serviceTimeAfter.length()-4);
                if (minAfter.equals(minBefore) && secondBefore.equals(secondAfter)) {
                    newList.add(o);
                }
            }
            System.out.println("开始执行订单时间智能重建...");
            TimeFormat timeFormat = new TimeFormat();
            for(orderServiceTime o : newList){
                orderServiceTime newOST = timeFormat.changeOrderTime(o);
                finalList.add(newOST);
            }
            for(orderServiceTime o:finalList){
                Statement stmt2 = conn.createStatement();
                String sql2 = "update userordergenerate set serviceTimeAfter='"+o.getServiceTimeAfter()+"' where orderId="+o.getOrderId();
                if(stmt2.executeUpdate(sql2)<1) System.out.print("任务执行失败，请检查订单ID："+o.getOrderId());
                stmt2.close();
            }
            conn.close();
            stmt1.close();
            rsl=1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return rsl;
    }
    Integer instrClientTypeTable() {
        ArrayList<OldClientTypeObj> oldList = new ArrayList<>();
        String time = new TimeFormat().getToDayFullStrTime();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt0 = conn.createStatement();
            String sql0 = "select oldId,clientType from oldpeopleinfo";
            ResultSet rs0 = stmt0.executeQuery(sql0);
            while(rs0.next()){
                oldList.add(new OldClientTypeObj(rs0.getInt(1),rs0.getInt(2),time,1));
            }
            for(OldClientTypeObj o:oldList){
                Statement stmt1 = conn.createStatement();
                String sql1 = "insert into oldCustomerType(oldId,customerTypeId,createTime,status) value("+o.getOldId()+","+o.getCustomerTypeId()+",'"+o.getCreateTime()+"',"+o.getStatus()+")";
                int rs = stmt1.executeUpdate(sql1);
                if(rs<1) System.out.println("新建数据失败，请检查老人ID："+o.getOldId());
                stmt1.close();
            }
            stmt0.close();
            conn.close();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    Integer findOldInfoByIdNumber(String idNumber) {
        //sql
        return 0;
    }
    int pbxDataChange(String sql) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt0 = conn.createStatement();
            if(stmt0.executeUpdate(sql)==1){
                stmt0.close();
                conn.close();
                return 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    void findOldByHometownAreaCode(String buffer) {
        ArrayList<OldByFunction19> oldList = new ArrayList<>();
        ArrayList<callLog> logArrayList = new ArrayList<>();
        String key = "'%"+buffer+"%'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt0 = conn.createStatement();
            String sql0 = "select cusname,phone from oldpeopleinfo where hometownAreaCode like "+key;
            ResultSet rs0 = stmt0.executeQuery(sql0);
            while (rs0.next()){
                oldList.add(new OldByFunction19(rs0.getString("cusname"),rs0.getString("phone")));
            }
            conn.close();
            stmt0.close();

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection("jdbc:mysql://124.227.230.82:9884/lianfsdb?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull", "root", "wuzhoudb123");
            Statement stmt1 = conn1.createStatement();
            for(OldByFunction19 old : oldList){
                String sql1 = "select caller_id_number,destination_number,start_stamp,liancalltype,billsec from liancdrall where caller_id_number = '"+old.getPhone()+"' or destination_number = '"+old.getPhone()+"'";
                ResultSet rs1 = stmt1.executeQuery(sql1);
                while (rs1.next()){
                    if(rs1.getInt("billsec")>0) logArrayList.add(new callLog(rs1.getString("start_stamp"),rs1.getString("caller_id_number"),rs1.getString("destination_number"),rs1.getString("liancalltype"),"已接听",rs1.getInt("billsec"),old.getName()));
                    else logArrayList.add(new callLog(rs1.getString("start_stamp"),rs1.getString("caller_id_number"),rs1.getString("destination_number"),rs1.getString("liancalltype"),"未接听",rs1.getInt("billsec"),old.getName()));
                }
            }
            conn1.close();
            stmt1.close();
            File filename = new File("/Users/mac/javaProject/callLog.txt");
            filename.createNewFile();
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8)));
                for(callLog log : logArrayList){
                    String text = "通话时间："+log.getStart_stamp()+"呼叫号码："+log.getCaller_id_number()+"接听号码："+log.getDestination_number()+"呼叫类型："+log.getLiancalltype()+"呼叫状态："+log.getCallStatus()+"通话时长："+log.getBillsec()+"用户姓名："+log.getCusname();
                    System.out.println(text);
                    out.write(text+"\n");
                }
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void findOldServiceCountByServerType(int serType) {
        ArrayList<oldObject> oldList = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt1 = conn1.createStatement();
            String sql1 = "select cusname,homeAddress,hometownAreaCode,sex,idNumber,birthDate,residenceAddress,userbody,phone,cid from oldpeopleinfo where serveType="+serType;
            ResultSet rs1 = stmt1.executeQuery(sql1);
            while (rs1.next()){
                oldList.add(new oldObject(
                        rs1.getString("cusname"),
                        rs1.getString("homeAddress"),
                        rs1.getString("hometownAreaCode"),
                        rs1.getString("sex"),
                        rs1.getString("idNumber"),
                        rs1.getString("birthDate"),
                        rs1.getString("residenceAddress"),
                        rs1.getString("userbody"),
                        rs1.getString("phone"),
                        rs1.getString("cid")));
            }
            if(oldList.size()>0){
                System.out.println("已找到对应类型老人，正在查找服务数据...");
                ArrayList<Integer> callLogList = new ArrayList<>();
                ArrayList<Integer> orderList = new ArrayList<>();
                for (oldObject old : oldList) {
                    int userId = 0;
                    String sql2 = "select count(*) from cdr_table_ab where callee_id_number='" + old.getPhone() + "' or destination_number='" + old.getPhone()+"'";
                    ResultSet rs2 = stmt1.executeQuery(sql2);
                    while (rs2.next()) {
                        callLogList.add(rs2.getInt(1));
                    }
                    String sql3 = "select userId from userregister where mobile='" + old.getPhone()+"'";
                    ResultSet rs3 = stmt1.executeQuery(sql3);
                    while (rs3.next()) {
                        userId = rs3.getInt("userId");
                    }
                    String sql4 = "select count(*) from userordergenerate where userId=" + userId;
                    ResultSet rs4 = stmt1.executeQuery(sql4);
                    while (rs4.next()) {
                        orderList.add(rs4.getInt(1));
                    }
                }
                stmt1.close();
                conn1.close();
                if(oldList.size()==callLogList.size() && oldList.size()==orderList.size()){
                    System.out.println("搜索结束，正在生成文件...");
                    File filename = new File("/Users/mac/javaProject/callLog.txt");
                    filename.createNewFile();
                    try {
                        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8)));
                        String text = "姓名,联系方式,身份证号码,出生日期,通话记录数量,订单服务数量";
                        out.write(text+"\n");
                        for(int i=0;i<oldList.size();i++){
                            oldObject old = oldList.get(i);
                            Integer callLog = callLogList.get(i);
                            Integer order = orderList.get(i);
                            String data = old.getCusname()+","+old.getPhone()+","+old.getIdNumber()+","+old.getBirthDate()+","+callLog+","+order;
                            out.write(data+"\n");
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void findBackSystemDataByClientType(int clientType) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
            Statement stmt = conn.createStatement();
            String sql0 = "select o.cusname,u.merchantName,m.merchantName,n.realName,u.orderId,u.orderRemark,e.comment from oldpeopleinfo o,userregister r,userordergenerate u,nursingworkersinfo n,merchantsinfo m,orderevaluation e where e.orderId=u.orderId and n.merchantId=m.merchantId and u.nursingId=n.nursingId and o.phone=r.mobile and u.userId=r.userId and o.clientType = "+clientType;
            ResultSet rs0 = stmt.executeQuery(sql0);
            while (rs0.next()){
                String o_cusname=rs0.getString(1);
                String u_merchantName=rs0.getString(2);
                String m_merchantName=rs0.getString(3);
                String n_realName=rs0.getString(4);
                String u_orderId=rs0.getString(5);
                String u_orderRemark=rs0.getString(6);
                String e_comment=rs0.getString(7);
                list.add(o_cusname+","+u_merchantName+","+m_merchantName+","+n_realName+","+u_orderId+","+u_orderRemark+","+e_comment);
            }
            conn.close();
            stmt.close();
            if(list.size()>0){
                File filename = new File("/Users/mac/javaProject/bigData.csv");
                filename.createNewFile();
                try {
                    BufferedWriter csv = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "GB2312"));
                    for(String text : list){
                        System.out.println(text);
                        csv.write(text+"\n");
                    }
                    csv.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else System.out.println("结果为空！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void changeOrderTimeByOrderId(HashMap<Integer, String> hashMap) {
        TimeFormat timeFormat = new TimeFormat();
        int line=1;
        for(Map.Entry<Integer, String> entry: hashMap.entrySet()) {
            Integer orderId = entry.getKey();
            String serviceTime = entry.getValue();
            orderServiceTime timeObj = timeFormat.changeOrderTimeByServiceTime(orderId,serviceTime);
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
                Statement stmt1 = conn.createStatement();
                String sql = "update userordergenerate set orderTime='"+timeObj.getOrderTime()+"',serviceTimeBefore='"+timeObj.getServiceTimeBefore()+"',serviceTimeAfter='"+timeObj.getServiceTimeAfter()+"' where orderId="+timeObj.getOrderId();
                int i = stmt1.executeUpdate(sql);
                if(i==1){
                    System.out.println("第"+line+"条执行成功");
                }
                stmt1.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            line+=1;
        }
    }
    HashMap<Integer, ArrayList<ServiceTable>> createServiceTableFile(ArrayList<Integer> list) {
        ArrayList<Integer> oldIdList = new ArrayList<>();
        ArrayList<ServiceTable> serviceTablesList = new ArrayList<>();
        HashMap<Integer, ArrayList<ServiceTable>> map = new HashMap<>();
        for(Integer orderId:list){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_Url, DB_USER, DB_PASS);
                Statement stmt = conn.createStatement();
                String sql = "select o.oldId,o.cusname,u.consigneeName,u.shippingAddress,u.merchantName as goodsName,u.serviceTimeBefore,u.serviceTimeAfter,n.realName,m.merchantName from userordergenerate u,userregister r,oldpeopleinfo o,nursingworkersinfo n,merchantcommodity c,merchantsinfo m where u.commodityId=c.commodityId and c.merchantId=m.merchantId and o.phone=r.mobile and r.userId=u.userId and o.`status`!=0 and r.`status`!=0 and u.nursingId=n.nursingId and u.orderId="+orderId;
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    oldIdList.add(rs.getInt("oldId"));
                    serviceTablesList.add(new ServiceTable(rs.getInt("oldId"),orderId,rs.getString("cusname"),rs.getString("consigneeName"),rs.getString("shippingAddress"),rs.getString("goodsName"),rs.getString("serviceTimeBefore"),rs.getString("serviceTimeAfter"),rs.getString("realName"),rs.getString("merchantName")));
                }
                stmt.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//        assert oldIdList.size()==list.size():"找到的老人id数量与订单数量不匹配，程序已终止，请检查数据库关联问题：oldId数量："+oldIdList.size()+",订单数量："+list.size();
        assert serviceTablesList.size()==oldIdList.size():"找到的老人id数量与老人对象数量不匹配，程序已终止，请检查数据库关联问题:老人对象数量："+serviceTablesList.size()+",订单数量："+list.size();
        for (Integer oldId : oldIdList) {
            ArrayList<ServiceTable> valueList = new ArrayList<>();
            for (ServiceTable serviceTable : serviceTablesList) {
                if (oldId.equals(serviceTable.getOldId())) {
                    valueList.add(serviceTable);
                }
            }
            map.put(oldId, valueList);
        }
        assert map.size()>0:"无法构建文件内容";
        return map;
    }
    void instrOCP_Question(ArrayList<OracleCertification> list) {
        for(OracleCertification o : list){
            try(Connection connection = DriverManager.getConnection(Local_DB_Url, Local_DB_USER, Local_DB_PASS);
                Statement statement = connection.createStatement()){
                String sql = "insert into OCP (question,optionA,optionB,optionC,optionD,answer,img) value('"+o.getQuestion()+"','"+o.getOptionA()+"','"+o.getOptionB()+"','"+o.getOptionC()+"','"+o.getOptionD()+"','"+o.getAnswer()+"','"+o.getImg()+"')";
                int rs = statement.executeUpdate(sql);
                if(rs<1) {
                    throw new Exception("写入失败，题目为："+o.toString());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}