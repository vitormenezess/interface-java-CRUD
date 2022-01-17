//padrão de projeto Data Access Object
package dao;

import beans.Aluno;
import beans.Curso;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AlunoDao {
    private Conexao conexao;
    private Connection conn;
    
    
    public AlunoDao(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Aluno aluno){
        String sql = "INSERT INTO alunos (nomealuno, cursoid) Values "
                + "(?, ?)";
        try{       
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNomeAluno());
            stmt.setInt(2, aluno.getCursoid().getId());
            stmt.execute();
        }
        catch(Exception e){
            System.out.println("Erro ao Inserir aluno: "+e.getMessage());  
        }
    }
    public void editar(Aluno aluno){
        String sql = "UPDATE alunos SET nomealuno=?, cursoid=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNomeAluno());
            stmt.setInt(2, aluno.getCursoid().getId());
            stmt.setInt(3,aluno.getId());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ao editar aluno:" +e.getMessage());
        }
    }
    public void excluir(int id){
        String sql= "DELETE FROM alunos WHERE id =?";
        try {
            PreparedStatement stmt =  this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            
        } catch (Exception e) {
            System.out.println("Erro ao excluir aluno: "+e.getMessage());
        }
    }
    public Aluno getAluno(int id){
        String sql = "SELECT * FROM  alunos WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Aluno aluno = new Aluno();
            
            rs.first();
            aluno.setId(id);
            aluno.setNomeAluno(rs.getString("nomealuno"));
            Curso cursoid = new Curso();
            cursoid.setId(rs.getInt("cursoid"));
            aluno.setCursoid(cursoid);   
            return aluno;
            
        } catch (Exception e) {
            return null;
        }
    }
    public List<Aluno> getAlunos(){
        String  sql = "SELECT alunos.id as id, nomealuno, cursoid, nomecurso FROM "
                +"alunos INNER JOIN cursos ON alunos.cursoid = cursos.id ";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            List<Aluno> lista = new ArrayList<>();
            
            while(rs.next()){
                Aluno aluno = new Aluno();
                Curso curso = new Curso();
                
                aluno.setId((rs.getInt("id")));
                aluno.setNomeAluno(rs.getString("nomealuno"));
                curso.setId(rs.getInt("cursoid"));
                curso.setNomecurso(rs.getString("nomecurso"));
                aluno.setCursoid(curso);
                lista.add(aluno);
            }
            return lista;  
        } catch (Exception e) {
            return null;
        }
    }
      
    
}

