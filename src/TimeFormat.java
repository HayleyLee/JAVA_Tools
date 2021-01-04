import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.stream.StreamSupport;

class TimeFormat {
    static String createTime(){
        Integer startHour = new Random().nextInt(20) % (20 - 8 + 1) + 8;
        Integer startMin=(int)(Math.random()*60);
        Integer startSecond=(int)(Math.random()*60);
        String strstartHour;
        String strstartMin;
        String strstartSecond;
        if(startHour<10){
            strstartHour="0"+startHour;
        }else {
            strstartHour=startHour.toString();
        }
        if(startMin<10){
            strstartMin="0"+startMin;
        }else {
            strstartMin=startMin.toString();
        }
        if(startSecond<10){
            strstartSecond="0"+startSecond;
        }else {
            strstartSecond=startSecond.toString();
        }
        return strstartHour+":"+strstartMin+":"+strstartSecond;
    }
    ArrayList<String> craeteTimeByOrderTime(String orderTime){
        ArrayList<String> list = new ArrayList<>();
        String[] s = orderTime.split(" ");
        String[] bigTime = s[0].split("-");
        int month = Integer.parseInt(bigTime[1]);
        int day = Integer.parseInt(bigTime[2]);
        Integer newDay = 0;
        if((bigTime[1].equals("01")) || (bigTime[1].equals("03")) || (bigTime[1].equals("05")) || (bigTime[1].equals("07")) || (bigTime[1].equals("08")) || (bigTime[1].equals("10")) || (bigTime[1].equals("12"))){
            newDay = ((int) (Math.random() * 7 + 1)) + day;
            if(newDay>31) {
                month++;
                newDay-=31;
            }
        }
        if((bigTime[1].equals("04")) || (bigTime[1].equals("06")) || (bigTime[1].equals("09")) || (bigTime[1].equals("11"))){
            newDay = ((int) (Math.random() * 7 + 1)) + day;
            if(newDay>30) {
                month++;
                newDay-=30;
            }
        }
        if(bigTime[1].equals("02")){
            newDay = ((int) (Math.random() * 7 + 1)) + day;
            if(newDay>28) {
                month++;
                newDay-=28;
            }
        }
        Integer startHour = new Random().nextInt(17) % (17 - 8 + 1) + 8;
        Integer startMin=(int)(Math.random()*60);
        Integer startSecond=(int)(Math.random()*60);
        Integer endHour=startHour+1;
        String strNewDay;
        String strstartHour;
        String strstartMin;
        String strstartSecond;
        String strendHour;
        String strendMin;
        String strendSecond;
        if(newDay<10){
            strNewDay="0"+newDay;
        }else {
            strNewDay=newDay.toString();
        }
        if(startHour<10){
            strstartHour="0"+startHour;
        }else {
            strstartHour=startHour.toString();
        }
        if(startMin<10){
            strstartMin="0"+startMin;
        }else {
            strstartMin=startMin.toString();
        }
        if(startSecond<10){
            strstartSecond="0"+startSecond;
        }else {
            strstartSecond=startSecond.toString();
        }
        if(endHour<10){
            strendHour="0"+endHour;
        }else {
            strendHour=endHour.toString();
        }
        if(startMin <10){
            strendMin="0"+ startMin;
        }else {
            strendMin= startMin.toString();
        }
        int tempSecond = (int) (Math.random() * 60);
        if(tempSecond <10){
            strendSecond="0"+ tempSecond;
        }else {
            strendSecond= Integer.toString(tempSecond);
        }
        if(startMin>15){
            int tempMin = startMin - (int) (Math.random() * 10 + 1);
            if(tempMin<10){
                strendMin = "0" + tempMin;
            }
            else {
                strendMin = Integer.toString(tempMin);
            }
        }
        String startTime=bigTime[0]+"-"+month+"-"+strNewDay+" "+strstartHour+":"+strstartMin+":"+strstartSecond;
        String endTime=bigTime[0]+"-"+month+"-"+strNewDay+" "+strendHour+":"+strendMin+":"+strendSecond;
        list.add(startTime);
        list.add(endTime);
        return list;
    }
    String getToDayFullStrTime(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        month++;
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String StrMonth = String.valueOf(month);
        String StrDate = String.valueOf(date);
        String StrHour = String.valueOf(hour);
        String StrMinute = String.valueOf(minute);
        String StrSecond = String.valueOf(second);
        if(month<10) StrMonth = "0"+month;
        if(date<10) StrDate = "0"+date;
        if(hour<10) StrHour = "0"+hour;
        if(minute<10) StrMinute = "0"+minute;
        if(second<10) StrSecond = "0"+minute;
        return year+"-"+StrMonth+"-"+StrDate+" "+StrHour+":"+StrMinute+":"+StrSecond;
    }
    orderServiceTime changeOrderTime(orderServiceTime o) {
        int Hour;
        int Min;
        int Second = (int) (Math.random() * 60);
        String finalHour;
        String finalMin;
        String finalSecond;
        String serviceTimeBefore = o.getServiceTimeBefore();
        String serviceTimeAfter = o.getServiceTimeAfter();
        String timeArrBefore = serviceTimeBefore.split(" ")[1];
        String[] timeArrB = timeArrBefore.split(":");
        int min = (int) (Math.random() * 16) + 45;
        if(Integer.parseInt(timeArrB[1])+min>59){
            Hour=Integer.parseInt(timeArrB[0])+1;
            Min=Integer.parseInt(timeArrB[1])+min-60;
        }
        else {
            Hour=Integer.parseInt(timeArrB[0]);
            Min=Integer.parseInt(timeArrB[1])+min;
        }
        if(Hour<10) finalHour="0"+Hour;
        else finalHour= Integer.toString(Hour);
        if(Min<10) finalMin="0"+Min;
        else finalMin= Integer.toString(Min);
        if(Second<10) finalSecond="0"+Second;
        else finalSecond= Integer.toString(Second);
        o.setServiceTimeAfter(serviceTimeAfter.split(" ")[0]+" "+finalHour+":"+finalMin+":"+finalSecond);
        return o;
    }
    orderServiceTime changeOrderTimeByServiceTime(Integer orderId, String serviceTime) {
        String[] timeArr;
        if(serviceTime.contains("-")) timeArr = serviceTime.split("-");
        else if(serviceTime.contains("/")) timeArr = serviceTime.split("/");
        else {
            System.out.println("时间格式不合法，请检查订单id："+orderId);
            return null;
        }
        String orderTime;
        int orderDay = new Random().nextInt(12) % (12 - 7 + 1) + 7;
        int moreDay = Integer.parseInt(timeArr[2]) - orderDay;
        if(moreDay > 9){
            orderTime=timeArr[0]+"-"+timeArr[1]+"-"+moreDay;
        }
        else if(moreDay > 0){
            orderTime=timeArr[0]+"-"+timeArr[1]+"-0"+moreDay;
        }
        else {
            int newDay;
            int month = Integer.parseInt(timeArr[1]) - 1;
            if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
                newDay = 31 + moreDay;
            }
            else if(month==2){
                newDay = 28 + moreDay;
            }
            else {
                newDay = 30 + moreDay;
            }
            if(month>9) orderTime=timeArr[0]+"-"+month+"-"+newDay;
            else orderTime=timeArr[0]+"-0"+month+"-"+newDay;
        }
        int oldDay = Integer.parseInt(timeArr[2]);
        int oldMonth = Integer.parseInt(timeArr[1]);
        String newServiceTime;
        String newMonth;
        String newDay;
        if(oldDay>9) newDay= Integer.toString(oldDay);
        else newDay="0"+oldDay;
        if(oldMonth>9) newMonth= Integer.toString(oldMonth);
        else newMonth="0"+oldMonth;
        newServiceTime=timeArr[0]+"-"+newMonth+"-"+newDay;
        String orderFullTime = orderTime+" "+createTime();
        String startTime = newServiceTime+" "+createTime();
        String endTime = newServiceTime+" "+createTime();
        return changeOrderTime(new orderServiceTime(orderId, orderFullTime, startTime, endTime));
    }
}