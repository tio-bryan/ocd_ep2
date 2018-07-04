import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class EP2 {
    static void compilaCode(FileReader read) throws IOException {
        BufferedReader leitor = new BufferedReader(read);
        String line;
        String subString = "";
        String opCode;
        int i = 0;
        while ((line = leitor.readLine()) != null) {
            while (line.charAt(i) != ' ')  // separa o primeiro comando
                subString += line.charAt(i);

            switch (subString) {
                case "ADD":
                        opCode = "0001";

                        break;
                    case "MOV":
                        opCode = "0010";

                        break;
                    case "MUL":
                        opCode = "0011";

                        break;
                    case "DIV":
                        opCode = "0100";

                        break;
                    case "CMP":
                        opCode = "0101";

                        break;
                    case "INC":
                        opCode = "0110";

                        break;
                    case "JE":
                        opCode = "0111";

                        break;
                    case "JNE":
                        opCode = "1000";

                        break;
                    case "JG":
                        opCode = "1001";

                        break;
                    case "JGE":
                        opCode = "1010";

                        break;
                    case "JL":
                        opCode = "1011";

                        break;
                    case "JLE":
                        opCode = "1100";

                        break;
                }
            while (line.charAt(i) != ' ')
                
            }
        }


    public static void main(String[] args) throws IOException {
        List<M> lista = new LinkedList<M>(); // lista ligada com os comandos

        FileReader arquivo = new FileReader(args[0]);

        if (arquivo != null) {
            compilaCode(arquivo);
        }

    }
}



