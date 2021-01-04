public class Thread01 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(0, 5000)==1){
            System.out.println("线程1执行完毕");
        }
    }
}
