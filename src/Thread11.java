public class Thread11 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(50000, 55000)==1){
            System.out.println("线程11执行完毕");
        }
    }
}
