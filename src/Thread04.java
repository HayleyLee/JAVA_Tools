public class Thread04 extends Thread{
    public void run(){
        JDBC j = new JDBC();
        if(j.instrOrderClientType(15000, 20000)==1){
            System.out.println("线程4执行完毕");
        }
    }
}
