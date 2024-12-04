package lambda;

public class Test {
    public static void main(String[] args) {
        Student engineeringStudent = new Student() {
            @Override
            public String getBio(String name) {
                return name+" is an engineering student";
            }
        };
        String bio = engineeringStudent.getBio("Ram");
        System.out.println(bio);

        Student lawStudent = name -> name + " is a law student.";

        System.out.println(lawStudent.getBio("Anish"));
    }

}
