public class Thread13 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(60000, 65000)==1){
            System.out.println("线程13执行完毕");
        }
    }
}
