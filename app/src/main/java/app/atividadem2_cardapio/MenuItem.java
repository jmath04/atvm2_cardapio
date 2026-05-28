package app.atividadem2_cardapio;

public class MenuItem {

    private String nome;
    private String preco;
    private String categoria;
    private int imagem;

    public MenuItem(String nome, String preco, String categoria, int imagem) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getImagem() {
        return imagem;
    }
}