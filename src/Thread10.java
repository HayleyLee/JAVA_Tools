public class Thread10 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(45000, 50000)==1){
            System.out.println("线程10执行完毕");
        }
    }
}
