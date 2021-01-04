public class Thread08 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(35000, 40000)==1){
            System.out.println("线程8执行完毕");
        }
    }
}
