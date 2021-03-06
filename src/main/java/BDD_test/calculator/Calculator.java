package BDD_test.calculator;

public class Calculator {

    public Integer addTwoNumbers(Integer int1, Integer int2) {
        /*
        Integer result = int1 + int2;
        return result;*/
        return int1+int2;
    }

    public Integer subtractTwoNum(Integer a, Integer b) {
        return a-b;
    }

    public Integer multiplyTwoNum(Integer a, Integer b ) {
        return a*b;
    }

    public Double divideTwoNum(Double a, Double b) {
        return a/b;
    }
}
