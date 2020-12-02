package ec.edu.ups.modelo;

/**
 *
 * @author Sebastian Uyaguari
 */
public class Curso {
    
    private int codigo;
    private String curso;
    private int alumnos;
    private Persona docente;

    public Curso() {
    }

    public Curso(int codigo, String curso, int alumnos, Persona docente) {
        this.setCodigo(codigo);
        this.setCurso(curso);
        this.setAlumnos(alumnos);
        this.setDocente(docente);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = validarEspacios(curso, 15);
    }

    public int getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(int alumnos) {
        this.alumnos = alumnos;
    }

    public Persona getDocente() {
        return docente;
    }

    public void setDocente(Persona docente) {
        this.docente = docente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Curso other = (Curso) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cursos{" + "codigo=" + codigo + ", curso=" + curso 
                + ", alumnos=" + alumnos + ", docente=" + docente + '}';
    }
    
    public String validarEspacios(String cadena, int numero){
        if(cadena.length()==numero){
            return cadena;
        }else{
            if(cadena.length()>numero){
                cadena = cadena.substring(0,numero);
                return cadena;
            }else{
                for (int i = cadena.length(); i < numero; i++) {
                    cadena+=" ";
                }
                return cadena;
            }
        }
    }
}
