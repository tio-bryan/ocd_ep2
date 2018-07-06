
public class M {
    String opCode; //4 bits de comando + 4 bits de registrador + 8 para registrador ou constante
    int endereco;

    public M(int end, String conteudo) {
        this.opCode = conteudo;
        this.endereco = end;
    }
}
