package beans;


public class Curso {
    private int id;
    private String nomecurso;
    private String nivel;
    private int duracao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    //o método toString é a representação do objeto em String.
    //Significa que quando um objeto do tipo "Curso" for printado,
    //ele imprime o que estiver sendo retornado por esse método. 
    public String toString(){
       return this.nomecurso;
    }
    public boolean equals(Object object){
        Curso c = (Curso) object;
        if(this.id == c.getId()){
            return true;
        }else{
            return false;
        }    
    } 
}
