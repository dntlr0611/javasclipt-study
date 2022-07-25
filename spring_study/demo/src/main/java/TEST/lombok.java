package TEST;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class lombok {

    private String hello;
    private int lombok;

    public static void main(String[] args) {
        lombok helloLombok = new lombok();
        helloLombok.setHello("헬로");
        helloLombok.setLombok(5);

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());
    }
}