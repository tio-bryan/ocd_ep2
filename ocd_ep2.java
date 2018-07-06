import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ocd_ep2 {
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

    static void compilaCode(FileReader read, List<M> fila) throws IOException {
        BufferedReader leitor = new BufferedReader(read);
        String line;
        int length; // tamanho de parte do comando com inteiro
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
                    length = 8;
                    instrucao_bin += DecimalParaBinario(Integer.parseInt(c2), length); // caso o resto da instrucao seja um int
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
                    length = 0;
                    instrucao_bin += DecimalParaBinario(Integer.parseInt(c1), length); // caso o resto da instrucao seja um int
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
            System.out.println(instrucao_bin.length());
            System.out.println(instrucao_bin);
            M objeto = new M(enderecoMEM, instrucao_bin);
            fila.add(objeto);
            enderecoMEM++;
        }
    }

    static void leCodigo(List<M> fila){
        String bin = fila.get(0).opCode;
        String instru = bin.substring(0,3);

        switch (instru){
            case "0001":
            case "0010":
            case "0011":
            case "0100":
            case "0101":
            case "0110":
            case "0111":
            case "1000":
            case "1001":
            case "1010":
            case "1011":
            case "1100":
            case "1101":

        }
    }

    public static void main(String[] args) throws IOException {
        List<M> fila = new LinkedList<M>(); // lista ligada com o binario e endereco
        int[] barramento = new int[13]; // barramento 

        if (args[0] != null) {
            compilaCode(new FileReader(args[0]), fila);
            // hurr durr
        }


    }
}



