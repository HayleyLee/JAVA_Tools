public class Thread02 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(5000, 10000)==1){
            System.out.println("线程2执行完毕");
        }
    }
}
