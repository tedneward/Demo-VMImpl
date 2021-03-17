/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.newardassociates.simplevm;

// choose longs or ints for everything to avoid byte-packing issues
// make all fields package for easier testing

/*
(*) gradle init, munge App to Interpreter
(*) create class Interpreter
(*) we need a method to load the code (load(), .code)
(*) we need a method to do the fetch-decode-execute loop (execute())
    which means having a instruction pointer (.ip)
(*) we need some instructions (create class Bytecode, 
    constants NOP/HALT) to execute (add NOP, HALT to execute())
(*) we want some stack-manipulating instructions (CONST, PRINT) 
    which means we need a stack (.stack, .sp)
*/ 

import static com.newardassociates.simplevm.Bytecode.*;

public class Interpreter {
    int[] code;
    int ip;
    int[] stack;
    int sp;

    public Interpreter(String... args) {
    }

    public void load(int[] code) {
        this.code = code;
    }

    public void execute() {
        ip = 0;
        stack = new int[100];
        sp = -1;

        while (ip < code.length) {
            int opcode = code[ip];

            switch(opcode) {
                case NOP:
                    // Do nothing!
                    break;
                case HALT: {
                    // Terminate VM
                    System.exit(-1);
                    break;
                }
                case CONST: {
                    // Push operand onto stack
                    int operand = code[++ip];
                    stack[++sp] = operand;
                    break;
                }
                case PRINT: {
                    // Pop operand off of stack, print it
                    int operand = stack[sp];
                    sp--;
                    System.out.println(">>> " + operand);
                    break;
                }
            }

            ip++;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Interpreter(args));
    }
}
