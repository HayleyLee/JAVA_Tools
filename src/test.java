public class test {
    public static void main(String[] args) {
        String a = "System.out.println(test \"a\")";
        System.out.println(a);

        a = a.replaceAll("\"","\\\\\"");
        System.out.println(a);

        String b = "System.out.println(test \'b\')";
        System.out.println(b);

        b = b.replaceAll("'","\\\\'");
        System.out.println(b);
    }
}
