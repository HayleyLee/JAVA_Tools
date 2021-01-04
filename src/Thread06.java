public class Thread06 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(25000, 30000)==1){
            System.out.println("线程6执行完毕");
        }
    }
}
