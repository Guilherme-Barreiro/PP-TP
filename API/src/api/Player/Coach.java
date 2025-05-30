/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.Player;

import com.ppstudios.footballmanager.api.contracts.team.IClub;

/**
 * Representa um treinador (Coach) associado a um clube de futebol.
 *
 * A classe permite consultar, modificar ou trocar o clube atual ao qual o
 * treinador está associado.
 */
public class Coach {

    private IClub club;

    /**
     * Constrói um novo treinador associado a um clube específico.
     *
     * @param club Clube inicial atribuído ao treinador.
     */
    public Coach(IClub club) {
        this.club = club;
    }

    /**
     * Devolve o clube atualmente associado ao treinador.
     *
     * @return Clube atual.
     */
    public IClub getClub() {
        return club;
    }

    /**
     * Define ou atualiza o clube associado ao treinador.
     *
     * @param club Novo clube a associar.
     */
    public void setClub(IClub club) {
        this.club = club;
    }

    /**
     * Altera o clube do treinador para um novo clube.
     *
     * @param newClub Novo clube a ser atribuído.
     */
    public void changeClub(IClub newClub) {
        this.club = newClub;
    }

    /**
     * Devolve uma representação textual do treinador, mostrando o nome do clube
     * associado.
     *
     * @return String no formato "Coach{club=NomeDoClube}".
     */
    public String toString() {
        return "Coach{club=" + club.getName() + "}";
    }
}
