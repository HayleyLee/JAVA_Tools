public class Thread07 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(30000, 35000)==1){
            System.out.println("线程7执行完毕");
        }
    }
}
