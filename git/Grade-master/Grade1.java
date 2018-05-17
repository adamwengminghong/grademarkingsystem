public class Grade1
{
    // Convert the mark into a grade   TODO: complete this
    String grade(int mark)
    {
        String result;
        if (mark < 0) result = "Invalid";
        else if (mark < 50) result = "Fail";
        else if (mark < 60) result = "Pass";
        else if (mark < 70) result = "Merit";
        else if (mark <= 100) result = "Distinction";
        else result = "Invalid";
        return result;
    }

    // Convert string to int, or -1 if invalid TODO: complete this
    int convert(String mark)
    {
        if (mark.length() > 1 && mark.charAt(0) == '0') return -1;
        int n;
        try {
          n = Integer.parseInt(mark);
        } catch (Exception err) {
            n = -1;
        }
        if (n > 100) n = -1;
        return n;
    }

    // Crash the program if a test fails (instead of assert)
    void claim(boolean b)
    {
      if (!b) throw new Error("Test failure");
    }

    // Run the tests.
    void test()
    {
        // Check each grade
        claim(grade(45).equals("Fail"));
        claim(grade(55).equals("Pass"));
        claim(grade(65).equals("Merit"));
        claim(grade(75).equals("Distinction"));
        // 0..49 fail, 50..59 pass, 60..69 merit, 70..100 distinction
        claim(grade(0).equals("Fail"));
        claim(grade(49).equals("Fail"));
        claim(grade(50).equals("Pass"));
        claim(grade(59).equals("Pass"));
        claim(grade(60).equals("Merit"));
        claim(grade(69).equals("Merit"));
        claim(grade(70).equals("Distinction"));
        claim(grade(100).equals("Distinction"));
        // Out of range gives Invalid
        claim(grade(-1).equals("Invalid"));
        claim(grade(101).equals("Invalid"));
        // Convert works on numbers in range
        claim(convert("0") == 0);
        claim(convert("53") == 53);
        claim(convert("100") == 100);
        // Non-digits or number out of range or leading zero gives -1
        // (Note: leading zeros are potentially ambiguous because they
        // indicate octal in some circumstances)
        claim(convert("x") == -1);
        claim(convert("5x") == -1);
        claim(convert("5x6") == -1);
        claim(convert("40.5") == -1);
        claim(convert("-1") == -1);
        claim(convert("101") == -1);
        claim(convert("01") == -1);
        claim(convert("099") == -1);
        System.out.println("All tests passed");
    }

    void run(String[] args)
    {
        if (args.length == 0) test();
        else if (args.length == 1) System.out.println(grade(convert(args[0])));
        else {
            System.err.println("Use:  java Grade   or  java Grade m");
            System.err.println("where m is an integer mark from 0 to 100");
            System.exit(1);
        }
    }

    public static void main(String[] args)
    {
        Grade1 program = new Grade1();
        program.run(args);
    }
}
