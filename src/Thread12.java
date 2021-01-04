public class Thread12 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(55000, 60000)==1){
            System.out.println("线程12执行完毕");
        }
    }
}
