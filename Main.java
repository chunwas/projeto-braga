public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco() {};
        Horta horta = new Horta();
        Visao visao = new Visao();
        Controle controle = new Controle(banco, horta, visao);
        controle.executar();
    }
}
