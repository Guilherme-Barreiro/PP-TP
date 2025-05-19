package Exceptions;

/**
 *
 * @author guiba
 */
public class TeamExceptions {

    /**
     * Exceção lançada quando se tenta adicionar um jogador nulo.
     */
    public static class NullPlayerException extends IllegalArgumentException {

        public NullPlayerException() {
            super("O jogador nao pode ser null.");
        }
    }

    /**
     * Exceção lançada quando se tenta adicionar mais do que 11 jogadores.
     */
    public static class TeamFullException extends IllegalStateException {

        public TeamFullException() {
            super("Nao e possivel adicionar mais de 11 jogadores a equipa.");
        }
    }

    /**
     * Exceção lançada quando a formação ainda não foi definida.
     */
    public static class FormationNotSetException extends IllegalArgumentException {

        public FormationNotSetException() {
            super("A formacao ainda não foi definida.");
        }
    }

    /**
     * Exceção lançada quando o jogador já foi adicionado à equipa.
     */
    public static class PlayerAlreadyInTeamException extends IllegalStateException {

        public PlayerAlreadyInTeamException() {
            super("O jogador ja se encontra na equipa.");
        }
    }

    /**
     * Exceção lançada quando já existe um guarda-redes na equipa.
     */
    public static class GoalkeeperAlreadyExistsException extends IllegalStateException {

        public GoalkeeperAlreadyExistsException() {
            super("Ja existe um guarda-redes na equipa.");
        }
    }

    
    
}
