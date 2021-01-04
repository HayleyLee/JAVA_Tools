public class Thread05 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(20000, 25000)==1){
            System.out.println("线程5执行完毕");
        }
    }
}
