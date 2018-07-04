import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ocd_ep2 {
    // Baseado no http://www.guj.com.br/t/transforma-decimal-em-binario/47061
    public static String DecimalParaBinario(int c2) {
        int d = c2;
        StringBuffer binario = new StringBuffer(); // guarda os dados

        while (d > 0) {
            int b = d % 2;
            binario.append(b);
            d = d >> 1; // é a divisão que você deseja
        }
        
        for (int i = 0; i <= (9 - binario.length()); i++) {
            binario.append(0);
        }

        return binario.reverse().toString(); // inverte a ordem e imprime
    }

    static void compilaCode(FileReader read) throws IOException {
        BufferedReader leitor = new BufferedReader(read);
        String line;

        String instrucao = "";
        String c1 = "";
        String c2 = "";

        String instrucao_bin = "";
        String c1_bin = "000000";
        String c2_bin = "000000";

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
                        instrucao_bin += "001";
                        break;
                    case "BX":
                        instrucao_bin += "010";
                        break;
                    case "CX":
                        instrucao_bin += "011";
                        break;
                    case "DX":
                        instrucao_bin += "100";
                        break;
                }

                try {
                    instrucao_bin += DecimalParaBinario(Integer.parseInt(c2));

                } catch (Exception e) {
                    switch (c2) {
                        case "AX":
                            instrucao_bin += "000000001";
                            break;
                        case "BX":
                            instrucao_bin += "000000010";
                            break;
                        case "CX":
                            instrucao_bin += "000000011";
                            break;
                        case "DX":
                            instrucao_bin += "000000100";
                            break;
                    }
                }

            } else {
                c1 = parts[1];
            }

            System.out.println(instrucao_bin);
        }

        // while ((line = leitor.readLine()) != null) {
        //     while (line.charAt(i) != ' ')  // separa o primeiro comando
        //         instrucao += line.charAt(i);

            
        //     while (line.charAt(i) != ' ') {

        //     }    
        // }
    }

    public static void main(String[] args) throws IOException {
        //List<M> lista = new LinkedList<M>(); // lista ligada com os comandos

        if (args[0] != null) {
            compilaCode(new FileReader(args[0]));
        }
    }
}



