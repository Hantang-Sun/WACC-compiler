package armPrinter;

public class IOFunctionTranslator {
  public IOFunctionTranslator() {
  }

  //IMPORTANT the following msgs are reserved and should not be used for other purposes.
  //msg 0 "%.*s\0"
  //msg 1 "\0"
  //msg 2 "%d\0"
  //msg 3 "%c\0"
  //msg 4 "true\0"
  //msg 5 "false\0"
  //msg 6 overflow
  //msg 7 div by 0
  //msg 8 "%p\0"

  public String getPrintInt() {
    return  "p_print_int:\n" +
            "\tPUSH {lr}\n" +
            "\tMOV r1, r0\n" +
            "\tLDR r0, =msg_2\n" +
            "\tADD r0, r0, #4\n" +
            "\tBL printf\n" +
            "\tMOV r0, #0\n" +
            "\tBL fflush\n" +
            "\tPOP {pc}\n";
  }

  public String getPrintString() {
    return  "p_print_string:\n" +
            "\tPUSH {lr}\n" +
            "\tLDR r1, [r0]\n" +
            "\tADD r2, r0, #4\n" +
            "\tLDR r0, =msg_0\n" +
            "\tADD r0, r0, #4\n" +
            "\tBL printf\n" +
            "\tMOV r0, #0\n" +
            "\tBL fflush\n" +
            "\tPOP {pc}\n";
  }

  public String getPrintNewLine() {
    return  "p_print_ln:\n" +
            "\tPUSH {lr}\n" +
            "\tLDR r0, =msg_1\n" +
            "\tADD r0, r0, #4\n" +
            "\tBL puts\n" +
            "\tMOV r0, #0\n" +
            "\tBL fflush\n" +
            "\tPOP {pc}\n";
  }

  public String getPrintBool() {
    return  "p_print_bool:\n" +
            "\tPUSH {lr}\n" +
            "\tCMP r0, #0\n" +
            "\tLDRNE r0, =msg_4\n" +
            "\tLDREQ r0, =msg_5\n" +
            "\tADD r0, r0, #4\n" +
            "\tBL printf\n" +
            "\tMOV r0, #0\n" +
            "\tBL fflush\n" +
            "\tPOP {pc}\n";
  }

  public String getPrintReference() {
    return "p_print_reference:\n" +
            "\tPUSH {lr}\n" +
            "\tMOV r1, r0\n" +
            "\tLDR r0, =msg_8\n" +
            "\tADD r0, r0, #4\n" +
            "\tBL printf\n" +
            "\tMOV r0, #0\n" +
            "\tBL fflush\n" +
            "\tPOP {pc}\n";
  }

  public String getReadChar() {
    return "p_read_char:\n" +
            "\tPUSH {lr}\n" +
            "\tMOV r1, r0\n" +
            "\tLDR r0, =msg_3\n" +
            "\tADD r0, r0, #4\n" +
            "\tBL scanf\n" +
            "\tPOP {pc}\n";
  }

  public String getReadInt() {
    return  "p_read_int:\n" +
            "\tPUSH {lr}\n" +
            "\tMOV r1, r0\n " +
            "\tLDR r0, =msg_2\n" +
            "\tADD r0, r0, #4\n" +
            "\tBL scanf\n" +
            "\tPOP {pc}\n";
  }

  public String getOverflowError() {
    return "p_throw_overflow_error:\n" +
            "\tLDR r0, =msg_6\n" +
            "\tBL p_throw_runtime_error\n";
  }

  public String getRuntimeError() {
    return "p_throw_runtime_error:\n" +
            "\tBL p_print_string\n" +
            "\tMOV r0, #-1\n" +
            "\tBL exit\n";
  }

  public String getDivisionByZeroError() {
    return "p_check_divide_by_zero:\n" +
            "\tPUSH {lr}\n" +
            "\tCMP r1, #0\n" +
            "\tLDREQ r0, =msg_7\n" +
            "\tBLEQ p_throw_runtime_error\n" +
            "\tPOP {pc}\n";
  }

  public String getArrayBoundsCheck() {
    return "p_check_array_bounds:\n" +
    	    	"\tPUSH {lr}\n" +
            "\tCMP r0, #0\n" +
            "\tLDRLT r0, =msg_9\n" +
            "\tBLLT p_throw_runtime_error\n" +
            "\tLDR r1, [r1]\n" +
            "\tCMP r0, r1\n" +
            "\tLDRCS r0, =msg_10\n" +
            "\tBLCS p_throw_runtime_error\n" +
            "\tPOP {pc}\n";
  }

  public String getNullPointerCheck() {
    return "p_check_null_pointer:\n" +
        		"\tPUSH {lr}\n" +
            "\tCMP r0, #0\n" +
            "\tLDREQ r0, =msg_11\n" +
            "\tBLEQ p_throw_runtime_error\n" +
            "\tPOP {pc}\n";
  }

  public String getFreePair() {
    return "p_free_pair:\n" +
            "\tPUSH {lr}\n" +
            "\tCMP r0, #0\n" +
            "\tLDREQ r0, =msg_11\n" +
            "\tBEQ p_throw_runtime_error\n" +
            "\tPUSH {r0}\n" +
            "\tLDR r0, [r0]\n" +
            "\tBL free\n" +
            "\tLDR r0, [sp]\n" +
            "\tLDR r0, [r0, #4]\n" +
            "\tBL free\n" +
            "\tPOP {r0}\n" +
            "\tBL free\n" +
            "\tPOP {pc}\n";
  }
}
