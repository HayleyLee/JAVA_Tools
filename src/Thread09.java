public class Thread09 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(40000, 45000)==1){
            System.out.println("线程9执行完毕");
        }
    }
}
