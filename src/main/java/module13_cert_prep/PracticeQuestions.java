package module13_cert_prep;
/**
 * MODULE 13 — Certification Practice Questions
 *
 * Instructions:
 *   For each question:
 *     1. Read the code
 *     2. Write your answer in the comment "YOUR ANSWER:"
 *     3. Then run the code to verify
 *
 */
public class PracticeQuestions {

    public static void main(String[] args) {
        System.out.println("=== SECTION 1: Output Questions ===\n");
        q1_output();
        q2_output();
        q3_output();
        q4_output();
        q5_output();

        System.out.println("\n=== SECTION 2: Inheritance & Polymorphism ===\n");
        q6_inheritance();
        q7_constructorChain();
        q8_overrideVsHide();

        System.out.println("\n=== SECTION 3: Collections & Generics ===\n");
        q9_collections();
        q10_autoboxing();

        System.out.println("\n=== SECTION 4: Lambdas & Streams ===\n");
        q11_lambdas();
        q12_streams();

        System.out.println("\n=== SECTION 5: Exception Handling ===\n");
        q13_exceptions();
        q14_tryFinally();

        System.out.println("\n=== SECTION 6: Modern Java (Records, Switch) ===\n");
        q15_records();
        q16_switchExpression();
    }

    // =========================================================================
    // SECTION 1: What is the output?
    // =========================================================================

    static void q1_output() {
        // Q1: What does this print?
        // YOUR ANSWER: _______________

        int x = 5;
        int y = ++x + x++;
        System.out.println("Q1: x=" + x + ", y=" + y);
        // ANSWER: x=7, y=12
        // Trace: ++x → x=6, then y=6+6=12, then x++ → x=7
    }

    static void q2_output() {
        // Q2: What does this print?
        // YOUR ANSWER: _______________

        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");

        System.out.println("Q2a: " + (s1 == s2));
        System.out.println("Q2b: " + (s1 == s3));
        System.out.println("Q2c: " + s1.equals(s3));
        // ANSWER: true, false, true
    }

    static void q3_output() {
        // Q3: What does this print?
        // YOUR ANSWER: _______________

        Integer a = 100;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;

        System.out.println("Q3a: " + (a == b));
        System.out.println("Q3b: " + (c == d));
        System.out.println("Q3c: " + c.equals(d));
        // ANSWER: true, false, true
        // Integer cache covers -128 to 127
    }

    static void q4_output() {
        // Q4: What does this print?
        // YOUR ANSWER: _______________

        System.out.println("Q4a: " + (10 / 3));
        System.out.println("Q4b: " + (10 / 3.0));
        System.out.println("Q4c: " + (10 % 3));
        System.out.println("Q4d: " + ((double)10 / 3));
        // ANSWER: 3, 3.3333..., 1, 3.3333...
    }

    static void q5_output() {
        // Q5: What does this print?
        // YOUR ANSWER: _______________

        int i = 0;
        int j = 0;

        if (i++ > 0 && j++ > 0) { }
        System.out.println("Q5a: i=" + i + ", j=" + j);

        i = 0; j = 0;
        if (i++ > 0 || j++ > 0) { }
        System.out.println("Q5b: i=" + i + ", j=" + j);
        // ANSWER Q5a: i=1, j=0 (short-circuit AND — j++ never executes)
        // ANSWER Q5b: i=1, j=1 (both evaluated for OR after first is false)
    }

    // =========================================================================
    // SECTION 2: Inheritance & Polymorphism
    // =========================================================================

    static void q6_inheritance() {
        // Q6: What does this print?
        // YOUR ANSWER: _______________

        Animal6 a = new Dog6();
        System.out.println("Q6a: " + a.name);    // field access — reference type
        a.speak();                                  // method call — actual type
        // ANSWER: a.name → "Animal" (fields are NOT polymorphic)
        //         speak() → "Woof!" (methods ARE polymorphic)
    }

    static void q7_constructorChain() {
        // Q7: What is printed when you do: new C7()
        // Write the order: _______________

        new C7();
        // ANSWER: "A", "B", "C"
        // Constructors chain upward first, then execute outward
    }

    static void q8_overrideVsHide() {
        // Q8: What does this print?
        // YOUR ANSWER: _______________

        Parent8 p = new Child8();
        System.out.println("Q8a: " + p.value);   // field
        System.out.println("Q8b: " + p.getValue()); // method
        // ANSWER: 10 (field, reference type), 20 (method, actual type)
    }

    // =========================================================================
    // SECTION 3: Collections & Generics
    // =========================================================================

    static void q9_collections() {
        // Q9: What does this print?
        // YOUR ANSWER: _______________

        java.util.List<String> list = java.util.Arrays.asList("a", "b", "c");
        java.util.List<String> copy = new java.util.ArrayList<>(list);

        list.set(0, "z");      // modifies list
        copy.set(0, "x");      // modifies copy

        System.out.println("Q9a: list[0]=" + list.get(0));
        System.out.println("Q9b: copy[0]=" + copy.get(0));
        System.out.println("Q9c: list[1]=" + list.get(1));
        System.out.println("Q9c: copy[1]=" + copy.get(1));
        // ANSWER: z, x, b, b — they are independent copies
    }

