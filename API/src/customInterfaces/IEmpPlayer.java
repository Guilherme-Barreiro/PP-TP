package customInterfaces;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

public interface IEmpPlayer extends IPlayer, Cloneable {

    /**
     * Método para clonar o jogador.
     * 
     * @return uma cópia deste jogador
     */
    IEmpPlayer clone();

    /**
     * Devolve a pontuação geral do jogador, calculada com base nos atributos.
     * 
     * @return valor inteiro representando a qualidade geral
     */
    int getOverallRating();

    /**
     * Aumenta a capacidade de remate do jogador em certa quantidade.
     * 
     * @param amount quantidade a treinar
     */
    void trainShooting(int amount);

    /**
     * Método extra para resetar o estado do jogador, útil para testes.
     */
    void resetStats();
}
