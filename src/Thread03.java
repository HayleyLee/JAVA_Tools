public class Thread03 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(10000, 15000)==1){
            System.out.println("线程3执行完毕");
        }
    }
}
