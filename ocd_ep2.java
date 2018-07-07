import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class ocd_ep2 {
    static int ax, bx, cx, dx, PC, ZERO;

    // Baseado no http://www.guj.com.br/t/transforma-decimal-em-binario/47061
    public static String DecimalParaBinario(int c2, int length) {

        int d = c2;
        StringBuffer binario = new StringBuffer(); // guarda os dados

        while (d > 0) {
            int b = d % 2;
            binario.append(b);
            d = d >> 1; // é a divisão que você deseja
        }

        int length1 = length - binario.length();
        
        for (int i = 0; i < length1; i++) {
            if (length1 - i == 1) {
                binario.append(1);
                break;
            }
            binario.append(0);
        }

        return binario.reverse().toString(); // inverte a ordem e imprime
    }

    public static int BinarioParaDecimal(int binario) {
        // System.out.println(binario);
        String myString = new StringBuilder(Integer.toString(binario)).reverse().toString();
        int result = 0;

        for (int i = 0; i < myString.length(); i++) {
            result += Character.getNumericValue(myString.charAt(i)) * Math.pow(2, i);
        }

        return result;
    }

    static void compilaCode(FileReader read, List<M> fila) throws IOException {
        BufferedReader leitor = new BufferedReader(read);
        String line;
        int enderecoMEM = 0;// endereco do comando
        int length = 0;

        String instrucao = "";
        String c1 = "";
        String c2 = "";

        String instrucao_bin = "";
        // String c1_bin = "000000";
        // String c2_bin = "000000";

        while ((line = leitor.readLine()) != null) {
            String[] parts = line.split(" ");
            instrucao = parts[0];

            switch (instrucao) {
                case "mov":
                    instrucao_bin = "0001";
                    break;
                case "inc":
                    instrucao_bin = "0010";
                    break;
                case "add":
                    instrucao_bin = "0011";
                    break;
                case "sub":
                    instrucao_bin = "0100";
                    break;
                case "mul":
                    instrucao_bin = "0101";
                    break;
                case "div":
                    instrucao_bin = "0110";
                    break;
                case "cmp":
                    instrucao_bin = "0111";
                    break;
                case "je":
                    instrucao_bin = "1000";
                    break;
                case "jne":
                    instrucao_bin = "1001";
                    break;
                case "jg":
                    instrucao_bin = "1010";
                    break;
                case "jge":
                    instrucao_bin = "1011";
                    break;
                case "jl":
                    instrucao_bin = "1100";
                    break;
                case "jle":
                    instrucao_bin = "1101";
                    break;
            }

            if (parts[1].contains(",")) {
                String[] c = parts[1].split(",");
                c1 = c[0];
                c2 = c[1];

                switch (c1) {
                    case "ax":
                        instrucao_bin += "0001";
                        break;
                    case "bx":
                        instrucao_bin += "0010";
                        break;
                    case "cx":
                        instrucao_bin += "0011";
                        break;
                    case "dx":
                        instrucao_bin += "0100";
                        break;
                    case "[ax]":
                        instrucao_bin += "0101";
                        break;
                    case "[bx]":
                        instrucao_bin += "0110";
                        break;
                    case "[cx]":
                        instrucao_bin += "0111";
                        break;
                    case "[dx]":
                        instrucao_bin += "1000";
                        break;
                }

                try {
                    length = 8;
                    instrucao_bin += DecimalParaBinario(Integer.parseInt(c2), length); // caso o resto da instrucao seja um int
                }
                catch (Exception e) { // caso seja um registrador
                    switch (c2) {
                        case "axax":
                            instrucao_bin += "00000001";
                            break;
                        case "bx":
                            instrucao_bin += "00000010";
                            break;
                        case "cx":
                            instrucao_bin += "00000011";
                            break;
                        case "dx":
                            instrucao_bin += "00000100";
                            break;
                        case "[ax]":
                            instrucao_bin += "00000101";
                            break;
                        case "[bx]":
                            instrucao_bin += "00000110";
                            break;
                        case "[cx]":
                            instrucao_bin += "00000111";
                            break;
                        case "[dx]":
                            instrucao_bin += "00001000";
                            break;
                    }
                }

            } else {
                c1 = parts[1];
                try {
                    length = 8;
                    instrucao_bin += "0000" + DecimalParaBinario(Integer.parseInt(c1), length); // caso o resto da instrucao seja um int
                } catch (Exception e) { // caso seja um registrador
                    switch (c1) {
                        case "ax":
                            instrucao_bin += "000000000001";
                            break;
                        case "bx":
                            instrucao_bin += "000000000010";
                            break;
                        case "cx":
                            instrucao_bin += "000000000011";
                            break;
                        case "dx":
                            instrucao_bin += "000000000100";
                            break;
                        case "[ax]":
                            instrucao_bin += "000000000101";
                            break;
                        case "[bx]":
                            instrucao_bin += "000000000110";
                            break;
                        case "[cx]":
                            instrucao_bin += "000000000111";
                            break;
                        case "[dx]":
                            instrucao_bin += "000000001000";
                            break;
                    }
                }
            }
            System.out.println(instrucao_bin);
            M objeto = new M(enderecoMEM, instrucao_bin);
            fila.add(objeto);
            enderecoMEM++;
        }
    }

    static void inc (int A, int[] flags) {
        add(A, 1, flags);
    }
    
    static void add(int A, int B, int[] flags) {
        if (flags[5] == 1) {
            ocd_ep2.ax = A + B;
        }
        if (flags[7] == 1) {
            ocd_ep2.bx = A + B;
        }
        if (flags[9] == 1) {
            ocd_ep2.cx = A + B;
        }
        if (flags[11] == 1) {
            ocd_ep2.dx = A + B;
        }
    }

    static void sub(int A, int B, int[] flags) {
        if (flags[5] == 1) {
            ocd_ep2.ax = A - B;
            if (ocd_ep2.ax == 0) ocd_ep2.ZERO = 1; // flag de soma 0
            
        }
        if (flags[7] == 1) {
            ocd_ep2.bx = A - B;
            if (ocd_ep2.bx == 0) ocd_ep2.ZERO = 1; // flag de soma 0
        }
        if (flags[9] == 1) {
            ocd_ep2.cx = A - B;
            if (ocd_ep2.cx == 0) ocd_ep2.ZERO = 1; // flag de soma 0
        }
        if (flags[11] == 1) {
            ocd_ep2.dx = A - B;
            if (ocd_ep2.dx == 0) ocd_ep2.ZERO = 1; // flag de soma 0
        }
    }

    static void mul(int A, int B, int[] flags) {
        if (flags[5] == 1) {
            ocd_ep2.ax = A * B;
        }
        if (flags[7] == 1) {
            ocd_ep2.bx = A * B;
        }
        if (flags[9] == 1) {
            ocd_ep2.cx = A * B;
        }
        if (flags[11] == 1) {
            ocd_ep2.dx = A * B;
        }
    }

    static void div(int A, int B, int[] flags) {
        if (flags[5] == 1) {
            ocd_ep2.ax = A / B;
        }
        if (flags[7] == 1) {
            ocd_ep2.bx = A / B;
        }
        if (flags[9] == 1) {
            ocd_ep2.cx = A / B;
        }
        if (flags[11] == 1) {
            ocd_ep2.dx = A / B;
        }
    }

    static void cmp(int A, int B, int[] flags) {
        sub(A, B, flags); //verifica conta passada if==0 entao eh igual        
    }

    static void je(int A) {
        if (ocd_ep2.ZERO == 1) {
            ocd_ep2.PC = A; // altera linha de PC para o jump
            ocd_ep2.ZERO = 0;
        }
        
    }

    static void leCodigo(String code, int[] flags, List<M> fila) {
        String instru = code.substring(0,4);
        String a = code.substring(4,8);
        String b = code.substring(9,16);
        int reg1 = 0;
        int reg2 = 0;

        System.out.println(ocd_ep2.PC);

        switch (a) {
            case "0001":
                reg1 = ocd_ep2.ax;
                flags[5] = 1;  //porta de entrada do reg do diagrama - 1
                break;
            case "0010":
                reg1 = ocd_ep2.bx;
                flags[7] = 1;
                break;
            case "0011":
                reg1 = ocd_ep2.cx;
                flags[9] = 1;
                break;
            case "0100":
                reg1 = ocd_ep2.dx; //portas de dx entram entre a seq do diagrama
                flags[11] = 1;
                break;
            case "0101":
                //instrucao_bin += "0101";
                break;
            case "0110":
                //instrucao_bin += "0110";
                break;
            case "0111":
                //instrucao_bin += "0111";
                break;
            case "1000":
                //instrucao_bin += "1000";
                break;
        }

        if (code.charAt(8) == '1') {
            reg2 = BinarioParaDecimal(Integer.parseInt(b));
        } else {
            switch (b) {
                case "0001":
                    reg2 = ocd_ep2.ax;
                    flags[6] = 1;  //porta de saida do reg do diagrama - 1
                    break;
                case "0010":
                    reg2 = ocd_ep2.bx;
                    flags[8] = 1;
                    break;
                case "0011":
                    reg2 = ocd_ep2.cx;
                    flags[10] = 1;
                    break;
                case "0100":
                    reg2 = ocd_ep2.dx; 
                    flags[12] = 1;
                    break;
                case "0101":
                    //instrucao_bin += "0101";
                    break;
                case "0110":
                    //instrucao_bin += "0110";
                    break;
                case "0111":
                    //instrucao_bin += "0111";
                    break;
                case "1000":
                    //instrucao_bin += "1000";
                    break;
            }
        }                    

        switch (instru) {
            case "0001":
                // mov();
                break;            
            case "0010":
                inc(reg1, flags);
                break;
            case "0011":
                add(reg1, reg2, flags);
                break;
            case "0100":
                sub(reg1, reg2, flags);
                break;
            case "0101":
                mul(reg1, reg2, flags);
                break;
            case "0110":
                div(reg1, reg2, flags);
                break;
            case "0111":
                cmp(reg1, reg2, flags);
                break;
            case "1000":
                je(reg1);
                break;
            case "1001":
                //jne(reg1);
                break;
            case "1010":
                // jg(reg1);
                break;
            case "1011":
                // jge(reg1);
                break;
            case "1100":
                // jl(reg1);
                break;
            case "1101":
                // jle(reg1);
                break;
        }

        busca(fila, flags);
    }

    static void busca(List<M> lista,int[] flags) {

        // Iterator it = lista.iterator();

        // while (it.hasNext()) {
        //     M MAR = lista.get(ocd_ep2.PC);
        //     if (MAR.endereco == ocd_ep2.PC) {
        //         ocd_ep2.PC++;
        //         leCodigo(MAR.opCode, flags, lista) ;
        //     }
        // }

        // M MAR = lista.get(ocd_ep2.PC);

        for (M elemento : lista) {
            if (elemento.endereco == ocd_ep2.PC) {
                ocd_ep2.PC++;
                leCodigo(elemento.opCode, flags, lista) ;
            }

        }
        
    }
    static void registradores() { // Printa os registradores apos ciclo

    }


    public static void main(String[] args) throws IOException {
        int[] flags = new int[24]; // barramento 
        ocd_ep2.PC = 0;
        List<M> fila = new LinkedList<M>(); // lista ligada com o binario e endereco

        if (args[0] != null) compilaCode(new FileReader(args[0]), fila);

        //leCodigo(busca(fila, ocd_ep2.PC, flags), flags, fila);
        busca(fila, flags);

    }
}