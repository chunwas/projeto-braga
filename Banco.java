import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Banco {
    private Map<String, List<Map<String, String>>> tabelas;

    // Construtor
    public Banco() {
        this.tabelas = new HashMap<>();
        inicializarTabelas();
    }

    // Inicializar tabelas no banco
    protected void inicializarTabelas() {
        createTable("parcelas");
        createTable("plantas");
        createTable("regas");
        createTable("adubos");
    }

    // Criar uma nova tabela
    protected void createTable(String nomeTabela) {
        if (!tabelas.containsKey(nomeTabela)) {
            tabelas.put(nomeTabela, new ArrayList<>());
        }
    }

    // Inserir registro em uma tabela
    public void insert(String nomeTabela, Map<String, String> valores) {
        if (!tabelas.containsKey(nomeTabela)) {
            throw new IllegalArgumentException("Tabela '" + nomeTabela + "' não existe.");
        }
        tabelas.get(nomeTabela).add(new HashMap<>(valores));
        System.out.println("Registro inserido na tabela '" + nomeTabela + "': " + valores);
    }

    // Atualizar registros em uma tabela
    public void update(String nomeTabela, Map<String, String> novosValores, Map<String, String> condicoes) {
        if (!tabelas.containsKey(nomeTabela)) {
            throw new IllegalArgumentException("Tabela '" + nomeTabela + "' não existe.");
        }

        List<Map<String, String>> registros = tabelas.get(nomeTabela);
        boolean encontrou = false;

        for (Map<String, String> registro : registros) {
            if (satisfazCondicoes(registro, condicoes)) {
                registro.putAll(novosValores);
                encontrou = true;
            }
        }

        if (encontrou) {
            System.out.println("Registros atualizados na tabela '" + nomeTabela + "' com valores: " + novosValores);
        } else {
            System.out.println("Nenhum registro encontrado para as condições: " + condicoes);
        }
    }

    // Deletar registros de uma tabela
    public void delete(String nomeTabela, Map<String, String> condicoes) {
        if (!tabelas.containsKey(nomeTabela)) {
            throw new IllegalArgumentException("Tabela '" + nomeTabela + "' não existe.");
        }

        List<Map<String, String>> registros = tabelas.get(nomeTabela);
        boolean removido = registros.removeIf(registro -> satisfazCondicoes(registro, condicoes));

        if (removido) {
            System.out.println("Registros removidos da tabela '" + nomeTabela + "' para as condições: " + condicoes);
        } else {
            System.out.println("Nenhum registro encontrado para as condições: " + condicoes);
        }
    }

    // Selecionar registros de uma tabela
    public List<Map<String, String>> select(String nomeTabela) {
        if (!tabelas.containsKey(nomeTabela)) {
            throw new IllegalArgumentException("Tabela '" + nomeTabela + "' não existe.");
        }
        return new ArrayList<>(tabelas.get(nomeTabela));
    }

    // Verificar se o registro satisfaz as condições
    private boolean satisfazCondicoes(Map<String, String> registro, Map<String, String> condicoes) {
        return condicoes.entrySet().stream()
                .allMatch(condicao -> condicao.getValue().equals(registro.get(condicao.getKey())));
    }
}