    static void q10_autoboxing() {
        // Q10: What does this print?
        // YOUR ANSWER: _______________

        java.util.List<Integer> list = new java.util.ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(1);     // remove by INDEX (1), not value
        System.out.println("Q10a: " + list);

        list.remove(Integer.valueOf(1));   // remove by VALUE (1)
        System.out.println("Q10b: " + list);
        // ANSWER Q10a: [1, 3] (removed index 1 = value 2)
        // ANSWER Q10b: [3] (removed value 1)
    }

    // =========================================================================
    // SECTION 4: Lambdas & Streams
    // =========================================================================

    static void q11_lambdas() {
        // Q11: What does this print?
        // YOUR ANSWER: _______________

        java.util.function.Function<Integer, Integer> f = x -> x * 2;
        java.util.function.Function<Integer, Integer> g = x -> x + 3;

        // andThen: apply f first, then g
        java.util.function.Function<Integer, Integer> fg = f.andThen(g);
        // compose: apply g first, then f
        java.util.function.Function<Integer, Integer> gf = f.compose(g);

        System.out.println("Q11a andThen: " + fg.apply(5));    // f(5)=10, then g(10)=13
        System.out.println("Q11b compose: " + gf.apply(5));    // g(5)=8, then f(8)=16
        // ANSWER: 13, 16
    }

    static void q12_streams() {
        // Q12: What does this print?
        // YOUR ANSWER: _______________

        long count = java.util.stream.Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .filter(n -> n > 20)
            .count();

        System.out.println("Q12: count=" + count);
        // Trace: evens → [2,4,6,8,10], squared → [4,16,36,64,100], >20 → [36,64,100]
        // ANSWER: 3
    }

    // =========================================================================
    // SECTION 5: Exception Handling
    // =========================================================================

    static void q13_exceptions() {
        // Q13: What does this print?
        // YOUR ANSWER: _______________

        try {
            System.out.println("try");
            if (true) throw new RuntimeException("oops");
            System.out.println("after throw");
        } catch (RuntimeException e) {
            System.out.println("catch: " + e.getMessage());
        } finally {
            System.out.println("finally");
        }
        System.out.println("after try-catch");
        // ANSWER: try, catch: oops, finally, after try-catch
    }

    static void q14_tryFinally() {
        // Q14: What does this return/print?
        // YOUR ANSWER: _______________

        System.out.println("Q14: " + getValueQ14());
        // ANSWER: 3
        // finally OVERRIDES the try's return value
    }

    static int getValueQ14() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;  // this always wins
        }
    }

    // =========================================================================
    // SECTION 6: Modern Java
    // =========================================================================

    static void q15_records() {
        // Q15: What does this print?
        // YOUR ANSWER: _______________

        record Point(int x, int y) {}

        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(5, 6);

        System.out.println("Q15a: " + p1.x());         // getter
        System.out.println("Q15b: " + p1);              // toString
        System.out.println("Q15c: " + p1.equals(p2));   // equals by value
        System.out.println("Q15d: " + (p1 == p2));      // reference equality
        // ANSWER: 3, Point[x=3, y=4], true, false
    }

    static void q16_switchExpression() {
        // Q16: What does this print?
        // YOUR ANSWER: _______________

        for (int i = 1; i <= 4; i++) {
            String type = switch (i) {
                case 1, 3 -> "odd";
                case 2, 4 -> "even";
                default   -> "unknown";
            };
            System.out.println("Q16: " + i + " is " + type);
        }
        // ANSWER: 1 is odd, 2 is even, 3 is odd, 4 is even
    }

    // =========================================================================
    // COMPILE ERROR QUESTIONS
    // =========================================================================
    // Study these — exam often asks "which lines cause a compile error?"

    /*
    void compileErrorExamples() {

        // CE1: Cannot assign to final
        final int x = 5;
        x = 10;  // COMPILE ERROR

        // CE2: Incompatible types (loss of precision)
        int i = 3.14;  // COMPILE ERROR — needs explicit cast: (int) 3.14

        // CE3: String is immutable — this is fine (just creates new object)
        String s = "hello";
        s = s.toUpperCase();   // OK, s now points to new "HELLO"

        // CE4: ArrayList<Integer> cannot accept String
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        list.add("hello");   // COMPILE ERROR

        // CE5: Unreachable statement
        if (true) return;
        System.out.println("unreachable");  // COMPILE ERROR (unreachable)

        // CE6: catch order
        try { }
        catch (Exception e) { }
        catch (RuntimeException e) { }  // COMPILE ERROR — RuntimeException is-a Exception

        // CE7: Access modifiers — cannot reduce visibility in override
    }
    */

    // =========================================================================
    // BONUS: Write your own tricky questions here
    // =========================================================================
    // As you study, add questions that trip you up:
    //
    // static void myOwnQ() {
    //     // question code
    //     // YOUR ANSWER:
    //     // ACTUAL ANSWER:
    // }
}

// Supporting classes for questions
class Animal6 {
    String name = "Animal";
    void speak() { System.out.println("..."); }
}

class Dog6 extends Animal6 {
    String name = "Dog";    // HIDES, not overrides
    @Override void speak() { System.out.println("Woof!"); }
}

class A7 {
    A7() { System.out.println("A"); }
}

class B7 extends A7 {
    B7() { super(); System.out.println("B"); }
}

class C7 extends B7 {
    C7() { super(); System.out.println("C"); }
}

class Parent8 {
    int value = 10;
    int getValue() { return value; }
}

class Child8 extends Parent8 {
    int value = 20;
    @Override int getValue() { return value; }
}
