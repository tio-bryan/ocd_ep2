import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class ocd_ep2 {
    static int AX, BX, CX, DX;

    // Baseado no http://www.guj.com.br/t/transforma-decimal-em-binario/47061
    public static String DecimalParaBinario(int c2) {

        int d = c2;
        StringBuffer binario = new StringBuffer(); // guarda os dados

        while (d > 0) {
            int b = d % 2;
            binario.append(b);
            d = d >> 1; // é a divisão que você deseja
        }

        int length = 8 - binario.length();
        
        for (int i = 0; i < length; i++) {
            if (length - i == 1) {
                binario.append(1);
                break;
            }
            binario.append(0);
        }

        return binario.reverse().toString(); // inverte a ordem e imprime
    }

    public static int BinarioParaDecimal(int binario) {
        
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
                case "MOV":
                    instrucao_bin = "0001";
                    break;
                case "INC":
                    instrucao_bin = "0010";
                    break;
                case "ADD":
                    instrucao_bin = "0011";
                    break;
                case "SUB":
                    instrucao_bin = "0100";
                    break;
                case "MUL":
                    instrucao_bin = "0101";
                    break;
                case "DIV":
                    instrucao_bin = "0110";
                    break;
                case "CMP":
                    instrucao_bin = "0111";
                    break;
                case "JE":
                    instrucao_bin = "1000";
                    break;
                case "JNE":
                    instrucao_bin = "1001";
                    break;
                case "JG":
                    instrucao_bin = "1010";
                    break;
                case "JGE":
                    instrucao_bin = "1011";
                    break;
                case "JL":
                    instrucao_bin = "1100";
                    break;
                case "JLE":
                    instrucao_bin = "1101";
                    break;
            }

            if (parts[1].contains(",")) {
                String[] c = parts[1].split(",");
                c1 = c[0];
                c2 = c[1];

                switch (c1) {
                    case "AX":
                        instrucao_bin += "0001";
                        break;
                    case "BX":
                        instrucao_bin += "0010";
                        break;
                    case "CX":
                        instrucao_bin += "0011";
                        break;
                    case "DX":
                        instrucao_bin += "0100";
                        break;
                    case "[AX]":
                        instrucao_bin += "0101";
                        break;
                    case "[BX]":
                        instrucao_bin += "0110";
                        break;
                    case "[CX]":
                        instrucao_bin += "0111";
                        break;
                    case "[DX]":
                        instrucao_bin += "1000";
                        break;
                }

                try {
                    instrucao_bin += DecimalParaBinario(Integer.parseInt(c2)); // caso o resto da instrucao seja um int
                }
                catch (Exception e) { // caso seja um registrador
                    switch (c2) {
                        case "AX":
                            instrucao_bin += "00000001";
                            break;
                        case "BX":
                            instrucao_bin += "00000010";
                            break;
                        case "CX":
                            instrucao_bin += "00000011";
                            break;
                        case "DX":
                            instrucao_bin += "00000100";
                            break;
                        case "[AX]":
                            instrucao_bin += "00000101";
                            break;
                        case "[BX]":
                            instrucao_bin += "00000110";
                            break;
                        case "[CX]":
                            instrucao_bin += "00000111";
                            break;
                        case "[DX]":
                            instrucao_bin += "00001000";
                            break;
                    }
                }

            } else {
                c1 = parts[1];
                try {
                    instrucao_bin += DecimalParaBinario(Integer.parseInt(c1)); // caso o resto da instrucao seja um int
                } catch (Exception e) { // caso seja um registrador
                    switch (c1) {
                        case "AX":
                            instrucao_bin += "000000000001";
                            break;
                        case "BX":
                            instrucao_bin += "000000000010";
                            break;
                        case "CX":
                            instrucao_bin += "000000000011";
                            break;
                        case "DX":
                            instrucao_bin += "000000000100";
                            break;
                        case "[AX]":
                            instrucao_bin += "000000000101";
                            break;
                        case "[BX]":
                            instrucao_bin += "000000000110";
                            break;
                        case "[CX]":
                            instrucao_bin += "000000000111";
                            break;
                        case "[DX]":
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
static void inc ()n int A{}
        add(A, 1);
    
    static void add(int A, int B) {
        ocd_ep2.AX = A + B;
    }
    static void sub(int A, int B){
        ocd_ep2.AX = A - B; 
    }
    static void mul(int A, int B){
        ocd_ep2.AX = A * B; 
    }
    static void div(int A, int B){
        ocd_ep2.AX = A/B; 
    }
    static void cmp(int A, int B){
        sub(A,B); //verifica conta passada if==0 entao eh igual
    }


    static void leCodigo(String code, int [] flags) {
        
        String instru = code.substring(0,3);
        String a = code.substring(4,7);
        String b = code.substring(9,15);
        int reg1 = 0;
        int reg2 = 0;

        switch (a) {
            case "0001":
                reg1 = ocd_ep2.AX;
                flags[5] = 1;  //porta de entrada do reg do diagrama - 1
                break;
            case "0010":
                reg1 = ocd_ep2.BX;
                flags[7] = 1;
                break;
            case "0011":
                reg1 = ocd_ep2.CX;
                flags[9] = 1;
                break;
            case "0100":
                reg1 = ocd_ep2.DX; //portas de DX entram entre a seq do diagrama
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
                reg2 = ocd_ep2.AX;
                flags[6] = 1;  //porta de saida do reg do diagrama - 1
                break;
            case "0010":
                reg2 = ocd_ep2.BX;
                flags[8] = 1;
                break;
            case "0011":
                reg2 = ocd_ep2.CX;
                flags[10] = 1;
                break;
            case "0100":
                reg2 = ocd_ep2.DX; 
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
            case "0010":
                inc(reg1);
            case "0011":
                add(reg1, reg2);
                break;
            case "0100":
                sub(reg1, reg2);
            case "0101":
                mul(reg1, reg2);
            case "0110":
                div(reg1, reg2);
            case "0111":
                cmp(reg1, reg2);
            case "1000":
            case "1001":
            case "1010":
            case "1011":
            case "1100":
            case "1101":
        }
    }

    static String busca(List<M> lista, int PC) {
        Iterator it = lista.iterator();

        while (it.hasNext()) {
            M MAR = lista.get(PC);
            if (MAR.endereco == PC) {
                PC++;
                return MAR.opCode;
            }
        }

        return "-1";
    }
    static void registradores() { // Printa os registradores apos ciclo

    }
    void verificaFlag(int [] flags){
        
    }

    public static void main(String[] args) throws IOException {
        List<M> fila = new LinkedList<M>(); // lista ligada com o binario e endereco
        int[] flags = new int[24]; // barramento 

        if (args[0] != null) {
            compilaCode(new FileReader(args[0]), fila);
        }

        leCodigo(busca(fila, 0), flags);

    }
}



