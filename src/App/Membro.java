package App;

public class Membro {
    private int id;
    private boolean lider;
    private boolean ativo; 

    public Membro(int id, boolean lider, boolean ativo) {
        this.id = id;
        this.lider = lider;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public boolean isLider() {
        return lider;
    }

    public void setLider(boolean lider) {
        this.lider = lider;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
